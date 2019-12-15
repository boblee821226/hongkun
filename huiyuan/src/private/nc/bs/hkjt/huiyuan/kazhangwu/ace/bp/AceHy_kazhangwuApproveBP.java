package nc.bs.hkjt.huiyuan.kazhangwu.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHy_kazhangwuApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public KazhangwuBillVO[] approve(KazhangwuBillVO[] clientBills,
			KazhangwuBillVO[] originBills) {
		for (KazhangwuBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KazhangwuBillVO> update = new BillUpdate<KazhangwuBillVO>();
		KazhangwuBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
