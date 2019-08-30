package nc.bs.hkjt.zulin.sjdy.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_zulin_sjdyUnApproveBP {

	public SjdyBillVO[] unApprove(SjdyBillVO[] clientBills,
			SjdyBillVO[] originBills) {
		for (SjdyBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<SjdyBillVO> update = new BillUpdate<SjdyBillVO>();
		SjdyBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
