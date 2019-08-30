package nc.api.hkjt.action;

import nc.api.hkjt.itf.ApiPubInfo;
import nc.api.hkjt.vo.LoginResultVO;
import nc.api.hkjt.vo.LoginVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
/**
 * ��¼
 * @author lb
 *
 */
public class Action_LOGIN {
	
	public static Object doAction(
		 String action
		,Object data
		,Object other
	)throws BusinessException
	{
		if( action.equals(ApiPubInfo.ACTION_QUY) )
		{// ����ѯ
			return QUY(data,other);
		}
		return null;
	}
	
	/**
	 * ��ѯ���
	 */
	private static Object QUY (
			 Object data
			,Object other
	) throws BusinessException
	{
		
		/**
		 * 
		 */
		LoginVO loginVO = (LoginVO)data;
		String userCode = loginVO.getUserCode();
		String userName = loginVO.getUserName();
		String userType = loginVO.getUserType();	// manager  customer
		String  userPwd = loginVO.getUserPwd();
		
		// ����У�� userPwd �Ƿ�Ϸ���
		UFBoolean is_PWD = ApiPubInfo.check_PWD(loginVO);
		if(!is_PWD.booleanValue())
		{
			throw new BusinessException("��¼ʧ�ܣ����벻�Ϸ���");
		}
		
		if(ApiPubInfo.ADMIN.equals(userCode)){	// �����û�
			loginVO.setUserID(ApiPubInfo.ADMIN);
		}
		else if(ApiPubInfo.USERTYPE_CUSTOMER.equals(userType)){		// �ͻ�
			
//			if("cus001".equals(userCode)){
//				
//				// ��ѯ�ɹ��󣬸�ֵ ID
//				loginVO.setUserID("pk_cus001");
//				
//			}
//			else{
//				throw new BusinessException("��¼ʧ�ܣ�����ÿͻ��Ƿ���ڡ�");
//			}
		}
		else if(ApiPubInfo.USERTYPE_MANAGER.equals(userType)){	// ����Ա
			
//			if("man001".equals(userCode)){
//				
//				// ��ѯ�ɹ��󣬸�ֵ ID
//				loginVO.setUserID("pk_man001");
//				
//			}else{
//				throw new BusinessException("��¼ʧ�ܣ������ҵ��Ա�Ƿ���ڡ�");
//			}
		}
		else{
			throw new BusinessException("��¼ʧ�ܣ���ָ����¼��ݡ�");
		}
		
		String token = ApiPubInfo.getRandChar(ApiPubInfo.TOKEN_LENGTH);
		loginVO.setToken(token);						// TOKEN
		UFDateTime loginTime = new UFDateTime();
		UFDateTime  loseTime = loginTime.getDateTimeAfter(1);
		loginVO.setLoginTime(loginTime.toString());		// ��¼ʱ��
		loginVO.setLoseTime ( loseTime.toString());		// ʧЧʱ��
		
		ApiPubInfo.TOKEN.put(token,loginVO);
		
		/**
		 * ��װ����VO
		 */
		LoginResultVO resultVO = new LoginResultVO(token);
		
		return resultVO;
		
	}
	
}
