package nc.api_oa.hkjt.vo._263X;

import java.io.Serializable;

/**
 * 借款ItemVO
 */
public class JkItemVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7357104247231336633L;

	private String jkxm;	// 收支项目
	private String sxsm;	// 事项说明
	private Float je;		// 金额
	
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
	public Float getJe() {
		return je;
	}
	public void setJe(Float je) {
		this.je = je;
	}

}
