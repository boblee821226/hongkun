package nc.ui.hkjt.hg_rsbaogao_tz.ace.view;

public class ShowUpableBatchBillTable extends
nc.ui.pubapp.uif2app.view.ShowUpableBatchBillTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9183710660877424931L;

	@Override
	public void initUI() {
		// TODO �Զ����ɵķ������
		super.initUI();
		new BillColumnHandler(this).getTableColumnMerge(false);
	}
}
