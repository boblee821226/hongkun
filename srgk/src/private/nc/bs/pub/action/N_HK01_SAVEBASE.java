package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.zhangdan.plugin.bpplugin.Hg_zhangdanPluginPoint;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.itf.hkjt.IHg_zhangdanMaintain;

public class N_HK01_SAVEBASE extends AbstractPfAction<ZhangdanBillVO> {

	@Override
	protected CompareAroundProcesser<ZhangdanBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ZhangdanBillVO> processor = null;
		ZhangdanBillVO[] clientFullVOs = (ZhangdanBillVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<ZhangdanBillVO>(
					Hg_zhangdanPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<ZhangdanBillVO>(
					Hg_zhangdanPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<ZhangdanBillVO> rule = null;

		return processor;
	}

	@Override
	protected ZhangdanBillVO[] processBP(Object userObj,
			ZhangdanBillVO[] clientFullVOs, ZhangdanBillVO[] originBills) {

		ZhangdanBillVO[] bills = null;
		try {
			IHg_zhangdanMaintain operator = NCLocator.getInstance()
					.lookup(IHg_zhangdanMaintain.class);
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
