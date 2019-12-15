package nc.ui.hkjt.srgk.huiguan.sgshuju.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.CopyAction;
import nc.vo.pub.lang.UFDouble;

public class DefCopyAction extends CopyAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7337602845125593877L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);
		// 将界面和缓冲数据的金额数据清空
		handlerDatas();
	}

	private void handlerDatas() {

		getEditor().getBillCardPanel().setHeadItem("pk_hk_srgk_hg_sgshuju",
				null);
		getEditor().getBillCardPanel().setHeadItem("ibillstatus", -1);
		getEditor().getBillCardPanel().setHeadItem("vbillcode", null);
		getEditor().getBillCardPanel().setTailItem("approver", null);
		getEditor().getBillCardPanel().setTailItem("taudittime", null);
		getEditor().getBillCardPanel().setTailItem("modifiedtime", null);
		getEditor().getBillCardPanel().setTailItem("modifier", null);
		getEditor().getBillCardPanel().setTailItem("xgr", null);
		getEditor().getBillCardPanel().setTailItem("zdr", null);
		getEditor().getBillCardPanel().setTailItem("creator", null);
		getEditor().getBillCardPanel().setTailItem("creationtime", null);
		getEditor().getBillCardPanel().setHeadItem("ts", null);

		// 将界面的数据金额赋值为0
		int count = getEditor().getBillCardPanel().getRowCount();
		String[] jinecolumns = new String[] { "tz_km_data_1", "tz_km_data_2", };
		String[] pkcolumn = new String[] { "pk_hk_srgk_hg_sgshuju",
				"pk_hk_srgk_hg_sgshuju_b", "ts" };
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < jinecolumns.length; j++) {
				String column = jinecolumns[j];
				getEditor().getBillCardPanel().setBodyValueAt(
						UFDouble.ZERO_DBL, i, column);
			}
			for (int j = 0; j < pkcolumn.length; j++) {
				String column = pkcolumn[j];
				getEditor().getBillCardPanel().setBodyValueAt(null, i, column);
			}
		}
	}
}
