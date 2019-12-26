package nc.ui.hkjt.srgk.jiudian.ruzhangmingxi.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;

public class FanghaoAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public FanghaoAction() {
		setBtnName("房号");
		setCode("fanghaoAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;

	public ShowUpableBillListView getListview() {
		return listview;
	}

	public void setListview(ShowUpableBillListView listview) {
		this.listview = listview;
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		String pk_org = PuPubVO.getString_TrimZeroLenAsNull(
			this.getEditor().getBillCardPanel().getHeadItem("pk_org").getValueCache()
		);
		
		// 只有 0308， 才可以点此按钮
		if (! "0001N510000000001SY3".equals(pk_org)) {
			MessageDialog.showErrorDlg(this.getEditor(), "", "只有【0308】可点此按钮");
			return ;
		}
		
		String _0309 = "88888";	// 0309的虚拟房号
		String vbmemo = "系统赋值";

		BillModel model = this.getEditor().getBillCardPanel().getBillModel();
		// 循环表体 进行处理
		for (int i = 0; i < model.getRowCount(); i++) {
			String rmno = PuPubVO.getString_TrimZeroLenAsNull(model.getValueAt(i, "rmno"));
			if (rmno == null) { // 房号等于空，才需要处理
				String rzxm = PuPubVO.getString_TrimZeroLenAsNull(model.getValueAt(i, "item_name"));
				String khmz = PuPubVO.getString_TrimZeroLenAsNull(model.getValueAt(i, "khmz"));
				if (rzxm != null) {
					if (rzxm.startsWith("K")) {
						// 如果入账项目名称，是以K 开头，则说明是 康福瑞的房间
						model.setValueAt(_0309 , i, "rmno");
						model.setValueAt(vbmemo , i, "vbmemo");
					} else if (rzxm.equals("城市挂账") && khmz != null && khmz.startsWith("（康）")) {
						// 如果入账项目名称，是 城市挂账，并且 客户是以 （康） 开头，则说明是 康福瑞的房间
						model.setValueAt(_0309 , i, "rmno");
						model.setValueAt(vbmemo , i, "vbmemo");
					}
				}
			}
		}
		
		MessageDialog.showWarningDlg(this.getEditor(), "", "康西的房号，赋值成功。");
	}
	
}
