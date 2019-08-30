package nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHg_zhangdanUnSendApproveBP {

	public ZhangdanBillVO[] unSend(ZhangdanBillVO[] clientBills,
			ZhangdanBillVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<ZhangdanBillVO> update = new BillUpdate<ZhangdanBillVO>();
		ZhangdanBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(ZhangdanBillVO[] clientBills) {
		for (ZhangdanBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
