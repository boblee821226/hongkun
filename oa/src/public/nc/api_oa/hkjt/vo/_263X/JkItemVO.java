package nc.api_oa.hkjt.vo._263X;

import java.io.Serializable;

/**
 * ���ItemVO
 */
public class JkItemVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7357104247231336633L;

	private String jkxm;	// ��֧��Ŀ
	private String sxsm;	// ����˵��
	private Double je;		// ���
	
	public String getJkxm() {
		return jkxm;
	}
	public void setJkxm(String jkxm) {
		this.jkxm = jkxm;
	}
	public String getSxsm() {
		return sxsm;
	}
	public void setSxsm(String sxsm) {
		this.sxsm = sxsm;
	}
	public Double getJe() {
		return je;
	}
	public void setJe(Double je) {
		this.je = je;
	}

}
