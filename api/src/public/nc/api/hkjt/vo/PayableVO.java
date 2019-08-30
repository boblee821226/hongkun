package nc.api.hkjt.vo;

import java.io.Serializable;
/**
 * 缴费通知VO
 * @author lb
 *
 */
public class PayableVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7045360356911220319L;

	private String orgCode;			// 所属物业公司编码
	private String payableType;		// 缴费通知类型
	private String payableId;		// 缴费通知ID
	private String custId;			// 客户ID
	private String custCode;		// 客户编码
	private String custName;		// 客户名称
	private Double payableMoney;	// 应缴金额
	private String scomment;		// 摘要
	private String roomId;			// 房间ID
	private String roomName;		// 房间名称-房间号
	private String equipmentLocation;	// 水电表-位置
	private String equipmentType;		// 水电表-类型
	private String ctCode;				// 合同号
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getPayableType() {
		return payableType;
	}
	public void setPayableType(String payableType) {
		this.payableType = payableType;
	}
	public String getPayableId() {
		return payableId;
	}
	public void setPayableId(String payableId) {
		this.payableId = payableId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public Double getPayableMoney() {
		return payableMoney;
	}
	public void setPayableMoney(Double payableMoney) {
		this.payableMoney = payableMoney;
	}
	public String getScomment() {
		return scomment;
	}
	public void setScomment(String scomment) {
		this.scomment = scomment;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getEquipmentLocation() {
		return equipmentLocation;
	}
	public void setEquipmentLocation(String equipmentLocation) {
		this.equipmentLocation = equipmentLocation;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getCtCode() {
		return ctCode;
	}
	public void setCtCode(String ctCode) {
		this.ctCode = ctCode;
	}
}
