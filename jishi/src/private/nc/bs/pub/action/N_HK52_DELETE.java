package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.jishi.shoudan.plugin.bpplugin.Js_shoudanPluginPoint;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.itf.hkjt.IJs_shoudanMaintain;

public class N_HK52_DELETE extends AbstractPfAction<ShoudanBillVO> {

	@Override
	protected CompareAroundProcesser<ShoudanBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ShoudanBillVO> processor = new CompareAroundProcesser<ShoudanBillVO>(
				Js_shoudanPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected ShoudanBillVO[] processBP(Object userObj,
			ShoudanBillVO[] clientFullVOs, ShoudanBillVO[] originBills) {
		IJs_shoudanMaintain operator = NCLocator.getInstance().lookup(
				IJs_shoudanMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
