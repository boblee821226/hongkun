package nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceJd_rzmxSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public RzmxBillVO[] sendApprove(RzmxBillVO[] clientBills,
			RzmxBillVO[] originBills) {
		for (RzmxBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		RzmxBillVO[] returnVos = new BillUpdate<RzmxBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
