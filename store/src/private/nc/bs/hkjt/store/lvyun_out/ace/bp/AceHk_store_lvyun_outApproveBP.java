package nc.bs.hkjt.store.lvyun_out.ace.bp;

import hd.vo.pub.tools.PuPubVO;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TimeZone;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.itf.uap.pf.IplatFormEntry;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBVO;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreHVO;
import nc.vo.ic.m4d.entity.MaterialOutBodyVO;
import nc.vo.ic.m4d.entity.MaterialOutHeadVO;
import nc.vo.ic.m4d.entity.MaterialOutVO;

/**
 * 标准单据审核的BP
 */
public class AceHk_store_lvyun_outApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 * @throws BusinessException 
	 */
	public LyOutStoreBillVO[] approve(LyOutStoreBillVO[] clientBills,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		/**
		 * 按单循环处理，支持批量操作
		 */
		ArrayList<String> dept_list = new ArrayList<String>();// 部门list
		// 分摊数据
		HashMap<LyOutStoreHVO,HashMap<String,ArrayList<LyOutStoreBVO>>> FENDAN = new HashMap<>();
		for (LyOutStoreBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
			// 第一次循环，进行分单，获得出deptPK
			// 分单规则 = 单子vo + 部门
			LyOutStoreHVO hVO = clientBill.getParentVO();
			LyOutStoreBVO[] bVOs = (LyOutStoreBVO[])clientBill.getChildren(new LyOutStoreBVO().getMetaData());
			HashMap<String,ArrayList<LyOutStoreBVO>> bVO_map = new HashMap<>();
			for (LyOutStoreBVO bVO : bVOs) {
				String key = bVO.getPk_dept();
				if (!bVO_map.containsKey(key)) {
					bVO_map.put(key, new ArrayList<LyOutStoreBVO>());
				}
				bVO_map.get(key).add(bVO);
				// 添加 部门list
				if (!dept_list.contains(key)) {
					dept_list.add(key);
				}
			}
			FENDAN.put(hVO, bVO_map);
		}
		// 第二次循环之前，先获取到 档案
		this.init_doc(dept_list);
		// 第二次循环，按照分单结果，去封装出来要保持的单据。
		ArrayList<MaterialOutVO> save_list = new ArrayList<MaterialOutVO>();
		for (Entry<LyOutStoreHVO,HashMap<String,ArrayList<LyOutStoreBVO>>> item : FENDAN.entrySet()) {
			LyOutStoreHVO hVO = item.getKey();
			HashMap<String,ArrayList<LyOutStoreBVO>> bVO_map = item.getValue();
			save_list = this.gen_bill(hVO, bVO_map, save_list);
		}
		
		// 标准产品的更新状态
		BillUpdate<LyOutStoreBillVO> update = new BillUpdate<LyOutStoreBillVO>();
		LyOutStoreBillVO[] returnVos = update.update(clientBills, originBills);
		
		// 进行保存
		if (save_list != null && !save_list.isEmpty()) {
			IplatFormEntry iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
			
			for (MaterialOutVO ckBillVO : save_list) {
				Object res = iplatFormEntry.processAction(
					"WRITE"
					, "4D"
					, null
					, ckBillVO
					, null
					, null
				);
			}
		}
		
		return returnVos;
	}
	
	/**
	 * DAO
	 */
	private BaseDAO DAO = null;
	private BaseDAO getDAO() {
		if (DAO == null) {
			DAO = new BaseDAO();
		}
		return DAO;
	}
	/**
	 * 档案数据的准备
	 * key：pk_source  value：pk_v
	 */
	private HashMap<String,String> DOC_DEPT;
	private String BILL_TYPE_CODE;	// 4D-Cxx-002
	private String BILL_TYPE_ID;
	private void init_doc(ArrayList<String> dept_list) throws BusinessException {
		/**
		 * 交易类型
		 */
		{
			BILL_TYPE_CODE = "4D-Cxx-002";
			StringBuffer querySQL = 
				new StringBuffer("select pk_billtypeid from bd_billtype ")
						.append(" where pk_billtypecode = '").append(BILL_TYPE_CODE).append("' ")
				;
			ArrayList list = (ArrayList)this.getDAO().executeQuery(querySQL.toString(), new ArrayListProcessor());
			if (list == null || list.isEmpty()) {
				throw new BusinessException("交易类型不存在："+BILL_TYPE_CODE);
			}
			BILL_TYPE_ID = PuPubVO.getString_TrimZeroLenAsNull(((Object[])list.get(0))[0]);
		}
		/**
		 * 部门档案
		 * key：id  value：vid
		 */
		{
			DOC_DEPT = new HashMap<String,String>();
			StringBuffer querySQL_dept = 
				new StringBuffer("select pk_dept,pk_vid from org_dept_v ")
						.append("where dr = 0 ")
						.append("and pk_dept in ").append(PuPubVO.getSqlInByList(dept_list)).append(" ")
				;
			ArrayList list_dept = (ArrayList)this.getDAO().executeQuery(querySQL_dept.toString(), new ArrayListProcessor());
			if (list_dept == null || list_dept.isEmpty()) {
				throw new BusinessException("查询部门为空");
			}
			for (Object item : list_dept) {
				Object[] obj = (Object[])item;
				DOC_DEPT.put(
					  PuPubVO.getString_TrimZeroLenAsNull(obj[0])
					, PuPubVO.getString_TrimZeroLenAsNull(obj[1])
				);
			}
		}
	}
	/**
	 * 封装 库存材料出库单
	 */
	private ArrayList<MaterialOutVO> gen_bill(
			LyOutStoreHVO ly_hVO,
			HashMap<String,ArrayList<LyOutStoreBVO>> ly_bVO_map,
			ArrayList<MaterialOutVO> ck_list) {
		
		String pk_group = ly_hVO.getPk_group();
		String pk_org = ly_hVO.getPk_org();
		String pk_org_v = ly_hVO.getPk_org_v();
		UFDate billDate = ly_hVO.getDbilldate();
		String pk_head = ly_hVO.getPk_hk_store_lvyun_out();
		String vbillcode = ly_hVO.getVbillcode();
		UFDateTime ts_head = ly_hVO.getTs();
		
		// 根据 ly_bVO_map 分单
		for (Entry<String,ArrayList<LyOutStoreBVO>> item : ly_bVO_map.entrySet()) {
			ArrayList<LyOutStoreBVO> ly_bVO_list = item.getValue();
			
			String pk_dept = ly_bVO_list.get(0).getPk_dept();
			String pk_dept_v = DOC_DEPT.get(pk_dept);
			String pk_store = ly_bVO_list.get(0).getPk_store();
			String yyd = ly_bVO_list.get(0).getYyd_name();
			
			MaterialOutHeadVO ck_hVO = this.gen_hvo(
					pk_group, pk_org, pk_org_v, pk_dept, pk_dept_v, pk_store, billDate
					, yyd
					);
			MaterialOutBodyVO[] ck_bVOs = new MaterialOutBodyVO[ly_bVO_list.size()];
			for (int i = 0; i < ck_bVOs.length; i++) {
				String rowno = "" + ((i+1)*10);
				LyOutStoreBVO ly_bVO = ly_bVO_list.get(i);
				UFDouble num = ly_bVO.getSp_out_quantity();
				String pk_inv = ly_bVO.getVbdef01();
				String pk_inv_v = ly_bVO.getPk_inv();
				String pk_unit = ly_bVO.getVbdef02();
				String pk_body = ly_bVO.getPk_hk_store_lvyun_out_b();
				String vrowno = ly_bVO.getVrowno();
				UFDateTime ts_body = ly_bVO.getTs();
				String pk_cklb = ly_bVO.getVbdef03();
				
				ck_bVOs[i] = this.gen_bvo(
					pk_group, pk_org, pk_org_v, pk_dept_v, pk_dept_v, pk_store, billDate
					, num, rowno, pk_inv, pk_inv_v, pk_unit
					, pk_head, pk_body, vbillcode, vrowno, ts_head, ts_body
					, pk_cklb
					, yyd
					);
			}
			
			MaterialOutVO billVO = new MaterialOutVO();
			billVO.setParentVO(ck_hVO);
			billVO.setChildrenVO(ck_bVOs);
			
			ck_list.add(billVO);
		}

		return ck_list;
	}
	
	/**
	 * 封装材料出库表头vo
	 */
	private MaterialOutHeadVO gen_hvo(
			 String pk_group
			,String pk_org
			,String pk_org_v
			,String pk_dept
			,String pk_dept_v
			,String pk_store
			,UFDate billDate
			,String yyd			// 营业点
			) {
		MaterialOutHeadVO hVO = new MaterialOutHeadVO();
		// 集团
		hVO.setPk_group(pk_group);			// "0001N510000000000EGY"
		// 组织、组织v
		hVO.setCdrawcalbodyoid(pk_org);		// "0001N510000000001SXV"
		hVO.setCdrawcalbodyvid(pk_org_v);	// "0001N510000000001SXU"
		hVO.setCorpoid(pk_org);
		hVO.setCorpvid(pk_org_v);
		hVO.setPk_org(pk_org);
		hVO.setPk_org_v(pk_org_v);
		// 部门、部门v
		hVO.setCdptid(pk_dept);		// "1001N510000000000NG1"
		hVO.setCdptvid(pk_dept_v);	// "0001N510000000002JIV"
		// 仓库
		hVO.setCwarehouseid(pk_store);	// "1001N510000000000OJR"
		// 出入库类型
		hVO.setVtrantypecode(BILL_TYPE_CODE);	// "4D-Cxx-002"
		hVO.setCtrantypeid(BILL_TYPE_ID);	// "1001M910000000BM8C0L"
		// 备注
		hVO.setVnote(billDate.toString().substring(0,10) + yyd + "出库");
		
		hVO.setDbilldate(billDate);	// 单据日期
		hVO.setDirty(false);		// dr
		hVO.setAttributeValue("dr", 0);	// dr
		hVO.setFbillflag(2);		// 单据状态
		hVO.setIprintcount(0);		// 打印次数
		
		return hVO;
	}
	/**
	 * 封装材料出库表体vo
	 */
	private MaterialOutBodyVO gen_bvo(
			 String pk_group
			,String pk_org
			,String pk_org_v
			,String pk_dept
			,String pk_dept_v
			,String pk_store
			,UFDate billDate
			,UFDouble num
			,String rowno
			,String pk_inv
			,String pk_inv_v
			,String pk_unit
			,String pk_head
			,String pk_body
			,String vbillcode
			,String vrowno
			,UFDateTime ts_head
			,UFDateTime ts_body
			,String pk_cklb		// 出库类别id
			,String yyd			// 营业点
			) {
		MaterialOutBodyVO bVO = new MaterialOutBodyVO();
		bVO.setBassetcard(UFBoolean.FALSE);		// 是否生成资产卡片 
		bVO.setBbarcodeclose(UFBoolean.FALSE);	// 单据行是否条码关闭 
		bVO.setBcseal(UFBoolean.FALSE);			// 批次是否封存
		bVO.setBonroadflag(UFBoolean.FALSE);	// 是否在途
		bVO.setBreworkflag(UFBoolean.FALSE);	// 产成品返修
		// 出入库类型
		bVO.setCbodytranstypecode(BILL_TYPE_CODE);	// "4D-Cxx-002"
		// 集团
		bVO.setPk_group(pk_group);
		// 组织、组织v
		bVO.setCioliabilityoid(pk_org);
		bVO.setCioliabilityvid(pk_org_v);
		bVO.setCliabilityoid(pk_org);
		bVO.setCliabilityvid(pk_org_v);
		bVO.setCorpoid(pk_org);
		bVO.setCorpvid(pk_org_v);
		bVO.setPk_org(pk_org);
		bVO.setPk_org_v(pk_org_v);
		// 仓库
		bVO.setCbodywarehouseid(pk_store);// "1001N510000000000OJR"
		// 物料
		bVO.setCmaterialoid(pk_inv);// "1001N51000000078KH6N"
		bVO.setCmaterialvid(pk_inv_v);// "1001N51000000078KH6N"
		// 单位、辅单位、换算率
		bVO.setCunitid(pk_unit);// "1001N510000000000LN3"
		bVO.setCastunitid(pk_unit);// "1001N510000000000LN3"
		bVO.setVchangerate("1.00/1.00");
		// 行号
		bVO.setCrowno(rowno);
//		bVO.setCSnunitid("");
		
		bVO.setNassistnum(num);	// 数量
		bVO.setNnum(num);		// 数量
		bVO.setNvolume(UFDouble.ZERO_DBL);
		bVO.setNweight(UFDouble.ZERO_DBL);
		bVO.setPseudoColumn(0);
		// 自定义项
//		bVO.setVbdef1(null);	// 餐饮类型
//		bVO.setVbdef2(null);	// 临时名称
		bVO.setVbdef3(pk_cklb);	// 出库类别
		bVO.setVbdef5(yyd);		// 需求部门 = 营业点
		bVO.setVbdef8("N");		// 是否智云单据
		// 行备注
//		bVO.setVnotebody(null);
		
		bVO.setDbizdate(billDate);	// 业务日期
		bVO.setDirty(false);		// dr
		bVO.setAttributeValue("dr", 0);	// dr
		
		// 来源信息
		bVO.setCsourcebillbid(pk_body);
		bVO.setCsourcebillhid(pk_head);
		bVO.setCsourcetranstype("0001ZZ100000001P7OHF");// 来源类型：绿云出库明细
		bVO.setCsourcetype("0001ZZ100000001P7OHF");		// 来源类型：绿云出库明细
		bVO.setTsourcebodyts(ts_body);
		bVO.setTsourceheadts(ts_head);
		bVO.setVsourcebillcode(vbillcode);
		bVO.setVsourcerowno(vrowno);
		// 源头信息（目前单据都没赋值，我们也不需要赋值）
		
		return bVO;
	}
}
