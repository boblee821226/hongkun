package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.znjjs.plugin.bpplugin.Hk_zulin_znjjsPluginPoint;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.itf.hkjt.IHk_zulin_znjjsMaintain;

public class N_HK41_APPROVE extends AbstractPfAction<ZnjjsBillVO> {

	public N_HK41_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<ZnjjsBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ZnjjsBillVO> processor = new CompareAroundProcesser<ZnjjsBillVO>(
				Hk_zulin_znjjsPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected ZnjjsBillVO[] processBP(Object userObj,
			ZnjjsBillVO[] clientFullVOs, ZnjjsBillVO[] originBills) {
		ZnjjsBillVO[] bills = null;
		IHk_zulin_znjjsMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_znjjsMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
