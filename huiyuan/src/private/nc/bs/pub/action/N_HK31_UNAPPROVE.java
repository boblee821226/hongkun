package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikatongji.plugin.bpplugin.Hy_cikatongjiPluginPoint;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;
import nc.itf.hkjt.IHy_cikatongjiMaintain;

public class N_HK31_UNAPPROVE extends AbstractPfAction<CikatongjiBillVO> {

	@Override
	protected CompareAroundProcesser<CikatongjiBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikatongjiBillVO> processor = new CompareAroundProcesser<CikatongjiBillVO>(
				Hy_cikatongjiPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected CikatongjiBillVO[] processBP(Object userObj,
			CikatongjiBillVO[] clientFullVOs, CikatongjiBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		CikatongjiBillVO[] bills = null;
		try {
			IHy_cikatongjiMaintain operator = NCLocator.getInstance()
					.lookup(IHy_cikatongjiMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
