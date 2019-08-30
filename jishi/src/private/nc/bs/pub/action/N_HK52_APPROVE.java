package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.jishi.shoudan.plugin.bpplugin.Js_shoudanPluginPoint;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.itf.hkjt.IJs_shoudanMaintain;

public class N_HK52_APPROVE extends AbstractPfAction<ShoudanBillVO> {

	public N_HK52_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<ShoudanBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ShoudanBillVO> processor = new CompareAroundProcesser<ShoudanBillVO>(
				Js_shoudanPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected ShoudanBillVO[] processBP(Object userObj,
			ShoudanBillVO[] clientFullVOs, ShoudanBillVO[] originBills) {
		ShoudanBillVO[] bills = null;
		IJs_shoudanMaintain operator = NCLocator.getInstance().lookup(
				IJs_shoudanMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
