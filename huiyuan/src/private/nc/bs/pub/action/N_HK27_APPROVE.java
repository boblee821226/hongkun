package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kazhangwuzong.plugin.bpplugin.Hy_kazhangwuzongPluginPoint;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.itf.hkjt.IHy_kazhangwuzongMaintain;

public class N_HK27_APPROVE extends AbstractPfAction<KazhangwuzongBillVO> {

	public N_HK27_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<KazhangwuzongBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KazhangwuzongBillVO> processor = new CompareAroundProcesser<KazhangwuzongBillVO>(
				Hy_kazhangwuzongPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected KazhangwuzongBillVO[] processBP(Object userObj,
			KazhangwuzongBillVO[] clientFullVOs, KazhangwuzongBillVO[] originBills) {
		KazhangwuzongBillVO[] bills = null;
		IHy_kazhangwuzongMaintain operator = NCLocator.getInstance().lookup(
				IHy_kazhangwuzongMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
