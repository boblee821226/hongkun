package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikazong.plugin.bpplugin.Hy_cikazongPluginPoint;
import nc.vo.hkjt.huiyuan.cikazong.CikazongBillVO;
import nc.itf.hkjt.IHy_cikazongMaintain;

public class N_HK32_UNSAVEBILL extends AbstractPfAction<CikazongBillVO> {

	@Override
	protected CompareAroundProcesser<CikazongBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikazongBillVO> processor = new CompareAroundProcesser<CikazongBillVO>(
				Hy_cikazongPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected CikazongBillVO[] processBP(Object userObj,
			CikazongBillVO[] clientFullVOs, CikazongBillVO[] originBills) {
		IHy_cikazongMaintain operator = NCLocator.getInstance().lookup(
				IHy_cikazongMaintain.class);
		CikazongBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
