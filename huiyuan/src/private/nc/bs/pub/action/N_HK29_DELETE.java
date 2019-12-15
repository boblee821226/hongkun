package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikainfo.plugin.bpplugin.Hy_cikainfoPluginPoint;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.itf.hkjt.IHy_cikainfoMaintain;

public class N_HK29_DELETE extends AbstractPfAction<CikainfoBillVO> {

	@Override
	protected CompareAroundProcesser<CikainfoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikainfoBillVO> processor = new CompareAroundProcesser<CikainfoBillVO>(
				Hy_cikainfoPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected CikainfoBillVO[] processBP(Object userObj,
			CikainfoBillVO[] clientFullVOs, CikainfoBillVO[] originBills) {
		IHy_cikainfoMaintain operator = NCLocator.getInstance().lookup(
				IHy_cikainfoMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
