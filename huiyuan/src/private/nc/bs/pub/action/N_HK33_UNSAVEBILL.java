package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kaipiaoinfo.plugin.bpplugin.Hy_kaipiaoinfoPluginPoint;
import nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBillVO;
import nc.itf.hkjt.IHy_kaipiaoinfoMaintain;

public class N_HK33_UNSAVEBILL extends AbstractPfAction<KaipiaoinfoBillVO> {

	@Override
	protected CompareAroundProcesser<KaipiaoinfoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KaipiaoinfoBillVO> processor = new CompareAroundProcesser<KaipiaoinfoBillVO>(
				Hy_kaipiaoinfoPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected KaipiaoinfoBillVO[] processBP(Object userObj,
			KaipiaoinfoBillVO[] clientFullVOs, KaipiaoinfoBillVO[] originBills) {
		IHy_kaipiaoinfoMaintain operator = NCLocator.getInstance().lookup(
				IHy_kaipiaoinfoMaintain.class);
		KaipiaoinfoBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
