package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.jishi.shoudan.plugin.bpplugin.Js_shoudanPluginPoint;
import nc.vo.hkjt.jishi.shoudan.ShoudanBillVO;
import nc.itf.hkjt.IJs_shoudanMaintain;

public class N_HK52_UNAPPROVE extends AbstractPfAction<ShoudanBillVO> {

	@Override
	protected CompareAroundProcesser<ShoudanBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ShoudanBillVO> processor = new CompareAroundProcesser<ShoudanBillVO>(
				Js_shoudanPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected ShoudanBillVO[] processBP(Object userObj,
			ShoudanBillVO[] clientFullVOs, ShoudanBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		ShoudanBillVO[] bills = null;
		try {
			IJs_shoudanMaintain operator = NCLocator.getInstance()
					.lookup(IJs_shoudanMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
