package nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceJd_rzmxUnSendApproveBP {

	public RzmxBillVO[] unSend(RzmxBillVO[] clientBills,
			RzmxBillVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<RzmxBillVO> update = new BillUpdate<RzmxBillVO>();
		RzmxBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(RzmxBillVO[] clientBills) {
		for (RzmxBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
