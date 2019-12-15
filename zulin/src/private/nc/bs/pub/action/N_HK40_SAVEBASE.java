package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.sdflr.plugin.bpplugin.Hk_zulin_sdflrPluginPoint;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;
import nc.itf.hkjt.IHk_zulin_sdflrMaintain;

public class N_HK40_SAVEBASE extends AbstractPfAction<SdflrBillVO> {

	@Override
	protected CompareAroundProcesser<SdflrBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SdflrBillVO> processor = null;
		SdflrBillVO[] clientFullVOs = (SdflrBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<SdflrBillVO>(
					Hk_zulin_sdflrPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<SdflrBillVO>(
					Hk_zulin_sdflrPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<SdflrBillVO> rule = null;

		return processor;
	}

	@Override
	protected SdflrBillVO[] processBP(Object userObj,
			SdflrBillVO[] clientFullVOs, SdflrBillVO[] originBills) {

		SdflrBillVO[] bills = null;
		try {
			IHk_zulin_sdflrMaintain operator = NCLocator.getInstance()
					.lookup(IHk_zulin_sdflrMaintain.class);
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
