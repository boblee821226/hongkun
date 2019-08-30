package nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHy_kaipiaoinfoSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public KaipiaoinfoBillVO[] sendApprove(KaipiaoinfoBillVO[] clientBills,
			KaipiaoinfoBillVO[] originBills) {
		for (KaipiaoinfoBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		KaipiaoinfoBillVO[] returnVos = new BillUpdate<KaipiaoinfoBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
