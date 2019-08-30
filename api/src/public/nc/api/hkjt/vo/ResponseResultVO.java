package nc.api.hkjt.vo;

import java.io.Serializable;

/**
 * ��̨ ���ؽ����VO
 */
public class ResponseResultVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7959889930124710054L;

	private String data;		//json����
	private String status;		//״̬ S/F �ֱ��ʾ �ɹ���ʧ��
	private String message;		//��Ϣ

	public ResponseResultVO() {
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
