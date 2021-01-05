package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.operate.plugin.bpplugin.Hk_arap_operatePluginPoint;
import nc.vo.hkjt.arap.operate.OperateBillVO;
import nc.itf.hkjt.IHk_arap_operateMaintain;

public class N_HK54_DELETE extends AbstractPfAction<OperateBillVO> {

	@Override
	protected CompareAroundProcesser<OperateBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<OperateBillVO> processor = new CompareAroundProcesser<OperateBillVO>(
				Hk_arap_operatePluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected OperateBillVO[] processBP(Object userObj,
			OperateBillVO[] clientFullVOs, OperateBillVO[] originBills) {
		IHk_arap_operateMaintain operator = NCLocator.getInstance().lookup(
				IHk_arap_operateMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
