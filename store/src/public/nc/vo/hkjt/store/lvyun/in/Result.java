package nc.vo.hkjt.store.lvyun.in;

import java.io.Serializable;
/**
 * 接口返回
 */
public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2703473988781415821L;

	private Integer resultCode;
	private String resultMsg;
	private String errorMsg;
	private String count;
	// 加密错误时的返回
	private String errorToken;
	private String code;
	private String message;
	private String solution;
	
	public Integer getResultCode() {
		return resultCode;
	}
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getErrorToken() {
		return errorToken;
	}
	public void setErrorToken(String errorToken) {
		this.errorToken = errorToken;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	
}
