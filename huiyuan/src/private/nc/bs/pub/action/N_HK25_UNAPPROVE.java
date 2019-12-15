package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kayue.plugin.bpplugin.Hy_kayuePluginPoint;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;
import nc.itf.hkjt.IHy_kayueMaintain;

public class N_HK25_UNAPPROVE extends AbstractPfAction<KayueBillVO> {

	@Override
	protected CompareAroundProcesser<KayueBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KayueBillVO> processor = new CompareAroundProcesser<KayueBillVO>(
				Hy_kayuePluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected KayueBillVO[] processBP(Object userObj,
			KayueBillVO[] clientFullVOs, KayueBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		KayueBillVO[] bills = null;
		try {
			IHy_kayueMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kayueMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
