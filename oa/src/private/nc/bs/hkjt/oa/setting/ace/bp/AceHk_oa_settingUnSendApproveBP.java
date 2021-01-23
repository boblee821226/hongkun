package nc.bs.hkjt.oa.setting.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHk_oa_settingUnSendApproveBP {

	public OaSettingBillVO[] unSend(OaSettingBillVO[] clientBills,
			OaSettingBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<OaSettingBillVO> update = new BillUpdate<OaSettingBillVO>();
		OaSettingBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(OaSettingBillVO[] clientBills) {
		for (OaSettingBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
