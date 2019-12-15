package nc.api.admin.vo;

import java.io.Serializable;

/**
 * nc ���ؽ����VO
 */
public class ResponseResultVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7959889930124710054L;

	private String data;		//json����
	private String status;		//״̬ S/F �ֱ��ʾ �ɹ���ʧ��
	private String message;		//��Ϣ
	private Integer code;		//״̬�� 0Ϊ�ɹ� ��0Ϊ������
	private String msg;			//״̬����

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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
