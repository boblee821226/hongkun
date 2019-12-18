package nc.api.admin.impl;

import nc.api.admin.impl.service.ApprovalFlowQueryCountService;
import nc.api.admin.impl.service.ApprovalFlowQueryService;
import nc.api.admin.impl.service.ApprovalFlowWorkService;
import nc.api.admin.impl.service.DocService;
import nc.api.admin.impl.service.PublicService;
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
		 * 初始化
		 */
		if (ApiPubInfo.BILLTYPE_INIT.equals(billType)) {
			PublicService.getGroup(account);
			PublicService.getUser(account);
			DocService.doAction(account);
			return "初始化完成";
		}
		/**
		 * 用于测试
		 */
		if (ApiPubInfo.BILLTYPE_TEST.equals(billType)) {
			return "test";
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
		 * 更新用户
		 *  key：数据源
		 * 		key：email
		 * 			value：userId
		 */
		if (ApiPubInfo.BILLTYPE_USER.equals(billType)) {
			return PublicService.getUser(account);
		}
		/**
		 * 更新集团
		 *  key：数据源
		 * 		key：集团name
		 * 			value：GroupId
		 */
		if (ApiPubInfo.BILLTYPE_USER.equals(billType)) {
			return PublicService.getGroup(account);
		}
		/**
		 * 判断，档案如果为空，就先查询档案
		 */
		if ( ApiPubInfo.CACHE_DOC == null 
		|| ApiPubInfo.CACHE_DOC.get(account) == null 
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
