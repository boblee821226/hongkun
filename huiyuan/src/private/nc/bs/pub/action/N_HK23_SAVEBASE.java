package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.huanka.plugin.bpplugin.Hy_huankaPluginPoint;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.itf.hkjt.IHy_huankaMaintain;

public class N_HK23_SAVEBASE extends AbstractPfAction<HuankaBillVO> {

	@Override
	protected CompareAroundProcesser<HuankaBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<HuankaBillVO> processor = null;
		HuankaBillVO[] clientFullVOs = (HuankaBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<HuankaBillVO>(
					Hy_huankaPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<HuankaBillVO>(
					Hy_huankaPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<HuankaBillVO> rule = null;

		return processor;
	}

	@Override
	protected HuankaBillVO[] processBP(Object userObj,
			HuankaBillVO[] clientFullVOs, HuankaBillVO[] originBills) {

		HuankaBillVO[] bills = null;
		try {
			IHy_huankaMaintain operator = NCLocator.getInstance()
					.lookup(IHy_huankaMaintain.class);
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
