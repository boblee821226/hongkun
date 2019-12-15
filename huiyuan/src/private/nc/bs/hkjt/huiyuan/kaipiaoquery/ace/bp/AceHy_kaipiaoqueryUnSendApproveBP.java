package nc.bs.hkjt.huiyuan.kaipiaoquery.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHy_kaipiaoqueryUnSendApproveBP {

	public KaipiaoqueryBillVO[] unSend(KaipiaoqueryBillVO[] clientBills,
			KaipiaoqueryBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<KaipiaoqueryBillVO> update = new BillUpdate<KaipiaoqueryBillVO>();
		KaipiaoqueryBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(KaipiaoqueryBillVO[] clientBills) {
		for (KaipiaoqueryBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
