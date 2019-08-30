// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-08-10 09:02:57
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   N_F2_SAVE.java

package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.arap.actions.N_BASE_ACTION;
import nc.bs.dao.BaseDAO;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
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
			 */
			{
				GatheringBillItemVO[] bVOs = (GatheringBillItemVO[])paraVo.m_preValueVo.getChildrenVO();
				for( GatheringBillItemVO bVO : bVOs )
	        	{
	        		String def30 = bVO.getDef30();
	        		if(def30!=null&&!"~".equals(def30))
	        		{
	        			bVO.setScomment(def30);
	        		}
	        	}
			}
			/***END***/

			obj = runClass("nc.bs.arap.actions.GatheringbillSaveBatchBSAction", "insertVOs",
				"&context:nc.vo.pub.AggregatedValueObject[]", paraVo, m_keyHas);

			AggGatheringBillVO[] return_obj = (AggGatheringBillVO[])obj;
			String after_type = return_obj[0].getParentVO().getAttributeValue("pk_tradetypeid").toString();
			String pk = return_obj[0].getPrimaryKey();
			
			/**
			 * 2019��4��15��15:23:08
			 * ���������޸�
			 */
			if(!after_type.equals(befor_type))
			{// �������ǰ��� ���Ͳ�һ�£�����Ҫ �޸�
			
				BaseDAO daseDAO = new BaseDAO();
				
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
			
			return obj;
			
		} catch (Exception exception) {
			throw ExceptionHandler.handleException(this.getClass(), exception);
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