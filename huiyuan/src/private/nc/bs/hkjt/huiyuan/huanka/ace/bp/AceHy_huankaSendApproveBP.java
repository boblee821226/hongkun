package nc.bs.hkjt.huiyuan.huanka.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHy_huankaSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public HuankaBillVO[] sendApprove(HuankaBillVO[] clientBills,
			HuankaBillVO[] originBills) {
		for (HuankaBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		HuankaBillVO[] returnVos = new BillUpdate<HuankaBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
