package nc.api_oa.hkjt.impl;

import java.util.HashMap;

import nc.api.zhiyun.tool.MyHttpUtil;
import nc.api.zhiyun.vo.RequestParamVO;
import nc.api_oa.hkjt.impl.service.DocService;
import nc.api_oa.hkjt.impl.service.PublicService;
import nc.api_oa.hkjt.impl.service._263X.N263XService;
import nc.api_oa.hkjt.impl.service._263X.N263XServiceDELETE;
import nc.api_oa.hkjt.impl.service._263X.N263XServiceQUERY;
import nc.api_oa.hkjt.impl.service._264X.N264XService;
import nc.api_oa.hkjt.impl.service._264X.N264XServiceDELETE;
import nc.api_oa.hkjt.impl.service._264X.N264XServiceQUERY;
import nc.api_oa.hkjt.impl.service.other.OtherService;
import nc.api_oa.hkjt.impl.service.other.OtherServiceDELETE;
import nc.api_oa.hkjt.impl.service.other.OtherServiceQUERY;
import nc.api_oa.hkjt.itf.ApiBusinessItf;
import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.vo.LoginVO;
import nc.bs.hkjt.srgk.lvyun.workplugin.ImpLvyunData;
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
			Object res3 = DocService.doAction(account, null);
			return "初始化完成";
		}
		
		/**
		 * 用于测试
		 */
		if (ApiPubInfo.BILLTYPE_TEST.equals(billType)) {
			// TODO 测试代码
//			CurrEnvVO context = new CurrEnvVO();
//			context.setPk_orgs(new String[]{
////					"0001N510000000001SY3", // 朗丽兹 9
////					"0001N510000000001SY5", // 康西 11
////					"0001N510000000001SY7", // 西山温泉 10
////					"0001N510000000001SY1", // 学院路16
//					"0001N5100000000UVI5I"	// 太申18
//			});
//			context.getKeyMap().put("bdate", "2020-08-26");
//			context.getKeyMap().put("edate", "2020-08-26");
//			Object res = new ImpLvyunOutData().executeTask(context);
			
			/** 绿云-入账明细 **/
			Object res = new ImpLvyunData().executeTest(null);
			
			/** 更新缓存 **/
			
			
			return res;
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
			return DocService.doAction(account, null);
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
			return DocService.doAction(account, null);
		}
		/**
		 * 新增（提交）
		 */
		if (action.equals(ApiPubInfo.ACTION_WRITE)
//		 || action.equals(ApiPubInfo.ACTION_SAVEBASE)
		) {
			// 根据单据类型 去 判断，调用哪个处理类。
			if (billType.startsWith("263X")) {
				return new N263XService().doAction(account, billType, paramObj, action, userId);
			} else if (billType.startsWith("264X")) {
				return new N264XService().doAction(account, billType, paramObj, action, userId);
			} else if (billType.startsWith("OA")) {
				return new OtherService().doAction(account, billType, paramObj, action, userId);
			} else {
				throw new BusinessException("不支持的类型");
			}
		}
		/**
		 * 删除（收回）
		 */
		if (action.equals(ApiPubInfo.ACTION_DELETE)) {
			// 根据单据类型 去 判断，调用哪个处理类。
			if (billType.startsWith("263X")) {
				return new N263XServiceDELETE().doAction(account, billType, paramObj, action, userId);
			} else if (billType.startsWith("264X")) {
				return new N264XServiceDELETE().doAction(account, billType, paramObj, action, userId);
			} else if (paramObj instanceof HashMap[]) {
				return new OtherServiceDELETE().doAction(account, billType, paramObj, action, userId);
			} else {
				throw new BusinessException("不支持的类型");
			}
		}
		/**
		 * 查询
		 */
		if (action.equals(ApiPubInfo.ACTION_QUERY)) {
			// 根据单据类型 去 判断，调用哪个处理类。
			if (billType.startsWith("263X")) {
				return new N263XServiceQUERY().doAction(account, billType, paramObj, action, userId);
			} else if (billType.startsWith("264X")) {
				return new N264XServiceQUERY().doAction(account, billType, paramObj, action, userId);
			} else if (paramObj instanceof HashMap[]) {
				return new OtherServiceQUERY().doAction(account, billType, paramObj, action, userId);
			} else {
				throw new BusinessException("不支持的类型");
			}
		}
		/**
		 * 查询审批流数量（待我审批、我已审批、待我提交、我已提交）
		 */
//		if (action.equals(ApiPubInfo.ACTION_QUY_COUNT)) {
//			return ApprovalFlowQueryCountService.doAction(account, billType, paramObj, action, userId);
//		}
		
//		ActionVO actionVO = ApiPubInfo.ACTION.get(action);
//		Class paramClass = actionVO.getParamClass();
		
		/**
		 * 提交、审核（驳回、不通过、批准）、弃审、收回、删除
		 */
//		if (paramClass == ApprovalFlowWorkVO.class) {
//			return ApprovalFlowWorkService.doAction(account, billType, paramObj, action, userId);
//		}
		/**
		 * 查询审批流信息
		 */
//		else if (paramClass == ApprovalFlowQueryVO.class) {
//			return ApprovalFlowQueryService.doAction(account, billType, paramObj, action, userId);
//		}
		
		return "类型不存在";
	}
	
	/**
	 * 更新缓存
	 */
	private Object updateCache(HashMap params) {
		
		String cache_url = "http://10.0.0.50:9081,http://10.0.0.50:9082,http://10.0.0.50:9083,http://10.0.0.50:9084,http://10.0.0.50:9085,http://10.0.0.50:9086";
		
		String baseUrl = "/service/~hkjt/nc.api_oa.hkjt.BillServlet";
		cache_url = cache_url.replaceAll("、", ",").replaceAll("，", ",");
		String[] cache_urls = cache_url.split(",");
		for (String apiUrl : cache_urls) {
			String url = apiUrl + baseUrl;
			/**
			 * {
			    "account": "NC65",
			    "userCode": "543308",
			    "billType": "DOC",
			    "action": "WRITE",
			    "data": null
				}
			 */
			RequestParamVO paramObj = new RequestParamVO();
//			paramObj.setAccount("NC65");
			paramObj.setUserCode("543308");
			paramObj.setBillType("DOC");
			paramObj.setAction("WRITE");
			try {
				String res = MyHttpUtil.doPost(url, paramObj);
				System.out.println("==res:" + res + "==");
			} catch (Exception ex) {
				
			}
		}
		
		return "更新缓存成功";
	}
}
