package nc.bs.pub.action;

import java.util.List;

import nc.bs.am.actionscript.BaseCommitActionScript;
import nc.bs.framework.common.NCLocator;
import nc.itf.fa.prv.IAlter;
import nc.vo.fa.alter.AlterVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;
import nc.vo.uif2.LoginContext;

public class N_HG_SAVE extends BaseCommitActionScript {

	@SuppressWarnings("unchecked")
	@Override
	protected Object doInsertAction(PfParameterVO parameterVo)
			throws BusinessException {
		super.m_tmpVo = parameterVo;
		// 1.���ñ�������½������
		setParameter("context", null);

		// 2.���ñ���������VO�������
		AlterVO billVO = (AlterVO) getVo();
		setParameter("vo", billVO);

		// 3.���ý���仯�ֶ�
		setParameter("showAlterKeyList", getUserObj());

		//		Object retObj = runClass("nc.impl.fa.alter.AlterImpl", "insert",
		//				"&context:nc.vo.uif2.LoginContext,&vo:nc.vo.fa.alter.AlterVO,&showAlterKeyList:java.util.List", 
		//				parameterVo, getKeyHash());
		//
		//		return retObj;

		try {
			/** modify by zhumyc ����V65�µ����̲���  **/
			Object retVal = null;
			if (getUserObj() instanceof List) {            	
				retVal =
						NCLocator.getInstance().lookup(IAlter.class)
						.insert(null, (AlterVO) getVo(), (List<String>) getUserObj());
			} else {
				// ����ǵ��������ύ   �����userObj��һ������
				retVal =
						NCLocator.getInstance().lookup(IAlter.class)
						.insert(null, (AlterVO) getVo(), (List<String>)((Object[])getUserObj())[0]);            	
			}
			/** end **/
			return retVal;
		} catch  (Exception ex) {
			if (ex instanceof BusinessException)
				throw (BusinessException) ex;
			else
				throw new PFBusinessException(ex.getMessage(), ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object doUpdateAction(PfParameterVO parameterVo)
			throws BusinessException {
		super.m_tmpVo = parameterVo;
		// 1.���ñ�������½������
		setParameter("context", null);

		// 2.���ñ���������VO�������
		AlterVO billVO = (AlterVO) getVo();
		setParameter("vo", billVO);

		// 3.���ý���仯�ֶ�
		setParameter("showAlterKeyList", getUserObj());

		//		Object retObj = runClass("nc.impl.fa.alter.AlterImpl", "update",
		//				"&context:nc.vo.uif2.LoginContext,&vo:nc.vo.fa.alter.AlterVO,&showAlterKeyList:java.util.List", 
		//				parameterVo, getKeyHash());
		//
		//		return retObj;

		try {
			/** modify by zhumyc ����V65�µ����̲���  **/
			Object retVal = null;
			if (getUserObj() instanceof List) {            	
				retVal =
						NCLocator.getInstance().lookup(IAlter.class)
						.update(null, (AlterVO) getVo(), (List<String>) getUserObj());
			} else {
				// ����ǵ��������ύ   �����userObj��һ������
				retVal =
						NCLocator.getInstance().lookup(IAlter.class)
						.update(null, (AlterVO) getVo(), (List<String>)((Object[])getUserObj())[0]);            	
			}
			/** end **/
			return retVal;
		} catch  (Exception ex) {
			if (ex instanceof BusinessException)
				throw (BusinessException) ex;
			else
				throw new PFBusinessException(ex.getMessage(), ex);
		}
	}

	@Override
	protected Object doCommitAction(PfParameterVO parameterVo)
			throws BusinessException {
		super.m_tmpVo = parameterVo;
		// ####���ű����뺬�з���ֵ,����DLG��PNL������������з���ֵ####
		// ####��Ҫ˵�������ɵ�ҵ���������������Ҫ�����޸�####

		// 1.���ñ�������½������
		if (getUserObj() instanceof LoginContext) {
			setParameter("context", getUserObj());
		} else {
			setParameter("context", null);
		}

		// 2.���ñ���������VO�������
		AlterVO billVO = (AlterVO) getVo();
		setParameter("vo", billVO);

		//		// 3.����
		//		Object retObj = runClass("nc.impl.fa.alter.AlterImpl", "commit",
		//				"&context:nc.vo.uif2.LoginContext,&vo:nc.vo.fa.alter.AlterVO", 
		//				parameterVo, getKeyHash());
		//
		//		return retObj;

		try {
			Object retVal = null;
			retVal =
					NCLocator.getInstance().lookup(IAlter.class)
					.commit(null, (AlterVO) getVo());
			return retVal;
		} catch  (Exception ex) {
			if (ex instanceof BusinessException)
				throw (BusinessException) ex;
			else
				throw new PFBusinessException(ex.getMessage(), ex);
		}
	}

}
