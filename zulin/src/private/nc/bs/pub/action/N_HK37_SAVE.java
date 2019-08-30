package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.nc.ui.hkjt.zulin.yuebao.plugin.bpplugin.Hk_zulin_yuebaoPluginPoint;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;
import nc.itf.hkjt.IHk_zulin_yuebaoMaintain;

public class N_HK37_SAVE extends AbstractPfAction<YuebaoBillVO> {

	protected CompareAroundProcesser<YuebaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<YuebaoBillVO> processor = new CompareAroundProcesser<YuebaoBillVO>(
				Hk_zulin_yuebaoPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<YuebaoBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected YuebaoBillVO[] processBP(Object userObj,
			YuebaoBillVO[] clientFullVOs, YuebaoBillVO[] originBills) {
		IHk_zulin_yuebaoMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_yuebaoMaintain.class);
		YuebaoBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
