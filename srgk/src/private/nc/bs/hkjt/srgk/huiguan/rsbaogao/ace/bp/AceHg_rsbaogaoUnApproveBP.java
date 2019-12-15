package nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHg_rsbaogaoUnApproveBP {

	public RsbaogaoBillVO[] unApprove(RsbaogaoBillVO[] clientBills,
			RsbaogaoBillVO[] originBills) {
		for (RsbaogaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<RsbaogaoBillVO> update = new BillUpdate<RsbaogaoBillVO>();
		RsbaogaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
