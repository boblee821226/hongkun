// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-08-10 09:03:09
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   N_F3_SAVE.java

package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.arap.actions.N_BASE_ACTION;
import nc.bs.arap.paybp.PayBillBO;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillItemVO;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.fipub.exception.ExceptionHandler;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;



/**
 * fk(����) F3(��Ӧ�̸��) D3(��Ӧ�̸��) ap_paybill  ap_payitem  </br>
 * #######################################################################################################</br>
 * nc.bs.arap.actions.PaybillBatchSaveBSAction#invertVOs Agg+����+VO     ����+BillVO  ����+ItemVO</br>
 * @modified by liaobx 2010-7-6 
 * @author guodw
 *
 */


public class N_F3_SAVE extends N_BASE_ACTION {
	private Hashtable<String, Object> m_methodReturnHas;
	private Hashtable<String, Object> m_keyHas;

	public N_F3_SAVE() {
		m_methodReturnHas = new Hashtable<String, Object>();
	}

	private boolean hasBill(String primaryKey)
			throws BusinessException {
		boolean hasBill=false;
		if(primaryKey!=null){
			AggPayBillVO[] bvos = null;
			try {
				PayBillBO payableBO = new PayBillBO();
				bvos = payableBO.findBillByPrimaryKey(new String[]{primaryKey});
			}catch (Exception e) {
			}
			if(bvos!=null && bvos.length!=0 && bvos[0]!=null){
				hasBill=true;
			}
		}
		return hasBill;
	}
	public Object runComClass(PfParameterVO paraVo) throws nc.vo.pub.BusinessException {
		try {
			if (paraVo.m_preValueVos == null)
				return null;
			Object obj = null;
			super.m_tmpVo = paraVo;
			// // ������
			setParameter("context", paraVo.m_preValueVos);

			/**
			 * HK 2019��10��31�� 15��00��
			 * ��¼ ��������ȷ�� ��ͬ����
			 * ���Ϊ ��ͬ�����ֵ��ȷ�� �������ͱ���
			 */
//			String befor_type = paraVo.m_preValueVo.getParentVO().getAttributeValue("pk_tradetypeid").toString();
//			if (IPub_data.BKHT_tradetypeid.equals(befor_type)) {
//				paraVo.m_preValueVo.getParentVO().setAttributeValue("pk_tradetype", IPub_data.BKHT_tradetype);
//			}
////			String befor_type_code = paraVo.m_preValueVo.getParentVO().getAttributeValue("pk_tradetype").toString();
			/***END***/
			
			beforeCheck();

			/**
			 * 2020��3��19��14:59:56
			 * ��def30 ���µ� ��ע
			 */
			{
				PayBillItemVO[] bVOs = (PayBillItemVO[])paraVo.m_preValueVo.getChildrenVO();
				for(PayBillItemVO bVO : bVOs)
	        	{
	        		String def30 = bVO.getDef30();
	        		if(def30!=null && !"~".equals(def30))
	        		{
	        			bVO.setScomment(def30);
	        		}
	        	}
			}
			/***END***/
			
			String primaryKey = paraVo.m_preValueVos[0].getParentVO().getPrimaryKey();
			
			if(hasBill(primaryKey)){
				paraVo.m_preValueVos[0].getParentVO().setAttributeValue(IBillFieldGet.APPROVESTATUS, BillEnumCollection.ApproveStatus.COMMIT.VALUE);
				obj = runClass("nc.bs.arap.actions.PaybillEditSaveBatchBSAction", "updateVOs",
						"&context:nc.vo.pub.AggregatedValueObject[]", paraVo, m_keyHas);
			}else{
				obj = runClass("nc.bs.arap.actions.PaybillSaveBatchBSAction", "insertVOs",
					"&context:nc.vo.pub.AggregatedValueObject[]", paraVo, m_keyHas);
			}
			// if (obj != null)
			// m_methodReturnHas.put("insertVOs", obj);
			afterCheck();
			
			
			/**
			 * HK 2019��10��31�� 15��03��
			 * ���������޸�
			 */
//			AggPayBillVO[] return_obj = (AggPayBillVO[])obj;
//			String after_type = return_obj[0].getParentVO().getAttributeValue("pk_tradetypeid").toString();
//			String pk = return_obj[0].getPrimaryKey();
//			
//			if(!after_type.equals(befor_type))
//			{// �������ǰ��� ���Ͳ�һ�£�����Ҫ �޸�
//				BaseDAO daseDAO = new BaseDAO();
//				
//				String updateSQL = 
//						" update ap_paybill " +
//						" set pk_tradetypeid = '"+befor_type+"' " +
//						" , pk_tradetype = '"+IPub_data.BKHT_tradetype+"' " +
//						" where pk_paybill = '"+pk+"' "
//				;
////				daseDAO.setAddTimeStamp(false);
//				int flag = daseDAO.executeUpdate(updateSQL);
////				daseDAO.setAddTimeStamp(true);
//				if(flag>0)
//				{
//					return_obj[0].getParentVO().setAttributeValue("pk_tradetypeid",befor_type);
//					return_obj[0].getParentVO().setAttributeValue("pk_tradetype",IPub_data.BKHT_tradetype);
//				}
//			}
			/***END***/
			
			/**
			 * HK 2020��3��19��14:52:50
			 * ���Զ���30��ժҪ ��ֵ�� ժҪ�ֶ�
			 */
//			AggPayBillVO[] return_obj = (AggPayBillVO[])obj;
//			String pk = return_obj[0].getPrimaryKey();
//			
//			BaseDAO daseDAO = new BaseDAO();
//			
//			String updateSQL = 
//					" update ap_payitem " +
//					" set scomment = def30 " +
//					" , def30 = '~' " +
//					" where pk_paybill = '"+pk+"' " +
//					" and nvl(def30, '~') <> '~' " +
//					" and dr = 0 "
//					
//			;
////				daseDAO.setAddTimeStamp(false);
//			int flag = daseDAO.executeUpdate(updateSQL);
////				daseDAO.setAddTimeStamp(true);
//			if(flag>0)
//			{
//				return_obj[0].getParentVO().setAttributeValue("pk_tradetypeid",befor_type);
//				return_obj[0].getParentVO().setAttributeValue("pk_tradetype",IPub_data.BKHT_tradetype);
//			}
			/***END***/

			return obj;
		} catch (Exception exception) {
			throw ExceptionHandler.handleException(this.getClass(), exception);

		}
	}

	protected void setParameter(String key, Object val) {
		if (m_keyHas == null) {
			m_keyHas = new Hashtable<String, Object>();
		}
		if (val != null) {
			m_keyHas.put(key, val);
		}
	}

	public String getCodeRemark(){
		return " arap action script not allowed to modify ,all rights reserved!";
	}
}