package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.tiaozheng.ace.bp.AceHg_cctzBeforeSaveAction;
import nc.bs.hkjt.srgk.huiguan.tiaozheng.plugin.bpplugin.Hg_cctzPluginPoint;
import nc.vo.hkjt.srgk.huiguan.cctz.CctzBillVO;
import nc.itf.hkjt.IHg_cctzMaintain;

public class N_HK05_SAVEBASE extends AbstractPfAction<CctzBillVO> {

	@Override
	protected CompareAroundProcesser<CctzBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<CctzBillVO> processor = null;
		CctzBillVO[] clientFullVOs = (CctzBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<CctzBillVO>(
					Hg_cctzPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<CctzBillVO>(
					Hg_cctzPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<CctzBillVO> rule = null;
		processor.addBeforeRule(new AceHg_cctzBeforeSaveAction());
		return processor;
	}

	@Override
	protected CctzBillVO[] processBP(Object userObj,
			CctzBillVO[] clientFullVOs, CctzBillVO[] originBills) {

		CctzBillVO[] bills = null;
		try {
			IHg_cctzMaintain operator = NCLocator.getInstance()
					.lookup(IHg_cctzMaintain.class);
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
