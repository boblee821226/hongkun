package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.fapiao_fk.bill.plugin.bpplugin.Hk_fp_sk_billPluginPoint;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.itf.hkjt.IHk_fp_sk_billMaintain;

public class N_HK45_SAVE extends AbstractPfAction<BillSkFpBillVO> {

	protected CompareAroundProcesser<BillSkFpBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<BillSkFpBillVO> processor = new CompareAroundProcesser<BillSkFpBillVO>(
				Hk_fp_sk_billPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<BillSkFpBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected BillSkFpBillVO[] processBP(Object userObj,
			BillSkFpBillVO[] clientFullVOs, BillSkFpBillVO[] originBills) {
		IHk_fp_sk_billMaintain operator = NCLocator.getInstance().lookup(
				IHk_fp_sk_billMaintain.class);
		BillSkFpBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
