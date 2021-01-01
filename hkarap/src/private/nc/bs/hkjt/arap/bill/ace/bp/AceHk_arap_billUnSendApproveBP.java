package nc.bs.hkjt.arap.bill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.arap.bill.BillBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHk_arap_billUnSendApproveBP {

	public BillBillVO[] unSend(BillBillVO[] clientBills,
			BillBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<BillBillVO> update = new BillUpdate<BillBillVO>();
		BillBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(BillBillVO[] clientBills) {
		for (BillBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
