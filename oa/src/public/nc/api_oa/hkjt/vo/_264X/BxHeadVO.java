package nc.api_oa.hkjt.vo._264X;

import java.io.Serializable;

/**
 * 报销HeadVO
 */
public class BxHeadVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3711883053250962730L;

	private String djbh;	// 单号 y
	private String djrq;	// 单据日期（yyyy-MM-dd） y
	private Float spje;		// 合计金额 y
	private String xght;	// 相关合同 y
	private String jsfs;	// 结算方式 y
	private String djbt;	// 单据标题 y
	private String fycdgs;	// 费用承担公司 y
	private String fycdbm;	// 费用承担部门 y
	private String szgs;	// 公司 y
	private String szbm;	// 部门 y
	private String zdr;		// 制单人 y
	private String skdx;	// 收款对象 0员工 1供应商 2客户
	private String skr;			// 收款-人 y
	private String gryhzh;		// 收款-人-银行账户 y
	private String gys;			// 收款-供应商 y
	private String kh;			// 收款-客户 y
	private String ksyhzh;		// 收款-客商-银行账户 y
	/**
	 * 工程付款
	 */
	private String kgrq;	// 开工日期
	private String jgrq;	// 竣工日期
	private String gcwxsqd;	// 工程维修申请单
	private String xmmc;	// 项目名称
	
	private String url;	// oa路径
	
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
	public String getXght() {
		return xght;
	}
	public void setXght(String xght) {
		this.xght = xght;
	}
	public String getJsfs() {
		return jsfs;
	}
	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}
	public String getDjbt() {
		return djbt;
	}
	public void setDjbt(String djbt) {
		this.djbt = djbt;
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
	public String getGys() {
		return gys;
	}
	public void setGys(String gys) {
		this.gys = gys;
	}
	public String getKh() {
		return kh;
	}
	public void setKh(String kh) {
		this.kh = kh;
	}
	public String getKsyhzh() {
		return ksyhzh;
	}
	public void setKsyhzh(String ksyhzh) {
		this.ksyhzh = ksyhzh;
	}
	public String getKgrq() {
		return kgrq;
	}
	public void setKgrq(String kgrq) {
		this.kgrq = kgrq;
	}
	public String getJgrq() {
		return jgrq;
	}
	public void setJgrq(String jgrq) {
		this.jgrq = jgrq;
	}
	public String getGcwxsqd() {
		return gcwxsqd;
	}
	public void setGcwxsqd(String gcwxsqd) {
		this.gcwxsqd = gcwxsqd;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
