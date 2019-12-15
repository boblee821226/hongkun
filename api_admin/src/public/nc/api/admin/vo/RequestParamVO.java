package nc.api.admin.vo;

import java.io.Serializable;

/**
 * web�� ���������VO
 */
public class RequestParamVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8209889414887217352L;

	private String account;		// ���ף��ɿգ�Ĭ��Ϊdesign��
	private String userId;		// �û������룩
	private String billType;	// �������ͣ����룩�����ŷָ������
	private String action;		// ���������룩
	private String data;		// json���ݣ����룩
	private String token;		// ���ƣ����ã�

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
