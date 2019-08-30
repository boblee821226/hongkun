package nc.ui.hkjt.srgk.huiguan.sgshuju.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuHVO;

public class CardBodyBeforeEditEvent
		implements
		IAppEventHandler<nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent> {

	@Override
	public void handleAppEvent(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		// ���÷���ֵ
		e.setReturnValue(true);

		if (SgshujuBVO.ZD_PK.equals(e.getKey())) {
			getBeforeHandler().resetZDHRefSql(e);
		}
		else if (SgshujuBVO.ZD_ITEM_PK.equals(e.getKey())) {
			getBeforeHandler().resetZDBRefSql(e);
		}
		else if (  SgshujuBVO.TZ_KM_JZFS_1.equals(e.getKey())
				|| SgshujuBVO.TZ_KM_JZFS_2.equals(e.getKey())
				) {
			getBeforeHandler().resetJZFSRefSql(e);
		}
		else if (  SgshujuBVO.TZ_KM_SRXM_1.equals(e.getKey())
				|| SgshujuBVO.TZ_KM_SRXM_2.equals(e.getKey())
				) {
			getBeforeHandler().resetSRXMRefSql(e);
		}
		if(SgshujuBVO.TZ_KM_JZFS_1.equals(e.getKey())){//���˷�ʽ1
			Object srxm = e.getBillCardPanel().getBodyValueAt(e.getRow(), SgshujuBVO.TZ_KM_SRXM_1);
			if(srxm!=null){
				e.setReturnValue(false);
			}
		}else if(SgshujuBVO.TZ_KM_SRXM_1.equals(e.getKey())){//������Ŀ1
			Object jzfs = e.getBillCardPanel().getBodyValueAt(e.getRow(), SgshujuBVO.TZ_KM_JZFS_1);
			if(jzfs!=null){//������˷�ʽ1��Ϊ�գ���������Ŀ1���ܱ༭
				e.setReturnValue(false);
			}
		}else if(SgshujuBVO.TZ_KM_JZFS_2.equals(e.getKey())){//���˷�ʽ2
			Object srxm = e.getBillCardPanel().getBodyValueAt(e.getRow(), SgshujuBVO.TZ_KM_SRXM_2);
			if(srxm!=null){
				e.setReturnValue(false);
			}
		}else if(SgshujuBVO.TZ_KM_SRXM_2.equals(e.getKey())){//������Ŀ2
			Object jzfs = e.getBillCardPanel().getBodyValueAt(e.getRow(), SgshujuBVO.TZ_KM_JZFS_2);
			if(jzfs!=null){//������˷�ʽ1��Ϊ�գ���������Ŀ1���ܱ༭
				e.setReturnValue(false);
			}
		}
	}

	BeforeHandler beforhandler = null;

	private BeforeHandler getBeforeHandler() {
		if (beforhandler == null) {
			beforhandler = new BeforeHandler();
		}
		return beforhandler;
	}
}
