package nc.bs.hkjt.oa.setting.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHk_oa_settingApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public OaSettingBillVO[] approve(OaSettingBillVO[] clientBills,
			OaSettingBillVO[] originBills) {
		for (OaSettingBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<OaSettingBillVO> update = new BillUpdate<OaSettingBillVO>();
		OaSettingBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
