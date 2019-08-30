package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.hkjt.srgk.huiguan.sgshuju.plugin.bpplugin.Hg_sgshujuPluginPoint;
import nc.bs.hkjt.srgk.huiguan.sgshuju.rewrite.action.AceHg_sgshujuAfterUNApproveAction;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.hkjt.IHg_sgshujuMaintain;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_HK02_UNAPPROVE extends AbstractPfAction<SgshujuBillVO> {

	@Override
	protected CompareAroundProcesser<SgshujuBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SgshujuBillVO> processor = new CompareAroundProcesser<SgshujuBillVO>(
				Hg_sgshujuPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());
		processor.addAfterRule(new AceHg_sgshujuAfterUNApproveAction());
		return processor;
	}

	@Override
	protected SgshujuBillVO[] processBP(Object userObj,
			SgshujuBillVO[] clientFullVOs, SgshujuBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		SgshujuBillVO[] bills = null;
		try {
			IHg_sgshujuMaintain operator = NCLocator.getInstance()
					.lookup(IHg_sgshujuMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
