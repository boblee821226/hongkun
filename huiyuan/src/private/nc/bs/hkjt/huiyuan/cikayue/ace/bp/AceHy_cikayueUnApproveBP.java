package nc.bs.hkjt.huiyuan.cikayue.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_cikayueUnApproveBP {

	public CikayueBillVO[] unApprove(CikayueBillVO[] clientBills,
			CikayueBillVO[] originBills) {
		for (CikayueBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<CikayueBillVO> update = new BillUpdate<CikayueBillVO>();
		CikayueBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
