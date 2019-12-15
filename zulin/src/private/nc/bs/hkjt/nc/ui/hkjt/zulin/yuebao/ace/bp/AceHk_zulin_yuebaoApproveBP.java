package nc.bs.hkjt.nc.ui.hkjt.zulin.yuebao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHk_zulin_yuebaoApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public YuebaoBillVO[] approve(YuebaoBillVO[] clientBills,
			YuebaoBillVO[] originBills) {
		for (YuebaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<YuebaoBillVO> update = new BillUpdate<YuebaoBillVO>();
		YuebaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
