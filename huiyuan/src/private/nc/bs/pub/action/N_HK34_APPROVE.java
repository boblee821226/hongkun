package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kaipiaoquery.plugin.bpplugin.Hy_kaipiaoqueryPluginPoint;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.itf.hkjt.IHy_kaipiaoqueryMaintain;

public class N_HK34_APPROVE extends AbstractPfAction<KaipiaoqueryBillVO> {

	public N_HK34_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<KaipiaoqueryBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KaipiaoqueryBillVO> processor = new CompareAroundProcesser<KaipiaoqueryBillVO>(
				Hy_kaipiaoqueryPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected KaipiaoqueryBillVO[] processBP(Object userObj,
			KaipiaoqueryBillVO[] clientFullVOs, KaipiaoqueryBillVO[] originBills) {
		KaipiaoqueryBillVO[] bills = null;
		IHy_kaipiaoqueryMaintain operator = NCLocator.getInstance().lookup(
				IHy_kaipiaoqueryMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
