package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.yyribao.plugin.bpplugin.Hg_yyribaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.itf.hkjt.IHg_yyribaoMaintain;

public class N_HK06_UNAPPROVE extends AbstractPfAction<YyribaoBillVO> {

	@Override
	protected CompareAroundProcesser<YyribaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<YyribaoBillVO> processor = new CompareAroundProcesser<YyribaoBillVO>(
				Hg_yyribaoPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected YyribaoBillVO[] processBP(Object userObj,
			YyribaoBillVO[] clientFullVOs, YyribaoBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		YyribaoBillVO[] bills = null;
		try {
			IHg_yyribaoMaintain operator = NCLocator.getInstance()
					.lookup(IHg_yyribaoMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
