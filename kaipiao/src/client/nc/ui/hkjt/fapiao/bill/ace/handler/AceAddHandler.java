package nc.ui.hkjt.fapiao.bill.ace.handler;

import nc.ui.hkjt.pub.Pub_Param;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.AppContext;

public class AceAddHandler implements IAppEventHandler<AddEvent> {
	
	@Override
	public void handleAppEvent(AddEvent e) {
		String pk_group = e.getContext().getPk_group();
		String pk_org = e.getContext().getPk_org();
		BillCardPanel panel = e.getBillForm().getBillCardPanel();
		// ��������֯Ĭ��ֵ
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", pk_org);
		// ���õ���״̬������ҵ������Ĭ��ֵ
		panel.setHeadItem("ibillstatus", BillStatusEnum.FREE.value());
		panel.setHeadItem("dbilldate", AppContext.getInstance().getBusiDate());
		/**
		 * ���� ��Ʊ���š���Ʊ����
		 * 2016��8��26��22:42:54
		 * Ʊ�ų���
		 * 2018��8��10��10:16:25
		 */
		panel.setHeadItem("fpdm", Pub_Param.getInstance().FPDM);		// ��Ʊ����
		panel.setHeadItem("fphm", Pub_Param.getInstance().FPHM);		// ��Ʊ����
		panel.setHeadItem("vdef10", Pub_Param.getInstance().FPLENGTH);	// Ʊ�ų���
		/**END*/
	}
}
