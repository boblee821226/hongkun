package nc.ui.cmp.settlement.actions;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.hkjt.report.HkjtReportITF;
import nc.itf.uap.IUAPQueryBS;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.query2.model.IModelDataManager;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.cmp.settlement.SettlementAggVO;
import nc.vo.cmp.settlement.SettlementHeadVO;
import nc.vo.scmpub.res.SCMActionCode;

public class HkSendAction extends NCAction{
	
	  private static final long serialVersionUID = 6127314224018704919L;

	  private ShowUpableBillForm billForm;

	  IUAPQueryBS  iUAPQueryBS =    (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 

	  private nc.ui.cmp.settlement.model.SettleModel model;
	  
	  private IModelDataManager dataManager;
	  
	  public HkSendAction() {
	    super();
	    SCMActionInitializer.initializeAction(this, SCMActionCode.PP_SENDASKBILL);
	    this.setBtnName("发出");
	    this.setCode("btnHkSend");
	  }

	@Override
	public void doAction(ActionEvent event) throws Exception {
		
	    if (null == this.model.getSelectedData()) {
	      return;
	    }
	    
	    try
	    {
	    	Object[] aggVOs_obj = (Object[])this.model.getSelectedOperaDatas();
	    	
	    	/**
	    	 * 首先判断 如果不是 结算成功，则报错
	    	 */
	    	for( int i=0;i<aggVOs_obj.length;i++)
	    	{
	    		SettlementAggVO 	aggVO = (SettlementAggVO)aggVOs_obj[i];
	    		SettlementHeadVO   headVO = (SettlementHeadVO)aggVO.getParentVO();
	    		Integer settlestatus = headVO.getSettlestatus();
	    		String billcode = headVO.getBillcode();
	    		if(settlestatus!=5){
	    			MessageDialog.showErrorDlg(
	    	    			this.getBillForm(),
	    	    			"",
	    	    			"【"+billcode+"】不是[结算成功]状态，不能发Email。"
	    	    	);
	    			return;
	    		}
	    	}
	    	/***END***/
	    	
//	    	if(true) return ;	// 测试
	    	
	    	HkjtReportITF hkjtReportITF = NCLocator.getInstance().lookup(HkjtReportITF.class);
	    	
	    	Object resultMsg = hkjtReportITF.sendMail(aggVOs_obj,null);

	    	MessageDialog.showWarningDlg(
	    			this.getBillForm(),
	    			"发送信息",
	    			resultMsg.toString()
	    	);
	    	
	    }
	    catch(Exception e)
	    {
	    	throw new Exception(e);
	    }

	    getDataManager().refresh();	// 刷新
	    
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

	public IModelDataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(IModelDataManager dataManager) {
		this.dataManager = dataManager;
	}

	@Override
  protected boolean isActionEnable() {
    Object[] datas = this.model.getSelectedOperaDatas();
//    if (datas != null && datas.length == 1) {
//      return true;
//    }
    if (this.model.getSelectedData() != null) {
      return true;
    }
    return false;
  }
}
