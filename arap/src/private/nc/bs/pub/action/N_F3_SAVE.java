// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-08-10 09:03:09
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   N_F3_SAVE.java

package nc.bs.pub.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import nc.bs.arap.actions.N_BASE_ACTION;
import nc.bs.arap.paybp.PayBillBO;
import nc.bs.dao.BaseDAO;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillItemVO;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.fipub.exception.ExceptionHandler;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.lang.UFDouble;



/**
 * fk(付款) F3(供应商付款单) D3(供应商付款单) ap_paybill  ap_payitem  </br>
 * #######################################################################################################</br>
 * nc.bs.arap.actions.PaybillBatchSaveBSAction#invertVOs Agg+表名+VO     表名+BillVO  表名+ItemVO</br>
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
			// // 走批量
			setParameter("context", paraVo.m_preValueVos);

			/**
			 * HK 2020年6月23日18:35:11
			 * 如果来源是 4D48、4D50、4D83 ：表体金额为0，则return
			 * 如果来源是 4D83：款项冲销 & 金额为负数、则return
			 */
			{
				Map<String, String> _48 = new HashMap<>();
				_48.put("4D48", "进度款单");
				_48.put("4D50", "结算单");
				_48.put("4D83", "费用结算单");
				PayBillItemVO[] bVOs = (PayBillItemVO[])paraVo.m_preValueVo.getChildrenVO();
				for(PayBillItemVO bVO : bVOs)
	        	{
	        		UFDouble money_de = bVO.getMoney_de();
	        		if (_48.containsKey(bVO.getSrc_billtype())) {
		        		if(PuPubVO.getUFDouble_ZeroAsNull(money_de) == null)
		        		{// 若 金额为0的，则不生单
		        			return null;
		        		}
	        		}
	        		if ("4D83".equals(bVO.getSrc_billtype())) {
	        			// 如果是 来源是费用结算单，则 需要检查费用结算单 的 表头自定义5 = 款项冲销，并且 金额小于0
	        			if (money_de.compareTo(UFDouble.ZERO_DBL) < 0) {
	        				// 如果金额小于0 才需要做判断。
	        				String fyjsId = bVO.getSrc_billid(); // 费用结算单id
	        				StringBuffer querySQL = 
	        				new StringBuffer("select ")
		        					.append(" f.pk_feebalance ")
		        					.append(" from pm_feebalance f ")
		        					.append(" inner join bd_defdoc d on f.def5 = d.pk_defdoc ")
		        					.append(" where ")
		        					.append(" d.name = '款项冲销' ") // 查询 费用结算单 是不是 款项冲销的
		        					.append(" and f.pk_feebalance = '").append(fyjsId).append("' ")
	        				;
	        				BaseDAO dao = new BaseDAO();
	        				ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
	        				if (list != null && !list.isEmpty()) {
	        					// 若是 款项冲销的，则不生单。
	        					return null;
	        				}
	        			}
	        		}
	        	}
			}
			/***END***/
			
			/**
			 * HK 2019年10月31日 15点00分
			 * 记录 单据上正确的 合同类型
			 * 如果为 合同付款，则赋值正确的 单据类型编码
			 */
//			String befor_type = paraVo.m_preValueVo.getParentVO().getAttributeValue("pk_tradetypeid").toString();
//			if (IPub_data.BKHT_tradetypeid.equals(befor_type)) {
//				paraVo.m_preValueVo.getParentVO().setAttributeValue("pk_tradetype", IPub_data.BKHT_tradetype);
//			}
////			String befor_type_code = paraVo.m_preValueVo.getParentVO().getAttributeValue("pk_tradetype").toString();
			/***END***/
			
			beforeCheck();

			/**
			 * 2020年3月19日14:59:56
			 * 将def30 更新到 备注
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
			 * HK 2019年10月31日 15点03分
			 * 单据类型修改
			 */
//			AggPayBillVO[] return_obj = (AggPayBillVO[])obj;
//			String after_type = return_obj[0].getParentVO().getAttributeValue("pk_tradetypeid").toString();
//			String pk = return_obj[0].getPrimaryKey();
//			
//			if(!after_type.equals(befor_type))
//			{// 如果保存前后的 类型不一致，则需要 修改
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
			 * HK 2020年3月19日14:52:50
			 * 将自定义30的摘要 赋值给 摘要字段
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