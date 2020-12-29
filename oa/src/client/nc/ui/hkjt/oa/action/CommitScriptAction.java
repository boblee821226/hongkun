package nc.ui.hkjt.oa.action;

import java.awt.event.ActionEvent;

import nc.ui.trade.business.HYPubBO_Client;
import nc.vo.hkjt.oa.HkOaSettingVO;
import nc.vo.pub.BusinessException;

public class CommitScriptAction extends
		nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8648254404503395047L;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		String billType = this.getBillType();
		needSendOA(billType);
		super.doAction(e);
	}
	/**
	 * 判断是否存在与配置表里，如果存在就需要发送给OA。
	 */
	static void needSendOA(String billType) throws BusinessException {
		HkOaSettingVO[] vos = (HkOaSettingVO[])(HYPubBO_Client.queryByCondition(HkOaSettingVO.class, "dr=0 and parentbilltype = '"+billType+"'"));
		if (vos != null && vos.length > 0) throw new BusinessException("请先发送OA，走OA的审批流。");
	}
}
