package nc.api_oa.hkjt.itf;

import java.util.HashMap;

import nc.api_oa.hkjt.vo.ActionVO;
import nc.api_oa.hkjt.vo.ApprovalFlowQueryVO;
import nc.api_oa.hkjt.vo.ApprovalFlowWorkVO;
import nc.api_oa.hkjt.vo.BillTypeVO;
import nc.api_oa.hkjt.vo._263X.JkVO;
import nc.api_oa.hkjt.vo._264X.BxVO;
import nc.vo.pub.BusinessException;

public class ApiPubInfo {
	/**
	 * ϵͳ����
	 */
	public static String USER = "NC_USER0000000000000";
	/**
	 * ��������
	 * ���������Ժ���֧�ֵĵ������ͣ�
	 */
	public static HashMap<String,BillTypeVO> BILLTYPE = new HashMap<String,BillTypeVO>();
	public static String BILLTYPE_INIT = "INIT";
	public static String BILLTYPE_DOC = "DOC";
	public static String BILLTYPE_USER = "USER";
	public static String BILLTYPE_TEST = "TEST";
	/**
	 * ����
	 */
	public static HashMap<String,ActionVO> ACTION = new HashMap<String,ActionVO>();
//	public static String ACTION_ADD = "ADD";	// ����
//	public static String ACTION_DEL = "DEL";	// ɾ��
//	public static String ACTION_MOD = "MOD";	// �޸�
//	public static String ACTION_QUY = "QUY";	// ��ѯ����飩
	public static String ACTION_QUY_COUNT = "QUY_COUNT";	// ��ѯ��������ص�����
	public static String ACTION_QUY_DO = "QUY_DO";			// ��ѯ������-��������
	public static String ACTION_QUY_DONE = "QUY_DONE";		// ��ѯ������-��������
	public static String ACTION_QUY_SEND = "QUY_SEND";// ��ѯ����-�����ύ
	public static String ACTION_QUY_SENT = "QUY_SENT";		// ��ѯ����-�����ύ
	public static String ACTION_SAVE = "SAVE";				// �ύ
	public static String ACTION_APPROVE = "APPROVE";		// ���
	public static String ACTION_UNAPPROVE = "UNAPPROVE";	// ����
	public static String ACTION_UNSAVEBILL = "UNSAVEBILL";	// �ջ�
//	public static String ACTION_SAVEBASE = "SAVEBASE";		// ���� SAVEBASE
	public static String ACTION_WRITE = "WRITE";			// ���� WRITE
	public static String ACTION_DELETE = "DELETE";			// ɾ�� DELETE
	public static String ACTION_QUERY = "QUERY";			// ��ѯ
	/**
	 * ��̬���캯��
	 */
	static
	{
		BILLTYPE.put(BILLTYPE_INIT, null);	// �������л���
		BILLTYPE.put(BILLTYPE_DOC, null);	// ���ڸ��µ�������
		BILLTYPE.put(BILLTYPE_TEST, null);	// ���ڲ���
		BILLTYPE.put("263X", new BillTypeVO("��", JkVO.class));
		BILLTYPE.put("264X", new BillTypeVO("������", BxVO.class));
		BILLTYPE.put("OA", new BillTypeVO("oa����", HashMap.class));
		
//		ACTION.put(ACTION_QUY_COUNT, new ActionVO("����������", ApprovalFlowQueryVO.class));
//		ACTION.put(ACTION_QUY_DO, new ActionVO("��������", ApprovalFlowQueryVO.class));
//		ACTION.put(ACTION_QUY_DONE, new ActionVO("��������", ApprovalFlowQueryVO.class));
//		ACTION.put(ACTION_QUY_SENT, new ActionVO("�����ύ", ApprovalFlowQueryVO.class));
//		ACTION.put(ACTION_QUY_SEND, new ActionVO("�����ύ", ApprovalFlowQueryVO.class));
		
//		ACTION.put(ACTION_SAVE, new ActionVO("�ύ", ApprovalFlowWorkVO.class));
//		ACTION.put(ACTION_APPROVE, new ActionVO("���", ApprovalFlowWorkVO.class));
//		ACTION.put(ACTION_UNAPPROVE, new ActionVO("����", ApprovalFlowWorkVO.class));
//		ACTION.put(ACTION_UNSAVEBILL, new ActionVO("�ջ�", ApprovalFlowWorkVO.class));
		
		ACTION.put(ACTION_DELETE, null);
//		ACTION.put(ACTION_SAVEBASE, null);
		ACTION.put(ACTION_WRITE, null);
		ACTION.put(ACTION_QUERY, null);
		
		ACTION.put(ACTION_WRITE + "#263X", new ActionVO("����", JkVO[].class));	// ��VO
		ACTION.put(ACTION_WRITE + "#264X", new ActionVO("����", BxVO[].class));	// ������VO
		ACTION.put(ACTION_WRITE + "#OA", new ActionVO("����", HashMap[].class));	// oa����VO
		
		ACTION.put(ACTION_DELETE + "#263X", new ActionVO("ɾ��", JkVO[].class));	// ��VO
		ACTION.put(ACTION_DELETE + "#264X", new ActionVO("ɾ��", BxVO[].class));	// ������VO
		ACTION.put(ACTION_DELETE + "#OA", new ActionVO("ɾ��", HashMap[].class));// oa����VO
		
		ACTION.put(ACTION_QUERY + "#263X", new ActionVO("��ѯ", JkVO[].class));	// ��VO
		ACTION.put(ACTION_QUERY + "#264X", new ActionVO("��ѯ", BxVO[].class));	// ������VO
		ACTION.put(ACTION_QUERY + "#OA", new ActionVO("��ѯ", HashMap[].class));	// oa����VO
		
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
	CACHE_DOC = new HashMap<String,HashMap<String,HashMap<String,HashMap<String,String>>>>();
	
	/**
	 * �����û���Ϣ,���� ���ӿ�ʱ������� userId
	 * key������Դ
	 * 		key��email
	 * 			value��userId
	 */
	public static HashMap<String, HashMap<String, String>>
	CACHE_USER = new HashMap<String, HashMap<String, String>>();
	
	/**
	 * ���ؼ�����Ϣ
	 * key������Դ
	 * 		key������name
	 * 			value��GroupId
	 */
	public static HashMap<String, HashMap<String, String>>
	CACHE_GROUP = new HashMap<String, HashMap<String, String>>();
	
	/**
	 * ֻ��һ���߳���updateCache
	 * �����߳��ڸ��»���ʱ������lock=true��������ɺ�lock=false��
	 */
	private static boolean isLocked = false;
	/**
	 * ����
	 */
	public static synchronized void lock() throws BusinessException {
		if (!isLocked) {
			isLocked = true;
		} else {
			throw new BusinessException("�������ڸ��£����Ժ����ԡ�");
		}
	}
	/**
	 * ����
	 */
	public static synchronized void unLock() throws BusinessException {
		if (isLocked) {
			isLocked = false;
		} else {
			throw new BusinessException("�������ڸ��£����Ժ����ԡ�");
		}
	}
	
}
