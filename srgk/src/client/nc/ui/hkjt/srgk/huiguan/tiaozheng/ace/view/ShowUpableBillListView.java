package nc.ui.hkjt.srgk.huiguan.tiaozheng.ace.view;


public class ShowUpableBillListView extends
		nc.ui.pubapp.uif2app.view.ShowUpableBillListView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3158047806266649686L;

	@Override
	public void initUI() {
		super.initUI();
		//表体列头合并单元格
		new BillColumnHandler(this).getTableColumnMerge(true);
	}
}
