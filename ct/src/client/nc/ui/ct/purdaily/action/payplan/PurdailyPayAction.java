package nc.ui.ct.purdaily.action.payplan;

import java.util.HashSet;
import java.util.Set;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.bs.framework.common.NCLocator;
import nc.itf.ct.purdaily.ICtPayPlanQuery;
import nc.itf.hkjt.IPub_data;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.ui.scmf.payplan.action.PayAction;
import nc.ui.uif2.UIState;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillVO;
import nc.vo.ct.enumeration.CtFlowEnum;
import nc.vo.ct.purdaily.entity.AggPayPlanVO;
import nc.vo.ct.purdaily.entity.PayPlanViewVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanViewVO;
import nc.vo.scmpub.res.billtype.CTBillType;

/**
 * ����
 * 
 * @since 6.0
 * @version 2011-2-18 ����02:21:46
 * @author wuxla
 */
public class PurdailyPayAction extends PayAction {

	private static final long serialVersionUID = -8216475400500416684L;

	@Override
	protected boolean check(Object[] objs) {
		for (Object obj : objs) {
			PayPlanViewVO viewvo = (PayPlanViewVO) obj;
			if (viewvo != null
					&& !CtFlowEnum.VALIDATE.value().equals(
							viewvo.getFstatusflag())) {
				ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl
						.getNCLangRes()
						.getStrByID("4020003_0", "04020003-0332")/*
																 * @res
																 * "����Ч̬��ͬ�������ɸ������룬���飡"
																 */);
				return false;
			}
		}
		return super.check(objs);
	}

	@Override
	protected AggregatedValueObject[] getDestVOs(Object[] objs) {
		PayPlanViewVO[] views = ArrayUtil.convertArrayType(objs);
		AggPayPlanVO[] vos = PayPlanViewVO.getAggPayPlanVO(views);
		AggregatedValueObject[] destVOs = null;

		destVOs = PfServiceScmUtil.exeVOChangeByBillItfDef(
				CTBillType.PurDaily.getCode(), IArapBillTypeCons.D3, vos);
		
		AggregatedValueObject[] resultVOs = this.convertVOs(destVOs);
		
		/**
		 * HK 2019��10��31�� 15��59��
		 * ����� �տں�ͬ �� ���������� ��ͬ����
		 */
		for(AggregatedValueObject vo : resultVOs) {
			AggPayBillVO billVO = (AggPayBillVO)vo;
			PayBillVO hVO = billVO.getHeadVO();
			hVO.setPk_tradetype(IPub_data.BKHT_tradetype);
			hVO.setPk_tradetypeid(IPub_data.BKHT_tradetypeid);
		}
		/***END***/
		
		return resultVOs;
	}

	@Override
	protected Object[] getUpdateVOs(Object[] objs) {
		PayPlanViewVO[] views = ArrayUtil.convertArrayType(objs);
		Set<String> bidSet = new HashSet<String>();

		for (PayPlanViewVO view : views) {
			if (bidSet.size() <= 0 || !bidSet.contains(view.getPk_ct_pu())) {
				bidSet.add(view.getPk_ct_pu());
			}
		}
		String[] bids = bidSet.toArray(new String[bidSet.size()]);

		ICtPayPlanQuery service = NCLocator.getInstance().lookup(
				ICtPayPlanQuery.class);
		try {
			AggPayPlanVO[] payplanVOs = service.queryPayPlanVOs(bids);
			return AggPayPlanVO.getPayPlanViewVO(payplanVOs);

		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return null;
	}

	@Override
	protected boolean isActionEnable() {
		if (this.getModel().getUiState() != UIState.NOT_EDIT) {
			return false;
		}

		if (this.getModel().getRows().isEmpty()) {
			return false;
		}

		// ����ѡ�вɹ��ƻ��� �ĺ�ͬ״̬�� �жϰ�ť�Ƿ����
		if (this.getModel().getSelectedOperaDatas() == null) {
			return false;
		}
		if (this.getModel().getSelectedOperaDatas().length > 1) {
			// ѡ���˶��У���ť����
			return true;
		}
		// ��ѡ��һ����ΪʧЧʱ��������
		PayPlanViewVO view = (PayPlanViewVO) this.getModel()
				.getSelectedOperaDatas()[0];
		if (!CtFlowEnum.VALIDATE.value().equals(view.getFstatusflag())) {
			return false;
		}

		return true;
	}

}
