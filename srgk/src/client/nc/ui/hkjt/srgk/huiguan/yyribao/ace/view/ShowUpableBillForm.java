package nc.ui.hkjt.srgk.huiguan.yyribao.ace.view;

public class ShowUpableBillForm extends
		nc.ui.pubapp.uif2app.view.ShowUpableBillForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9183710660877424931L;

	@Override
	public void initUI() {
		// TODO �Զ����ɵķ������
		super.initUI();
		super.getBillCardPanel().getBodyPanel().setTotalRowShow(false);
//		new BillColumnHandler(this).getTableColumnMerge(false);
	}
}
