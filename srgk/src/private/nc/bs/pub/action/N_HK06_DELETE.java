package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.yyribao.plugin.bpplugin.Hg_yyribaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBillVO;
import nc.itf.hkjt.IHg_yyribaoMaintain;

public class N_HK06_DELETE extends AbstractPfAction<YyribaoBillVO> {

	@Override
	protected CompareAroundProcesser<YyribaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<YyribaoBillVO> processor = new CompareAroundProcesser<YyribaoBillVO>(
				Hg_yyribaoPluginPoint.SCRIPT_DELETE);
		// TODO �ڴ˴����ǰ�����
		return processor;
	}

	@Override
	protected YyribaoBillVO[] processBP(Object userObj,
			YyribaoBillVO[] clientFullVOs, YyribaoBillVO[] originBills) {
		IHg_yyribaoMaintain operator = NCLocator.getInstance().lookup(
				IHg_yyribaoMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
