package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kadangan.plugin.bpplugin.Hy_huiyuanPluginPoint;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.itf.hkjt.IHy_huiyuanMaintain;

public class N_HK22_DELETE extends AbstractPfAction<KadanganBillVO> {

	@Override
	protected CompareAroundProcesser<KadanganBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KadanganBillVO> processor = new CompareAroundProcesser<KadanganBillVO>(
				Hy_huiyuanPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected KadanganBillVO[] processBP(Object userObj,
			KadanganBillVO[] clientFullVOs, KadanganBillVO[] originBills) {
		IHy_huiyuanMaintain operator = NCLocator.getInstance().lookup(
				IHy_huiyuanMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
