package nc.bs.hkjt.zulin.sjdy.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHk_zulin_sjdySendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public SjdyBillVO[] sendApprove(SjdyBillVO[] clientBills,
			SjdyBillVO[] originBills) {
		for (SjdyBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		SjdyBillVO[] returnVos = new BillUpdate<SjdyBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
