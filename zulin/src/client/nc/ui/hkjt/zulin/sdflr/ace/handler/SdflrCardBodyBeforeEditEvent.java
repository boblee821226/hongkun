package nc.ui.hkjt.zulin.sdflr.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.model.AbstractUIAppModel;

public class SdflrCardBodyBeforeEditEvent implements IAppEventHandler<CardBodyBeforeEditEvent> {

	private AbstractUIAppModel model;		// model
	private ShowUpableBillForm cardForm;	// 卡片界面
	
	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		
		try
		{
			if( "pk_sfxm".equals( e.getKey() ) )	// 收费项目
			{
				String key = e.getKey();
				UIRefPane uIRefPane = (UIRefPane)this.getCardForm().getBillCardPanel().getBodyItem(key).getComponent();
				uIRefPane.setWhereString(" name in ('能源—水','能源—电') ");
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		e.setReturnValue(true);
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
