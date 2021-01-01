package nc.bs.hkjt.arap.unit.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.arap.unit.UnitBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHk_arap_unitApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public UnitBillVO[] approve(UnitBillVO[] clientBills,
			UnitBillVO[] originBills) {
		for (UnitBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<UnitBillVO> update = new BillUpdate<UnitBillVO>();
		UnitBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
