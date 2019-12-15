package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.srdibiao.plugin.bpplugin.Hg_srdibiaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.itf.hkjt.IHg_srdibiaoMaintain;

public class N_HK03_APPROVE extends AbstractPfAction<SrdibiaoBillVO> {

	public N_HK03_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<SrdibiaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SrdibiaoBillVO> processor = new CompareAroundProcesser<SrdibiaoBillVO>(
				Hg_srdibiaoPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected SrdibiaoBillVO[] processBP(Object userObj,
			SrdibiaoBillVO[] clientFullVOs, SrdibiaoBillVO[] originBills) {
		SrdibiaoBillVO[] bills = null;
		IHg_srdibiaoMaintain operator = NCLocator.getInstance().lookup(
				IHg_srdibiaoMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
