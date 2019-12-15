package nc.ui.hkjt.hg_spfl.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.hkjt.srgk.huiguan.spfl.SpflHVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	@Override
	public void handleAppEvent(AddEvent e) {
		BillCardPanel cardPanel = e.getBillForm().getBillCardPanel();
		String pk_group = AppContext.getInstance().getPkGroup();
		cardPanel.setHeadItem(SpflHVO.PK_GROUP, pk_group);
		// 设置默认的登录组织
		String pk_user = AppContext.getInstance().getPkUser();
		String pk_org = e.getContext().getPk_org();
		cardPanel.setHeadItem(SpflHVO.PK_ORG, pk_org);
		cardPanel.setHeadItem(SpflHVO.CREATOR, pk_user);
		cardPanel.setHeadItem(SpflHVO.CREATIONTIME, new UFDateTime());
		initParentCode(e);
	}

	private void initParentCode(AddEvent e) {
		BillItem parentCode = e.getBillForm().getBillCardPanel()
				.getHeadItem(SpflHVO.PK_PARENT);
		if (e.getBillForm().getModel().getSelectedData() != null) {
			SpflHVO vo = (SpflHVO) e.getBillForm().getModel().getSelectedData();
			((UIRefPane) parentCode.getComponent()).setPK(vo.getPrimaryKey());
			parentCode.setEdit(false);
		} else {
			parentCode.setEdit(true);
		}
	}
}
