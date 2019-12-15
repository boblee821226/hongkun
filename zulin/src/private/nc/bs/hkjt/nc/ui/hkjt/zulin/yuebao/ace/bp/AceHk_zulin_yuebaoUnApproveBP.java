package nc.bs.hkjt.nc.ui.hkjt.zulin.yuebao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_zulin_yuebaoUnApproveBP {

	public YuebaoBillVO[] unApprove(YuebaoBillVO[] clientBills,
			YuebaoBillVO[] originBills) {
		for (YuebaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<YuebaoBillVO> update = new BillUpdate<YuebaoBillVO>();
		YuebaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
