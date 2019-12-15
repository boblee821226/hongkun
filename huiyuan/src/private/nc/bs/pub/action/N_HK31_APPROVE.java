package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikatongji.plugin.bpplugin.Hy_cikatongjiPluginPoint;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;
import nc.itf.hkjt.IHy_cikatongjiMaintain;

public class N_HK31_APPROVE extends AbstractPfAction<CikatongjiBillVO> {

	public N_HK31_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<CikatongjiBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikatongjiBillVO> processor = new CompareAroundProcesser<CikatongjiBillVO>(
				Hy_cikatongjiPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected CikatongjiBillVO[] processBP(Object userObj,
			CikatongjiBillVO[] clientFullVOs, CikatongjiBillVO[] originBills) {
		CikatongjiBillVO[] bills = null;
		IHy_cikatongjiMaintain operator = NCLocator.getInstance().lookup(
				IHy_cikatongjiMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
