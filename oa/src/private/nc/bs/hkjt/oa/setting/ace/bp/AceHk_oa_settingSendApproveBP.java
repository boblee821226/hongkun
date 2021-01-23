package nc.bs.hkjt.oa.setting.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHk_oa_settingSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public OaSettingBillVO[] sendApprove(OaSettingBillVO[] clientBills,
			OaSettingBillVO[] originBills) {
		for (OaSettingBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		OaSettingBillVO[] returnVos = new BillUpdate<OaSettingBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
