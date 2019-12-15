package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.tiaozheng.plugin.bpplugin.Hk_zulin_tiaozhengPluginPoint;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;
import nc.itf.hkjt.IHk_zulin_tiaozhengMaintain;

public class N_HK38_APPROVE extends AbstractPfAction<TzBillVO> {

	public N_HK38_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<TzBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<TzBillVO> processor = new CompareAroundProcesser<TzBillVO>(
				Hk_zulin_tiaozhengPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected TzBillVO[] processBP(Object userObj,
			TzBillVO[] clientFullVOs, TzBillVO[] originBills) {
		TzBillVO[] bills = null;
		IHk_zulin_tiaozhengMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_tiaozhengMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
