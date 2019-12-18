package nc.api.admin.impl.service;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api.admin.itf.ApiPubInfo;
import nc.api.admin.tool.PuPubVO;
import nc.api.admin.vo.ApprovalFlowWorkVO;
import nc.api.admin.vo.BillInfoVO;
import nc.api.admin.vo.BillTypeVO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pub.util.db.BillQueryByCond;
import nc.itf.uap.pf.IWorkflowMachine;
import nc.itf.uap.pf.IplatFormEntry;
import nc.message.vo.AttachmentVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.workflownote.WorkflownoteVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

public class ApprovalFlowWorkService {
	/**
	 * @param account
	 * @param billType_xx （该参数 无用）
	 * @param paramObj
	 * @param action
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	public static Object doAction(String account, String billType_xx, Object paramObj, String action, String userId) throws BusinessException  {
		ApprovalFlowWorkVO param = (ApprovalFlowWorkVO)paramObj;
		BillInfoVO[] allBillInfoVOs = param.getBillInfo();
		HashMap<String, BillInfoVO> allBillMap = new HashMap<String, BillInfoVO>();
		// 传递过来的数据，是多个单据类型的结果集，所以 首先需要 按单据类型分组处理。
		HashMap<String, ArrayList<BillInfoVO>> typeBillMap = new HashMap<String, ArrayList<BillInfoVO>>();
		for (BillInfoVO vo : allBillInfoVOs) {
			String billType = vo.getBillType();
			String billId = vo.getBillId();
			String mapKey = billType + "####" + billId;
			allBillMap.put(mapKey, vo);
			
			if (!typeBillMap.containsKey(billType)) {
				typeBillMap.put(billType, new ArrayList<BillInfoVO>());
			}
			typeBillMap.get(billType).add(vo);
		}
		
		for (String billType : typeBillMap.keySet()) {
		
			ArrayList<BillInfoVO> billInfoList = typeBillMap.get(billType);
			
			BillTypeVO billTypeVO = ApiPubInfo.BILLTYPE.get(billType);
			Class billVoClass = billTypeVO.getBillVoClass();
			String[] billIds = new String[billInfoList.size()];
			
			for (int i = 0; i < billIds.length; i++) {
				String id = billInfoList.get(i).getBillId();
				billIds[i] = id;
			}
			// 需要缓存，太慢了
			BillQueryByCond billQuery = new BillQueryByCond(billVoClass);
			IBill[] billVOs = billQuery.query(billIds, true);
			
			IplatFormEntry iplatFormEntry = (IplatFormEntry) NCLocator.getInstance().lookup(IplatFormEntry.class.getName());
			IWorkflowMachine iWorkflowMachine = (IWorkflowMachine) NCLocator.getInstance().lookup(IWorkflowMachine.class.getName());
			
			for (int i = 0; i < billVOs.length; i++) {
				AggregatedValueObject billVO = (AggregatedValueObject) billVOs[i];
				String ts = PuPubVO.getString_TrimZeroLenAsNull(
						billVO.getParentVO().getAttributeValue("ts") );
				String billId = billVO.getParentVO().getPrimaryKey();
				String mapKey = billType + "####" + billId;
				BillInfoVO billInfoVO = allBillMap.get(mapKey);
				if (ts.equals(billInfoVO.getTs())) {
					try {
						// 1、先检查工作流，是否本人应该操作该单据
						boolean checkPass = false;
						WorkflownoteVO workFlowVO = null;
						try {
							HashMap<String,Object> hmPfExParams = new HashMap<String,Object>();
							hmPfExParams.put("reload_vo", billVO);
							if (action.equals(ApiPubInfo.ACTION_SAVE)) {//提交
								workFlowVO = iWorkflowMachine.checkWorkFlow(action, billType, billVO, hmPfExParams);
							} else if(action.equals(ApiPubInfo.ACTION_APPROVE)) {//审核
								// TODO 驳回不好用
								workFlowVO = iWorkflowMachine.checkWorkFlow(action + userId, billType, billVO, hmPfExParams);
							} else if(action.equals(ApiPubInfo.ACTION_UNAPPROVE)) {//弃审
								
							}
	//						HashMap<String,Object> hmPfExParams = new HashMap<String,Object>();
	//						hmPfExParams.put("reload_vo", billVO);
	//						workFlowVO = iWorkflowMachine.checkWorkFlow(action + userId, billType, billVO, hmPfExParams);
							/**
							 * 	checknote={{需要填写：批语}}
								workflow_type={{需要填写： 2}}
								messagenote=高双林 弃审单据, {billno}: HK372019120700001329, {please}审批单据   {{需要填写：消息}}
								attachmentsetting={{需要填写：默认为空数组[] }}
								approveresult={{需要填写：审批结果 N-不批准  Y-批准  R-驳回}}
							 */
							if (workFlowVO != null) {
								workFlowVO.setWorkflow_type(2);
								workFlowVO.setChecknote(billInfoVO.getCheckNote());
								workFlowVO.setApproveresult(billInfoVO.getApproveResult());
								workFlowVO.setAttachmentSetting(new ArrayList<AttachmentVO>());
							}
							checkPass = true;
						} catch (Exception ex) {
							billInfoVO.setResInfo(1004, ex.getMessage());
						}
						// 2、进行操作
						if (checkPass) {
							Object res = iplatFormEntry.processAction(action ,billType ,workFlowVO ,billVO ,null ,null);
							if (res != null) {
								billInfoVO.setResInfo(0, "ok");
							} else {
								billInfoVO.setResInfo(1003, "fail");
							}
						}
					} catch (Exception ex) {
						billInfoVO.setResInfo(1002, ex.getMessage());
					}
				} else {
					billInfoVO.setResInfo(1001, "ts不一致");
				}
			}
		}
		return allBillMap.values();
	}
}
