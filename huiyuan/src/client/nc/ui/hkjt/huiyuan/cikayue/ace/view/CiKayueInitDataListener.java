package nc.ui.hkjt.huiyuan.cikayue.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.hkjt.huiyuan.cikayue.ace.action.CemxAction.LinkQueryData_hy;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;

public class CiKayueInitDataListener extends DefaultFuncNodeInitDataListener {
	
	@Override
	public void initData(FuncletInitData data) {
	 super.initData(null);
	 if(data!=null&&data.getInitData()!=null){
		 LinkQueryData_hy linkQueryData=(LinkQueryData_hy)data.getInitData();
		 this.getModel().initModel(linkQueryData.getUserObject());
		 
		 this.getEditor().getBillCardPanel().getBodyItem("ka_code").setShow(true);
		 this.getEditor().getBillCardPanel().getBodyItem("startdata").setShow(true);
		 this.getEditor().getBillCardPanel().getBodyItem("vbdef03").setShow(true);	// ²»¼õ´Î
		 
		 getEditor().getBillCardPanel().setBillData(
					getEditor().getBillCardPanel().getBillData());
		 
//		 this.getBbcxAction().setEnabled(false);
//		 this.getCemxAction().setEnabled(false);
	 }
	 
	 
	 
	 
	}

	private ShowUpableBillForm editor;
	public ShowUpableBillForm getEditor() {
		return editor;
	}
	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}
	
	private ShowUpableBillListView listview;
	public ShowUpableBillListView getListview() {
		return listview;
	}

	public void setListview(ShowUpableBillListView listview) {
		this.listview = listview;
	}
	
	private NCAction bbcxAction;
	public NCAction getBbcxAction() {
		return bbcxAction;
	}
	public void setBbcxAction(NCAction bbcxAction) {
		this.bbcxAction = bbcxAction;
	}
	
	private NCAction cemxAction;
	public NCAction getCemxAction() {
		return cemxAction;
	}
	public void setCemxAction(NCAction cemxAction) {
		this.cemxAction = cemxAction;
	}
	
}
