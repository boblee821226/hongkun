package nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_kazhangwuzongUnApproveBP {

	public KazhangwuzongBillVO[] unApprove(KazhangwuzongBillVO[] clientBills,
			KazhangwuzongBillVO[] originBills) {
		for (KazhangwuzongBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KazhangwuzongBillVO> update = new BillUpdate<KazhangwuzongBillVO>();
		KazhangwuzongBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
