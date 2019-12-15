package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kazuofei.plugin.bpplugin.Hy_kazuofeiPluginPoint;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;
import nc.itf.hkjt.IHy_kazuofeiMaintain;

public class N_HK28_APPROVE extends AbstractPfAction<KazuofeiBillVO> {

	public N_HK28_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<KazuofeiBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KazuofeiBillVO> processor = new CompareAroundProcesser<KazuofeiBillVO>(
				Hy_kazuofeiPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected KazuofeiBillVO[] processBP(Object userObj,
			KazuofeiBillVO[] clientFullVOs, KazuofeiBillVO[] originBills) {
		KazuofeiBillVO[] bills = null;
		IHy_kazuofeiMaintain operator = NCLocator.getInstance().lookup(
				IHy_kazuofeiMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
