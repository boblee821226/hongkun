package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.cikatongji.plugin.bpplugin.Hy_cikatongjiPluginPoint;
import nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBillVO;
import nc.itf.hkjt.IHy_cikatongjiMaintain;

public class N_HK31_SAVE extends AbstractPfAction<CikatongjiBillVO> {

	@Override
	protected CompareAroundProcesser<CikatongjiBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CikatongjiBillVO> processor = new CompareAroundProcesser<CikatongjiBillVO>(
				Hy_cikatongjiPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<CikatongjiBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected CikatongjiBillVO[] processBP(Object userObj,
			CikatongjiBillVO[] clientFullVOs, CikatongjiBillVO[] originBills) {
		IHy_cikatongjiMaintain operator = NCLocator.getInstance().lookup(
				IHy_cikatongjiMaintain.class);
		CikatongjiBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
