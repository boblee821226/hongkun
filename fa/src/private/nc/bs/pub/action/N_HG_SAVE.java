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
		// 1.设置变量：登陆上下文
		setParameter("context", null);

		// 2.设置变量：单据VO数组对象。
		AlterVO billVO = (AlterVO) getVo();
		setParameter("vo", billVO);

		// 3.设置界面变化字段
		setParameter("showAlterKeyList", getUserObj());

		//		Object retObj = runClass("nc.impl.fa.alter.AlterImpl", "insert",
		//				"&context:nc.vo.uif2.LoginContext,&vo:nc.vo.fa.alter.AlterVO,&showAlterKeyList:java.util.List", 
		//				parameterVo, getKeyHash());
		//
		//		return retObj;

		try {
			/** modify by zhumyc 适配V65新的流程参数  **/
			Object retVal = null;
			if (getUserObj() instanceof List) {            	
				retVal =
						NCLocator.getInstance().lookup(IAlter.class)
						.insert(null, (AlterVO) getVo(), (List<String>) getUserObj());
			} else {
				// 如果是单独保存提交   这里的userObj是一个数组
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
		// 1.设置变量：登陆上下文
		setParameter("context", null);

		// 2.设置变量：单据VO数组对象。
		AlterVO billVO = (AlterVO) getVo();
		setParameter("vo", billVO);

		// 3.设置界面变化字段
		setParameter("showAlterKeyList", getUserObj());

		//		Object retObj = runClass("nc.impl.fa.alter.AlterImpl", "update",
		//				"&context:nc.vo.uif2.LoginContext,&vo:nc.vo.fa.alter.AlterVO,&showAlterKeyList:java.util.List", 
		//				parameterVo, getKeyHash());
		//
		//		return retObj;

		try {
			/** modify by zhumyc 适配V65新的流程参数  **/
			Object retVal = null;
			if (getUserObj() instanceof List) {            	
				retVal =
						NCLocator.getInstance().lookup(IAlter.class)
						.update(null, (AlterVO) getVo(), (List<String>) getUserObj());
			} else {
				// 如果是单独保存提交   这里的userObj是一个数组
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
		// ####本脚本必须含有返回值,返回DLG和PNL的组件不允许有返回值####
		// ####重要说明：生成的业务组件方法尽量不要进行修改####

		// 1.设置变量：登陆上下文
		if (getUserObj() instanceof LoginContext) {
			setParameter("context", getUserObj());
		} else {
			setParameter("context", null);
		}

		// 2.设置变量：单据VO数组对象。
		AlterVO billVO = (AlterVO) getVo();
		setParameter("vo", billVO);

		//		// 3.设置
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
