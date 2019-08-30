package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.zhangdan.plugin.bpplugin.Hg_zhangdanPluginPoint;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.itf.hkjt.IHg_zhangdanMaintain;

public class N_HK01_SAVE extends AbstractPfAction<ZhangdanBillVO> {

	protected CompareAroundProcesser<ZhangdanBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ZhangdanBillVO> processor = new CompareAroundProcesser<ZhangdanBillVO>(
				Hg_zhangdanPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<ZhangdanBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected ZhangdanBillVO[] processBP(Object userObj,
			ZhangdanBillVO[] clientFullVOs, ZhangdanBillVO[] originBills) {
		IHg_zhangdanMaintain operator = NCLocator.getInstance().lookup(
				IHg_zhangdanMaintain.class);
		ZhangdanBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
