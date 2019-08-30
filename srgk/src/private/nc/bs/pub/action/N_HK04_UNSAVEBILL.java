package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.rsbaogao.plugin.bpplugin.Hg_rsbaogaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.itf.hkjt.IHg_rsbaogaoMaintain;

public class N_HK04_UNSAVEBILL extends AbstractPfAction<RsbaogaoBillVO> {

	@Override
	protected CompareAroundProcesser<RsbaogaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<RsbaogaoBillVO> processor = new CompareAroundProcesser<RsbaogaoBillVO>(
				Hg_rsbaogaoPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected RsbaogaoBillVO[] processBP(Object userObj,
			RsbaogaoBillVO[] clientFullVOs, RsbaogaoBillVO[] originBills) {
		IHg_rsbaogaoMaintain operator = NCLocator.getInstance().lookup(
				IHg_rsbaogaoMaintain.class);
		RsbaogaoBillVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
