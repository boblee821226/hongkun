package nc.api.hkjt.vo;

import java.io.Serializable;
/**
 * ��ͬ����ϢVO
 * @author lb
 */
public class ContractBVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4782650079950616735L;
	
	private String rowNo;			// �к�
	private String payItem;			// �շ���Ŀ
	private String beginDate;		// ��ʼ����
	private String endDate;			// ��������
	private Double paymentCycle;	// �ɷ�����
	private Double price;			// ����
	private Double area;			// ���
	private Double rental;			// ���
	private Double payableMoney;	// Ӧ�ɽ��
	private Double payMoney;		// �տ���
	private Double notPayMoney;		// δ�տ���
	private String vnote;			// ��ע
	
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
