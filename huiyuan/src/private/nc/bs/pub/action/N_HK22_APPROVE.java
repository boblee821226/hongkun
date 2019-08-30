package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kadangan.plugin.bpplugin.Hy_huiyuanPluginPoint;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.itf.hkjt.IHy_huiyuanMaintain;

public class N_HK22_APPROVE extends AbstractPfAction<KadanganBillVO> {

	public N_HK22_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<KadanganBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KadanganBillVO> processor = new CompareAroundProcesser<KadanganBillVO>(
				Hy_huiyuanPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected KadanganBillVO[] processBP(Object userObj,
			KadanganBillVO[] clientFullVOs, KadanganBillVO[] originBills) {
		KadanganBillVO[] bills = null;
		IHy_huiyuanMaintain operator = NCLocator.getInstance().lookup(
				IHy_huiyuanMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
