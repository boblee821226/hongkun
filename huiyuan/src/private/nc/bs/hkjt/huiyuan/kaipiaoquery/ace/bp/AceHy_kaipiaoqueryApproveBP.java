package nc.bs.hkjt.huiyuan.kaipiaoquery.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHy_kaipiaoqueryApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public KaipiaoqueryBillVO[] approve(KaipiaoqueryBillVO[] clientBills,
			KaipiaoqueryBillVO[] originBills) {
		for (KaipiaoqueryBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<KaipiaoqueryBillVO> update = new BillUpdate<KaipiaoqueryBillVO>();
		KaipiaoqueryBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
