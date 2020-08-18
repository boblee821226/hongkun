package nc.api_oa.hkjt.impl.service._264X;

import java.util.HashMap;

import hd.vo.pub.tools.PuPubVO;
import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.vo._264X.BxVO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.uap.pf.PfProcessBatchRetObject;

public class N264XService {
	
	private IplatFormEntry iplatFormEntry;
	private IplatFormEntry getIplatFormEntry() {
		if (iplatFormEntry == null) {
			iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
		}
		return iplatFormEntry;
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
	public Object doAction(String account, String billType, Object paramObj, String action, String userId) throws BusinessException  {
		BxVO[] param = (BxVO[])paramObj;
		nc.vo.ep.bx.BXVO[] billVOs = new nc.vo.ep.bx.BXVO[param.length];
		/**
		 * VO转换
		 */
		for (int i = 0; i < param.length; i++) {
			billVOs[i] = getBxVO(param[i], account, billType);
		}
		/**
		 * 进行保存
		 */
		Object res = getIplatFormEntry().processBatch(action, billType, null, billVOs, null, null);
		
		if (res != null && res instanceof PfProcessBatchRetObject) {
			PfProcessBatchRetObject resRet = (PfProcessBatchRetObject)res;
			String errMsg = resRet.getExceptionMsg();
			if (PuPubVO.getString_TrimZeroLenAsNull(errMsg) != null) {
				throw new BusinessException(errMsg);
			}
			
		} else {
			throw new BusinessException("错误");
		}
		
		return null;
	}
	
	/**
	 * 翻译聚合VO
	 */
	private nc.vo.ep.bx.BXVO getBxVO(
			nc.api_oa.hkjt.vo._264X.BxVO srcBillVO
			, String account
			, String billType
			) 
			throws BusinessException {
		/**
		 * 表头表头共享变量
		 */
		HashMap<String,String> param = new HashMap<>();
		param.put("account", account); // 账套
		param.put("szxm", srcBillVO.getItems()[0].getZcxm()); // 收支项目：表体第一行的 赋值给表头
		/***END***/
		nc.vo.ep.bx.BXVO distBillVO = new nc.vo.ep.bx.BXVO();
		nc.vo.ep.bx.BXHeaderVO distHVO = getBxHeadVO(srcBillVO.getHead(), param);
		nc.vo.ep.bx.BXBusItemVO[] distBVOs = new nc.vo.ep.bx.BXBusItemVO[srcBillVO.getItems().length];
		for (int i = 0; i < srcBillVO.getItems().length; i++) {
			param.put("rowno", ""+(i+1));
			distBVOs[i] = getBxItemVO(srcBillVO.getItems()[i], param);
		}
		
		distBillVO.setParentVO(distHVO);
		distBillVO.setChildrenVO(distBVOs);
		return distBillVO;
	}
	
	/**
	 * 翻译表头vo
	 * 结算方式 带出 付款账户  jsfs=="1001N5100000008LDY9M"||jsfs=="1001N5100000009BN9JV","",getcolvalue("org_orgs","def2","pk_org",pk_org)
	 */
	private nc.vo.ep.bx.BXHeaderVO getBxHeadVO(
			  nc.api_oa.hkjt.vo._264X.BxHeadVO srcHVO
			, HashMap<String,String> param 
			)
			throws BusinessException {
		String account = param.get("account");		// 账套
		String billTypeCode = "264X-Cxx-01";		// 单据类型code
		String billTypeId = "1001N51000000004LSR8";	// 单据类型id
		UFDouble spje = PuPubVO.getUFDouble_NullAsZero(srcHVO.getSpje());	// 金额
		UFDateTime currTime = new UFDateTime();	// 当前时间
		String djbh = srcHVO.getDjbh();			// 单据编号
		UFDate djrq = PuPubVO.getUFDate(srcHVO.getDjrq());	// 单据日期
		String djbt = srcHVO.getDjbt();			// 单据标题
		String bz = "1002Z0100000000001K1";		// 币种
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();	// 集团
		String fycdgsStr = srcHVO.getFycdgs();	// 费用承担公司
		String fycdbmStr = srcHVO.getFycdbm();	// 费用承担部门
		String szgsStr = srcHVO.getSzgs();		// 所在公司
		String szbmStr = srcHVO.getSzbm();		// 所在部门
		HashMap<String,String> org_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(szgsStr);
		String pk_org = org_map.get("id");
		String pk_org_v = org_map.get("vid");
		String fkyhzh = org_map.get("account");		// 付款银行账户
		HashMap<String,String> dept_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(szbmStr);
		String pk_dept = dept_map.get("id");
		String pk_dept_v = dept_map.get("vid");
		HashMap<String,String> fycdgs_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(fycdgsStr);
		String fycdgs = fycdgs_map.get("id");
		String fycdgs_v = fycdgs_map.get("vid");
		HashMap<String,String> fycdbm_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(fycdbmStr);
		String fycdbm = fycdbm_map.get("id");
		String fycdbm_v = fycdbm_map.get("vid");
		String userId = InvocationInfoProxy.getInstance().getUserId();
		String zdrStr = srcHVO.getZdr();	// 制单人
		String zdr = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(zdrStr).get("id");
		String jsfs = srcHVO.getJsfs();		// 结算方式
		String szxm = param.get("szxm");	// 收支项目
		String skdxStr = srcHVO.getSkdx();	// 收款对象：0员工 1供应商 2客户
		Integer skdx = 0;
		if ("供应商".equals(skdxStr)) {
			skdx = 1;
		} else if ("客户".equals(skdxStr)) {
			skdx = 2;
		}
		
		String skr = null;
		String skyhzh = null;		// 收款人账户
		String gys = null;			// 供应商
		String kh = null;			// 客户
		String custaccount = null; 	// 客商账户
	
		String url = srcHVO.getUrl();	// url
		
		if (0 == skdx) { // 员工
//			gys = null;
//			kh = null;
//			custaccount = null;
			String skrStr = srcHVO.getSkr();
			skr = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(skrStr).get("id");
			skyhzh = srcHVO.getGryhzh();
		} else if (1 == skdx) {	// 供应商
//			skr = null;
//			skyhzh = null;
//			kh = null;
			gys = srcHVO.getGys();
			custaccount = srcHVO.getKsyhzh();
		} else if (2 == skdx) {	// 客户
//			skr = null;
//			skyhzh = null;
//			gys = null;
			kh = srcHVO.getKh();
			custaccount = srcHVO.getKsyhzh();
		}
		
		nc.vo.ep.bx.BXHeaderVO distHVO = new nc.vo.ep.bx.BXHeaderVO();
		distHVO.setBbhl(UFDouble.ONE_DBL);			// 本币汇率 1
		distHVO.setBbje(spje);						// 本币金额
		distHVO.setTotal(spje);
		distHVO.setYbje(spje);
		distHVO.setZfbbje(spje);
		distHVO.setZfybje(spje);
		distHVO.setBzbm(bz);	// 币种-人民币
		
		distHVO.setCreationtime(currTime);			// 创建时间
		distHVO.setCreator(userId);	// 创建人
		distHVO.setOperator(userId);// 操作员
		
		distHVO.setDjbh(djbh);				// 单据编号
		distHVO.setDjdl("bx");				// 单据大类-报销
		distHVO.setDjlxbm(billTypeCode);	// 单据类型
		distHVO.setPk_tradetypeid(billTypeId);// 单据类型id
		distHVO.setDjrq(djrq);			// 单据日期
		distHVO.setDjzt(1);				// 单据状态
		distHVO.setDr(0);
		distHVO.setPk_group(pk_group);	// 集团
		distHVO.setDwbm(pk_org);		// 公司
		distHVO.setDwbm_v(pk_org_v);	// 公司v
		distHVO.setPk_org(pk_org);
		distHVO.setPk_org_v(pk_org_v);
		distHVO.setPk_payorg(pk_org);
		distHVO.setPk_payorg_v(pk_org_v);
		distHVO.setPk_fiorg(pk_org);
		distHVO.setFydwbm(fycdgs);		// 费用承担公司
		distHVO.setFydwbm_v(fycdgs_v);
		
		distHVO.setDeptid(pk_dept);		// 部门
		distHVO.setDeptid_v(pk_dept_v);	// 部门v
		distHVO.setFydeptid(fycdbm);	// 费用承担部门
		distHVO.setFydeptid_v(fycdbm_v);
		
		distHVO.setCjkbbje(UFDouble.ZERO_DBL);
		distHVO.setCjkybje(UFDouble.ZERO_DBL);
		distHVO.setGlobalbbhl(UFDouble.ZERO_DBL);
		distHVO.setGlobalbbje(UFDouble.ZERO_DBL);
		distHVO.setGlobalcjkbbje(UFDouble.ZERO_DBL);
		distHVO.setGlobalhkbbje(UFDouble.ZERO_DBL);
		distHVO.setGlobalzfbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupbbhl(UFDouble.ZERO_DBL);
		distHVO.setGroupbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupcjkbbje(UFDouble.ZERO_DBL);
		distHVO.setGrouphkbbje(UFDouble.ZERO_DBL);
		distHVO.setGroupzfbbje(UFDouble.ZERO_DBL);
		distHVO.setHkbbje(UFDouble.ZERO_DBL);
		distHVO.setHkybje(UFDouble.ZERO_DBL);
		
		distHVO.setFlexible_flag(UFBoolean.FALSE);
		distHVO.setIscheck(UFBoolean.FALSE);
		distHVO.setIscostshare(UFBoolean.FALSE);
		distHVO.setIsexpamt(UFBoolean.FALSE);
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
		distHVO.setCustaccount(custaccount);// 客商账户
		distHVO.setCustomer(kh);			// 客户
		distHVO.setHbbm(gys);				// 供应商
		
		distHVO.setSpzt(-1);		// 审批状态
		distHVO.setSxbz(0);			// 生效标志
		distHVO.setSzxmid(szxm);	// 收支项目
		
		distHVO.setZyx12(djbt);		// 标题
		distHVO.setZyx18("1001N51000000011M2L4"); // 是否结算中心付款（暂时默认为否）
		distHVO.setZyx26(url);	// url
		
		/**
		 * param
		 */
		param.put("pk_org", pk_org);	// 公司
		param.put("pk_dept", pk_dept);	// 部门
		param.put("skr", skr);			// 收款人
		param.put("kh", kh);			// 客户
		param.put("gys", gys);			// 供应商
		param.put("custaccount", custaccount);	// 客商银行账户
		param.put("skyhzh", skyhzh);	// 个人收款账户
		param.put("skdx", ""+skdx);		// 收款对象
		param.put("bxr", zdr);			// 报销人（人员）
		/***END***/
		
		return distHVO;
	}
	
	/**
	 * 翻译表体vo
	 */
	private nc.vo.ep.bx.BXBusItemVO getBxItemVO(
			  nc.api_oa.hkjt.vo._264X.BxItemVO srcBVO
			, HashMap<String,String> param 
			) 
			throws BusinessException {
		String account = param.get("account");	// 账套
		String dkfsStr = srcBVO.getDkfs();
		String dkfs = ApiPubInfo.CACHE_DOC.get(account).get("bd_defdoc_dkfs").get(dkfsStr).get("id");
		String szxm = srcBVO.getZcxm();			// 收支项目
		UFDouble jshj = PuPubVO.getUFDouble_NullAsZero(srcBVO.getJshjje());	// 价税合计
		UFDouble sl = PuPubVO.getUFDouble_NullAsZero(srcBVO.getSl());		// 税率
		UFDouble se = PuPubVO.getUFDouble_NullAsZero(srcBVO.getSe());		// 税额
		UFDouble wsje = PuPubVO.getUFDouble_NullAsZero(srcBVO.getWsje());	// 无税金额
		String sxsm = srcBVO.getSxsm();	// 事项说明
		String pk_org = param.get("pk_org");
		String pk_dept = param.get("pk_dept");
		String skr = param.get("skr");
		String kh = param.get("kh");
		String gys = param.get("gys");
		String custaccount = param.get("custaccount");
		String skyhzh = param.get("skyhzh");
		Integer skdx = PuPubVO.getInteger_NullAs(param.get("skdx"),0);
		String bxr = param.get("bxr");
		Integer rowno = PuPubVO.getInteger_NullAs(param.get("rowno"),0);
		
		nc.vo.ep.bx.BXBusItemVO distBVO = new nc.vo.ep.bx.BXBusItemVO();
		distBVO.setDefitem12(sl.toString());	// 税率
		distBVO.setDefitem13(wsje.toString());	// 无税金额
		distBVO.setDefitem15(se.toString());	// 税额
		distBVO.setDefitem7(jshj.toString());	// 价税合计
		distBVO.setDefitem14(dkfs);		// 抵扣方式
		distBVO.setDefitem5(sxsm);		// 事项说明
		distBVO.setDwbm(pk_org);		// 公司
		distBVO.setDeptid(pk_dept);		// 部门
		distBVO.setDr(0);
		distBVO.setCjkbbje(UFDouble.ZERO_DBL);
		distBVO.setCjkybje(UFDouble.ZERO_DBL);
		distBVO.setGlobalbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalcjkbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalhkbbje(UFDouble.ZERO_DBL);
		distBVO.setGlobalzfbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupcjkbbje(UFDouble.ZERO_DBL);
		distBVO.setGrouphkbbje(UFDouble.ZERO_DBL);
		distBVO.setGroupzfbbje(UFDouble.ZERO_DBL);
		distBVO.setHkbbje(UFDouble.ZERO_DBL);
		distBVO.setHkybje(UFDouble.ZERO_DBL);
		distBVO.setPaytarget(skdx);			// 收款对象
		distBVO.setCustomer(kh);			// 客户
		distBVO.setHbbm(gys);				// 供应商
		distBVO.setCustaccount(custaccount);// 客商账户
		distBVO.setSkyhzh(skyhzh);			// 个人账户
		distBVO.setReceiver(skr);	// 收款人
		distBVO.setJkbxr(bxr);		// 报销人
		distBVO.setRowno(rowno);	// 行号
		distBVO.setSzxmid(szxm);	// 收支项目
		distBVO.setTablecode("arap_bxbusitem");
		distBVO.setAmount(jshj);	// 金额
		distBVO.setBbje(jshj);
		distBVO.setYbje(jshj);
		distBVO.setZfbbje(jshj);
		distBVO.setZfybje(jshj);
		
		return distBVO;
	}
}
