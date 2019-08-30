package nc.bs.hkjt.zulin.sjdy.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHk_zulin_sjdyUnSendApproveBP {

	public SjdyBillVO[] unSend(SjdyBillVO[] clientBills,
			SjdyBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<SjdyBillVO> update = new BillUpdate<SjdyBillVO>();
		SjdyBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(SjdyBillVO[] clientBills) {
		for (SjdyBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
