package nc.bs.hkjt.huiyuan.cikazong.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_cikazongUnApproveBP {

	public CikazongBillVO[] unApprove(CikazongBillVO[] clientBills,
			CikazongBillVO[] originBills) {
		for (CikazongBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<CikazongBillVO> update = new BillUpdate<CikazongBillVO>();
		CikazongBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
