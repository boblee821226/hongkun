package nc.bs.hkjt.zulin.sdflr.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHk_zulin_sdflrApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public SdflrBillVO[] approve(SdflrBillVO[] clientBills,
			SdflrBillVO[] originBills) {
		for (SdflrBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<SdflrBillVO> update = new BillUpdate<SdflrBillVO>();
		SdflrBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
