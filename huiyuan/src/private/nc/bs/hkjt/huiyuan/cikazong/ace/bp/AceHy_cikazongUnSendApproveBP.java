package nc.bs.hkjt.huiyuan.cikazong.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceHy_cikazongUnSendApproveBP {

	public CikazongBillVO[] unSend(CikazongBillVO[] clientBills,
			CikazongBillVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<CikazongBillVO> update = new BillUpdate<CikazongBillVO>();
		CikazongBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(CikazongBillVO[] clientBills) {
		for (CikazongBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
