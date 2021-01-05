package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.operate.plugin.bpplugin.Hk_arap_operatePluginPoint;
import nc.vo.hkjt.arap.operate.OperateBillVO;
import nc.itf.hkjt.IHk_arap_operateMaintain;

public class N_HK54_UNAPPROVE extends AbstractPfAction<OperateBillVO> {

	@Override
	protected CompareAroundProcesser<OperateBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<OperateBillVO> processor = new CompareAroundProcesser<OperateBillVO>(
				Hk_arap_operatePluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected OperateBillVO[] processBP(Object userObj,
			OperateBillVO[] clientFullVOs, OperateBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		OperateBillVO[] bills = null;
		try {
			IHk_arap_operateMaintain operator = NCLocator.getInstance()
					.lookup(IHk_arap_operateMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
