package nc.api_oa.hkjt.vo._263X;

import java.io.Serializable;

/**
 * 借款HeadVO
 */
public class JKHeaderVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3711883053250962730L;

	private String pk_tradetypeid; // 交易类型
	private String djbh;	// 单号
	private String djrq;	// 单据日期（yyyy-MM-dd）
	private String fkyhzh;	// 付款银行账户
	private Double total;	// 合计金额
	private String jsfs;	// 结算方式
	private String fydwbm;	// 费用承担公司
	private String fydeptid;// 费用承担部门
	private String dwbm;	// 公司
	private String deptid;	// 部门
	private String jkbxr;	// 借款人员
	private Integer paytarget;	// 收款对象 0员工 1供应商 2客户
	private String receiver;	// 收款-人
	private String skyhzh;		// 收款-人-银行账户
	private String hbbm;		// 收款-供应商
	private String customer;	// 收款-客户
	private String custaccount;	// 收款-客商-银行账户
	private String zyx19;		// 是否结算中心付款：是、否
	
	public String getPk_tradetypeid() {
		return pk_tradetypeid;
	}
	public void setPk_tradetypeid(String pk_tradetypeid) {
		this.pk_tradetypeid = pk_tradetypeid;
	}
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
	public String getFkyhzh() {
		return fkyhzh;
	}
	public void setFkyhzh(String fkyhzh) {
		this.fkyhzh = fkyhzh;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getJsfs() {
		return jsfs;
	}
	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}
	public String getFydwbm() {
		return fydwbm;
	}
	public void setFydwbm(String fydwbm) {
		this.fydwbm = fydwbm;
	}
	public String getFydeptid() {
		return fydeptid;
	}
	public void setFydeptid(String fydeptid) {
		this.fydeptid = fydeptid;
	}
	public String getDwbm() {
		return dwbm;
	}
	public void setDwbm(String dwbm) {
		this.dwbm = dwbm;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getJkbxr() {
		return jkbxr;
	}
	public void setJkbxr(String jkbxr) {
		this.jkbxr = jkbxr;
	}
	public Integer getPaytarget() {
		return paytarget;
	}
	public void setPaytarget(Integer paytarget) {
		this.paytarget = paytarget;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSkyhzh() {
		return skyhzh;
	}
	public void setSkyhzh(String skyhzh) {
		this.skyhzh = skyhzh;
	}
	public String getHbbm() {
		return hbbm;
	}
	public void setHbbm(String hbbm) {
		this.hbbm = hbbm;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCustaccount() {
		return custaccount;
	}
	public void setCustaccount(String custaccount) {
		this.custaccount = custaccount;
	}
	public String getZyx19() {
		return zyx19;
	}
	public void setZyx19(String zyx19) {
		this.zyx19 = zyx19;
	}
	
}
