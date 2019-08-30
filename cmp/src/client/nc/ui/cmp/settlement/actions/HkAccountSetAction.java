package nc.ui.cmp.settlement.actions;

import java.awt.event.ActionEvent;

import nc.ui.pp.m29.account.AccountManageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
/**
 * ” œ‰…Ë÷√∞¥≈•
 * */
public class HkAccountSetAction extends NCAction{

   private static final long serialVersionUID = -930760991849505020L;

   private ShowUpableBillForm billForm;

   private nc.ui.cmp.settlement.model.SettleModel model;
   
   public HkAccountSetAction() {
	    super();
	    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
	        "4005001_0", "04005001-10000")/* @res "” œ‰…Ë÷√" */);
	    this.setCode("btnHkAccountSet");
	  }
	  
	@Override
	public void doAction(ActionEvent e) throws Exception {
		 BillCardPanel billCardPanel = this.billForm.getBillCardPanel();
		    AccountManageDialog accountManageDialog =
		        new AccountManageDialog(billCardPanel, nc.vo.ml.NCLangRes4VoTransl
		            .getNCLangRes().getStrByID("4005001_0", "04005001-10001")/*
		                                                                      * @res
		                                                                      * "’À∫≈π‹¿Ì"
		                                                                      */);
		accountManageDialog.showModal();
	}

	public ShowUpableBillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}

	public nc.ui.cmp.settlement.model.SettleModel getModel() {
		return model;
	}

	public void setModel(nc.ui.cmp.settlement.model.SettleModel model) {
		this.model = model;
	}
	

}
