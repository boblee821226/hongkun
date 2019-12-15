package nc.api.hkjt.vo;

import java.io.Serializable;
/**
 * �ͻ���ϢVO
 * �� WEB�� ����
 */
public class CustomerVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9085405104433882771L;
	
	private String orgCode;			// ������ҵ��˾����
	private String custId;			// �ͻ�ID
	private String custCode;		// �ͻ�����
	private String custName;		// �ͻ�����
	private String tradeName;		// ������ҵ����
	private String tradeId;			// ������ҵID
	private Double registerfund;	// ע���ʽ�
	private String currtype;		// ����
	private String inTime;			// ������פʱ��
	private String bankName;		// ��������
	private String accnum;			// �����˺�
	private String address;			// ��ַ
	private String phone;			// �绰
	private String tin;				// ��˰��ʶ���
	
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
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public Double getRegisterfund() {
		return registerfund;
	}
	public void setRegisterfund(Double registerfund) {
		this.registerfund = registerfund;
	}
	public String getCurrtype() {
		return currtype;
	}
	public void setCurrtype(String currtype) {
		this.currtype = currtype;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccnum() {
		return accnum;
	}
	public void setAccnum(String accnum) {
		this.accnum = accnum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
	
}
