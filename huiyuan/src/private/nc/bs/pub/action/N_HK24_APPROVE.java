package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kainfo.plugin.bpplugin.Hy_kainfoPluginPoint;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.itf.hkjt.IHy_kainfoMaintain;

public class N_HK24_APPROVE extends AbstractPfAction<KainfoBillVO> {

	public N_HK24_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<KainfoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KainfoBillVO> processor = new CompareAroundProcesser<KainfoBillVO>(
				Hy_kainfoPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected KainfoBillVO[] processBP(Object userObj,
			KainfoBillVO[] clientFullVOs, KainfoBillVO[] originBills) {
		KainfoBillVO[] bills = null;
		IHy_kainfoMaintain operator = NCLocator.getInstance().lookup(
				IHy_kainfoMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
