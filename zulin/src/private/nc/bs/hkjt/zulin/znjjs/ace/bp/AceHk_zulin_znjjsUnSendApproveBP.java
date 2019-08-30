package nc.bs.hkjt.zulin.znjjs.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceHk_zulin_znjjsUnSendApproveBP {

	public ZnjjsBillVO[] unSend(ZnjjsBillVO[] clientBills,
			ZnjjsBillVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<ZnjjsBillVO> update = new BillUpdate<ZnjjsBillVO>();
		ZnjjsBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(ZnjjsBillVO[] clientBills) {
		for (ZnjjsBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
