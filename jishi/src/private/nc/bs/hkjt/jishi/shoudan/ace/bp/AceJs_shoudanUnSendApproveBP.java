package nc.bs.hkjt.jishi.shoudan.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceJs_shoudanUnSendApproveBP {

	public ShoudanBillVO[] unSend(ShoudanBillVO[] clientBills,
			ShoudanBillVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<ShoudanBillVO> update = new BillUpdate<ShoudanBillVO>();
		ShoudanBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(ShoudanBillVO[] clientBills) {
		for (ShoudanBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
