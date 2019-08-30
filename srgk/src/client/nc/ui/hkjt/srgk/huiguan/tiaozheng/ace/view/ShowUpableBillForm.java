package nc.ui.hkjt.srgk.huiguan.tiaozheng.ace.view;

public class ShowUpableBillForm extends
		nc.ui.pubapp.uif2app.view.ShowUpableBillForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9183710660877424931L;

	@Override
	public void initUI() {
		// TODO 自动生成的方法存根
		super.initUI();
		new BillColumnHandler(this).getTableColumnMerge(false);
	}
}
