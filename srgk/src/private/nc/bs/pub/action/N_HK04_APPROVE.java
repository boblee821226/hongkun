package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.rsbaogao.plugin.bpplugin.Hg_rsbaogaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.itf.hkjt.IHg_rsbaogaoMaintain;

public class N_HK04_APPROVE extends AbstractPfAction<RsbaogaoBillVO> {

	public N_HK04_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<RsbaogaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<RsbaogaoBillVO> processor = new CompareAroundProcesser<RsbaogaoBillVO>(
				Hg_rsbaogaoPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected RsbaogaoBillVO[] processBP(Object userObj,
			RsbaogaoBillVO[] clientFullVOs, RsbaogaoBillVO[] originBills) {
		RsbaogaoBillVO[] bills = null;
		IHg_rsbaogaoMaintain operator = NCLocator.getInstance().lookup(
				IHg_rsbaogaoMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
