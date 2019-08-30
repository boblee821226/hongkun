package nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHg_srdibiaoSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public SrdibiaoBillVO[] sendApprove(SrdibiaoBillVO[] clientBills,
			SrdibiaoBillVO[] originBills) {
		for (SrdibiaoBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		SrdibiaoBillVO[] returnVos = new BillUpdate<SrdibiaoBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
