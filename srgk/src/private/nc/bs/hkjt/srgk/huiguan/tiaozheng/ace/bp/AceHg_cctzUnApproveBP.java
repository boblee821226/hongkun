package nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHg_cctzUnApproveBP {

	public CctzBillVO[] unApprove(CctzBillVO[] clientBills,
			CctzBillVO[] originBills) {
		for (CctzBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<CctzBillVO> update = new BillUpdate<CctzBillVO>();
		CctzBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
