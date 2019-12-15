package nc.api.admin.vo;

import java.io.Serializable;

public class BillInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -761315158975590302L;

	private String id;
	private String ts;
	private String checkNote;		// ����
	private String approveResult;	// N-����׼  Y-��׼  R-����
	private Integer resCode;
	private String resMsg;
	
	public void setResInfo(Integer resCode, String resMsg) {
		this.setResCode(resCode);
		this.setResMsg(resMsg);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
