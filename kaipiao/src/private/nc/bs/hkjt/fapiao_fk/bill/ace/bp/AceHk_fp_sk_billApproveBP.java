package nc.bs.hkjt.fapiao_fk.bill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHk_fp_sk_billApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public BillSkFpBillVO[] approve(BillSkFpBillVO[] clientBills,
			BillSkFpBillVO[] originBills) {
		for (BillSkFpBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<BillSkFpBillVO> update = new BillUpdate<BillSkFpBillVO>();
		BillSkFpBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
