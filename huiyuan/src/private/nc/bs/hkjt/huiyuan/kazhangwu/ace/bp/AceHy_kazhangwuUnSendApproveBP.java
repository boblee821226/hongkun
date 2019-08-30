package nc.bs.hkjt.huiyuan.kazhangwu.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceHy_kazhangwuUnSendApproveBP {

	public KazhangwuBillVO[] unSend(KazhangwuBillVO[] clientBills,
			KazhangwuBillVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<KazhangwuBillVO> update = new BillUpdate<KazhangwuBillVO>();
		KazhangwuBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(KazhangwuBillVO[] clientBills) {
		for (KazhangwuBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
