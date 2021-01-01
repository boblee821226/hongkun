package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.account.plugin.bpplugin.Hk_arap_accoutPluginPoint;
import nc.vo.hkjt.arap.account.AccountBillVO;
import nc.itf.hkjt.IHk_arap_accoutMaintain;

public class N_HK51_APPROVE extends AbstractPfAction<AccountBillVO> {

	public N_HK51_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AccountBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AccountBillVO> processor = new CompareAroundProcesser<AccountBillVO>(
				Hk_arap_accoutPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AccountBillVO[] processBP(Object userObj,
			AccountBillVO[] clientFullVOs, AccountBillVO[] originBills) {
		AccountBillVO[] bills = null;
		IHk_arap_accoutMaintain operator = NCLocator.getInstance().lookup(
				IHk_arap_accoutMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
