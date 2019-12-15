package nc.ui.hkjt.huiyuan.kainfo.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBVO;

public class BodyBeforeEditHandler implements
IAppEventHandler<CardBodyBeforeEditEvent> {

	@Override
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		
		String editKey = e.getKey();
		if( KainfoBVO.XMLX.equals(editKey) )
		{// 项目类型
			
//			e.get
			
		}
		
		e.setReturnValue(true);
	}

}
