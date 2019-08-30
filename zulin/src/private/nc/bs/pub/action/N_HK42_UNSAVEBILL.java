package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.znjjm.plugin.bpplugin.Hk_zulin_znjjmPluginPoint;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.itf.hkjt.IHk_zulin_znjjmMaintain;

public class N_HK42_UNSAVEBILL extends AbstractPfAction<ZnjjmBillVO> {

	@Override
	protected CompareAroundProcesser<ZnjjmBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ZnjjmBillVO> processor = new CompareAroundProcesser<ZnjjmBillVO>(
				Hk_zulin_znjjmPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected ZnjjmBillVO[] processBP(Object userObj,
			ZnjjmBillVO[] clientFullVOs, ZnjjmBillVO[] originBills) {
		IHk_zulin_znjjmMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_znjjmMaintain.class);
		ZnjjmBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
