package nc.api.hkjt.itf;

import nc.api.hkjt.vo.LoginVO;
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
		Object data,		// ����
		String token,		// ����
		LoginVO loginVO,	// ��¼�û�VO
		Object other		// ���������ã�
	)throws BusinessException; 
	
}
