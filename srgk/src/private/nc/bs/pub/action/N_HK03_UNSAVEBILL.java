package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.srdibiao.plugin.bpplugin.Hg_srdibiaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBillVO;
import nc.itf.hkjt.IHg_srdibiaoMaintain;

public class N_HK03_UNSAVEBILL extends AbstractPfAction<SrdibiaoBillVO> {

	@Override
	protected CompareAroundProcesser<SrdibiaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SrdibiaoBillVO> processor = new CompareAroundProcesser<SrdibiaoBillVO>(
				Hg_srdibiaoPluginPoint.UNSEND_APPROVE);
		// TODO �ڴ˴����ǰ�����
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected SrdibiaoBillVO[] processBP(Object userObj,
			SrdibiaoBillVO[] clientFullVOs, SrdibiaoBillVO[] originBills) {
		IHg_srdibiaoMaintain operator = NCLocator.getInstance().lookup(
				IHg_srdibiaoMaintain.class);
		SrdibiaoBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
