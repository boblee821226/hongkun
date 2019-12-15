package nc.bs.hkjt.huiyuan.cikayue.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHy_cikayueSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public CikayueBillVO[] sendApprove(CikayueBillVO[] clientBills,
			CikayueBillVO[] originBills) {
		for (CikayueBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		CikayueBillVO[] returnVos = new BillUpdate<CikayueBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
