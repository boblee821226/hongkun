package nc.api_oa.hkjt.vo._263X;

import java.io.Serializable;

/**
 * 借款ItemVO
 */
public class JKBusItemVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7357104247231336633L;

	private String szxmid;		// 收支项目
	private String defitem5;	// 事项说明
	private Double amount;		// 金额
	
	public String getSzxmid() {
		return szxmid;
	}
	public void setSzxmid(String szxmid) {
		this.szxmid = szxmid;
	}
	public String getDefitem5() {
		return defitem5;
	}
	public void setDefitem5(String defitem5) {
		this.defitem5 = defitem5;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
