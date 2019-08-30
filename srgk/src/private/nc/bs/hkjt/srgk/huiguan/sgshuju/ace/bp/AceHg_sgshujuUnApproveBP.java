package nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHg_sgshujuUnApproveBP {

	public SgshujuBillVO[] unApprove(SgshujuBillVO[] clientBills,
			SgshujuBillVO[] originBills) {
		for (SgshujuBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<SgshujuBillVO> update = new BillUpdate<SgshujuBillVO>();
		SgshujuBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
