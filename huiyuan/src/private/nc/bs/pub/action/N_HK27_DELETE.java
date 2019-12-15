package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kazhangwuzong.plugin.bpplugin.Hy_kazhangwuzongPluginPoint;
import nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBillVO;
import nc.itf.hkjt.IHy_kazhangwuzongMaintain;

public class N_HK27_DELETE extends AbstractPfAction<KazhangwuzongBillVO> {

	@Override
	protected CompareAroundProcesser<KazhangwuzongBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KazhangwuzongBillVO> processor = new CompareAroundProcesser<KazhangwuzongBillVO>(
				Hy_kazhangwuzongPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected KazhangwuzongBillVO[] processBP(Object userObj,
			KazhangwuzongBillVO[] clientFullVOs, KazhangwuzongBillVO[] originBills) {
		IHy_kazhangwuzongMaintain operator = NCLocator.getInstance().lookup(
				IHy_kazhangwuzongMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
