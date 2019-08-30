package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.hkjt.huiyuan.kaipiaoquery.plugin.bpplugin.Hy_kaipiaoqueryPluginPoint;
import nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBillVO;
import nc.itf.hkjt.IHy_kaipiaoqueryMaintain;

public class N_HK34_DELETE extends AbstractPfAction<KaipiaoqueryBillVO> {

	@Override
	protected CompareAroundProcesser<KaipiaoqueryBillVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<KaipiaoqueryBillVO> processor = new CompareAroundProcesser<KaipiaoqueryBillVO>(
				Hy_kaipiaoqueryPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected KaipiaoqueryBillVO[] processBP(Object userObj,
			KaipiaoqueryBillVO[] clientFullVOs, KaipiaoqueryBillVO[] originBills) {
		IHy_kaipiaoqueryMaintain operator = NCLocator.getInstance().lookup(
				IHy_kaipiaoqueryMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
