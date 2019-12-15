package nc.ui.hkjt.hg_srxm.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	@Override
	public void handleAppEvent(AddEvent e) {
		BillCardPanel cardPanel = e.getBillForm().getBillCardPanel();
		String pk_group = AppContext.getInstance().getPkGroup();
		String pk_user = AppContext.getInstance().getPkUser();
		// ����Ĭ�ϵĵ�¼��֯
		String pk_org = e.getContext().getPk_org();
		cardPanel.setHeadItem(SrxmHVO.PK_GROUP, pk_group);
		cardPanel.setHeadItem(SrxmHVO.PK_ORG, pk_org);
		cardPanel.setHeadItem(SrxmHVO.CREATOR, pk_user);
		cardPanel.setHeadItem(SrxmHVO.CREATIONTIME, new UFDateTime());
		initParentCode(e);
		initCode(e);// ���¼��ı��룬Ĭ���ȸ�ֵ �ϼ��ı���
	}

	private void initParentCode(AddEvent e) {
		BillItem parentCode = e.getBillForm().getBillCardPanel()
				.getHeadItem(SrxmHVO.PK_PARENT);
		BillItem levelcode =e.getBillForm().getBillCardPanel()
				.getHeadItem(SrxmHVO.LEVELNO);
		if (e.getBillForm().getModel().getSelectedData() != null) {
			SrxmHVO vo = (SrxmHVO) e.getBillForm().getModel().getSelectedData();
			((UIRefPane) parentCode.getComponent()).setPK(vo.getPrimaryKey());
			((UIRefPane) parentCode.getComponent()).getRefModel().getRefSql();
//			e.getBillForm().getBillCardPanel().setHeadItem(SrxmHVO.PK_PARENT, vo.getPrimaryKey());
			//�㼶��Ÿ�ֵ
			Integer levelno = vo.getLevelno();
			if(levelno!=null){
				levelcode.setValue(levelno+1);
				levelcode.setEdit(false);
			}
			parentCode.setEdit(false);
		} else {
			((UIRefPane) parentCode.getComponent()).getRefModel().getRefSql();
			parentCode.setEdit(true);
			//����ϲ�㼶���Ϊ����ôĬ�ϸ�ֵΪ1
			levelcode.setValue(1);
			levelcode.setEdit(false);
		}
	}
	
	private void initCode(AddEvent e)
	{// ���¼��ı��룬Ĭ���ȸ�ֵ �ϼ��ı���
		if (e.getBillForm().getModel().getSelectedData() != null) {
			SrxmHVO vo = (SrxmHVO) e.getBillForm().getModel().getSelectedData();
			BillItem code =e.getBillForm().getBillCardPanel().getHeadItem(SrxmHVO.CODE);
			code.setValue(vo.getCode() + "**");
		}
	}
}
