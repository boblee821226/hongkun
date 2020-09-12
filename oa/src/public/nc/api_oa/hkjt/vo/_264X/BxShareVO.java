package nc.api_oa.hkjt.vo._264X;

import java.io.Serializable;

public class BxShareVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3076186239485866893L;

	private String cdgs;	// 承担公司
	private String cdbm;	// 承担部门
	private String zcxm;	// 收支项目
	private Double ftbl;	// 分摊比例
	private Double cdje;	// 承担金额
	private Double sl;		// 税率
	private Double se;		// 税额
	public String getCdgs() {
		return cdgs;
	}
	public void setCdgs(String cdgs) {
		this.cdgs = cdgs;
	}
	public String getCdbm() {
		return cdbm;
	}
	public void setCdbm(String cdbm) {
		this.cdbm = cdbm;
	}
	public String getZcxm() {
		return zcxm;
	}
	public void setZcxm(String zcxm) {
		this.zcxm = zcxm;
	}
	public Double getFtbl() {
		return ftbl;
	}
	public void setFtbl(Double ftbl) {
		this.ftbl = ftbl;
	}
	public Double getCdje() {
		return cdje;
	}
	public void setCdje(Double cdje) {
		this.cdje = cdje;
	}
	public Double getSl() {
		return sl;
	}
	public void setSl(Double sl) {
		this.sl = sl;
	}
	public Double getSe() {
		return se;
	}
	public void setSe(Double se) {
		this.se = se;
	}
	
}
