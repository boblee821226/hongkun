package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.hkjt.srgk.huiguan.rsbaogao.checkaction.AceHg_rsbaogaoBeforeSaveAction;
import nc.bs.hkjt.srgk.huiguan.rsbaogao.plugin.bpplugin.Hg_rsbaogaoPluginPoint;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.hkjt.IHg_rsbaogaoMaintain;
import nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBillVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class N_HK04_SAVEBASE extends AbstractPfAction<RsbaogaoBillVO> {

	@Override
	protected CompareAroundProcesser<RsbaogaoBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<RsbaogaoBillVO> processor = null;
		RsbaogaoBillVO[] clientFullVOs = (RsbaogaoBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<RsbaogaoBillVO>(
					Hg_rsbaogaoPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<RsbaogaoBillVO>(
					Hg_rsbaogaoPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<RsbaogaoBillVO> rule = new AceHg_rsbaogaoBeforeSaveAction();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected RsbaogaoBillVO[] processBP(Object userObj,
			RsbaogaoBillVO[] clientFullVOs, RsbaogaoBillVO[] originBills) {

		RsbaogaoBillVO[] bills = null;
		try {
			IHg_rsbaogaoMaintain operator = NCLocator.getInstance()
					.lookup(IHg_rsbaogaoMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
