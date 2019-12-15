package nc.bs.hkjt.srgk.huiguan.yyribao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;

/**
 * ��׼������˵�BP
 */
public class AceHg_yyribaoApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public YyribaoBillVO[] approve(YyribaoBillVO[] clientBills,
			YyribaoBillVO[] originBills) {
		for (YyribaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<YyribaoBillVO> update = new BillUpdate<YyribaoBillVO>();
		YyribaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
