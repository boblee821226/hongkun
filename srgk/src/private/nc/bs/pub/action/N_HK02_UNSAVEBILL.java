package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.sgshuju.plugin.bpplugin.Hg_sgshujuPluginPoint;
import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBillVO;
import nc.itf.hkjt.IHg_sgshujuMaintain;

public class N_HK02_UNSAVEBILL extends AbstractPfAction<SgshujuBillVO> {

	@Override
	protected CompareAroundProcesser<SgshujuBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SgshujuBillVO> processor = new CompareAroundProcesser<SgshujuBillVO>(
				Hg_sgshujuPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected SgshujuBillVO[] processBP(Object userObj,
			SgshujuBillVO[] clientFullVOs, SgshujuBillVO[] originBills) {
		IHg_sgshujuMaintain operator = NCLocator.getInstance().lookup(
				IHg_sgshujuMaintain.class);
		SgshujuBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
