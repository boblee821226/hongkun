package nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHg_zhangdanUnApproveBP {

	public ZhangdanBillVO[] unApprove(ZhangdanBillVO[] clientBills,
			ZhangdanBillVO[] originBills) {
		for (ZhangdanBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<ZhangdanBillVO> update = new BillUpdate<ZhangdanBillVO>();
		ZhangdanBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
