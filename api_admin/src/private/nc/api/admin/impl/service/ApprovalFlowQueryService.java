package nc.api.admin.impl.service;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api.admin.itf.ApiPubInfo;
import nc.api.admin.tool.PuPubVO;
import nc.api.admin.vo.ApprovalFlowQueryResVO;
import nc.api.admin.vo.ApprovalFlowQueryVO;
import nc.api.admin.vo.BillTypeVO;
import nc.bs.dao.BaseDAO;
import nc.impl.pub.util.db.BillQueryByCond;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

public class ApprovalFlowQueryService {
	public static Object doAction(String account, String billType, Object paramObj, String action, String userId) throws BusinessException  {
		BaseDAO dao = new BaseDAO(account);
		
		ApprovalFlowQueryVO param = (ApprovalFlowQueryVO)paramObj;
		if (param==null) param = new ApprovalFlowQueryVO();
		String[] option = param.getOption();
		
		// 将 xx,yy 改成 'xx','yy' 的模式
		String billTypeWhere = "('" + billType.replaceAll(",", "','") + "')";
		
		// 判断，是否只是查数量
//		if (option != null && option.length > 0 && "count".equals(option[0])) {
//			StringBuffer querySQL = 
//				new StringBuffer("select ")
//						.append(" count(0) ")	//单据数量
//						.append(" from pub_wf_taskreceiver wt ")	// 工作栈
//						.append(" inner join pub_workflownote wf on wt.pk_wf_task = wf.pk_wf_task ")	// 审批消息
//						.append(" where wt.dr = 0 and nvl(wf.dr, 0) = 0 ")
//						.append(approvestatusWhere)	// 处理状态
//						.append(" and wf.pk_billtype in ").append(billTypeWhere)	// 要查询的单据类型，逗号分隔多个
//						.append(" and wt.cuserid = '").append(userId).append("' ")	// 只查本用户的数据
//			;
//			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
//			Integer billCount = 0;
//			if (list != null && list.size() > 0) {
//				billCount = PuPubVO.getInteger_NullAs(((Object[])list.get(0))[0], 0);
//			}
//			HashMap<String,Object> result = new HashMap<String,Object>();
//			result.put("count", billCount);
//			return result;
//		}
		
		// 传递过来 单据类型
		// __billTypeName 单据类型name
		// __from 来源用户 （作废）
		// __billId 单据id
		// __billType 单据类型
		String templateList = 
				"<p><span style='background-color: rgb(194, 79, 74); color: rgb(238, 236, 224);'>" +
			"{{__billTypeName}}" +
				"</span>&nbsp;&nbsp;<span style='color: rgb(77, 128, 191);'>" +
			"{{h_vbillcode}}" +
				"</span>&nbsp;&nbsp;<span style='background-color: rgb(194, 79, 74); color: rgb(238, 236, 224);'>" +
			"{{h_yearmonth}}" +
				"</span>" +
				"</p>";
		
		// 返回的 ArrayList
		ArrayList<ApprovalFlowQueryResVO> result = new ArrayList<ApprovalFlowQueryResVO>();
		
		HashMap<String,String> fieldMap = new HashMap<String,String>();
		fieldMap.put("\\{\\{__billTypeName\\}\\}", "__billTypeName");
		fieldMap.put("\\{\\{h_vbillcode\\}\\}", "h_vbillcode");
		fieldMap.put("\\{\\{h_yearmonth\\}\\}", "h_yearmonth");
//		fieldMap.put("\\{\\{__from\\}\\}", "__from");
		
		StringBuffer querySQL = new StringBuffer();
		
		if (action.equals(ApiPubInfo.ACTION_QUY_DO)
		|| action.equals(ApiPubInfo.ACTION_QUY_DONE)
		) {
			// 根据单据类型，去判断 where条件
			String approvestatusWhere = "";
			if (action.equals(ApiPubInfo.ACTION_QUY_DO)) { // 待审批
				approvestatusWhere = " and wf.approvestatus = 0 ";
			}
			else if (action.equals(ApiPubInfo.ACTION_QUY_DONE)) { // 已审批
				approvestatusWhere = " and wf.approvestatus = 1 ";
			}
			// 根据用户，查询当前需要处理的单据 (待审批、已审批)
			querySQL.append("select ")
				.append(" wf.billid ")		//0-单据id
				.append(",wf.billno ")		//1-单据号
				.append(",wf.pk_billtype ")	//2-单据类型
				.append(",wf.senderman ")	//3-发送人
				.append(",wf.senddate ")	//4-发送时间
				.append(" from pub_wf_taskreceiver wt ")	// 工作栈
				.append(" inner join pub_workflownote wf on wt.pk_wf_task = wf.pk_wf_task ")	// 审批消息
				.append(" where wt.dr = 0 and nvl(wf.dr, 0) = 0 ")
				.append(approvestatusWhere)	// 处理状态
				.append(" and wf.pk_billtype in ").append(billTypeWhere)	// 要查询的单据类型，逗号分隔多个
				.append(" and wt.cuserid = '").append(userId).append("' ")	// 只查本用户的数据
				.append(" order by wf.senddate desc")	// 按时间倒序，最大时间 在前，然后 分页。
			;
		} else if (action.equals(ApiPubInfo.ACTION_QUY_SEND)) {
			// 待我提交
			querySQL.append("select ")
				.append(" pk_hk_zulin_yuebao ")	//0-单据id
				.append(",vbillcode ")			//1-单据号
				.append(",vbilltypecode ")		//2-单据类型
				.append(",creator ")			//3-发送人
				.append(",creationtime ")		//4-发送时间
				.append(" from hk_zulin_yuebao ")// 业务单据
				.append(" where dr = 0 ")
				.append(" and ibillstatus = -1 ")// 未提交的
				.append(" and vbilltypecode in ").append(billTypeWhere)	// 要查询的单据类型，逗号分隔多个
				.append(" and creator = '").append(userId).append("' ")	// 只查本用户的数据
				.append(" order by creationtime desc")	// 按时间倒序，最大时间 在前，然后 分页。
			;
		} else if (action.equals(ApiPubInfo.ACTION_QUY_SENT)) {
			// 我已提交
			querySQL.append("select ")
				.append(" wi.billid ")		//0-单据id
				.append(",wi.billno ")		//1-单据号
				.append(",wi.billtype ")	//2-单据类型
				.append(",wi.billmaker ")	//3-发送人
				.append(",wi.startts ")		//4-发送时间
				.append(" from pub_wf_instance wi ")// 流程实例
				.append(" where wi.dr = 0 ")
				.append(" and wi.billtype in ").append(billTypeWhere)			// 要查询的单据类型，逗号分隔多个
				.append(" and wi.billmaker = '").append(userId).append("' ")	// 只查本用户的数据
				.append(" order by wi.startts desc")	// 按时间倒序，最大时间 在前，然后 分页。
			;
		}
		
		ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
		// 按单据类型 分组 存放 billid
		HashMap<String,ArrayList<String>> billid_MAP = new HashMap<String,ArrayList<String>>();
		// 单据类型+单据id，组合为key，放到map中，备用。
		HashMap<String,String[]> bill_MAP = new HashMap<String,String[]>();
		for (Object obj : list) {
			Object[] row = (Object[])obj;
			String billid = PuPubVO.getString_TrimZeroLenAsNull(row[0]);
			String billno = PuPubVO.getString_TrimZeroLenAsNull(row[1]);
			String pk_billtype = PuPubVO.getString_TrimZeroLenAsNull(row[2]);
			String senderman = PuPubVO.getString_TrimZeroLenAsNull(row[3]);
			String senddate = PuPubVO.getString_TrimZeroLenAsNull(row[4]);
			
			String key = pk_billtype + "@@@@" + billid;
			bill_MAP.put(key, new String[]{
					billid,
					billno,
					pk_billtype,
					senderman,
					senddate
			});
			
			if (!billid_MAP.containsKey(pk_billtype)) {
				billid_MAP.put(pk_billtype, new ArrayList<String>());
			}
			billid_MAP.get(pk_billtype).add(billid);
		}
		
		// 循环单据类型，进行处理
		for (String pk_billtype : billid_MAP.keySet()) {
			ArrayList<String> billids = billid_MAP.get(pk_billtype);
			BillTypeVO billTypeVO = ApiPubInfo.BILLTYPE.get(pk_billtype);
			Class billVoClass = billTypeVO.getBillVoClass();
			String billTypeName = billTypeVO.getBillTypeName();
			// 需要缓存，太慢了
			BillQueryByCond billQuery = new BillQueryByCond(billVoClass);
			IBill[] billVOs = billQuery.query(
				billids.toArray(new String[0]),
				true);
			
			// 循环 单据，封装出填充了数据的 模板字符串
			for (IBill bill : billVOs) {
				HashMap<String,Object> dataMap = new HashMap<String,Object>();
				dataMap.put("__billTypeName", "");
				dataMap.put("h_vbillcode", "");
				dataMap.put("h_yearmonth", "");
//				dataMap.put("__from", "");
				ISuperVO hVO = bill.getParent();
				String billid = hVO.getPrimaryKey();
				String ts = PuPubVO.getString_TrimZeroLenAsNull(hVO.getAttributeValue("ts"));
				// 取值
				for (String field : dataMap.keySet()) {
					if ("__billTypeName".equals(field)) {
						dataMap.put(field, billTypeName);
					} else if (field.startsWith("h_")) {
						String trueField = field.substring(2);
						Object value = hVO.getAttributeValue(trueField);
						dataMap.put(field, value);
					}
				}
				// 替换
				String templateListData = templateList;
				for (String tmpField : fieldMap.keySet()) {
					Object value = dataMap.get(fieldMap.get(tmpField));
					templateListData = templateListData.replaceAll(tmpField, value == null ? "" : value.toString());
				}
				// 完成后 添加到返回值
				String key = pk_billtype + "@@@@" + billid;
				String[] billInfo = bill_MAP.get(key);
				ApprovalFlowQueryResVO resVO = new ApprovalFlowQueryResVO();
				resVO.setBillId(billid);
				resVO.setTs(ts);
				resVO.setBillType(pk_billtype);
				resVO.setSendMan(ApiPubInfo.CACHE_DOC.get(account).get("sm_user").get(billInfo[3]).get("name"));
				resVO.setSendDate(billInfo[4]);
				resVO.setTemplateListData(templateListData);
				result.add(resVO);
			}
		}
		return result;
	}
}
