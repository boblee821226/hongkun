package nc.ui.ct.saledaily.action;

import hd.ui.hl.pub.tools.PubBatchEditDialog;
import hd.vo.pub.tools.PuPubVO;

import java.awt.event.ActionEvent;
import java.util.Vector;

import nc.ui.ct.action.HelpAction;
import nc.ui.ct.model.CTModel;
import nc.ui.ct.saledaily.view.SaledailyBillForm;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.trade.business.HYPubBO_Client;
import nc.vo.ct.saledaily.entity.AggCtSaleVO;
import nc.vo.ct.saledaily.entity.CtSaleBVO;
import nc.vo.ct.saledaily.entity.CtSaleVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class TuizuAction extends HelpAction {

	/**
	 * 退租
	 */
	private static final long serialVersionUID = 3533254360820084816L;
	
	private SaledailyBillForm cardForm = null;
	
	private ShowUpableBillListView listForm = null;
	
	public SaledailyBillForm getCardForm() {
	    return this.cardForm;
	  }
	
	  public void setCardForm(SaledailyBillForm cardForm) {
	    this.cardForm = cardForm;
	  }
	  
	  public ShowUpableBillListView getListForm() {
		    return this.listForm;
	}
	  public void setListForm(ShowUpableBillListView listForm) {
		    this.listForm = listForm;
	}
	  
	public TuizuAction()
	{
		this.setCode("tuizuAction");
	    this.setBtnName("退租");
	}
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
//		UFDate ddd = new UFDate("2018-10-01");
//		
//		String testMsg = getXmonthDate(ddd,8,-1).toString();
//		
//		MessageDialog.showWarningDlg(this.getCardForm(), "", testMsg);
//		
//		if(true) return ;	// 测试
		
		BillModel model_term = this.getCardForm().getBillCardPanel().getBillModel("pk_ct_sale_term");
		
		BillModel model_b = this.getCardForm().getBillCardPanel().getBillModel("pk_ct_sale_b");
		
		Integer htzz = PuPubVO.getInteger_NullAs(
				this.getCardForm().getBillCardPanel().getHeadItem("fstatusflag").getValueObject()
				,-1
		);	// 合同状态 
		
		if(htzz!=1)
		{// 合同状态 不是生效的  不能点
			MessageDialog.showErrorDlg(this.getCardForm(), "", "只有生效的合同才能退租");
			return;
		}
		
		// 退租日期
		final BillItem tzrq_item = new BillItem();	
		tzrq_item.setName("租金确认截止日");
		tzrq_item.setKey("tzrq");
		tzrq_item.setDataType(IBillItem.UFREF);
		tzrq_item.setRefType("日历");
		tzrq_item.setEdit(true);
		tzrq_item.setValue("");		// 默认值
		tzrq_item.setNull(true);	// 是否非空  false 不是非空
		
		PubBatchEditDialog dlg = new PubBatchEditDialog(
				 this.getCardForm()
				,new BillItem[]{
					 tzrq_item,
				});
		dlg.setTitle("选择");
		
		if( UIDialog.ID_OK != dlg.showModal()) return;
		
		CTModel model = (CTModel)this.getModel();
		System.out.println("=="+model.getSelectedData()+"==");
		
		CtSaleVO saleVO = ((AggCtSaleVO)model.getSelectedData()).getParentVO();
		
		saleVO.setVdef19(tzrq_item.getValue());
		saleVO.setDirty(false);
		saleVO.setDr(0);
		HYPubBO_Client.update(saleVO);
		
		MessageDialog.showWarningDlg(this.getCardForm(), "", "退租成功，请刷新数据。");
		
	}
	
	@Override
	protected boolean isActionEnable() {
		return true;
	}
	
}
