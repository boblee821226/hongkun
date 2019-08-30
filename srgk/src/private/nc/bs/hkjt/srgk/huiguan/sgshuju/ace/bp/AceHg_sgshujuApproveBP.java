package nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHg_sgshujuApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public SgshujuBillVO[] approve(SgshujuBillVO[] clientBills,
			SgshujuBillVO[] originBills) {
		for (SgshujuBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<SgshujuBillVO> update = new BillUpdate<SgshujuBillVO>();
		SgshujuBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
