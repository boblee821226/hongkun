package nc.api.admin.vo;

import java.io.Serializable;

/**
 * nc 返回结果的VO
 */
public class ResponseResultVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7959889930124710054L;

	private String data;		//json数据
	private Integer code;		//状态码 200为成功 非0为错误码
	private String msg;			//状态描述

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
