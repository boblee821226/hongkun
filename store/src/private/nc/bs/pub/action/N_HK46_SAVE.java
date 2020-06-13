package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.store.lvyun_out.plugin.bpplugin.Hk_store_lvyun_outPluginPoint;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.itf.hkjt.IHk_store_lvyun_outMaintain;

public class N_HK46_SAVE extends AbstractPfAction<LyOutStoreBillVO> {

	protected CompareAroundProcesser<LyOutStoreBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<LyOutStoreBillVO> processor = new CompareAroundProcesser<LyOutStoreBillVO>(
				Hk_store_lvyun_outPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<LyOutStoreBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected LyOutStoreBillVO[] processBP(Object userObj,
			LyOutStoreBillVO[] clientFullVOs, LyOutStoreBillVO[] originBills) {
		IHk_store_lvyun_outMaintain operator = NCLocator.getInstance().lookup(
				IHk_store_lvyun_outMaintain.class);
		LyOutStoreBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
