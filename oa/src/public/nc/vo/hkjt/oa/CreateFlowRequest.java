package nc.vo.hkjt.oa;

import com.alibaba.fastjson.JSONObject;

public class CreateFlowRequest {
	private String billType;
	private String action;
	private String data;
	
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
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
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
