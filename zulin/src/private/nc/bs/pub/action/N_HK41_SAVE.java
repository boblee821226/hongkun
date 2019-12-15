package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.znjjs.plugin.bpplugin.Hk_zulin_znjjsPluginPoint;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.itf.hkjt.IHk_zulin_znjjsMaintain;

public class N_HK41_SAVE extends AbstractPfAction<ZnjjsBillVO> {

	protected CompareAroundProcesser<ZnjjsBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ZnjjsBillVO> processor = new CompareAroundProcesser<ZnjjsBillVO>(
				Hk_zulin_znjjsPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<ZnjjsBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected ZnjjsBillVO[] processBP(Object userObj,
			ZnjjsBillVO[] clientFullVOs, ZnjjsBillVO[] originBills) {
		IHk_zulin_znjjsMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_znjjsMaintain.class);
		ZnjjsBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
