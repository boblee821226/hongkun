package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikazong.plugin.bpplugin.Hy_cikazongPluginPoint;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;
import nc.itf.hkjt.IHy_cikazongMaintain;

public class N_HK32_APPROVE extends AbstractPfAction<CikazongBillVO> {

	public N_HK32_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<CikazongBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikazongBillVO> processor = new CompareAroundProcesser<CikazongBillVO>(
				Hy_cikazongPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected CikazongBillVO[] processBP(Object userObj,
			CikazongBillVO[] clientFullVOs, CikazongBillVO[] originBills) {
		CikazongBillVO[] bills = null;
		IHy_cikazongMaintain operator = NCLocator.getInstance().lookup(
				IHy_cikazongMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
