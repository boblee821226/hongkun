package nc.bs.hkjt.arap.bill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.arap.bill.BillBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_arap_billUnApproveBP {

	public BillBillVO[] unApprove(BillBillVO[] clientBills,
			BillBillVO[] originBills) {
		for (BillBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<BillBillVO> update = new BillUpdate<BillBillVO>();
		BillBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
