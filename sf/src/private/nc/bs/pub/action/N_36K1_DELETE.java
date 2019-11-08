/*
 * @(#)N_36K1_DELETE.java 2011-2-28
 * Copyright 2010 UFIDA Software CO.LTD. All rights reserved.
 */
package nc.bs.pub.action;

import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.sf.allocateapply.AllocateApplyBR;
import nc.itf.hkjt.report.HkjtReportITF;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.sf.allocateapply.AggAllocateApplyVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * �²����뵥ɾ��
 *
 * @author  chengfei
 * @version 1.0 2011-2-28
 * @since   NC6.0
 */
public class N_36K1_DELETE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_36K1_DELETE ������ע�⡣
	 */
	public N_36K1_DELETE() {
		super();
	}

	/*
	 * ��ע��ƽ̨��д������ �ӿ�ִ����
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			AggAllocateApplyVO aggAllocateApplyVO = (AggAllocateApplyVO) this.getVo();

			setParameter("BILLVO", aggAllocateApplyVO);

			new AllocateApplyBR().deleteAllocateApply(aggAllocateApplyVO);

			/**
			 * HK 2019��11��7�� 15��02��
			 * ����� ���㵥 �ƹ����ģ���ԭ ���㵥 �������ϵ
			 */
			HkjtReportITF itf = (HkjtReportITF) NCLocator.getInstance().lookup(HkjtReportITF.class.getName());
			itf.delXbsqBackJsd(aggAllocateApplyVO, null);
			/***END***/
			
			return aggAllocateApplyVO;
		} catch (Exception ex) {
			if (ex instanceof BusinessException)
				throw (BusinessException) ex;
			else
				throw new PFBusinessException(ex.getMessage(), ex);
		}
	}

	/*
	 * ��ע��ƽ̨��дԭʼ�ű�
	 */
	public String getCodeRemark() {
		return "	\n";
	}

	/*
	 * ��ע�����ýű�������HAS
	 */
	private void setParameter(String key, Object val) {
		if (m_keyHas == null) {
			m_keyHas = new Hashtable();
		}
		if (val != null) {
			m_keyHas.put(key, val);
		}
	}

}
