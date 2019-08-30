package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kazuofei.plugin.bpplugin.Hy_kazuofeiPluginPoint;
import nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBillVO;
import nc.itf.hkjt.IHy_kazuofeiMaintain;

public class N_HK28_SAVEBASE extends AbstractPfAction<KazuofeiBillVO> {

	@Override
	protected CompareAroundProcesser<KazuofeiBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KazuofeiBillVO> processor = null;
		KazuofeiBillVO[] clientFullVOs = (KazuofeiBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<KazuofeiBillVO>(
					Hy_kazuofeiPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<KazuofeiBillVO>(
					Hy_kazuofeiPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<KazuofeiBillVO> rule = null;

		return processor;
	}

	@Override
	protected KazuofeiBillVO[] processBP(Object userObj,
			KazuofeiBillVO[] clientFullVOs, KazuofeiBillVO[] originBills) {

		KazuofeiBillVO[] bills = null;
		try {
			IHy_kazuofeiMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kazuofeiMaintain.class);
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
