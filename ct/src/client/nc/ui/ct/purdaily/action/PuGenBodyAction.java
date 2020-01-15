package nc.ui.ct.purdaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.logging.Logger;
import nc.ui.ct.saledaily.action.GenHtmxAction;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.uif2.NCAction;
import nc.vo.ct.entity.CtZzDzInfoVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

import org.codehaus.jackson.map.ObjectMapper;

public class PuGenBodyAction extends NCAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1051151601078510030L;
	
	public PuGenBodyAction() {
		super();
		setBtnName("������ϸ����");
		setCode("genBodyAction");
	}

	private nc.ui.ct.model.CTModel model;
	private nc.ui.ct.purdaily.view.PurdailyBillForm editor;
	
	public nc.ui.ct.model.CTModel getModel() {
		return model;
	}
	public void setModel(nc.ui.ct.model.CTModel model) {
		this.model = model;
	}
	public nc.ui.ct.purdaily.view.PurdailyBillForm getEditor() {
		return editor;
	}
	public void setEditor(nc.ui.ct.purdaily.view.PurdailyBillForm editor) {
		this.editor = editor;
	}

	@Override
	public void doAction(ActionEvent event) throws Exception {
		
		boolean hasBody = this.getEditor().getBillCardPanel().getBillModel().getRowCount() > 0;
		if (hasBody) {
			Integer isFugai = MessageDialog.showYesNoDlg(this.getEditor(), "", "����������ݣ��Ƿ񸲸ǡ�");
			if (isFugai == MessageDialog.ID_YES) {
				// ��ձ���
				this.getEditor().getBillCardPanel().getBillModel().clearBodyData();
			} else {
				return;
			}
		}
		
		// pk_group
		String pk_group = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("pk_group").getValueObject());
		// pk_org
		String pk_org = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("pk_org").getValueObject());
		// pk_org_v
		String pk_org_v = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("pk_org_v").getValueObject());
		
		// ������Ϣ-������ʽ
		UIRefPane dz_type_ref = (UIRefPane)this.getEditor().getBillCardPanel().getHeadItem("vdef19").getComponent();
		String dz_type = dz_type_ref.getRefName();
		// ������Ϣ-��������
		String vdef1 = PuPubVO.getString_TrimZeroLenAsNull(this.getEditor().getBillCardPanel().getHeadItem("vdef1").getValueObject());
		ObjectMapper objectMapper = new ObjectMapper();
		CtZzDzInfoVO dzInfoVO = null;
		if (vdef1 != null) {
			dzInfoVO = objectMapper.readValue(vdef1, CtZzDzInfoVO.class);
		}
		
		// ��ͬ��ʼ����
		UFDate ht_date_begin = PuPubVO.getUFDate(
			this.getEditor().getBillCardPanel().getHeadItem("valdate").getValueObject());
		// ��ͬ��ֹ����
		UFDate ht_date_end = PuPubVO.getUFDate(
			this.getEditor().getBillCardPanel().getHeadItem("invallidate").getValueObject());
//		// ���⿪ʼ����
//		UFDate mz_date_begin = PuPubVO.getUFDate(
//			this.getEditor().getBillCardPanel().getHeadItem("vdef6").getValueObject());
//		// �����������
//		UFDate mz_date_end = PuPubVO.getUFDate(
//			this.getEditor().getBillCardPanel().getHeadItem("vdef14").getValueObject());
		// ����-����
		UFDouble price_up = PuPubVO.getUFDouble_NullAsZero(
			this.getEditor().getBillCardPanel().getHeadItem("vdef9").getValueObject());
		// ���-����
		UFDouble area_up = PuPubVO.getUFDouble_NullAsZero(
			this.getEditor().getBillCardPanel().getHeadItem("vdef8").getValueObject());
		// ����-����
		UFDouble price_down = PuPubVO.getUFDouble_NullAsZero(
			this.getEditor().getBillCardPanel().getHeadItem("vdef12").getValueObject());
		// ���-����
		UFDouble area_down = PuPubVO.getUFDouble_NullAsZero(
			this.getEditor().getBillCardPanel().getHeadItem("vdef11").getValueObject());
		
		// �����
		UFDouble area_all = area_up.add(area_down);
		
		// ���ʽ
		UIRefPane fk_type_ref = (UIRefPane)this.getEditor().getBillCardPanel().getHeadItem("vdef5").getComponent();
		String fk_type = fk_type_ref.getRefName();
		// �������ڣ��£�
		Integer fk_month = -1;
			 if("�¸�".equals(fk_type))	fk_month = 1;
		else if("����".equals(fk_type))	fk_month = 3;
		else if("���긶".equals(fk_type))	fk_month = 6;
		else if("�긶".equals(fk_type))	fk_month = 12;
		else throw new Exception("�޷�����ĸ��ʽ");
		
		// ֧����Ŀ1
		String zcxm_1 = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("vdef13").getValueObject());
		// ��ͬ���ޣ��£�
		Integer ht_month = PuPubVO.getInteger_NullAs(
			this.getEditor().getBillCardPanel().getHeadItem("vdef2").getValueObject(), 0);
		
		// ��Լ��֤��
		String lybzj = PuPubVO.getString_TrimZeroLenAsNull(
				this.getEditor().getBillCardPanel().getHeadItem("vdef7").getValueObject());
		// ֧����Ŀ����֤��
		String zcxm_bzj = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("vdef18").getValueObject());
//		Map<String, String> lybzjMap = new HashMap<>();
//		lybzjMap.put("2005", "1001N5100000000EYKBT");	// ��֤�𣨾�Ӫ��
//		lybzjMap.put("2022", "1001N510000000AEFM6J");	// ��֤������Ŀ��
//		String lybzj_szxm = null;
//		UFDouble lybzj_mny = null;
//		if (!"��".equals(lybzj)) {
//			// �� ȫ�Ƕ��ţ�ת���� ���
//			lybzj.replaceAll("��", ",");
//			
//			if (lybzj.contains(",")) {
//				String[] splitStr = lybzj.split(",");
//				lybzj_szxm = lybzjMap.get(splitStr[0]);
//				lybzj_mny = PuPubVO.getUFDouble_NullAsZero(splitStr[1]);
//			}
//		}
		String lybzj_szxm = zcxm_bzj;
		UFDouble lybzj_mny = UFDouble.ZERO_DBL;
		try {
			lybzj_mny = PuPubVO.getUFDouble_NullAsZero(lybzj);
		} catch (Exception ex) {
			Logger.error(ex.getMessage());
		}
		
		// �ܺ�ͬ�ܶ�������֮�� �������⣩
		UFDouble total_ht_money = UFDouble.ZERO_DBL;
		
		// �ӵڼ���֮��ʼ���⣬Ĭ��Ϊ12������Ϊ�ӵڶ��꿪ʼ
		Integer zzBeginMonth = 12;
		
		// �� ��ͬ���ޣ��£��� ������������� ����֡�
		Integer[] exec_time = getExecTime(ht_month, zzBeginMonth, dzInfoVO);
//				new Integer[]{
////			12, 12, 12, 12, 12
//			12, 6,6, 6,6, 6,6, 6,6
//		};
		// �ҵ� ÿ�������ڼ�� ��ʼ���������ڡ�
		UFDate[][] exec_date = exec_date(ht_date_begin, ht_date_end, exec_time);
		
		// �����������������Ϣ
		UFDouble[][] dzInfo = getZzDzInfo(dz_type, dzInfoVO, exec_time);
		
		// �ҵ� ÿ���ڼ���������-����
		UFDouble[] dzLvPrice = dzInfo[0];

		// �ҵ� ÿ���ڼ��������-����
		UFDouble[] dzJePrice = dzInfo[1];
		
		// �ҵ� ÿ���ڼ���������-�ܶ�
		UFDouble[] dzLvMoney = dzInfo[2];

		// �ҵ� ÿ���ڼ��������-�ܶ�
		UFDouble[] dzJeMoney = dzInfo[3];

		// �����һ��������Ϊ ���� ���ܶ������ ����
		UFDouble FirstYearMny = null;
		
		/**
		 * HK 2020��1��9��20:35:22
		 * ��Ϊ ����˰��
		 */
		Integer ftaxtypeflag = 0;	// 0��Ӧ˰�ں�		1��Ӧ˰���
		
		// �жϣ��Ƿ���Ҫ���� ��Լ����
		if (lybzj_szxm != null || lybzj_mny != null) {
			this.getEditor().getBillCardPanel().getBillModel().addLine();
			Integer rowNo = this.getEditor().getBillCardPanel().getBillModel().getRowCount() - 1;	// ��ǰ��������
			String body_rowno = "" + ((rowNo+1) * 10);
			// ��֤�� ����˰��
			UFDouble body_tax = UFDouble.ZERO_DBL;
			UFDouble body_mny = PuPubVO.getUFDouble_NullAsZero(lybzj_mny).sub(body_tax);	// ��˰���
			
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_rowno, rowNo, "crowno");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(lybzj_szxm, rowNo, "vbdef1");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_group, rowNo, "pk_group");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_org");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_org_v");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_financeorg");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_financeorg_v");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_arrvstock");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_arrvstock_v");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_date_begin, rowNo, "vbdef3");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_date_end, rowNo, "vbdef4");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(ht_date_begin, rowNo, "vbdef2");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(lybzj_mny, rowNo, "norigtaxmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(lybzj_mny, rowNo, "ntaxmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(lybzj_mny, rowNo, "ncaltaxmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("1.00/1.00", rowNo, "vqtunitrate");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("1.00/1.00", rowNo, "vchangerate");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "csendcountryid");	// �������
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "crececountryid");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "ctaxcountryid");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(2, rowNo, "fbuysellflag");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(ftaxtypeflag, rowNo, "ftaxtypeflag");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_tax, rowNo, "ntax");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(UFDouble.ZERO_DBL, rowNo, "ntaxrate");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "ncalcostmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "nmny");
			this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "norigmny");
		}
		
		/**
		 * �������������ѭ������ ��Ϊÿ������� ���ͬ��
		 */
		for (int time_i = 0; time_i < exec_time.length; time_i++)
		{
			UFDate date_begin = exec_date[time_i][0];
			UFDate date_end = exec_date[time_i][1];
			
			Integer time_month = exec_time[time_i];
			
			// ÿ��Ľ�� = ����-���� * ���-���� + ����-���� * ���-����
			UFDouble day_money = UFDouble.ZERO_DBL;
			
			// �������-����
			if (dzLvPrice != null) {
				UFDouble dzLv_temp = dzLvPrice[time_i];
				day_money = 
					price_up.multiply(dzLv_temp).multiply(area_up)
				.add(
					price_down.multiply(dzLv_temp).multiply(area_down)
				);
			} else if (dzJePrice != null) {	// ������-����
				UFDouble dzJe_temp = dzJePrice[time_i];
				day_money = 
					price_up.add(dzJe_temp).multiply(area_up)
				.add(
					price_down.add(dzJe_temp).multiply(area_down)
				);
			} else { // ������
				day_money = 
					price_up.multiply(area_up)
				.add(
					price_down.multiply(area_down)
				);
			}
			
			// ������������ = ��ͬ��ֹ���� - ��ͬ��ʼ���� + 1 (���� ͷ �� β)
			Integer days = date_end.getDaysAfter(date_begin) + 1;
			if (time_i == 0) {
				days = 365;
			}
			
			// �ȼ���� ÿ�������ڼ�ĺ�ͬ�ܶ
			// ��ͬ�ܽ�� ntotalorigmny
			// �������� = ���� * ʵ������
			UFDouble ht_money = day_money.multiply(days).setScale(2, UFDouble.ROUND_HALF_UP);
			
			// ��¼��һ��� �����
			if (time_i==0) {
				FirstYearMny = ht_money;
			}
			
			// ����� ��ͬ�ܶ�ĵ�����ʽ�� ��ô ����Ҫ�������Ϊ����
			// ��Ϊ�����ڼ� ����������Ҫ����� ʵ���ڼ�� ��ͬ�������� �������� / һ���12���� ���ó�������Ȼ����� �����
			if (dzLvMoney != null || dzJeMoney != null) {
				ht_money = FirstYearMny.multiply(
							new UFDouble(time_month).div(12.00)
						);
			}
			// �ٽ��� ��ͬ���ĵ�������
			if (dzLvMoney != null) {
				UFDouble dzLv_temp = dzLvMoney[time_i];
				ht_money = ht_money.multiply(dzLv_temp);
			} else if (dzJeMoney != null) {
				UFDouble dzJe_temp = dzJeMoney[time_i];
				ht_money = ht_money.add(dzJe_temp);
			}
			
			// ��¼ ��ͬ�ܶ�
			total_ht_money = total_ht_money.add(ht_money);
			
			// ������� = ��ͬ���ޣ��£� / �������ڣ��£�
			Integer fk_count = time_month / fk_month;
			
			Object[][] qijian = GenHtmxAction.genQiJian(date_begin, date_end, fk_month, fk_count, null);
			
			UFDouble total_money = UFDouble.ZERO_DBL;	// �ѷ���ĺ�ͬ���
			
			// ˰�� vdef4
			UIRefPane taxrate_ref = (UIRefPane)this.getEditor().getBillCardPanel().getHeadItem("vdef4").getComponent();
			String taxrate = taxrate_ref.getRefName().replaceAll("%", "");
			
			// ���ݿͻ� �ҵ�˰��˰��
	//		String ctaxcodeid = "1001N5100000005N6HJG";
			UFDouble ntaxrate = new UFDouble(taxrate);
			
			// ���ݸ�����������ɱ�������
			for(int i = 0; i < fk_count; i++) {
				UFDate body_date_begin = PuPubVO.getUFDate(qijian[i][0]);
				UFDate body_date_end = PuPubVO.getUFDate(qijian[i][1]);
				UFDate body_date_fk = body_date_begin;
				UFDouble body_money = UFDouble.ZERO_DBL;
				if (i == fk_count - 1) {
					body_money = ht_money.sub(total_money);
				} else {
					body_money = ht_money.div(fk_count).setScale(2, UFDouble.ROUND_HALF_UP);
					total_money = total_money.add(body_money);
				}
				
				/**
				 * ����˰���㷨�� 
				 * 		���� ˰�� = ��˰ * ˰��  
				 * 		���� ��˰ = ��˰ - ˰��
				 */
//				UFDouble body_tax = body_money.multiply(ntaxrate).div(100.00).setScale(2, UFDouble.ROUND_HALF_UP);
//				UFDouble body_mny = body_money.sub(body_tax);	// ��˰���
				
				/**
				 * ����˰���㷨�� 
				 * 		���� ��˰ = ��˰ / (1+˰��)  
				 * 		���� ˰�� = ��˰ - ��˰
				 */
				UFDouble body_mny = body_money.div(UFDouble.ONE_DBL.add(ntaxrate.div(100.00)));
				UFDouble body_tax = body_money.sub(body_mny);
				
				this.getEditor().getBillCardPanel().getBillModel().addLine();
				Integer rowNo = this.getEditor().getBillCardPanel().getBillModel().getRowCount() - 1;	// ��ǰ��������
				String body_rowno = "" + ((rowNo+1) * 10);
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_rowno, rowNo, "crowno");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(zcxm_1, rowNo, "vbdef1");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_group, rowNo, "pk_group");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_org");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_org_v");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_financeorg");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_financeorg_v");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org, rowNo, "pk_arrvstock");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(pk_org_v, rowNo, "pk_arrvstock_v");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_date_begin, rowNo, "vbdef3");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_date_end, rowNo, "vbdef4");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_date_fk, rowNo, "vbdef2");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_money, rowNo, "norigtaxmny");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_money, rowNo, "ntaxmny");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_money, rowNo, "ncaltaxmny");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt("1.00/1.00", rowNo, "vqtunitrate");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt("1.00/1.00", rowNo, "vchangerate");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "csendcountryid");	// �������
				this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "crececountryid");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt("0001Z010000000079UJJ", rowNo, "ctaxcountryid");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(2, rowNo, "fbuysellflag");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(ftaxtypeflag, rowNo, "ftaxtypeflag");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_tax, rowNo, "ntax");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(ntaxrate, rowNo, "ntaxrate");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "ncalcostmny");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "nmny");
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(body_mny, rowNo, "norigmny");
				// ���㵥�ۣ�ÿ�졢ÿƽ�ף�
				UFDouble js_price = day_money.div(area_all).setScale(2, UFDouble.ROUND_HALF_UP);
				this.getEditor().getBillCardPanel().getBillModel().setValueAt(js_price, rowNo, "vbdef5");
			}
		}
		
		// �� ���շ������
		getEditor().getBillCardPanel().getBillModel().loadLoadRelationItemValue();
		// ��ͷ- ��ͬ�ܶ�
		this.getEditor().getBillCardPanel().getHeadItem("ntotalorigmny").setValue(total_ht_money);
				
	}
	
	/**
	 * ��ȡ�����ڼ�ε� ��ʼ���������ڡ�
	 */
	private static UFDate[][] exec_date(UFDate ht_date_begin, UFDate ht_date_end, Integer[] exec_time) {
		UFDate[][] result = new UFDate[exec_time.length][];
		
		UFDate date_begin = ht_date_begin;
		UFDate date_end = null;
		
		for(int i = 0; i < exec_time.length; i++) {
			
			int addMonth = exec_time[i];
			
			date_end = addMonth(date_begin, addMonth);
			
			result[i] = new UFDate[]{
				PuPubVO.getUFDate(date_begin.clone()),
				PuPubVO.getUFDate(date_end.clone()),
			};
			
			date_begin = date_end.getDateAfter(1);
		}
		
		return result;
	}

	/**
	 * ���� ���ڣ����ӵ��·ݣ� ���� ֮�������(ע��Ҫ��һ��)��
	 */
	public static UFDate addMonth(UFDate date, Integer addMonth_parm) {
		
		if(addMonth_parm == 0) {
			return date;
		}
		
		UFDate result = null;
		
		Integer year = date.getYear();
		Integer month = date.getMonth();
		Integer day = date.getDay();
		
		Integer newMonth = month + addMonth_parm;
		
		Integer addYear  = newMonth / 12;
		Integer addMonth = newMonth % 12;
		
		Integer afterYear = year+addYear;
		Integer afterMonth = addMonth;
		
		if (addMonth == 0) {
			afterYear--;
			afterMonth = 12;
		}
		
		result = new UFDate(""+(afterYear)+"-"+(afterMonth)+"-"+day);
		
		return result.getDateBefore(1);
	}
	
	/**
	 * ���� ��ͬ���ޣ��£������ⷽʽ���������� �� ���� ��ֵĽ׶� ������Ϣ��
	 */
	public static UFDouble[][] getZzDzInfo(String dz_type, CtZzDzInfoVO dzInfoVO, Integer[] exec_time) {
		
		// �ҵ� ÿ���ڼ���������-����
		UFDouble[] dzLvPrice = null;

		// �ҵ� ÿ���ڼ��������-����
		UFDouble[] dzJePrice = null;

		// �ҵ� ÿ���ڼ���������-�ܶ�
		UFDouble[] dzLvMoney = null;

		// �ҵ� ÿ���ڼ��������-�ܶ�
		UFDouble[] dzJeMoney = null;
		
		if(dz_type != null
		&& dzInfoVO != null
		&& exec_time != null
		) {
//			Integer dzMonth = dzInfoVO.getDzMonth();
			UFDouble dzLv = PuPubVO.getUFDouble_ZeroAsNull(dzInfoVO.getDzLv());
			UFDouble dzJe = PuPubVO.getUFDouble_ZeroAsNull(dzInfoVO.getDzJe());
	
			if ("����".equals(dz_type)) {
				if (dzLv != null) {// �����ı��� Ĭ��Ϊ1
					dzLvPrice = new UFDouble[exec_time.length];
					dzLvPrice[0] = UFDouble.ONE_DBL;
					for (int i = 1; i < exec_time.length; i++) {
						// ��ȡ�������� ��һ�ڵ�ֵ
						UFDouble temp = dzLvPrice[i-1];
//						if (i % dzMonth == 0 ) { // ����絽�˵������ޣ� ˵��Ҫ���� ���Ӵ���
							temp = temp.add(
								temp.multiply(dzLv).div(100.00)
							);
//						}
						dzLvPrice[i] = temp;
					}
				} else if (dzJe != null) {// �����Ľ�� Ĭ��Ϊ0
					dzJePrice = new UFDouble[exec_time.length];
					dzJePrice[0] = UFDouble.ZERO_DBL;
					for (int i = 1; i < exec_time.length; i++) {
						// ��ȡ�������� ��һ�ڵ�ֵ
						UFDouble temp = dzJePrice[i-1];
//						if (i % dzMonth == 0 ) { // ����絽�˵������ޣ� ˵��Ҫ���� ���Ӵ���
							temp = temp.add(
								dzJe
							);
//						}
						dzJePrice[i] = temp;
					}
				}
			} else if ("���ܼ�".equals(dz_type)) {
				if (dzLv != null) {// �����ı��� Ĭ��Ϊ1
					dzLvMoney = new UFDouble[exec_time.length];
					dzLvMoney[0] = UFDouble.ONE_DBL;
					for (int i = 1; i < exec_time.length; i++) {
						// ��ȡ�������� ��һ�ڵ�ֵ
						UFDouble temp = dzLvMoney[i-1];
//						if (i % dzMonth == 0 ) { // ����絽�˵������ޣ� ˵��Ҫ���� ���Ӵ���
							temp = temp.add(
								temp.multiply(dzLv).div(100.00)
							);
//						}
						dzLvMoney[i] = temp;
					}
				} else if (dzJe != null) {// �����Ľ�� Ĭ��Ϊ0
					dzJeMoney = new UFDouble[exec_time.length];
					dzJeMoney[0] = UFDouble.ZERO_DBL;
					for (int i = 1; i < exec_time.length; i++) {
						// ��ȡ�������� ��һ�ڵ�ֵ
						UFDouble temp = dzJeMoney[i-1];
//						if (i % dzMonth == 0 ) { // ����絽�˵������ޣ� ˵��Ҫ���� ���Ӵ���
							temp = temp.add(
								dzJe
							);
//						}
						dzJeMoney[i] = temp;
					}
				}
			}
		}
		
		return new UFDouble[][]{
			dzLvPrice,
			dzJePrice,
			dzLvMoney,
			dzJeMoney
		};
	}
	
	/**
	 * ���� ��ͬ���ޣ�������������ʼ��������������Ƶ�ʣ�����������ڼ��
	 */
	public static Integer[] getExecTime(Integer total, Integer begin, CtZzDzInfoVO zzInfo) {
		
		// ���������ϢΪ�գ�����Ϊ�����⣬ֱ�ӷ���  ��ͬ����
		if (zzInfo == null) {
			return new Integer[]{
				total
			};
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		// �������
		list.add(begin);
		// ֻ��Ƶ�ʴ���0 ���ܴ���
		Integer rate = zzInfo.getDzMonth();
		if (rate <=0 ) {
			return new Integer[]{
				total
			};
		}
		
		Integer sub = total - begin;
		// ѭ����ӣ�ʣ���ڼ����� �� ����Ƶ��
		while (sub > 0) {
			if (sub >= rate) {
				list.add(rate);
				sub = sub - rate;
			} else {
				list.add(sub);
				sub = 0;
			}
		}
		
		return list.toArray(new Integer[0]);
	}
	
}
