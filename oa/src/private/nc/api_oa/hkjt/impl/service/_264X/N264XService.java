package nc.api_oa.hkjt.impl.service._264X;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api_oa.hkjt.impl.service.DocService;
import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.vo._264X.BxVO;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.arap.pub.IBXBillPublic;
import nc.itf.uap.pf.IplatFormEntry;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class N264XService {
	
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
	public Object doAction(String account, String billType, Object paramObj, String action, String userId) throws BusinessException  {
		BxVO[] param = (BxVO[])paramObj;
//		nc.vo.ep.bx.BXVO[] billVOs = new nc.vo.ep.bx.BXVO[param.length];
		ArrayList<nc.vo.ep.bx.BXVO> billVOlist = new ArrayList<>();
		/**
		 * VO转换
		 */
		for (int i = 0; i < param.length; i++) {
			/**
			 * 先查询，是否存在单据
			 */
			nc.vo.ep.bx.BXVO dbVO = N264XServiceQUERY.getBxVO(param[i], account, billType);
			if (dbVO != null) {
				// 如果存在，就不需要再保存，直接返回200
			} else {
				nc.vo.ep.bx.BXVO billVO = getBxVO(param[i], account, billType);
				billVOlist.add(billVO);
			}
		}
		/**
		 * 循环处理：保存、审核
		 */
		for (int i = 0; i < billVOlist.size(); i++) {
			nc.vo.ep.bx.BXVO billVO = billVOlist.get(i);
			// 保存
//			Object saveRes = getIplatFormEntry().processAction("WRITE", "264X-Cxx-01", null, billVO, null, null);
			nc.vo.ep.bx.JKBXVO[] writeRes = getIBXBillPublic().save(new nc.vo.ep.bx.JKBXVO[]{billVO});
			billVO = (nc.vo.ep.bx.BXVO)writeRes[0];
			// 提交
			Object saveRes = getIplatFormEntry().processAction("SAVE", billType, null, billVO, null, null);
			billVO = (nc.vo.ep.bx.BXVO)saveRes;
			// 审核
//			Object approveRes = getIplatFormEntry().processAction("APPROVE", "264X", null, ((nc.vo.ep.bx.BXVO[])saveRes)[0], null, null);
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
		 * 单据类型
		 */
		HashMap<String, String> djlx_MAP = new HashMap<>();
		djlx_MAP.put("264X-Cxx-01", "1001N51000000004LSR8");	// 日常费用审批单
		djlx_MAP.put("264X-Cxx-02", "1001N51000000004MN92");	// 往来付款审批单
		djlx_MAP.put("264X-Cxx-03", "1001N51000000004MN9R");	// 工资福利审批单
		djlx_MAP.put("264X-Cxx-04", "1001N51000000004MNGQ");	// 工程付款审批单
		djlx_MAP.put("264X-Cxx-05", "1001N5100000006NLZTC");	// 分摊费用审批单
		/**
		 * 表头表头共享变量
		 */
		HashMap<String,String> param = new HashMap<>();
		param.put("account", account); // 账套
		param.put("szxm", srcBillVO.getItems()[0].getZcxm()); // 收支项目：表体第一行的 赋值给表头
		param.put("billTypeCode", billType);			// 单据类型code
		param.put("billTypeId", djlx_MAP.get(billType));// 单据类型id
		/***END***/
		nc.vo.ep.bx.BXVO distBillVO = new nc.vo.ep.bx.BXVO();
		nc.vo.ep.bx.BXHeaderVO distHVO = getBxHeadVO(srcBillVO.getHead(), param);
		nc.vo.ep.bx.BXBusItemVO[] distBVOs = new nc.vo.ep.bx.BXBusItemVO[srcBillVO.getItems().length];
		for (int i = 0; i < srcBillVO.getItems().length; i++) {
			param.put("rowno", ""+(i+1));
			distBVOs[i] = getBxItemVO(srcBillVO.getItems()[i], param);
		}
		nc.vo.erm.costshare.CShareDetailVO[] distSVOs = null;
		if (srcBillVO.getShares() != null && srcBillVO.getShares().length > 0) {
			distSVOs = new nc.vo.erm.costshare.CShareDetailVO[srcBillVO.getShares().length];
			for (int i = 0; i < srcBillVO.getShares().length; i++) {
				param.put("rowno", ""+(i+1));
				distSVOs[i] = getBxShareVO(srcBillVO.getShares()[i], param);
			}
		}
		
		distBillVO.setParentVO(distHVO);
		distBillVO.setChildrenVO(distBVOs);		// 明细
		distBillVO.setcShareDetailVo(distSVOs);	// 分摊页签
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
		String billTypeCode = param.get("billTypeCode");// 单据类型code
		String billTypeId = param.get("billTypeId");	// 单据类型id
//		String billTypeCode = "264X-Cxx-01";		// 单据类型code
//		String billTypeId = "1001N51000000004LSR8";	// 单据类型id
		UFDouble spje = PuPubVO.getUFDouble_NullAsZero(srcHVO.getSpje());	// 金额
		UFDateTime currTime = new UFDateTime();	// 当前时间
		String djbh = srcHVO.getDjbh();			// 单据编号
		UFDate djrq = new UFDateTime(srcHVO.getDjrq()).getDate();	// 单据日期
		String bt = srcHVO.getBt();			// 标题
		String bz = "1002Z0100000000001K1";		// 币种
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();	// 集团
		String bxdwStr = srcHVO.getFycdgs();	// 报销单位（除了投资单，用投资方，其它的都用费用承担公司）
		String fycdgsStr = srcHVO.getFycdgs();	// 费用承担公司（除了人员所在公司，其它的都用费用承担公司）
		String fycdbmStr = srcHVO.getFycdbm();	// 费用承担部门
		String szgsStr = srcHVO.getSzgs();		// 所在公司（只是 人员所在公司）
		String szbmStr = srcHVO.getSzbm();		// 所在部门
		UFBoolean isShare = PuPubVO.getUFBoolean_NullAs(srcHVO.getFt(), UFBoolean.FALSE);	// 是否分摊
		
		if ("264X-Cxx-06".equals(billTypeCode)) {
			// 投资业务审批单，对 报销单位 和 费用承担公司 重新赋值
			bxdwStr = srcHVO.getTzf();
			fycdgsStr = srcHVO.getSzgs();
			fycdbmStr = srcHVO.getSzbm();
		}
		
		if (
			ApiPubInfo.CACHE_DOC.get(account)==null
		|| ApiPubInfo.CACHE_DOC.get(account).get("org_orgs")==null
		|| ApiPubInfo.CACHE_DOC.get(account).get("org_dept")==null
		|| ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc")==null
		|| ApiPubInfo.CACHE_DOC.get(account).get("bd_balatype")==null
		|| ApiPubInfo.CACHE_DOC.get(account).get("bd_defdoc_dkfs")==null
		) {
			throw new BusinessException("档案缓存有问题");
		}
		
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
		
		HashMap<String,String> bxdw_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(bxdwStr);
		if (bxdw_map == null) {throw new BusinessException("报销单位不匹配：" + bxdwStr);}
		String bxdw = bxdw_map.get("id");
		String bxdw_v = bxdw_map.get("vid");
		
		String fkyhzh = fycdgs_map.get("account");		// 付款银行账户
		HashMap<String,String> fycdbm_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(fycdbmStr);
		if (fycdbm_map == null) {throw new BusinessException("费用承担部门不匹配：" + fycdbmStr);}
		String fycdbm = fycdbm_map.get("id");
		String fycdbm_v = fycdbm_map.get("vid");
		
		String userId = InvocationInfoProxy.getInstance().getUserId();
		String zdrStr = srcHVO.getZdr();	// 制单人
		HashMap<String,String> zdr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(zdrStr);
		if (zdr_map == null) { // 如果缓存为空，则需要更新缓存 重新查找（暂时先加在人员档案上，其它档案 看情况再说）
			DocService.doAction(account, "bd_psndoc");
			zdr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(zdrStr);
		}
		if (zdr_map == null) {throw new BusinessException("制单人不匹配：" + zdrStr);}
		String zdr = zdr_map.get("id");
		String zdrUserId = PuPubVO.getString_TrimZeroLenAsNull(zdr_map.get("userId"));
		if (zdrUserId == null) zdrUserId = ApiPubInfo.USER;
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
			String skrStr = srcHVO.getSkr();
			HashMap<String,String> skr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(skrStr);
			if (skr_map == null) {throw new BusinessException("收款人不匹配：" + skrStr);}
			skr = skr_map.get("id");
			skyhzh = srcHVO.getGryhzh();
		} else if (1 == skdx) {	// 供应商
			gys = srcHVO.getGys();
			custaccount = srcHVO.getKsyhzh();
			// 收款人
			String skrStr = PuPubVO.getString_TrimZeroLenAsNull(srcHVO.getSkr());
			if (skrStr != null) {
				HashMap<String,String> skr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(skrStr);
				if (skr_map == null) {throw new BusinessException("收款人不匹配：" + skrStr);}
				skr = skr_map.get("id");
			}
		} else if (2 == skdx) {	// 客户
			kh = srcHVO.getKh();
			custaccount = srcHVO.getKsyhzh();
			// 收款人：可为空，如果为空就不做翻译
			String skrStr = PuPubVO.getString_TrimZeroLenAsNull(srcHVO.getSkr());
			if (skrStr != null) {
				HashMap<String,String> skr_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_psndoc").get(skrStr);
				if (skr_map == null) {throw new BusinessException("收款人不匹配：" + skrStr);}
				skr = skr_map.get("id");
			}
		}
		
		/**
		 * 工程付款 专属
		 */
		String kgrq = srcHVO.getKgrq();		// 开工日期
		String jgrq = srcHVO.getJgrq();		// 竣工日期
		String gcwxsqd = srcHVO.getGcwxsqd();	// 工程维修申请单
		String xmmc = srcHVO.getXmmc();	// 项目名称
		/***END***/
		
		nc.vo.ep.bx.BXHeaderVO distHVO = new nc.vo.ep.bx.BXHeaderVO();
		distHVO.setBbhl(UFDouble.ONE_DBL);			// 本币汇率 1
		distHVO.setBbje(spje);						// 本币金额
		distHVO.setTotal(spje);
		distHVO.setYbje(spje);
		distHVO.setZfbbje(spje);
		distHVO.setZfybje(spje);
		distHVO.setBzbm(bz);	// 币种-人民币
		
		distHVO.setCreationtime(currTime);			// 创建时间
		distHVO.setCreator(zdrUserId);	// 创建人
		distHVO.setOperator(userId);// 操作员
		
		distHVO.setDjbh(djbh);				// 单据编号
		distHVO.setDjdl("bx");				// 单据大类-报销
		distHVO.setDjlxbm(billTypeCode);	// 单据类型
		distHVO.setPk_tradetypeid(billTypeId);// 单据类型id
		distHVO.setDjrq(djrq);			// 单据日期
		distHVO.setDjzt(1);				// 单据状态
		distHVO.setDr(0);
		
		distHVO.setPk_group(pk_group);	// 集团
		distHVO.setPk_org(bxdw);		// 报销组织
		distHVO.setPk_org_v(bxdw_v);	// 报销组织v
		distHVO.setPk_fiorg(bxdw);		// 报销财务组织
		distHVO.setPk_payorg(fycdgs);
		distHVO.setPk_payorg_v(fycdgs_v);
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
		distHVO.setIscostshare(isShare);	// 是否分摊
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
		
		distHVO.setSpzt(-1);			// 审批状态（3=提交，-1=自由）（保存时设置为提交状态）
		distHVO.setSxbz(0);			// 生效标志
		distHVO.setSzxmid(szxm);	// 收支项目
		
		distHVO.setZyx12(bt);		// 标题
		distHVO.setZyx18("1001N51000000011M2L4"); // 是否结算中心付款（暂时默认为否）
		distHVO.setZyx26(url);	// url
		
		distHVO.setZyx16(kgrq);	// 开工日期
		distHVO.setZyx17(jgrq);	// 竣工日期
		distHVO.setZyx1(xmmc);	// 项目名称
		
		/**
		 * param
		 */
		param.put("pk_group", pk_group);// 集团
		param.put("pk_org", fycdgs);	// 公司
		param.put("pk_dept", fycdbm);	// 部门
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
		String dkfsStr = srcBVO.getDkfs();		// 抵扣方式
		String dkfs = null;
		if (dkfsStr != null) {
			HashMap<String,String> dkfs_map = ApiPubInfo.CACHE_DOC.get(account).get("bd_defdoc_dkfs").get(dkfsStr);
			if (dkfs_map == null) {throw new BusinessException("抵扣方式不匹配：" + dkfsStr);}
			dkfs = dkfs_map.get("id");
		}
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
		String billTypeCode = PuPubVO.getString_TrimZeroLenAsNull(param.get("billTypeCode"));
		
		nc.vo.ep.bx.BXBusItemVO distBVO = new nc.vo.ep.bx.BXBusItemVO();
		
		if ("264X-Cxx-06".equals(billTypeCode)) {
			// 投资单：金额重新赋值
			jshj = PuPubVO.getUFDouble_NullAsZero(srcBVO.getTzje1());	// 投资金额
		} else if ("264X-Cxx-07".equals(billTypeCode)) {
			// 差旅费：字段赋值
			/**
			 * cfrq	出发日期	"项目主键	defitem1"
				fhrq	到达日期	"项目主键	defitem2"
				ccdd	出差地点	"项目主键	defitem4"
				ccts	出差天数	"项目主键	defitem9"
				jtgj	交通工具	"项目主键	defitem8"
				jtf	交通费	"项目主键	defitem10"
				zsfy	住宿费用	"项目主键	defitem11"
				bxf	保险费	"项目主键	defitem12"
				cfbz	餐费补助	"项目主键	defitem13"
				qtfy	其他费用	"项目主键	defitem14"
			 */
			String cfrq = srcBVO.getCfrq();	// 出发日期
			String fhrq = srcBVO.getFhrq();	// 到达日期
			String ccdd = srcBVO.getCcdd();	// 出差地点
			String jtgj = srcBVO.getJtgj();	// 交通工具
			UFDouble ccts = PuPubVO.getUFDouble_NullAsZero(srcBVO.getCcts()); // 出差天数
			UFDouble jtf = PuPubVO.getUFDouble_NullAsZero(srcBVO.getJtf()); // 交通费
			UFDouble zsfy = PuPubVO.getUFDouble_NullAsZero(srcBVO.getZsfy()); // 住宿费用
			UFDouble bxf = PuPubVO.getUFDouble_NullAsZero(srcBVO.getBxf()); // 保险费
			UFDouble cfbz = PuPubVO.getUFDouble_NullAsZero(srcBVO.getCfbz()); // 餐费补助
			UFDouble qtfy = PuPubVO.getUFDouble_NullAsZero(srcBVO.getQtfy()); // 其他费用
			
			distBVO.setDefitem1(cfrq);
			distBVO.setDefitem2(fhrq);
			distBVO.setDefitem4(ccdd);
			distBVO.setDefitem9(ccts);
			distBVO.setDefitem8(jtgj);
			distBVO.setDefitem10(jtf);
			distBVO.setDefitem11(zsfy);
			distBVO.setDefitem12(bxf);
			distBVO.setDefitem13(cfbz);
			distBVO.setDefitem14(qtfy);
		}
		
		distBVO.setDefitem5(sxsm);				// 事项说明
		distBVO.setDefitem7(jshj.toString());	// 价税合计
		
		// 非 分摊的 报销单，才进行赋值。
		if (!"264X-Cxx-05".equals(billTypeCode)) {
			distBVO.setDefitem20(sl.toString());	// 税率
			distBVO.setDefitem21(dkfs);				// 抵扣方式
			distBVO.setDefitem22(se.toString());	// 税额
			distBVO.setDefitem23(wsje.toString());	// 无税金额
		}
		
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
	
	/**
	 * 翻译 费用分摊vo
	 */
	private nc.vo.erm.costshare.CShareDetailVO getBxShareVO(
			  nc.api_oa.hkjt.vo._264X.BxShareVO srcSVO
			, HashMap<String,String> param 
			) 
			throws BusinessException {
		/**
		 *  assume_amount=600.00
			assume_dept=1001N5100000006VD3BB
			assume_org=0001N510000000001SXL
			bbhl=1
			bbje=600.00
			dr=0
			globalbbhl=0.00000000
			globalbbje=0.00000000
			groupbbhl=0.00000000
			groupbbje=0.00000000
			hbbm=1001N510000000000NW6
			pk_group=0001N510000000000EGY
			pk_iobsclass=1001N5100000000EYKBS
			share_ratio=60.00
		 */
		String account = param.get("account");	// 账套
		UFDouble cdje = PuPubVO.getUFDouble_NullAsZero(srcSVO.getCdje());	// 承担金额
		UFDouble ftbl = PuPubVO.getUFDouble_NullAsZero(srcSVO.getFtbl());	// 分摊比例
		Double se = srcSVO.getSe();	// 税额
		Double sl = srcSVO.getSl();	// 税率
		String cdgsStr = srcSVO.getCdgs();	// 承担公司
		HashMap<String,String> cdgs_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(cdgsStr);
		if (cdgs_map == null) {throw new BusinessException("承担公司不匹配：" + cdgsStr);}
		String cdgs = cdgs_map.get("id");
		String cdbmStr = srcSVO.getCdbm();	// 承担部门
		HashMap<String,String> cdbm_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(cdbmStr);
		if (cdbm_map == null) {throw new BusinessException("承担部门不匹配：" + cdbmStr);}
		String cdbm = cdbm_map.get("id");
		String zcxm = srcSVO.getZcxm();			// 收支项目
		String kh = param.get("kh");			// 客户
		String gys = param.get("gys");			// 供应商
		String pk_group = param.get("pk_group");// 集团
		/**
		 * 
		 * HashMap<String,String> dept_map = ApiPubInfo.CACHE_DOC.get(account).get("org_dept").get(szbmStr);
		String pk_dept = dept_map.get("id");
		String pk_dept_v = dept_map.get("vid");
		HashMap<String,String> fycdgs_map = ApiPubInfo.CACHE_DOC.get(account).get("org_orgs").get(fycdgsStr);
		String fycdgs = fycdgs_map.get("id");
		 */
		nc.vo.erm.costshare.CShareDetailVO distSVO = new nc.vo.erm.costshare.CShareDetailVO();
		distSVO.setAssume_amount(cdje);
		distSVO.setAssume_dept(cdbm);
		distSVO.setAssume_org(cdgs);
		distSVO.setBbhl(UFDouble.ONE_DBL);
		distSVO.setBbje(cdje);
		distSVO.setDr(0);
		distSVO.setGlobalbbhl(UFDouble.ZERO_DBL);
		distSVO.setGlobalbbje(UFDouble.ZERO_DBL);
		distSVO.setGroupbbhl(UFDouble.ZERO_DBL);
		distSVO.setGroupbbje(UFDouble.ZERO_DBL);
		distSVO.setHbbm(gys);
		distSVO.setCustomer(kh);
		distSVO.setPk_group(pk_group);
		distSVO.setShare_ratio(ftbl);
		distSVO.setPk_iobsclass(zcxm);
		distSVO.setStatus(VOStatus.NEW);	// 设置vo状态为NEW，才能进行保存
		distSVO.setDefitem1(sl == null ? null : sl.toString());	// 税率
		distSVO.setDefitem2(se == null ? null : se.toString());	// 税额
		
		return distSVO;
	}
}
