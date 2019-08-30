package nc.bs.hkjt.zulin.znjjm.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHk_zulin_znjjmSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public ZnjjmBillVO[] sendApprove(ZnjjmBillVO[] clientBills,
			ZnjjmBillVO[] originBills) {
		for (ZnjjmBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		ZnjjmBillVO[] returnVos = new BillUpdate<ZnjjmBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
