package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.store.lvyun_out.plugin.bpplugin.Hk_store_lvyun_outPluginPoint;
import nc.vo.hkjt.store.lvyun.out.LyOutStoreBillVO;
import nc.itf.hkjt.IHk_store_lvyun_outMaintain;

public class N_HK46_DELETE extends AbstractPfAction<LyOutStoreBillVO> {

	@Override
	protected CompareAroundProcesser<LyOutStoreBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<LyOutStoreBillVO> processor = new CompareAroundProcesser<LyOutStoreBillVO>(
				Hk_store_lvyun_outPluginPoint.SCRIPT_DELETE);
		// TODO �ڴ˴����ǰ�����
		return processor;
	}

	@Override
	protected LyOutStoreBillVO[] processBP(Object userObj,
			LyOutStoreBillVO[] clientFullVOs, LyOutStoreBillVO[] originBills) {
		IHk_store_lvyun_outMaintain operator = NCLocator.getInstance().lookup(
				IHk_store_lvyun_outMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
