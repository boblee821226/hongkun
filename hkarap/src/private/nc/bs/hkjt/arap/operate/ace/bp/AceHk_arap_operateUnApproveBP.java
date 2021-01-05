package nc.bs.hkjt.arap.operate.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.arap.operate.OperateBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_arap_operateUnApproveBP {

	public OperateBillVO[] unApprove(OperateBillVO[] clientBills,
			OperateBillVO[] originBills) {
		for (OperateBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<OperateBillVO> update = new BillUpdate<OperateBillVO>();
		OperateBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
