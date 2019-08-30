package nc.bs.hkjt.zulin.sjdy.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHk_zulin_sjdyApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public SjdyBillVO[] approve(SjdyBillVO[] clientBills,
			SjdyBillVO[] originBills) {
		for (SjdyBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<SjdyBillVO> update = new BillUpdate<SjdyBillVO>();
		SjdyBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
