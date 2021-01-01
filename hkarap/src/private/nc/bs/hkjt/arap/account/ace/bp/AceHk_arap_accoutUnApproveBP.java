package nc.bs.hkjt.arap.account.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.arap.account.AccountBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHk_arap_accoutUnApproveBP {

	public AccountBillVO[] unApprove(AccountBillVO[] clientBills,
			AccountBillVO[] originBills) {
		for (AccountBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AccountBillVO> update = new BillUpdate<AccountBillVO>();
		AccountBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
