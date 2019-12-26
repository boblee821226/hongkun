package nc.ui.hkjt.srgk.jiudian.ruzhangmingxi.ace.action;

import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.IJd_rzmxMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxHVO;

public class ChaifenAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public ChaifenAction() {
		setBtnName("���");
		setCode("chaifenAction");
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
		
		RzmxBillVO billVO = (RzmxBillVO)this.getModel().getSelectedData();
		
		IJd_rzmxMaintain itf = (IJd_rzmxMaintain)NCLocator.getInstance().lookup(IJd_rzmxMaintain.class.getName());
		itf.chaifen(new RzmxBillVO[]{billVO});
		
		MessageDialog.showWarningDlg(this.getEditor(), "", "�����ɣ����ɿ�����������ϸ����ˢ�µ���");
		
	}

}
