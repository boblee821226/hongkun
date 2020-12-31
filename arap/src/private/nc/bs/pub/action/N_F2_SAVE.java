// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-08-10 09:02:57
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   N_F2_SAVE.java

package nc.bs.pub.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import nc.bs.arap.actions.N_BASE_ACTION;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.arap.gathering.GatheringBillVO;
import nc.vo.fipub.exception.ExceptionHandler;
import nc.vo.pub.compiler.PfParameterVO;

/**
 * F2(�ͻ��տ) D2(�ͻ��տ)  ar_gatherbill  ar_gatheritem 
 * nc.bs.arap.actions.GatheringbillBatchSaveBSAction.invertVOs  Agg+����+VO     ����+BillVO  ����+ItemVO
 * @author guodw
 * @modified by liaobx 2010-7-13 10:15:00
 *
 */
public class N_F2_SAVE extends N_BASE_ACTION{

	private Hashtable<String, Object> m_keyHas;

	public N_F2_SAVE() {
		m_keyHas = null;
	}

	public Object runComClass(PfParameterVO paraVo) throws nc.vo.pub.BusinessException {
		try {
			super.m_tmpVo = paraVo;
			Object obj = null;
			setParameter("context", paraVo.m_preValueVos);
			
			String befor_type = paraVo.m_preValueVo.getParentVO().getAttributeValue("pk_tradetypeid").toString();
			
			beforeCheck();
			
			/**
			 * 2018��10��10��23:37:48
			 * �� def10 ���µ� ��ע
			 * 2019��1��23��17:43:00
			 * ��Ϊ def30
			 * 2020��9��8��19:36:58
			 * �ĳ� ����������+�ڼ�+�շ���Ŀ��  �ڼ�� ˮ���¼�뵥��ȡ��
			 */
			{
				GatheringBillItemVO[] bVOs = (GatheringBillItemVO[])paraVo.m_preValueVo.getChildrenVO();
				ArrayList<String> pk_sdflr_b_list = new ArrayList<>();
				// ��һ��ѭ�����壬������е� ¼�뵥����id
				for( GatheringBillItemVO bVO : bVOs )
	        	{
					String pk_sdflr_b = PuPubVO.getString_TrimZeroLenAsNull(bVO.getDef29());
					if (pk_sdflr_b != null && !"~".equals(pk_sdflr_b)) {
						pk_sdflr_b_list.add(pk_sdflr_b);
					}
	        	}
				// ��ѯ����װժҪ
				if (!pk_sdflr_b_list.isEmpty()) {
					StringBuffer querySQL = 
					new StringBuffer("select ")
							.append(" sb.pk_hk_zulin_sdflr_b ")
							.append(",cust.name || '��' || substr(s.vdef01,1,10) || '��' || substr(s.vdef02,1,10) || '��' || sfxm.name ")
							.append(" from hk_zulin_sdflr s ")
							.append(" inner join hk_zulin_sdflr_b sb on s.pk_hk_zulin_sdflr = sb.pk_hk_zulin_sdflr ")
							.append(" left join bd_customer cust on sb.pk_cust = cust.pk_customer ")
							.append(" left join bd_defdoc sfxm on sb.pk_sfxm = sfxm.pk_defdoc ")
							.append(" where s.dr = 0 and sb.dr = 0 ")
							.append(" and sb.pk_hk_zulin_sdflr_b in ").append(PuPubVO.getSqlInByList(pk_sdflr_b_list))
					;
					BaseDAO dao = new BaseDAO();
					ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
					HashMap<String,String> ZY_MAP = new HashMap<>();
					if (list != null && !list.isEmpty()) {
						for (Object itemObj : list) {
							Object[] item = (Object[])itemObj;
							ZY_MAP.put(
									  PuPubVO.getString_TrimZeroLenAsNull(item[0])
									, PuPubVO.getString_TrimZeroLenAsNull(item[1]));
						}
					}
					// �ڶ���ѭ�����壬��ժҪ��ֵ
					for( GatheringBillItemVO bVO : bVOs )
		        	{
						String pk_sdflr_b = PuPubVO.getString_TrimZeroLenAsNull(bVO.getDef29());
						if (pk_sdflr_b != null && !"~".equals(pk_sdflr_b)) {
							String zy = ZY_MAP.get(pk_sdflr_b);
							bVO.setScomment(zy);
						}
		        	}
				}
			}
			/***END***/

			obj = runClass("nc.bs.arap.actions.GatheringbillSaveBatchBSAction", "insertVOs",
				"&context:nc.vo.pub.AggregatedValueObject[]", paraVo, m_keyHas);

			AggGatheringBillVO[] return_obj = (AggGatheringBillVO[])obj;
			String after_type = return_obj[0].getParentVO().getAttributeValue("pk_tradetypeid").toString();
			String pk = return_obj[0].getPrimaryKey();
			BaseDAO daseDAO = new BaseDAO();
			/**
			 * 2019��4��15��15:23:08
			 * ���������޸�
			 */
			if(!after_type.equals(befor_type))
			{// �������ǰ��� ���Ͳ�һ�£�����Ҫ �޸�
				String updateSQL = 
						" update ar_gatherbill " +
						" set pk_tradetypeid = '"+befor_type+"' " +
						" where pk_gatherbill = '"+pk+"' "
				;
//				daseDAO.setAddTimeStamp(false);
				int flag = daseDAO.executeUpdate(updateSQL);
//				daseDAO.setAddTimeStamp(true);
				if(flag>0)
				{
					return_obj[0].getParentVO().setAttributeValue("pk_tradetypeid",befor_type);
				}
			}
			/***END***/
			
			/**
			 * 2020��12��30��23��11��
			 * ���� ���κ�ͬ�� �ۼ��տ�  noritotalgpmny��ntotalgpmny
			 * money_cr��local_money_cr 
			 * ֻȡ Эͬ���� src_syscode = 9
			 */
			back2ct(return_obj, daseDAO);
			/***END***/
			
			return obj;
			
		} catch (Exception exception) {
			throw ExceptionHandler.handleException(this.getClass(), exception);
		}
	}

	/**
	 * 2020��12��30��23��11��
	 * ���� ���κ�ͬ�� �ۼ��տ�  noritotalgpmny��ntotalgpmny
	 * money_cr��local_money_cr 
	 * ֻȡ Эͬ���� src_syscode = 9
	 */
	static void back2ct(AggGatheringBillVO[] return_obj, BaseDAO daseDAO)
			throws DAOException {
		for (AggGatheringBillVO item : return_obj) {
			GatheringBillVO hVO = item.getHeadVO();
			GatheringBillItemVO[] bVOs = item.getBodyVOs();
			Integer sysCode = hVO.getSyscode();
			if (sysCode != 9) continue;
			String pk_tradetype = hVO.getPk_tradetype();	// F2-Cxx-90
			if (!"F2-Cxx-90".equals(pk_tradetype)) continue;
			ArrayList<String> pkList = new ArrayList<>();
			for (GatheringBillItemVO bItem : bVOs) {
				String def29 = PuPubVO.getString_TrimZeroLenAsNull(bItem.getDef29());
				if (def29 != null) pkList.add(def29);
			}
			if (pkList.isEmpty()) continue;
			{// ����ͬ��
				String pkListStr = PuPubVO.getSqlInByList(pkList);
				String updateSQL = "update ct_sale_b htb " +
						 " set htb.ntotalgpmny = (select sum(skb.local_money_cr) " +
							                     " from ar_gatheritem skb " +
							                     " inner join ar_gatherbill sk on skb.pk_gatherbill = sk.pk_gatherbill " +
							                     " where skb.dr = 0 and sk.dr = 0 " +
							                     " and sk.src_syscode = 9 and sk.pk_tradetype = 'F2-Cxx-90' " +
							                     " and skb.def29 = htb.pk_ct_sale_b " +
							                     " group by skb.def29) " +
		                 ", htb.noritotalgpmny = (select sum(skb.money_cr) " +
							                     " from ar_gatheritem skb " +
							                     " inner join ar_gatherbill sk on skb.pk_gatherbill = sk.pk_gatherbill " +
							                     " where skb.dr = 0 and sk.dr = 0 " +
							                     " and sk.src_syscode = 9 and sk.pk_tradetype = 'F2-Cxx-90' " +
							                     " and skb.def29 = htb.pk_ct_sale_b " +
							                     " group by skb.def29) " +
					     " where htb.pk_ct_sale_b in " + pkListStr + " " 
					     ;
				int flag = daseDAO.executeUpdate(updateSQL);
			}
		}
	}

	public String getCodeRemark() {
		return " arap action script not allowed to modify ,all rights reserved!";
	}

	protected void setParameter(String key, Object val) {
		if (m_keyHas == null) {
			m_keyHas = new Hashtable<String, Object>();
		}
		if (val != null) {
			m_keyHas.put(key, val);
		}
	}
}