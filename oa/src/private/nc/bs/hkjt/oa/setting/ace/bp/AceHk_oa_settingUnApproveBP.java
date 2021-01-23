package nc.bs.hkjt.oa.setting.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_oa_settingUnApproveBP {

	public OaSettingBillVO[] unApprove(OaSettingBillVO[] clientBills,
			OaSettingBillVO[] originBills) {
		for (OaSettingBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<OaSettingBillVO> update = new BillUpdate<OaSettingBillVO>();
		OaSettingBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
