package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kaxing.plugin.bpplugin.Hy_kaxingPluginPoint;
import nc.vo.hkjt.huiyuan.kaxing.KaxingBillVO;
import nc.itf.hkjt.IHy_kaxingMaintain;

public class N_HK21_SAVEBASE extends AbstractPfAction<KaxingBillVO> {

	@Override
	protected CompareAroundProcesser<KaxingBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KaxingBillVO> processor = null;
		KaxingBillVO[] clientFullVOs = (KaxingBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<KaxingBillVO>(
					Hy_kaxingPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<KaxingBillVO>(
					Hy_kaxingPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<KaxingBillVO> rule = null;

		return processor;
	}

	@Override
	protected KaxingBillVO[] processBP(Object userObj,
			KaxingBillVO[] clientFullVOs, KaxingBillVO[] originBills) {

		KaxingBillVO[] bills = null;
		try {
			IHy_kaxingMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kaxingMaintain.class);
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
