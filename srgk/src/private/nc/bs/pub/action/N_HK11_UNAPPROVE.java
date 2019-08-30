package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.plugin.bpplugin.Jd_rzmxPluginPoint;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.itf.hkjt.IJd_rzmxMaintain;

public class N_HK11_UNAPPROVE extends AbstractPfAction<RzmxBillVO> {

	@Override
	protected CompareAroundProcesser<RzmxBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<RzmxBillVO> processor = new CompareAroundProcesser<RzmxBillVO>(
				Jd_rzmxPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected RzmxBillVO[] processBP(Object userObj,
			RzmxBillVO[] clientFullVOs, RzmxBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		RzmxBillVO[] bills = null;
		try {
			IJd_rzmxMaintain operator = NCLocator.getInstance()
					.lookup(IJd_rzmxMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
