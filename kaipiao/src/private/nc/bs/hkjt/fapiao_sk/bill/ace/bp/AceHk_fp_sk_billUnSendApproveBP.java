package nc.bs.hkjt.fapiao_sk.bill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHk_fp_sk_billUnSendApproveBP {

	public BillSkFpBillVO[] unSend(BillSkFpBillVO[] clientBills,
			BillSkFpBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<BillSkFpBillVO> update = new BillUpdate<BillSkFpBillVO>();
		BillSkFpBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(BillSkFpBillVO[] clientBills) {
		for (BillSkFpBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
