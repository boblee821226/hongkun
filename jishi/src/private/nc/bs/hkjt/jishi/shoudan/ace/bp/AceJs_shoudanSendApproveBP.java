package nc.bs.hkjt.jishi.shoudan.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceJs_shoudanSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public ShoudanBillVO[] sendApprove(ShoudanBillVO[] clientBills,
			ShoudanBillVO[] originBills) {
		for (ShoudanBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		ShoudanBillVO[] returnVos = new BillUpdate<ShoudanBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
