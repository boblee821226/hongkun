package nc.bs.hkjt.arap.account.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.arap.account.AccountBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceHk_arap_accoutSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AccountBillVO[] sendApprove(AccountBillVO[] clientBills,
			AccountBillVO[] originBills) {
		for (AccountBillVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AccountBillVO[] returnVos = new BillUpdate<AccountBillVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
