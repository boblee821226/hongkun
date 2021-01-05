package nc.bs.hkjt.arap.operate.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.arap.operate.OperateBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHk_arap_operateApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public OperateBillVO[] approve(OperateBillVO[] clientBills,
			OperateBillVO[] originBills) {
		for (OperateBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<OperateBillVO> update = new BillUpdate<OperateBillVO>();
		OperateBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
