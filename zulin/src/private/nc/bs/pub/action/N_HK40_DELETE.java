package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.sdflr.plugin.bpplugin.Hk_zulin_sdflrPluginPoint;
import nc.vo.hkjt.zulin.sdflr.SdflrBillVO;
import nc.itf.hkjt.IHk_zulin_sdflrMaintain;

public class N_HK40_DELETE extends AbstractPfAction<SdflrBillVO> {

	@Override
	protected CompareAroundProcesser<SdflrBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<SdflrBillVO> processor = new CompareAroundProcesser<SdflrBillVO>(
				Hk_zulin_sdflrPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected SdflrBillVO[] processBP(Object userObj,
			SdflrBillVO[] clientFullVOs, SdflrBillVO[] originBills) {
		IHk_zulin_sdflrMaintain operator = NCLocator.getInstance().lookup(
				IHk_zulin_sdflrMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
