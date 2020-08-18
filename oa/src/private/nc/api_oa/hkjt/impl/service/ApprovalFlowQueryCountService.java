package nc.api_oa.hkjt.impl.service;

import hd.vo.pub.tools.PuPubVO;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api_oa.hkjt.vo.ApprovalFlowQueryVO;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;

public class ApprovalFlowQueryCountService {
	public static Object doAction(String account, String billType, Object paramObj, String action, String userId) throws BusinessException  {
		BaseDAO dao = new BaseDAO(account);
		ApprovalFlowQueryVO param = (ApprovalFlowQueryVO)paramObj;
		if (param==null) param = new ApprovalFlowQueryVO();
		
		// 将 xx,yy 改成 'xx','yy' 的模式
		String billTypeWhere = "('" + billType.replaceAll(",", "','") + "')";
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		// 待我审批
		{
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" count(0) ")	//单据数量
						.append(" from pub_wf_taskreceiver wt ")	// 工作栈
						.append(" inner join pub_workflownote wf on wt.pk_wf_task = wf.pk_wf_task ")	// 审批消息
						.append(" where wt.dr = 0 and nvl(wf.dr, 0) = 0 ")
						.append(" and wf.approvestatus = 0 ")	// 待处理
						.append(" and wf.pk_billtype in ").append(billTypeWhere)	// 要查询的单据类型，逗号分隔多个
						.append(" and wt.cuserid = '").append(userId).append("' ")	// 只查本用户的数据
			;
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			Integer billCount = 0;
			if (list != null && list.size() > 0) {
				billCount = PuPubVO.getInteger_NullAs(((Object[])list.get(0))[0], 0);
			}
			result.put("doCount", billCount);
		}
		// 我已审批
		{
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" count(0) ")	//单据数量
						.append(" from pub_wf_taskreceiver wt ")	// 工作栈
						.append(" inner join pub_workflownote wf on wt.pk_wf_task = wf.pk_wf_task ")	// 审批消息
						.append(" where wt.dr = 0 and nvl(wf.dr, 0) = 0 ")
						.append(" and wf.approvestatus = 1 ")	// 已处理
						.append(" and wf.pk_billtype in ").append(billTypeWhere)	// 要查询的单据类型，逗号分隔多个
						.append(" and wt.cuserid = '").append(userId).append("' ")	// 只查本用户的数据
			;
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			Integer billCount = 0;
			if (list != null && list.size() > 0) {
				billCount = PuPubVO.getInteger_NullAs(((Object[])list.get(0))[0], 0);
			}
			result.put("doneCount", billCount);
		}
		// 我已提交
		{
			StringBuffer querySQL = 
			new StringBuffer("select ")
				.append(" count(0) ")	//单据数量
				.append(" from pub_wf_instance wi ")	// 流程实例
				.append(" where wi.dr = 0 ")
				.append(" and wi.billtype in ").append(billTypeWhere)			// 要查询的单据类型，逗号分隔多个
				.append(" and wi.billmaker = '").append(userId).append("' ")	// 只查本用户的数据
			;
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			Integer billCount = 0;
			if (list != null && list.size() > 0) {
				billCount = PuPubVO.getInteger_NullAs(((Object[])list.get(0))[0], 0);
			}
			result.put("sentCount", billCount);
		}
		// 待我提交
		{
			StringBuffer querySQL = new StringBuffer
			("select count(0) from hk_zulin_yuebao where dr = 0 and ibillstatus = -1 and creator = '")
			.append(userId).append("' ")
			.append(" and vbilltypecode in ").append(billTypeWhere)
			;
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			Integer billCount = 0;
			if (list != null && list.size() > 0) {
				billCount = PuPubVO.getInteger_NullAs(((Object[])list.get(0))[0], 0);
			}
			result.put("sendCount", billCount);
		}
		return result;
	}
}
