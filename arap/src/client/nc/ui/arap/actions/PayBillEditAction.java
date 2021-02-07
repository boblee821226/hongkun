package nc.ui.arap.actions;

import java.awt.event.ActionEvent;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.ui.trade.business.HYPubBO_Client;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.hkjt.oa.HkOaInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

public class PayBillEditAction extends BillEditAction {
	private static final long serialVersionUID = -108570661184590521L;

	@Override
	public void doAction(ActionEvent e) throws Exception
	{	
		/**
		 * HK �ж��Ƿ��Ѿ����͸�OA
		 */
		AggPayBillVO billVO = (AggPayBillVO)this.getModel().getSelectedData();
		String idValue = billVO.getHeadVO().getPk_paybill();
		String pkBillTypeCode = billVO.getHeadVO().getPk_tradetype();
		SuperVO[] infoVOs = HYPubBO_Client.queryByCondition(
				HkOaInfoVO.class,
				" dr = 0 " +
				" and billid = '" + idValue + "' " +
				" and pk_billtypecode = '" + pkBillTypeCode + "' "
				);
		if (infoVOs != null && infoVOs.length > 0) {
			throw new BusinessException("�Ѿ��ύ��oa�������޸ġ�");
		}
		/******/
		super.doAction(e);
		// �ȵ��޸ĺ󣬿��ܵ�ȡ��
		WorkbenchEnvironment.getInstance().putClientCache(IBillFieldGet.CANCEL_COMMISSON_FLAG, false);
	}
	public PayBillEditAction() {
		super();
	}
}
