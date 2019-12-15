package nc.bs.hkjt.huiyuan.kaxing.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kaxing.KaxingBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_kaxingUnApproveBP {

	public KaxingBillVO[] unApprove(KaxingBillVO[] clientBills,
			KaxingBillVO[] originBills) {
		for (KaxingBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KaxingBillVO> update = new BillUpdate<KaxingBillVO>();
		KaxingBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
