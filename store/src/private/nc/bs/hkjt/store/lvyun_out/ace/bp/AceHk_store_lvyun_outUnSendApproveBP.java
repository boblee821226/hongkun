package nc.bs.hkjt.store.lvyun_out.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreDVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceHk_store_lvyun_outUnSendApproveBP {

	public LyOutStoreBillVO[] unSend(LyOutStoreBillVO[] clientBills,
			LyOutStoreBillVO[] originBills) throws BusinessException {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<LyOutStoreBillVO> update = new BillUpdate<LyOutStoreBillVO>();
		LyOutStoreBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(LyOutStoreBillVO[] clientBills) {
		for (LyOutStoreBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
			/**
			 * 2020��7��30��14:52:57
			 * ���ҳǩ��
			 */
			LyOutStoreDVO[] dVOs = (LyOutStoreDVO[])clientBill.getChildren(new LyOutStoreDVO().getMetaData());
			for (LyOutStoreDVO dVO : dVOs) {
				dVO.setStatus(VOStatus.DELETED);
			}
			/***END***/
		}
	}
}
