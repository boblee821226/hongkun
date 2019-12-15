package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kazhangwuzong.plugin.bpplugin.Hy_kazhangwuzongPluginPoint;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.itf.hkjt.IHy_kazhangwuzongMaintain;

public class N_HK27_UNAPPROVE extends AbstractPfAction<KazhangwuzongBillVO> {

	@Override
	protected CompareAroundProcesser<KazhangwuzongBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KazhangwuzongBillVO> processor = new CompareAroundProcesser<KazhangwuzongBillVO>(
				Hy_kazhangwuzongPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected KazhangwuzongBillVO[] processBP(Object userObj,
			KazhangwuzongBillVO[] clientFullVOs, KazhangwuzongBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		KazhangwuzongBillVO[] bills = null;
		try {
			IHy_kazhangwuzongMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kazhangwuzongMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
