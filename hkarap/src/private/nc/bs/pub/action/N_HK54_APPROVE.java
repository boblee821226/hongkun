package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.operate.plugin.bpplugin.Hk_arap_operatePluginPoint;
import nc.vo.hkjt.arap.operate.OperateBillVO;
import nc.itf.hkjt.IHk_arap_operateMaintain;

public class N_HK54_APPROVE extends AbstractPfAction<OperateBillVO> {

	public N_HK54_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<OperateBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<OperateBillVO> processor = new CompareAroundProcesser<OperateBillVO>(
				Hk_arap_operatePluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected OperateBillVO[] processBP(Object userObj,
			OperateBillVO[] clientFullVOs, OperateBillVO[] originBills) {
		OperateBillVO[] bills = null;
		IHk_arap_operateMaintain operator = NCLocator.getInstance().lookup(
				IHk_arap_operateMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
