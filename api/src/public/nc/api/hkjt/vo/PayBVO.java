package nc.api.hkjt.vo;

import java.io.Serializable;
/**
 * �ɷѼ�¼ ��VO
 * @author lb
 *
 */
public class PayBVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6259626103851557314L;
	
	private String payId;		// �ɷѼ�¼ID
	private Double payMoney;	// �ɷѽ��
	private String payableId;	// ��Ӧ�Ľɷ�֪ͨID
	private String scomment;	// ժҪ
	private String payItem;		// �շ���Ŀ
	private String payWay;		// �ɷѷ�ʽ
	private String ctCode;		// ��ͬ��
	
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
