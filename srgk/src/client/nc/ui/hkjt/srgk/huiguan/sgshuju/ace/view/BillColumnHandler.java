package nc.ui.hkjt.srgk.huiguan.sgshuju.ace.view;

import javax.swing.table.TableColumnModel;

import nc.ui.pub.beans.table.ColumnGroup;
import nc.ui.pub.beans.table.GroupableTableHeader;

public class BillColumnHandler {

	ShowUpableBillListView billistview;
	ShowUpableBillForm billform;

	public BillColumnHandler(ShowUpableBillListView billistview_new) {
		billistview = billistview_new;
	}

	public BillColumnHandler(ShowUpableBillForm billform_new) {
		billform = billform_new;
	}

	/**
	 * �����ֶκϲ�����
	 * */
	public void getTableColumnMerge(boolean listListview) {
		// �б����ϲ�
		if (listListview) {

			TableColumnModel columnModellist = billistview.getBillListPanel()
					.getBodyTable().getColumnModel();
			GroupableTableHeader listHeader = (GroupableTableHeader) billistview
					.getBillListPanel().getBodyTable().getTableHeader();
			setBodyGroup(columnModellist, listHeader,true);
			billistview.getBillListPanel().getBodyTable()
					.setTableHeader(listHeader);

		} else {
			// ��Ƭ����ϲ�
			TableColumnModel columnModellist = billform.getBillCardPanel()
					.getBodyPanel().getTable().getColumnModel();
			GroupableTableHeader listHeader = (GroupableTableHeader) billform
					.getBillCardPanel().getBodyPanel().getTable()
					.getTableHeader();
			setBodyGroup(columnModellist, listHeader,false);
			billform.getBillCardPanel().getBodyPanel().getTable()
					.setTableHeader(listHeader);

		}
	}

	private int getBodyTableColumnShowOrder_Card(String column) {
		int showorder = billform.getBillCardPanel().getBodyColByKey(column);
		return showorder;
	}

	private int getBodyTableColumnShowOrder_List(String column) {
		int showorder = billistview.getBillListPanel().getBodyBillModel().getBodyColByKey(column);
		return showorder;
	}

	private void setBodyGroup(TableColumnModel columnModellist,
			GroupableTableHeader listHeader, boolean islistview) {

		ColumnGroup colG1 = new ColumnGroup("����");
		ColumnGroup colGA = new ColumnGroup("A");
		ColumnGroup colGB = new ColumnGroup("B");
		colGA.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("tz_km_jzfs_1")
						: getBodyTableColumnShowOrder_Card("tz_km_jzfs_1")));
		colGA.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("tz_km_srxm_1")
						: getBodyTableColumnShowOrder_Card("tz_km_srxm_1")));
		colGA.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("tz_km_srxm_type1")
						: getBodyTableColumnShowOrder_Card("tz_km_srxm_type1")));
		colGA.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("tz_km_info_1")
						: getBodyTableColumnShowOrder_Card("tz_km_info_1")));
		colGA.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("tz_km_data_1")
						: getBodyTableColumnShowOrder_Card("tz_km_data_1")));
		colGB.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("tz_km_jzfs_2")
						: getBodyTableColumnShowOrder_Card("tz_km_jzfs_2")));
		
		colGB.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("tz_km_srxm_2")
						: getBodyTableColumnShowOrder_Card("tz_km_srxm_2")));
		colGB.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("tz_km_srxm_type2")
						: getBodyTableColumnShowOrder_Card("tz_km_srxm_type2")));
		colGB.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("tz_km_info_2")
						: getBodyTableColumnShowOrder_Card("tz_km_info_2")));
		colGB.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("tz_km_data_2")
						: getBodyTableColumnShowOrder_Card("tz_km_data_2")));
		colG1.add(colGA);
		colG1.add(colGB);
		listHeader.addColumnGroup(colG1);
	}
}
