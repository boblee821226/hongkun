package nc.bs.hkjt.arap.unit.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.arap.unit.UnitBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHk_arap_unitSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public UnitBillVO[] sendApprove(UnitBillVO[] clientBills,
			UnitBillVO[] originBills) {
		for (UnitBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		UnitBillVO[] returnVos = new BillUpdate<UnitBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
