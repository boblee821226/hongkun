package nc.ui.hkjt.huiyuan.kaipiaoinfo.ace.handler;

import nc.ui.hkjt.pub.Pub_Param;
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
		 * 设置 发票代码、发票号码
		 * 2016年9月12日20:49:08
		 * 发票长度
		 * 2018年8月23日18:06:43
		 */
		panel.setHeadItem("fpdm", Pub_Param.getInstance().FPDM);
		panel.setHeadItem("fph", Pub_Param.getInstance().FPHM);
		panel.setHeadItem("vdef10", Pub_Param.getInstance().FPLENGTH);	// 票号长度
		/**END*/
	}
}
