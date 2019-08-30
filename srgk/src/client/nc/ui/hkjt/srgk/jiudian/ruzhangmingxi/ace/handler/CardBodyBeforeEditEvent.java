package nc.ui.hkjt.srgk.jiudian.ruzhangmingxi.ace.handler;

import hd.vo.pub.tools.PuPubVO;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBVO;
import nc.vo.pub.lang.UFDouble;

public class CardBodyBeforeEditEvent
		implements
		IAppEventHandler<nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent> {

	@Override
	public void handleAppEvent(
			nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent e) {
		// 设置返回值
		e.setReturnValue(true);

		if(RzmxBVO.SRXM_ID.equals(e.getKey())){		// 收入项目
			UFDouble charge = 
				PuPubVO.getUFDouble_ZeroAsNull(
						e.getBillCardPanel().getBodyValueAt(e.getRow(), RzmxBVO.CHARGE)
				);
			if(charge!=null){//消费金额 有值， 才可以录入 收入项目
				getBeforeHandler().resetSRXMRefSql(e);
			}
			else{
				e.setReturnValue(false);
			}
		}
		else if (RzmxBVO.JZFS_ID.equals(e.getKey())){	// 结账方式
			UFDouble payment = 
				PuPubVO.getUFDouble_ZeroAsNull(
						e.getBillCardPanel().getBodyValueAt(e.getRow(), RzmxBVO.PAYMENT)
				);
			if(payment!=null){//结账金额 有值， 才可以录入 结账方式
				getBeforeHandler().resetSRXMRefSql(e);
			}
			else{
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
