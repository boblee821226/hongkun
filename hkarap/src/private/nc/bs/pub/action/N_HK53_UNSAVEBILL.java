package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.bill.plugin.bpplugin.Hk_arap_billPluginPoint;
import nc.vo.hkjt.arap.bill.BillBillVO;
import nc.itf.hkjt.IHk_arap_billMaintain;

public class N_HK53_UNSAVEBILL extends AbstractPfAction<BillBillVO> {

	@Override
	protected CompareAroundProcesser<BillBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<BillBillVO> processor = new CompareAroundProcesser<BillBillVO>(
				Hk_arap_billPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected BillBillVO[] processBP(Object userObj,
			BillBillVO[] clientFullVOs, BillBillVO[] originBills) {
		IHk_arap_billMaintain operator = NCLocator.getInstance().lookup(
				IHk_arap_billMaintain.class);
		BillBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
