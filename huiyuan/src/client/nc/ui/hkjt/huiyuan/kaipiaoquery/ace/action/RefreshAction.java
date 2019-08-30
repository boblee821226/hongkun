package nc.ui.hkjt.huiyuan.kaipiaoquery.ace.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;

public class RefreshAction extends NCAction {
	
	private static final long serialVersionUID = 7589665530870496301L;

	public RefreshAction() {
		setBtnName("Ë¢ÐÂ");
		setCode("refreshAction");
	}

	private BillManageModel model;
	private ShowUpableBillForm editor;
	private ShowUpableBillListView listview;
	private BbcxAction bbcxAction;
	
	public BbcxAction getBbcxAction() {
		return bbcxAction;
	}

	public void setBbcxAction(BbcxAction bbcxAction) {
		this.bbcxAction = bbcxAction;
	}

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
		
		String ka_code = this.getBbcxAction().KA_CODE;
		String fph = this.getBbcxAction().FPH;
		
		if( ka_code==null && fph==null )
		{
			return ;
		}
		
		if(!ka_code.startsWith("'"))
			ka_code = "'"+ka_code+"'";
		
		this.getBbcxAction().bbcx(ka_code, fph,false,true);
	}
	
}
