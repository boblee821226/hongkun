package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.rsbaogao.plugin.bpplugin.Hg_rsbaogaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.itf.hkjt.IHg_rsbaogaoMaintain;

public class N_HK04_UNAPPROVE extends AbstractPfAction<RsbaogaoBillVO> {

	@Override
	protected CompareAroundProcesser<RsbaogaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<RsbaogaoBillVO> processor = new CompareAroundProcesser<RsbaogaoBillVO>(
				Hg_rsbaogaoPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected RsbaogaoBillVO[] processBP(Object userObj,
			RsbaogaoBillVO[] clientFullVOs, RsbaogaoBillVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		RsbaogaoBillVO[] bills = null;
		try {
			IHg_rsbaogaoMaintain operator = NCLocator.getInstance()
					.lookup(IHg_rsbaogaoMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
