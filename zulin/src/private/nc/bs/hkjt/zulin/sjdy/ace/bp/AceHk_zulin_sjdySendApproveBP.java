package nc.bs.hkjt.zulin.sjdy.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHk_zulin_sjdySendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public SjdyBillVO[] sendApprove(SjdyBillVO[] clientBills,
			SjdyBillVO[] originBills) {
		for (SjdyBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		SjdyBillVO[] returnVos = new BillUpdate<SjdyBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
