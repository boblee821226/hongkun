package nc.bs.hkjt.fapiao.bill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_fp_billUnApproveBP {

	public BillFpBillVO[] unApprove(BillFpBillVO[] clientBills,
			BillFpBillVO[] originBills) {
		for (BillFpBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<BillFpBillVO> update = new BillUpdate<BillFpBillVO>();
		BillFpBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
