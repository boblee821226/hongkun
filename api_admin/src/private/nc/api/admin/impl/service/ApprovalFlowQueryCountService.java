package nc.api.admin.impl.service;

import java.util.ArrayList;
import java.util.HashMap;

import nc.api.admin.tool.PuPubVO;
import nc.api.admin.vo.ApprovalFlowQueryVO;
import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.vo.pub.BusinessException;

public class ApprovalFlowQueryCountService {
	public static Object doAction(String account, String billType, Object paramObj, String action, String userId) throws BusinessException  {
		BaseDAO dao = new BaseDAO(account);
		ApprovalFlowQueryVO param = (ApprovalFlowQueryVO)paramObj;
		if (param==null) param = new ApprovalFlowQueryVO();
		
		// �� xx,yy �ĳ� 'xx','yy' ��ģʽ
		String billTypeWhere = "('" + billType.replaceAll(",", "','") + "')";
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		// ��������
		{
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" count(0) ")	//��������
						.append(" from pub_wf_taskreceiver wt ")	// ����ջ
						.append(" inner join pub_workflownote wf on wt.pk_wf_task = wf.pk_wf_task ")	// ������Ϣ
						.append(" where wt.dr = 0 and nvl(wf.dr, 0) = 0 ")
						.append(" and wf.approvestatus = 0 ")	// ������
						.append(" and wf.pk_billtype in ").append(billTypeWhere)	// Ҫ��ѯ�ĵ������ͣ����ŷָ����
						.append(" and wt.cuserid = '").append(userId).append("' ")	// ֻ�鱾�û�������
			;
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			Integer billCount = 0;
			if (list != null && list.size() > 0) {
				billCount = PuPubVO.getInteger_NullAs(((Object[])list.get(0))[0], 0);
			}
			result.put("doCount", billCount);
		}
		// ��������
		{
			StringBuffer querySQL = 
				new StringBuffer("select ")
						.append(" count(0) ")	//��������
						.append(" from pub_wf_taskreceiver wt ")	// ����ջ
						.append(" inner join pub_workflownote wf on wt.pk_wf_task = wf.pk_wf_task ")	// ������Ϣ
						.append(" where wt.dr = 0 and nvl(wf.dr, 0) = 0 ")
						.append(" and wf.approvestatus = 1 ")	// �Ѵ���
						.append(" and wf.pk_billtype in ").append(billTypeWhere)	// Ҫ��ѯ�ĵ������ͣ����ŷָ����
						.append(" and wt.cuserid = '").append(userId).append("' ")	// ֻ�鱾�û�������
			;
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			Integer billCount = 0;
			if (list != null && list.size() > 0) {
				billCount = PuPubVO.getInteger_NullAs(((Object[])list.get(0))[0], 0);
			}
			result.put("doneCount", billCount);
		}
		// �����ύ
		{
			StringBuffer querySQL = 
			new StringBuffer("select ")
				.append(" count(0) ")	//��������
				.append(" from pub_wf_instance wi ")	// ����ʵ��
				.append(" where wi.dr = 0 ")
				.append(" and wi.billtype in ").append(billTypeWhere)			// Ҫ��ѯ�ĵ������ͣ����ŷָ����
				.append(" and wi.billmaker = '").append(userId).append("' ")	// ֻ�鱾�û�������
			;
			ArrayList list = (ArrayList)dao.executeQuery(querySQL.toString(), new ArrayListProcessor());
			Integer billCount = 0;
			if (list != null && list.size() > 0) {
				billCount = PuPubVO.getInteger_NullAs(((Object[])list.get(0))[0], 0);
			}
			result.put("sentCount", billCount);
		}
		// �����ύ
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
