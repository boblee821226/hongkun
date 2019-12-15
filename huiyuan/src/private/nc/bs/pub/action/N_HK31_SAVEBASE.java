package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikatongji.plugin.bpplugin.Hy_cikatongjiPluginPoint;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;
import nc.itf.hkjt.IHy_cikatongjiMaintain;

public class N_HK31_SAVEBASE extends AbstractPfAction<CikatongjiBillVO> {

	@Override
	protected CompareAroundProcesser<CikatongjiBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikatongjiBillVO> processor = null;
		CikatongjiBillVO[] clientFullVOs = (CikatongjiBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<CikatongjiBillVO>(
					Hy_cikatongjiPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<CikatongjiBillVO>(
					Hy_cikatongjiPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<CikatongjiBillVO> rule = null;

		return processor;
	}

	@Override
	protected CikatongjiBillVO[] processBP(Object userObj,
			CikatongjiBillVO[] clientFullVOs, CikatongjiBillVO[] originBills) {

		CikatongjiBillVO[] bills = null;
		try {
			IHy_cikatongjiMaintain operator = NCLocator.getInstance()
					.lookup(IHy_cikatongjiMaintain.class);
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
