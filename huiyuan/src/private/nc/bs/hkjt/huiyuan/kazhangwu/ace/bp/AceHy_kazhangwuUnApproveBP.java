package nc.bs.hkjt.huiyuan.kazhangwu.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_kazhangwuUnApproveBP {

	public KazhangwuBillVO[] unApprove(KazhangwuBillVO[] clientBills,
			KazhangwuBillVO[] originBills) {
		for (KazhangwuBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KazhangwuBillVO> update = new BillUpdate<KazhangwuBillVO>();
		KazhangwuBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
