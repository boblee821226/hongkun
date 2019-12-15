package nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceHg_yyribaoUnApproveBP {

	public YyribaoBillVO[] unApprove(YyribaoBillVO[] clientBills,
			YyribaoBillVO[] originBills) {
		for (YyribaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<YyribaoBillVO> update = new BillUpdate<YyribaoBillVO>();
		YyribaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
