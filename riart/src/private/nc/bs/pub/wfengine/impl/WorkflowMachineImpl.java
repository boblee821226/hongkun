package nc.bs.pub.wfengine.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.ITimeService;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.pf.pub.PfDataCache;
import nc.bs.pub.pf.IPFClientBizProcessBS;
import nc.bs.pub.pf.IPfBeforeAction;
import nc.bs.pub.pf.PfMessageUtil;
import nc.bs.pub.pf.PfUtilTools;
import nc.bs.pub.pflock.PfBusinessLock;
import nc.bs.pub.pflock.VOConsistenceCheck;
import nc.bs.pub.pflock.VOLockData;
import nc.bs.pub.taskmanager.TaskManagerDMO;
import nc.bs.pub.taskmanager.WfTaskManager;
import nc.bs.pub.workflownote.WorknoteManager;
import nc.bs.trade.business.HYPubBO;
import nc.bs.wfengine.engine.ActivityInstance;
import nc.bs.wfengine.engine.EngineService;
import nc.bs.wfengine.engine.ProcessInstance;
import nc.bs.wfengine.engine.WfInstancePool;
import nc.bs.wfengine.engine.persistence.EnginePersistence;
import nc.impl.uap.pf.PFConfigImpl;
import nc.impl.uap.pf.WorkflowAdminImpl;
import nc.itf.uap.pf.IPFBusiAction;
import nc.itf.uap.pf.IWorkflowAdmin;
import nc.itf.uap.pf.IWorkflowMachine;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.exception.DbException;
import nc.jdbc.framework.processor.BaseProcessor;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.message.attachment.AttachmentReturnExcutor;
import nc.message.vo.AttachmentVO;
import nc.ui.pf.pub.PFClientBizRetObj;
import nc.ui.pub.pf.IPFClientBizProcess;
import nc.vo.hkjt.oa.HkOaSettingVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pf.mobileapp.MobileAppUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype2.Billtype2VO;
import nc.vo.pub.billtype2.ExtendedClassEnum;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.msg.AbstractMsgL10NCallback;
import nc.vo.pub.msg.IMsgL10NCallback;
import nc.vo.pub.pf.AssignableInfo;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pub.pf.PFClientBizRetVO;
import nc.vo.pub.pf.PfClientBizProcessContext;
import nc.vo.pub.pf.TransitionSelectableInfo;
import nc.vo.pub.pf.workflow.IPFActionName;
import nc.vo.pub.workflownote.WorkflownoteAttVO;
import nc.vo.pub.workflownote.WorkflownoteVO;
import nc.vo.uap.pf.PFBusinessException;
import nc.vo.uap.pf.RetBackWfVo;
import nc.vo.wfengine.core.activity.Activity;
import nc.vo.wfengine.core.parser.XPDLParserException;
import nc.vo.wfengine.core.workflow.WorkflowProcess;
import nc.vo.wfengine.definition.ActivityTypeEnum;
import nc.vo.wfengine.definition.WorkflowTypeEnum;
import nc.vo.wfengine.engine.ExecuteResult;
import nc.vo.wfengine.pub.WFTask;
import nc.vo.wfengine.pub.WfTaskOrInstanceStatus;
import nc.vo.wfengine.pub.WfTaskType;

import org.apache.commons.lang.StringUtils;

/**
 * �������������ʵ����
 * 
 * @author wzhy 2004-2-21
 * @modifier leijun 2006-4-7 ʹ�ö�̬�����Ʋ����ͷ�����
 * @modifier leijun 2008-8 ���ӹ�������ش���
 * @modifier leijun 2008-12 ������չ���������Ƿ�����װ��VO
 * @modifier guowl 2010-5 6.0����֧��һ�����׵�ģʽ
 */
public class WorkflowMachineImpl implements IWorkflowMachine {

	public WorkflowMachineImpl() {
	}

	/**
	 * �Ƿ�Ϊ����У��Ķ�������
	 * 
	 * @param actionName
	 * @param billType
	 * @return
	 */
	private boolean isCheckAction(String actionName, String billType) {
		if (PfUtilBaseTools.isStartFlowAction(actionName, billType)
				|| PfUtilBaseTools.isSignalFlowAction(actionName, billType))
			return true;
		return false;
	}

	public WorkflownoteVO checkWorkflowActions(String billType,
			String originBillId) throws BusinessException {
		String billid = null;
		try {
			AggregatedValueObject billvo = MobileAppUtil.queryBillEntity(
					billType, originBillId);

			PfParameterVO paraVO = PfUtilBaseTools.getVariableValue(billType,
					IPFActionName.APPROVE, billvo, null, null, null, null,
					new HashMap(), new Hashtable());
			billid = paraVO.m_billVersionPK;
			ActionEnvironment.getInstance().putParaVo(billid, paraVO);

			return new EngineService().checkUnfinishedWorkitem(paraVO,
					WorkflowTypeEnum.Approveflow.getIntValue());
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				throw (BusinessException) e;
			} else {
				throw new BusinessException(e.getMessage(), e);
			}
		} finally {
			ActionEnvironment.getInstance().putParaVo(billid, null);
		}
	}

	public WorkflownoteVO checkWorkFlow(String actionCode, String billType,
			AggregatedValueObject billVO, HashMap hmPfExParams)
			throws BusinessException {
		Logger.init("workflow");
		Logger.debug("*���̼�� EngineService.checkWorkFlow��ʼ");
		Logger.debug("*********************************************");
		Logger.debug("* actionName=" + actionCode);
		Logger.debug("* billType=" + billType);
		Logger.debug("* billEntity=" + billVO);
		Logger.debug("* eParam=" + hmPfExParams);
		Logger.debug("*********************************************");

		long start = System.currentTimeMillis();

		// ���ж϶�������
		if (!isCheckAction(actionCode, billType))
			throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("pfworkflow", "wfMachineImpl-0000", null,
							new String[] { actionCode })/* ���Ϸ��ĵ��ݶ�������={0} */);

		PfBusinessLock pfLock = new PfBusinessLock();
		String strBillId = null;
		try {

			if (hmPfExParams != null
					&& hmPfExParams.get(PfUtilBaseTools.PARAM_NOFLOW) != null) {
				// yanke1 2012-9-27 ��������ָ���˲�������
				// ��ôֱ�ӷ���null
				return null;
			}

			// ���ݼ�����һ����У��
			// �ж��Ƿ���Ҫ���� leijun+2008-12
			Object paramNoLock = hmPfExParams == null ? null : hmPfExParams
					.get(PfUtilBaseTools.PARAM_NO_LOCK);
			if (paramNoLock == null) {
				pfLock.lock(new VOLockData(billVO, billType),
						new VOConsistenceCheck(billVO, billType));
			}

			// �ж��Ƿ���Ҫ���¼���VO leijun+2008-12
			Object paramReloadVO = hmPfExParams == null ? null : hmPfExParams
					.get(PfUtilBaseTools.PARAM_RELOAD_VO);
			if (paramReloadVO != null) {
				String billId = billVO.getParentVO().getPrimaryKey();
				billVO = new PFConfigImpl().queryBillDataVO(billType, billId);
				if (billVO == null)
					throw new PFBusinessException(NCLangRes4VoTransl
							.getNCLangRes().getStrByID("pfworkflow",
									"wfMachineImpl-0001", null,
									new String[] { billType, billId })/*
																	 * ���ݵ�������{0}�͵���
																	 * {
																	 * 1}��ȡ�������ݾۺ�VO
																	 */);
			}

			// XXX:guowl+2010-5 ����ǰ��ҵ������ȫVO
			// ���bd_billtype.checkclassnameע���ҵ����ʵ��
			Object checkObj = PfUtilTools.getBizRuleImpl(billType);
			AggregatedValueObject completeVO = billVO;
			if (checkObj instanceof IPfBeforeAction) {
				completeVO = ((IPfBeforeAction) checkObj).beforeAction(billVO,
						null, hmPfExParams);
			}

			Hashtable hashBilltypeToParavo = new Hashtable();
			PfParameterVO paraVO = PfUtilBaseTools.getVariableValue(billType,
					actionCode, completeVO, null, null, null, null,
					hmPfExParams, hashBilltypeToParavo);
			strBillId = paraVO.m_billVersionPK;
			ActionEnvironment.getInstance().putParaVo(strBillId, paraVO);
			// Ϊ���̶��帳ֵ
			Object paramDefPK = hmPfExParams == null ? null : hmPfExParams
					.get(PfUtilBaseTools.PARAM_FLOWPK);
			paraVO.m_flowDefPK = paramDefPK == null ? null : String
					.valueOf(paramDefPK);
			WorkflownoteVO note = null;
			if (PfUtilBaseTools.isSaveAction(actionCode, billType)) {
				// �ύ����������鹤����
				note = checkApproveflowWhenSave(paraVO);
				setIsAreadyTracked(paraVO, note);
			} else if (PfUtilBaseTools.isStartAction(actionCode, billType)) {
				// ��������������鹤����
				note = checkWorkflowWhenStart(paraVO);
				setIsAreadyTracked(paraVO, note);
			} else if (PfUtilBaseTools.isApproveAction(actionCode, billType)) {
				// ��������鹤����
				note = new EngineService().checkUnfinishedWorkitem(paraVO,
						WorkflowTypeEnum.Approveflow.getIntValue());
				setIsAreadyTracked(paraVO, note);
			} else if (PfUtilBaseTools.isSignalAction(actionCode, billType)) {
				// ��������ת����鹤����
				note = new EngineService().checkUnfinishedWorkitem(paraVO,
						WorkflowTypeEnum.Workflow.getIntValue());
				setIsAreadyTracked(paraVO, note);
			}
			return note;
		} catch (DbException ex) {
			Logger.error(ex.getMessage(), ex);
			throw new PFBusinessException(NCLangResOnserver.getInstance()
					.getStrByID("pfworkflow", "UPPpfworkflow-000004")/*
																	 * ��鹤����ʱ�������ݿ��쳣
																	 * ��
																	 */
					+ ex.getMessage());
		} finally {
			Logger.debug("*���̼�� EngineService.checkWorkFlow��������ʱ"
					+ (System.currentTimeMillis() - start) + "ms");
			// XXX::�����Ƴ�,�����ڴ�й©
			ActionEnvironment.getInstance().putParaVo(strBillId, null);
			// �ͷ���
//			if (pfLock != null) {
//				pfLock.unLock();
//			}
		}
	}

	private void setIsAreadyTracked(PfParameterVO paraVO, WorkflownoteVO note)
			throws BusinessException {
		if (note == null) {
			return;
		}
		String processInsID = note.getTaskInfo() == null ? null : note
				.getTaskInfo().getTask() == null ? null : note.getTaskInfo()
				.getTask().getWfProcessInstancePK();
		if (StringUtils.isNotBlank(processInsID)) {
			IWorkflowAdmin workflowAdmin = NCLocator.getInstance().lookup(
					IWorkflowAdmin.class);
			boolean isTracked = workflowAdmin.isAlreadyTracked(processInsID,
					paraVO.m_operator);
			note.setTrack(isTracked);
		}
	}

	/**
	 * �ڶ���SAVE ����������ʱ �����ã���ѯ������
	 * 
	 * @param paraVO
	 *            ����ִ�в���VO
	 * @return ������������VO
	 * @throws BusinessException
	 */
	private WorkflownoteVO checkApproveflowWhenSave(PfParameterVO paraVO)
			throws BusinessException {
		int status;
		EngineService queryDMO = new EngineService();
		try {
			// ��ѯ���ݵ�����������״̬������̬���ύ̬�������У�ͨ����δͨ��
			status = queryDMO.queryApproveflowStatus(paraVO.m_billVersionPK,
					paraVO.m_billType);
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
			throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("pfworkflow", "wfMachineImpl-0002", null,
							new String[] { e.getMessage() })/*
															 * ��ѯ������״̬�������ݿ��쳣��{0}
															 */);
		}

		if (paraVO.getCustomProperty(PfUtilBaseTools.PARAM_FORCESTART) != null) {
			// ���ָ����FORCESTART����
			// ��ô�˴�ҲҪǿ��ִ��һ��
			// ����������
		} else {
			switch (status) {
			case IPfRetCheckInfo.COMMIT:
			case IPfRetCheckInfo.NOSTATE:
			case IPfRetCheckInfo.NOPASS:
				// ����������
				break;

			case IPfRetCheckInfo.GOINGON:
				// ����������,ȴ�ٴ��ύ,���ؿ�,����Ӱ������
				return null;
			default:
				return null;
			}
		}

		return queryApproveflowOnSave(queryDMO, paraVO, status);
	}

	private WorkflownoteVO queryApproveflowOnSave(EngineService queryDMO,
			PfParameterVO paraVo, int status) throws BusinessException {

		try {
			return queryDMO.queryApproveflowOnSave(paraVo, status);
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
			String msg = NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"pfworkflow", "wfMachineImpl-0003", null,
					new String[] { e.getMessage() })/*
													 * ����ʱ��ѯ�������������ݿ��쳣 �� { 0 }
													 */;
			throw new PFBusinessException(msg, e);
		}

	}

	/**
	 * �ڶ���START ����������ʱ �����ã���ѯ������
	 * 
	 * @param paraVO
	 * @return
	 * @throws BusinessException
	 * @since 5.5
	 */
	private WorkflownoteVO checkWorkflowWhenStart(PfParameterVO paraVO)
			throws BusinessException {
		int status;
		EngineService queryDMO = new EngineService();
		try {
			// ��ѯ���ݵĹ�����״̬������̬���ύ̬�������У�������ͨ����
			status = queryDMO.queryWorkflowStatus(paraVO.m_billVersionPK,
					paraVO.m_billType);
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
			throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("pfworkflow", "wfMachineImpl-0004", null,
							new String[] { e.getMessage() })/*
															 * ��ѯ������״̬�������ݿ��쳣��{0}
															 */);
		}

		if (paraVO.getCustomProperty(PfUtilBaseTools.PARAM_FORCESTART) != null) {
			// ���ָ����FORCE_START����
			// ��ôǿ����������
			// ����������
		} else {
			switch (status) {
			case IPfRetCheckInfo.COMMIT:
			case IPfRetCheckInfo.NOSTATE:
				// ����������
				break;
			case IPfRetCheckInfo.GOINGON:
				// ������������,ȴ�ٴ�����,���ؿ�,����Ӱ������
				return null;
			default:
				return null;
			}
		}

		return queryWorkflowOnSave(queryDMO, paraVO, status);
	}

	private WorkflownoteVO queryWorkflowOnSave(EngineService queryDMO,
			PfParameterVO paraVO, int status) throws BusinessException {
		try {
			return queryDMO.queryWorkflowOnSave(paraVO, status);
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);

			String msg = NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"pfworkflow", "wfMachineImpl-0005", null,
					new String[] { e.getMessage() });/*
													 * ����ʱ��ѯ�������������ݿ��쳣 �� { 0 }
													 */
			throw new PFBusinessException(msg, e);
		}
	}

	public RetBackWfVo backCheckFlow(PfParameterVO paraVo)
			throws BusinessException {
		if (PfUtilBaseTools.isUnapproveAction(paraVo.m_actionName,
				paraVo.m_billType))
			// ����������
			return backWorkflow(paraVo,
					WorkflowTypeEnum.Approveflow.getIntValue());
		else if (PfUtilBaseTools.isRollbackAction(paraVo.m_actionName,
				paraVo.m_billType))
			// ����������
			return backWorkflow(paraVo, WorkflowTypeEnum.Workflow.getIntValue());
		else if (PfUtilBaseTools.isUnSaveAction(paraVo.m_actionName,
				paraVo.m_billType))
			// �������ջ�
			return reCallFlow(paraVo,
					WorkflowTypeEnum.Approveflow.getIntValue());
		else if (PfUtilBaseTools.isRecallAction(paraVo.m_actionName,
				paraVo.m_billType))
			// ������ȡ���ύ
			return reCallFlow(paraVo, WorkflowTypeEnum.Workflow.getIntValue());
		else
			throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("pfworkflow", "wfMachineImpl-0006")/* �Ƿ����ݶ���= */
					+ paraVo.m_actionName);

	}

	/**
	 * �ջء�ȡ���ύ
	 * 
	 * @param paraVo
	 * @return
	 */
	private RetBackWfVo reCallFlow(PfParameterVO paraVo, int wftype)
			throws BusinessException {
		RetBackWfVo retBackVO = new RetBackWfVo();
		ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
				paraVo);
		try {
			EngineService queryDMO = new EngineService();
			// ֻ�б����ύ�����̲ſ��ջ�
			ProcessInstance pi = queryDMO.queryProcessInstance(
					paraVo.m_billVersionPK, paraVo.m_billType, wftype);

			if (pi == null) {
				// �ж��Ƿ������̶���
				WorkflowProcess wp = queryDMO.findProcessOfBill(
						paraVo.m_billVersionPK, paraVo.m_billType, wftype);
				if (wp != null)
					throw new PFBusinessException(NCLangRes4VoTransl
							.getNCLangRes().getStrByID("pfworkflow",
									"wfMachineImpl-0007")/* ������δ�ύ���޷�ȡ���� */);
			} else if (!paraVo.m_operator.equals(pi.getBillMaker())
					&& !paraVo.m_operator.equals(pi.getBillCommiter())) {
				throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes()
						.getStrByID("pfworkflow", "wfMachineImpl-0008")/*
																		 * ֻ��ȡ�������ύ�ĵ���
																		 * ��
																		 */);
			} else {
				// ��ѯ�����˹���Ƿ��Ѵ����Ѵ��������ջأ�
				boolean isFinished = queryDMO.isExistFinishedWorkitem(
						paraVo.m_billVersionPK, paraVo.m_billType, wftype);
				if (isFinished) {
					throw new PFBusinessException(NCLangRes4VoTransl
							.getNCLangRes().getStrByID("pfworkflow",
									"wfMachineImpl-0009")/* ������Ѿ���ɣ��޷�ȡ���ύ�� */);
				}
			}

			// ��ֹ����ʵ�������ݱ�Ϊ����̬
			new WorkflowAdminImpl().terminateWorkflow(paraVo, wftype);

			// ����ִ����ɺ�Ĵ���
			// ״̬����Ϊ����̬
			retBackVO.setBackState(IPfRetCheckInfo.NOSTATE);
			return retBackVO;
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
			throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("pfworkflow", "wfMachineImpl-0010", null,
							new String[] { e.getMessage() })/* ȡ���ύʱ�������ݿ��쳣��{0} */);
		} finally {
			// XXX::�����Ƴ�,�����ڴ�й©
			ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
					null);
		}
	}

	/**
	 * ROLLBACKʱ�Ĺ��������˴���UNAPPROVEʱ��������������, ֻ֧���𼶻���
	 * 
	 * @param paraVo
	 * @param i
	 * @return
	 */
	private RetBackWfVo backWorkflow(PfParameterVO paraVo, int wftype)
			throws BusinessException {

		RetBackWfVo retBackVO = new RetBackWfVo();
		ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
				paraVo);
		try {
			EngineService queryDMO = new EngineService();
			// ��ѯ���� ����ɵĹ�����������
			paraVo.m_workFlow = queryDMO.checkFinishedWorkitem(
					paraVo.m_billType, paraVo.m_billVersionPK,
					paraVo.m_operator, wftype);

			// /û������ɵĹ����Ҳû��δ��ɵĹ��������������������δ��ɵĹ��������������
			if (paraVo.m_workFlow == null) {
				// ���û������ɵĹ������ֱ�ӷ���
				retBackVO.setIsFinish(UFBoolean.TRUE);
				retBackVO.setBackState(IPfRetCheckInfo.NOSTATE);
				return retBackVO;
			}

			if (wftype == WorkflowTypeEnum.Workflow.getIntValue()) {
				canBackWorkflow(paraVo, wftype);
			}

			// ������ʵ���ж��Ƿ����
			boolean isFinished = queryDMO.isFlowFinished(
					paraVo.m_billVersionPK, paraVo.m_billType, wftype);
			if (isFinished) {
				// ��ǰ�����������̽�������˵�
				retBackVO.setIsFinish(UFBoolean.TRUE);
			}

			// ִ�л�������
			WFTask currentTask = paraVo.m_workFlow.getTaskInfo().getTask();
			currentTask.setModifyTime(NCLocator.getInstance()
					.lookup(ITimeService.class).getUFDateTime());
			currentTask.setOutObject(paraVo.m_preValueVo);
			currentTask.setOperator(paraVo.m_operator);
			currentTask
					.setStatus(WfTaskOrInstanceStatus.Finished.getIntValue());
			currentTask.setTaskType(WfTaskType.Withdraw.getIntValue());

			WfTaskManager.getInstance().acceptTaskFromBusi(currentTask);

			// ����ִ����ɺ�Ĵ���
			// ��ѯ�Ƿ����һ���Ѵ�����ɵĹ��������б�ʾ����������Ϊ"������"
			boolean isGoing = queryDMO.isExistFinishedWorkitem(
					paraVo.m_billVersionPK, paraVo.m_billType,
					paraVo.m_workFlow.getWorkflow_type());
			if (isGoing) {
				// ״̬����Ϊ������
				retBackVO.setBackState(IPfRetCheckInfo.GOINGON);
				retBackVO.setPreCheckMan(paraVo.m_workFlow.getSenderman());
				if (isFinished) {
					// �������ڶ���1��ʱ�����һ��������Ҫ֪ͨ�Ƶ���
					WorknoteManager noteMgr = new WorknoteManager();
					noteMgr.sendMessageToBillMaker(
							new AbstractMsgL10NCallback() {

								@Override
								public String getMessage() {
									// ����ռλ������Ϊ��¼����ID��������Ӧ���ﻷ���Զ�����
									return "{wfMachineImpl-0011}"/* ���̻��� */;
								}
							}, paraVo);
				}
			} else {
				// ״̬����Ϊ�ύ̬
				retBackVO.setBackState(IPfRetCheckInfo.COMMIT);
				WorknoteManager noteMgr = new WorknoteManager();
				noteMgr.sendMessageToBillMaker(new AbstractMsgL10NCallback() {

					@Override
					public String getMessage() {
						// ����ռλ������Ϊ��¼����ID��������Ӧ���ﻷ���Զ�����
						return "{wfMachineImpl-0011}"/* ���̻��� */;
					}
				}, paraVo);
			}

			return retBackVO;
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
			throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("pfworkflow", "wfMachineImpl-0012", null,
							new String[] { e.getMessage() })/* ����ʱ�������ݿ��쳣��{0} */);
		} finally {
			// XXX::�����Ƴ�,�����ڴ�й©
			ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
					null);
		}
	}

	// /�������ܷ����
	// /���������ˣ�Ҫ���ǹ�������1��A->B->C->B�͹�������2��A->B->B�������һ��B�Ѿ���ɣ�C�Ѿ���ɣ��ڶ���B����
	private void canBackWorkflow(PfParameterVO paraVo, int wftype)
			throws BusinessException {
		EngineService queryDMO = new EngineService();
		WorkflownoteVO notFinishedWorkFlow = null;
		try {
			// ��ѯ���� δ��ɵĹ�����������
			notFinishedWorkFlow = queryDMO.checkUnfinishedWorkitem(paraVo,
					wftype);
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
			throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("pfworkflow", "wfMachineImpl-0012", null,
							new String[] { e.getMessage() })/* ����ʱ�������ݿ��쳣��{0} */);
		} catch (PFBusinessException pfbe) {
		}

		// /���������ˣ���δ��ɵĹ����û��δ��ɵĹ������������������Ҳ��δ��ɵĹ��������������
		if (notFinishedWorkFlow != null) {
			// �����������ӹ����������������ǣ��ӹ������أ�������
			if (notFinishedWorkFlow.getWorkflow_type() == WorkflowTypeEnum.SubApproveflow
					.getIntValue()
					|| notFinishedWorkFlow.getWorkflow_type() == WorkflowTypeEnum.SubWorkApproveflow
							.getIntValue()
					|| paraVo.m_workFlow.getWorkflow_type() == WorkflowTypeEnum.SubApproveflow
							.getIntValue()
					|| paraVo.m_workFlow.getWorkflow_type() == WorkflowTypeEnum.SubWorkApproveflow
							.getIntValue())
				return;

			ProcessInstance processInstance = WfInstancePool.getInstance()
					.getProcessInstance(
							notFinishedWorkFlow.getTaskInfo().getTask()
									.getWfProcessInstancePK());
			ActivityInstance activityInstance = processInstance
					.findActivityInstanceByPK(notFinishedWorkFlow.getTaskInfo()
							.getTask().getActivityInstancePK());
			ProcessInstance processInstanceFinished = WfInstancePool
					.getInstance().getProcessInstance(
							paraVo.m_workFlow.getTaskInfo().getTask()
									.getWfProcessInstancePK());
			ActivityInstance activityInstanceFinished = processInstanceFinished
					.findActivityInstanceByPK(paraVo.m_workFlow.getTaskInfo()
							.getTask().getActivityInstancePK());
			Vector srcActivityInstanceGUIDs = activityInstance
					.getSrcActivityInstancePKs();
			boolean back = false;// û�й�������2��A->B->B
			for (int i = 0; i < srcActivityInstanceGUIDs.size(); i++) {
				String srcActivityInstanceGUID = (String) srcActivityInstanceGUIDs
						.get(i);
				ActivityInstance actInst = processInstance
						.findActivityInstanceByPK(srcActivityInstanceGUID);
				if (actInst.getActivity().getActivityType() == ActivityTypeEnum.Auto
						.getIntValue()
						|| actInst.getActivity().getActivityType() == ActivityTypeEnum.Route
								.getIntValue()) {
					back = true;
					continue;
				}
				// XXX�����������ܵ��� ����ǰ�ʵ������Դ�Ϊ�û���ѳ���ʵ�������ǲ��ǳ���ʱ�ʵ������Դ��ϵ���ô���
				if (actInst.getActivityID().equals(
						activityInstance.getActivityID())) {
					back = true;
				}

				if (activityInstanceFinished.getActivityID().equals(
						actInst.getActivityID())) {
					back = true;
				}
			}
			if (!back) {
				throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes()
						.getStrByID("pfworkflow", "wfMachineImpl-0020")/*
																		 * ��ǰ�����˵Ĺ�����������δ���
																		 * �����ܻ���
																		 */);
			}
		}
	}

	public void deleteCheckFlow(String billType, String billId,
			AggregatedValueObject billVO, String checkMan)
			throws BusinessException {
		int status;
		try {
			// ״̬��ѯ
			EngineService es = new EngineService();
			status = es.queryApproveflowStatus(billId, billType);
			// lj@2006-11-3 ����ͨ����Ҳ�ɰ�����ʵ��ɾ��
			switch (status) {
			case IPfRetCheckInfo.NOSTATE:
			case IPfRetCheckInfo.COMMIT:
			case IPfRetCheckInfo.NOPASS:
			case IPfRetCheckInfo.PASSING:
				es.rollbackWorkflow(billId, billType, billVO,
						WorkflowTypeEnum.Approveflow.getIntValue());
				es.deleteWorkflow(billId, billType, false,
						WorkflowTypeEnum.Approveflow.getIntValue());
				break;
			case IPfRetCheckInfo.GOINGON:
				// ɾ��������Ϣ
				es.rollbackWorkflow(billId, billType, billVO,
						WorkflowTypeEnum.Approveflow.getIntValue());
				es.deleteWorkflow(billId, billType, false,
						WorkflowTypeEnum.Approveflow.getIntValue());
				// �����������˺��Ƶ��˷�֪ͨ��Ϣ
				WorknoteManager noteMgr = new WorknoteManager();
				noteMgr.sendAllPersonMessage(new AbstractMsgL10NCallback() {

					@Override
					public String getMessage() {
						return "{UPPpfworkflow-000272}"/*
														 * @res "��ɾ��"
														 */;
					}
				}, null, billId, billType, checkMan);
				break;
			}
		} catch (DbException ex) {
			Logger.error(ex.getMessage(), ex);
			throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("pfworkflow", "wfMachineImpl-0013", null,
							new String[] { ex.getMessage() })/* ɾ���������ݳ������ݿ��쳣��{0} */);
		}

	}

	public int forwardCheckFlow(PfParameterVO paraVo) throws BusinessException {
		Logger.error("###WorkflowMachineImpl forwardCheckFlow ��ʼ "
				+ System.currentTimeMillis() + "ms");
		ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
				paraVo);
		WFTask currentTask = paraVo.m_workFlow.getTaskInfo().getTask();
		// ��ʱ�浽ActionEnvironment��
		List<ActivityInstance> lstActIns = null;
		try {
			lstActIns = queryActInsByPrceInsPK(
					currentTask.getWfProcessInstancePK(), new int[] { 0, 1 });
			paraVo.setOldActIns(lstActIns);
		} catch (XPDLParserException e) {
			Logger.error(e.getMessage(), e);
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
		}
		// +����
		// yk1+ 2012-3-8 ��������ȷ��������Ҫ�������λ���
		// currentTask.setAttachmentSetting(getAttachmentOfTask(paraVo.m_workFlow.getPk_wf_task()));

		if (currentTask.getTaskType() == WfTaskType.Backward.getIntValue()) {

			// 1-����
			if (paraVo.m_workFlow.getActiontype().endsWith(
					WorkflownoteVO.WORKITEM_ADDAPPROVER_SUFFIX)) {
				throw new PFBusinessException(nc.vo.ml.NCLangRes4VoTransl
						.getNCLangRes().getStrByID("pfworkflow61_0",
								"0pfworkflow61-0087")/* @res "��ǩ���û��޷����أ�" */);
			}
			// �ύ̬�޷�����

			try {
				int status = new EngineService().queryFlowStatus(
						paraVo.m_billVersionPK, paraVo.m_billType,
						currentTask.getWorkflowType(),
						currentTask.getApproveResult());

				// yanke1 2012-7-18
				// �Ѹ�������ȷ�����κλ��ڶ�Ҫ֧�ֲ���
				// ���ע������ж�
				// if(status == IPfRetCheckInfo.COMMIT)
				// throw new
				// PFBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pfworkflow61_0","0pfworkflow61-0063")/*@res
				// "����״̬���ύ̬��������Ч��"*/);
			} catch (DbException ex) {
				Logger.error(ex.getMessage(), ex);
			}

			// ����Ҫ�� ������,�޸�ʱ��
			currentTask.setModifyTime(NCLocator.getInstance()
					.lookup(ITimeService.class).getUFDateTime());
			currentTask.setOutObject(paraVo.m_preValueVo);
			currentTask.setOperator(paraVo.m_operator);
			currentTask.setNote(paraVo.m_workFlow.getChecknote());
			currentTask
					.setStatus(WfTaskOrInstanceStatus.Finished.getIntValue());
			currentTask.setTaskType(WfTaskType.Backward.getIntValue());

			// FIXME::���Ӳ��ؽ��?! lj@2005-5-16
			currentTask.setApproveResult("R");
		} else {
			// 3-����
			// Ϊ���������� ��̻�Ĳ�����ָ����Ϣ
			fillAssignableInfo(paraVo, currentTask);
			// Ϊ���������� ���ת�ƵĿ��ֹ�ѡ����Ϣ
			fillTransitionSelectableInfo(paraVo, currentTask);
			// ����Ҫ�� ���ҵ������,������,�������,�޸�ʱ��
			currentTask.setOutObject(paraVo.m_preValueVo);
			currentTask.setOperator(paraVo.m_operator);
			if ("Y".equals(paraVo.m_workFlow.getApproveresult())) {
				currentTask.setApproveResult("Y");
			} else {
				currentTask.setApproveResult("N");
			}
			currentTask.setModifyTime(NCLocator.getInstance()
					.lookup(ITimeService.class).getUFDateTime());
			currentTask.setNote(paraVo.m_workFlow.getChecknote());
			currentTask
					.setStatus(WfTaskOrInstanceStatus.Finished.getIntValue());
		}

		// �����淢������
		Logger.error("###WorkflowMachineImpl call acceptTaskFromBusi ��ʼ "
				+ System.currentTimeMillis() + "ms");
		WfTaskManager.getInstance().acceptTaskFromBusi(currentTask);
		Logger.error("###WorkflowMachineImpl call forwardCheckFlow ���� "
				+ System.currentTimeMillis() + "ms");
		// ������ɺ�Ѷ�Ӧ����Ϣ״̬��Ϊ�Ѵ���
		PfMessageUtil.setHandled(paraVo.m_workFlow);

		PfParameterVO tmpparaVo = ActionEnvironment.getInstance().getParaVo(
				paraVo.m_billVersionPK + "@auto");
		if (tmpparaVo == null)
			tmpparaVo = ActionEnvironment.getInstance().getParaVo(
					paraVo.m_billVersionPK);

		// XXX zhouzhenga ���ÿ���������paraVO��û�и���
		// ����ǰ����ԭ�й��������ݹ�����ʹ��
		WorkflownoteVO curTaskNoteVO = paraVo.m_workFlow;
		// paraVo = tmpparaVo;
		paraVo.m_billType = tmpparaVo.m_billType;
		paraVo.emendEnum = tmpparaVo.emendEnum;
		paraVo.m_actionName = tmpparaVo.m_actionName;
		paraVo.m_autoApproveAfterCommit = tmpparaVo.m_autoApproveAfterCommit;
		paraVo.m_billEntities = tmpparaVo.m_billEntities;
		paraVo.m_billEntity = tmpparaVo.m_billEntity;
		paraVo.m_billId = tmpparaVo.m_billId;
		paraVo.m_billNo = tmpparaVo.m_billNo;
		paraVo.m_billVersionPK = tmpparaVo.m_billVersionPK;
		//modifier fengjks ������һ���������Զ������Ժ��޷���������������bug
		String oldBusitype = paraVo.m_businessType;
		paraVo.m_businessType = tmpparaVo.m_businessType;
		paraVo.m_flowDefPK = tmpparaVo.m_flowDefPK;
		paraVo.m_makeBillOperator = tmpparaVo.m_makeBillOperator;
		paraVo.m_operator = tmpparaVo.m_operator;
		paraVo.m_pkGroup = tmpparaVo.m_pkGroup;
		paraVo.m_pkOrg = tmpparaVo.m_pkOrg;
		paraVo.m_preValueVo = tmpparaVo.m_preValueVo;
		paraVo.m_preValueVos = tmpparaVo.m_preValueVos;
		paraVo.m_splitValueVos = tmpparaVo.m_splitValueVos;
		paraVo.m_standHeadVo = tmpparaVo.m_standHeadVo;
		paraVo.m_userObj = tmpparaVo.m_userObj;
		paraVo.m_userObjs = tmpparaVo.m_userObjs;
		paraVo.m_workFlow = tmpparaVo.m_workFlow;

		// ���ݵ��ݵ�ǰ����״̬,������ ��Ч����
		int status = -10;
		try {
			EngineService engine = new EngineService();
			int iCurrentWfType = currentTask.getWorkflowType();
			status = engine.queryFlowStatus(paraVo.m_billVersionPK,
					paraVo.m_billType, iCurrentWfType,
					currentTask.getApproveResult());
			if (status == IPfRetCheckInfo.PASSING
					|| status == IPfRetCheckInfo.NOPASS) {
				// ���̽���������δ��ɵĻʵ����Ϊ��Ч
				engine.updateUnfinishedActInstancesToInefficient(
						currentTask.getBillversionPK(),
						currentTask.getBillType(), iCurrentWfType);
				if(status == IPfRetCheckInfo.PASSING && paraVo.m_businessType == null && oldBusitype != null){
					paraVo.m_businessType = oldBusitype;
				}
			} else if (status == IPfRetCheckInfo.NOSTATE) {
				// ���ݻص�����̬�����лʵ����Ϊ��Ч
				if (!currentTask.isSubmit2RjectTache())
					engine.updateAllActivityInstancesToInefficient(
							currentTask.getBillID(), currentTask.getBillType(),
							iCurrentWfType);
			}

			// XXX:leijun+2010-2 ����ʱ��Ҳ��Ҫ���˵������
			// if (currentTask.isBackToFirstActivity()) {
			// engine.rollbackWorkflow(paraVo.m_billVersionPK,
			// paraVo.m_billType, paraVo.m_preValueVo, iCurrentWfType);
			// }
			// ���ص��������
			if (currentTask.getTaskType() == WfTaskType.Backward.getIntValue()) {
				engine.rollbackGadgets(paraVo);
			}
			// ������Ϣ
			WorknoteManager noteMgr = new WorknoteManager();
			IMsgL10NCallback msgStr = null;
			// ������ɺ������˷���ҵ����Ϣ��������Ҫ���� ��ǩ�����ɣ� @2009-06-24 dingxm
			if (!StringUtil.isEmptyWithTrim(paraVo.m_workFlow.getObserver())) {
				String actiontype = paraVo.m_workFlow.getActiontype();
				if (!StringUtil.isEmptyWithTrim(actiontype)) {
					if (actiontype
							.endsWith(WorkflownoteVO.WORKITEM_ADDAPPROVER_SUFFIX)) {
						msgStr = new AbstractMsgL10NCallback() {

							// �������ռλ��
							@Override
							public String getMessage() {
								return "{wfMachineImpl-0014}"/* ��ǩ�������Ѵ��� */;
							}
						};
					} else if (actiontype
							.endsWith(WorkflownoteVO.WORKITEM_APPOINT_SUFFIX)) {
						msgStr = new AbstractMsgL10NCallback() {

							// �������ռλ��
							@Override
							public String getMessage() {
								return "{wfMachineImpl-0015}"/* ���ɹ������Ѵ��� */;
							}
						};
					}

					if (msgStr != null) {
						noteMgr.sendMessage(msgStr, paraVo,
								paraVo.m_workFlow.getObserver());
					}
				}
			}

			if (status == IPfRetCheckInfo.PASSING) {
				// �������ͨ��,����Ƶ��˷��ͷ�ҵ����Ϣ
				Logger.debug("***����ʵ������֮����ͨ��,����Ƶ��˷���ҵ����Ϣ***");
				final boolean isWorkflow = WorkflowTypeEnum
						.isWorkflowInstance(iCurrentWfType);

				msgStr = new AbstractMsgL10NCallback() {

					@Override
					public String getMessage() {
						return isWorkflow ? "{UPPpfworkflow-000701}"/* "�������������" */
						: "{UPPpfworkflow-000273}"/* "����ͨ��" */;
					}
				};
				noteMgr.sendMessageToBillMaker(msgStr, paraVo);

			} else if (status == IPfRetCheckInfo.NOPASS) {
				// ���������ͨ��,������뱾�����̵��˷��ͷ�ҵ����Ϣ
				Logger.debug("***����ʵ������֮������ͨ��,������뱾�����̵��˷���ҵ����Ϣ***");

				msgStr = new AbstractMsgL10NCallback() {

					@Override
					public String getMessage() {
						return "{UPPpfworkflow-000274}"/* "������ͨ��" */;
					}
				};
				noteMgr.sendMessageAfterNoPass(msgStr, paraVo);
			}

			// 2011-7-13 wcj ����г�����Ϣ���ڴ˷���
			// yanke1+ 2011-7-14 ��ǰ�������������̸���
			IWorkflowAdmin wfAdmin = NCLocator.getInstance().lookup(
					IWorkflowAdmin.class);

			// �������̸���
			boolean isTrack = paraVo.m_workFlow.isTrack();
			wfAdmin.trackWFinstance(paraVo.m_workFlow,
					paraVo.m_workFlow.getCheckman(), isTrack);

			// ���ͳ�����Ϣ
			Logger.debug("***��������Ϣ***");
			String result = currentTask.getApproveResult();
			String title = "";
			if ("Y".equals(result)) {
				title = WorkflowTypeEnum.isWorkflowInstance(iCurrentWfType) ? "{UPPpfworkflow-000701}"/* "�������������" */
						: "{UPPpfworkflow-000273}"/* "����ͨ��" */;
			} else if ("N".equals(result)) {
				title = "{UPPpfworkflow-000274}"/* "������ͨ��" */;
			} else if ("R".equals(result)) {
				title = "{UPPpfworkflow-000804}"/* "����" */;
			}

			String note = curTaskNoteVO.getChecknote();
			wfAdmin.cpySendByMailAndMsg(curTaskNoteVO, new String[] { title,
					note });

		} catch (DbException ex) {
			Logger.error(ex.getMessage(), ex);
			throw new PFBusinessException(
					NCLangRes4VoTransl.getNCLangRes().getStrByID("pfworkflow",
							"wfMachineImpl-0016", null,
							new String[] { ex.getMessage() })/* ������ת�������ݿ��쳣��{0} */,
					ex);
		} finally {
			// XXX::�����Ƴ�,�����ڴ�й©
			ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
					null);
			ActionEnvironment.getInstance().putParaVo(
					paraVo.m_billVersionPK + "@auto", null);
		}
		Logger.error("###WorkflowMachineImpl forwardCheckFlow ���� "
				+ System.currentTimeMillis() + "ms");
		return status;
	}

	private List<AttachmentVO> getAttachmentOfTask(String pk_wf_task)
			throws BusinessException {
		BaseDAO dao = new BaseDAO();
		EnginePersistence persist = new EnginePersistence();

		String sql = null;
		SQLParameter param = null;

		sql = "select pk_wf_instance from pub_wf_task where pk_wf_task=?";
		param = new SQLParameter();
		param.addParam(pk_wf_task);

		String pk_wf_instance = (String) dao.executeQuery(sql, param,
				new ColumnProcessor());

		List<String> instList = new ArrayList<String>();

		while (!StringUtil.isEmptyWithTrim(pk_wf_instance)) {
			instList.add(pk_wf_instance);

			pk_wf_instance = persist
					.findParentProcessInstancePK(pk_wf_instance);
		}

		if (instList.size() == 0) {
			return null;
		}

		param = new SQLParameter();
		param.addParam(WfTaskOrInstanceStatus.Inefficient.getIntValue());
		StringBuffer sb = new StringBuffer();

		for (String pk : instList) {
			sb.append(",?");
			param.addParam(pk);
		}

		sql = "select t.* from pub_workflownote_att t join pub_workflownote n on t.pk_checkflow=n.pk_checkflow join pub_wf_task k on n.pk_wf_task=k.pk_wf_task where n.approvestatus<>? and k.pk_wf_instance in ("
				+ sb.substring(1) + ") order by t.ts desc";

		Collection<WorkflownoteAttVO> col = (Collection<WorkflownoteAttVO>) dao
				.executeQuery(sql, param, new BeanListProcessor(
						WorkflownoteAttVO.class));

		List<AttachmentVO> attList = new ArrayList<AttachmentVO>();

		for (WorkflownoteAttVO noteAttVO : col) {
			AttachmentVO attVO = new AttachmentVO();

			attVO.setPk_file(noteAttVO.getPk_file());
			attVO.setFilename(noteAttVO.getFilename());
			attVO.setFilesize(noteAttVO.getFilesize());

			attList.add(attVO);
		}

		return attList;
	}

	public boolean sendWorkFlowOnSave(PfParameterVO paraVo,
			Hashtable m_methodReturnHas, HashMap hmPfExParams)
			throws BusinessException {
		Logger.debug("BILLNO**********" + paraVo.m_billNo + "**********");
		Logger.debug("BILLID**********" + paraVo.m_billVersionPK + "**********");

		ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
				paraVo);
		ActionEnvironment.getInstance().putMethodReturn(paraVo.m_billVersionPK,
				m_methodReturnHas);
		try {
			boolean isWorkflow = PfUtilBaseTools.isStartAction(
					paraVo.m_actionName, paraVo.m_billType);
			if (!isWorkflow) {
				// ����������
				boolean[] wfRet = startApproveflow(paraVo, hmPfExParams);
				return wfRet[0];
			} else {
				// ����������
				boolean[] wfRet = startWorkflow(paraVo, hmPfExParams);
				return wfRet[0];
			}

		} finally {
			// XXX::�����Ƴ�,�����ڴ�й©
			ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
					null);
			ActionEnvironment.getInstance().putMethodReturn(
					paraVo.m_billVersionPK, null);
		}
	}

	/**
	 * ����������
	 * 
	 * @param paraVo
	 * @param hmPfExParams
	 * @return
	 * @throws BusinessException
	 */
	private boolean[] startWorkflow(PfParameterVO paraVo, HashMap hmPfExParams)
			throws BusinessException {
		Logger.debug("��������Ϊ" + paraVo.m_actionName + "������������");

		// ����ʱ��ɾ����������Ϣ������״̬Ϊ"ͨ����������"�ĳ���
		deleteWhenStartExceptPassOrGoing(paraVo,
				WorkflowTypeEnum.Workflow.getIntValue());

		if (paraVo.m_workFlow == null
				|| paraVo.m_workFlow.getTaskInfo().getTask() == null) {
			// �����չ�����к������̶���PK����ֱ�Ӹ�ֵ
			Object paramDefPK = hmPfExParams == null ? null : hmPfExParams
					.get(PfUtilBaseTools.PARAM_FLOWPK);
			paraVo.m_flowDefPK = paramDefPK == null ? null : String
					.valueOf(paramDefPK);

			Object noteChecked = hmPfExParams == null ? null : hmPfExParams
					.get(PfUtilBaseTools.PARAM_NOTE_CHECKED);
			// leijun+2009-7 ���ǰ̨PfUtilClient.runAction�Ѿ���鲻�����������Ҳû�б�Ҫ�ٴμ��
			if (noteChecked == null)
				paraVo.m_workFlow = checkWorkflowWhenStart(paraVo);
			if (paraVo.m_workFlow == null)
				// ���ݵĹ�����״̬���ڽ����У������
				return new boolean[] { false, false };
		}

		WFTask currentTask = paraVo.m_workFlow.getTaskInfo().getTask();
		// �������ݺź����ɵ����.
		currentTask.setBillNO(paraVo.m_billNo);
		currentTask.setBillID(paraVo.m_billId);
		currentTask.setBillversionPK(paraVo.m_billVersionPK);

		// Ϊ���������� ��̻�Ĳ�����ָ����Ϣ
		fillAssignableInfo(paraVo, currentTask);
		// Ϊ���������� ���ת�ƵĿ��ֹ�ѡ����Ϣ
		fillTransitionSelectableInfo(paraVo, currentTask);

		// ����Ҫ�� ���ҵ������,������,�޸�ʱ��
		currentTask.setOutObject(paraVo.m_preValueVo);
		// currentTask.setOperator(paraVo.m_operator);
		currentTask.setOperator(paraVo.m_makeBillOperator); // �Ƶ�����
		currentTask.setModifyTime(NCLocator.getInstance()
				.lookup(ITimeService.class).getUFDateTime());
		currentTask.setStatus(WfTaskOrInstanceStatus.Finished.getIntValue()); // �Ƶ����������

		// ִ������
		ExecuteResult result = WfTaskManager.getInstance().acceptTaskFromBusi(
				currentTask);
		if (result != null && result.isApprovePass()) {
			// �������������漴������ͨ����
			// throw new ApproveAfterCommitException(IPfRetCheckInfo.PASSING,
			// NCLangResOnserver
			// .getInstance().getStrByID("pfworkflow", "UPPpfworkflow-000273")/*
			// ����ͨ�� */);
			return new boolean[] { true, false };
		} else

			return new boolean[] { true, false };
	}

	/**
	 * ����ʱ��ɾ����������Ϣ������״̬Ϊ"ͨ����������"�ĳ��� <li>ֻ������̬���ύ̬��δͨ��̬����ɾ��
	 * 
	 * @param paraVo
	 * @param iWorkflowOrApproveflow
	 * @throws BusinessException
	 */
	private void deleteWhenStartExceptPassOrGoing(PfParameterVO paraVo,
			int iWorkflowOrApproveflow) throws BusinessException {
		int status;
		EngineService es = new EngineService();
		try {
			// ��ѯ���ݵ�����������״̬������̬���ύ̬�������У�ͨ����δͨ��
			status = iWorkflowOrApproveflow == WorkflowTypeEnum.Workflow
					.getIntValue() ? es.queryWorkflowStatus(
					paraVo.m_billVersionPK, paraVo.m_billType) : es
					.queryApproveflowStatus(paraVo.m_billVersionPK,
							paraVo.m_billType);
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
			throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("pfworkflow", "wfMachineImpl-0017", null,
							new String[] { e.getMessage() })/* ��ѯ����״̬�������ݿ��쳣��{0} */);
		}

		if (paraVo.getCustomProperty(PfUtilBaseTools.PARAM_FORCESTART) != null) {
			// ��������ָ����forcestart
			// ��ô��ʹ���������״̬ҲҪɾ������
			// ����������
		} else {
			/*
			 * ����������ʱ��ɾ���ɵ���������Ϣ��������������ʷ�� 1)����̬�����ػ���ת���Ƶ����ڣ��������ύ�������µ�����������
			 * 2)�ύ̬����ͨ��̬�������ύ�������µ�����������
			 */
			switch (status) {
			case IPfRetCheckInfo.COMMIT:
			case IPfRetCheckInfo.NOSTATE:
			case IPfRetCheckInfo.NOPASS:
				// ����������
				break;
			default:
				// ����
				return;
			}
		}

		deleteWorkflow(es, paraVo, iWorkflowOrApproveflow);
	}

	private void deleteWorkflow(EngineService es, PfParameterVO paraVo, int type)
			throws BusinessException {
		try {
			es.rollbackWorkflow(paraVo.m_billVersionPK, paraVo.m_billType,
					paraVo.m_preValueVo, type);
			es.deleteWorkflow(paraVo.m_billVersionPK, paraVo.m_billType, true,
					type);
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);

			String msg = NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"pfworkflow", "wfMachineImpl-0018", null,
					new String[] { e.getMessage() });/* ɾ���������������ݿ��쳣��{0} */
			throw new PFBusinessException(msg, e);
		}
	}

	/**
	 * ����������
	 * 
	 * @param paraVo
	 * @param hmPfExParams
	 * @return
	 * @throws BusinessException
	 */
	private boolean[] startApproveflow(PfParameterVO paraVo,
			HashMap hmPfExParams) throws BusinessException {
		Logger.debug("��������Ϊ" + paraVo.m_actionName + "������������");

		if (paraVo.m_workFlow == null
				|| (paraVo.m_workFlow.getTaskInfo().getTask() != null && StringUtil
						.isEmptyWithTrim(paraVo.m_workFlow.getTaskInfo()
								.getTask().getRejectTacheActivityID())))
			// ����ʱ��ɾ����������Ϣ������״̬Ϊ"ͨ����������"�ĳ���
			deleteWhenStartExceptPassOrGoing(paraVo,
					WorkflowTypeEnum.Approveflow.getIntValue());

		if (paraVo.m_workFlow == null
				|| paraVo.m_workFlow.getTaskInfo().getTask() == null) {
			// �����չ�����к������̶���PK����ֱ�Ӹ�ֵ
			Object paramDefPK = hmPfExParams == null ? null : hmPfExParams
					.get(PfUtilBaseTools.PARAM_FLOWPK);
			paraVo.m_flowDefPK = paramDefPK == null ? null : String
					.valueOf(paramDefPK);

			Object noteChecked = hmPfExParams == null ? null : hmPfExParams
					.get(PfUtilBaseTools.PARAM_NOTE_CHECKED);
			// leijun+2009-7 ���ǰ̨PfUtilClient.runAction�Ѿ���鲻�����������Ҳû�б�Ҫ�ٴμ��
			if (noteChecked == null)
				paraVo.m_workFlow = checkApproveflowWhenSave(paraVo);
			if (paraVo.m_workFlow == null)
				// ����̬���޿����������̶��壻�����С�����ͨ��
				return new boolean[] { false, false };
		}

		WFTask startTask = paraVo.m_workFlow.getTaskInfo().getTask();
		// �������ݺź����ɵ����.
		startTask.setBillNO(paraVo.m_billNo);
		startTask.setBillID(paraVo.m_billId);
		startTask.setBillversionPK(paraVo.m_billVersionPK);

		// �Ѻ�̻�Ĳ�����ָ����Ϣ��ֵ���������
		fillAssignableInfo(paraVo, startTask);

		// ����Ҫ�� ���ҵ������,������,�޸�ʱ��
		startTask.setOutObject(paraVo.m_preValueVo);

		// yanke1 2013-4-18
		// startTask.setOperator(paraVo.m_operator);
		// ͨ��task��operatorӦΪ��ǰ��¼�˵�pk
		// ���Ƕ����Ƶ��
		// ����ϣ��ƥ�����̡��Լ��������ϼ��޶�ʱ�����Ƶ�������
		// ������￪һ������
		// startTask��operatorҪȡ�Ƶ���
		startTask.setOperator(paraVo.m_makeBillOperator);

		startTask.setModifyTime(NCLocator.getInstance()
				.lookup(ITimeService.class).getUFDateTime());
		startTask.setStatus(WfTaskOrInstanceStatus.Finished.getIntValue()); // �Ƶ����������

		/** ִ������ */
		ExecuteResult result = WfTaskManager.getInstance().acceptTaskFromBusi(
				startTask);
		if (result != null && result.isApprovePass()) {
			// �ύ������ͨ��
			return new boolean[] { true, true };
			// xry 2011.6.28 �쳣��ʽ��ֹ������ִ�����̣����һع������񣬵�������ʵ����Ϣû�д浽���ݿ�
			// throw new ApproveAfterCommitException(IPfRetCheckInfo.PASSING,
			// NCLangResOnserver.getInstance().getStrByID("pfworkflow",
			// "UPPpfworkflow-000273")/* ����ͨ�� */);
		} else
			return new boolean[] { true, false };
	}

	/**
	 * �Ѻ�̻�Ĳ�����ָ����Ϣ��ֵ���������
	 * 
	 * @param paraVo
	 * @param currentTask
	 */
	private void fillAssignableInfo(PfParameterVO paraVo, WFTask currentTask) {
		Vector assInfos = paraVo.m_workFlow.getTaskInfo().getAssignableInfos();
		for (int i = 0; i < assInfos.size(); i++) {
			AssignableInfo assInfo = (AssignableInfo) assInfos.get(i);
			currentTask.setAssignNextOperators(
					WFTask.getActAssignID(assInfo.getProcessDefPK(),
							assInfo.getActivityDefId()),
					assInfo.getAssignedOperatorPKs());
		}
	}

	/**
	 * �Ѻ��ת�ƵĿ��ֹ�ѡ����Ϣ��ֵ���������
	 * 
	 * @param paraVo
	 * @param currentTask
	 */
	private void fillTransitionSelectableInfo(PfParameterVO paraVo,
			WFTask currentTask) {
		Vector tsInfos = paraVo.m_workFlow.getTaskInfo()
				.getTransitionSelectableInfos();
		for (int i = 0; i < tsInfos.size(); i++) {
			TransitionSelectableInfo tsInfo = (TransitionSelectableInfo) tsInfos
					.get(i);
			if (tsInfo.isChoiced()) {
				Vector vec = new Vector();
				vec.add(tsInfo.getTransitionDefId());
				currentTask.setAssignNextTransition(WFTask.getTransAssignID(
						tsInfo.getProcessDefPK(), tsInfo.getTransitionDefId()),
						vec);
			}
		}
	}

	@Override
	public Object processSingleBillFlow_RequiresNew(String actionName,
			String billOrTranstype, WorkflownoteVO workflowVo,
			AggregatedValueObject billvo, Object userObj, HashMap param)
			throws BusinessException {
		try {
			return NCLocator
					.getInstance()
					.lookup(IPFBusiAction.class)
					.processAction(actionName, billOrTranstype, workflowVo,
							billvo, userObj, param);
		} catch (Exception e) {
			throw e;
		} finally {
			new AttachmentReturnExcutor().postProcess();
		}
	}

	@Override
	public boolean[] sendWorkFlowOnSave_RequiresNew(PfParameterVO paraVo,
			Hashtable returnHas, HashMap hmPfExParams) throws BusinessException {
		boolean[] res = sendWorkFlowOnSaveWithFinishJudge(paraVo, returnHas,
				hmPfExParams);
		return res;
		// Logger.debug("BILLNO**********" + paraVo.m_billNo + "**********");
		// Logger.debug("BILLID**********" + paraVo.m_billVersionPK +
		// "**********");
		//
		// ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
		// paraVo);
		// ActionEnvironment.getInstance().putMethodReturn(paraVo.m_billVersionPK,
		// returnHas);
		// try {
		// boolean isWorkflow = PfUtilBaseTools.isStartAction(
		// paraVo.m_actionName, paraVo.m_billType);
		// if (!isWorkflow)
		// // ����������
		// return startApproveflow(paraVo, hmPfExParams);
		// else
		// // ����������
		// return startWorkflow(paraVo, hmPfExParams);
		//
		// } finally {
		// // XXX::�����Ƴ�,�����ڴ�й©
		// ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
		// null);
		// ActionEnvironment.getInstance().putMethodReturn(paraVo.m_billVersionPK,
		// null);
		// }
	}

	public RetBackWfVo reCallFlow_RequiresNew(PfParameterVO paraVo, int wftype)
			throws BusinessException {
		Logger.debug("BILLNO**********" + paraVo.m_billNo + "**********");
		Logger.debug("BILLID**********" + paraVo.m_billVersionPK + "**********");

		return reCallFlow(paraVo, wftype);
	}

	public String getBillMaker(String processInstPk) throws BusinessException {
		// FIXME::��ѯ������ʵ�����Ƶ���
		EnginePersistence persistenceDmo = new EnginePersistence();
		ProcessInstance instance;
		try {
			instance = persistenceDmo.loadProcessInstance(processInstPk);
			return instance.getBillMaker();
		} catch (DbException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("pfworkflow", "wfMachineImpl-0019")/*
																	 * ��ȡ����ʵ���ķ�����ʧ�ܣ�
																	 */);
		}
	}

	@Override
	public String findParentProcessInstancePK(String subProcessInstancePK)
			throws DAOException {
		return new EnginePersistence()
				.findParentProcessInstancePK(subProcessInstancePK);
	}

	@Override
	public boolean[] sendWorkFlowOnSaveWithFinishJudge(PfParameterVO paraVo,
			Hashtable returnHas, HashMap hmPfExParams) throws BusinessException {
		Logger.debug("BILLNO**********" + paraVo.m_billNo + "**********");
		Logger.debug("BILLID**********" + paraVo.m_billVersionPK + "**********");

		ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
				paraVo);
		ActionEnvironment.getInstance().putMethodReturn(paraVo.m_billVersionPK,
				returnHas);
		try {
			boolean isWorkflow = PfUtilBaseTools.isStartAction(
					paraVo.m_actionName, paraVo.m_billType);
			if (!isWorkflow) {
				// ����������
				boolean[] res = startApproveflow(paraVo, hmPfExParams);
				
				/**
				 * HK 2020��11��12��21:30:47
				 */
				if (true) {
					String id = paraVo.m_billId;
					String type = paraVo.m_billType;
					if (
						type.startsWith("F3")	// ����
					) {
						// �����������ñ�����ڵģ��Ž������²�����
						boolean isExist = false;
						HkOaSettingVO[] vos = (HkOaSettingVO[])(new HYPubBO().queryByCondition(HkOaSettingVO.class, "dr=0 and pk_billtypecode = '"+type+"'"));
						if (vos != null && vos.length > 0) isExist = true;
						if (isExist) {
							BaseDAO dao = new BaseDAO();
							String updateSQL_1 = 
								" update sm_msg_content " +
								" set receiver = receiver || '-oa' " +
								" where detail like '" + id + "@" + type + "@%' " +
								" and destination = 'inbox' "
							;
							String updateSQL_2 = 
								" update pub_workflownote " +
								" set checkman = checkman || '-oa' " +
								" where pk_billtype = '" + type + "' and billid = '" + id + "' "
							;
							Integer res_1 = dao.executeUpdate(updateSQL_1);
							Integer res_2 = dao.executeUpdate(updateSQL_2);
						}
					}
				}
				/***END***/
				
				return res;
			}
			else
				// ����������
				return startWorkflow(paraVo, hmPfExParams);

		} finally {
			// XXX::�����Ƴ�,�����ڴ�й©
			ActionEnvironment.getInstance().putParaVo(paraVo.m_billVersionPK,
					null);
			ActionEnvironment.getInstance().putMethodReturn(
					paraVo.m_billVersionPK, null);
		}
	}

	@Override
	public PFClientBizRetVO executeClientBizProcess(
			AggregatedValueObject billVo, WorkflownoteVO wfVo,
			boolean isMakeBill) throws BusinessException {
		if (wfVo != null && wfVo.getApplicationArgs() != null
				&& wfVo.getApplicationArgs().size() > 0) {
			String billtype = wfVo.getTaskInfo().getTask().getBillType();

			PFClientBizRetVO ret = null;
			Throwable e = null;

			try {
				ret = executeClient(billtype, billVo, wfVo, isMakeBill);

				if (ret != null) {
					return ret;
				}
			} catch (Throwable ex) {
				Logger.error(ex.getMessage(), ex);
				e = ex;
			}

			try {
				Logger.debug("begin execute bs");
				ret = executeBS(billtype, billVo, wfVo, isMakeBill);
				Logger.debug("after execute bs, ret is " + ret);

				if (ret != null) {
					return ret;
				}
			} catch (Exception ex) {
				Logger.error(ex.getMessage(), ex);
				if (ex instanceof BusinessException) {
					throw (BusinessException) ex;
				} else {
					throw new BusinessException(ex.getMessage(), ex);
				}
			}

			// ���������������ҵ���鶼��ʵ��bs�Ľӿ�
			// ��˴�ʱӦ���׳�ִ��ui�ӿ�ʱ���쳣����һ����ʾ
			if (e != null) {
				if (e instanceof BusinessException) {
					throw (BusinessException) e;
				} else {
					throw new BusinessException(e.getMessage(), e);
				}
			}

		}
		Logger.debug("no result for client biz process");
		return null;

	}

	private PFClientBizRetVO executeBS(String billtype,
			AggregatedValueObject billVo, WorkflownoteVO wfVo,
			boolean isMakeBill) throws Exception {
		ArrayList<Billtype2VO> bt2VOs = PfDataCache.getBillType2Info(billtype,
				ExtendedClassEnum.PROC_CLIENT_BS.getIntValue());

		Logger.debug("billtype: " + billtype + " has been registed "
				+ bt2VOs.size() + " IPFClientBizProcessBS implementations");

		// ʵ����
		for (Iterator iterator = bt2VOs.iterator(); iterator.hasNext();) {
			Billtype2VO bt2VO = (Billtype2VO) iterator.next();

			Logger.debug("IPFClientBizProcessBS implementation name: "
					+ bt2VO.getClassname());

			Object obj = PfUtilTools.findBizImplOfBilltype(billtype,
					bt2VO.getClassname());
			PfClientBizProcessContext context = new PfClientBizProcessContext();
			context.setBillvo(billVo);
			context.setArgsList(wfVo.getApplicationArgs());
			context.setMakeBill(isMakeBill);
			PFClientBizRetVO retVO = ((IPFClientBizProcessBS) obj)
					.executeBS(context);

			return retVO;
		}

		return null;
	}

	private PFClientBizRetVO executeClient(String billtype,
			AggregatedValueObject billVo, WorkflownoteVO wfVo,
			boolean isMakeBill) throws Exception {
		ArrayList<Billtype2VO> bt2VOs = PfDataCache.getBillType2Info(billtype,
				ExtendedClassEnum.PROC_CLIENT.getIntValue());

		try {
			// ʵ����
			for (Iterator iterator = bt2VOs.iterator(); iterator.hasNext();) {
				Billtype2VO bt2VO = (Billtype2VO) iterator.next();

				Object obj = PfUtilTools.findBizImplOfBilltype(billtype,
						bt2VO.getClassname());
				PfClientBizProcessContext context = new PfClientBizProcessContext();
				context.setBillvo(billVo);
				context.setArgsList(wfVo.getApplicationArgs());
				context.setMakeBill(isMakeBill);
				PFClientBizRetObj retObj = ((IPFClientBizProcess) obj).execute(
						null, context);
				PFClientBizRetVO retVO = new PFClientBizRetVO();

				retVO.setShowNoPass(retObj.isShowNoPass());
				retVO.setShowPass(retObj.isShowPass());
				retVO.setShowReject(retObj.isShowReject());
				retVO.setStopFlow(retObj.isStopFlow());
				retVO.setHintMessage(retObj.getHintMessage());

				return retVO;
			}

			return null;
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public WFTask getWFTask(String pk_wf_task) throws BusinessException {
		try {
			WFTask task = new TaskManagerDMO().getTaskByPK(pk_wf_task);
			return task;
		} catch (DbException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@Override
	public WorkflownoteVO checkWorkflowActions(String billType,
			String originBillId, String pk_checkflow) throws BusinessException {
		// TODO Auto-generated method stub
		String billid = null;
		try {
			AggregatedValueObject billvo = MobileAppUtil.queryBillEntity(
					billType, originBillId);

			PfParameterVO paraVO = PfUtilBaseTools.getVariableValue(billType,
					IPFActionName.APPROVE, billvo, null, null, null, null,
					new HashMap(), new Hashtable());
			billid = paraVO.m_billVersionPK;
			ActionEnvironment.getInstance().putParaVo(billid, paraVO);

			return new EngineService().checkUnfinishedWorkitemByPKckeckflow(
					paraVO, WorkflowTypeEnum.Approveflow.getIntValue(),
					pk_checkflow);
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				throw (BusinessException) e;
			} else {
				throw new BusinessException(e.getMessage(), e);
			}
		} finally {
			ActionEnvironment.getInstance().putParaVo(billid, null);
		}
	}

	/**
	 * liningc +20140804
	 * 
	 * @param prceIns
	 * @param states
	 * @return
	 * @throws DbException
	 * @throws XPDLParserException
	 * @throws BusinessException
	 */
	private List<ActivityInstance> queryActInsByPrceInsPK(String prceIns,
			int[] states) throws DbException, XPDLParserException,
			BusinessException {
		PersistenceManager persist = null;
		try {
			persist = PersistenceManager.getInstance();
			JdbcSession jdbc = persist.getJdbcSession();
			String stateIn = "";

			for (int s : states) {
				stateIn = stateIn + "," + s;
			}
			if (stateIn.length() > 0)
				stateIn = stateIn.substring(1);
			SQLParameter para = new SQLParameter();
			String sqlActivityQuery = "select a.activitydefid,a.actstatus,a.createtime,a.modifytime,b.processdefid from pub_wf_actinstance a left join pub_wf_instance b on a.pk_wf_instance=b.pk_wf_instance"
					+ " where a.pk_wf_instance=?";
			para.addParam(prceIns);
			if (!StringUtil.isEmptyWithTrim(stateIn)) {
				sqlActivityQuery += " and a.actstatus in (" + stateIn + ")";
			}
			sqlActivityQuery += " order by a.ts desc";

			List<ActivityInstance> alActInstance = (List<ActivityInstance>) jdbc
					.executeQuery(sqlActivityQuery, para, new BaseProcessor() {
						ArrayList<ActivityInstance> al = new ArrayList<ActivityInstance>();

						@Override
						public Object processResultSet(ResultSet rs)
								throws SQLException {
							while (rs.next()) {
								ActivityInstance ai = new ActivityInstance();
								int index = 1;
								// activitydefid;
								String act_defid = rs.getString(index);
								ai.setActivityID(act_defid == null ? null
										: act_defid.trim());
								// actstatus;
								index++;
								int status = rs.getInt(index);
								ai.setStatus(status);

								// createtime liningc+;
								index++;
								String ct = rs.getString(index);
								UFDateTime createTime = new UFDateTime(ct);
								ai.setCreateTime(createTime);
								// modifytime liningc+;
								index++;
								String mt = rs.getString(index);
								UFDateTime modifyTime = new UFDateTime(mt);
								ai.setModifyTime(modifyTime);

								// processdefid
								index++;
								String wf_defid = rs.getString(index);
								ai.setWfProcessDefPK(wf_defid == null ? null
										: wf_defid.trim());
								al.add(ai);
							}

							return al;
						}
					});

			for (int i = 0; i < alActInstance.size(); i++) {
				ActivityInstance ai = alActInstance.get(i);
				WorkflowProcess wp = PfDataCache.getWorkflowProcess(
						ai.getWfProcessDefPK(), ai.getWfProcessInstancePK());
				Activity act = wp.findActivityByID(ai.getActivityID());
				ai.setActivity(act);
			}

			return alActInstance;
		} finally {
			if (persist != null)
				persist.release();
		}
	}

	public List<WFTask> getWFTasks(List<String> wfTaskpks)
			throws BusinessException {

		List<WFTask> tasks = null;
		try {
			tasks = new TaskManagerDMO().getTaskByPKs(wfTaskpks);
		} catch (DbException e) {
			throw new BusinessException(e.getMessage(), e);
		}

		return tasks;
	}
}