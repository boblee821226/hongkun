package nc.api_oa.hkjt.vo._263X;

import java.io.Serializable;

public class JkBxQueryVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7966993304724977452L;

	private String djbh;		// ����
	private String djrq;		// �������ڣ�yyyy-MM-dd��
	private String szgs;		// ��˾
	private Boolean callable;	// �Ƿ���ջ�
	
	public String getDjbh() {
		return djbh;
	}
	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}
	public String getDjrq() {
		return djrq;
	}
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	public String getSzgs() {
		return szgs;
	}
	public void setSzgs(String szgs) {
		this.szgs = szgs;
	}
	public Boolean getCallable() {
		return callable;
	}
	public void setCallable(Boolean callable) {
		this.callable = callable;
	}

}
