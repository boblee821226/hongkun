package nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHg_yyribaoSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public YyribaoBillVO[] sendApprove(YyribaoBillVO[] clientBills,
			YyribaoBillVO[] originBills) {
		for (YyribaoBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		YyribaoBillVO[] returnVos = new BillUpdate<YyribaoBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
