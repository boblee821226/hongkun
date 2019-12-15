package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.tiaozheng.plugin.bpplugin.Hg_cctzPluginPoint;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;
import nc.itf.hkjt.IHg_cctzMaintain;

public class N_HK05_UNAPPROVE extends AbstractPfAction<CctzBillVO> {

	@Override
	protected CompareAroundProcesser<CctzBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CctzBillVO> processor = new CompareAroundProcesser<CctzBillVO>(
				Hg_cctzPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected CctzBillVO[] processBP(Object userObj,
			CctzBillVO[] clientFullVOs, CctzBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		CctzBillVO[] bills = null;
		try {
			IHg_cctzMaintain operator = NCLocator.getInstance()
					.lookup(IHg_cctzMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
