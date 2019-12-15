package nc.api.admin.itf;

import java.util.HashMap;

import nc.api.admin.vo.ActionVO;
import nc.api.admin.vo.ApprovalFlowQueryVO;
import nc.api.admin.vo.ApprovalFlowWorkVO;
import nc.api.admin.vo.BillTypeVO;

public class ApiPubInfo {
	
	/**
	 * ��������
	 * ���������Ժ���֧�ֵĵ������ͣ�
	 */
	public static HashMap<String,BillTypeVO> BILLTYPE = new HashMap<String,BillTypeVO>();
	public static String BILLTYPE_DOC = "DOC";
	public static String BILLTYPE_TEST = "TEST";
	/**
	 * ����
	 */
	public static HashMap<String,ActionVO> ACTION = new HashMap<String,ActionVO>();
//	public static String ACTION_ADD = "ADD";	// ����
//	public static String ACTION_DEL = "DEL";	// ɾ��
//	public static String ACTION_MOD = "MOD";	// �޸�
	public static String ACTION_QUY = "QUY";	// ��ѯ����飩
	public static String ACTION_QUY_COUNT = "QUY_COUNT";	// ��ѯ��������ص�����
	public static String ACTION_QUY_DO = "QUY_DO";			// ��ѯ������-��������
	public static String ACTION_QUY_DONE = "QUY_DONE";		// ��ѯ������-��������
	public static String ACTION_QUY_SEND = "QUY_SEND";// ��ѯ����-�����ύ
	public static String ACTION_QUY_SENT = "QUY_SENT";		// ��ѯ����-�����ύ
	public static String ACTION_SAVE = "SAVE";				// �ύ
	public static String ACTION_APPROVE = "APPROVE";		// ���
	public static String ACTION_UNAPPROVE = "UNAPPROVE";	// ����
	public static String ACTION_UNSAVEBILL = "UNSAVEBILL";	// �ջ�
//	public static String ACTION_SAVEBASE = "SAVEBASE";		// ����
	public static String ACTION_DELETE = "DELETE";			// ɾ��
	/**
	 * ��̬���캯��
	 */
	static
	{
		BILLTYPE.put(BILLTYPE_DOC, null);	// ���ڸ��µ�������
		BILLTYPE.put(BILLTYPE_TEST, null);	// ���ڲ���
		BILLTYPE.put("HK37", new BillTypeVO("�����±�", nc.vo.hkjt.zulin.yuebao.YuebaoBillVO.class));
		
		ACTION.put(ACTION_QUY_COUNT, new ActionVO("����������", ApprovalFlowQueryVO.class));
		ACTION.put(ACTION_QUY_DO, new ActionVO("��������", ApprovalFlowQueryVO.class));
		ACTION.put(ACTION_QUY_DONE, new ActionVO("��������", ApprovalFlowQueryVO.class));
		ACTION.put(ACTION_QUY_SENT, new ActionVO("�����ύ", ApprovalFlowQueryVO.class));
		ACTION.put(ACTION_QUY_SEND, new ActionVO("�����ύ", ApprovalFlowQueryVO.class));
		
		ACTION.put(ACTION_SAVE, new ActionVO("�ύ", ApprovalFlowWorkVO.class));
		ACTION.put(ACTION_APPROVE, new ActionVO("���", ApprovalFlowWorkVO.class));
		ACTION.put(ACTION_UNAPPROVE, new ActionVO("����", ApprovalFlowWorkVO.class));
		ACTION.put(ACTION_UNSAVEBILL, new ActionVO("�ջ�", ApprovalFlowWorkVO.class));
		ACTION.put(ACTION_DELETE, new ActionVO("ɾ��", ApprovalFlowWorkVO.class));
//		ACTION.put(ACTION_SAVEBASE, ACTION_SAVEBASE);
		
	}
	
	/**
	 * DOC_MAP�Ľṹ
	 * key������Դ
	 *    key����������ȡ����
	 *       key������id
	 *          key����������code��name �Ǳ�Ҫ���ԡ�
	 *             value������ֵ ���ַ�����
	 * ����ͨ�õĵ���
	 * 1���û�
	 * 2����Ա
	 * 3����֯����˾������...��
	 * 4���ֿ�
	 */
	public static HashMap<String,HashMap<String,HashMap<String,HashMap<String,String>>>> 
	DOC_CACHE = new HashMap<String,HashMap<String,HashMap<String,HashMap<String,String>>>>();
	
}
