package nc.bs.hkjt.huiyuan.kayue.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_kayueUnApproveBP {

	public KayueBillVO[] unApprove(KayueBillVO[] clientBills,
			KayueBillVO[] originBills) {
		for (KayueBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KayueBillVO> update = new BillUpdate<KayueBillVO>();
		KayueBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
