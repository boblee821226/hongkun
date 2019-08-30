package nc.bs.hkjt.srgk.huiguan.zhangdan.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHg_zhangdanApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public ZhangdanBillVO[] approve(ZhangdanBillVO[] clientBills,
			ZhangdanBillVO[] originBills) {
		for (ZhangdanBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<ZhangdanBillVO> update = new BillUpdate<ZhangdanBillVO>();
		ZhangdanBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
