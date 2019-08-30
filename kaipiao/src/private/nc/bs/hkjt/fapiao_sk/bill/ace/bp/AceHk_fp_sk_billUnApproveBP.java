package nc.bs.hkjt.fapiao_sk.bill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_fp_sk_billUnApproveBP {

	public BillSkFpBillVO[] unApprove(BillSkFpBillVO[] clientBills,
			BillSkFpBillVO[] originBills) {
		for (BillSkFpBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<BillSkFpBillVO> update = new BillUpdate<BillSkFpBillVO>();
		BillSkFpBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
