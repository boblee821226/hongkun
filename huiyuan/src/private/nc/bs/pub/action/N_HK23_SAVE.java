package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.huanka.plugin.bpplugin.Hy_huankaPluginPoint;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.itf.hkjt.IHy_huankaMaintain;

public class N_HK23_SAVE extends AbstractPfAction<HuankaBillVO> {

	@Override
	protected CompareAroundProcesser<HuankaBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<HuankaBillVO> processor = new CompareAroundProcesser<HuankaBillVO>(
				Hy_huankaPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<HuankaBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected HuankaBillVO[] processBP(Object userObj,
			HuankaBillVO[] clientFullVOs, HuankaBillVO[] originBills) {
		IHy_huankaMaintain operator = NCLocator.getInstance().lookup(
				IHy_huankaMaintain.class);
		HuankaBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
