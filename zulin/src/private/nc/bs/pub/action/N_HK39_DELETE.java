package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.sjdy.plugin.bpplugin.Hk_zulin_sjdyPluginPoint;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;
import nc.itf.hkjt.IHk_zulin_sjdyMaintain;

public class N_HK39_DELETE extends AbstractPfAction<SjdyBillVO> {

	@Override
	protected CompareAroundProcesser<SjdyBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SjdyBillVO> processor = new CompareAroundProcesser<SjdyBillVO>(
				Hk_zulin_sjdyPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected SjdyBillVO[] processBP(Object userObj,
			SjdyBillVO[] clientFullVOs, SjdyBillVO[] originBills) {
		IHk_zulin_sjdyMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_sjdyMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
