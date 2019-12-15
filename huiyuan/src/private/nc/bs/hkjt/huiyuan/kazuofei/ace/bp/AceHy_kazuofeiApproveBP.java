package nc.bs.hkjt.huiyuan.kazuofei.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHy_kazuofeiApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public KazuofeiBillVO[] approve(KazuofeiBillVO[] clientBills,
			KazuofeiBillVO[] originBills) {
		for (KazuofeiBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KazuofeiBillVO> update = new BillUpdate<KazuofeiBillVO>();
		KazuofeiBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
