package nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHy_kazhangwuzongSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public KazhangwuzongBillVO[] sendApprove(KazhangwuzongBillVO[] clientBills,
			KazhangwuzongBillVO[] originBills) {
		for (KazhangwuzongBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		KazhangwuzongBillVO[] returnVos = new BillUpdate<KazhangwuzongBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
