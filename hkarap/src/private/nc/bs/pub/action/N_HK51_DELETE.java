package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.account.plugin.bpplugin.Hk_arap_accoutPluginPoint;
import nc.vo.hkjt.arap.account.AccountBillVO;
import nc.itf.hkjt.IHk_arap_accoutMaintain;

public class N_HK51_DELETE extends AbstractPfAction<AccountBillVO> {

	@Override
	protected CompareAroundProcesser<AccountBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AccountBillVO> processor = new CompareAroundProcesser<AccountBillVO>(
				Hk_arap_accoutPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AccountBillVO[] processBP(Object userObj,
			AccountBillVO[] clientFullVOs, AccountBillVO[] originBills) {
		IHk_arap_accoutMaintain operator = NCLocator.getInstance().lookup(
				IHk_arap_accoutMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
