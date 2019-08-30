package nc.ui.hkjt.srgk.huiguan.srdibiao.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeTabChangedEvent;

public class SRCardBodyBeforeTabChangedEvent implements IAppEventHandler<CardBodyBeforeTabChangedEvent> {

	@Override
	public void handleAppEvent(CardBodyBeforeTabChangedEvent e) {
		e.getBillCardPanel().getBodyPanel().getTable().setSortEnabled(false);
	}	

}
