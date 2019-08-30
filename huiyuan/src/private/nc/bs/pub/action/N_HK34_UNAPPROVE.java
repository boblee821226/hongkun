package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kaipiaoquery.plugin.bpplugin.Hy_kaipiaoqueryPluginPoint;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.itf.hkjt.IHy_kaipiaoqueryMaintain;

public class N_HK34_UNAPPROVE extends AbstractPfAction<KaipiaoqueryBillVO> {

	@Override
	protected CompareAroundProcesser<KaipiaoqueryBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KaipiaoqueryBillVO> processor = new CompareAroundProcesser<KaipiaoqueryBillVO>(
				Hy_kaipiaoqueryPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected KaipiaoqueryBillVO[] processBP(Object userObj,
			KaipiaoqueryBillVO[] clientFullVOs, KaipiaoqueryBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		KaipiaoqueryBillVO[] bills = null;
		try {
			IHy_kaipiaoqueryMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kaipiaoqueryMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
