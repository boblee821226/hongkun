package nc.api_oa.hkjt.vo;

import java.io.Serializable;

/**
 * web�� ���������VO
 */
public class RequestParamVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8209889414887217352L;

	private String account;		// ����\����Դ���ɿգ�Ĭ��Ϊdesign��
	private String userCode;	// �û������룩
	private String billType;	// �������ͣ����룩�����ŷָ������
	private String action;		// ���������룩
	private String data;		// json���ݣ����룩
	private String token;		// ���ƣ����ã�
	private String cacheUserId;	// �����û�id
	private String busiDate;	// ҵ������

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCacheUserId() {
		return cacheUserId;
	}

	public void setCacheUserId(String cacheUserId) {
		this.cacheUserId = cacheUserId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBusiDate() {
		return busiDate;
	}

	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}

}
