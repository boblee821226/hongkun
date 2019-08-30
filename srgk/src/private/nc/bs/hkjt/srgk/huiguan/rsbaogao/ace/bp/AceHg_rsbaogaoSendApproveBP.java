package nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHg_rsbaogaoSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public RsbaogaoBillVO[] sendApprove(RsbaogaoBillVO[] clientBills,
			RsbaogaoBillVO[] originBills) {
		for (RsbaogaoBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		RsbaogaoBillVO[] returnVos = new BillUpdate<RsbaogaoBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
