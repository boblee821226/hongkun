package nc.bs.hkjt.arap.operate.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.arap.operate.OperateBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHk_arap_operateSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public OperateBillVO[] sendApprove(OperateBillVO[] clientBills,
			OperateBillVO[] originBills) {
		for (OperateBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		OperateBillVO[] returnVos = new BillUpdate<OperateBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
