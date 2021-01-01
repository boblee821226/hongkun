package nc.bs.hkjt.arap.bill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.arap.bill.BillBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHk_arap_billApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public BillBillVO[] approve(BillBillVO[] clientBills,
			BillBillVO[] originBills) {
		for (BillBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<BillBillVO> update = new BillUpdate<BillBillVO>();
		BillBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
