package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikayue.plugin.bpplugin.Hy_cikayuePluginPoint;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;
import nc.itf.hkjt.IHy_cikayueMaintain;

public class N_HK30_SAVE extends AbstractPfAction<CikayueBillVO> {

	@Override
	protected CompareAroundProcesser<CikayueBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikayueBillVO> processor = new CompareAroundProcesser<CikayueBillVO>(
				Hy_cikayuePluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<CikayueBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected CikayueBillVO[] processBP(Object userObj,
			CikayueBillVO[] clientFullVOs, CikayueBillVO[] originBills) {
		IHy_cikayueMaintain operator = NCLocator.getInstance().lookup(
				IHy_cikayueMaintain.class);
		CikayueBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
