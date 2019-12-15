package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.fapiao.bill.plugin.bpplugin.Hk_fp_billPluginPoint;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;
import nc.itf.hkjt.IHk_fp_billMaintain;

public class N_HK35_SAVE extends AbstractPfAction<BillFpBillVO> {

	protected CompareAroundProcesser<BillFpBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<BillFpBillVO> processor = new CompareAroundProcesser<BillFpBillVO>(
				Hk_fp_billPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<BillFpBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected BillFpBillVO[] processBP(Object userObj,
			BillFpBillVO[] clientFullVOs, BillFpBillVO[] originBills) {
		IHk_fp_billMaintain operator = NCLocator.getInstance().lookup(
				IHk_fp_billMaintain.class);
		BillFpBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
