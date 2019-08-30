package nc.bs.hkjt.huiyuan.kayue.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHy_kayueApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public KayueBillVO[] approve(KayueBillVO[] clientBills,
			KayueBillVO[] originBills) {
		for (KayueBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KayueBillVO> update = new BillUpdate<KayueBillVO>();
		KayueBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
