package nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHg_srdibiaoUnApproveBP {

	public SrdibiaoBillVO[] unApprove(SrdibiaoBillVO[] clientBills,
			SrdibiaoBillVO[] originBills) {
		for (SrdibiaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<SrdibiaoBillVO> update = new BillUpdate<SrdibiaoBillVO>();
		SrdibiaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
