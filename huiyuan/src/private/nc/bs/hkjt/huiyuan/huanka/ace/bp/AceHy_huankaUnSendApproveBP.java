package nc.bs.hkjt.huiyuan.huanka.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHy_huankaUnSendApproveBP {

	public HuankaBillVO[] unSend(HuankaBillVO[] clientBills,
			HuankaBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<HuankaBillVO> update = new BillUpdate<HuankaBillVO>();
		HuankaBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(HuankaBillVO[] clientBills) {
		for (HuankaBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
