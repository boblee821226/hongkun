package nc.api_oa.hkjt.impl;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

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
import nc.api_oa.hkjt.tool.MyHttpUtil;
import nc.api_oa.hkjt.vo.LoginVO;
import nc.bs.hkjt.srgk.lvyun.workplugin.ImpLvyunData;
import nc.vo.pub.BusinessException;

public class ApiBusinessImpl implements ApiBusinessItf {

	@Override
	public Object doBusiness(
			String account,		// ����
			String userId,		// �û�
			String billType,	// ����
			String action,		// ����
			Object paramObj,	// ����(param)
			String token,		// ����
			LoginVO loginVO,	// ��¼�û�VO
			Object other		// ���������ã�
			)
	throws BusinessException 
	{
		
		/**
		 * ��ʼ��
		 */
		if (ApiPubInfo.BILLTYPE_INIT.equals(billType)) {
			Object res1 = PublicService.getGroup(account);
			Object res2 = PublicService.getUser(account);
			Object res3 = DocService.doAction(account, null);
			return "��ʼ�����";
		}
		
		/**
		 * ���ڲ���
		 */
		if (ApiPubInfo.BILLTYPE_TEST.equals(billType)) {
			// TODO ���Դ���
//			CurrEnvVO context = new CurrEnvVO();
//			context.setPk_orgs(new String[]{
////					"0001N510000000001SY3", // ������ 9
////					"0001N510000000001SY5", // ���� 11
////					"0001N510000000001SY7", // ��ɽ��Ȫ 10
////					"0001N510000000001SY1", // ѧԺ·16
//					"0001N5100000000UVI5I"	// ̫��18
//			});
//			context.getKeyMap().put("bdate", "2020-08-26");
//			context.getKeyMap().put("edate", "2020-08-26");
//			Object res = new ImpLvyunOutData().executeTask(context);
			
			/** ����-������ϸ **/
			Object res = new ImpLvyunData().executeTest(null);
			
			/** ���»��� **/
			
			
			return res;
		}
		/**
		 * ���µ���
		 * key������Դ
		 *    key����������ȡ����
		 *       key������id
		 *          key����������code��name �Ǳ�Ҫ���ԡ�
		 *             value������ֵ ���ַ�����
		 */
		if (ApiPubInfo.BILLTYPE_DOC.equals(billType)) {
			return DocService.doAction(account, null);
		}
		/**
		 * �����û�
		 *  key������Դ
		 * 		key��email
		 * 			value��userId
		 */
		if (ApiPubInfo.BILLTYPE_USER.equals(billType)) {
			return PublicService.getUser(account);
		}
		/**
		 * ���¼���
		 *  key������Դ
		 * 		key������name
		 * 			value��GroupId
		 */
		if (ApiPubInfo.BILLTYPE_USER.equals(billType)) {
			return PublicService.getGroup(account);
		}
		/**
		 * �жϣ��������Ϊ�գ����Ȳ�ѯ����
		 */
		if ( ApiPubInfo.CACHE_DOC == null 
		|| ApiPubInfo.CACHE_DOC.get(account) == null 
		) {
			return DocService.doAction(account, null);
		}
		/**
		 * �������ύ��
		 */
		if (action.equals(ApiPubInfo.ACTION_WRITE)
//		 || action.equals(ApiPubInfo.ACTION_SAVEBASE)
		) {
			// ���ݵ������� ȥ �жϣ������ĸ������ࡣ
			if (billType.startsWith("263X")) {
				return new N263XService().doAction(account, billType, paramObj, action, userId);
			} else if (billType.startsWith("264X")) {
				return new N264XService().doAction(account, billType, paramObj, action, userId);
			} else if (billType.startsWith("OA")) {
				return new OtherService().doAction(account, billType, paramObj, action, userId);
			} else {
				throw new BusinessException("��֧�ֵ�����");
			}
		}
		/**
		 * ɾ�����ջأ�
		 */
		if (action.equals(ApiPubInfo.ACTION_DELETE)) {
			// ���ݵ������� ȥ �жϣ������ĸ������ࡣ
			if (billType.startsWith("263X")) {
				return new N263XServiceDELETE().doAction(account, billType, paramObj, action, userId);
			} else if (billType.startsWith("264X")) {
				return new N264XServiceDELETE().doAction(account, billType, paramObj, action, userId);
			} else if (paramObj instanceof HashMap[]) {
				return new OtherServiceDELETE().doAction(account, billType, paramObj, action, userId);
			} else {
				throw new BusinessException("��֧�ֵ�����");
			}
		}
		/**
		 * ��ѯ
		 */
		if (action.equals(ApiPubInfo.ACTION_QUERY)) {
			// ���ݵ������� ȥ �жϣ������ĸ������ࡣ
			if (billType.startsWith("263X")) {
				return new N263XServiceQUERY().doAction(account, billType, paramObj, action, userId);
			} else if (billType.startsWith("264X")) {
				return new N264XServiceQUERY().doAction(account, billType, paramObj, action, userId);
			} else if (paramObj instanceof HashMap[]) {
				return new OtherServiceQUERY().doAction(account, billType, paramObj, action, userId);
			} else {
				throw new BusinessException("��֧�ֵ�����");
			}
		}
		/**
		 * ��ѯ�������������������������������������ύ�������ύ��
		 */
//		if (action.equals(ApiPubInfo.ACTION_QUY_COUNT)) {
//			return ApprovalFlowQueryCountService.doAction(account, billType, paramObj, action, userId);
//		}
		
//		ActionVO actionVO = ApiPubInfo.ACTION.get(action);
//		Class paramClass = actionVO.getParamClass();
		
		/**
		 * �ύ����ˣ����ء���ͨ������׼���������ջء�ɾ��
		 */
//		if (paramClass == ApprovalFlowWorkVO.class) {
//			return ApprovalFlowWorkService.doAction(account, billType, paramObj, action, userId);
//		}
		/**
		 * ��ѯ��������Ϣ
		 */
//		else if (paramClass == ApprovalFlowQueryVO.class) {
//			return ApprovalFlowQueryService.doAction(account, billType, paramObj, action, userId);
//		}
		
		return "���Ͳ�����";
	}
	
	/**
	 * ���»���
	 */
	private Object updateCache(HashMap params) {
		
		String cache_url = "http://10.0.0.50:9081,http://10.0.0.50:9082,http://10.0.0.50:9083,http://10.0.0.50:9084,http://10.0.0.50:9085,http://10.0.0.50:9086";
		
		String baseUrl = "/service/~hkjt/nc.api_oa.hkjt.BillServlet";
		cache_url = cache_url.replaceAll("��", ",").replaceAll("��", ",");
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
				String res = MyHttpUtil.doPost(url, JSONObject.toJSONString(paramObj));
				System.out.println("==res:" + res + "==");
			} catch (Exception ex) {
				
			}
		}
		
		return "���»���ɹ�";
	}
}
