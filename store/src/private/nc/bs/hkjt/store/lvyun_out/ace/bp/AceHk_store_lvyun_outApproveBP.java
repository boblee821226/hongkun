package nc.bs.hkjt.store.lvyun_out.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHk_store_lvyun_outApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public LyOutStoreBillVO[] approve(LyOutStoreBillVO[] clientBills,
			LyOutStoreBillVO[] originBills) {
		for (LyOutStoreBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<LyOutStoreBillVO> update = new BillUpdate<LyOutStoreBillVO>();
		LyOutStoreBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
