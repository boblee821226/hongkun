package nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHg_srdibiaoApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public SrdibiaoBillVO[] approve(SrdibiaoBillVO[] clientBills,
			SrdibiaoBillVO[] originBills) {
		for (SrdibiaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<SrdibiaoBillVO> update = new BillUpdate<SrdibiaoBillVO>();
		SrdibiaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
