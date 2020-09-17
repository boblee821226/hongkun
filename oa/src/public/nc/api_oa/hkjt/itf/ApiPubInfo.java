package nc.api_oa.hkjt.itf;

import java.util.HashMap;

import nc.api_oa.hkjt.vo.ActionVO;
import nc.api_oa.hkjt.vo.ApprovalFlowQueryVO;
import nc.api_oa.hkjt.vo.ApprovalFlowWorkVO;
import nc.api_oa.hkjt.vo.BillTypeVO;
import nc.api_oa.hkjt.vo._263X.JkVO;
import nc.api_oa.hkjt.vo._264X.BxVO;

public class ApiPubInfo {
	
	/**
	 * 单据类型
	 * （经过测试后，所支持的单据类型）
	 */
	public static HashMap<String,BillTypeVO> BILLTYPE = new HashMap<String,BillTypeVO>();
	public static String BILLTYPE_INIT = "INIT";
	public static String BILLTYPE_DOC = "DOC";
	public static String BILLTYPE_USER = "USER";
	public static String BILLTYPE_TEST = "TEST";
	/**
	 * 动作
	 */
	public static HashMap<String,ActionVO> ACTION = new HashMap<String,ActionVO>();
//	public static String ACTION_ADD = "ADD";	// 增加
//	public static String ACTION_DEL = "DEL";	// 删除
//	public static String ACTION_MOD = "MOD";	// 修改
//	public static String ACTION_QUY = "QUY";	// 查询（检查）
	public static String ACTION_QUY_COUNT = "QUY_COUNT";	// 查询审批流相关的数量
	public static String ACTION_QUY_DO = "QUY_DO";			// 查询审批流-待我审批
	public static String ACTION_QUY_DONE = "QUY_DONE";		// 查询审批流-我已审批
	public static String ACTION_QUY_SEND = "QUY_SEND";// 查询单据-待我提交
	public static String ACTION_QUY_SENT = "QUY_SENT";		// 查询单据-我已提交
	public static String ACTION_SAVE = "SAVE";				// 提交
	public static String ACTION_APPROVE = "APPROVE";		// 审核
	public static String ACTION_UNAPPROVE = "UNAPPROVE";	// 弃审
	public static String ACTION_UNSAVEBILL = "UNSAVEBILL";	// 收回
//	public static String ACTION_SAVEBASE = "SAVEBASE";		// 保存 SAVEBASE
	public static String ACTION_WRITE = "WRITE";			// 保存 WRITE
	public static String ACTION_DELETE = "DELETE";			// 删除 DELETE
	public static String ACTION_QUERY = "QUERY";			// 查询
	/**
	 * 静态构造函数
	 */
	static
	{
		BILLTYPE.put(BILLTYPE_INIT, null);	// 用于所有缓存
		BILLTYPE.put(BILLTYPE_DOC, null);	// 用于更新档案数据
		BILLTYPE.put(BILLTYPE_TEST, null);	// 用于测试
		BILLTYPE.put("263X", new BillTypeVO("借款单", JkVO.class));
		BILLTYPE.put("264X", new BillTypeVO("报销单", BxVO.class));
		
//		ACTION.put(ACTION_QUY_COUNT, new ActionVO("工作流数量", ApprovalFlowQueryVO.class));
//		ACTION.put(ACTION_QUY_DO, new ActionVO("待我审批", ApprovalFlowQueryVO.class));
//		ACTION.put(ACTION_QUY_DONE, new ActionVO("我已审批", ApprovalFlowQueryVO.class));
//		ACTION.put(ACTION_QUY_SENT, new ActionVO("待我提交", ApprovalFlowQueryVO.class));
//		ACTION.put(ACTION_QUY_SEND, new ActionVO("我已提交", ApprovalFlowQueryVO.class));
		
//		ACTION.put(ACTION_SAVE, new ActionVO("提交", ApprovalFlowWorkVO.class));
//		ACTION.put(ACTION_APPROVE, new ActionVO("审核", ApprovalFlowWorkVO.class));
//		ACTION.put(ACTION_UNAPPROVE, new ActionVO("弃审", ApprovalFlowWorkVO.class));
//		ACTION.put(ACTION_UNSAVEBILL, new ActionVO("收回", ApprovalFlowWorkVO.class));
		
		ACTION.put(ACTION_DELETE, null);
//		ACTION.put(ACTION_SAVEBASE, null);
		ACTION.put(ACTION_WRITE, null);
		ACTION.put(ACTION_QUERY, null);
		
		ACTION.put(ACTION_WRITE + "#263X", new ActionVO("新增", JkVO[].class));	// 借款单VO
		ACTION.put(ACTION_WRITE + "#264X", new ActionVO("新增", BxVO[].class));	// 报销单VO
		
		ACTION.put(ACTION_DELETE + "#263X", new ActionVO("删除", JkVO[].class));	// 借款单VO
		ACTION.put(ACTION_DELETE + "#264X", new ActionVO("删除", BxVO[].class));	// 报销单VO
		
		ACTION.put(ACTION_QUERY + "#263X", new ActionVO("查询", JkVO[].class));	// 借款单VO
		ACTION.put(ACTION_QUERY + "#264X", new ActionVO("查询", BxVO[].class));	// 报销单VO
	}
	
	/**
	 * DOC_MAP的结构
	 * key：数据源
	 *    key：档案名，取表名
	 *       key：档案id
	 *          key：档案属性code、name 是必要属性。
	 *             value：档案值 （字符串）
	 * 加载通用的档案
	 * 1、用户
	 * 2、人员
	 * 3、组织（公司、部门...）
	 * 4、仓库
	 */
	public static HashMap<String,HashMap<String,HashMap<String,HashMap<String,String>>>> 
	CACHE_DOC = new HashMap<String,HashMap<String,HashMap<String,HashMap<String,String>>>>();
	
	/**
	 * 加载用户信息,用于 掉接口时，翻译成 userId
	 * key：数据源
	 * 		key：email
	 * 			value：userId
	 */
	public static HashMap<String, HashMap<String, String>>
	CACHE_USER = new HashMap<String, HashMap<String, String>>();
	
	/**
	 * 加载集团信息
	 * key：数据源
	 * 		key：集团name
	 * 			value：GroupId
	 */
	public static HashMap<String, HashMap<String, String>>
	CACHE_GROUP = new HashMap<String, HashMap<String, String>>();
}
