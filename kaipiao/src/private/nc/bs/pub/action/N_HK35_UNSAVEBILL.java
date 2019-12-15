package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.fapiao.bill.plugin.bpplugin.Hk_fp_billPluginPoint;
import nc.vo.hkjt.fapiao.bill.BillFpBillVO;
import nc.itf.hkjt.IHk_fp_billMaintain;

public class N_HK35_UNSAVEBILL extends AbstractPfAction<BillFpBillVO> {

	@Override
	protected CompareAroundProcesser<BillFpBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<BillFpBillVO> processor = new CompareAroundProcesser<BillFpBillVO>(
				Hk_fp_billPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected BillFpBillVO[] processBP(Object userObj,
			BillFpBillVO[] clientFullVOs, BillFpBillVO[] originBills) {
		IHk_fp_billMaintain operator = NCLocator.getInstance().lookup(
				IHk_fp_billMaintain.class);
		BillFpBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
