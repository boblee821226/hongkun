package nc.pubimpl.arap.bill;

import hd.vo.pub.tools.PuPubVO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import nc.bs.arap.actions.BillTempSaveBatchAction;
import nc.bs.arap.bill.ArapBillPubUtil;
import nc.bs.arap.util.ArapBillType2TableMapping;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.itf.fipub.service.IArapEJBService;
import nc.itf.uap.pf.IPFMetaModel;
import nc.itf.uap.pf.IWorkflowDefine;
import nc.pubitf.arap.bill.IArapBillPubService;
import nc.vo.arap.basebill.BaseAggVO;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.arap.gathering.GatheringBillVO;
import nc.vo.arap.pf.PFCheckVO;
import nc.vo.arap.pub.ArapBillTypeInfo;
import nc.vo.arap.service.ServiceVO;
import nc.vo.arap.utils.ArrayUtil;
import nc.vo.fipub.billcode.FinanceBillCodeUtils;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

import org.apache.commons.lang.ArrayUtils;


public class ArapBillPubServiceImpl implements IArapBillPubService {

	@Override
	public void delete(BaseAggVO[] bills) throws BusinessException {
		check(bills);

		List<ServiceVO> servicevos = constructServiceVO(bills, "delete");

		// 批量调用功能接口
		NCLocator.getInstance().lookup(IArapEJBService.class)
				.callBatchEJBService(servicevos.toArray(new ServiceVO[0]));

	}

	@Override
	public void delete(BaseAggVO bill) throws BusinessException {
		delete(new BaseAggVO[] { bill });
	}

	@Override
	public BaseAggVO save(BaseAggVO bill) throws BusinessException {
		return ArrayUtil.getFirstInArrays(save(new BaseAggVO[] { bill }));
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseAggVO[] save(BaseAggVO[] bills) throws BusinessException {
		check(bills);

		List<ServiceVO> servicevos = constructServiceVO(bills, "save");

		// 批量调用功能查询接口
		Map<String, Object> callBatchEJBService = NCLocator.getInstance()
				.lookup(IArapEJBService.class).callBatchEJBService(
						servicevos.toArray(new ServiceVO[0]));
		Set<Entry<String, Object>> retEntry = callBatchEJBService.entrySet();

		//查询数据中抽取数据
		List<BaseAggVO> retList=new ArrayList<BaseAggVO>(callBatchEJBService.size());
		for (Entry<String, Object> entry : retEntry) {
			if(entry.getValue().getClass().isArray()){
				 BaseAggVO[] array=(BaseAggVO[]) entry.getValue();
				 retList.addAll(Arrays.asList(array));

			}else{
				retList.addAll((Collection<? extends BaseAggVO>) entry.getValue());
			}
		}
		return retList.toArray(new BaseAggVO[0]);
	}

	/** */
	private void check(BaseAggVO[] bills) throws BusinessException {
		// 判断为空
		if (ArrayUtils.isEmpty(bills))
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006pub_0","02006pub-0359")/*@res "参数不能为空"*/);
		Set<BaseAggVO> nonempties = new HashSet<BaseAggVO>();
		for (BaseAggVO vo : bills) {
			nonempties.add(vo);
		}
		if(nonempties.size() ==0){
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006pub_0","02006pub-0359")/*@res "参数不能为空"*/);
		}
	}

	/**
	 * 更新单据状态
	 */
	public AggregatedValueObject update(BaseAggVO bill)
			throws BusinessException {
		this.setBillCode(new AggregatedValueObject[]{bill});
		AggregatedValueObject res = ArrayUtil.getFirstInArrays(update(new BaseAggVO[] { bill }));
		
		if (res instanceof AggGatheringBillVO){
			/**
			  * HK 2021年1月5日01:11:22
			  * 回写合同
			  */
			AggGatheringBillVO item = (AggGatheringBillVO)res;
			BaseDAO daseDAO = new BaseDAO();
			GatheringBillVO hVO = item.getHeadVO();
			GatheringBillItemVO[] bVOs = item.getBodyVOs();
			Integer sysCode = hVO.getSyscode();
			if (sysCode != 0) return res;	// 只处理 协同生成的
			String pk_tradetype = hVO.getPk_tradetype();	// F2-Cxx-90 
			if (!"F2-Cxx-90".equals(pk_tradetype)) return res;// 只处理 内部交易付款单
			ArrayList<String> pkList = new ArrayList<>();
			for (GatheringBillItemVO bItem : bVOs) {
				String def29 = PuPubVO.getString_TrimZeroLenAsNull(bItem.getDef29());
				if (def29 != null) pkList.add(def29);
			}
			if (pkList.isEmpty()) return res;	// 合同pk为空  不处理
			{// 进行同步
				String pkListStr = PuPubVO.getSqlInByList(pkList);
				String updateSQL = "update ct_sale_b htb " +
				 " set htb.ntotalgpmny = nvl((select sum(skb.local_money_cr) " +
					                     " from ar_gatheritem skb " +
					                     " inner join ar_gatherbill sk on skb.pk_gatherbill = sk.pk_gatherbill " +
					                     " where skb.dr = 0 and sk.dr = 0 " +
					                     " and sk.coordflag = 1 " +	// 只取确认状态的
					                     " and sk.src_syscode = 9 and sk.pk_tradetype = 'F2-Cxx-90' " +
					                     " and skb.def29 = htb.pk_ct_sale_b " +
					                     " group by skb.def29), 0) " +
                 ", htb.noritotalgpmny = nvl((select sum(skb.money_cr) " +
					                     " from ar_gatheritem skb " +
					                     " inner join ar_gatherbill sk on skb.pk_gatherbill = sk.pk_gatherbill " +
					                     " where skb.dr = 0 and sk.dr = 0 " +
					                     " and sk.coordflag = 1 " +	// 只取确认状态的
					                     " and sk.src_syscode = 9 and sk.pk_tradetype = 'F2-Cxx-90' " +
					                     " and skb.def29 = htb.pk_ct_sale_b " +
					                     " group by skb.def29), 0) " +
			     " where htb.pk_ct_sale_b in " + pkListStr + " " 
			     ;
				int flag = daseDAO.executeUpdate(updateSQL);
				System.out.println(flag);
			}
		}
		
		return res;
	}

	/**
	 * 生成单据号
	 * @param bills
	 * @throws BusinessException
	 */
	void setBillCode(AggregatedValueObject[] bills) throws BusinessException {
		FinanceBillCodeUtils util = ArapBillPubUtil.getBillCodeUtil(bills[0]);
		List<AggregatedValueObject> lastVo = new ArrayList<AggregatedValueObject>();
		for (AggregatedValueObject bill : bills) {
			if (!util.isPrecode(bill))
				lastVo.add(bill);
		}
		if (lastVo.size() > 0)
			util.createBillCode(lastVo.toArray(new AggregatedValueObject[lastVo.size()]));
	}

	@SuppressWarnings("unchecked")
	@Override
	public AggregatedValueObject[] update(BaseAggVO[] bills)
			throws BusinessException {
		check(bills);

		List<ServiceVO> servicevos = constructServiceVO(bills, "update");

		// 批量调用功能查询接口
		Map<String, Object> callBatchEJBService = NCLocator.getInstance()
				.lookup(IArapEJBService.class).callBatchEJBService(
						servicevos.toArray(new ServiceVO[0]));
		Set<Entry<String, Object>> retEntry = callBatchEJBService.entrySet();

		//查询数据中抽取数据
		List<BaseAggVO> retList=new ArrayList<BaseAggVO>(callBatchEJBService.size());
		for (Entry<String, Object> entry : retEntry) {
			if(entry.getValue().getClass().isArray()){
				 BaseAggVO[] array=(BaseAggVO[]) entry.getValue();
				 retList.addAll(Arrays.asList(array));

			}else{
				retList.addAll((Collection<? extends BaseAggVO>) entry.getValue());
			}
		}
		return retList.toArray(new BaseAggVO[0]);
	}

	private List<ServiceVO> constructServiceVO(BaseAggVO[] bills,
			String methodName) {
		// 按单据类型归类
		Map<String, Set<BaseAggVO>> billtype2pk = new HashMap<String, Set<BaseAggVO>>();

		for (BaseAggVO baseAggVO : bills) {
			Set<BaseAggVO> set = billtype2pk.get(baseAggVO.getHeadVO()
					.getPk_billtype());
			if (null == set) {
				set = new HashSet<BaseAggVO>();
				billtype2pk.put(baseAggVO.getHeadVO().getPk_billtype(), set);
			}
			set.add(baseAggVO);
		}

		// 遍历单据类型， 创建ServiceVO调用接口服务类
		List<ServiceVO> servicevos = new ArrayList<ServiceVO>();
		for (Entry<String, Set<BaseAggVO>> entry : billtype2pk.entrySet()) {

			String billtype = entry.getKey();
			ServiceVO servicevo = new ServiceVO();
			// 方法名
			servicevo.setMethodname(methodName);
			// 接口查询接口名称
			servicevo.setClassname(ArapBillType2TableMapping
					.getPubServiceByBilltype(entry.getKey()));
			// 查询的参数
			BaseAggVO[] superVO = entry.getValue().toArray(new BaseAggVO[0]);
			Class<IBill> subVoClazz = ArapBillType2TableMapping
			.getVOClassByBilltype(billtype);

			IBill[] newSuperVO = (IBill[]) Array.newInstance(subVoClazz, superVO.length);
			try {
				System.arraycopy(superVO, 0, newSuperVO, 0, superVO.length);
			} catch (ArrayStoreException ex) {
				for(int i = 0 ; i< (newSuperVO.length) ; i++){
					newSuperVO[i] = superVO[i];
				}
			}
			servicevo.setParam(new Object[]{newSuperVO});
			// 查询的参数类型
			servicevo.setParamtype(new Class[] { Array.newInstance(subVoClazz , 0).getClass()});
			servicevos.add(servicevo);
		}
		return servicevos;
	}


	@Override
	public BaseAggVO approve(BaseAggVO bill) throws BusinessException {
		return ArrayUtil.getFirstInArrays(approve(new BaseAggVO[] { bill }));
	}

	@SuppressWarnings("unchecked")
	@Override
	public BaseAggVO[] approve(BaseAggVO[] bills) throws BusinessException {
		check(bills);

		for(BaseAggVO bill:bills){
			if(bill!=null){
				bill.getHeadVO().setForceEffect(true);
			}
		}
		
		List<ServiceVO> servicevos = constructServiceVO(bills, "approve");

		// 批量调用功能查询接口
		Map<String, Object> callBatchEJBService = NCLocator.getInstance()
				.lookup(IArapEJBService.class).callBatchEJBService(
						servicevos.toArray(new ServiceVO[0]));
		Set<Entry<String, Object>> retEntry = callBatchEJBService.entrySet();

		//查询数据中抽取数据
		List<BaseAggVO> retList=new ArrayList<BaseAggVO>(callBatchEJBService.size());
		for (Entry<String, Object> entry : retEntry) {
			if(entry.getValue().getClass().isArray()){
				 BaseAggVO[] array=(BaseAggVO[]) entry.getValue();
				 retList.addAll(Arrays.asList(array));

			}else{
				retList.addAll((Collection<? extends BaseAggVO>) entry.getValue());
			}
		}
		return retList.toArray(new BaseAggVO[0]);
	}

	public BaseAggVO tempSave(BaseAggVO bill) throws BusinessException {
		return ArrayUtil.getFirstInArrays(tempSave(new BaseAggVO[]{bill}));
	}

	public BaseAggVO[] tempSave(BaseAggVO[] bills) throws BusinessException {
		return ArrayUtil.convertSupers2Subs(new BillTempSaveBatchAction().insertVOs(bills), BaseAggVO.class);
	}

	
	
	private String buildCacheKey(PFCheckVO vo){
		return vo.getPk_group()+vo.getPk_org()+vo.getPk_tradetype()+vo.getOperator()+vo.getWorkflowtype();
	}
	private String buildCacheKey2(PFCheckVO vo){
		return  vo.getPk_tradetype() +vo.getPk_busitype();
	}
	
	@Override
	public boolean messageDrive(PFCheckVO[] checkVOs) throws BusinessException {
		Map<String, Boolean> cache = new HashMap<String, Boolean>();
		IWorkflowDefine wfDefine = NCLocator.getInstance().lookup(IWorkflowDefine.class);
		for (PFCheckVO check : checkVOs) {
			String key = buildCacheKey(check);
			if (cache.get(key) == null) {
				// 如果不是修订流程，传-1
				boolean flag = wfDefine.hasValidProcessDef(check.getPk_group(), check.getPk_tradetype(), check
								.getPk_org(), check.getOperator(),-1 , check.getWorkflowtype());
				cache.put(key, flag);
			}

			if (cache.get(key)) {
				return true;
			}
		}

		Map<String, Boolean> cache2 = new HashMap<String, Boolean>();
		IPFMetaModel pfmodel = (NCLocator.getInstance().lookup(IPFMetaModel.class));
		for (PFCheckVO check : checkVOs) {
			String key = buildCacheKey2(check);
			if (cache2.get(key) == null) {
				MessagedriveVO[] msgVOs = pfmodel.queryAllMsgdrvVOs(check.getPk_tradetype(), check.getPk_busitype(),
								null);
				if (msgVOs == null || msgVOs.length == 0 || msgVOs[0] == null) {
					cache2.put(key, false);
				}else {
					cache2.put(key, true);
				}
				
				if (cache2.get(key)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String validateFlowBillCurr(AggregatedValueObject[] bills) {
		if(bills==null || bills.length==0 || bills[0]==null){
			return null;
		}
		
		String billtype = (String) bills[0].getParentVO().getAttributeValue(IBillFieldGet.PK_BILLTYPE);
		if(billtype==null)
			return null;
		
		Map<String,String> topPkMap=new HashMap<String, String>();
		Set<String> topBilltypes=new HashSet<String>();
		for(AggregatedValueObject bill:bills){
			CircularlyAccessibleValueObject[] childrenVO = bill.getChildrenVO();
			for(CircularlyAccessibleValueObject child:childrenVO){
				String top_billtype = (String) child.getAttributeValue(IBillFieldGet.TOP_BILLTYPE);
				String top_itemid = (String) child.getAttributeValue(IBillFieldGet.TOP_ITEMID);
				String pk_curr = (String) child.getAttributeValue(IBillFieldGet.PK_CURRTYPE);
				if(top_billtype!=null && top_billtype.trim().length()!=0 
						&& top_itemid!=null && top_itemid.trim().length()!=0
						&& pk_curr!=null && pk_curr.trim().length()!=0){
					topBilltypes.add(top_billtype);
					
					if(top_billtype.equals(IBillFieldGet.F0) || top_billtype.equals(IBillFieldGet.F1) ||top_billtype.equals(IBillFieldGet.F2) ||top_billtype.equals(IBillFieldGet.F3)){
						topPkMap.put(top_itemid,pk_curr);
					}
				}
			}
		}
		if(topBilltypes.size()>1){
			return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006arappub0523_0","02006arappub0523-0054");/*@res "流程单据不能包括来自不同单据类型的行!"*/
		}
		
		if(topBilltypes.size()==0){
			return null;
		}
		if(topPkMap.size()==0){
			return null;
		}
		
//		IArapBilltypeInfo info = ArapBillTypeInfo.getInstance(topBilltypes.iterator().next());
		
//		try {
//			Map<String,String> currMap = (Map<String, String>) new BaseDAO().executeQuery("select pk_currtype,"+info.getTablePrimaryKeyItem()+" from "+info.getTableNameItem() +" where "+SqlUtils.getInStr(info.getTablePrimaryKeyItem(), topPkMap.keySet().toArray(new String[]{})), new ResultSetProcessor() {
//				@Override
//				public Object handleResultSet(ResultSet rs) throws SQLException {
//					Map<String,String> map=new HashMap<String, String>();
//					while(rs.next()){
//						map.put(rs.getString(2), rs.getString(1));
//					}
//					return map;
//				}
//			});
//			for(String key:currMap.keySet()){
//				if(!currMap.get(key).equals(topPkMap.get(key))){
//					return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("2006arappub0523_0","02006arappub0523-0055");/*@res "流程单据不能包括和上游单据行不同币种的行!"*/
//				}
//			}
//		} catch (DAOException e) {
//			ExceptionHandler.consume(e);
//		}
		
		return null;
	}


	@Override
	public List<String> checkCancelPrintOfficialPrintStatus(BaseAggVO data, String[] primaryKeys) throws BusinessException {
		List<String> nopeOfficialPrint = new ArrayList<String>();
		String offPrtUser = null;
		BaseAggVO[] vos = NCLocator.getInstance().lookup(ArapBillTypeInfo.getInstance(data).getBillQueryService()).findBillByPrimaryKey(primaryKeys);
		for(BaseAggVO vo : vos){
			offPrtUser = (String)(vo).getHeadVO().getOfficialprintuser();
			if(offPrtUser == null || offPrtUser.trim().length() == 0){
				nopeOfficialPrint.add(vo.getHeadVO().getBillno());
			}
		}
		return nopeOfficialPrint;
	}
	
}