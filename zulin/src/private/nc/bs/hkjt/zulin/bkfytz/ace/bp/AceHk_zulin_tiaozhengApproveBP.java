package nc.bs.hkjt.zulin.bkfytz.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHk_zulin_tiaozhengApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public TzBillVO[] approve(TzBillVO[] clientBills,
			TzBillVO[] originBills) {
		for (TzBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<TzBillVO> update = new BillUpdate<TzBillVO>();
		TzBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
