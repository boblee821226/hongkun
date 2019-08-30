package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.znjjm.plugin.bpplugin.Hk_zulin_znjjmPluginPoint;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.itf.hkjt.IHk_zulin_znjjmMaintain;

public class N_HK42_SAVE extends AbstractPfAction<ZnjjmBillVO> {

	protected CompareAroundProcesser<ZnjjmBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ZnjjmBillVO> processor = new CompareAroundProcesser<ZnjjmBillVO>(
				Hk_zulin_znjjmPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<ZnjjmBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected ZnjjmBillVO[] processBP(Object userObj,
			ZnjjmBillVO[] clientFullVOs, ZnjjmBillVO[] originBills) {
		IHk_zulin_znjjmMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_znjjmMaintain.class);
		ZnjjmBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
