package nc.api.hkjt.vo;

import java.io.Serializable;
/**
 * 缴费记录
 * @author lb
 *
 */
public class PayVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3511147457472224612L;

	private String orgCode;			// 所属物业公司编码
	private String custId;			// 客户ID
	private String custCode;		// 客户编码
	private String custName;		// 客户名称
	private String webCode;			// WEB端的单号
	private String ncCode;			// NC端的单号
	private String status;			// 状态（未生效、已生效）
	private PayBVO[] payBVOs;		// 表体VO
	
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
	public String getWebCode() {
		return webCode;
	}
	public void setWebCode(String webCode) {
		this.webCode = webCode;
	}
	public String getNcCode() {
		return ncCode;
	}
	public void setNcCode(String ncCode) {
		this.ncCode = ncCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PayBVO[] getPayBVOs() {
		return payBVOs;
	}
	public void setPayBVOs(PayBVO[] payBVOs) {
		this.payBVOs = payBVOs;
	}
	
}
