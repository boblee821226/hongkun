package nc.vo.pms;

import nc.vo.pub.SuperVO;

public class BillingQueryHistoryVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2984224098151982822L;
	
	private String hotelCode;	// ¾Æµê±àºÅ
	private String queryDate;	// ²éÑ¯ÈÕÆÚ
	
	private String reservationNumber;
	private String ddate;
	private String roomNumber;
	private String transactionCode;
	private String transactionDesc;
	private String remark;
	private Double amount;
	
	private Integer dr;
	private String ts;
	
	@Override
	public String getTableName() {
		return "hk_pms_billHistories";
	}
	public String getReservationNumber() {
		return reservationNumber;
	}
	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	public String getDdate() {
		return ddate;
	}
	public void setDdate(String ddate) {
		this.ddate = ddate.replaceFirst("Z", "").replaceFirst("T", " ");
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getTransactionDesc() {
		return transactionDesc;
	}
	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getDr() {
		return dr;
	}
	public void setDr(Integer dr) {
		this.dr = dr;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getQueryDate() {
		return queryDate;
	}
	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}
	
}
