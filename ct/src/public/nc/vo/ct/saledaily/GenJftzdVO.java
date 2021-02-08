package nc.vo.ct.saledaily;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class GenJftzdVO extends SuperVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8934977389351965901L;
	
	public String pk_ct_sale_b;
	public String pk_ct_sale;
	public UFDouble norigtaxmny;	// 合同金额（实际为  合同金额-收款金额）
	public String busi_date;
	public String pk_customer;
	public String pk_org;
	public String pk_org_v;
	public String vbillcode;
	public String jflx;			// 收费项目
	public String vbillcode2;	// 去掉#之后的合同号
	
	private String pk_deptid;	// 部门
	private String pk_deptid_v;	// 部门v
	
	public String vdef01;	// 房间号
	public String vdef02;	// 开始日期
	public String vdef03;	// 截止日期
	public String vdef04;	// 房间号id
	public String vdef05;	// 区域id
	public String vdef06;	// 收费项目id
	public String vdef07;	// 客户名称
	public String vdef08;
	public String vdef09;
	public String vdef10;
	
	public String getPk_ct_sale_b() {
		return pk_ct_sale_b;
	}
	public void setPk_ct_sale_b(String pk_ct_sale_b) {
		this.pk_ct_sale_b = pk_ct_sale_b;
	}
	public String getPk_ct_sale() {
		return pk_ct_sale;
	}
	public void setPk_ct_sale(String pk_ct_sale) {
		this.pk_ct_sale = pk_ct_sale;
	}
	public UFDouble getNorigtaxmny() {
		return norigtaxmny;
	}
	public void setNorigtaxmny(UFDouble norigtaxmny) {
		this.norigtaxmny = norigtaxmny;
	}
	public String getBusi_date() {
		return busi_date;
	}
	public void setBusi_date(String busi_date) {
		this.busi_date = busi_date;
	}
	public String getPk_customer() {
		return pk_customer;
	}
	public void setPk_customer(String pk_customer) {
		this.pk_customer = pk_customer;
	}
	public String getPk_org() {
		return pk_org;
	}
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}
	public String getPk_org_v() {
		return pk_org_v;
	}
	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}
	public String getVbillcode() {
		return vbillcode;
	}
	public void setVbillcode(String vbillcode) {
		this.vbillcode = vbillcode;
	}
	public String getJflx() {
		return jflx;
	}
	public void setJflx(String jflx) {
		this.jflx = jflx;
	}
	public String getVbillcode2() {
		return vbillcode2;
	}
	public void setVbillcode2(String vbillcode2) {
		this.vbillcode2 = vbillcode2;
	}
	public String getVdef01() {
		return vdef01;
	}
	public void setVdef01(String vdef01) {
		this.vdef01 = vdef01;
	}
	public String getVdef02() {
		return vdef02;
	}
	public void setVdef02(String vdef02) {
		this.vdef02 = vdef02;
	}
	public String getVdef03() {
		return vdef03;
	}
	public void setVdef03(String vdef03) {
		this.vdef03 = vdef03;
	}
	public String getVdef04() {
		return vdef04;
	}
	public void setVdef04(String vdef04) {
		this.vdef04 = vdef04;
	}
	public String getVdef05() {
		return vdef05;
	}
	public void setVdef05(String vdef05) {
		this.vdef05 = vdef05;
	}
	public String getVdef06() {
		return vdef06;
	}
	public void setVdef06(String vdef06) {
		this.vdef06 = vdef06;
	}
	public String getVdef07() {
		return vdef07;
	}
	public void setVdef07(String vdef07) {
		this.vdef07 = vdef07;
	}
	public String getVdef08() {
		return vdef08;
	}
	public void setVdef08(String vdef08) {
		this.vdef08 = vdef08;
	}
	public String getVdef09() {
		return vdef09;
	}
	public void setVdef09(String vdef09) {
		this.vdef09 = vdef09;
	}
	public String getVdef10() {
		return vdef10;
	}
	public void setVdef10(String vdef10) {
		this.vdef10 = vdef10;
	}
	public String getPk_deptid() {
		return pk_deptid;
	}
	public void setPk_deptid(String pk_deptid) {
		this.pk_deptid = pk_deptid;
	}
	public String getPk_deptid_v() {
		return pk_deptid_v;
	}
	public void setPk_deptid_v(String pk_deptid_v) {
		this.pk_deptid_v = pk_deptid_v;
	}
	
}
