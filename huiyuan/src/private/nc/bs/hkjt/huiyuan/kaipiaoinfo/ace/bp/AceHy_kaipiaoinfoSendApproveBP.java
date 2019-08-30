package nc.bs.hkjt.huiyuan.kaipiaoinfo.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHy_kaipiaoinfoSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public KaipiaoinfoBillVO[] sendApprove(KaipiaoinfoBillVO[] clientBills,
			KaipiaoinfoBillVO[] originBills) {
		for (KaipiaoinfoBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		KaipiaoinfoBillVO[] returnVos = new BillUpdate<KaipiaoinfoBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
