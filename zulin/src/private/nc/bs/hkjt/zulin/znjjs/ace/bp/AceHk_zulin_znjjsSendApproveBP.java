package nc.bs.hkjt.zulin.znjjs.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHk_zulin_znjjsSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public ZnjjsBillVO[] sendApprove(ZnjjsBillVO[] clientBills,
			ZnjjsBillVO[] originBills) {
		for (ZnjjsBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		ZnjjsBillVO[] returnVos = new BillUpdate<ZnjjsBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
