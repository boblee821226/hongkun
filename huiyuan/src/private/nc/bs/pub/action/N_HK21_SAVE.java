package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kaxing.plugin.bpplugin.Hy_kaxingPluginPoint;
import nc.vo.hkjt.huiyuan.kaxing.KaxingBillVO;
import nc.itf.hkjt.IHy_kaxingMaintain;

public class N_HK21_SAVE extends AbstractPfAction<KaxingBillVO> {

	@Override
	protected CompareAroundProcesser<KaxingBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KaxingBillVO> processor = new CompareAroundProcesser<KaxingBillVO>(
				Hy_kaxingPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<KaxingBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected KaxingBillVO[] processBP(Object userObj,
			KaxingBillVO[] clientFullVOs, KaxingBillVO[] originBills) {
		IHy_kaxingMaintain operator = NCLocator.getInstance().lookup(
				IHy_kaxingMaintain.class);
		KaxingBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
