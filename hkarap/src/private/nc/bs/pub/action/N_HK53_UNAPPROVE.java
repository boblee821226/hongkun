package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.arap.bill.plugin.bpplugin.Hk_arap_billPluginPoint;
import nc.vo.hkjt.arap.bill.BillBillVO;
import nc.itf.hkjt.IHk_arap_billMaintain;

public class N_HK53_UNAPPROVE extends AbstractPfAction<BillBillVO> {

	@Override
	protected CompareAroundProcesser<BillBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<BillBillVO> processor = new CompareAroundProcesser<BillBillVO>(
				Hk_arap_billPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected BillBillVO[] processBP(Object userObj,
			BillBillVO[] clientFullVOs, BillBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillBillVO[] bills = null;
		try {
			IHk_arap_billMaintain operator = NCLocator.getInstance()
					.lookup(IHk_arap_billMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
