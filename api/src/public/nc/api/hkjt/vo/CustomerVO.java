package nc.api.hkjt.vo;

import java.io.Serializable;
/**
 * 客户信息VO
 * 给 WEB端 返回
 */
public class CustomerVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9085405104433882771L;
	
	private String orgCode;			// 所属物业公司编码
	private String custId;			// 客户ID
	private String custCode;		// 客户编码
	private String custName;		// 客户名称
	private String tradeName;		// 所属行业名称
	private String tradeId;			// 所属行业ID
	private Double registerfund;	// 注册资金
	private String currtype;		// 币种
	private String inTime;			// 最早入驻时间
	private String bankName;		// 开户银行
	private String accnum;			// 银行账号
	private String address;			// 地址
	private String phone;			// 电话
	private String tin;				// 纳税人识别号
	
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
