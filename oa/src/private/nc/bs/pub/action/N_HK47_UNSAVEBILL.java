package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.oa.setting.plugin.bpplugin.Hk_oa_settingPluginPoint;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;
import nc.itf.hkjt.IHk_oa_settingMaintain;

public class N_HK47_UNSAVEBILL extends AbstractPfAction<OaSettingBillVO> {

	@Override
	protected CompareAroundProcesser<OaSettingBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<OaSettingBillVO> processor = new CompareAroundProcesser<OaSettingBillVO>(
				Hk_oa_settingPluginPoint.UNSEND_APPROVE);
		// TODO �ڴ˴����ǰ�����
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected OaSettingBillVO[] processBP(Object userObj,
			OaSettingBillVO[] clientFullVOs, OaSettingBillVO[] originBills) {
		IHk_oa_settingMaintain operator = NCLocator.getInstance().lookup(
				IHk_oa_settingMaintain.class);
		OaSettingBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
