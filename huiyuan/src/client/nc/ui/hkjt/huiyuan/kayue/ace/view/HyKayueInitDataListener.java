package nc.ui.hkjt.huiyuan.kayue.ace.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.hkjt.huiyuan.kayue.ace.action.LinkQueryData_hy;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;

public class HyKayueInitDataListener extends DefaultFuncNodeInitDataListener {
	
	@Override
	public void initData(FuncletInitData data) {
	 super.initData(null);
	 if(data!=null&&data.getInitData()!=null){
		 LinkQueryData_hy linkQueryData=(LinkQueryData_hy)data.getInitData();
		 this.getModel().initModel(linkQueryData.getUserObject());
		 
//		 this.getListview().getBillListPanel().getHeadItem("vdef02").setShow(true);
//		 this.getEditor().getBillCardPanel().getHeadItem("vdef02").setShow(true);
		 
		 this.getEditor().getBillCardPanel().getBodyItem("kaxing_name").setShow(false);
		 this.getEditor().getBillCardPanel().getBodyItem("ka_num").setShow(false);
		 this.getEditor().getBillCardPanel().getBodyItem("ka_code").setShow(true);
//		 this.getEditor().getBillCardPanel().getBodyItem("ka_dian").setShow(true);
		 
		 getEditor().getBillCardPanel().setBillData(
					getEditor().getBillCardPanel().getBillData());
		 
		 this.getBbcxAction().setEnabled(false);
		 this.getCemxAction().setEnabled(false);
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
