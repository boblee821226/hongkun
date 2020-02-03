package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.fapiao_fk.bill.plugin.bpplugin.Hk_fp_sk_billPluginPoint;
import nc.vo.hkjt.fapiao_sk.bill.BillSkFpBillVO;
import nc.itf.hkjt.IHk_fp_sk_billMaintain;

public class N_HK45_DELETE extends AbstractPfAction<BillSkFpBillVO> {

	@Override
	protected CompareAroundProcesser<BillSkFpBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<BillSkFpBillVO> processor = new CompareAroundProcesser<BillSkFpBillVO>(
				Hk_fp_sk_billPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected BillSkFpBillVO[] processBP(Object userObj,
			BillSkFpBillVO[] clientFullVOs, BillSkFpBillVO[] originBills) {
		IHk_fp_sk_billMaintain operator = NCLocator.getInstance().lookup(
				IHk_fp_sk_billMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
