package nc.bs.hkjt.jishi.shoudan.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceJs_shoudanSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public ShoudanBillVO[] sendApprove(ShoudanBillVO[] clientBills,
			ShoudanBillVO[] originBills) {
		for (ShoudanBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		ShoudanBillVO[] returnVos = new BillUpdate<ShoudanBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
