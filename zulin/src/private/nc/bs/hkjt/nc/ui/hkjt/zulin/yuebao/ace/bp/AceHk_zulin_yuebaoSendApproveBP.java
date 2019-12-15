package nc.bs.hkjt.nc.ui.hkjt.zulin.yuebao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHk_zulin_yuebaoSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public YuebaoBillVO[] sendApprove(YuebaoBillVO[] clientBills,
			YuebaoBillVO[] originBills) {
		for (YuebaoBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		YuebaoBillVO[] returnVos = new BillUpdate<YuebaoBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
