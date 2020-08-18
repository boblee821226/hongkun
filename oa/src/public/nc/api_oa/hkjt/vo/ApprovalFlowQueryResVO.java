package nc.api_oa.hkjt.vo;

import java.io.Serializable;

public class ApprovalFlowQueryResVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5341345418256720800L;

	private String billId;		// 单据id
	private String ts;			// ts
	private String billType;	// 单据类型
	private String templateListData;// 模板数据
	private String sendMan;		// 发送人
	private String sendDate;	// 发送时间
	
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getTemplateListData() {
		return templateListData;
	}
	public void setTemplateListData(String templateListData) {
		this.templateListData = templateListData;
	}
	public String getSendMan() {
		return sendMan;
	}
	public void setSendMan(String sendMan) {
		this.sendMan = sendMan;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	
}
