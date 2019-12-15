package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.sjdy.plugin.bpplugin.Hk_zulin_sjdyPluginPoint;
import nc.vo.hkjt.zulin.sjdy.SjdyBillVO;
import nc.itf.hkjt.IHk_zulin_sjdyMaintain;

public class N_HK39_SAVEBASE extends AbstractPfAction<SjdyBillVO> {

	@Override
	protected CompareAroundProcesser<SjdyBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SjdyBillVO> processor = null;
		SjdyBillVO[] clientFullVOs = (SjdyBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<SjdyBillVO>(
					Hk_zulin_sjdyPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<SjdyBillVO>(
					Hk_zulin_sjdyPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<SjdyBillVO> rule = null;

		return processor;
	}

	@Override
	protected SjdyBillVO[] processBP(Object userObj,
			SjdyBillVO[] clientFullVOs, SjdyBillVO[] originBills) {

		SjdyBillVO[] bills = null;
		try {
			IHk_zulin_sjdyMaintain operator = NCLocator.getInstance()
					.lookup(IHk_zulin_sjdyMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
