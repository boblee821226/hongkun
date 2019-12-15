package nc.ui.ct.saledaily.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JOptionPane;

import nc.ui.ct.action.HelpAction;
import nc.ui.ct.saledaily.view.SaledailyBillForm;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillModel;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.ct.saledaily.entity.CtSaleTermVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class GenHttkAction extends HelpAction {

	/**
	 * ���ɺ�ͬ����
	 */
	private static final long serialVersionUID = -8527791943376209507L;

	private SaledailyBillForm cardForm = null;
	
	public SaledailyBillForm getCardForm() {
	    return this.cardForm;
	  }
	
	  public void setCardForm(SaledailyBillForm cardForm) {
	    this.cardForm = cardForm;
	  }
	  
	public GenHttkAction()
	{
		this.setCode("GenHtmxAction");
	    this.setBtnName("���ɼ������");
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
//		MessageDialog.showWarningDlg(this.getCardForm(), "", "���ɺ�ͬ����");
		
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
		
		String str_pre_month = JOptionPane.showInputDialog(this.getCardForm(), "������ÿ������������",12);
		
		int pre_month = PuPubVO.getInteger_NullAs(str_pre_month, 12);	// �������  ������  ����һ�� ��ͬ���� ��Ĭ��Ϊ12���£�
		
		BillModel model_term = this.getCardForm().getBillCardPanel().getBillModel("pk_ct_sale_term");
		
		model_term.clearBodyData();		// ��ձ���
	
		/****************** ��ͬ��ͷ�ֶ�
		 * ��ͬ��Ч����	valdate
		 * ��ͬ��������	invallidate
		 * ����		vdef4
		 * ���		vdef5
		 * �ɷѷ�ʽ	vdef7
		 * �շ�����	vdef8
		 */
		UIRefPane ref_jffs = (UIRefPane)this.getCardForm().getBillCardPanel().getHeadItem("vdef7").getComponent();
		UFDate htksrq = PuPubVO.getUFDate( this.getCardForm().getBillCardPanel().getHeadItem("valdate").getValue() );
		UFDate htjsrq = PuPubVO.getUFDate( this.getCardForm().getBillCardPanel().getHeadItem("invallidate").getValue() );
		UFDouble danjia = PuPubVO.getUFDouble_NullAsZero( this.getCardForm().getBillCardPanel().getHeadItem("vdef4").getValue() );
		UFDouble mianji = PuPubVO.getUFDouble_NullAsZero( this.getCardForm().getBillCardPanel().getHeadItem("vdef5").getValue() );
	
		String  str_jffs = ref_jffs.getRefName();
		Integer int_jfzq = 0;	// �ɷ�����
		
		String pk_org_v = this.getCardForm().getBillCardPanel().getHeadItem("pk_org_v").getValue();
		String pk_org   = this.getCardForm().getBillCardPanel().getHeadItem("pk_org").getValue();
		String pk_group = this.getCardForm().getBillCardPanel().getHeadItem("pk_group").getValue();
		
		{// �ɷ����ڵĴ���
			int int_fu = str_jffs.indexOf("��");
			if(int_fu>=0)
			{// ����� ��  ����Ҫ����ɷ����� (ȡ �� ����� �����) (ѺX��XX)
				String str_yjzq = str_jffs.substring(int_fu+1, str_jffs.length());
				int_jfzq = GenHtmxAction.getShuZi(str_yjzq);
			}
		}
		
		/*******************��ͬ����ҳǩ
		 * ���뿪ʼ����	vhkbdef1
		 * �����������	vhkbdef2
		 * ��׼����		vhkbdef4
		 * ���			vhkbdef5
		 * ��׼�Ʒ�����	vhkbdef3
		 * ʵ�ʼƷ�����  	vhkbdef8
		 * ʵ�ʺ�ͬ���	vhkbdef6
		 * ʵ�ʵ���		vhkbdef7
		 * ��׼��ͬ���	vhkbdef9
		 */
		
		// ����  �ӿ�ʼ���� �� �������� ֮ǰ���£�����������/30.4  ȡ������
		int month = (int)PuPubVO.getUFDouble_NullAsZero( htjsrq.getDaysAfter(htksrq) ).div(30.40).setScale(0,UFDouble.ROUND_HALF_UP).getDouble();
		
		// ������Ҫ���� ��ͬ����� ����
		int httk_row = month/pre_month + (month%pre_month!=0?1:0);
		
		Vector<CtSaleTermVO> v_tvo = new Vector();
		
		// ��� �ڼ�����
		Object[][] qijian = genQiJian(htksrq,htjsrq,pre_month,month,httk_row);
		
//		System.out.println("==");
		
		/**
		 * ѭ������ ��ͬ��������
		 */
		for(int i=0;i<httk_row;i++)
		{
			Object[] obj_qijian = qijian[i];
			
			CtSaleTermVO tvo = new CtSaleTermVO();
			
			// ��ͬ��� = ���*����*�Ʒ�����
			UFDouble htje = mianji.multiply(danjia).multiply(PuPubVO.getUFDouble_NullAsZero(obj_qijian[2])).setScale(2, UFDouble.ROUND_HALF_UP);
			
			tvo.setVhkbdef1(obj_qijian[0].toString());	// ���뿪ʼ����
			tvo.setVhkbdef2(obj_qijian[1].toString());	// �����������
			tvo.setVhkbdef3(obj_qijian[2].toString());	// ʵ�ʼƷ�����
			tvo.setVhkbdef4(danjia.toString());			// ʵ�ʵ���
			tvo.setVhkbdef5(mianji.toString());			// ���
			tvo.setVhkbdef6(htje.toString());			// ʵ�ʺ�ͬ���
			tvo.setVhkbdef7(danjia.toString());			// ʵ�ʵ���
			tvo.setVhkbdef8(obj_qijian[2].toString());	// ʵ�ʼƷ�����
			tvo.setVhkbdef9(htje.toString());			// ��׼��ͬ���
			
			tvo.setPk_group(pk_group);	// ����
			tvo.setPk_org(pk_org);		// ��֯
			tvo.setPk_org_v(pk_org_v);	// ��֯v
			
			tvo.setVmemo(""+(i+1));		// ��ע
			
			v_tvo.add(tvo);
		}
		
		CtSaleTermVO[] tvos = new CtSaleTermVO[v_tvo.size()];
		tvos = v_tvo.toArray(tvos);
		
//		/**
//		 * ��� ���ɵĺ�ͬ���� �������ݴ���
//		 */
//		for(int i=0;i<tvos.length;i++)
//		{
//			tvos[i]..setCrowno(""+(i+1)*10);
//		}
		
		model_term.setBodyDataVO(tvos);	// ��ӱ�������
		
		model_term.loadLoadRelationItemValue();		// �� ��ֵ��PK  ����ɵ���
	}
	
	/**
	 * ���ݺ�ͬ��ʼ���ڡ���ͬ�������ڡ� ÿ��������������������   ���� ÿһ�ڵ� ��ʼ�������ڡ����������� �� �����棩
	 */
	public Object[][] genQiJian(UFDate ksrq,UFDate jsrq,int pre_month,int month,int row){
		
		Object[][] result = new Object[row][];
		
		UFDate execRQ = ksrq;
		
		for(int i=0;i<row;i++)
		{
			int exec_month = pre_month;
			
			if(i==row-1)
			{// ��������һ��  exec_month = ������-֮ǰ���е�����
				
				exec_month = month - ( pre_month*(i) );
			}
			
			result[i] = new Object[3];	// ��ʼ���ڡ��������ڡ�����
			
			result[i][0] = execRQ;
			UFDate endRQ = GenHtmxAction.getXmonthDate(execRQ, exec_month, -1);
			result[i][1] = endRQ;
			result[i][2] = endRQ.getDaysAfter(execRQ)+1;
			execRQ = endRQ.getDateAfter(1);
			
		}
		
		return result;
	}
	
	@Override
	protected boolean isActionEnable() {
		return true;
	}
	
}
