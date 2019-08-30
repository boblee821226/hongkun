package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.znjjs.plugin.bpplugin.Hk_zulin_znjjsPluginPoint;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.itf.hkjt.IHk_zulin_znjjsMaintain;

public class N_HK41_UNSAVEBILL extends AbstractPfAction<ZnjjsBillVO> {

	@Override
	protected CompareAroundProcesser<ZnjjsBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ZnjjsBillVO> processor = new CompareAroundProcesser<ZnjjsBillVO>(
				Hk_zulin_znjjsPluginPoint.UNSEND_APPROVE);
		// TODO �ڴ˴����ǰ�����
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected ZnjjsBillVO[] processBP(Object userObj,
			ZnjjsBillVO[] clientFullVOs, ZnjjsBillVO[] originBills) {
		IHk_zulin_znjjsMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_znjjsMaintain.class);
		ZnjjsBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
