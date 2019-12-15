package nc.api.hkjt.vo;

import java.io.Serializable;
/**
 * �ɷѼ�¼
 * @author lb
 *
 */
public class PayVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3511147457472224612L;

	private String orgCode;			// ������ҵ��˾����
	private String custId;			// �ͻ�ID
	private String custCode;		// �ͻ�����
	private String custName;		// �ͻ�����
	private String webCode;			// WEB�˵ĵ���
	private String ncCode;			// NC�˵ĵ���
	private String status;			// ״̬��δ��Ч������Ч��
	private PayBVO[] payBVOs;		// ����VO
	
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
