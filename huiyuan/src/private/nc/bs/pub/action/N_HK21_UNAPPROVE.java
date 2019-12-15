package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kaxing.plugin.bpplugin.Hy_kaxingPluginPoint;
import nc.vo.hkjt.huiyuan.kaxing.KaxingBillVO;
import nc.itf.hkjt.IHy_kaxingMaintain;

public class N_HK21_UNAPPROVE extends AbstractPfAction<KaxingBillVO> {

	@Override
	protected CompareAroundProcesser<KaxingBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KaxingBillVO> processor = new CompareAroundProcesser<KaxingBillVO>(
				Hy_kaxingPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected KaxingBillVO[] processBP(Object userObj,
			KaxingBillVO[] clientFullVOs, KaxingBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		KaxingBillVO[] bills = null;
		try {
			IHy_kaxingMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kaxingMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
