package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikainfo.plugin.bpplugin.Hy_cikainfoPluginPoint;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.itf.hkjt.IHy_cikainfoMaintain;

public class N_HK29_SAVE extends AbstractPfAction<CikainfoBillVO> {

	@Override
	protected CompareAroundProcesser<CikainfoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikainfoBillVO> processor = new CompareAroundProcesser<CikainfoBillVO>(
				Hy_cikainfoPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<CikainfoBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected CikainfoBillVO[] processBP(Object userObj,
			CikainfoBillVO[] clientFullVOs, CikainfoBillVO[] originBills) {
		IHy_cikainfoMaintain operator = NCLocator.getInstance().lookup(
				IHy_cikainfoMaintain.class);
		CikainfoBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
