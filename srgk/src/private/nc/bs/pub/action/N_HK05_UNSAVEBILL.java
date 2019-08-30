package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.tiaozheng.plugin.bpplugin.Hg_cctzPluginPoint;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;
import nc.itf.hkjt.IHg_cctzMaintain;

public class N_HK05_UNSAVEBILL extends AbstractPfAction<CctzBillVO> {

	@Override
	protected CompareAroundProcesser<CctzBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CctzBillVO> processor = new CompareAroundProcesser<CctzBillVO>(
				Hg_cctzPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected CctzBillVO[] processBP(Object userObj,
			CctzBillVO[] clientFullVOs, CctzBillVO[] originBills) {
		IHg_cctzMaintain operator = NCLocator.getInstance().lookup(
				IHg_cctzMaintain.class);
		CctzBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
