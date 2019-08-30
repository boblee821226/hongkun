package nc.ui.ct.editor.after.body;

import hd.vo.pub.tools.PuPubVO;
import nc.ui.ct.editor.listener.IBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.lang.UFDouble;

/**
 * ��׼����
 */
public class TermVhkbdef4 implements IBodyAfterEditEventListener {

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
		// ��׼����
		UFDouble danjia = 
			PuPubVO.getUFDouble_NullAsZero(
				event.getValue()
			);
		// ʵ������
		UFDouble sjts = 
			PuPubVO.getUFDouble_NullAsZero(
				model_term.getValueAt(row, "vhkbdef8")	
			);
		// ��׼����
		UFDouble bzts = 
				PuPubVO.getUFDouble_NullAsZero(
						model_term.getValueAt(row, "vhkbdef3")	
						);
		// ��ͬ��� = ����*���*ʵ������
		UFDouble sjhtje = danjia.multiply(mianji).multiply(sjts).setScale(2, UFDouble.ROUND_HALF_UP);
		UFDouble bzhtje = danjia.multiply(mianji).multiply(bzts).setScale(2, UFDouble.ROUND_HALF_UP);
		
		model_term.setValueAt(sjhtje   , row , "vhkbdef6");	// ʵ�ʺ�ͬ���
		model_term.setValueAt(danjia   , row , "vhkbdef7");	// ʵ�ʵ���
		model_term.setValueAt(bzhtje   , row , "vhkbdef9");	// ��׼��ͬ���
	}

}
