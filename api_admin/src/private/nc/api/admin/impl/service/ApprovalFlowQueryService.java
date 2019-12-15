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
		BaseDAO dao = new BaseDAO();
		
		ApprovalFlowQueryVO param = (ApprovalFlowQueryVO)paramObj;
		if (param==null) param = new ApprovalFlowQueryVO();
		String[] option = param.getOption();
		
		// �� xx,yy �ĳ� 'xx','yy' ��ģʽ
		String billTypeWhere = "('" + billType.replaceAll(",", "','") + "')";
		
		// �жϣ��Ƿ�ֻ�ǲ�����
//		if (option != null && option.length > 0 && "count".equals(option[0])) {
//			StringBuffer querySQL = 
//				new StringBuffer("select ")
//						.append(" count(0) ")	//��������
//						.append(" from pub_wf_taskreceiver wt ")	// ����ջ
//						.append(" inner join pub_workflownote wf on wt.pk_wf_task = wf.pk_wf_task ")	// ������Ϣ
//						.append(" where wt.dr = 0 and nvl(wf.dr, 0) = 0 ")
//						.append(approvestatusWhere)	// ����״̬
//						.append(" and wf.pk_billtype in ").append(billTypeWhere)	// Ҫ��ѯ�ĵ������ͣ����ŷָ����
//						.append(" and wt.cuserid = '").append(userId).append("' ")	// ֻ�鱾�û�������
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
		
		// ���ݹ��� ��������
		// __billTypeName ��������name
		// __from ��Դ�û� �����ϣ�
		// __billId ����id
		// __billType ��������
		String templateList = 
				"<p><span style='background-color: rgb(194, 79, 74); color: rgb(238, 236, 224);'>" +
			"{{__billTypeName}}" +
				"</span>&nbsp;&nbsp;<span style='color: rgb(77, 128, 191);'>" +
			"{{h_vbillcode}}" +
				"</span>&nbsp;&nbsp;<span style='background-color: rgb(194, 79, 74); color: rgb(238, 236, 224);'>" +
			"{{h_yearmonth}}" +
				"</span>" +
				"</p>";
		
		// ���ص� ArrayList
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
			// ���ݵ������ͣ�ȥ�ж� where����
			String approvestatusWhere = "";
			if (action.equals(ApiPubInfo.ACTION_QUY_DO)) { // ������
				approvestatusWhere = " and wf.approvestatus = 0 ";
			}
			else if (action.equals(ApiPubInfo.ACTION_QUY_DONE)) { // ������
				approvestatusWhere = " and wf.approvestatus = 1 ";
			}
			// �����û�����ѯ��ǰ��Ҫ����ĵ��� (��������������)
			querySQL.append("select ")
				.append(" wf.billid ")		//0-����id
				.append(",wf.billno ")		//1-���ݺ�
				.append(",wf.pk_billtype ")	//2-��������
				.append(",wf.senderman ")	//3-������
				.append(",wf.senddate ")	//4-����ʱ��
				.append(" from pub_wf_taskreceiver wt ")	// ����ջ
				.append(" inner join pub_workflownote wf on wt.pk_wf_task = wf.pk_wf_task ")	// ������Ϣ
				.append(" where wt.dr = 0 and nvl(wf.dr, 0) = 0 ")
				.append(approvestatusWhere)	// ����״̬
				.append(" and wf.pk_billtype in ").append(billTypeWhere)	// Ҫ��ѯ�ĵ������ͣ����ŷָ����
				.append(" and wt.cuserid = '").append(userId).append("' ")	// ֻ�鱾�û�������
				.append(" order by wf.senddate desc")	// ��ʱ�䵹�����ʱ�� ��ǰ��Ȼ�� ��ҳ��
			;
		} else if (action.equals(ApiPubInfo.ACTION_QUY_SEND)) {
			// �����ύ
			querySQL.append("select ")
				.append(" pk_hk_zulin_yuebao ")	//0-����id
				.append(",vbillcode ")			//1-���ݺ�
				.append(",vbilltypecode ")		//2-��������
				.append(",creator ")			//3-������
				.append(",creationtime ")		//4-����ʱ��
				.append(" from hk_zulin_yuebao ")// ҵ�񵥾�
				.append(" where dr = 0 ")
				.append(" and ibillstatus = -1 ")// δ�ύ��
				.append(" and vbilltypecode in ").append(billTypeWhere)	// Ҫ��ѯ�ĵ������ͣ����ŷָ����
				.append(" and creator = '").append(userId).append("' ")	// ֻ�鱾�û�������
				.append(" order by creationtime desc")	// ��ʱ�䵹�����ʱ�� ��ǰ��Ȼ�� ��ҳ��
			;
		} else if (action.equals(ApiPubInfo.ACTION_QUY_SENT)) {
			// �����ύ
			querySQL.append("select ")
				.append(" wi.billid ")		//0-����id
				.append(",wi.billno ")		//1-���ݺ�
				.append(",wi.billtype ")	//2-��������
				.append(",wi.billmaker ")	//3-������
				.append(",wi.startts ")		//4-����ʱ��
				.append(" from pub_wf_instance wi ")// ����ʵ��
				.append(" where wi.dr = 0 ")
				.append(" and wi.billtype in ").append(billTypeWhere)			// Ҫ��ѯ�ĵ������ͣ����ŷָ����
				.append(" and wi.billmaker = '").append(userId).append("' ")	// ֻ�鱾�û�������
				.append(" order by wi.startts desc")	// ��ʱ�䵹�����ʱ�� ��ǰ��Ȼ�� ��ҳ��
			;
		}
		
		ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
		// ���������� ���� ��� billid
		HashMap<String,ArrayList<String>> billid_MAP = new HashMap<String,ArrayList<String>>();
		// ��������+����id�����Ϊkey���ŵ�map�У����á�
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
		
		// ѭ���������ͣ����д���
		for (String pk_billtype : billid_MAP.keySet()) {
			ArrayList<String> billids = billid_MAP.get(pk_billtype);
			BillTypeVO billTypeVO = ApiPubInfo.BILLTYPE.get(pk_billtype);
			Class billVoClass = billTypeVO.getBillVoClass();
			String billTypeName = billTypeVO.getBillTypeName();
			// ��Ҫ���棬̫����
			BillQueryByCond billQuery = new BillQueryByCond(billVoClass);
			IBill[] billVOs = billQuery.query(
				billids.toArray(new String[0]),
				true);
			
			// ѭ�� ���ݣ���װ����������ݵ� ģ���ַ���
			for (IBill bill : billVOs) {
				HashMap<String,Object> dataMap = new HashMap<String,Object>();
				dataMap.put("__billTypeName", "");
				dataMap.put("h_vbillcode", "");
				dataMap.put("h_yearmonth", "");
//				dataMap.put("__from", "");
				ISuperVO hVO = bill.getParent();
				String billid = hVO.getPrimaryKey();
				String ts = PuPubVO.getString_TrimZeroLenAsNull(hVO.getAttributeValue("ts"));
				// ȡֵ
				for (String field : dataMap.keySet()) {
					if ("__billTypeName".equals(field)) {
						dataMap.put(field, billTypeName);
					} else if (field.startsWith("h_")) {
						String trueField = field.substring(2);
						Object value = hVO.getAttributeValue(trueField);
						dataMap.put(field, value);
					}
				}
				// �滻
				String templateListData = templateList;
				for (String tmpField : fieldMap.keySet()) {
					Object value = dataMap.get(fieldMap.get(tmpField));
					templateListData = templateListData.replaceAll(tmpField, value == null ? "" : value.toString());
				}
				// ��ɺ� ��ӵ�����ֵ
				String key = pk_billtype + "@@@@" + billid;
				String[] billInfo = bill_MAP.get(key);
				ApprovalFlowQueryResVO resVO = new ApprovalFlowQueryResVO();
				resVO.setBillId(billid);
				resVO.setTs(ts);
				resVO.setBillType(pk_billtype);
				resVO.setSendMan(ApiPubInfo.DOC_CACHE.get(account).get("sm_user").get(billInfo[3]).get("name"));
				resVO.setSendDate(billInfo[4]);
				resVO.setTemplateListData(templateListData);
				result.add(resVO);
			}
		}
		return result;
	}
}
