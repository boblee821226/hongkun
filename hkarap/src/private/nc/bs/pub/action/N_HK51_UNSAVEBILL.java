package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.account.plugin.bpplugin.Hk_arap_accoutPluginPoint;
import nc.vo.hkjt.arap.account.AccountBillVO;
import nc.itf.hkjt.IHk_arap_accoutMaintain;

public class N_HK51_UNSAVEBILL extends AbstractPfAction<AccountBillVO> {

	@Override
	protected CompareAroundProcesser<AccountBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AccountBillVO> processor = new CompareAroundProcesser<AccountBillVO>(
				Hk_arap_accoutPluginPoint.UNSEND_APPROVE);
		// TODO �ڴ˴����ǰ�����
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AccountBillVO[] processBP(Object userObj,
			AccountBillVO[] clientFullVOs, AccountBillVO[] originBills) {
		IHk_arap_accoutMaintain operator = NCLocator.getInstance().lookup(
				IHk_arap_accoutMaintain.class);
		AccountBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
