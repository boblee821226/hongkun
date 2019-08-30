package nc.bs.hkjt.huiyuan.cikazong.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHy_cikazongApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public CikazongBillVO[] approve(CikazongBillVO[] clientBills,
			CikazongBillVO[] originBills) {
		for (CikazongBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<CikazongBillVO> update = new BillUpdate<CikazongBillVO>();
		CikazongBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
