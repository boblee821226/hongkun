package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kaipiaoquery.plugin.bpplugin.Hy_kaipiaoqueryPluginPoint;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.itf.hkjt.IHy_kaipiaoqueryMaintain;

public class N_HK34_SAVE extends AbstractPfAction<KaipiaoqueryBillVO> {

	protected CompareAroundProcesser<KaipiaoqueryBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KaipiaoqueryBillVO> processor = new CompareAroundProcesser<KaipiaoqueryBillVO>(
				Hy_kaipiaoqueryPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<KaipiaoqueryBillVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected KaipiaoqueryBillVO[] processBP(Object userObj,
			KaipiaoqueryBillVO[] clientFullVOs, KaipiaoqueryBillVO[] originBills) {
		IHy_kaipiaoqueryMaintain operator = NCLocator.getInstance().lookup(
				IHy_kaipiaoqueryMaintain.class);
		KaipiaoqueryBillVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
