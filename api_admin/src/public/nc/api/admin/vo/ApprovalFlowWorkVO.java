package nc.api.admin.vo;

import java.io.Serializable;

public class ApprovalFlowWorkVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2429082295445883235L;
	/**
	 * Ìá½»¡¢ÉóºË¡¢ÆúÉó¡¢ÊÕ»Ø¡¢É¾³ý
	 * {
	 * 	billinfo: [{ id:String, ts:String }]
	 * }
	 */
	private BillInfoVO[] billInfo;

	public BillInfoVO[] getBillInfo() {
		return billInfo;
	}
	public void setBillInfo(BillInfoVO[] billInfo) {
		this.billInfo = billInfo;
	}
	
}
