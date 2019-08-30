package nc.bs.hkjt.huiyuan.cikayue.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHy_cikayueApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public CikayueBillVO[] approve(CikayueBillVO[] clientBills,
			CikayueBillVO[] originBills) {
		for (CikayueBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<CikayueBillVO> update = new BillUpdate<CikayueBillVO>();
		CikayueBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
