package nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;

/**
 * ��׼������˵�BP
 */
public class AceJd_rzmxApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public RzmxBillVO[] approve(RzmxBillVO[] clientBills,
			RzmxBillVO[] originBills) {
		for (RzmxBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<RzmxBillVO> update = new BillUpdate<RzmxBillVO>();
		RzmxBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
