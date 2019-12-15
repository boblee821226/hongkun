package nc.ui.hkjt.hg_srxm.ace.handler;

import nc.ui.hkjt.pub.ace.handler.DeptFilterUtils;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;

public class CardHeadTailBeforeEditHandler implements
IAppEventHandler<CardHeadTailBeforeEditEvent>{

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		e.setReturnValue(true);
		if(SrxmHVO.PK_DEPT.endsWith(e.getKey())){
			new DeptFilterUtils().resetDeptRefSql(e);
		}
		else if(SrxmHVO.PK_KJKM.equals(e.getKey()))
		{// ��ƿ�Ŀ
			String pk_org = e.getBillCardPanel().getHeadItem("pk_org")
					.getValueObject().toString();
			UIRefPane kmpane = (UIRefPane) e.getBillCardPanel()
					.getHeadItem(e.getKey()).getComponent();
			kmpane.setPk_org(pk_org);
		}
	}

}
