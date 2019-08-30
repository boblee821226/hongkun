package nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceJd_rzmxUnApproveBP {

	public RzmxBillVO[] unApprove(RzmxBillVO[] clientBills,
			RzmxBillVO[] originBills) {
		for (RzmxBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<RzmxBillVO> update = new BillUpdate<RzmxBillVO>();
		RzmxBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
