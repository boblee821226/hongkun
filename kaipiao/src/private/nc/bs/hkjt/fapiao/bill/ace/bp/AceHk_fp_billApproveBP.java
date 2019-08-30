package nc.bs.hkjt.fapiao.bill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHk_fp_billApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public BillFpBillVO[] approve(BillFpBillVO[] clientBills,
			BillFpBillVO[] originBills) {
		for (BillFpBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<BillFpBillVO> update = new BillUpdate<BillFpBillVO>();
		BillFpBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
