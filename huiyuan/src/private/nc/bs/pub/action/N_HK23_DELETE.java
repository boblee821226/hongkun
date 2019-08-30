package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.huanka.plugin.bpplugin.Hy_huankaPluginPoint;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.itf.hkjt.IHy_huankaMaintain;

public class N_HK23_DELETE extends AbstractPfAction<HuankaBillVO> {

	@Override
	protected CompareAroundProcesser<HuankaBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<HuankaBillVO> processor = new CompareAroundProcesser<HuankaBillVO>(
				Hy_huankaPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected HuankaBillVO[] processBP(Object userObj,
			HuankaBillVO[] clientFullVOs, HuankaBillVO[] originBills) {
		IHy_huankaMaintain operator = NCLocator.getInstance().lookup(
				IHy_huankaMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
