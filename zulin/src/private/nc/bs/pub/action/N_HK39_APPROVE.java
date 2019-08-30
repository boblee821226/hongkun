package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.sjdy.plugin.bpplugin.Hk_zulin_sjdyPluginPoint;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;
import nc.itf.hkjt.IHk_zulin_sjdyMaintain;

public class N_HK39_APPROVE extends AbstractPfAction<SjdyBillVO> {

	public N_HK39_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<SjdyBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SjdyBillVO> processor = new CompareAroundProcesser<SjdyBillVO>(
				Hk_zulin_sjdyPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected SjdyBillVO[] processBP(Object userObj,
			SjdyBillVO[] clientFullVOs, SjdyBillVO[] originBills) {
		SjdyBillVO[] bills = null;
		IHk_zulin_sjdyMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_sjdyMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
