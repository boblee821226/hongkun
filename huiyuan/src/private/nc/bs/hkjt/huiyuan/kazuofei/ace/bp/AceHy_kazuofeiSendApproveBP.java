package nc.bs.hkjt.huiyuan.kazuofei.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHy_kazuofeiSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public KazuofeiBillVO[] sendApprove(KazuofeiBillVO[] clientBills,
			KazuofeiBillVO[] originBills) {
		for (KazuofeiBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		KazuofeiBillVO[] returnVos = new BillUpdate<KazuofeiBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
