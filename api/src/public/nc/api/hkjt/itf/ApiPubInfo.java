package nc.api.hkjt.itf;

import hd.vo.pub.tools.PuPubVO;

import java.util.HashMap;

import uap.xbrl.adapter.log.Logger;

import nc.api.hkjt.vo.LoginVO;
import nc.pub.pushlet.util.Rand;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;

public class ApiPubInfo {
	
	/**
	 * 类型
	 */
	public static String BILLTYPE_LOGIN 	= "LOGIN";		// 登录
	public static String BILLTYPE_CUSTOMER 	= "CUSTOMER";	// 客户
	public static String BILLTYPE_MANAGER 	= "MANAGER";	// 员工
	public static String BILLTYPE_ROOM 		= "ROOM";		// 房间
	public static String BILLTYPE_CONTRACT 	= "CONTRACT";	// 合同
	public static String BILLTYPE_PAYABLE 	= "PAYABLE";	// 缴费通知（应缴）
	public static String BILLTYPE_PAY	 	= "PAY";		// 缴费记录（实缴）
	
	/**
	 * 动作
	 */
	public static String ACTION_ADD = "ADD";	// 增加
	public static String ACTION_DEL = "DEL";	// 删除
	public static String ACTION_MOD = "MOD";	// 修改
	public static String ACTION_QUY = "QUY";	// 查询（检查）
	
	/**
	 * 登录用户的身份
	 */
	public static String USERTYPE_MANAGER  = "manager";
	public static String USERTYPE_CUSTOMER = "customer";
	
	/**
	 * 超级用户
	 */
	public static String ADMIN = "admin";
	
	/**
	 * 对照表
	 */
	public static HashMap<String,Class> MAPPER = new HashMap<String,Class>();
	
	/**
	 * 不需要令牌的，否则 都要TOKEN
	 */
	public static HashMap<String,Object> NO_TOKEN = new HashMap<String,Object>();
	
	/**
	 * TOKEN 缓存
	 */
	public static HashMap<String,LoginVO> TOKEN = new HashMap<String,LoginVO>();
	
	/**
	 * 静态构造函数
	 */
	static
	{
		/**
		 * 不需要 TOKEN
		 */
		NO_TOKEN.put(	// 登录、查询
			BILLTYPE_LOGIN+"_"+ACTION_QUY,
			null
		);
		/***END***/
		
		/**
		 * 对照表
		 */
		MAPPER.put(	// 登录、查询
			BILLTYPE_LOGIN+"_"+ACTION_QUY, 
			LoginVO.class
		);
		MAPPER.put(	// 客户、查询
			BILLTYPE_CUSTOMER+"_"+ACTION_QUY, 
			LoginVO.class
		);
		MAPPER.put(	// 客户、修改
			BILLTYPE_CUSTOMER+"_"+ACTION_MOD, 
			LoginVO.class
		);
		MAPPER.put(	// 房间、查询
			BILLTYPE_ROOM+"_"+ACTION_QUY, 
			LoginVO.class
		);
		MAPPER.put(	// 员工、查询
			BILLTYPE_MANAGER+"_"+ACTION_QUY, 
			LoginVO.class
		);
		MAPPER.put(	// 合同、查询
			BILLTYPE_CONTRACT+"_"+ACTION_QUY, 
			LoginVO.class
		);
		MAPPER.put(	// 缴费通知、查询
			BILLTYPE_PAYABLE+"_"+ACTION_QUY, 
			LoginVO.class
		);
		MAPPER.put(	// 缴费记录、查询
			BILLTYPE_PAY+"_"+ACTION_QUY, 
			LoginVO.class
		);
		MAPPER.put(	// 缴费记录、新增
			BILLTYPE_PAY+"_"+ACTION_ADD, 
			LoginVO.class
		);
		/***END***/
	}
	
	/**
	 * Token 长度
	 */
	public static Integer TOKEN_LENGTH = 32;
	/**
	 * 获取随机字符串
	 */
	public static String getRandChar(int length){
		
		String     strPol = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz!@#*=-+";
		char[]    charPol = strPol.toCharArray();
		char[] charResult = new char[length];
		int max = strPol.length()-1;
		
		for(int i=0;i<length;i++){
			int randIndex = Rand.randomInt(0, max);
			charResult[i] = charPol[randIndex];
	    }
		return new String(charResult);
	}
	
	/**
	 * 检查令牌是否合法，如果合法  返回  TokenUserVO，否则返回空
	 */
	public static LoginVO check_TOKEN(String token) throws BusinessException{
		
		String token_temp = PuPubVO.getString_TrimZeroLenAsNull(token);
		
		if(token_temp==null||token_temp.length()!=TOKEN_LENGTH)
		{
			throw new BusinessException("令牌不合法");
		}
		
		LoginVO tokenVO = TOKEN.get(token);
		
		if(tokenVO==null)
		{
			throw new BusinessException("令牌不存在");
		}
		
		String nowTime = new UFDateTime().toString();
		
		if(tokenVO.getLoseTime().compareToIgnoreCase(nowTime)<0)
		{// 如果令牌的失效时间 小于 当前时间  则提示过期，并且删除令牌
			TOKEN.remove(token);
			throw new BusinessException("令牌已过期");
		}
		
		return tokenVO;
	}
	
	/**
	 * 检查密码是否合法，true，否则返回false
	 */
	public static UFBoolean check_PWD(LoginVO loginVO){
		
		try
		{
			String userCode = loginVO.getUserCode();
			String userType = loginVO.getUserType();	// manager  customer
			String  userPwd = loginVO.getUserPwd();
			String yyyymmdd = new UFDate().toString().substring(0,10).replace("-","");
			String  	key	= "HKJT";
			
			String md5_source = userCode + "&" + userType + "&" + yyyymmdd + "&" + key;
			
			String md5_result = uap.serverdes.appesc.MD5Util.getMD5(md5_source);
			
			if(userPwd.equalsIgnoreCase(md5_result)){
				return UFBoolean.TRUE;
			}
			
		}catch(Exception ex){
			Logger.debug(ex.getMessage());
		}
		
		return UFBoolean.FALSE;
	}
	
}
