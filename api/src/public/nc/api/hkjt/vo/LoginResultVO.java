package nc.api.hkjt.vo;

import java.io.Serializable;

/**
 * ��¼��  ���� ����
 */
public class LoginResultVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1015075756554898486L;
	
	public LoginResultVO(String token){
		this.token = token;
	}
	
	private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
