package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikayue.plugin.bpplugin.Hy_cikayuePluginPoint;
import nc.vo.hkjt.huiyuan.cikayue.CikayueBillVO;
import nc.itf.hkjt.IHy_cikayueMaintain;

public class N_HK30_UNAPPROVE extends AbstractPfAction<CikayueBillVO> {

	@Override
	protected CompareAroundProcesser<CikayueBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikayueBillVO> processor = new CompareAroundProcesser<CikayueBillVO>(
				Hy_cikayuePluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected CikayueBillVO[] processBP(Object userObj,
			CikayueBillVO[] clientFullVOs, CikayueBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		CikayueBillVO[] bills = null;
		try {
			IHy_cikayueMaintain operator = NCLocator.getInstance()
					.lookup(IHy_cikayueMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
