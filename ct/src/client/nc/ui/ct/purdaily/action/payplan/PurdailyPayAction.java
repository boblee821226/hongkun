package nc.ui.ct.purdaily.action.payplan;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.bs.framework.common.NCLocator;
import nc.itf.ct.purdaily.ICtPayPlanQuery;
import nc.itf.hkjt.IPub_data;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.scmf.payplan.action.PayAction;
import nc.ui.uif2.UIState;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillItemVO;
import nc.vo.arap.pay.PayBillVO;
import nc.vo.ct.enumeration.CtFlowEnum;
import nc.vo.ct.purdaily.entity.AggPayPlanVO;
import nc.vo.ct.purdaily.entity.PayPlanViewVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
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
		 * 2020��1��3��19:24:14
		 * ��ֵ�����ժҪ
		 */
		IUAPQueryBS iUAPQueryBS = (IUAPQueryBS)NCLocator.getInstance().lookup(IUAPQueryBS.class.getName()); 
		for(AggregatedValueObject vo : resultVOs) {
			AggPayBillVO billVO = (AggPayBillVO)vo;
			PayBillVO hVO = billVO.getHeadVO();
//			hVO.setPk_tradetype(IPub_data.BKHT_tradetype);
//			hVO.setPk_tradetypeid(IPub_data.BKHT_tradetypeid);
			PayBillItemVO[] bVOs = billVO.getBodyVOs();
			ArrayList<String> htBidsList = new ArrayList<>(bVOs.length);
			Map<String, PayBillItemVO> bVOsMap = new HashMap<>();
			for (PayBillItemVO bVO : bVOs) {
				String htBid = bVO.getTop_itemid();
				htBidsList.add(htBid);
				bVOsMap.put(htBid, bVO);
			}
			String bIdsStr = PuPubVO.getSqlInByList(htBidsList);
			StringBuffer querySQL = 
			new StringBuffer("select ")
				.append(" htp.pk_ct_payplan ")
				.append(",gys.name ||  '��' || substr(htb.vbdef3,1,10) || '��' || substr(htb.vbdef4,1,10) || '��' || szxm.name ")
				.append(" from ct_payplan htp ")
				.append(" inner join ct_pu_b htb on htp.pk_ct_pu = htb.pk_ct_pu and htp.crowno = htb.crowno ")
				.append(" inner join ct_pu ht on htp.pk_ct_pu = ht.pk_ct_pu ")
				.append(" left join bd_supplier gys on ht.cvendorid = gys.pk_supplier ")
				.append(" left join bd_inoutbusiclass szxm on htb.vbdef1 = szxm.pk_inoutbusiclass ")
				.append(" where htp.dr = 0 and htb.dr = 0 and ht.dr = 0 ")
				.append(" and htp.pk_ct_payplan in ").append(bIdsStr).append(" ")
			;
			ArrayList list = null;
			try {
				list = (ArrayList)iUAPQueryBS.executeQuery(querySQL.toString(), new ArrayListProcessor());
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			if (list != null && list.size() > 0) {
				Map<String, String> zyMap = new HashMap<>(list.size());
				for (Object obj : list) {
					Object[] row = (Object[])obj;
					String pk = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
					String zy = PuPubVO.getString_TrimZeroLenAsNull(row[1]);
					zyMap.put(pk, zy);
				}
				for (java.util.Map.Entry<String, PayBillItemVO> entry : bVOsMap.entrySet()) {
					String id = entry.getKey();
					String zy = zyMap.get(id);
					PayBillItemVO bVO = entry.getValue();
//					bVO.setScomment(zy);
//					bVO.setScomment("1001N510000000BEE45X");
					bVO.setDef30(zy);
				}
			}
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
