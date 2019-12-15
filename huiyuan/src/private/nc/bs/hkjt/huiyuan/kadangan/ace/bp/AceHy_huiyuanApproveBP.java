package nc.bs.hkjt.huiyuan.kadangan.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHy_huiyuanApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public KadanganBillVO[] approve(KadanganBillVO[] clientBills,
			KadanganBillVO[] originBills) {
		for (KadanganBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KadanganBillVO> update = new BillUpdate<KadanganBillVO>();
		KadanganBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
