package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kazhangwuzong.plugin.bpplugin.Hy_kazhangwuzongPluginPoint;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.itf.hkjt.IHy_kazhangwuzongMaintain;

public class N_HK27_SAVEBASE extends AbstractPfAction<KazhangwuzongBillVO> {

	@Override
	protected CompareAroundProcesser<KazhangwuzongBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KazhangwuzongBillVO> processor = null;
		KazhangwuzongBillVO[] clientFullVOs = (KazhangwuzongBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<KazhangwuzongBillVO>(
					Hy_kazhangwuzongPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<KazhangwuzongBillVO>(
					Hy_kazhangwuzongPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<KazhangwuzongBillVO> rule = null;

		return processor;
	}

	@Override
	protected KazhangwuzongBillVO[] processBP(Object userObj,
			KazhangwuzongBillVO[] clientFullVOs, KazhangwuzongBillVO[] originBills) {

		KazhangwuzongBillVO[] bills = null;
		try {
			IHy_kazhangwuzongMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kazhangwuzongMaintain.class);
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
