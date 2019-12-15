package nc.bs.hkjt.fapiao.bill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHk_fp_billSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public BillFpBillVO[] sendApprove(BillFpBillVO[] clientBills,
			BillFpBillVO[] originBills) {
		for (BillFpBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		BillFpBillVO[] returnVos = new BillUpdate<BillFpBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
