package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.unit.plugin.bpplugin.Hk_arap_unitPluginPoint;
import nc.vo.hkjt.arap.unit.UnitBillVO;
import nc.itf.hkjt.IHk_arap_unitMaintain;

public class N_HK52_SAVE extends AbstractPfAction<UnitBillVO> {

	protected CompareAroundProcesser<UnitBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<UnitBillVO> processor = new CompareAroundProcesser<UnitBillVO>(
				Hk_arap_unitPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<UnitBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected UnitBillVO[] processBP(Object userObj,
			UnitBillVO[] clientFullVOs, UnitBillVO[] originBills) {
		IHk_arap_unitMaintain operator = NCLocator.getInstance().lookup(
				IHk_arap_unitMaintain.class);
		UnitBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
