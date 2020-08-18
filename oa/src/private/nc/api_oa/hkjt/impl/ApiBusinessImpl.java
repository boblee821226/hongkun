package nc.api_oa.hkjt.impl;

import nc.api_oa.hkjt.impl.service.ApprovalFlowQueryCountService;
import nc.api_oa.hkjt.impl.service.ApprovalFlowQueryService;
import nc.api_oa.hkjt.impl.service.ApprovalFlowWorkService;
import nc.api_oa.hkjt.impl.service.DocService;
import nc.api_oa.hkjt.impl.service.PublicService;
import nc.api_oa.hkjt.impl.service._263X.N263XService;
import nc.api_oa.hkjt.impl.service._264X.N264XService;
import nc.api_oa.hkjt.itf.ApiBusinessItf;
import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.vo.ActionVO;
import nc.api_oa.hkjt.vo.ApprovalFlowQueryVO;
import nc.api_oa.hkjt.vo.ApprovalFlowWorkVO;
import nc.api_oa.hkjt.vo.LoginVO;
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
		
		/**
		 * 初始化
		 */
		if (ApiPubInfo.BILLTYPE_INIT.equals(billType)) {
			Object res1 = PublicService.getGroup(account);
			Object res2 = PublicService.getUser(account);
			Object res3 = DocService.doAction(account);
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
			return DocService.doAction(account);
		}
		/**
		 * 新增
		 */
		if (action.equals(ApiPubInfo.ACTION_WRITE)
		 || action.equals(ApiPubInfo.ACTION_SAVEBASE)
		) {
			// 根据单据类型 去 判断，调用哪个处理类。
//			return N263XService.doAction(account, billType, paramObj, action, userId);
			return new N264XService().doAction(account, billType, paramObj, action, userId);
		}
		/**
		 * 查询审批流数量（待我审批、我已审批、待我提交、我已提交）
		 */
		if (action.equals(ApiPubInfo.ACTION_QUY_COUNT)) {
			return ApprovalFlowQueryCountService.doAction(account, billType, paramObj, action, userId);
		}
		
		ActionVO actionVO = ApiPubInfo.ACTION.get(action);
		Class paramClass = actionVO.getParamClass();
		
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
