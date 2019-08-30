package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.yyribao.plugin.bpplugin.Hg_yyribaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.itf.hkjt.IHg_yyribaoMaintain;

public class N_HK06_UNSAVEBILL extends AbstractPfAction<YyribaoBillVO> {

	@Override
	protected CompareAroundProcesser<YyribaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<YyribaoBillVO> processor = new CompareAroundProcesser<YyribaoBillVO>(
				Hg_yyribaoPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected YyribaoBillVO[] processBP(Object userObj,
			YyribaoBillVO[] clientFullVOs, YyribaoBillVO[] originBills) {
		IHg_yyribaoMaintain operator = NCLocator.getInstance().lookup(
				IHg_yyribaoMaintain.class);
		YyribaoBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
