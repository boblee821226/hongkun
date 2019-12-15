package nc.bs.hkjt.huiyuan.cikatongji.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHy_cikatongjiUnSendApproveBP {

	public CikatongjiBillVO[] unSend(CikatongjiBillVO[] clientBills,
			CikatongjiBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<CikatongjiBillVO> update = new BillUpdate<CikatongjiBillVO>();
		CikatongjiBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(CikatongjiBillVO[] clientBills) {
		for (CikatongjiBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
