package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kainfo.plugin.bpplugin.Hy_kainfoPluginPoint;
import nc.vo.hkjt.huiyuan.kainfo.KainfoBillVO;
import nc.itf.hkjt.IHy_kainfoMaintain;

public class N_HK24_SAVEBASE extends AbstractPfAction<KainfoBillVO> {

	@Override
	protected CompareAroundProcesser<KainfoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KainfoBillVO> processor = null;
		KainfoBillVO[] clientFullVOs = (KainfoBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<KainfoBillVO>(
					Hy_kainfoPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<KainfoBillVO>(
					Hy_kainfoPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<KainfoBillVO> rule = null;

		return processor;
	}

	@Override
	protected KainfoBillVO[] processBP(Object userObj,
			KainfoBillVO[] clientFullVOs, KainfoBillVO[] originBills) {

		KainfoBillVO[] bills = null;
		try {
			IHy_kainfoMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kainfoMaintain.class);
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
