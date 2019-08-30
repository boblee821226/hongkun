package nc.bs.hkjt.zulin.znjjm.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceHk_zulin_znjjmUnSendApproveBP {

	public ZnjjmBillVO[] unSend(ZnjjmBillVO[] clientBills,
			ZnjjmBillVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<ZnjjmBillVO> update = new BillUpdate<ZnjjmBillVO>();
		ZnjjmBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(ZnjjmBillVO[] clientBills) {
		for (ZnjjmBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
