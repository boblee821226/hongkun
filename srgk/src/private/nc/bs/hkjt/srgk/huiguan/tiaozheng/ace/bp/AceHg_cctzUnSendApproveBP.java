package nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHg_cctzUnSendApproveBP {

	public CctzBillVO[] unSend(CctzBillVO[] clientBills,
			CctzBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<CctzBillVO> update = new BillUpdate<CctzBillVO>();
		CctzBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(CctzBillVO[] clientBills) {
		for (CctzBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
