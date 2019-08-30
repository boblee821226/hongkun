package nc.bs.hkjt.jishi.shoudan.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;

/**
 * 标准单据审核的BP
 */
public class AceJs_shoudanApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public ShoudanBillVO[] approve(ShoudanBillVO[] clientBills,
			ShoudanBillVO[] originBills) {
		for (ShoudanBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<ShoudanBillVO> update = new BillUpdate<ShoudanBillVO>();
		ShoudanBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
