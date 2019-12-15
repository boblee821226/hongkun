package nc.bs.hkjt.huiyuan.kazuofei.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHy_kazuofeiUnSendApproveBP {

	public KazuofeiBillVO[] unSend(KazuofeiBillVO[] clientBills,
			KazuofeiBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<KazuofeiBillVO> update = new BillUpdate<KazuofeiBillVO>();
		KazuofeiBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(KazuofeiBillVO[] clientBills) {
		for (KazuofeiBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
