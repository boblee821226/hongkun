package nc.ui.ct.editor.after.body;

import hd.vo.pub.tools.PuPubVO;
import nc.ui.ct.editor.listener.IBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.lang.UFDouble;
/**
 * ʵ������
 */
public class TermVhkbdef8 implements IBodyAfterEditEventListener {

	@Override
	public void afterEdit(CardBodyAfterEditEvent event) {
		
		BillCardPanel panel = event.getBillCardPanel();
		BillModel model_term = panel.getBillModel("pk_ct_sale_term");
		
		int row = event.getRow();
		
		// ���
		UFDouble mianji = 
			PuPubVO.getUFDouble_NullAsZero(
				model_term.getValueAt(row, "vhkbdef5")	
			);
		// ʵ�ʵ���
		UFDouble danjia = 
			PuPubVO.getUFDouble_NullAsZero(
				model_term.getValueAt(row, "vhkbdef7")	
			);
		// ʵ������
		UFDouble sjts = 
			PuPubVO.getUFDouble_NullAsZero(
				event.getValue()
			);
		// ��ͬ��� = ����*���*ʵ������
		UFDouble htje = danjia.multiply(mianji).multiply(sjts).setScale(2, UFDouble.ROUND_HALF_UP);
		
		model_term.setValueAt(htje   , row , "vhkbdef6");	// ʵ�ʺ�ͬ���
	}

}
