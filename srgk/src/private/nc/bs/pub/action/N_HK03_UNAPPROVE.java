package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.srdibiao.plugin.bpplugin.Hg_srdibiaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.itf.hkjt.IHg_srdibiaoMaintain;

public class N_HK03_UNAPPROVE extends AbstractPfAction<SrdibiaoBillVO> {

	@Override
	protected CompareAroundProcesser<SrdibiaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SrdibiaoBillVO> processor = new CompareAroundProcesser<SrdibiaoBillVO>(
				Hg_srdibiaoPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected SrdibiaoBillVO[] processBP(Object userObj,
			SrdibiaoBillVO[] clientFullVOs, SrdibiaoBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		SrdibiaoBillVO[] bills = null;
		try {
			IHg_srdibiaoMaintain operator = NCLocator.getInstance()
					.lookup(IHg_srdibiaoMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
