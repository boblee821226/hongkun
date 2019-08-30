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
		// ��������֯Ĭ��ֵ
		panel.setHeadItem("pk_group", pk_group);
		panel.setHeadItem("pk_org", pk_org);
		// ���õ���״̬������ҵ������Ĭ��ֵ
		panel.setHeadItem("ibillstatus", BillStatusEnum.FREE.value());
		panel.setHeadItem("dbilldate", AppContext.getInstance().getBusiDate());
		
		/**
		 * ���� ��Ʊ���롢��Ʊ����
		 * 2016��9��12��20:49:08
		 * ��Ʊ����
		 * 2018��8��23��18:06:43
		 */
		panel.setHeadItem("fpdm", Pub_Param.getInstance().FPDM);
		panel.setHeadItem("fph", Pub_Param.getInstance().FPHM);
		panel.setHeadItem("vdef10", Pub_Param.getInstance().FPLENGTH);	// Ʊ�ų���
		/**END*/
	}
}
