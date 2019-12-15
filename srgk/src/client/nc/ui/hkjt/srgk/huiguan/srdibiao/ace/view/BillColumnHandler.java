package nc.ui.hkjt.srgk.huiguan.srdibiao.ace.view;

import javax.swing.table.TableColumnModel;

import nc.ui.pub.beans.table.ColumnGroup;
import nc.ui.pub.beans.table.GroupableTableHeader;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;

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
	 * 表体字段合并处理
	 * */
	public void getTableColumnMerge(boolean listListview) {
		// 列表界面合并
		if (listListview) {

			TableColumnModel columnModellist = billistview.getBillListPanel()
					.getBodyTable().getColumnModel();
			GroupableTableHeader listHeader = (GroupableTableHeader) billistview
					.getBillListPanel().getBodyTable().getTableHeader();
			setBodyGroup(columnModellist, listHeader, true);
			billistview.getBillListPanel().getBodyTable()
					.setTableHeader(listHeader);

		} else {
			// 卡片界面合并
			TableColumnModel columnModellist = billform.getBillCardPanel()
					.getBodyPanel().getTable().getColumnModel();
			GroupableTableHeader listHeader = (GroupableTableHeader) billform
					.getBillCardPanel().getBodyPanel().getTable()
					.getTableHeader();
			setBodyGroup(columnModellist, listHeader, false);
			billform.getBillCardPanel().getBodyPanel().getTable()
					.setTableHeader(listHeader);

		}
	}

	private int getBodyTableColumnShowOrder_Card(String column) {
		int showorder = billform.getBillCardPanel().getBodyColByKey(column);
		return showorder;
	}

	private int getBodyTableColumnShowOrder_List(String column) {
		int showorder = billistview.getBillListPanel().getBodyBillModel()
				.getBodyColByKey(column);
		return showorder;
	}

	private void setBodyGroup(TableColumnModel columnModellist,
			GroupableTableHeader listHeader, boolean islistview) {

		ColumnGroup colG1 = new ColumnGroup("结账方式及往来挂账项目");
		colG1.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("jzfs_name")
						: getBodyTableColumnShowOrder_Card("jzfs_name")));
		colG1.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("jine")
						: getBodyTableColumnShowOrder_Card("jine")));

		ColumnGroup colG2 = new ColumnGroup("总收入");
		colG2.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("yingshou")
						: getBodyTableColumnShowOrder_Card("yingshou")));
		colG2.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("youhui_zidong")
						: getBodyTableColumnShowOrder_Card("youhui_zidong")));
		colG2.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("youhui_shougong")
						: getBodyTableColumnShowOrder_Card("youhui_shougong")));
		colG2.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("youhui_kabili")
						: getBodyTableColumnShowOrder_Card("youhui_kabili")));
		colG2.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("shouru")
						: getBodyTableColumnShowOrder_Card("shouru")));

		ColumnGroup colG3 = new ColumnGroup("收入项目");
		colG3.add(columnModellist
				.getColumn(islistview ? getBodyTableColumnShowOrder_List("srmx_name")
						: getBodyTableColumnShowOrder_Card("srmx_name")));
		colG3.add(colG2);

		listHeader.addColumnGroup(colG1);
		listHeader.addColumnGroup(colG3);
	}

	// 处理表体字段中的部门信息
	public  static void handlerBodyShouRuBmFalse(nc.ui.pubapp.uif2app.view.ShowUpableBillForm billform) {
		BillCardPanel billpanel = billform.getBillCardPanel();
		// 先定义要设置多少部门字段
		int bm = 50;
		String column = "";
		for (int i = 1; i <= bm; i++) {
			if (i < 10) {
				column = String.valueOf("0" + i);
			} else {
				column = String.valueOf(i);
			}
			BillItem billitem = billpanel.getBodyItem("shouru_bm" + column);
			if (billitem != null) {
				billitem.setShow(false);
			}
		}
	}
	// 处理表体字段中的部门信息
	public  static void handlerBodyShouRuBmTrue(BillCardPanel billpanel, String vdefbm,String bmname) {
			BillItem billitem = billpanel.getBodyItem(vdefbm);
			if (billitem != null) {
				billitem.setName(bmname);
				billitem.setShow(true);
			}
		}
}
