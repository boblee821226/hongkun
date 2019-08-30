package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kaipiaoinfo.plugin.bpplugin.Hy_kaipiaoinfoPluginPoint;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.itf.hkjt.IHy_kaipiaoinfoMaintain;

public class N_HK33_APPROVE extends AbstractPfAction<KaipiaoinfoBillVO> {

	public N_HK33_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<KaipiaoinfoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KaipiaoinfoBillVO> processor = new CompareAroundProcesser<KaipiaoinfoBillVO>(
				Hy_kaipiaoinfoPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected KaipiaoinfoBillVO[] processBP(Object userObj,
			KaipiaoinfoBillVO[] clientFullVOs, KaipiaoinfoBillVO[] originBills) {
		KaipiaoinfoBillVO[] bills = null;
		IHy_kaipiaoinfoMaintain operator = NCLocator.getInstance().lookup(
				IHy_kaipiaoinfoMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
