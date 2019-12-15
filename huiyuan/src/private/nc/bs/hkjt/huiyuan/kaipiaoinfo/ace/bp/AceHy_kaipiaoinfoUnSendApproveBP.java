package nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceHy_kaipiaoinfoUnSendApproveBP {

	public KaipiaoinfoBillVO[] unSend(KaipiaoinfoBillVO[] clientBills,
			KaipiaoinfoBillVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<KaipiaoinfoBillVO> update = new BillUpdate<KaipiaoinfoBillVO>();
		KaipiaoinfoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(KaipiaoinfoBillVO[] clientBills) {
		for (KaipiaoinfoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
