package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kazhangwu.plugin.bpplugin.Hy_kazhangwuPluginPoint;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.itf.hkjt.IHy_kazhangwuMaintain;

public class N_HK26_UNAPPROVE extends AbstractPfAction<KazhangwuBillVO> {

	@Override
	protected CompareAroundProcesser<KazhangwuBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KazhangwuBillVO> processor = new CompareAroundProcesser<KazhangwuBillVO>(
				Hy_kazhangwuPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected KazhangwuBillVO[] processBP(Object userObj,
			KazhangwuBillVO[] clientFullVOs, KazhangwuBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		KazhangwuBillVO[] bills = null;
		try {
			IHy_kazhangwuMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kazhangwuMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
