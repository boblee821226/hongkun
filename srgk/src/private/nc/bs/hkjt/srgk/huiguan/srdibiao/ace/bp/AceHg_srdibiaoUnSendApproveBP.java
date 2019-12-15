package nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHg_srdibiaoUnSendApproveBP {

	public SrdibiaoBillVO[] unSend(SrdibiaoBillVO[] clientBills,
			SrdibiaoBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<SrdibiaoBillVO> update = new BillUpdate<SrdibiaoBillVO>();
		SrdibiaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(SrdibiaoBillVO[] clientBills) {
		for (SrdibiaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
