package nc.bs.hkjt.zulin.znjjs.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHk_zulin_znjjsApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public ZnjjsBillVO[] approve(ZnjjsBillVO[] clientBills,
			ZnjjsBillVO[] originBills) {
		for (ZnjjsBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<ZnjjsBillVO> update = new BillUpdate<ZnjjsBillVO>();
		ZnjjsBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
