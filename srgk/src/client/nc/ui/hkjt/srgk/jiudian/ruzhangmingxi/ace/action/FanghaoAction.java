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
		setBtnName("����");
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
		
		// ֻ�� 0308�� �ſ��Ե�˰�ť
		if (! "0001N510000000001SY3".equals(pk_org)) {
			MessageDialog.showErrorDlg(this.getEditor(), "", "ֻ�С�0308���ɵ�˰�ť");
			return ;
		}
		
		String _0309 = "88888";	// 0309�����ⷿ��
		String vbmemo = "ϵͳ��ֵ";

		BillModel model = this.getEditor().getBillCardPanel().getBillModel();
		// ѭ������ ���д���
		for (int i = 0; i < model.getRowCount(); i++) {
			String rmno = PuPubVO.getString_TrimZeroLenAsNull(model.getValueAt(i, "rmno"));
			if (rmno == null) { // ���ŵ��ڿգ�����Ҫ����
				String rzxm = PuPubVO.getString_TrimZeroLenAsNull(model.getValueAt(i, "item_name"));
				String khmz = PuPubVO.getString_TrimZeroLenAsNull(model.getValueAt(i, "khmz"));
				if (rzxm != null) {
					if (rzxm.startsWith("K")) {
						// ���������Ŀ���ƣ�����K ��ͷ����˵���� ������ķ���
						model.setValueAt(_0309 , i, "rmno");
						model.setValueAt(vbmemo , i, "vbmemo");
					} else if (rzxm.equals("���й���") && khmz != null && khmz.startsWith("������")) {
						// ���������Ŀ���ƣ��� ���й��ˣ����� �ͻ����� ������ ��ͷ����˵���� ������ķ���
						model.setValueAt(_0309 , i, "rmno");
						model.setValueAt(vbmemo , i, "vbmemo");
					}
				}
			}
		}
		
		MessageDialog.showWarningDlg(this.getEditor(), "", "�����ķ��ţ���ֵ�ɹ���");
	}
	
}
