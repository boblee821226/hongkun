package nc.bs.hkjt.huiyuan.kazuofei.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_kazuofeiUnApproveBP {

	public KazuofeiBillVO[] unApprove(KazuofeiBillVO[] clientBills,
			KazuofeiBillVO[] originBills) {
		for (KazuofeiBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KazuofeiBillVO> update = new BillUpdate<KazuofeiBillVO>();
		KazuofeiBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
