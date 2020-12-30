// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-08-10 09:03:08
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   N_F2_EDIT.java

package nc.bs.pub.action;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.Hashtable;

import nc.bs.arap.actions.N_BASE_ACTION;
import nc.bs.dao.BaseDAO;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.arap.gathering.GatheringBillVO;
import nc.vo.fipub.exception.ExceptionHandler;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;

public class N_F2_MODIFY extends N_BASE_ACTION {
	private Hashtable<String, Object> m_keyHas = null;

	/*
	 * 备注：平台编写规则类 接口执行类
	 */
	public Object runComClass(PfParameterVO paraVo) throws BusinessException {
		try {

			if (paraVo.m_preValueVos == null)
				return null;
			Object obj = null;
			super.m_tmpVo = paraVo;

			beforeCheck();

			// 批量操作
			setParameter("context", paraVo.m_preValueVos);
			obj = runClass("nc.bs.arap.actions.GatheringbillEditSaveBatchBSAction", "updateVOs",
				"&context:nc.vo.pub.AggregatedValueObject[]", paraVo, m_keyHas);
			afterCheck();
			
			/**
			 * 2020年12月30日23点11分
			 * 更新 上游合同的 累计收款  noritotalgpmny、ntotalgpmny
			 * money_cr、local_money_cr 
			 */
			AggGatheringBillVO[] return_obj = (AggGatheringBillVO[])obj;
			BaseDAO daseDAO = new BaseDAO();
			for (AggGatheringBillVO item : return_obj) {
				GatheringBillVO hVO = item.getHeadVO();
				GatheringBillItemVO[] bVOs = item.getBodyVOs();
				Integer sysCode = hVO.getSyscode();
				String pk_tradetype = hVO.getPk_tradetype();	// F2-Cxx-90
				ArrayList<String> pkList = new ArrayList<>();
				for (GatheringBillItemVO bItem : bVOs) {
					String def29 = PuPubVO.getString_TrimZeroLenAsNull(bItem.getDef29());
					if (def29 != null) pkList.add(def29);
				}
				if (sysCode == 0 && "F2-Cxx-90".equals(pk_tradetype) && !pkList.isEmpty()) {
					String pkListStr = PuPubVO.getSqlInByList(pkList);
					String updateSQL = "update ct_sale_b htb " +
							 " set htb.ntotalgpmny = (select sum(skb.local_money_cr) " +
								                     " from ar_gatheritem skb " +
								                     " inner join ar_gatherbill sk on skb.pk_gatherbill = sk.pk_gatherbill " +
								                     " where skb.dr = 0 and sk.dr = 0 " +
								                     " and sk.src_syscode = 0 and sk.pk_tradetype = 'F2-Cxx-90' " +
								                     " and skb.def29 = htb.pk_ct_sale_b " +
								                     " group by skb.def29) " +
		                     ", htb.noritotalgpmny = (select sum(skb.money_cr) " +
								                     " from ar_gatheritem skb " +
								                     " inner join ar_gatherbill sk on skb.pk_gatherbill = sk.pk_gatherbill " +
								                     " where skb.dr = 0 and sk.dr = 0 " +
								                     " and sk.src_syscode = 0 and sk.pk_tradetype = 'F2-Cxx-90' " +
								                     " and skb.def29 = htb.pk_ct_sale_b " +
								                     " group by skb.def29) " +
						     " where htb.pk_ct_sale_b in " + pkListStr + " " 
						     ;
					int flag = daseDAO.executeUpdate(updateSQL);
				}
			}
			/***END***/
			
			return obj;
		} catch (Exception ex) {
			throw ExceptionHandler.handleException(this.getClass(), ex);
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