package nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceHy_kazhangwuzongUnSendApproveBP {

	public KazhangwuzongBillVO[] unSend(KazhangwuzongBillVO[] clientBills,
			KazhangwuzongBillVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<KazhangwuzongBillVO> update = new BillUpdate<KazhangwuzongBillVO>();
		KazhangwuzongBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(KazhangwuzongBillVO[] clientBills) {
		for (KazhangwuzongBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
