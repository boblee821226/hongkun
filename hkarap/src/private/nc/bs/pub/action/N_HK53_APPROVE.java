package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.bill.plugin.bpplugin.Hk_arap_billPluginPoint;
import nc.vo.hkjt.arap.bill.BillBillVO;
import nc.itf.hkjt.IHk_arap_billMaintain;

public class N_HK53_APPROVE extends AbstractPfAction<BillBillVO> {

	public N_HK53_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<BillBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<BillBillVO> processor = new CompareAroundProcesser<BillBillVO>(
				Hk_arap_billPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected BillBillVO[] processBP(Object userObj,
			BillBillVO[] clientFullVOs, BillBillVO[] originBills) {
		BillBillVO[] bills = null;
		IHk_arap_billMaintain operator = NCLocator.getInstance().lookup(
				IHk_arap_billMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
