package nc.api.hkjt.vo;

import java.io.Serializable;

/**
 * 后台 返回结果的VO
 */
public class ResponseResultVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7959889930124710054L;

	private String data;		//json数据
	private String status;		//状态 S/F 分别表示 成功和失败
	private String message;		//消息

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
