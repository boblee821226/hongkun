package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.plugin.bpplugin.Jd_rzmxPluginPoint;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.itf.hkjt.IJd_rzmxMaintain;

public class N_HK11_SAVE extends AbstractPfAction<RzmxBillVO> {

	protected CompareAroundProcesser<RzmxBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<RzmxBillVO> processor = new CompareAroundProcesser<RzmxBillVO>(
				Jd_rzmxPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<RzmxBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected RzmxBillVO[] processBP(Object userObj,
			RzmxBillVO[] clientFullVOs, RzmxBillVO[] originBills) {
		IJd_rzmxMaintain operator = NCLocator.getInstance().lookup(
				IJd_rzmxMaintain.class);
		RzmxBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
