package nc.api.admin.impl;

import nc.api.admin.impl.service.ApprovalFlowQueryCountService;
import nc.api.admin.impl.service.ApprovalFlowQueryService;
import nc.api.admin.impl.service.ApprovalFlowWorkService;
import nc.api.admin.impl.service.DocService;
import nc.api.admin.itf.ApiBusinessItf;
import nc.api.admin.itf.ApiPubInfo;
import nc.api.admin.vo.ActionVO;
import nc.api.admin.vo.ApprovalFlowQueryVO;
import nc.api.admin.vo.ApprovalFlowWorkVO;
import nc.api.admin.vo.LoginVO;
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
		ActionVO actionVO = ApiPubInfo.ACTION.get(action);
		Class paramClass = actionVO.getParamClass();
		
		/**
		 * ���ڲ���
		 */
		if (ApiPubInfo.BILLTYPE_TEST.equals(billType)) {
			return "test";
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
			return DocService.doAction(account);
		}
		/**
		 * �жϣ��������Ϊ�գ����Ȳ�ѯ����
		 */
		if ( ApiPubInfo.DOC_CACHE == null 
		|| ApiPubInfo.DOC_CACHE.get(account) == null 
		) {
			DocService.doAction(account);
		}
		/**
		 * ��ѯ�������������������������������������ύ�������ύ��
		 */
		if (action.equals(ApiPubInfo.ACTION_QUY_COUNT)) {
			return ApprovalFlowQueryCountService.doAction(account, billType, paramObj, action, userId);
		}
		/**
		 * �ύ����ˣ����ء���ͨ������׼���������ջء�ɾ��
		 */
		if (paramClass == ApprovalFlowWorkVO.class) {
			return ApprovalFlowWorkService.doAction(account, billType, paramObj, action, userId);
		}
		/**
		 * ��ѯ��������Ϣ
		 */
		else if (paramClass == ApprovalFlowQueryVO.class) {
			return ApprovalFlowQueryService.doAction(account, billType, paramObj, action, userId);
		}
		return "���Ͳ�����";
	}
}
