package nc.api_oa.hkjt.vo;

import java.io.Serializable;
/**
 * ��¼��Ϣ��VO�� ���ڣ�web���� �� ��̨�洢
 */
public class LoginVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3024639669483270961L;
	// ǰ̨����
	private String userType;	// ���ͣ��ͻ���ҵ��Ա
	private String userCode;
	private String userName;
	private String userPwd;		// userCode+userType+YYYYMMDD+HKJT  MD5���ܵĴ�
	// ��̨�洢
	private String token;
	private String loginTime;	// ��¼ʱ��(yyyy-mm-dd hh24:mi:ss)
	private String loseTime;	// ʧЧʱ��(yyyy-mm-dd hh24:mi:ss)
	private String userID;		// �û�ID
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoseTime() {
		return loseTime;
	}
	public void setLoseTime(String loseTime) {
		this.loseTime = loseTime;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
