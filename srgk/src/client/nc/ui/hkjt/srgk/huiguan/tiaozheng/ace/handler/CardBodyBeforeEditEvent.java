package nc.ui.hkjt.srgk.huiguan.tiaozheng.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBVO;
public class CardBodyBeforeEditEvent
		implements
		IAppEventHandler<nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent> {
	@Override
	public void handleAppEvent(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		if(CctzBVO.TZ_KM_JZFS_1.equals(e.getKey())){//���˷�ʽ1
			Object srxm = e.getBillCardPanel().getBodyValueAt(e.getRow(), CctzBVO.TZ_KM_SRXM_1);
			if(srxm!=null){
				e.setReturnValue(false);
			}
		}else if(CctzBVO.TZ_KM_SRXM_1.equals(e.getKey())){//������Ŀ1
			Object jzfs = e.getBillCardPanel().getBodyValueAt(e.getRow(), CctzBVO.TZ_KM_JZFS_1);
			if(jzfs!=null){//������˷�ʽ1��Ϊ�գ���������Ŀ1���ܱ༭
				e.setReturnValue(false);
			}
		}else if(CctzBVO.TZ_KM_JZFS_2.equals(e.getKey())){//���˷�ʽ2
			Object srxm = e.getBillCardPanel().getBodyValueAt(e.getRow(), CctzBVO.TZ_KM_SRXM_2);
			if(srxm!=null){
				e.setReturnValue(false);
			}
		}else if(CctzBVO.TZ_KM_SRXM_2.equals(e.getKey())){//������Ŀ2
			Object jzfs = e.getBillCardPanel().getBodyValueAt(e.getRow(), CctzBVO.TZ_KM_JZFS_2);
			if(jzfs!=null){//������˷�ʽ1��Ϊ�գ���������Ŀ1���ܱ༭
				e.setReturnValue(false);
			}
		}
		
	}
}
