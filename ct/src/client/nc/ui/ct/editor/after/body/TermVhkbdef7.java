package nc.ui.ct.editor.after.body;

import hd.vo.pub.tools.PuPubVO;
import nc.ui.ct.editor.listener.IBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.lang.UFDouble;
/**
 * 实际单价
 */
public class TermVhkbdef7 implements IBodyAfterEditEventListener {

	@Override
	public void afterEdit(CardBodyAfterEditEvent event) {
		
		BillCardPanel panel = event.getBillCardPanel();
		BillModel model_term = panel.getBillModel("pk_ct_sale_term");
		
		int row = event.getRow();
		
		// 面积
		UFDouble mianji = 
			PuPubVO.getUFDouble_NullAsZero(
				model_term.getValueAt(row, "vhkbdef5")	
			);
		// 实际单价
		UFDouble danjia = 
			PuPubVO.getUFDouble_NullAsZero(
				event.getValue()
			);
		// 实际天数
		UFDouble sjts = 
			PuPubVO.getUFDouble_NullAsZero(
				model_term.getValueAt(row, "vhkbdef8")	
			);
		// 合同金额 = 单价*面积*实际天数
		UFDouble htje = danjia.multiply(mianji).multiply(sjts).setScale(2, UFDouble.ROUND_HALF_UP);
		
		model_term.setValueAt(htje   , row , "vhkbdef6");	// 实际合同金额
	}

}
