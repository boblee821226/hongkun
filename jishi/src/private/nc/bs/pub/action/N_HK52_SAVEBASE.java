package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.jishi.shoudan.plugin.bpplugin.Js_shoudanPluginPoint;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.itf.hkjt.IJs_shoudanMaintain;

public class N_HK52_SAVEBASE extends AbstractPfAction<ShoudanBillVO> {

	@Override
	protected CompareAroundProcesser<ShoudanBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ShoudanBillVO> processor = null;
		ShoudanBillVO[] clientFullVOs = (ShoudanBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<ShoudanBillVO>(
					Js_shoudanPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<ShoudanBillVO>(
					Js_shoudanPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<ShoudanBillVO> rule = null;

		return processor;
	}

	@Override
	protected ShoudanBillVO[] processBP(Object userObj,
			ShoudanBillVO[] clientFullVOs, ShoudanBillVO[] originBills) {

		ShoudanBillVO[] bills = null;
		try {
			IJs_shoudanMaintain operator = NCLocator.getInstance()
					.lookup(IJs_shoudanMaintain.class);
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
