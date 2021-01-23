package nc.bs.hkjt.oa.setting.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHk_oa_settingSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public OaSettingBillVO[] sendApprove(OaSettingBillVO[] clientBills,
			OaSettingBillVO[] originBills) {
		for (OaSettingBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		OaSettingBillVO[] returnVos = new BillUpdate<OaSettingBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
