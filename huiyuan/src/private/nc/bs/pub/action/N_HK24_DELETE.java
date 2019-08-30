package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kainfo.plugin.bpplugin.Hy_kainfoPluginPoint;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.itf.hkjt.IHy_kainfoMaintain;

public class N_HK24_DELETE extends AbstractPfAction<KainfoBillVO> {

	@Override
	protected CompareAroundProcesser<KainfoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KainfoBillVO> processor = new CompareAroundProcesser<KainfoBillVO>(
				Hy_kainfoPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected KainfoBillVO[] processBP(Object userObj,
			KainfoBillVO[] clientFullVOs, KainfoBillVO[] originBills) {
		IHy_kainfoMaintain operator = NCLocator.getInstance().lookup(
				IHy_kainfoMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
