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
 * 下拨申请单删除
 *
 * @author  chengfei
 * @version 1.0 2011-2-28
 * @since   NC6.0
 */
public class N_36K1_DELETE extends AbstractCompiler2 {
	private java.util.Hashtable m_methodReturnHas = new java.util.Hashtable();
	private Hashtable m_keyHas = null;

	/**
	 * N_36K1_DELETE 构造子注解。
	 */
	public N_36K1_DELETE() {
		super();
	}

	/*
	 * 备注：平台编写规则类 接口执行类
	 */
	public Object runComClass(PfParameterVO vo) throws BusinessException {
		try {
			super.m_tmpVo = vo;
			AggAllocateApplyVO aggAllocateApplyVO = (AggAllocateApplyVO) this.getVo();

			setParameter("BILLVO", aggAllocateApplyVO);

			new AllocateApplyBR().deleteAllocateApply(aggAllocateApplyVO);

			/**
			 * HK 2019年11月7日 15点02分
			 * 如果是 结算单 推过来的，则还原 结算单 及相关联系
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
	 * 备注：平台编写原始脚本
	 */
	public String getCodeRemark() {
		return "	\n";
	}

	/*
	 * 备注：设置脚本变量的HAS
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
