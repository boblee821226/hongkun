package nc.bs.hkjt.zulin.znjjs.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHk_zulin_znjjsSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public ZnjjsBillVO[] sendApprove(ZnjjsBillVO[] clientBills,
			ZnjjsBillVO[] originBills) {
		for (ZnjjsBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		ZnjjsBillVO[] returnVos = new BillUpdate<ZnjjsBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
