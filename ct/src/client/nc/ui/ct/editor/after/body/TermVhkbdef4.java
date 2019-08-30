package nc.ui.ct.editor.after.body;

import hd.vo.pub.tools.PuPubVO;
import nc.ui.ct.editor.listener.IBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.lang.UFDouble;

/**
 * 标准单价
 */
public class TermVhkbdef4 implements IBodyAfterEditEventListener {

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
		// 标准单价
		UFDouble danjia = 
			PuPubVO.getUFDouble_NullAsZero(
				event.getValue()
			);
		// 实际天数
		UFDouble sjts = 
			PuPubVO.getUFDouble_NullAsZero(
				model_term.getValueAt(row, "vhkbdef8")	
			);
		// 标准天数
		UFDouble bzts = 
				PuPubVO.getUFDouble_NullAsZero(
						model_term.getValueAt(row, "vhkbdef3")	
						);
		// 合同金额 = 单价*面积*实际天数
		UFDouble sjhtje = danjia.multiply(mianji).multiply(sjts).setScale(2, UFDouble.ROUND_HALF_UP);
		UFDouble bzhtje = danjia.multiply(mianji).multiply(bzts).setScale(2, UFDouble.ROUND_HALF_UP);
		
		model_term.setValueAt(sjhtje   , row , "vhkbdef6");	// 实际合同金额
		model_term.setValueAt(danjia   , row , "vhkbdef7");	// 实际单价
		model_term.setValueAt(bzhtje   , row , "vhkbdef9");	// 标准合同金额
	}

}
