package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikatongji.plugin.bpplugin.Hy_cikatongjiPluginPoint;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;
import nc.itf.hkjt.IHy_cikatongjiMaintain;

public class N_HK31_DELETE extends AbstractPfAction<CikatongjiBillVO> {

	@Override
	protected CompareAroundProcesser<CikatongjiBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikatongjiBillVO> processor = new CompareAroundProcesser<CikatongjiBillVO>(
				Hy_cikatongjiPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected CikatongjiBillVO[] processBP(Object userObj,
			CikatongjiBillVO[] clientFullVOs, CikatongjiBillVO[] originBills) {
		IHy_cikatongjiMaintain operator = NCLocator.getInstance().lookup(
				IHy_cikatongjiMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
