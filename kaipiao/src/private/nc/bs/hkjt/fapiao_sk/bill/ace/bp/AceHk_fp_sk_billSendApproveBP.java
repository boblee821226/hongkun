package nc.bs.hkjt.fapiao_sk.bill.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHk_fp_sk_billSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public BillSkFpBillVO[] sendApprove(BillSkFpBillVO[] clientBills,
			BillSkFpBillVO[] originBills) {
		for (BillSkFpBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		BillSkFpBillVO[] returnVos = new BillUpdate<BillSkFpBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
