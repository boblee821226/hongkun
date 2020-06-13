package nc.bs.hkjt.store.lvyun_out.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHk_store_lvyun_outSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public LyOutStoreBillVO[] sendApprove(LyOutStoreBillVO[] clientBills,
			LyOutStoreBillVO[] originBills) {
		for (LyOutStoreBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		LyOutStoreBillVO[] returnVos = new BillUpdate<LyOutStoreBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
