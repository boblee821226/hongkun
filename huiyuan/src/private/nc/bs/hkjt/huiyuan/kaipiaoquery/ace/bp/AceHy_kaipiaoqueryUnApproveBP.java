package nc.bs.hkjt.huiyuan.kaipiaoquery.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_kaipiaoqueryUnApproveBP {

	public KaipiaoqueryBillVO[] unApprove(KaipiaoqueryBillVO[] clientBills,
			KaipiaoqueryBillVO[] originBills) {
		for (KaipiaoqueryBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KaipiaoqueryBillVO> update = new BillUpdate<KaipiaoqueryBillVO>();
		KaipiaoqueryBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
