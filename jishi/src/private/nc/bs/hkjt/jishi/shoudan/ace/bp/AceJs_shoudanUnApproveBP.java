package nc.bs.hkjt.jishi.shoudan.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceJs_shoudanUnApproveBP {

	public ShoudanBillVO[] unApprove(ShoudanBillVO[] clientBills,
			ShoudanBillVO[] originBills) {
		for (ShoudanBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<ShoudanBillVO> update = new BillUpdate<ShoudanBillVO>();
		ShoudanBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
