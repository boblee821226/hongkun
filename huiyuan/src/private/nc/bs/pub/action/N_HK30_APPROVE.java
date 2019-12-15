package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikayue.plugin.bpplugin.Hy_cikayuePluginPoint;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;
import nc.itf.hkjt.IHy_cikayueMaintain;

public class N_HK30_APPROVE extends AbstractPfAction<CikayueBillVO> {

	public N_HK30_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<CikayueBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikayueBillVO> processor = new CompareAroundProcesser<CikayueBillVO>(
				Hy_cikayuePluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected CikayueBillVO[] processBP(Object userObj,
			CikayueBillVO[] clientFullVOs, CikayueBillVO[] originBills) {
		CikayueBillVO[] bills = null;
		IHy_cikayueMaintain operator = NCLocator.getInstance().lookup(
				IHy_cikayueMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
