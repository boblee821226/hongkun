package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.huanka.plugin.bpplugin.Hy_huankaPluginPoint;
import nc.vo.hkjt.huiyuan.huanka.HuankaBillVO;
import nc.itf.hkjt.IHy_huankaMaintain;

public class N_HK23_APPROVE extends AbstractPfAction<HuankaBillVO> {

	public N_HK23_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<HuankaBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<HuankaBillVO> processor = new CompareAroundProcesser<HuankaBillVO>(
				Hy_huankaPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected HuankaBillVO[] processBP(Object userObj,
			HuankaBillVO[] clientFullVOs, HuankaBillVO[] originBills) {
		HuankaBillVO[] bills = null;
		IHy_huankaMaintain operator = NCLocator.getInstance().lookup(
				IHy_huankaMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
