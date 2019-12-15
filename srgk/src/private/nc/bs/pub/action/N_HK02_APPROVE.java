package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.hkjt.srgk.huiguan.sgshuju.plugin.bpplugin.Hg_sgshujuPluginPoint;
import nc.bs.hkjt.srgk.huiguan.sgshuju.rewrite.action.AceHg_sgshujuAfterApproveAction;
import nc.bs.hkjt.srgk.huiguan.sgshuju.rewrite.action.AceHg_sgshujuBeforeApproveAction;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.hkjt.IHg_sgshujuMaintain;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_HK02_APPROVE extends AbstractPfAction<SgshujuBillVO> {

	public N_HK02_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<SgshujuBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SgshujuBillVO> processor = new CompareAroundProcesser<SgshujuBillVO>(
				Hg_sgshujuPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		processor.addBeforeRule(new AceHg_sgshujuBeforeApproveAction());
		processor.addAfterRule(new AceHg_sgshujuAfterApproveAction());
		return processor;
	}

	@Override
	protected SgshujuBillVO[] processBP(Object userObj,
			SgshujuBillVO[] clientFullVOs, SgshujuBillVO[] originBills) {
		SgshujuBillVO[] bills = null;
		IHg_sgshujuMaintain operator = NCLocator.getInstance().lookup(
				IHg_sgshujuMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
