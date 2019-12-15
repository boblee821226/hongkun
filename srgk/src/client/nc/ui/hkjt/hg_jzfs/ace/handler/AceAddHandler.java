package nc.ui.hkjt.hg_jzfs.ace.handler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.billform.AddEvent;
import nc.vo.hkjt.srgk.huiguan.jzfs.JzfsHVO;
import nc.vo.hkjt.srgk.huiguan.srxm.SrxmHVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;

public class AceAddHandler implements IAppEventHandler<AddEvent> {

	@Override
	public void handleAppEvent(AddEvent e) {
		BillCardPanel cardPanel = e.getBillForm().getBillCardPanel();
		String pk_group = AppContext.getInstance().getPkGroup();
		cardPanel.setHeadItem(JzfsHVO.PK_GROUP, pk_group);
		// 设置默认的登录组织
		String pk_user = AppContext.getInstance().getPkUser();
		String pk_org = e.getContext().getPk_org();
		cardPanel.setHeadItem(JzfsHVO.PK_ORG, pk_org);
		cardPanel.setHeadItem(JzfsHVO.CREATOR, pk_user);
		cardPanel.setHeadItem(JzfsHVO.CREATIONTIME, new UFDateTime());
		initParentCode(e);
		initCode(e);// 将下级的编码，默认先赋值 上级的编码
	}

	private void initParentCode(AddEvent e) {
		BillItem parentCode = e.getBillForm().getBillCardPanel()
				.getHeadItem(JzfsHVO.PK_PARENT);
		BillItem levelcode = e.getBillForm().getBillCardPanel()
				.getHeadItem(SrxmHVO.LEVELNO);
		if (e.getBillForm().getModel().getSelectedData() != null) {
			JzfsHVO vo = (JzfsHVO) e.getBillForm().getModel().getSelectedData();
			((UIRefPane) parentCode.getComponent()).setPK(vo.getPrimaryKey());
			
			parentCode.setEdit(false);
			// 层级编号赋值
			Integer levelno = vo.getLevelno();
			if (levelno != null) {
				levelcode.setValue(levelno + 1);
				levelcode.setEdit(false);
			}
		} else {
			parentCode.setEdit(true);
			//如果上层层级编号为空那么默认赋值为1
			levelcode.setValue(1);
			levelcode.setEdit(false);
		}
	}
	
	private void initCode(AddEvent e)
	{// 将下级的编码，默认先赋值 上级的编码
		if (e.getBillForm().getModel().getSelectedData() != null) {
			JzfsHVO vo = (JzfsHVO) e.getBillForm().getModel().getSelectedData();
			BillItem code =e.getBillForm().getBillCardPanel().getHeadItem(SrxmHVO.CODE);
			code.setValue(vo.getCode() + "**");
		}
	}
}
