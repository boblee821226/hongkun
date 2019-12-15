package nc.ui.hkjt.hg_jzfs.ace.handler;

import nc.ui.hkjt.pub.ace.handler.DeptFilterUtils;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;

public class CardHeadTailBeforeEditHandler implements
IAppEventHandler<CardHeadTailBeforeEditEvent>{

	@Override
	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		e.setReturnValue(true);
		if(JzfsHVO.PK_DEPT.endsWith(e.getKey())){
			new DeptFilterUtils().resetDeptRefSql(e);
		}
		else if(JzfsHVO.PK_KJKM.equals(e.getKey()))
		{// 会计科目
			String pk_org = e.getBillCardPanel().getHeadItem("pk_org")
					.getValueObject().toString();
			UIRefPane kmpane = (UIRefPane) e.getBillCardPanel()
					.getHeadItem(e.getKey()).getComponent();
			kmpane.setPk_org(pk_org);
		}
	}

}
