package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.unit.plugin.bpplugin.Hk_arap_unitPluginPoint;
import nc.vo.hkjt.arap.unit.UnitBillVO;
import nc.itf.hkjt.IHk_arap_unitMaintain;

public class N_HK52_DELETE extends AbstractPfAction<UnitBillVO> {

	@Override
	protected CompareAroundProcesser<UnitBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<UnitBillVO> processor = new CompareAroundProcesser<UnitBillVO>(
				Hk_arap_unitPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected UnitBillVO[] processBP(Object userObj,
			UnitBillVO[] clientFullVOs, UnitBillVO[] originBills) {
		IHk_arap_unitMaintain operator = NCLocator.getInstance().lookup(
				IHk_arap_unitMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
