package nc.api_oa.hkjt.impl.service._263X;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.vo._263X.JkVO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.arap.pub.IBXBillPublic;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class N263XService {
	
	private IplatFormEntry iplatFormEntry;
	private IplatFormEntry getIplatFormEntry() {
		if (iplatFormEntry == null) {
			iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
		}
		return iplatFormEntry;
	}
	
	private IBXBillPublic iBXBillPublic;
	private IBXBillPublic getIBXBillPublic() {
		if (iBXBillPublic == null) {
			iBXBillPublic = (IBXBillPublic) NCLocator.getInstance().lookup(IBXBillPublic.class.getName());
		}
		return iBXBillPublic;
	}
	
	/**
	 * @param account
	 * @param billType
	 * @param paramObj
	 * @param action
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	public Object doAction(String account, String billType, Object paramObj, String action, String userId) 
			throws BusinessException  {
		JkVO[] param = (JkVO[])paramObj;
//		nc.vo.ep.bx.JKVO[] billVOs = new nc.vo.ep.bx.JKVO[param.length];
		ArrayList<nc.vo.ep.bx.JKVO> billVOlist = new ArrayList<>();
		/**
		 * VO转换
		 */
		for (int i = 0; i < param.length; i++) {
			/**
			 * 先查询，是否存在单据
			 */
			nc.vo.ep.bx.JKVO dbVO = N263XServiceQUERY.getJkVO(param[i], account, billType);
			if (dbVO != null) {
				// 如果存在，就不需要再保存，直接返回200
			} else {
				nc.vo.ep.bx.JKVO billVO = getJkVO(param[i], account, billType);
				billVOlist.add(billVO);
			}
		}		
		/**
		 * 循环处理：保存、审核
		 */
		for (int i = 0; i < billVOlist.size(); i++) {
			nc.vo.ep.bx.JKVO billVO = billVOlist.get(i);
			// 保存
//			Object saveRes = getIplatFormEntry().processAction("WRITE", "263X-Cxx-01", null, billVO, null, null);
//			billVO = ((nc.vo.ep.bx.JKVO[])saveRes)[0];
			nc.vo.ep.bx.JKBXVO[] writeRes = getIBXBillPublic().save(new nc.vo.ep.bx.JKBXVO[]{billVO});
			billVO = (nc.vo.ep.bx.JKVO)writeRes[0];
			// 提交
			Object saveRes = getIplatFormEntry().processAction("SAVE", billType, null, billVO, null, null);
			billVO = (nc.vo.ep.bx.JKVO)saveRes;
			/**
			 * 审核
			 * 需要赋值，跟审批相关的字段
			 */
			/**
			HashMap<String,String> hmPfExParams = new HashMap<>();
			hmPfExParams.put("notechecked", "notechecked");
			billVO.setCmpIdMap(null);
			billVO.setChildrenFetched(true);
//			billVO.setNCClient(true);
			billVO.setJkdMap(null);
			billVO.setSettlementMap(null);
//			billVO.getParentVO().setApprover(userId);	// 审核人
//			billVO.getParentVO().setDjzt(1);			// 审批状态
//			UFDateTime currTime = new UFDateTime();
//			billVO.getParentVO().setShrq(currTime);		// 审核日期
//			billVO.getParentVO().setShrq_show(currTime.getDate());	// 审核日期
			PfBusinessLock pfLock = null;
			try {
				pfLock = new PfBusinessLock();
				pfLock.lock(new VOLockData(billVO, billType), new VOConsistenceCheck(billVO, billType));
				Object approveRes = getIplatFormEntry().processAction("APPROVE"+userId, "263X-Cxx-01", null, billVO, null, hmPfExParams);
				nc.vo.erm.common.MessageVO approveMsgVO = ((nc.vo.erm.common.MessageVO[])approveRes)[0];
				if (!approveMsgVO.isSuccess()) {
					throw new BusinessException(approveMsgVO.getErrorMessage());
				}
				billVO = (nc.vo.ep.bx.JKVO)approveMsgVO.getSuccessVO();
				
			} catch (Exception ex) {
				throw new BusinessException(ex);
			} finally {
				if (pfLock != null) {
					pfLock.unLock(); // 释放锁
				}
			}
//			String billId = billVO.getParentVO().getPk_jkbx();
			// 审批后会加锁，没法自动释放，只能手工需要释放。
//			PKLock.getInstance().releaseBatchLock(new String[]{billId}, userId, account);
			 */
		}
		
		return null;
	}
	
	/**
	 * 翻译聚合VO
	 */
	private nc.vo.ep.bx.JKVO getJkVO(
			  nc.api_oa.hkjt.vo._263X.JkVO srcBillVO
			, String account
			, String billType
			) throws BusinessException {
		/**
		 * 表头表头共享变量
		 */
		HashMap<String,String> param = new HashMap<>();
		param.put("account", account); // 账套
//		param.put("szxm", srcBillVO.getItems()[0].getJkxm()); // 收支项目：表体第一行的 赋值给表头
		/***END***/
		nc.vo.ep.bx.JKVO distBillVO = new nc.vo.ep.bx.JKVO();
		nc.vo.ep.bx.JKHeaderVO distHVO = getJkHeadVO(srcBillVO.getHead(), param);
		nc.vo.ep.bx.JKBusItemVO[] distBVOs = new nc.vo.ep.bx.JKBusItemVO[srcBillVO.getItems().length];
		for (int i = 0; i < srcBillVO.getItems().length; i++) {
//			param.put("rowno", ""+(i+1));
			distBVOs[i] = getJkItemVO(srcBillVO.getItems()[i], param);
		}
		
		distBillVO.setParentVO(distHVO);
		distBillVO.setChildrenVO(distBVOs);
		return distBillVO;
	}
	
	/**
	 * 翻译表头vo
	 */
	private nc.vo.ep.bx.JKHeaderVO getJkHeadVO(
			  nc.api_oa.hkjt.vo._263X.JkHeadVO srcHVO
			, HashMap<String,String> param 
			) throws BusinessException {
		
		String account = param.get("account");		// 账套
		String billTypeCode = "263X-Cxx-01";		// 单据类型code
		String billTypeId = "1001N51000000004ILKQ";	// 单据类型id
		UFDouble spje = PuPubVO.getUFDouble_NullAsZero(srcHVO.getSpje());	// 金额
		UFDateTime currTime = new UFDateTime();		// 当前时间
		String djbh = srcHVO.getDjbh();				// 单据编号
		UFDate djrq = new UFDateTime(srcHVO.getDjrq()).getDate();	// 单据日期
		String bz = "1002Z0100000000001K1";		// 币种
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();	// 集团
		String fycdgsStr = srcHVO.getFycdgs();	// 费用承担公司（除了人员所在公司，其它的都用费用承担公司）
		String fycdbmStr = srcHVO.getFycdbm();	// 费用承担部门
		String szgsStr = srcHVO.getSzgs();		// 所在公司（只是 人员所在公司）
		String szbmStr = srcHVO.getSzbm();		// 所在部门
		HashMap<String,String> szgs_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(szgsStr);
		if (szgs_map == null) {throw new BusinessException("所在公司不匹配：" + szgsStr);}
		String szgs = szgs_map.get("id");
		String szgs_v = szgs_map.get("vid");
		HashMap<String,String> szbm_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(szbmStr);
		if (szbm_map == null) {throw new BusinessException("所在部门不匹配：" + szbmStr);}
		String szbm = szbm_map.get("id");
		String szbm_v = szbm_map.get("vid");
		HashMap<String,String> fycdgs_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(fycdgsStr);
		if (fycdgs_map == null) {throw new BusinessException("费用承担公司不匹配：" + fycdgsStr);}
		String fycdgs = fycdgs_map.get("id");
		String fycdgs_v = fycdgs_map.get("vid");
		String fkyhzh = fycdgs_map.get("account");		// 付款银行账户
		HashMap<String,String> fycdbm_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(fycdbmStr);
		if (fycdbm_map == null) {throw new BusinessException("费用承担部门不匹配：" + fycdbmStr);}
		String fycdbm = fycdbm_map.get("id");
		String fycdbm_v = fycdbm_map.get("vid");
		String userId = InvocationInfoProxy.getInstance().getUserId();
		String zdrStr = srcHVO.getZdr();	// 制单人
		HashMap<String,String> zdr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(zdrStr);
		if (zdr_map == null) {throw new BusinessException("制单人不匹配：" + zdrStr);}
		String zdr = zdr_map.get("id");
		/**
		 * HK 2020年9月17日19:07:30
		 * 结算方式需要匹配接收两种模式，name 和 pk
		 * 如果长度为 20，就认为是pk
		 * 不为 20，就认为是 name
		 */
		String jsfsStr = PuPubVO.getString_TrimZeroLenAsNull(srcHVO.getJsfs());	// 结算方式
		String jsfs = null;
		if (jsfsStr == null) {
			// 接口传过来为空，不做处理
		} else if (jsfsStr.length() == 20) {
			jsfs = jsfsStr;
		} else {
			HashMap<String,String> jsfs_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_balatype").get(jsfsStr);
			if (jsfs_map == null) {throw new BusinessException("结算方式不匹配：" + jsfsStr);}
			jsfs = jsfs_map.get("id");
		}
//		String szxm = param.get("szxm");	// 收支项目
//		String skdxStr = srcHVO.getSkdx();	// 收款对象：0员工 1供应商 2客户
		Integer skdx = 0;
		
		String skr = null;
		String skyhzh = null;		// 收款人账户
	
		String url = srcHVO.getUrl();	// url
		
		if (0 == skdx) { // 员工
			String skrStr = srcHVO.getSkr();
			HashMap<String,String> skr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(skrStr);
			if (skr_map == null) {throw new BusinessException("收款人不匹配：" + skrStr);}
			skr = skr_map.get("id");
			skyhzh = srcHVO.getGryhzh();
		}
		
		nc.vo.ep.bx.JKHeaderVO distHVO = new nc.vo.ep.bx.JKHeaderVO();
		distHVO.setBbhl(UFDouble.ONE_DBL);	// 汇率
		distHVO.setBbje(spje);		// 金额
		distHVO.setBbye(spje);
		distHVO.setTotal(spje);
		distHVO.setYbje(spje);
		distHVO.setYbye(spje);
		distHVO.setZfbbje(spje);
		distHVO.setZfybje(spje);
		distHVO.setBzbm(bz);		// 币种-人民币
		
		distHVO.setCreationtime(currTime);	// 创建时间
		distHVO.setCreator(userId);		// 创建人
		distHVO.setOperator(userId);	// 操作员
		
		distHVO.setDjbh(djbh);				// 单据编号
		distHVO.setDjdl("jk");				// 单据大类-报销
		distHVO.setDjlxbm(billTypeCode);	// 单据类型
		distHVO.setPk_tradetypeid(billTypeId);// 单据类型id
		distHVO.setDjrq(djrq);			// 单据日期
		distHVO.setDjzt(1);				// 单据状态
		distHVO.setDr(0);
		
		distHVO.setPk_group(pk_group);	// 集团
		distHVO.setPk_org(fycdgs);
		distHVO.setPk_org_v(fycdgs_v);
		distHVO.setPk_payorg(fycdgs);
		distHVO.setPk_payorg_v(fycdgs_v);
		distHVO.setPk_fiorg(fycdgs);
		distHVO.setFydwbm(fycdgs);		// 费用承担公司
		distHVO.setFydwbm_v(fycdgs_v);
		
		distHVO.setFydeptid(fycdbm);	// 费用承担部门
		distHVO.setFydeptid_v(fycdbm_v);
		
		/**
		 * 人员：所在组织、部门
		 */
		distHVO.setDwbm(szgs);			// 所在公司
		distHVO.setDwbm_v(szgs_v);		// 所在公司v
		distHVO.setDeptid(szbm);		// 所在部门
		distHVO.setDeptid_v(szbm_v);	// 所在部门v
		
		distHVO.setGlobalbbhl(UFDouble.ZERO_DBL);
		distHVO.setGlobalbbje(UFDouble.ZERO_DBL);
		distHVO.setGlobalbbye(UFDouble.ZERO_DBL);
		distHVO.setGlobalhkbbje(UFDouble.ZERO_DBL);
		distHVO.setGlobalzfbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupbbhl(UFDouble.ZERO_DBL);
		distHVO.setGroupbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupbbye(UFDouble.ZERO_DBL);
		distHVO.setGrouphkbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupzfbbje(UFDouble.ZERO_DBL);
		distHVO.setHkbbje(UFDouble.ZERO_DBL);
		distHVO.setHkybje(UFDouble.ZERO_DBL);

		distHVO.setIscheck(UFBoolean.FALSE);
		distHVO.setIsexpedited(UFBoolean.FALSE);
		distHVO.setIsinitgroup(UFBoolean.FALSE);
		distHVO.setIsmashare(UFBoolean.FALSE);
		distHVO.setIsneedimag(UFBoolean.FALSE);
		distHVO.setQcbz(UFBoolean.FALSE);
		
		distHVO.setJsfs(jsfs);			// 结算方式
		distHVO.setPaytarget(skdx);		// 报销对象
		distHVO.setFkyhzh(fkyhzh);		// 付款银行账户（根据组织带出来 付款账户）
		distHVO.setJkbxr(zdr);			// 报销人-人员档案
		
		distHVO.setReceiver(skr);		// 收款人-人员档案
		distHVO.setSkyhzh(skyhzh);		// 个人银行账户
		
		distHVO.setPayflag(1);			// 支付状态：1=未支付，2=支付中，3=支付完成，4=支付失败，20=部分支付完成，99=手工支付，101=全额冲销，102=调整， 
		distHVO.setQzzt(0);				// 清账状态：0=否，1=是， 
		distHVO.setSpzt(-1);			// 审批状态（3=提交，-1=自由）（保存时设置为提交状态）
		distHVO.setSxbz(0);				// 生效标志
		
		distHVO.setZhrq(distHVO.getDjrq().getDateAfter(30)); // 最迟还款日期（单据日期+30天）
//		distHVO.setZyx16("0.00");
//		distHVO.setZyx17("10000.00");
//		distHVO.setZyx18("10000.00");
		distHVO.setZyx19("1001N51000000011M2L4");	// 是否结算中心付款（暂时默认为否）
		distHVO.setZyx26(url);	// url
		
		/**
		 * param
		 */
		param.put("pk_org", fycdgs);	// 公司
		param.put("pk_dept", fycdbm);	// 部门
		param.put("skr", skr);			// 收款人
		param.put("skyhzh", skyhzh);	// 个人收款账户
		param.put("skdx", ""+skdx);		// 收款对象
		param.put("bxr", zdr);			// 报销人（人员）
		/***END***/
		
		return distHVO;
	}
	
	/**
	 * 翻译表体vo
	 */
	private nc.vo.ep.bx.JKBusItemVO getJkItemVO(
			  nc.api_oa.hkjt.vo._263X.JkItemVO srcBVO
			, HashMap<String,String> param 
			) throws BusinessException {
		
//		String account = param.get("account");	// 账套
		String szxm = srcBVO.getJkxm();			// 收支项目（借款项目）
		UFDouble jshj = PuPubVO.getUFDouble_NullAsZero(srcBVO.getJe());	// 价税合计
		String sxsm = srcBVO.getSxsm();	// 事项说明
//		String pk_org = param.get("pk_org");
//		String pk_dept = param.get("pk_dept");
//		String skr = param.get("skr");
//		String skyhzh = param.get("skyhzh");
//		Integer skdx = PuPubVO.getInteger_NullAs(param.get("skdx"),0);
//		String bxr = param.get("bxr");
//		Integer rowno = PuPubVO.getInteger_NullAs(param.get("rowno"),0);
		
		nc.vo.ep.bx.JKBusItemVO distBVO = new nc.vo.ep.bx.JKBusItemVO();
		
		distBVO.setDefitem5(sxsm);		// 事项说明
		distBVO.setDr(0);
		distBVO.setCjkbbje(UFDouble.ZERO_DBL);
		distBVO.setCjkybje(UFDouble.ZERO_DBL);
		distBVO.setGlobalbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalbbye(UFDouble.ZERO_DBL);
		distBVO.setGlobalcjkbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalhkbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalzfbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupbbye(UFDouble.ZERO_DBL);
		distBVO.setGroupcjkbbje(UFDouble.ZERO_DBL);
		distBVO.setGrouphkbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupzfbbje(UFDouble.ZERO_DBL);
		distBVO.setHkbbje(UFDouble.ZERO_DBL);
		distBVO.setHkybje(UFDouble.ZERO_DBL);
		distBVO.setRowno(null);
		distBVO.setSzxmid(szxm);
		distBVO.setTablecode("jk_busitem");
		distBVO.setAmount(jshj);
		distBVO.setBbje(jshj);
		distBVO.setBbye(jshj);
		distBVO.setYbje(jshj);
		distBVO.setYbye(jshj);
		distBVO.setZfbbje(jshj);
		distBVO.setZfybje(jshj);
		
		return distBVO;
	}
}
