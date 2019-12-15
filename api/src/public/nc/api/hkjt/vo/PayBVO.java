package nc.api.hkjt.vo;

import java.io.Serializable;
/**
 * 缴费记录 子VO
 * @author lb
 *
 */
public class PayBVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6259626103851557314L;
	
	private String payId;		// 缴费记录ID
	private Double payMoney;	// 缴费金额
	private String payableId;	// 对应的缴费通知ID
	private String scomment;	// 摘要
	private String payItem;		// 收费项目
	private String payWay;		// 缴费方式
	private String ctCode;		// 合同号
	
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public String getPayableId() {
		return payableId;
	}
	public void setPayableId(String payableId) {
		this.payableId = payableId;
	}
	public String getScomment() {
		return scomment;
	}
	public void setScomment(String scomment) {
		this.scomment = scomment;
	}
	public String getPayItem() {
		return payItem;
	}
	public void setPayItem(String payItem) {
		this.payItem = payItem;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getCtCode() {
		return ctCode;
	}
	public void setCtCode(String ctCode) {
		this.ctCode = ctCode;
	}
}
