package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.oa.setting.plugin.bpplugin.Hk_oa_settingPluginPoint;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;
import nc.itf.hkjt.IHk_oa_settingMaintain;

public class N_HK47_UNAPPROVE extends AbstractPfAction<OaSettingBillVO> {

	@Override
	protected CompareAroundProcesser<OaSettingBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<OaSettingBillVO> processor = new CompareAroundProcesser<OaSettingBillVO>(
				Hk_oa_settingPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected OaSettingBillVO[] processBP(Object userObj,
			OaSettingBillVO[] clientFullVOs, OaSettingBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		OaSettingBillVO[] bills = null;
		try {
			IHk_oa_settingMaintain operator = NCLocator.getInstance()
					.lookup(IHk_oa_settingMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
