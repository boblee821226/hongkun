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
	private Double jshjje;		// 价税合计金额
	private Double sl;		// 税率
	private String dkfs;	// 抵扣方式
	private Double se;		// 税额
	private Double wsje;		// 无税金额
	
	/**
	 * 投资业务审批单
	 */
	private Double tzje1;	// 投资金额1
	/**
	 * 差旅费审批单
	 */
	private String cfrq;	// 出发日期
	private String fhrq;	// 返回日期
	private String ccdd;	// 出差地点
	private Double ccts;	// 出差天数
	private String jtgj;	// 交通工具
	private Double jtf;		// 交通费
	private Double zsfy;	// 住宿费用
	private Double bxf;		// 保险费
	private Double cfbz;	// 餐费补助
	private Double qtfy;	// 其他费用

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
	public Double getJshjje() {
		return jshjje;
	}
	public void setJshjje(Double jshjje) {
		this.jshjje = jshjje;
	}
	public Double getSl() {
		return sl;
	}
	public void setSl(Double sl) {
		this.sl = sl;
	}
	public String getDkfs() {
		return dkfs;
	}
	public void setDkfs(String dkfs) {
		this.dkfs = dkfs;
	}
	public Double getSe() {
		return se;
	}
	public void setSe(Double se) {
		this.se = se;
	}
	public Double getWsje() {
		return wsje;
	}
	public void setWsje(Double wsje) {
		this.wsje = wsje;
	}
	public Double getTzje1() {
		return tzje1;
	}
	public void setTzje1(Double tzje1) {
		this.tzje1 = tzje1;
	}
	public String getCfrq() {
		return cfrq;
	}
	public void setCfrq(String cfrq) {
		this.cfrq = cfrq;
	}
	public String getCcdd() {
		return ccdd;
	}
	public void setCcdd(String ccdd) {
		this.ccdd = ccdd;
	}
	public Double getCcts() {
		return ccts;
	}
	public void setCcts(Double ccts) {
		this.ccts = ccts;
	}
	public String getJtgj() {
		return jtgj;
	}
	public void setJtgj(String jtgj) {
		this.jtgj = jtgj;
	}
	public Double getJtf() {
		return jtf;
	}
	public void setJtf(Double jtf) {
		this.jtf = jtf;
	}
	public Double getZsfy() {
		return zsfy;
	}
	public void setZsfy(Double zsfy) {
		this.zsfy = zsfy;
	}
	public Double getBxf() {
		return bxf;
	}
	public void setBxf(Double bxf) {
		this.bxf = bxf;
	}
	public Double getCfbz() {
		return cfbz;
	}
	public void setCfbz(Double cfbz) {
		this.cfbz = cfbz;
	}
	public Double getQtfy() {
		return qtfy;
	}
	public void setQtfy(Double qtfy) {
		this.qtfy = qtfy;
	}
	public String getFhrq() {
		return fhrq;
	}
	public void setFhrq(String fhrq) {
		this.fhrq = fhrq;
	}

}
