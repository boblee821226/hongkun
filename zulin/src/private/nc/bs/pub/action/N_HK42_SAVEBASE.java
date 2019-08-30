package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.znjjm.plugin.bpplugin.Hk_zulin_znjjmPluginPoint;
import nc.vo.hkjt.zulin.znjjm.ZnjjmBillVO;
import nc.itf.hkjt.IHk_zulin_znjjmMaintain;

public class N_HK42_SAVEBASE extends AbstractPfAction<ZnjjmBillVO> {

	@Override
	protected CompareAroundProcesser<ZnjjmBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ZnjjmBillVO> processor = null;
		ZnjjmBillVO[] clientFullVOs = (ZnjjmBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<ZnjjmBillVO>(
					Hk_zulin_znjjmPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<ZnjjmBillVO>(
					Hk_zulin_znjjmPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<ZnjjmBillVO> rule = null;

		return processor;
	}

	@Override
	protected ZnjjmBillVO[] processBP(Object userObj,
			ZnjjmBillVO[] clientFullVOs, ZnjjmBillVO[] originBills) {

		ZnjjmBillVO[] bills = null;
		try {
			IHk_zulin_znjjmMaintain operator = NCLocator.getInstance()
					.lookup(IHk_zulin_znjjmMaintain.class);
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
