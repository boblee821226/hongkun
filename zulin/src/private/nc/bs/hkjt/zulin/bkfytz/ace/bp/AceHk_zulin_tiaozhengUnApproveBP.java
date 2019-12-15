package nc.bs.hkjt.zulin.bkfytz.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_zulin_tiaozhengUnApproveBP {

	public TzBillVO[] unApprove(TzBillVO[] clientBills,
			TzBillVO[] originBills) {
		for (TzBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<TzBillVO> update = new BillUpdate<TzBillVO>();
		TzBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
