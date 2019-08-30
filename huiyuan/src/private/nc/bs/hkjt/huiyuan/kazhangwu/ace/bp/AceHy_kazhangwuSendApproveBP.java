package nc.bs.hkjt.huiyuan.kazhangwu.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHy_kazhangwuSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public KazhangwuBillVO[] sendApprove(KazhangwuBillVO[] clientBills,
			KazhangwuBillVO[] originBills) {
		for (KazhangwuBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		KazhangwuBillVO[] returnVos = new BillUpdate<KazhangwuBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
