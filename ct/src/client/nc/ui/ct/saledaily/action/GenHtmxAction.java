package nc.ui.ct.saledaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.Vector;

import nc.ui.ct.action.HelpAction;
import nc.ui.ct.saledaily.view.SaledailyBillForm;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillModel;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class GenHtmxAction extends HelpAction {

	/**
	 * ���ɺ�ͬ��ϸ
	 */
	private static final long serialVersionUID = 3533254360820084816L;

	private SaledailyBillForm cardForm = null;
	
	public SaledailyBillForm getCardForm() {
	    return this.cardForm;
	  }
	
	  public void setCardForm(SaledailyBillForm cardForm) {
	    this.cardForm = cardForm;
	  }
	  
	public GenHtmxAction()
	{
		this.setCode("GenHtmxAction");
	    this.setBtnName("���������ϸ");
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
//		UFDate ddd = new UFDate("2018-10-01");
//		
//		String testMsg = getXmonthDate(ddd,8,-1).toString();
//		
//		MessageDialog.showWarningDlg(this.getCardForm(), "", testMsg);
//		
//		if(true) return ;	// ����
		
		/**
		 * �汾��
		 * ���ʱ ����ʹ�ô˹���
		 */
		UFDouble bbh = PuPubVO.getUFDouble_NullAsZero(
				this.getCardForm().getBillCardPanel().getHeadItem("version").getValue()
		);
		if(bbh.compareTo(UFDouble.ONE_DBL)!=0)
		{// ����汾�Ų�Ϊ1�����˳�
			MessageDialog.showErrorDlg(this.getCardForm(), "", "���ʱ����ʹ�ô˹���");
			return;
		}
		
		BillModel model_term = this.getCardForm().getBillCardPanel().getBillModel("pk_ct_sale_term");
		
		BillModel model_b = this.getCardForm().getBillCardPanel().getBillModel("pk_ct_sale_b");
		
		model_b.clearBodyData();		// ��ձ���
		
		/**
		 * ���ݺ�ͬ���� ���ɺ�ͬ��ϸ
		 */
		// ��ͷ �ɷѷ�ʽ vdef7
		// ��ͷ �ɷ����� vdef8
		UIRefPane ref_jffs = (UIRefPane)this.getCardForm().getBillCardPanel().getHeadItem("vdef7").getComponent();
		UIRefPane ref_jfzq = (UIRefPane)this.getCardForm().getBillCardPanel().getHeadItem("vdef8").getComponent();
		
		String  str_jffs = ref_jffs.getRefName();
		Integer int_yjzq = 0;	// Ѻ�����ڣ�0Ϊ��Ѻ��
		Integer int_jfzq = PuPubVO.getInteger_NullAs(ref_jfzq.getRefName(), 0);	// �ɷ�����-��
		
		String pk_org_v = this.getCardForm().getBillCardPanel().getHeadItem("pk_org_v").getValue();
		String pk_org   = this.getCardForm().getBillCardPanel().getHeadItem("pk_org").getValue();
		String pk_group = this.getCardForm().getBillCardPanel().getHeadItem("pk_group").getValue();
		
		String zj_sfxm = this.getCardForm().getBillCardPanel().getHeadItem("vdef17").getValue();	// ���-�շ���Ŀ
		String yj_sfxm = this.getCardForm().getBillCardPanel().getHeadItem("vdef18").getValue();	// Ѻ��-�շ���Ŀ
		
		String pk_country = "0001Z010000000079UJJ";	// ����
		
		{// Ѻ�����ڵĴ���
			int int_ya = str_jffs.indexOf("Ѻ");
			if(int_ya>=0)
			{// ����� Ѻ  ����Ҫ����Ѻ������ (ȡ Ѻ �����һ����)
				String str_yjzq = str_jffs.substring(int_ya+1, int_ya+2);
				int_yjzq = getShuZi(str_yjzq);
			}
		}
		
		/**
		 * nc.vo.ct.saledaily.entity.CtSaleBVO
		 * ��� ��ͬ��ϸ������
		 * ��Ŀ����	crowno
		 * ��Ŀ����	pk_financeorg_v
		 * �շ���Ŀ	vbdef1
		 * ��ʼ����	vbdef3
		 * ��������	vbdef4
		 * �ɷ�����	vbdef8
		 * ����		vbdef5
		 * ���		vbdef6
		 * ���		vbdef7
		 */
		Vector<CtSaleBVO> v_bvo = new Vector();
		
		/**
		 * ѭ�� ��ͬ����ҳǩ������ ��ͬ��ϸ����
		 * ���뿪ʼ����	vhkbdef1
		 * �����������	vhkbdef2
		 * ����		vhkbdef4
		 * ���		vhkbdef5
		 * �Ʒ�����	vhkbdef3
		 * ʵ�ʼƷ�����  vhkbdef8
		 * ��ͬ���	vhkbdef6
		 * ʵ�ʵ���	vhkbdef7
		 */
		for(int row=0;row<model_term.getRowCount();row++)
		{
			// ���뿪ʼ����
			UFDate ksrq = PuPubVO.getUFDate(model_term.getValueAt(row, "vhkbdef1"));
			
			if(ksrq==null) continue;
			
			// �����������
			UFDate jsrq = PuPubVO.getUFDate(model_term.getValueAt(row, "vhkbdef2"));
			// ���
			UFDouble mianji = PuPubVO.getUFDouble_NullAsZero(model_term.getValueAt(row, "vhkbdef5"));
			// ʵ�ʵ���
			UFDouble sjdj = PuPubVO.getUFDouble_NullAsZero(model_term.getValueAt(row, "vhkbdef7"));
			// ��ͬ���
			UFDouble htje = PuPubVO.getUFDouble_NullAsZero(model_term.getValueAt(row, "vhkbdef6"));
			
			// �Ʒ�����
			Integer   jfts = PuPubVO.getInteger_NullAs(model_term.getValueAt(row, "vhkbdef3"),0);
			// ʵ�ʼƷ�����
			Integer sjjfts = PuPubVO.getInteger_NullAs(model_term.getValueAt(row, "vhkbdef8"),0);
			
			// û�м������ �����2��29��
			UFDate LeapYearDate = null;
			
			if(sjjfts<jfts)
			{// ��� ʵ�ʼƷ����� С�� �Ʒ������� ˵�� ������ ��������һ��������
			 // ����Ҫ �ҳ� ʱ�䷶Χ�ڵ� 2��29�ա� Ȼ���� ��ͬ��ϸ ��һ��Ĺ������У�����ʱ�� ��һ�졣
				Integer LeapYear = null;
				     if(ksrq.isLeapYear()) LeapYear = ksrq.getYear();
				else if(jsrq.isLeapYear()) LeapYear = jsrq.getYear();
				     
				if(LeapYear!=null)
				{
					LeapYearDate = new UFDate(""+LeapYear+"-02-29");
				}
			}
			
			/**
			 * HK 2019��4��15��19:05:13
			 * ����  ���� ��������
			 */
			int month = 0;
			if(sjjfts<365)
			{// ������� С�� 365���ͳ���30 ���� ������
				month = (int)PuPubVO.getUFDouble_NullAsZero( sjjfts ).div(30.00).setScale(0,UFDouble.ROUND_HALF_UP).getDouble();
			}
			{// ������� ���� 365�����ȳ���365�� ����м��꣬Ȼ��ʣ������ ��ģ30  ���������ۼӵ���������
				int year_temp  = sjjfts/365;
				int month_temp = (int)PuPubVO.getUFDouble_NullAsZero( sjjfts%365 ).div(30.00).setScale(0,UFDouble.ROUND_HALF_UP).getDouble();
				month = year_temp*12 + month_temp;
			}
			/***END***/
			
			// �µ��� = ��ͬ���/�·� ��8λС����
			UFDouble yuedj = htje.div(month).setScale(8, UFDouble.ROUND_HALF_UP);
			
			// �·� / �ɷ����� = ���� ��ͬ��ϸ������  ���˴���Ϊ�� �ܹ�������
			int htmx_row = month/int_jfzq;
			
			// ��� �ڼ�����
			Object[][] qijian = genQiJian(ksrq,jsrq,int_jfzq,htmx_row,LeapYearDate);
			
			// �����ۼ����
			UFDouble total_zujin = UFDouble.ZERO_DBL;
			
			if(row==0)
			{// ����Ѻ��Ĵ���(ֻ����һ��)
				if(int_yjzq>0)
				{
					CtSaleBVO bvo = new CtSaleBVO();
					bvo.setPk_financeorg_v(pk_org_v);		// ������֯v
					bvo.setPk_org(pk_org);			// ��֯
					bvo.setPk_org_v(pk_org_v);		// ��֯v
					bvo.setPk_group(pk_group);		// ����
					bvo.setVbdef1(yj_sfxm);			// �ɷ�����-Ѻ��
					bvo.setVbdef8(int_yjzq.toString());		// �ɷ�����
					bvo.setFtaxtypeflag(0);					// ��˰���
					
					bvo.setVbdef3(ksrq.toString());			// ��ʼ����
					bvo.setVbdef10(bvo.getVbdef3());		// �տ����� = ��ʼ����
					bvo.setVbdef4(ksrq.toString());			// ��������
					bvo.setVbdef6(mianji.toString());			// ���
					bvo.setVbdef5(sjdj.toString());				// ����
					
					UFDouble zujin = yuedj.multiply(int_yjzq).setScale(2, UFDouble.ROUND_HALF_UP);
					
					bvo.setVbdef7(zujin.toString());			// ���
					bvo.setNorigtaxmny(zujin);					// ��˰�ϼ�
					bvo.setNnum(mianji);						// ����
					
					bvo.setCrececountryid(pk_country);
					bvo.setCsendcountryid(pk_country);
					bvo.setCtaxcountryid(pk_country);
					bvo.setFbuysellflag(1);
					bvo.setVqtunitrate("1.00/1.00");
					bvo.setVchangerate("1.00/1.00");
					bvo.setPk_financeorg(pk_org);
					bvo.setNtaxmny(zujin);
					bvo.setNorigmny(zujin);
					bvo.setNmny(zujin);
					bvo.setNcaltaxmny(zujin);
					
					v_bvo.add(bvo);
				}
			}
			
			for(int i=0;i<htmx_row;i++)
			{
				CtSaleBVO bvo = new CtSaleBVO();
				bvo.setPk_financeorg_v(pk_org_v);		// ������֯v
				bvo.setPk_org(pk_org);			// ��֯
				bvo.setPk_org_v(pk_org_v);		// ��֯v
				bvo.setPk_group(pk_group);		// ����
				bvo.setVbdef1(zj_sfxm);			// �ɷ�����-���
				bvo.setVbdef8(int_jfzq.toString());		// �ɷ�����
				bvo.setFtaxtypeflag(0);					// ��˰���
				
				bvo.setVbdef3(qijian[i][0].toString());		// ��ʼ����
				bvo.setVbdef10(bvo.getVbdef3());			// �տ����� = ��ʼ����
				bvo.setVbdef4(qijian[i][1].toString());		// ��������
				bvo.setVbdef6(mianji.toString());			// ���
				bvo.setVbdef5(sjdj.toString());				// ����
				
				UFDouble zujin = UFDouble.ZERO_DBL;
				if(i==htmx_row-1)
				{// ���һ�� �� ˦��
					zujin = htje.sub(total_zujin);
				}
				else
				{
					zujin = yuedj.multiply(int_jfzq).setScale(2, UFDouble.ROUND_HALF_UP);
					total_zujin = total_zujin.add(zujin);
				}
				
				bvo.setVbdef7(zujin.toString());			// ���
				bvo.setNorigtaxmny(zujin);					// ��˰�ϼ�
				bvo.setNnum(mianji);						// ����
				
				bvo.setCrececountryid(pk_country);
				bvo.setCsendcountryid(pk_country);
				bvo.setCtaxcountryid(pk_country);
				bvo.setFbuysellflag(1);
				bvo.setVqtunitrate("1.00/1.00");
				bvo.setVchangerate("1.00/1.00");
				bvo.setPk_financeorg(pk_org);
				bvo.setNtaxmny(zujin);
				bvo.setNorigmny(zujin);
				bvo.setNmny(zujin);
				bvo.setNcaltaxmny(zujin);
				
				v_bvo.add(bvo);
			}
		}
		
		CtSaleBVO[] bvos = new CtSaleBVO[v_bvo.size()];
		bvos = v_bvo.toArray(bvos);
		
		/**
		 * ��� ���ɵĺ�ͬ��Ϣ �������ݴ���
		 */
		for(int i=0;i<bvos.length;i++)
		{
			bvos[i].setCrowno(""+(i+1)*10);
		}
		
		model_b.setBodyDataVO(bvos);	// ��ӱ�������
		
		model_b.loadLoadRelationItemValue();	// �� ��ֵ��PK  ����ɵ���
		
	}
	
	/**
	 * ���������� �ַ��� ת�������֣��п����� ��д���� Ҳ�п����� ����������
	 */
	public static Integer getShuZi(String str_shuzi)
	{
		Integer result = 0;
		
		     if("һ".equals(str_shuzi)) {result = 1;}
		else if("��".equals(str_shuzi)) {result = 2;}
		else if("��".equals(str_shuzi)) {result = 3;}
		else if("��".equals(str_shuzi)) {result = 4;}
		else if("��".equals(str_shuzi)) {result = 5;}
		else if("��".equals(str_shuzi)) {result = 6;}
		else if("��".equals(str_shuzi)) {result = 6;}
		else if("��".equals(str_shuzi)) {result = 6;}
		else if("��".equals(str_shuzi)) {result = 6;}
		else if("ʮ".equals(str_shuzi)) {result = 6;}
		else if("ʮһ".equals(str_shuzi)) {result = 6;}
		else if("ʮ��".equals(str_shuzi)) {result = 6;}
		else
		{
			try
			{
				result = PuPubVO.getInteger_NullAs(str_shuzi,0);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 * ���ݿ�ʼ���ڡ��������ڡ� �ɷ����ڡ�����   ���� ÿһ�ڵ� ��ʼ�������ڣ����� �� �����棩
	 * �� �������ڵ� �������� ��ǰһ��
	 */
	public static UFDate[][] genQiJian(UFDate ksrq,UFDate jsrq,int jfzq,int row,UFDate LeapYearDate){
		
		UFDate[][] result = new UFDate[row][];
		
		UFDate execRQ = ksrq;
		
		for(int i=0;i<row;i++)
		{
			result[i] = new UFDate[2];	// ��ʼ���ڡ���������
			
			result[i][0] = execRQ;	// ��ʼ����
			UFDate endRQ = getXmonthDate(execRQ, jfzq, -1);
			result[i][1] = endRQ;	// ��������
			
			execRQ = endRQ.getDateAfter(1);
			
			if( LeapYearDate!=null
			&& ( LeapYearDate.compareTo(result[i][0])>=0 && LeapYearDate.compareTo(result[i][1])<=0 )
			)
			{// ��� �������� ��Ϊ�գ� ���� �� ��ʼ���� �� �������� ��Χ�ڣ� �� �������� �ƺ�һ��
				result[i][1] = result[i][1].getDateAfter(-1);
			}
			
		}
		
		return result;
	}
	
	/**
	 * ��������  ����  X����֮������ڣ��Լ� ��������
	 */
	public static UFDate getXmonthDate(UFDate date,int Xmonth,int Xday){
		
		int year  = date.getYear();		// ��
		int month = date.getMonth();	// ��
		int day   = date.getDay();		// ��
		int dayM  = date.getDaysMonth();	// ���� ������
		
		// ��ʼȡ������ �·� ��Ĭ���Ǵӱ���ȡ ������
		int s_month = month;
		int s_year  = year;
		if(day==dayM)
		{// ����� ���һ�죬 �ʹ��¸��� ��ʼȡ ����
			s_month++;
		}
		
		// Ҫ�ӵ�������
		int addDays = Xday;
		
		for(int i=0;i<Xmonth;i++)
		{
			if(s_month>12)
			{
				s_year++;
				s_month=1;
			}
			
			UFDate s_date = new UFDate(""+s_year+"-"+(s_month<10?"0"+s_month:""+s_month)+"-01");
			
			addDays += s_date.getDaysMonth();
			
			s_month++;
			
//			System.out.println("=="+s_date+"=="+s_date.getDaysMonth()+"=="+addDays+"==");
		}
		
		return date.getDateAfter(addDays);
	}
	
	@Override
	protected boolean isActionEnable() {
		return true;
	}
	
}
