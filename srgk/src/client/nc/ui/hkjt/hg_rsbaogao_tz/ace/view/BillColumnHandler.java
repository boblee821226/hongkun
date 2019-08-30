package nc.ui.hkjt.hg_rsbaogao_tz.ace.view;

import javax.swing.table.TableColumnModel;

import nc.ui.pub.beans.table.ColumnGroup;
import nc.ui.pub.beans.table.GroupableTableHeader;

public class BillColumnHandler {

	ShowUpableBatchBillTable billform;


	public BillColumnHandler(ShowUpableBatchBillTable billform_new) {
		billform = billform_new;
	}

	/**
	 * 表体字段合并处理
	 * */
	public void getTableColumnMerge(boolean listListview) {
			// 卡片界面合并
			TableColumnModel columnModellist = billform.getBillCardPanel()
					.getBodyPanel().getTable().getColumnModel();
			GroupableTableHeader listHeader = (GroupableTableHeader) billform
					.getBillCardPanel().getBodyPanel().getTable()
					.getTableHeader();
			setBodyGroup(columnModellist, listHeader,false);
			billform.getBillCardPanel().getBodyPanel().getTable()
					.setTableHeader(listHeader);

	}

	private int getBodyTableColumnShowOrder_Card(String column) {
		int showorder = billform.getBillCardPanel().getBodyColByKey(column);
		return showorder;
	}


	private void setBodyGroup(TableColumnModel columnModellist,
			GroupableTableHeader listHeader, boolean islistview) {

		
		
		ColumnGroup colG1 = new ColumnGroup("调整");
		ColumnGroup colGA = new ColumnGroup("A");
		ColumnGroup colGB = new ColumnGroup("B");
		colGA.add(columnModellist
				.getColumn(getBodyTableColumnShowOrder_Card("tz_km_jzfs_1")));
		colGA.add(columnModellist
				.getColumn(getBodyTableColumnShowOrder_Card("tz_km_srxm_1")));
		colGA.add(columnModellist
				.getColumn(getBodyTableColumnShowOrder_Card("tz_km_data_1")));
		colGA.add(columnModellist
				.getColumn(getBodyTableColumnShowOrder_Card("tz_km_srxm_type1")));
		colGB.add(columnModellist
				.getColumn(getBodyTableColumnShowOrder_Card("tz_km_jzfs_2")));
		colGB.add(columnModellist
				.getColumn(getBodyTableColumnShowOrder_Card("tz_km_srxm_2")));
		colGB.add(columnModellist
				.getColumn(getBodyTableColumnShowOrder_Card("tz_km_data_2")));
		colGB.add(columnModellist
				.getColumn(getBodyTableColumnShowOrder_Card("tz_km_srxm_type2")));
		colG1.add(colGA);
		colG1.add(colGB);
		listHeader.addColumnGroup(colG1);
	}
}
