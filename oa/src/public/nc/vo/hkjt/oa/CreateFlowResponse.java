package nc.vo.hkjt.oa;

import java.util.Map;

public class CreateFlowResponse {
	private String status;
	private String error;
	private Map<String,Object> data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Map<String,Object> getData() {
		return data;
	}
	public void setData(Map<String,Object> data) {
		this.data = data;
	}

}
