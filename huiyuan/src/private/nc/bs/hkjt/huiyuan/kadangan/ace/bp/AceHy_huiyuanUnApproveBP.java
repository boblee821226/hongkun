package nc.bs.hkjt.huiyuan.kadangan.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_huiyuanUnApproveBP {

	public KadanganBillVO[] unApprove(KadanganBillVO[] clientBills,
			KadanganBillVO[] originBills) {
		for (KadanganBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KadanganBillVO> update = new BillUpdate<KadanganBillVO>();
		KadanganBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
