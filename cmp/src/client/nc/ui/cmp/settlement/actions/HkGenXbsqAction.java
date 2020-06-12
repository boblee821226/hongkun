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
	    this.setBtnName("�����²�����");
	    this.setCode("genXbsqAction");
	}
	
	@Override
	public void doAction(ActionEvent event) throws Exception {
		
		/**
		 * 1��Ӧ��		9478	++
		 * 0��Ӧ��		3986	
		 * 2���ֽ����		119103	
		 * 107����������	34642	++
		 */
		
		Object[] selectData = this.getModel().getSelectedOperaDatas();
		
		if (selectData == null || selectData.length <= 0) {
			MessageDialog.showErrorDlg(this.getListView(), "", "��ѡ������");
			return;
		}
		
		// ѡ��ʱ �ж� ����ѡ�����֯��
		String pk_org_buffer = null;
		for (Object data : selectData) {
			SettlementAggVO  slBillVO = (SettlementAggVO)data;
			SettlementHeadVO    slHVO = (SettlementHeadVO)slBillVO.getParentVO();
			String pk_org_temp = slHVO.getPk_org();
			if (pk_org_buffer == null) {
				pk_org_buffer = pk_org_temp;
			} else {
				if (!pk_org_buffer.equals(pk_org_temp)) {
					throw new BusinessException("����ѡ��ͬ��֯��");
				}
			}
		}
		
		// ��� pk_org ȥ�� �˻���Ϣ
		HashMap<String, String[]> map_zhanghu = new HashMap<String, String[]>();
		
		/**
		 * ѭ����ѡ���ݣ����зֵ�����
		 * �� 
		 */
		HashMap<String,ArrayList<String>> map_bill = new HashMap<String,ArrayList<String>>();
		for (Object data : selectData) {
			SettlementAggVO  slBillVO = (SettlementAggVO)data;
			SettlementHeadVO    slHVO = (SettlementHeadVO)slBillVO.getParentVO();
//			SettlementBodyVO[] slBVOs = (SettlementBodyVO[])slBillVO.getChildrenVO();
			
			String systemcode = slHVO.getSystemcode();			// ��Դϵͳ
			String pk_settlement = slHVO.getPk_settlement();	// ���㵥pk
			String pk_org = slHVO.getPk_org();	// ��֯pk
			if (!map_zhanghu.containsKey(pk_org)) {
				map_zhanghu.put(pk_org, this.getZhanghuInfo(pk_org));
			}
			
			if ("1".equals(systemcode)) {
				// 1��Ӧ��
			} else if ("107".equals(systemcode)) {
				// 2����������
			} else {
				// 3���������� ���账��
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
		 * ���շֵ��Ľ�� ���д������з��У� ���� �²����뵥�ݡ�
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
				// Ӧ��
				genList.add(this.genXbsqByYf(pkSettlementStr, map_zhanghu));
			} if ("107".equals(systemcode)) {
				// ����
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
	 * ���� Ӧ�� �� �²�����
	 * 0���²����뵥
	 * 1����ϵ��
	 */
	private Object[] genXbsqByYf(String pkSettlementStr, HashMap<String, String[]> map_zhanghu) throws Exception {
		String creator = InvocationInfoProxy.getInstance().getUserId();	// ��ǰ������
		IUAPQueryBS itf = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		AggAllocateApplyVO billVO = new AggAllocateApplyVO();
		/**
		 *  ��ϵ�� key = uuidΨһ���������� �²������е��Զ������С� 
		 *  	value = ArrayList<String[]> ������ ���� �²������е� ���㵥 ����pk
		 */
		HashMap<String, ArrayList<String[]>> link = new HashMap<String, ArrayList<String[]>>();
				
		StringBuffer querySQL = 
		new StringBuffer("select ")
				.append(" js.pk_settlement ")	// 0 ���㵥id
				.append(",jsb.pk_detail ")		// 1 ���㵥bid
				.append(",js.pk_group ")		// 2 pk_group
				.append(",js.pk_org ")			// 3 pk_org
				.append(",js.pk_org_v ")		// 4 pk_org_v
				.append(",jsb.pay ")			// 5 ���
				.append(",case fkb.prepay when 0 then 'Ӧ����' when 1 then 'Ԥ����' else 'δ֪' end ")	// 6 ��������
				.append(",fktype.name ")		// 7 ����ҵ������
				.append(",zjxm.pk_fundplan ")	// 8 �ʽ���Ŀpk
				.append(",zjxm.name ")			// 9 �ʽ���Ŀname
				.append(",case when jsb.systemcode = 'erm' then bi.defitem5 else jsb.memo end ")			// 10 ���㵥-ժҪ
				.append(",js.billcode ")		// 11 ҵ�񵥺�
				.append(" from cmp_settlement js ")
				.append(" inner join cmp_detail jsb on js.pk_settlement = jsb.pk_settlement ")
				.append(" left join ap_payitem fkb on (jsb.pk_billdetail = fkb.pk_payitem and fkb.dr = 0) ")
				.append(" left join ap_paybill fk on (fkb.pk_paybill = fk.pk_paybill and fk.dr = 0) ")
				.append(" left join fi_recpaytype fktype on (fkb.pk_recpaytype = fktype.pk_recpaytype) ")
				// ��֧��Ŀ
				.append(" left join bd_inoutbusiclass szxm on (fkb.pk_subjcode = szxm.pk_inoutbusiclass) ")
				// �ʽ���Ŀ
				.append(" left join bd_fundplan zjxm on (szxm.def5 = zjxm.pk_fundplan) ")
				// ȡժҪ
				.append(" left join er_busitem bi on (bi.pk_busitem = jsb.pk_billdetail)")
				.append(" where js.dr = 0 and jsb.dr = 0 ")
				.append(" and nvl(js.def1,'N') <> '").append(IPub_data.JSXB_done).append("' ")
				.append(" and js.pk_settlement in ").append(pkSettlementStr)
		;
		ArrayList list = (ArrayList)itf.executeQuery(querySQL.toString(), new ArrayListProcessor());
				
		/**
		 *  �����У�����ժҪ��
		 * key: ժҪ
		 * value: 0- AllocateApplyBVO (�Զ���01)
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
			String fkxz 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[6]);	// ��������
			String fklx 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[7]);	// ��������
			
			String zjjxxm 		= PuPubVO.getString_TrimZeroLenAsNull(rowObj[8]);	// �ʽ���Ŀpk
			String zjjxxm_name	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[9]);	// �ʽ���Ŀname
			
			String zhaiyao 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[10]);	// ���㵥-ժҪ
			String billCode = PuPubVO.getString_TrimZeroLenAsNull(rowObj[11]);	// ҵ�񵥺�
			
			if (zjjxxm == null) {
				zjjxxm = IPub_data.JSXB_jhxm_caigou;
				zjjxxm_name = "�ɹ���";
			}
			
//			String zjjxxm	= IPub_data.JSXB_jhxm_caigou;	// �ʽ�ƻ���Ŀ��Ĭ��Ϊ�ɹ��
			
//			if ("���̿�".equals(fkxz)) {
//				zjjxxm = IPub_data.JSXB_jhxm_gongcheng;	// ���̿�
//			}
			
			if (PK_GROUP == null) {
				PK_GROUP = pk_group;
				PK_ORG = pk_org;
				PK_ORG_V = pk_org_v;
			}
			
			String[] zhanghuInfo = map_zhanghu.get(pk_org);
			
//			String zhaiyao = fkxz + fklx;
			String key = pk_jsd + fkxz + zjjxxm_name; // key = ���㵥+��������+�ʽ�ƻ���Ŀ
			
			if (!map_body.containsKey(key)) {
				rowCount++;
				String uuid = UUID.randomUUID().toString();
				AllocateApplyBVO bVO = new AllocateApplyBVO();
				bVO.setApplyamount(UFDouble.ZERO_DBL);		// ��Ҫ����
				bVO.setApplyolcamount(UFDouble.ZERO_DBL);	// ��Ҫ����
				bVO.setApplyolcrate(new UFDouble(1.00));
				bVO.setDr(0);
				bVO.setIsnetpay(UFBoolean.FALSE);
				bVO.setPk_financeorg_r(pk_org);
				bVO.setPk_financeorg_r_v(pk_org_v);
				bVO.setPk_balatype(IPub_data.JSXB_pk_balatype_ZZ);	// ���㷽ʽ bd_balatype Ĭ��Ϊת��
				
				bVO.setPk_bankacc_r(zhanghuInfo[0]);	// �տ��˻�PK org_orgs.def3
				bVO.setBankacccode_r(zhanghuInfo[1]);	// �տ������˻� bd_bankaccsub.ACCNUM
				bVO.setBankaccname_r(zhanghuInfo[2]);	// �տ����л��� bd_bankaccsub.ACCNAME
				bVO.setBankname_r(zhanghuInfo[3]);		// �տλ�������� bd_bankaccbas.PK_BANKDOC
				bVO.setPk_accid(zhanghuInfo[4]);		// �տλ�ڲ��˻� bd_accid
				
				bVO.setPk_planitem_r(zjjxxm);	// �ʽ�ƻ���Ŀ
				bVO.setRemark("");	// ժҪ ��Ҫ����
				bVO.setRowno("" + (rowCount * 10));
				bVO.setStatus(VOStatus.NEW);
				bVO.setAttributeValue("pseudocolumn", (rowCount - 1));
				bVO.setVuserdef1(uuid);		// �����Զ�����1����¼������ϵ
				bVO.setVuserdef2(billCode);	// ҵ�񵥺�
				
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
				String zhaiyao_new = bVO.getRemark() + "��" + zhaiyao + "��";
				if (zhaiyao_new.length() < 280) {
					// �²������ժҪ  ��󳤶�Ϊ 300�� ������һ�����ƣ�ֻ��С��280 �ŵ���
					bVO.setRemark(bVO.getRemark() + "��" + zhaiyao + "��"); // ��Ҫ����
				} else if (zhaiyao_new.endsWith("...")) {
					// ����� ... ��β �Ͳ��ٴ���
				} else {
					bVO.setRemark(bVO.getRemark() + "...");
				}
			}
			totalMny = totalMny.add(mny);
		}
		
		// �����ͷvo
		UFDate nowDate = new UFDate();
		AllocateApplyVO hVO = new AllocateApplyVO();
		hVO.setApplydate(nowDate);
		hVO.setApplyglctotal(UFDouble.ZERO_DBL);
		hVO.setApplygllctotal(UFDouble.ZERO_DBL);
		hVO.setApplytotal(totalMny);
		hVO.setBillstatus(5);
		hVO.setBusidate(nowDate);
		hVO.setBusitype(2);
		hVO.setBillmaker(creator);	// �Ƶ���
		hVO.setCreator(creator);	// ������
		hVO.setDiffstatus("serverBefore");
		hVO.setDr(0);
		hVO.setIsfinancereturned(UFBoolean.FALSE);
		hVO.setMemo("�����Ƶ�-Ӧ����");
		hVO.setPk_billtype(IPub_data.BILLTYPE_36K1);	// �²�����
		hVO.setPk_billtypeid(IPub_data.BILLTYPE_36K1_ID);	
		hVO.setPk_currtype(IPub_data.CURRTYPE_CNY);		// ����-�����
		hVO.setPk_group(PK_GROUP);
		hVO.setPk_org(PK_ORG);
		hVO.setPk_org_v(PK_ORG_V);
		hVO.setPk_payorg(IPub_data.JSXB_pk_payorg);		// �ܲ�-��ʱ�̶�
		hVO.setPk_payorg_v(IPub_data.JSXB_pk_payorg_v);	// �ܲ�-��ʱ�̶�
		hVO.setVbillstatus(-1);
		hVO.setStatus(VOStatus.NEW);	// ��Ҫ���� VO״̬Ϊ ����
		
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
		getCaSignerXbsq().signWithClient(billVO);	// ����ǩ�� hVO.setEncryptkey()
		
		return new Object[]{
				billVO,
				link,
		};
	}
	
	/**
	 * ���� ���� �� �²�����
	 * 0���²����뵥
	 * 1����ϵ��
	 */
	private Object[] genXbsqByBx(String pkSettlementStr, HashMap<String, String[]> map_zhanghu) throws Exception {
		String creator = InvocationInfoProxy.getInstance().getUserId();	// ��ǰ������
		IUAPQueryBS itf = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		AggAllocateApplyVO billVO = new AggAllocateApplyVO();
		/**
		 *  ��ϵ�� key = uuidΨһ���������� �²������е��Զ������С� 
		 *  	value = ArrayList<String[]> ������ ���� �²������е� ���㵥 ����pk
		 */
		HashMap<String, ArrayList<String[]>> link = new HashMap<String, ArrayList<String[]>>();
				
		StringBuffer querySQL = 
		new StringBuffer("select ")
				.append(" js.pk_settlement ")	// 0 ���㵥id
				.append(",jsb.pk_detail ")		// 1 ���㵥bid
				.append(",js.pk_group ")		// 2 pk_group
				.append(",js.pk_org ")			// 3 pk_org
				.append(",js.pk_org_v ")		// 4 pk_org_v
				.append(",jsb.pay ")			// 5 ���
				.append(",szxm.def5 ")			// 6 �ʽ�ƻ���Ŀpk
				.append(",zjjxxm.name ")		// 7 �ʽ�ƻ���Ŀname
				.append(",case when jsb.systemcode = 'erm' then bi.defitem5 else jsb.memo end ")			// 8 ���㵥-ժҪ
				.append(",js.billcode ")		// 9 ҵ�񵥺�
				.append(" from cmp_settlement js ")
				.append(" inner join cmp_detail jsb on js.pk_settlement = jsb.pk_settlement ")
				.append(" left join er_busitem bxb on (jsb.pk_billdetail = bxb.PK_BUSITEM and bxb.dr = 0) ")
				.append(" left join er_bxzb bx on (bxb.PK_JKBX = bx.PK_JKBX and bx.dr = 0) ")
				.append(" left join bd_inoutbusiclass szxm ON (bxb.SZXMID = szxm.PK_INOUTBUSICLASS AND SZXM.DR = 0) ")
				.append(" left join bd_fundplan zjjxxm ON (szxm.DEF5 = zjjxxm.PK_FUNDPLAN AND ZJJXXM.dr = 0) ")
				// ȡժҪ
				.append(" left join er_busitem bi on (bi.pk_busitem = jsb.pk_billdetail)")
				.append(" where js.dr = 0 and jsb.dr = 0 ")
				.append(" and nvl(js.def1,'N') <> '").append(IPub_data.JSXB_done).append("' ")
				.append(" and js.pk_settlement in ").append(pkSettlementStr)
		;
		ArrayList list = (ArrayList)itf.executeQuery(querySQL.toString(), new ArrayListProcessor());
		
		/**
		 * �����У�����ժҪ��
		 * key: ժҪ
		 * value: 0- AllocateApplyBVO (�Զ���01)
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
			String zjjxxm 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[6]);		// �ʽ�ƻ���Ŀpk
			String zjjhxm_name 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[7]);	// �ʽ�ƻ���Ŀname
			String zhaiyao 	= PuPubVO.getString_TrimZeroLenAsNull(rowObj[8]);	// ժҪ
			String billCode = PuPubVO.getString_TrimZeroLenAsNull(rowObj[9]);	// ҵ�񵥺�
			
			if (PK_GROUP == null) {
				PK_GROUP = pk_group;
				PK_ORG = pk_org;
				PK_ORG_V = pk_org_v;
			}
			
			String[] zhanghuInfo = map_zhanghu.get(pk_org);
			
			String key = pk_jsd + "##" +zjjhxm_name; // �� ���㵥+�ʽ�ƻ���Ŀ  ��������
			
			if (!map_body.containsKey(key)) {
				rowCount++;
				String uuid = UUID.randomUUID().toString();
				AllocateApplyBVO bVO = new AllocateApplyBVO();
				bVO.setApplyamount(UFDouble.ZERO_DBL);		// ��Ҫ����
				bVO.setApplyolcamount(UFDouble.ZERO_DBL);	// ��Ҫ����
				bVO.setApplyolcrate(new UFDouble(1.00));
				bVO.setDr(0);
				bVO.setIsnetpay(UFBoolean.FALSE);
				bVO.setPk_financeorg_r(pk_org);
				bVO.setPk_financeorg_r_v(pk_org_v);
				bVO.setPk_balatype(IPub_data.JSXB_pk_balatype_ZZ);	// ���㷽ʽ bd_balatype Ĭ��Ϊת��
				
				bVO.setPk_bankacc_r(zhanghuInfo[0]);	// �տ��˻�PK org_orgs.def3
				bVO.setBankacccode_r(zhanghuInfo[1]);	// �տ������˻� bd_bankaccsub.ACCNUM
				bVO.setBankaccname_r(zhanghuInfo[2]);	// �տ����л��� bd_bankaccsub.ACCNAME
				bVO.setBankname_r(zhanghuInfo[3]);		// �տλ�������� bd_bankaccbas.PK_BANKDOC
				bVO.setPk_accid(zhanghuInfo[4]);		// �տλ�ڲ��˻� bd_accid
				
				bVO.setPk_planitem_r(zjjxxm);	// �ʽ�ƻ���Ŀ
				bVO.setRemark(""); // ��Ҫ����
				bVO.setRowno("" + (rowCount * 10));
				bVO.setStatus(VOStatus.NEW);
				bVO.setAttributeValue("pseudocolumn", (rowCount - 1));
				bVO.setVuserdef1(uuid);		// �����Զ�����1����¼������ϵ
				bVO.setVuserdef2(billCode);	// ҵ�񵥺�
				
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
				String zhaiyao_new = bVO.getRemark() + "��" + zhaiyao + "��";
				if (zhaiyao_new.length() < 280) {
					// �²������ժҪ  ��󳤶�Ϊ 300�� ������һ�����ƣ�ֻ��С��280 �ŵ���
					bVO.setRemark(bVO.getRemark() + "��" + zhaiyao + "��"); // ��Ҫ����
				} else if (zhaiyao_new.endsWith("...")) {
					// ����� ... ��β �Ͳ��ٴ���
				} else {
					bVO.setRemark(bVO.getRemark() + "...");
				}
			}
			totalMny = totalMny.add(mny);
		}
		
		// �����ͷvo
		UFDate nowDate = new UFDate();
		AllocateApplyVO hVO = new AllocateApplyVO();
		hVO.setApplydate(nowDate);
		hVO.setApplyglctotal(UFDouble.ZERO_DBL);
		hVO.setApplygllctotal(UFDouble.ZERO_DBL);
		hVO.setApplytotal(totalMny);
		hVO.setBillstatus(5);
		hVO.setBusidate(nowDate);
		hVO.setBusitype(2);
		hVO.setBillmaker(creator);	// �Ƶ���
		hVO.setCreator(creator);	// ������
		hVO.setDiffstatus("serverBefore");
		hVO.setDr(0);
		hVO.setIsfinancereturned(UFBoolean.FALSE);
		hVO.setMemo("�����Ƶ�-������");
		hVO.setPk_billtype(IPub_data.BILLTYPE_36K1);	// �²�����
		hVO.setPk_billtypeid(IPub_data.BILLTYPE_36K1_ID);	
		hVO.setPk_currtype(IPub_data.CURRTYPE_CNY);		// ����-�����
		hVO.setPk_group(PK_GROUP);
		hVO.setPk_org(PK_ORG);
		hVO.setPk_org_v(PK_ORG_V);
		hVO.setPk_payorg(IPub_data.JSXB_pk_payorg);		// �ܲ�-��ʱ�̶�
		hVO.setPk_payorg_v(IPub_data.JSXB_pk_payorg_v);	// �ܲ�-��ʱ�̶�
		hVO.setVbillstatus(-1);
		hVO.setStatus(VOStatus.NEW);	// ��Ҫ���� VO״̬Ϊ ����
		
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
		getCaSignerXbsq().signWithClient(billVO);	// ����ǩ�� hVO.setEncryptkey()
		
		return new Object[]{
				billVO,
				link,
		};
	}
	
	/**
	 * ������֯���ҵ� �˻���Ϣ
	 * 0-�˻�pk
	 * 1-�˻�code
	 * 2-�˻�name
	 * 3-������pk
	 * 4-�ڲ��˻�pk
	 */
	private String[] getZhanghuInfo(String pk_org) throws Exception {
		IUAPQueryBS itf = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName());
		StringBuffer querySQL = 
		new StringBuffer("SELECT ")
				.append(" bs.PK_BANKACCSUB ")	// �˻�pk
				.append(",bs.ACCNUM ")			// �˻�code
				.append(",bs.ACCNAME ")			// �˻�name
				.append(",bb.PK_BANKDOC ")		// ������pk
				.append(",ac.PK_ACCID ")		// �ڲ��˻�pk
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
