package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kazhangwu.plugin.bpplugin.Hy_kazhangwuPluginPoint;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.itf.hkjt.IHy_kazhangwuMaintain;

public class N_HK26_UNSAVEBILL extends AbstractPfAction<KazhangwuBillVO> {

	@Override
	protected CompareAroundProcesser<KazhangwuBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KazhangwuBillVO> processor = new CompareAroundProcesser<KazhangwuBillVO>(
				Hy_kazhangwuPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected KazhangwuBillVO[] processBP(Object userObj,
			KazhangwuBillVO[] clientFullVOs, KazhangwuBillVO[] originBills) {
		IHy_kazhangwuMaintain operator = NCLocator.getInstance().lookup(
				IHy_kazhangwuMaintain.class);
		KazhangwuBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
