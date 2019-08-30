package nc.bs.hkjt.huiyuan.cikainfo.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceHy_cikainfoUnSendApproveBP {

	public CikainfoBillVO[] unSend(CikainfoBillVO[] clientBills,
			CikainfoBillVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<CikainfoBillVO> update = new BillUpdate<CikainfoBillVO>();
		CikainfoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(CikainfoBillVO[] clientBills) {
		for (CikainfoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
