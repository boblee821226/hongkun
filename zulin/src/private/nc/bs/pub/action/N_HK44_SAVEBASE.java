package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.bkfytz.plugin.bpplugin.Hk_zulin_tiaozhengPluginPoint;
import nc.vo.hkjt.zulin.tiaozheng.TzBillVO;
import nc.itf.hkjt.IHk_zulin_tiaozhengMaintain;

public class N_HK44_SAVEBASE extends AbstractPfAction<TzBillVO> {

	@Override
	protected CompareAroundProcesser<TzBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<TzBillVO> processor = null;
		TzBillVO[] clientFullVOs = (TzBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<TzBillVO>(
					Hk_zulin_tiaozhengPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<TzBillVO>(
					Hk_zulin_tiaozhengPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<TzBillVO> rule = null;

		return processor;
	}

	@Override
	protected TzBillVO[] processBP(Object userObj,
			TzBillVO[] clientFullVOs, TzBillVO[] originBills) {

		TzBillVO[] bills = null;
		try {
			IHk_zulin_tiaozhengMaintain operator = NCLocator.getInstance()
					.lookup(IHk_zulin_tiaozhengMaintain.class);
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
