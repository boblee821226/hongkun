package nc.bs.hkjt.zulin.sdflr.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHk_zulin_sdflrSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public SdflrBillVO[] sendApprove(SdflrBillVO[] clientBills,
			SdflrBillVO[] originBills) {
		for (SdflrBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		SdflrBillVO[] returnVos = new BillUpdate<SdflrBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
