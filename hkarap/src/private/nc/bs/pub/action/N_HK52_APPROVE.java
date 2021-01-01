package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.unit.plugin.bpplugin.Hk_arap_unitPluginPoint;
import nc.vo.hkjt.arap.unit.UnitBillVO;
import nc.itf.hkjt.IHk_arap_unitMaintain;

public class N_HK52_APPROVE extends AbstractPfAction<UnitBillVO> {

	public N_HK52_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<UnitBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<UnitBillVO> processor = new CompareAroundProcesser<UnitBillVO>(
				Hk_arap_unitPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected UnitBillVO[] processBP(Object userObj,
			UnitBillVO[] clientFullVOs, UnitBillVO[] originBills) {
		UnitBillVO[] bills = null;
		IHk_arap_unitMaintain operator = NCLocator.getInstance().lookup(
				IHk_arap_unitMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
