package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.srgk.huiguan.zhangdan.plugin.bpplugin.Hg_zhangdanPluginPoint;
import nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBillVO;
import nc.itf.hkjt.IHg_zhangdanMaintain;

public class N_HK01_DELETE extends AbstractPfAction<ZhangdanBillVO> {

	@Override
	protected CompareAroundProcesser<ZhangdanBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<ZhangdanBillVO> processor = new CompareAroundProcesser<ZhangdanBillVO>(
				Hg_zhangdanPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected ZhangdanBillVO[] processBP(Object userObj,
			ZhangdanBillVO[] clientFullVOs, ZhangdanBillVO[] originBills) {
		IHg_zhangdanMaintain operator = NCLocator.getInstance().lookup(
				IHg_zhangdanMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
