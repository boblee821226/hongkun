package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.zhangdan.plugin.bpplugin.Hg_zhangdanPluginPoint;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.itf.hkjt.IHg_zhangdanMaintain;

public class N_HK01_UNAPPROVE extends AbstractPfAction<ZhangdanBillVO> {

	@Override
	protected CompareAroundProcesser<ZhangdanBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ZhangdanBillVO> processor = new CompareAroundProcesser<ZhangdanBillVO>(
				Hg_zhangdanPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected ZhangdanBillVO[] processBP(Object userObj,
			ZhangdanBillVO[] clientFullVOs, ZhangdanBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		ZhangdanBillVO[] bills = null;
		try {
			IHg_zhangdanMaintain operator = NCLocator.getInstance()
					.lookup(IHg_zhangdanMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
