package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.oa.setting.plugin.bpplugin.Hk_oa_settingPluginPoint;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;
import nc.itf.hkjt.IHk_oa_settingMaintain;

public class N_HK47_SAVE extends AbstractPfAction<OaSettingBillVO> {

	protected CompareAroundProcesser<OaSettingBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<OaSettingBillVO> processor = new CompareAroundProcesser<OaSettingBillVO>(
				Hk_oa_settingPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<OaSettingBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected OaSettingBillVO[] processBP(Object userObj,
			OaSettingBillVO[] clientFullVOs, OaSettingBillVO[] originBills) {
		IHk_oa_settingMaintain operator = NCLocator.getInstance().lookup(
				IHk_oa_settingMaintain.class);
		OaSettingBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
