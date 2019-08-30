package nc.ui.hkjt.zulin.sjdy.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.AppContext;
import nc.ui.pub.bill.BillCardPanel;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	@Override
	public void handleAppEvent(AddEvent e) {
		String pk_group = e.getContext().getPk_group();
		String pk_org = e.getContext().getPk_org();
		BillCardPanel panel = e.getBillForm().getBillCardPanel();
		// 设置主组织默认值
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", pk_org);
		// 设置单据状态、单据业务日期默认值
		panel.setHeadItem("ibillstatus", BillStatusEnum.FREE.value());
		panel.setHeadItem("dbilldate", AppContext.getInstance().getBusiDate());
		/**
		 * HK 2019年3月26日11:46:43
		 * 收款开始日期、收款结束日期 都等于 单据日期。
		 */
		panel.setHeadItem("vdef01", AppContext.getInstance().getBusiDate());
		panel.setHeadItem("vdef02", AppContext.getInstance().getBusiDate());
		/***END***/
	}
}
