package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kazhangwu.plugin.bpplugin.Hy_kazhangwuPluginPoint;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.itf.hkjt.IHy_kazhangwuMaintain;

public class N_HK26_APPROVE extends AbstractPfAction<KazhangwuBillVO> {

	public N_HK26_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<KazhangwuBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KazhangwuBillVO> processor = new CompareAroundProcesser<KazhangwuBillVO>(
				Hy_kazhangwuPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected KazhangwuBillVO[] processBP(Object userObj,
			KazhangwuBillVO[] clientFullVOs, KazhangwuBillVO[] originBills) {
		KazhangwuBillVO[] bills = null;
		IHy_kazhangwuMaintain operator = NCLocator.getInstance().lookup(
				IHy_kazhangwuMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
