package nc.bs.hkjt.nc.ui.hkjt.zulin.yuebao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHk_zulin_yuebaoUnSendApproveBP {

	public YuebaoBillVO[] unSend(YuebaoBillVO[] clientBills,
			YuebaoBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<YuebaoBillVO> update = new BillUpdate<YuebaoBillVO>();
		YuebaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(YuebaoBillVO[] clientBills) {
		for (YuebaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
