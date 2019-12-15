package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikainfo.plugin.bpplugin.Hy_cikainfoPluginPoint;
import nc.vo.hkjt.huiyuan.cikainfo.CikainfoBillVO;
import nc.itf.hkjt.IHy_cikainfoMaintain;

public class N_HK29_SAVEBASE extends AbstractPfAction<CikainfoBillVO> {

	@Override
	protected CompareAroundProcesser<CikainfoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikainfoBillVO> processor = null;
		CikainfoBillVO[] clientFullVOs = (CikainfoBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<CikainfoBillVO>(
					Hy_cikainfoPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<CikainfoBillVO>(
					Hy_cikainfoPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<CikainfoBillVO> rule = null;

		return processor;
	}

	@Override
	protected CikainfoBillVO[] processBP(Object userObj,
			CikainfoBillVO[] clientFullVOs, CikainfoBillVO[] originBills) {

		CikainfoBillVO[] bills = null;
		try {
			IHy_cikainfoMaintain operator = NCLocator.getInstance()
					.lookup(IHy_cikainfoMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
