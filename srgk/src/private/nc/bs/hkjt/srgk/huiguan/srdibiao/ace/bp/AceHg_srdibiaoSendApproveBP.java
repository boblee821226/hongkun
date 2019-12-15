package nc.bs.hkjt.srgk.huiguan.srdibiao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHg_srdibiaoSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public SrdibiaoBillVO[] sendApprove(SrdibiaoBillVO[] clientBills,
			SrdibiaoBillVO[] originBills) {
		for (SrdibiaoBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		SrdibiaoBillVO[] returnVos = new BillUpdate<SrdibiaoBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
