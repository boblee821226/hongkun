package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.rsbaogao.plugin.bpplugin.Hg_rsbaogaoPluginPoint;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.itf.hkjt.IHg_rsbaogaoMaintain;

public class N_HK04_DELETE extends AbstractPfAction<RsbaogaoBillVO> {

	@Override
	protected CompareAroundProcesser<RsbaogaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<RsbaogaoBillVO> processor = new CompareAroundProcesser<RsbaogaoBillVO>(
				Hg_rsbaogaoPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected RsbaogaoBillVO[] processBP(Object userObj,
			RsbaogaoBillVO[] clientFullVOs, RsbaogaoBillVO[] originBills) {
		IHg_rsbaogaoMaintain operator = NCLocator.getInstance().lookup(
				IHg_rsbaogaoMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
