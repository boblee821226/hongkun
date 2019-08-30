package nc.api.hkjt.vo;

import java.io.Serializable;
/**
 * �ɷ�֪ͨVO
 * @author lb
 *
 */
public class PayableVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7045360356911220319L;

	private String orgCode;			// ������ҵ��˾����
	private String payableType;		// �ɷ�֪ͨ����
	private String payableId;		// �ɷ�֪ͨID
	private String custId;			// �ͻ�ID
	private String custCode;		// �ͻ�����
	private String custName;		// �ͻ�����
	private Double payableMoney;	// Ӧ�ɽ��
	private String scomment;		// ժҪ
	private String roomId;			// ����ID
	private String roomName;		// ��������-�����
	private String equipmentLocation;	// ˮ���-λ��
	private String equipmentType;		// ˮ���-����
	private String ctCode;				// ��ͬ��
	
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
