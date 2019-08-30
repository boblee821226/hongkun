package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.sdflr.plugin.bpplugin.Hk_zulin_sdflrPluginPoint;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;
import nc.itf.hkjt.IHk_zulin_sdflrMaintain;

public class N_HK40_UNAPPROVE extends AbstractPfAction<SdflrBillVO> {

	@Override
	protected CompareAroundProcesser<SdflrBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SdflrBillVO> processor = new CompareAroundProcesser<SdflrBillVO>(
				Hk_zulin_sdflrPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected SdflrBillVO[] processBP(Object userObj,
			SdflrBillVO[] clientFullVOs, SdflrBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		SdflrBillVO[] bills = null;
		try {
			IHk_zulin_sdflrMaintain operator = NCLocator.getInstance()
					.lookup(IHk_zulin_sdflrMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
