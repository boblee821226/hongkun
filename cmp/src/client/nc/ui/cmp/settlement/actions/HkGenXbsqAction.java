package nc.ui.cmp.settlement.actions;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IPub_data;
import nc.itf.hkjt.report.HkjtReportITF;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.uif2.NCAction;
import nc.vo.cmp.settlement.SettlementAggVO;
import nc.vo.cmp.settlement.SettlementHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sf.allocateapply.AggAllocateApplyVO;
import nc.vo.sf.allocateapply.AllocateApplyBVO;
import nc.vo.sf.allocateapply.AllocateApplyVO;

public class HkGenXbsqAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7921042581033253865L;
	
	private nc.ui.cmp.settlement.view.SettlementCard billForm;
	private nc.ui.cmp.settlement.model.SettleModel model;
	private nc.ui.cmp.settlement.model.SettleDataManager dataManager;
	private nc.ui.cmp.settlement.view.SettlementList listView;
	private nc.ui.tmpub.security.DefCommonClientSign caSignerXbsq;
	
	public HkGenXbsqAction () {
		super();
	    this.setBtnName("生成下拨申请");
	    this.setCode("genXbsqAction");
	}
	
	@Override
	public void doAction(ActionEvent event) throws Exception {
		
		/**
		 * 1：应付		9478	++
		 * 0：应收		3986	
		 * 2：现金管理		119103	
		 * 107：报销管理	34642	++
		 */
		
		Object[] selectData = this.getModel().getSelectedOperaDatas();
		
		if (selectData == null || selectData.length <= 0) {
			MessageDialog.showErrorDlg(this.getListView(), "", "请选择数据");
			return;
		}
		
		// 选单时 判断 不能选多个组织的
		String pk_org_buffer = null;
		for (Object data : selectData) {
			SettlementAggVO  slBillVO = (SettlementAggVO)data;
			SettlementHeadVO    slHVO = (SettlementHeadVO)slBillVO.getParentVO();
			String pk_org_temp = slHVO.getPk_org();
			if (pk_org_buffer == null) {
				pk_org_buffer = pk_org_temp;
			} else {
				if (!pk_org_buffer.equals(pk_org_temp)) {
					throw new BusinessException("必须选择同组织的");
				}
			}
		}
		
		// 获得 pk_org 去找 账户信息
		HashMap<String, String[]> map_zhanghu = new HashMap<String, String[]>();
		
		/**
		 * 循环所选数据，进行分单处理
		 * 分 
		 */
		HashMap<String,ArrayList<String>> map_bill = new HashMap<String,ArrayList<String>>();
		for (Object data : selectData) {
			SettlementAggVO  slBillVO = (SettlementAggVO)data;
			SettlementHeadVO    slHVO = (SettlementHeadVO)slBillVO.getParentVO();
//			SettlementBodyVO[] slBVOs = (SettlementBodyVO[])slBillVO.getChildrenVO();
			
			String systemcode = slHVO.getSystemcode();			// 来源系统
			String pk_settlement = slHVO.getPk_settlement();	// 结算单pk
			String pk_org = slHVO.getPk_org();	// 组织pk
			if (!map_zhanghu.containsKey(pk_org)) {
				map_zhanghu.put(pk_org, this.getZhanghuInfo(pk_org));
			}
			
			if ("1".equals(systemcode)) {
				// 1、应付
			} else if ("107".equals(systemcode)) {
				// 2、报销管理
			} else {
				// 3、其它类型 不予处理
				continue;
			}
			
			if (map_bill.containsKey(systemcode)) {
				map_bill.get(systemcode).add(pk_settlement);
			} else {
				ArrayList<String> arr = new ArrayList<String>();
				arr.add(pk_settlement);
				map_bill.put(systemcode, arr);
			}
		}
		
		/**
		 * 按照分单的结果 进行处理，进行分行， 生成 下拨申请单据。
		 */
		if (map_bill == null || map_bill.size() <= 0) return;
		
		String[] map_bill_key = new String[map_bill.size()];
		map_bill_key = map_bill.keySet().toArray(map_bill_key);
		
		ArrayList<Object[]> genList = new ArrayList<Object[]>();
		
		for(int i = 0; i < map_bill_key.length; i++) {
			String systemcode = map_bill_key[i];
			ArrayList<String> pkSettlementList = map_bill.get(systemcode);
			String pkSettlementStr = PuPubVO.getSqlInByList(pkSettlementList);
			
			if ("1".equals(systemcode)) {
				// 应付
				genList.add(this.genXbsqByYf(pkSettlementStr, map_zhanghu));
			} if ("107".equals(systemcode)) {
				// 报销
				genList.add(this.genXbsqByBx(pkSettlementStr, map_zhanghu));
			}
		}
		
		if( genList.size() <= 0 ) return;
		
		HkjtReportITF itf = (HkjtReportITF)NCLocator.getInstance().lookup(HkjtReportITF.class.getName()); 
		Object result = itf.genXbsqByJsd(genList, null);
		System.out.print("=="+result+"==");
	}

	public nc.ui.cmp.settlement.view.SettlementCard getBillForm() {
		return billForm;
	}

	public void setBillForm(nc.ui.cmp.settlement.view.SettlementCard billForm) {
		this.billForm = billForm;
	}

	public nc.ui.cmp.settlement.model.SettleModel getModel() {
		return model;
	}

	public void setModel(nc.ui.cmp.settlement.model.SettleModel model) {
		this.model = model;
	}

	public nc.ui.cmp.settlement.model.SettleDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(
			nc.ui.cmp.settlement.model.SettleDataManager dataManager) {
		this.dataManager = dataManager;
	}

	public nc.ui.cmp.settlement.view.SettlementList getListView() {
		return listView;
	}

	public void setListView(nc.ui.cmp.settlement.view.SettlementList listView) {
		this.listView = listView;
	}

	public nc.ui.tmpub.security.DefCommonClientSign getCaSignerXbsq() {
		return caSignerXbsq;
	}

	public void setCaSignerXbsq(
			nc.ui.tmpub.security.DefCommonClientSign caSignerXbsq) {
		this.caSignerXbsq = caSignerXbsq;
	}
	
	/**
	 * 生成 应付 的 下拨申请
	 * 0：下拨申请单
	 * 1：关系表
	 */
	private Object[] genXbsqByYf(String pkSettlementStr, HashMap<String, String[]> map_zhanghu) throws Exception {
		String creator = InvocationInfoProxy.getInstance().getUserId();	// 当前操作人
		IUAPQueryBS itf = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		AggAllocateApplyVO billVO = new AggAllocateApplyVO();
		/**
		 *  关系： key = uuid唯一串，保存在 下拨申请行的自定义项中。 
		 *  	value = ArrayList<String[]> 保存着 构成 下拨申请行的 结算单 主子pk
		 */
		HashMap<String, ArrayList<String[]>> link = new HashMap<String, ArrayList<String[]>>();
				
		StringBuffer querySQL = 
		new StringBuffer("select ")
				.append(" js.pk_settlement ")	// 0 结算单id
				.append(",jsb.pk_detail ")		// 1 结算单bid
				.append(",js.pk_group ")		// 2 pk_group
				.append(",js.pk_org ")			// 3 pk_org
				.append(",js.pk_org_v ")		// 4 pk_org_v
				.append(",jsb.pay ")			// 5 金额
				.append(",case fkb.prepay when 0 then '应付款' when 1 then '预付款' else '未知' end ")	// 6 付款性质
				.append(",fktype.name ")		// 7 付款业务类型
				.append(",zjxm.pk_fundplan ")	// 8 资金项目pk
				.append(",zjxm.name ")			// 9 资金项目name
				.append(",case when jsb.systemcode = 'erm' then bi.defitem5 else jsb.memo end ")			// 10 结算单-摘要
				.append(",js.billcode ")		// 11 业务单号
				.append(" from cmp_settlement js ")
				.append(" inner join cmp_detail jsb on js.pk_settlement = jsb.pk_settlement ")
				.append(" left join ap_payitem fkb on (jsb.pk_billdetail = fkb.pk_payitem and fkb.dr = 0) ")
				.append(" left join ap_paybill fk on (fkb.pk_paybill = fk.pk_paybill and fk.dr = 0) ")
				.append(" left join fi_recpaytype fktype on (fkb.pk_recpaytype = fktype.pk_recpaytype) ")
				// 收支项目
				.append(" left join bd_inoutbusiclass szxm on (fkb.pk_subjcode = szxm.pk_inoutbusiclass) ")
				// 资金项目
				.append(" left join bd_fundplan zjxm on (szxm.def5 = zjxm.pk_fundplan) ")
				// 取摘要
				.append(" left join er_busitem bi on (bi.pk_busitem = jsb.pk_billdetail)")
				.append(" where js.dr = 0 and jsb.dr = 0 ")
				.append(" and nvl(js.def1,'N') <> '").append(IPub_data.JSXB_done).append("' ")
				.append(" and js.pk_settlement in ").append(pkSettlementStr)
		;
		ArrayList list = (ArrayList)itf.executeQuery(querySQL.toString(), new ArrayListProcessor());
				
		/**
		 *  汇总行（按照摘要）
		 * key: 摘要
		 * value: 0- AllocateApplyBVO (自定义01)
		 * 		  1- Array<String[pk_jsd,pk_jsd_b]> 
		 * 		  2- String uuid
		*/
		HashMap<String, Object[]> map_body = new HashMap<String, Object[]>();
		Integer rowCount = 0;
		UFDouble totalMny = UFDouble.ZERO_DBL;
		String PK_GROUP = null;
		String PK_ORG = null;
		String PK_ORG_V = null;
		for (Object obj : list) {
			Object[] rowObj = (Object[])obj;
			String pk_jsd 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[0]);
			String pk_jsd_b = PuPubVO.getString_TrimZeroLenAsNull(rowObj[1]);
			String pk_group = PuPubVO.getString_TrimZeroLenAsNull(rowObj[2]);
			String pk_org 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[3]);
			String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(rowObj[4]);
			UFDouble mny 	= PuPubVO.getUFDouble_NullAsZero(rowObj[5]);
			String fkxz 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[6]);	// 付款性质
			String fklx 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[7]);	// 付款类型
			
			String zjjxxm 		= PuPubVO.getString_TrimZeroLenAsNull(rowObj[8]);	// 资金项目pk
			String zjjxxm_name	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[9]);	// 资金项目name
			
			String zhaiyao 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[10]);	// 结算单-摘要
			String billCode = PuPubVO.getString_TrimZeroLenAsNull(rowObj[11]);	// 业务单号
			
			if (zjjxxm == null) {
				zjjxxm = IPub_data.JSXB_jhxm_caigou;
				zjjxxm_name = "采购款";
			}
			
//			String zjjxxm	= IPub_data.JSXB_jhxm_caigou;	// 资金计划项目（默认为采购款）
			
//			if ("工程款".equals(fkxz)) {
//				zjjxxm = IPub_data.JSXB_jhxm_gongcheng;	// 工程款
//			}
			
			if (PK_GROUP == null) {
				PK_GROUP = pk_group;
				PK_ORG = pk_org;
				PK_ORG_V = pk_org_v;
			}
			
			String[] zhanghuInfo = map_zhanghu.get(pk_org);
			
//			String zhaiyao = fkxz + fklx;
			String key = pk_jsd + fkxz + zjjxxm_name; // key = 结算单+付款性质+资金计划项目
			
			if (!map_body.containsKey(key)) {
				rowCount++;
				String uuid = UUID.randomUUID().toString();
				AllocateApplyBVO bVO = new AllocateApplyBVO();
				bVO.setApplyamount(UFDouble.ZERO_DBL);		// 需要叠加
				bVO.setApplyolcamount(UFDouble.ZERO_DBL);	// 需要叠加
				bVO.setApplyolcrate(new UFDouble(1.00));
				bVO.setDr(0);
				bVO.setIsnetpay(UFBoolean.FALSE);
				bVO.setPk_financeorg_r(pk_org);
				bVO.setPk_financeorg_r_v(pk_org_v);
				bVO.setPk_balatype(IPub_data.JSXB_pk_balatype_ZZ);	// 结算方式 bd_balatype 默认为转账
				
				bVO.setPk_bankacc_r(zhanghuInfo[0]);	// 收款账户PK org_orgs.def3
				bVO.setBankacccode_r(zhanghuInfo[1]);	// 收款银行账户 bd_bankaccsub.ACCNUM
				bVO.setBankaccname_r(zhanghuInfo[2]);	// 收款银行户名 bd_bankaccsub.ACCNAME
				bVO.setBankname_r(zhanghuInfo[3]);		// 收款单位开户银行 bd_bankaccbas.PK_BANKDOC
				bVO.setPk_accid(zhanghuInfo[4]);		// 收款单位内部账户 bd_accid
				
				bVO.setPk_planitem_r(zjjxxm);	// 资金计划项目
				bVO.setRemark("");	// 摘要 需要叠加
				bVO.setRowno("" + (rowCount * 10));
				bVO.setStatus(VOStatus.NEW);
				bVO.setAttributeValue("pseudocolumn", (rowCount - 1));
				bVO.setVuserdef1(uuid);		// 表体自定义项1，记录关联关系
				bVO.setVuserdef2(billCode);	// 业务单号
				
				Object[] body_value = new Object[3];
				body_value[0] = bVO;
				body_value[1] = new ArrayList<String[]>();
				body_value[2] = uuid;
				
				map_body.put(key, body_value);
			}
			
			Object[] body_value = map_body.get(key);
			AllocateApplyBVO bVO = (AllocateApplyBVO)body_value[0];
			ArrayList<String[]> body_link = (ArrayList<String[]>)body_value[1];
			body_link.add(new String[]{
					pk_jsd,
					pk_jsd_b,
			});
			bVO.setApplyamount(bVO.getApplyamount().add(mny));
			bVO.setApplyolcamount(bVO.getApplyolcamount().add(mny));
			if (zhaiyao != null) {
				String zhaiyao_bc = "【" + zhaiyao + "】";
				if (!bVO.getRemark().contains(zhaiyao_bc)) { // 只有不重复的摘要，才可以添加
					String zhaiyao_new = bVO.getRemark() + zhaiyao_bc;
					if (PuPubVO.getStringLength(zhaiyao_new, "utf-8") < 280) {
						// 下拨申请的摘要  最大长度为 300， 所以做一下限制，只有小于280 才叠加
						bVO.setRemark(bVO.getRemark() + "【" + zhaiyao + "】"); // 需要叠加
					} else if (bVO.getRemark().endsWith("...")) {
						// 如果以 ... 结尾 就不再处理
					} else {
						bVO.setRemark(bVO.getRemark() + "...");
					}
				}
			}
			totalMny = totalMny.add(mny);
		}
		
		// 构造表头vo
		UFDate nowDate = new UFDate();
		AllocateApplyVO hVO = new AllocateApplyVO();
		hVO.setApplydate(nowDate);
		hVO.setApplyglctotal(UFDouble.ZERO_DBL);
		hVO.setApplygllctotal(UFDouble.ZERO_DBL);
		hVO.setApplytotal(totalMny);
		hVO.setBillstatus(5);
		hVO.setBusidate(nowDate);
		hVO.setBusitype(2);
		hVO.setBillmaker(creator);	// 制单人
		hVO.setCreator(creator);	// 创建人
		hVO.setDiffstatus("serverBefore");
		hVO.setDr(0);
		hVO.setIsfinancereturned(UFBoolean.FALSE);
		hVO.setMemo("结算推单-应付款");
		hVO.setPk_billtype(IPub_data.BILLTYPE_36K1);	// 下拨申请
		hVO.setPk_billtypeid(IPub_data.BILLTYPE_36K1_ID);	
		hVO.setPk_currtype(IPub_data.CURRTYPE_CNY);		// 币种-人民币
		hVO.setPk_group(PK_GROUP);
		hVO.setPk_org(PK_ORG);
		hVO.setPk_org_v(PK_ORG_V);
		hVO.setPk_payorg(IPub_data.JSXB_pk_payorg);		// 总部-暂时固定
		hVO.setPk_payorg_v(IPub_data.JSXB_pk_payorg_v);	// 总部-暂时固定
		hVO.setVbillstatus(-1);
		hVO.setStatus(VOStatus.NEW);	// 需要设置 VO状态为 新增
		
		AllocateApplyBVO[] bVOs = new AllocateApplyBVO[map_body.size()];
		Integer bVOs_i = 0;
		for ( Entry<String, Object[]> entry : map_body.entrySet() ) {
			String zhaiyao = entry.getKey();
			Object[] body_value = map_body.get(zhaiyao);
			AllocateApplyBVO bVO = (AllocateApplyBVO)body_value[0];
			ArrayList<String[]> body_link = (ArrayList<String[]>)body_value[1];
			String uuid = (String)body_value[2];
			
			bVOs[bVOs_i] = bVO;
			bVOs_i++;
			
			link.put(uuid, body_link);
		}
		
		billVO.setHead(hVO);
		billVO.setChildren(bVOs);
		getCaSignerXbsq().signWithClient(billVO);	// 进行签名 hVO.setEncryptkey()
		
		return new Object[]{
				billVO,
				link,
		};
	}
	
	/**
	 * 生成 报销 的 下拨申请
	 * 0：下拨申请单
	 * 1：关系表
	 */
	private Object[] genXbsqByBx(String pkSettlementStr, HashMap<String, String[]> map_zhanghu) throws Exception {
		String creator = InvocationInfoProxy.getInstance().getUserId();	// 当前操作人
		IUAPQueryBS itf = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		AggAllocateApplyVO billVO = new AggAllocateApplyVO();
		/**
		 *  关系： key = uuid唯一串，保存在 下拨申请行的自定义项中。 
		 *  	value = ArrayList<String[]> 保存着 构成 下拨申请行的 结算单 主子pk
		 */
		HashMap<String, ArrayList<String[]>> link = new HashMap<String, ArrayList<String[]>>();
				
		StringBuffer querySQL = 
		new StringBuffer("select ")
				.append(" js.pk_settlement ")	// 0 结算单id
				.append(",jsb.pk_detail ")		// 1 结算单bid
				.append(",js.pk_group ")		// 2 pk_group
				.append(",js.pk_org ")			// 3 pk_org
				.append(",js.pk_org_v ")		// 4 pk_org_v
				.append(",jsb.pay ")			// 5 金额
				.append(",szxm.def5 ")			// 6 资金计划项目pk
				.append(",zjjxxm.name ")		// 7 资金计划项目name
				.append(",case when jsb.systemcode = 'erm' then bi.defitem5 else jsb.memo end ")			// 8 结算单-摘要
				.append(",js.billcode ")		// 9 业务单号
				.append(" from cmp_settlement js ")
				.append(" inner join cmp_detail jsb on js.pk_settlement = jsb.pk_settlement ")
				.append(" left join er_busitem bxb on (jsb.pk_billdetail = bxb.PK_BUSITEM and bxb.dr = 0) ")
				.append(" left join er_bxzb bx on (bxb.PK_JKBX = bx.PK_JKBX and bx.dr = 0) ")
				.append(" left join bd_inoutbusiclass szxm ON (bxb.SZXMID = szxm.PK_INOUTBUSICLASS AND SZXM.DR = 0) ")
				.append(" left join bd_fundplan zjjxxm ON (szxm.DEF5 = zjjxxm.PK_FUNDPLAN AND ZJJXXM.dr = 0) ")
				// 取摘要
				.append(" left join er_busitem bi on (bi.pk_busitem = jsb.pk_billdetail)")
				.append(" where js.dr = 0 and jsb.dr = 0 ")
				.append(" and nvl(js.def1,'N') <> '").append(IPub_data.JSXB_done).append("' ")
				.append(" and js.pk_settlement in ").append(pkSettlementStr)
		;
		ArrayList list = (ArrayList)itf.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		/**
		 * 汇总行（按照摘要）
		 * key: 摘要
		 * value: 0- AllocateApplyBVO (自定义01)
		 * 		  1- Array<String[pk_jsd,pk_jsd_b]> 
		 * 		  2- String uuid
		*/
		HashMap<String, Object[]> map_body = new HashMap<String, Object[]>();
		Integer rowCount = 0;
		UFDouble totalMny = UFDouble.ZERO_DBL;
		String PK_GROUP = null;
		String PK_ORG = null;
		String PK_ORG_V = null;
		for (Object obj : list) {
			Object[] rowObj = (Object[])obj;
			String pk_jsd 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[0]);
			String pk_jsd_b = PuPubVO.getString_TrimZeroLenAsNull(rowObj[1]);
			String pk_group = PuPubVO.getString_TrimZeroLenAsNull(rowObj[2]);
			String pk_org 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[3]);
			String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(rowObj[4]);
			UFDouble mny 	= PuPubVO.getUFDouble_NullAsZero(rowObj[5]);
			String zjjxxm 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[6]);		// 资金计划项目pk
			String zjjhxm_name 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[7]);	// 资金计划项目name
			String zhaiyao 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[8]);	// 摘要
			String billCode = PuPubVO.getString_TrimZeroLenAsNull(rowObj[9]);	// 业务单号
			
			if (PK_GROUP == null) {
				PK_GROUP = pk_group;
				PK_ORG = pk_org;
				PK_ORG_V = pk_org_v;
			}
			
			String[] zhanghuInfo = map_zhanghu.get(pk_org);
			
			String key = pk_jsd + "##" +zjjhxm_name; // 按 结算单+资金计划项目  来汇总行
			
			if (!map_body.containsKey(key)) {
				rowCount++;
				String uuid = UUID.randomUUID().toString();
				AllocateApplyBVO bVO = new AllocateApplyBVO();
				bVO.setApplyamount(UFDouble.ZERO_DBL);		// 需要叠加
				bVO.setApplyolcamount(UFDouble.ZERO_DBL);	// 需要叠加
				bVO.setApplyolcrate(new UFDouble(1.00));
				bVO.setDr(0);
				bVO.setIsnetpay(UFBoolean.FALSE);
				bVO.setPk_financeorg_r(pk_org);
				bVO.setPk_financeorg_r_v(pk_org_v);
				bVO.setPk_balatype(IPub_data.JSXB_pk_balatype_ZZ);	// 结算方式 bd_balatype 默认为转账
				
				bVO.setPk_bankacc_r(zhanghuInfo[0]);	// 收款账户PK org_orgs.def3
				bVO.setBankacccode_r(zhanghuInfo[1]);	// 收款银行账户 bd_bankaccsub.ACCNUM
				bVO.setBankaccname_r(zhanghuInfo[2]);	// 收款银行户名 bd_bankaccsub.ACCNAME
				bVO.setBankname_r(zhanghuInfo[3]);		// 收款单位开户银行 bd_bankaccbas.PK_BANKDOC
				bVO.setPk_accid(zhanghuInfo[4]);		// 收款单位内部账户 bd_accid
				
				bVO.setPk_planitem_r(zjjxxm);	// 资金计划项目
				bVO.setRemark(""); // 需要叠加
				bVO.setRowno("" + (rowCount * 10));
				bVO.setStatus(VOStatus.NEW);
				bVO.setAttributeValue("pseudocolumn", (rowCount - 1));
				bVO.setVuserdef1(uuid);		// 表体自定义项1，记录关联关系
				bVO.setVuserdef2(billCode);	// 业务单号
				
				Object[] body_value = new Object[3];
				body_value[0] = bVO;
				body_value[1] = new ArrayList<String[]>();
				body_value[2] = uuid;
				
				map_body.put(key, body_value);
			}
			
			Object[] body_value = map_body.get(key);
			AllocateApplyBVO bVO = (AllocateApplyBVO)body_value[0];
			ArrayList<String[]> body_link = (ArrayList<String[]>)body_value[1];
			body_link.add(new String[]{
					pk_jsd,
					pk_jsd_b,
			});
			bVO.setApplyamount(bVO.getApplyamount().add(mny));
			bVO.setApplyolcamount(bVO.getApplyolcamount().add(mny));
			if (zhaiyao != null) {
				String zhaiyao_bc = "【" + zhaiyao + "】";
				if (!bVO.getRemark().contains(zhaiyao_bc)) { // 只有不重复的摘要，才可以添加
					String zhaiyao_new = bVO.getRemark() + zhaiyao_bc;
					if (PuPubVO.getStringLength(zhaiyao_new, "utf-8") < 280) {
						// 下拨申请的摘要  最大长度为 300， 所以做一下限制，只有小于280 才叠加
						bVO.setRemark(bVO.getRemark() + "【" + zhaiyao + "】"); // 需要叠加
					} else if (bVO.getRemark().endsWith("...")) {
						// 如果以 ... 结尾 就不再处理
					} else {
						bVO.setRemark(bVO.getRemark() + "...");
					}
				}
			}
			/**
			 * if (zhaiyao != null) {
				String zhaiyao_bc = "【" + zhaiyao + "】";
				if (!bVO.getRemark().contains(zhaiyao_bc)) { // 只有不重复的摘要，才可以添加
					String zhaiyao_new = bVO.getRemark() + zhaiyao_bc;
					if (zhaiyao_new.length() < 280) {
						// 下拨申请的摘要  最大长度为 300， 所以做一下限制，只有小于280 才叠加
						bVO.setRemark(bVO.getRemark() + "【" + zhaiyao + "】"); // 需要叠加
					} else if (bVO.getRemark().endsWith("...")) {
						// 如果以 ... 结尾 就不再处理
					} else {
						bVO.setRemark(bVO.getRemark() + "...");
					}
				}
			}
			 */
			totalMny = totalMny.add(mny);
		}
		
		// 构造表头vo
		UFDate nowDate = new UFDate();
		AllocateApplyVO hVO = new AllocateApplyVO();
		hVO.setApplydate(nowDate);
		hVO.setApplyglctotal(UFDouble.ZERO_DBL);
		hVO.setApplygllctotal(UFDouble.ZERO_DBL);
		hVO.setApplytotal(totalMny);
		hVO.setBillstatus(5);
		hVO.setBusidate(nowDate);
		hVO.setBusitype(2);
		hVO.setBillmaker(creator);	// 制单人
		hVO.setCreator(creator);	// 创建人
		hVO.setDiffstatus("serverBefore");
		hVO.setDr(0);
		hVO.setIsfinancereturned(UFBoolean.FALSE);
		hVO.setMemo("结算推单-报销款");
		hVO.setPk_billtype(IPub_data.BILLTYPE_36K1);	// 下拨申请
		hVO.setPk_billtypeid(IPub_data.BILLTYPE_36K1_ID);	
		hVO.setPk_currtype(IPub_data.CURRTYPE_CNY);		// 币种-人民币
		hVO.setPk_group(PK_GROUP);
		hVO.setPk_org(PK_ORG);
		hVO.setPk_org_v(PK_ORG_V);
		hVO.setPk_payorg(IPub_data.JSXB_pk_payorg);		// 总部-暂时固定
		hVO.setPk_payorg_v(IPub_data.JSXB_pk_payorg_v);	// 总部-暂时固定
		hVO.setVbillstatus(-1);
		hVO.setStatus(VOStatus.NEW);	// 需要设置 VO状态为 新增
		
		AllocateApplyBVO[] bVOs = new AllocateApplyBVO[map_body.size()];
		Integer bVOs_i = 0;
		for ( Entry<String, Object[]> entry : map_body.entrySet() ) {
			String zhaiyao = entry.getKey();
			Object[] body_value = map_body.get(zhaiyao);
			AllocateApplyBVO bVO = (AllocateApplyBVO)body_value[0];
			ArrayList<String[]> body_link = (ArrayList<String[]>)body_value[1];
			String uuid = (String)body_value[2];
			
			bVOs[bVOs_i] = bVO;
			bVOs_i++;
			
			link.put(uuid, body_link);
		}
		
		billVO.setHead(hVO);
		billVO.setChildren(bVOs);
		getCaSignerXbsq().signWithClient(billVO);	// 进行签名 hVO.setEncryptkey()
		
		return new Object[]{
				billVO,
				link,
		};
	}
	
	/**
	 * 根据组织，找到 账户信息
	 * 0-账户pk
	 * 1-账户code
	 * 2-账户name
	 * 3-开户行pk
	 * 4-内部账户pk
	 */
	private String[] getZhanghuInfo(String pk_org) throws Exception {
		IUAPQueryBS itf = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		StringBuffer querySQL = 
		new StringBuffer("SELECT ")
				.append(" bs.PK_BANKACCSUB ")	// 账户pk
				.append(",bs.ACCNUM ")			// 账户code
				.append(",bs.ACCNAME ")			// 账户name
				.append(",bb.PK_BANKDOC ")		// 开户行pk
				.append(",ac.PK_ACCID ")		// 内部账户pk
				.append(" FROM ORG_ORGS o ")
				.append(" LEFT JOIN bd_bankaccsub bs ON (o.DEF3 = bs.PK_BANKACCSUB AND bs.DR = 0) ")
				.append(" LEFT JOIN bd_bankaccbas bb ON (bs.PK_BANKACCBAS = bb.PK_BANKACCBAS AND BB.DR = 0) ")
				.append(" LEFT JOIN bd_accid ac ON (ac.PK_OWNERORG = o.PK_ORG AND NVL(ac.billstatus, 'N') = 'Y' AND ac.ACCCLASS = 2 AND ac.ENABLESTATE = 2 AND ac.FROZENFLAG = 0) ")
				.append(" WHERE o.PK_ORG = '").append(pk_org).append("'")
		;
		
		ArrayList list = (ArrayList)itf.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		if (list != null && list.size() > 0) {
			Object[] obj = (Object[])list.get(0);
			return new String[]{
				PuPubVO.getString_TrimZeroLenAsNull(obj[0]),
				PuPubVO.getString_TrimZeroLenAsNull(obj[1]),
				PuPubVO.getString_TrimZeroLenAsNull(obj[2]),
				PuPubVO.getString_TrimZeroLenAsNull(obj[3]),
				PuPubVO.getString_TrimZeroLenAsNull(obj[4]),
			};
		}
		
		return null;
	}
}
