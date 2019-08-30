package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kazhangwu.plugin.bpplugin.Hy_kazhangwuPluginPoint;
import nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBillVO;
import nc.itf.hkjt.IHy_kazhangwuMaintain;

public class N_HK26_SAVEBASE extends AbstractPfAction<KazhangwuBillVO> {

	@Override
	protected CompareAroundProcesser<KazhangwuBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KazhangwuBillVO> processor = null;
		KazhangwuBillVO[] clientFullVOs = (KazhangwuBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<KazhangwuBillVO>(
					Hy_kazhangwuPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<KazhangwuBillVO>(
					Hy_kazhangwuPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<KazhangwuBillVO> rule = null;

		return processor;
	}

	@Override
	protected KazhangwuBillVO[] processBP(Object userObj,
			KazhangwuBillVO[] clientFullVOs, KazhangwuBillVO[] originBills) {

		KazhangwuBillVO[] bills = null;
		try {
			IHy_kazhangwuMaintain operator = NCLocator.getInstance()
					.lookup(IHy_kazhangwuMaintain.class);
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
