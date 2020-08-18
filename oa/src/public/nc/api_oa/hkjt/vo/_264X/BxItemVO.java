package nc.api_oa.hkjt.vo._264X;

import java.io.Serializable;

/**
 * 报销ItemVO
 */
public class BxItemVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7357104247231336633L;

	private String zcxm;		// 收支项目
	private String sxsm;		// 事项说明
	private Float jshjje;		// 价税合计金额
	private Float sl;		// 税率
	private String dkfs;	// 抵扣方式
	private Float se;		// 税额
	private Float wsje;		// 无税金额
	
	public String getZcxm() {
		return zcxm;
	}
	public void setZcxm(String zcxm) {
		this.zcxm = zcxm;
	}
	public String getSxsm() {
		return sxsm;
	}
	public void setSxsm(String sxsm) {
		this.sxsm = sxsm;
	}
	public Float getJshjje() {
		return jshjje;
	}
	public void setJshjje(Float jshjje) {
		this.jshjje = jshjje;
	}
	public Float getSl() {
		return sl;
	}
	public void setSl(Float sl) {
		this.sl = sl;
	}
	public String getDkfs() {
		return dkfs;
	}
	public void setDkfs(String dkfs) {
		this.dkfs = dkfs;
	}
	public Float getSe() {
		return se;
	}
	public void setSe(Float se) {
		this.se = se;
	}
	public Float getWsje() {
		return wsje;
	}
	public void setWsje(Float wsje) {
		this.wsje = wsje;
	}

}
