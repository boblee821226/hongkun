package nc.bs.hkjt.huiyuan.kaxing.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.huiyuan.kaxing.KaxingBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHy_kaxingApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public KaxingBillVO[] approve(KaxingBillVO[] clientBills,
			KaxingBillVO[] originBills) {
		for (KaxingBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KaxingBillVO> update = new BillUpdate<KaxingBillVO>();
		KaxingBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
