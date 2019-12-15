package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.bkfytz.plugin.bpplugin.Hk_zulin_tiaozhengPluginPoint;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;
import nc.itf.hkjt.IHk_zulin_tiaozhengMaintain;

public class N_HK44_SAVE extends AbstractPfAction<TzBillVO> {

	protected CompareAroundProcesser<TzBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<TzBillVO> processor = new CompareAroundProcesser<TzBillVO>(
				Hk_zulin_tiaozhengPluginPoint.SEND_APPROVE);
		// TODO �ڴ˴�������ǰ�����
		IRule<TzBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected TzBillVO[] processBP(Object userObj,
			TzBillVO[] clientFullVOs, TzBillVO[] originBills) {
		IHk_zulin_tiaozhengMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_tiaozhengMaintain.class);
		TzBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
