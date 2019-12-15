package nc.ui.ct.editor.after.body;

import hd.vo.pub.tools.PuPubVO;
import nc.ui.ct.editor.listener.IBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.lang.UFDouble;
/**
 * ʵ�ʺ�ͬ���
 */
public class TermVhkbdef6 implements IBodyAfterEditEventListener {

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
		// ��ͬ���
		UFDouble htje = 
			PuPubVO.getUFDouble_NullAsZero(
				event.getValue()
			);
		// ʵ������
		UFDouble sjts = 
			PuPubVO.getUFDouble_NullAsZero(
				model_term.getValueAt(row, "vhkbdef8")	
			);
		// ʵ�ʵ��� = ��ͬ���/���/ʵ������
		UFDouble sjdj = htje.div(mianji).div(sjts).setScale(8, UFDouble.ROUND_HALF_UP);
		
		model_term.setValueAt(sjdj , row , "vhkbdef7");
	}

}
