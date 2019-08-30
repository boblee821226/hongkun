package nc.bs.hkjt.huiyuan.cikatongji.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;

/**
 * 标准单据审核的BP
 */
public class AceHy_cikatongjiApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public CikatongjiBillVO[] approve(CikatongjiBillVO[] clientBills,
			CikatongjiBillVO[] originBills) {
		for (CikatongjiBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<CikatongjiBillVO> update = new BillUpdate<CikatongjiBillVO>();
		CikatongjiBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
