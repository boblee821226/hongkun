package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kadangan.plugin.bpplugin.Hy_huiyuanPluginPoint;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.itf.hkjt.IHy_huiyuanMaintain;

public class N_HK22_UNAPPROVE extends AbstractPfAction<KadanganBillVO> {

	@Override
	protected CompareAroundProcesser<KadanganBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KadanganBillVO> processor = new CompareAroundProcesser<KadanganBillVO>(
				Hy_huiyuanPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected KadanganBillVO[] processBP(Object userObj,
			KadanganBillVO[] clientFullVOs, KadanganBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		KadanganBillVO[] bills = null;
		try {
			IHy_huiyuanMaintain operator = NCLocator.getInstance()
					.lookup(IHy_huiyuanMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
