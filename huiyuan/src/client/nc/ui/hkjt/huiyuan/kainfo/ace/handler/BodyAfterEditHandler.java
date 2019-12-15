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
	private ShowUpableBillForm cardForm;	// ��Ƭ����
	
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
//		BillCardPanel cardPanel = e.getBillCardPanel();
//	    boolean istotalshow = cardPanel.getBodyPanel().isTatolRow();
//	    cardPanel.getBodyPanel().setTotalRowShow(false);
		try
		{
		    String editKey = e.getKey();
		    
		    // ��Ŀ����
		    if (KainfoBVO.XMLX.equals(editKey)) {
		    	
//		    	String oldValue = PuPubVO.getString_TrimZeroLenAsNull(e.getOldValue());
		    	String newValue = PuPubVO.getString_TrimZeroLenAsNull(e.getValue());
		    	int row = e.getRow();
		    	
		    	if("���Ͽ�".equals(newValue))
		    	{
		    		e.getBillCardPanel().getBillModel().setValueAt(null, row, editKey);
		    		MessageDialog.showErrorDlg(e.getBillCardPanel(), "ע��", "����ֱ��ѡ�����Ͽ�����ͨ�������Ͽ�����ť������");
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
