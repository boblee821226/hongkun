package nc.bs.hkjt.listener;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.ic.general.util.ICEventType;
import nc.vo.pub.BusinessException;
import nc.vo.ic.m4a.entity.GeneralInVO;

public class SendLvyunDataListener implements IBusinessListener {

	@Override
	public void doAction(IBusinessEvent event) throws BusinessException {
		
		nc.bs.ic.general.businessevent.ICGeneralCommonEvent e = (nc.bs.ic.general.businessevent.ICGeneralCommonEvent)event;
		
		System.out.println("===测试一下===");
		String eventType = e.getEventType();
		
		if (ICEventType.SignAfter.getCode().equals(eventType)) {
			// 签字后
			for (Object item : e.getOldObjs()) {
				GeneralInVO billVO = (GeneralInVO)item;
			}
//			throw new BusinessException("签字后");
			
		} else if (ICEventType.CancelsignAfter.getCode().equals(eventType)) {
			// 取消签字后
			for (Object item : e.getOldObjs()) {
				GeneralInVO billVO = (GeneralInVO)item;
			}
			throw new BusinessException("取消签字后");
		}

	}

}
