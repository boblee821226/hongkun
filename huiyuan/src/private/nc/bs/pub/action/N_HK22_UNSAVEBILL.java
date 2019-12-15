package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kadangan.plugin.bpplugin.Hy_huiyuanPluginPoint;
import nc.vo.hkjt.huiyuan.kadangan.KadanganBillVO;
import nc.itf.hkjt.IHy_huiyuanMaintain;

public class N_HK22_UNSAVEBILL extends AbstractPfAction<KadanganBillVO> {

	@Override
	protected CompareAroundProcesser<KadanganBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KadanganBillVO> processor = new CompareAroundProcesser<KadanganBillVO>(
				Hy_huiyuanPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected KadanganBillVO[] processBP(Object userObj,
			KadanganBillVO[] clientFullVOs, KadanganBillVO[] originBills) {
		IHy_huiyuanMaintain operator = NCLocator.getInstance().lookup(
				IHy_huiyuanMaintain.class);
		KadanganBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
