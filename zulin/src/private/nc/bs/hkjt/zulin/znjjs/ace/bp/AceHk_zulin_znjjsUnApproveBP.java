package nc.bs.hkjt.zulin.znjjs.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_zulin_znjjsUnApproveBP {

	public ZnjjsBillVO[] unApprove(ZnjjsBillVO[] clientBills,
			ZnjjsBillVO[] originBills) {
		for (ZnjjsBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<ZnjjsBillVO> update = new BillUpdate<ZnjjsBillVO>();
		ZnjjsBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
