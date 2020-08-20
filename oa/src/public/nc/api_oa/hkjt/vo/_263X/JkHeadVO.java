package nc.api_oa.hkjt.vo._263X;

import java.io.Serializable;

/**
 * 借款HeadVO
 */
public class JkHeadVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3711883053250962730L;

	private String djbh;	// 单号 y
	private String djrq;	// 单据日期（yyyy-MM-dd） y
	private Float spje;		// 合计金额 y
	private String jsfs;	// 结算方式 y
	private String fycdgs;	// 费用承担公司 y
	private String fycdbm;	// 费用承担部门 y
	private String szgs;	// 公司 y
	private String szbm;	// 部门 y
	private String zdr;		// 制单人 y
	private String skdx;	// 收款对象 0员工 1供应商 2客户
	private String skr;		// 收款-人 y
	private String gryhzh;	// 收款-人-银行账户 y
	
	private String url;		// oa-url
	
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
	public Float getSpje() {
		return spje;
	}
	public void setSpje(Float spje) {
		this.spje = spje;
	}
	public String getJsfs() {
		return jsfs;
	}
	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}
	public String getFycdgs() {
		return fycdgs;
	}
	public void setFycdgs(String fycdgs) {
		this.fycdgs = fycdgs;
	}
	public String getFycdbm() {
		return fycdbm;
	}
	public void setFycdbm(String fycdbm) {
		this.fycdbm = fycdbm;
	}
	public String getSzgs() {
		return szgs;
	}
	public void setSzgs(String szgs) {
		this.szgs = szgs;
	}
	public String getSzbm() {
		return szbm;
	}
	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}
	public String getZdr() {
		return zdr;
	}
	public void setZdr(String zdr) {
		this.zdr = zdr;
	}
	public String getSkdx() {
		return skdx;
	}
	public void setSkdx(String skdx) {
		this.skdx = skdx;
	}
	public String getSkr() {
		return skr;
	}
	public void setSkr(String skr) {
		this.skr = skr;
	}
	public String getGryhzh() {
		return gryhzh;
	}
	public void setGryhzh(String gryhzh) {
		this.gryhzh = gryhzh;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
