package nc.bs.hkjt.huiyuan.kazhangwuzong.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHy_kazhangwuzongSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public KazhangwuzongBillVO[] sendApprove(KazhangwuzongBillVO[] clientBills,
			KazhangwuzongBillVO[] originBills) {
		for (KazhangwuzongBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		KazhangwuzongBillVO[] returnVos = new BillUpdate<KazhangwuzongBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
