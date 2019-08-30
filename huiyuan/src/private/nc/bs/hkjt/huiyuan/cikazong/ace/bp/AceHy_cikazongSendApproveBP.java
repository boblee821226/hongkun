package nc.bs.hkjt.huiyuan.cikazong.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHy_cikazongSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public CikazongBillVO[] sendApprove(CikazongBillVO[] clientBills,
			CikazongBillVO[] originBills) {
		for (CikazongBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		CikazongBillVO[] returnVos = new BillUpdate<CikazongBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
