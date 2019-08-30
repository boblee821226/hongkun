package nc.api.hkjt.action;

import nc.api.hkjt.itf.ApiPubInfo;
import nc.api.hkjt.vo.LoginResultVO;
import nc.api.hkjt.vo.LoginVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
/**
 * 登录
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
		{// 检查查询
			return QUY(data,other);
		}
		return null;
	}
	
	/**
	 * 查询检查
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
		
		// 首先校验 userPwd 是否合法。
		UFBoolean is_PWD = ApiPubInfo.check_PWD(loginVO);
		if(!is_PWD.booleanValue())
		{
			throw new BusinessException("登录失败，密码不合法。");
		}
		
		if(ApiPubInfo.ADMIN.equals(userCode)){	// 超级用户
			loginVO.setUserID(ApiPubInfo.ADMIN);
		}
		else if(ApiPubInfo.USERTYPE_CUSTOMER.equals(userType)){		// 客户
			
//			if("cus001".equals(userCode)){
//				
//				// 查询成功后，赋值 ID
//				loginVO.setUserID("pk_cus001");
//				
//			}
//			else{
//				throw new BusinessException("登录失败，请检查该客户是否存在。");
//			}
		}
		else if(ApiPubInfo.USERTYPE_MANAGER.equals(userType)){	// 管理员
			
//			if("man001".equals(userCode)){
//				
//				// 查询成功后，赋值 ID
//				loginVO.setUserID("pk_man001");
//				
//			}else{
//				throw new BusinessException("登录失败，请检查该业务员是否存在。");
//			}
		}
		else{
			throw new BusinessException("登录失败，请指明登录身份。");
		}
		
		String token = ApiPubInfo.getRandChar(ApiPubInfo.TOKEN_LENGTH);
		loginVO.setToken(token);						// TOKEN
		UFDateTime loginTime = new UFDateTime();
		UFDateTime  loseTime = loginTime.getDateTimeAfter(1);
		loginVO.setLoginTime(loginTime.toString());		// 登录时间
		loginVO.setLoseTime ( loseTime.toString());		// 失效时间
		
		ApiPubInfo.TOKEN.put(token,loginVO);
		
		/**
		 * 封装返回VO
		 */
		LoginResultVO resultVO = new LoginResultVO(token);
		
		return resultVO;
		
	}
	
}
