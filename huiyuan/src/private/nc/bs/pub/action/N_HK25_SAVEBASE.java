package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kayue.plugin.bpplugin.Hy_kayuePluginPoint;
import nc.vo.hkjt.huiyuan.kayue.KayueBillVO;
import nc.itf.hkjt.IHy_kayueMaintain;

public class N_HK25_SAVEBASE extends AbstractPfAction<KayueBillVO> {

	@Override
	protected CompareAroundProcesser<KayueBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KayueBillVO> processor = null;
		KayueBillVO[] clientFullVOs = (KayueBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<KayueBillVO>(
					Hy_kayuePluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<KayueBillVO>(
					Hy_kayuePluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<KayueBillVO> rule = null;

		return processor;
	}

	@Override
	protected KayueBillVO[] processBP(Object userObj,
			KayueBillVO[] clientFullVOs, KayueBillVO[] originBills) {

		KayueBillVO[] bills = null;
		try {
			IHy_kayueMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kayueMaintain.class);
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
