// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-08-10 09:03:08
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) ansi 
// Source File Name:   N_F2_DELETE.java

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

public class N_F2_DELETE extends N_BASE_ACTION {

	private Hashtable<String, Object> m_keyHas = null;

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO paraVo) throws BusinessException {
		try {

			if (paraVo.m_preValueVos == null)
				return null;
			Object obj = null;
			super.m_tmpVo = paraVo;
			// ��������
			setParameter("context", paraVo.m_preValueVos);
			obj = runClass("nc.bs.arap.actions.GatheringbillDeleteBatchBSAction", "deleteVOs",
							"&context:nc.vo.pub.AggregatedValueObject[]", paraVo, m_keyHas);
			
			afterCheck();
			
			/**
			 * 2020��12��30��23��11��
			 * ���� ���κ�ͬ�� �ۼ��տ�  noritotalgpmny��ntotalgpmny
			 * money_cr��local_money_cr 
			 */
			AggGatheringBillVO[] return_obj = (AggGatheringBillVO[])paraVo.m_preValueVos;
			BaseDAO daseDAO = new BaseDAO();
			N_F2_SAVE.back2ct(return_obj, daseDAO);
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
