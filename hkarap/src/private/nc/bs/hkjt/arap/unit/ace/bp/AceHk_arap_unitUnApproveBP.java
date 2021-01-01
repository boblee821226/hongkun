package nc.bs.hkjt.arap.unit.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.arap.unit.UnitBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_arap_unitUnApproveBP {

	public UnitBillVO[] unApprove(UnitBillVO[] clientBills,
			UnitBillVO[] originBills) {
		for (UnitBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<UnitBillVO> update = new BillUpdate<UnitBillVO>();
		UnitBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
