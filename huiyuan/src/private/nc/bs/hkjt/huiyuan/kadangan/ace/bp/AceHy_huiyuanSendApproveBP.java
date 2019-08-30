package nc.bs.hkjt.huiyuan.kadangan.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceHy_huiyuanSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public KadanganBillVO[] sendApprove(KadanganBillVO[] clientBills,
			KadanganBillVO[] originBills) {
		for (KadanganBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		KadanganBillVO[] returnVos = new BillUpdate<KadanganBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
