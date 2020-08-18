package nc.api_oa.hkjt.itf;

import nc.api_oa.hkjt.vo.LoginVO;
import nc.vo.pub.BusinessException;

/**
 * API ҵ����ӿ�
 */
public interface ApiBusinessItf {
	
	public Object doBusiness(
		String account,		// ����
		String userid,		// �û�
		String billtype,	// ����
		String action,		// ����
		Object paramObj,	// ���ݣ�������
		String token,		// ����
		LoginVO loginVO,	// ��¼�û�VO
		Object other		// ���������ã�
	)throws BusinessException; 
	
}
