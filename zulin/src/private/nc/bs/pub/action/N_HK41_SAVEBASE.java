package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.zulin.znjjs.plugin.bpplugin.Hk_zulin_znjjsPluginPoint;
import nc.vo.hkjt.zulin.znjjs.ZnjjsBillVO;
import nc.itf.hkjt.IHk_zulin_znjjsMaintain;

public class N_HK41_SAVEBASE extends AbstractPfAction<ZnjjsBillVO> {

	@Override
	protected CompareAroundProcesser<ZnjjsBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ZnjjsBillVO> processor = null;
		ZnjjsBillVO[] clientFullVOs = (ZnjjsBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<ZnjjsBillVO>(
					Hk_zulin_znjjsPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<ZnjjsBillVO>(
					Hk_zulin_znjjsPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<ZnjjsBillVO> rule = null;

		return processor;
	}

	@Override
	protected ZnjjsBillVO[] processBP(Object userObj,
			ZnjjsBillVO[] clientFullVOs, ZnjjsBillVO[] originBills) {

		ZnjjsBillVO[] bills = null;
		try {
			IHk_zulin_znjjsMaintain operator = NCLocator.getInstance()
					.lookup(IHk_zulin_znjjsMaintain.class);
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
