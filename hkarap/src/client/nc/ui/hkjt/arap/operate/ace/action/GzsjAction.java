package nc.ui.hkjt.arap.operate.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IHk_arap_operateMaintain;
import nc.ui.pub.beans.UITable;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.uif2.LoginContext;

public class GzsjAction extends NCAction {
	
	private static final long serialVersionUID = 3845662171905193692L;
	
	private BillListView listView = null;
	private AbstractAppModel model = null;
	private BillForm editor = null;
	private String billType = null;
	private LoginContext context = null;
	
	public GzsjAction() {
		setBtnName("挂账审计");
		setCode("gzsjAction");
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		BillModel model = this.getListView().getBillListPanel().getBodyBillModel();
		int rowCount = model.getRowCount();
		List<Integer> selectRows = new ArrayList<>();
		ArrayList<String> selectPks = new ArrayList<>();
		for (int i=0; i<rowCount; i++) {
			if (model.getRowState(i) == BillModel.SELECTED) {
				selectRows.add(i);
				String pk = PuPubVO.getString_TrimZeroLenAsNull(model.getValueAt(i, "csourcebillbid"));
				selectPks.add(pk);
			}
		}
		if (selectPks.isEmpty()) return;
		IHk_arap_operateMaintain itf = NCLocator.getInstance().lookup(IHk_arap_operateMaintain.class);
		String updateSql = " update hk_arap_bill_b " +
				" set nc_gzsj_01 = 'Y' " +
				" where dr = 0 " +
				" and pk_hk_arap_bill_b in " + PuPubVO.getSqlInByList(selectPks);
		int res = itf.execUpdateSQL(updateSql);
		if (res > 0) {
			for (int row : selectRows) {
				model.setValueAt(UFBoolean.TRUE, row, "vbdef20");
			}
		}
		ShowStatusBarMsgUtil.showStatusBarMsg("挂账审计成功【"+res+"条】", this.context);
	}

	public BillListView getListView() {
		return listView;
	}

	public void setListView(BillListView listView) {
		this.listView = listView;
	}

	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
	}

	public BillForm getEditor() {
		return editor;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public LoginContext getContext() {
		return context;
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}

}
