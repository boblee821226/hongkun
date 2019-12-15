package nc.ui.hkjt.nc.ui.hkjt.zulin.yuebao.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.model.AbstractUIAppModel;

public class YuebaoCardHeadAfterEditEvent implements IAppEventHandler<CardHeadTailAfterEditEvent> {

	private AbstractUIAppModel model;		// model
	private ShowUpableBillForm cardForm;	// 卡片界面
	
	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		
		try
		{
			if( "vdef01".equals( e.getKey() ) )	// 期间
			{
//				System.out.println("==vdef01==after==");
				
				UIRefPane refPane = (UIRefPane)this.getCardForm().getBillCardPanel().getHeadItem("vdef01").getComponent();
//				Object obj1 = obj.getRefModel().getFieldCode();
//				Object obj2 = obj.getRefModel().getData();
//				Object obj3 = obj.getText();
				
				// bd_accperiodmonth.yearmth, bd_accperiodmonth.begindate, bd_accperiodmonth.enddate
				Object yearmth   = refPane.getRefValue("bd_accperiodmonth.yearmth");
				Object begindate = refPane.getRefValue("bd_accperiodmonth.begindate");
				Object enddate   = refPane.getRefValue("bd_accperiodmonth.enddate");
				
				this.getCardForm().getBillCardPanel().getHeadItem("yearmonth").setValue(yearmth);
				this.getCardForm().getBillCardPanel().getHeadItem("begindate").setValue(begindate);
				this.getCardForm().getBillCardPanel().getHeadItem("enddate").setValue(enddate);
				
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
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
