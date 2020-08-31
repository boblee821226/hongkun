package nc.api_oa.hkjt.impl;

import nc.api_oa.hkjt.impl.service.ApprovalFlowQueryCountService;
import nc.api_oa.hkjt.impl.service.ApprovalFlowQueryService;
import nc.api_oa.hkjt.impl.service.ApprovalFlowWorkService;
import nc.api_oa.hkjt.impl.service.DocService;
import nc.api_oa.hkjt.impl.service.PublicService;
import nc.api_oa.hkjt.impl.service._263X.N263XService;
import nc.api_oa.hkjt.impl.service._263X.N263XServiceDELETE;
import nc.api_oa.hkjt.impl.service._264X.N264XService;
import nc.api_oa.hkjt.impl.service._264X.N264XServiceDELETE;
import nc.api_oa.hkjt.itf.ApiBusinessItf;
import nc.api_oa.hkjt.itf.ApiPubInfo;
import nc.api_oa.hkjt.vo.ActionVO;
import nc.api_oa.hkjt.vo.ApprovalFlowQueryVO;
import nc.api_oa.hkjt.vo.ApprovalFlowWorkVO;
import nc.api_oa.hkjt.vo.LoginVO;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.zyhk.btc.PubPlugin.GetQtrkdPlugin;
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
			Object res3 = DocService.doAction(account);
			return "��ʼ�����";
		}
		
		/**
		 * ���ڲ���
		 */
		if (ApiPubInfo.BILLTYPE_TEST.equals(billType)) {
			// TODO ���Դ���
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
			return DocService.doAction(account);
		}
		/**
		 * ����
		 */
		if (action.equals(ApiPubInfo.ACTION_WRITE)
		 || action.equals(ApiPubInfo.ACTION_SAVEBASE)
		) {
			// ���ݵ������� ȥ �жϣ������ĸ������ࡣ
			if (billType.startsWith("263X")) {
				return new N263XService().doAction(account, billType, paramObj, action, userId);
			} else if (billType.startsWith("264X")) {
				return new N264XService().doAction(account, billType, paramObj, action, userId);
			} else {
				throw new BusinessException("��֧�ֵ�����");
			}
		}
		/**
		 * ɾ��
		 */
		if (action.equals(ApiPubInfo.ACTION_DELETE)) {
			// ���ݵ������� ȥ �жϣ������ĸ������ࡣ
			if (billType.startsWith("263X")) {
				return new N263XServiceDELETE().doAction(account, billType, paramObj, action, userId);
			} else if (billType.startsWith("264X")) {
				return new N264XServiceDELETE().doAction(account, billType, paramObj, action, userId);
			} else {
				throw new BusinessException("��֧�ֵ�����");
			}
		}
		/**
		 * ��ѯ�������������������������������������ύ�������ύ��
		 */
		if (action.equals(ApiPubInfo.ACTION_QUY_COUNT)) {
			return ApprovalFlowQueryCountService.doAction(account, billType, paramObj, action, userId);
		}
		
		ActionVO actionVO = ApiPubInfo.ACTION.get(action);
		Class paramClass = actionVO.getParamClass();
		
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
