package nc.ui.hkjt.huiyuan.kainfo.ace.handler;

import hd.vo.pub.tools.PuPubVO;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBVO;

public class BodyAfterEditHandler implements
IAppEventHandler<CardBodyAfterEditEvent> {

	private AbstractUIAppModel model;		// model
	private ShowUpableBillForm cardForm;	// 卡片界面
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
//		BillCardPanel cardPanel = e.getBillCardPanel();
//	    boolean istotalshow = cardPanel.getBodyPanel().isTatolRow();
//	    cardPanel.getBodyPanel().setTotalRowShow(false);
		try
		{
		    String editKey = e.getKey();
		    
		    // 项目类型
		    if (KainfoBVO.XMLX.equals(editKey)) {
		    	
//		    	String oldValue = PuPubVO.getString_TrimZeroLenAsNull(e.getOldValue());
		    	String newValue = PuPubVO.getString_TrimZeroLenAsNull(e.getValue());
		    	int row = e.getRow();
		    	
		    	if("作废卡".equals(newValue))
		    	{
		    		e.getBillCardPanel().getBillModel().setValueAt(null, row, editKey);
		    		MessageDialog.showErrorDlg(e.getBillCardPanel(), "注意", "不能直接选择作废卡，请通过【作废卡】按钮来处理。");
		    	}
		    	
		    }
		    
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

	public AbstractUIAppModel getModel() {
		return model;
	}

	public void setModel(AbstractUIAppModel model) {
		this.model = model;
	}

	public ShowUpableBillForm getCardForm() {
		return cardForm;
	}

	public void setCardForm(ShowUpableBillForm cardForm) {
		this.cardForm = cardForm;
	}
	
}
