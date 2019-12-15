package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.jiudian.ruzhangmingxi.plugin.bpplugin.Jd_rzmxPluginPoint;
import nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBillVO;
import nc.itf.hkjt.IJd_rzmxMaintain;

public class N_HK11_SAVEBASE extends AbstractPfAction<RzmxBillVO> {

	@Override
	protected CompareAroundProcesser<RzmxBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<RzmxBillVO> processor = null;
		RzmxBillVO[] clientFullVOs = (RzmxBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<RzmxBillVO>(
					Jd_rzmxPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<RzmxBillVO>(
					Jd_rzmxPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<RzmxBillVO> rule = null;

		return processor;
	}

	@Override
	protected RzmxBillVO[] processBP(Object userObj,
			RzmxBillVO[] clientFullVOs, RzmxBillVO[] originBills) {

		RzmxBillVO[] bills = null;
		try {
			IJd_rzmxMaintain operator = NCLocator.getInstance()
					.lookup(IJd_rzmxMaintain.class);
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
