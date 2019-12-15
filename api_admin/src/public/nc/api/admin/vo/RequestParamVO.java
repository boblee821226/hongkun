package nc.api.admin.vo;

import java.io.Serializable;

/**
 * web端 发送请求的VO
 */
public class RequestParamVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8209889414887217352L;

	private String account;		// 账套（可空，默认为design）
	private String userId;		// 用户（必须）
	private String billType;	// 单据类型（必须）（逗号分隔多个）
	private String action;		// 动作（必须）
	private String data;		// json数据（必须）
	private String token;		// 令牌（备用）

	public RequestParamVO() {
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

}
