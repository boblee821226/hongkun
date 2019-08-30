package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.plugin.bpplugin.Jd_rzmxPluginPoint;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.itf.hkjt.IJd_rzmxMaintain;

public class N_HK11_APPROVE extends AbstractPfAction<RzmxBillVO> {

	public N_HK11_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<RzmxBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<RzmxBillVO> processor = new CompareAroundProcesser<RzmxBillVO>(
				Jd_rzmxPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected RzmxBillVO[] processBP(Object userObj,
			RzmxBillVO[] clientFullVOs, RzmxBillVO[] originBills) {
		RzmxBillVO[] bills = null;
		IJd_rzmxMaintain operator = NCLocator.getInstance().lookup(
				IJd_rzmxMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
