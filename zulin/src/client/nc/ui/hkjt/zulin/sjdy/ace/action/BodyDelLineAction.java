package nc.ui.hkjt.zulin.sjdy.ace.action;

import hd.vo.pub.tools.PuPubVO;
import nc.vo.pub.lang.UFDouble;

public class BodyDelLineAction extends
		nc.ui.pubapp.uif2app.actions.BodyDelLineAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7346328761197832169L;

	@Override
	public void doAction() {
		
		super.doAction();
		
		/**
		 * 表头金额字段重新赋值
		 */
		UFDouble total_je = UFDouble.ZERO_DBL;
		
		int rowCount = getCardPanel().getBillModel().getRowCount();
		
		for(int i=0;i<rowCount;i++) {
			UFDouble skje = PuPubVO.getUFDouble_NullAsZero(
					getCardPanel().getBillModel().getValueAt(i, "skje"));	// 收款金额
			total_je = total_je.add(skje);
		}
		
		getCardPanel().getHeadItem("vdef03").setValue(total_je);	// 表头-合计金额
		/***END***/
		
	}
	
}
