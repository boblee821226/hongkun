package nc.bs.hkjt.srgk.huiguan.sgshuju.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHg_sgshujuUnSendApproveBP {

	public SgshujuBillVO[] unSend(SgshujuBillVO[] clientBills,
			SgshujuBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<SgshujuBillVO> update = new BillUpdate<SgshujuBillVO>();
		SgshujuBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(SgshujuBillVO[] clientBills) {
		for (SgshujuBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
