package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.operate.plugin.bpplugin.Hk_arap_operatePluginPoint;
import nc.vo.hkjt.arap.operate.OperateBillVO;
import nc.itf.hkjt.IHk_arap_operateMaintain;

public class N_HK54_SAVEBASE extends AbstractPfAction<OperateBillVO> {

	@Override
	protected CompareAroundProcesser<OperateBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<OperateBillVO> processor = null;
		OperateBillVO[] clientFullVOs = (OperateBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<OperateBillVO>(
					Hk_arap_operatePluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<OperateBillVO>(
					Hk_arap_operatePluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<OperateBillVO> rule = null;

		return processor;
	}

	@Override
	protected OperateBillVO[] processBP(Object userObj,
			OperateBillVO[] clientFullVOs, OperateBillVO[] originBills) {

		OperateBillVO[] bills = null;
		try {
			IHk_arap_operateMaintain operator = NCLocator.getInstance()
					.lookup(IHk_arap_operateMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
