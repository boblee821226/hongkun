package nc.bs.hkjt.zulin.sdflr.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_zulin_sdflrUnApproveBP {

	public SdflrBillVO[] unApprove(SdflrBillVO[] clientBills,
			SdflrBillVO[] originBills) {
		for (SdflrBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<SdflrBillVO> update = new BillUpdate<SdflrBillVO>();
		SdflrBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
