package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.oa.setting.plugin.bpplugin.Hk_oa_settingPluginPoint;
import nc.vo.hkjt.oa.setting.OaSettingBillVO;
import nc.itf.hkjt.IHk_oa_settingMaintain;

public class N_HK47_SAVEBASE extends AbstractPfAction<OaSettingBillVO> {

	@Override
	protected CompareAroundProcesser<OaSettingBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<OaSettingBillVO> processor = null;
		OaSettingBillVO[] clientFullVOs = (OaSettingBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<OaSettingBillVO>(
					Hk_oa_settingPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<OaSettingBillVO>(
					Hk_oa_settingPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<OaSettingBillVO> rule = null;

		return processor;
	}

	@Override
	protected OaSettingBillVO[] processBP(Object userObj,
			OaSettingBillVO[] clientFullVOs, OaSettingBillVO[] originBills) {

		OaSettingBillVO[] bills = null;
		try {
			IHk_oa_settingMaintain operator = NCLocator.getInstance()
					.lookup(IHk_oa_settingMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
