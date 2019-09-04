package nc.api.hkjt.itf;

import hd.vo.pub.tools.PuPubVO;

import java.util.HashMap;

import uap.xbrl.adapter.log.Logger;

import nc.api.hkjt.vo.LoginVO;
import nc.api.hkjt.vo.QueryParamVO;
import nc.pub.pushlet.util.Rand;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;

public class ApiPubInfo {
	
	/**
	 * ����
	 */
	public static String BILLTYPE_LOGIN 	= "LOGIN";		// ��¼
	public static String BILLTYPE_CUSTOMER 	= "CUSTOMER";	// �ͻ�
	public static String BILLTYPE_MANAGER 	= "MANAGER";	// Ա��
	public static String BILLTYPE_ROOM 		= "ROOM";		// ����
	public static String BILLTYPE_CONTRACT 	= "CONTRACT";	// ��ͬ
	public static String BILLTYPE_PAYABLE 	= "PAYABLE";	// �ɷ�֪ͨ��Ӧ�ɣ�
	public static String BILLTYPE_PAY	 	= "PAY";		// �ɷѼ�¼��ʵ�ɣ�
	
	/**
	 * ����
	 */
	public static String ACTION_ADD = "ADD";	// ����
	public static String ACTION_DEL = "DEL";	// ɾ��
	public static String ACTION_MOD = "MOD";	// �޸�
	public static String ACTION_QUY = "QUY";	// ��ѯ����飩
	
	/**
	 * ��¼�û������
	 */
	public static String USERTYPE_MANAGER  = "manager";
	public static String USERTYPE_CUSTOMER = "customer";
	
	/**
	 * �����û�
	 */
	public static String ADMIN = "admin";
	
	/**
	 * ���ձ�
	 * key������+����
	 * value��data������vo����
	 */
	public static HashMap<String,Class> MAPPER = new HashMap<String,Class>();
	
	/**
	 * ����Ҫ���Ƶģ����� ��ҪTOKEN
	 */
	public static HashMap<String,Object> NO_TOKEN = new HashMap<String,Object>();
	
	/**
	 * TOKEN ����
	 */
	public static HashMap<String,LoginVO> TOKEN = new HashMap<String,LoginVO>();
	
	/**
	 * ��̬���캯��
	 */
	static
	{
		/**
		 * ����Ҫ TOKEN
		 */
		NO_TOKEN.put(	// ��¼����ѯ
			BILLTYPE_LOGIN+"_"+ACTION_QUY,
			null
		);
		/***END***/
		
		/**
		 * ���ձ�
		 */
		MAPPER.put(	// ��¼����ѯ
			BILLTYPE_LOGIN+"_"+ACTION_QUY, 
			LoginVO.class
		);
		MAPPER.put(	// �ͻ�����ѯ
			BILLTYPE_CUSTOMER+"_"+ACTION_QUY, 
			QueryParamVO.class
		);
		MAPPER.put(	// �ͻ����޸�
			BILLTYPE_CUSTOMER+"_"+ACTION_MOD, 
			QueryParamVO.class
		);
		MAPPER.put(	// ���䡢��ѯ
			BILLTYPE_ROOM+"_"+ACTION_QUY, 
			QueryParamVO.class
		);
		MAPPER.put(	// Ա������ѯ
			BILLTYPE_MANAGER+"_"+ACTION_QUY, 
			QueryParamVO.class
		);
		MAPPER.put(	// ��ͬ����ѯ
			BILLTYPE_CONTRACT+"_"+ACTION_QUY, 
			QueryParamVO.class
		);
		MAPPER.put(	// �ɷ�֪ͨ����ѯ
			BILLTYPE_PAYABLE+"_"+ACTION_QUY, 
			QueryParamVO.class
		);
		MAPPER.put(	// �ɷѼ�¼����ѯ
			BILLTYPE_PAY+"_"+ACTION_QUY, 
			QueryParamVO.class
		);
		MAPPER.put(	// �ɷѼ�¼������
			BILLTYPE_PAY+"_"+ACTION_ADD, 
			QueryParamVO.class
		);
		/***END***/
	}
	
	/**
	 * Token ����
	 */
	public static Integer TOKEN_LENGTH = 32;
	/**
	 * ��ȡ����ַ���
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
	 * ��������Ƿ�Ϸ�������Ϸ�  ����  TokenUserVO�����򷵻ؿ�
	 */
	public static LoginVO check_TOKEN(String token) throws BusinessException{
		
		String token_temp = PuPubVO.getString_TrimZeroLenAsNull(token);
		
		if(token_temp==null||token_temp.length()!=TOKEN_LENGTH)
		{
			throw new BusinessException("���Ʋ��Ϸ�");
		}
		
		LoginVO tokenVO = TOKEN.get(token);
		
		if(tokenVO==null)
		{
			throw new BusinessException("���Ʋ�����");
		}
		
		String nowTime = new UFDateTime().toString();
		
		if(tokenVO.getLoseTime().compareToIgnoreCase(nowTime)<0)
		{// ������Ƶ�ʧЧʱ�� С�� ��ǰʱ��  ����ʾ���ڣ�����ɾ������
			TOKEN.remove(token);
			throw new BusinessException("�����ѹ���");
		}
		
		return tokenVO;
	}
	
	/**
	 * ��������Ƿ�Ϸ���true�����򷵻�false
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
