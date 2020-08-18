package nc.api_oa.hkjt.vo;

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
	private Integer code;		//״̬�� 200Ϊ�ɹ� ��0Ϊ������
	private String msg;			//״̬����

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
