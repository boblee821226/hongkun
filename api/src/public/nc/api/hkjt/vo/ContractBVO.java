package nc.api.hkjt.vo;

import java.io.Serializable;
/**
 * 合同子信息VO
 * @author lb
 */
public class ContractBVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4782650079950616735L;
	
	private String rowNo;			// 行号
	private String payItem;			// 收费项目
	private String beginDate;		// 开始日期
	private String endDate;			// 截至日期
	private Double paymentCycle;	// 缴费周期
	private Double price;			// 单价
	private Double area;			// 面积
	private Double rental;			// 租金
	private Double payableMoney;	// 应缴金额
	private Double payMoney;		// 收款金额
	private Double notPayMoney;		// 未收款金额
	private String vnote;			// 备注
	
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getPayItem() {
		return payItem;
	}
	public void setPayItem(String payItem) {
		this.payItem = payItem;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Double getPaymentCycle() {
		return paymentCycle;
	}
	public void setPaymentCycle(Double paymentCycle) {
		this.paymentCycle = paymentCycle;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Double getRental() {
		return rental;
	}
	public void setRental(Double rental) {
		this.rental = rental;
	}
	public Double getPayableMoney() {
		return payableMoney;
	}
	public void setPayableMoney(Double payableMoney) {
		this.payableMoney = payableMoney;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public Double getNotPayMoney() {
		return notPayMoney;
	}
	public void setNotPayMoney(Double notPayMoney) {
		this.notPayMoney = notPayMoney;
	}
	public String getVnote() {
		return vnote;
	}
	public void setVnote(String vnote) {
		this.vnote = vnote;
	}
}
