package nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHg_cctzApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public CctzBillVO[] approve(CctzBillVO[] clientBills,
			CctzBillVO[] originBills) {
		for (CctzBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<CctzBillVO> update = new BillUpdate<CctzBillVO>();
		CctzBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
