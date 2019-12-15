package nc.bs.hkjt.srgk.huiguan.rsbaogao.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceHg_rsbaogaoUnSendApproveBP {

	public RsbaogaoBillVO[] unSend(RsbaogaoBillVO[] clientBills,
			RsbaogaoBillVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<RsbaogaoBillVO> update = new BillUpdate<RsbaogaoBillVO>();
		RsbaogaoBillVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(RsbaogaoBillVO[] clientBills) {
		for (RsbaogaoBillVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
