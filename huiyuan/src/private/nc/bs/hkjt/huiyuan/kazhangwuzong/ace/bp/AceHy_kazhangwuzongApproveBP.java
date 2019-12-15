package nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHy_kazhangwuzongApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public KazhangwuzongBillVO[] approve(KazhangwuzongBillVO[] clientBills,
			KazhangwuzongBillVO[] originBills) {
		for (KazhangwuzongBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KazhangwuzongBillVO> update = new BillUpdate<KazhangwuzongBillVO>();
		KazhangwuzongBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
