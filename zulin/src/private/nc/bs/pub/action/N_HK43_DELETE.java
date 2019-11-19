package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.bkfyft.plugin.bpplugin.Hk_zulin_yuebaoPluginPoint;
import nc.vo.hkjt.zulin.yuebao.YuebaoBillVO;
import nc.itf.hkjt.IHk_zulin_yuebaoMaintain;

public class N_HK43_DELETE extends AbstractPfAction<YuebaoBillVO> {

	@Override
	protected CompareAroundProcesser<YuebaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<YuebaoBillVO> processor = new CompareAroundProcesser<YuebaoBillVO>(
				Hk_zulin_yuebaoPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected YuebaoBillVO[] processBP(Object userObj,
			YuebaoBillVO[] clientFullVOs, YuebaoBillVO[] originBills) {
		IHk_zulin_yuebaoMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_yuebaoMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
