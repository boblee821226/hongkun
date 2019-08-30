package nc.bs.hkjt.huiyuan.cikatongji.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHy_cikatongjiUnApproveBP {

	public CikatongjiBillVO[] unApprove(CikatongjiBillVO[] clientBills,
			CikatongjiBillVO[] originBills) {
		for (CikatongjiBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<CikatongjiBillVO> update = new BillUpdate<CikatongjiBillVO>();
		CikatongjiBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
