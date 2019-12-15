package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.sgshuju.plugin.bpplugin.Hg_sgshujuPluginPoint;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.itf.hkjt.IHg_sgshujuMaintain;

public class N_HK02_SAVE extends AbstractPfAction<SgshujuBillVO> {

	protected CompareAroundProcesser<SgshujuBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SgshujuBillVO> processor = new CompareAroundProcesser<SgshujuBillVO>(
				Hg_sgshujuPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<SgshujuBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected SgshujuBillVO[] processBP(Object userObj,
			SgshujuBillVO[] clientFullVOs, SgshujuBillVO[] originBills) {
		IHg_sgshujuMaintain operator = NCLocator.getInstance().lookup(
				IHg_sgshujuMaintain.class);
		SgshujuBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
