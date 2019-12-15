package nc.api.hkjt.vo;

import java.io.Serializable;
/**
 * 登录信息的VO， 用于，web传输 和 后台存储
 */
public class LoginVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3024639669483270961L;
	// 前台传递
	private String userType;	// 类型：客户、业务员
	private String userCode;
	private String userName;
	private String userPwd;		// userCode+userType+YYYYMMDD+HKJT  MD5加密的串
	// 后台存储
	private String token;
	private String loginTime;	// 登录时间(yyyy-mm-dd hh24:mi:ss)
	private String loseTime;	// 失效时间(yyyy-mm-dd hh24:mi:ss)
	private String userID;		// 用户ID
	
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
