package nc.api_oa.hkjt.vo;

import java.io.Serializable;

public class BillInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -761315158975590302L;

	private String billId;
	private String billType;
	private String ts;
	private String checkNote;		// 批语
	private String approveResult;	// N-不批准  Y-批准  R-驳回
	private Integer resCode;
	private String resMsg;
	
	public void setResInfo(Integer resCode, String resMsg) {
		this.setResCode(resCode);
		this.setResMsg(resMsg);
	}
	
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

	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public Integer getResCode() {
		return resCode;
	}
	public void setResCode(Integer resCode) {
		this.resCode = resCode;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getCheckNote() {
		return checkNote;
	}

	public void setCheckNote(String checkNote) {
		this.checkNote = checkNote;
	}

	public String getApproveResult() {
		return approveResult;
	}

	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}
	
}
