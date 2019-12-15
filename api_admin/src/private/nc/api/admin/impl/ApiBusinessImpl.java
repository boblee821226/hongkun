package nc.api.admin.impl;

import nc.api.admin.impl.service.ApprovalFlowQueryCountService;
import nc.api.admin.impl.service.ApprovalFlowQueryService;
import nc.api.admin.impl.service.ApprovalFlowWorkService;
import nc.api.admin.impl.service.DocService;
import nc.api.admin.impl.service.HttpClient;
import nc.api.admin.itf.ApiBusinessItf;
import nc.api.admin.itf.ApiPubInfo;
import nc.api.admin.vo.ActionVO;
import nc.api.admin.vo.ApprovalFlowQueryVO;
import nc.api.admin.vo.ApprovalFlowWorkVO;
import nc.api.admin.vo.LoginVO;
import nc.vo.pub.BusinessException;

public class ApiBusinessImpl implements ApiBusinessItf {

	@Override
	public Object doBusiness(
			String account,		// 账套
			String userId,		// 用户
			String billType,	// 类型
			String action,		// 动作
			Object paramObj,	// 数据(param)
			String token,		// 令牌
			LoginVO loginVO,	// 登录用户VO
			Object other		// 其它（备用）
			)
	throws BusinessException 
	{
		ActionVO actionVO = ApiPubInfo.ACTION.get(action);
		Class paramClass = actionVO.getParamClass();
		
		/**
		 * 用于测试
		 */
		if (ApiPubInfo.BILLTYPE_TEST.equals(billType)) {
			return HttpClient.doHttp();
		}
		
		/**
		 * 更新档案
		 * key：数据源
		 *    key：档案名，取表名
		 *       key：档案id
		 *          key：档案属性code、name 是必要属性。
		 *             value：档案值 （字符串）
		 */
		if (ApiPubInfo.BILLTYPE_DOC.equals(billType)) {
			return DocService.doAction(account);
		}
		/**
		 * 判断，档案如果为空，就先查询档案
		 */
		if ( ApiPubInfo.DOC_CACHE == null 
		|| ApiPubInfo.DOC_CACHE.get(account) == null 
		) {
			DocService.doAction(account);
		}
		/**
		 * 查询审批流数量（待我审批、我已审批、待我提交、我已提交）
		 */
		if (action.equals(ApiPubInfo.ACTION_QUY_COUNT)) {
			return ApprovalFlowQueryCountService.doAction(account, billType, paramObj, action, userId);
		}
		/**
		 * 提交、审核（驳回、不通过、批准）、弃审、收回、删除
		 */
		if (paramClass == ApprovalFlowWorkVO.class) {
			return ApprovalFlowWorkService.doAction(account, billType, paramObj, action, userId);
		}
		/**
		 * 查询审批流信息
		 */
		else if (paramClass == ApprovalFlowQueryVO.class) {
			return ApprovalFlowQueryService.doAction(account, billType, paramObj, action, userId);
		}
		return "类型不存在";
	}
}
