package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kayue.plugin.bpplugin.Hy_kayuePluginPoint;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;
import nc.itf.hkjt.IHy_kayueMaintain;

public class N_HK25_SAVE extends AbstractPfAction<KayueBillVO> {

	@Override
	protected CompareAroundProcesser<KayueBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KayueBillVO> processor = new CompareAroundProcesser<KayueBillVO>(
				Hy_kayuePluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<KayueBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected KayueBillVO[] processBP(Object userObj,
			KayueBillVO[] clientFullVOs, KayueBillVO[] originBills) {
		IHy_kayueMaintain operator = NCLocator.getInstance().lookup(
				IHy_kayueMaintain.class);
		KayueBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
