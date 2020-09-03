package nc.vo.hkjt.store.lvyun.out.report;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;
/**
 * BOM标准成本
 */
public class Type01VO extends SuperVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5377960554562300653L;
	
	// 集团
	private String pk_group;
	// 组织
	private String pk_org;
	// 仓库
	private String pk_stordoc;
	// 部门
	private String pk_dept;
	// 开始日期
	private String begin_date;
	// 结束日期
	private String end_date;
	// 菜品(加工品)
	private String pk_cp;
	private String cp_code;
	private String cp_name;
	// 材料
	private String pk_inv;
	private String inv_code;
	private String inv_name;
	private String inv_unit;
	// 基本用量
	private UFDouble base_usage;
	// 使用次数
	private UFDouble use_times;
	// 均次用量
	private UFDouble time_usage;
	// 出库单价
	private UFDouble out_price;
	// 标准成本金额
	private UFDouble base_cost;
	// 销售数量
	private UFDouble sale_quantity;
	// 销售成本
	private UFDouble sale_cost;
	
	// 扩展项
	private String vdef01;
	private String vdef02;
	private String vdef03;
	private String vdef04;
	private String vdef05;
	private String vdef06;
	private String vdef07;
	private String vdef08;
	private String vdef09;
	private String vdef10;
	private UFDouble vdef11;
	private UFDouble vdef12;
	private UFDouble vdef13;
	private UFDouble vdef14;
	private UFDouble vdef15;
	private UFDouble vdef16;
	private UFDouble vdef17;
	private UFDouble vdef18;
	private UFDouble vdef19;
	private UFDouble vdef20;
	// 系统必备
	private String ts;
	private Integer dr;
	
	@Override
	public String getTableName() {
		return "hk_report_type01";
	}

	public String getPk_group() {
		return pk_group;
	}

	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getPk_stordoc() {
		return pk_stordoc;
	}

	public void setPk_stordoc(String pk_stordoc) {
		this.pk_stordoc = pk_stordoc;
	}

	public String getPk_dept() {
		return pk_dept;
	}

	public void setPk_dept(String pk_dept) {
		this.pk_dept = pk_dept;
	}

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getPk_cp() {
		return pk_cp;
	}

	public void setPk_cp(String pk_cp) {
		this.pk_cp = pk_cp;
	}

	public String getCp_code() {
		return cp_code;
	}

	public void setCp_code(String cp_code) {
		this.cp_code = cp_code;
	}

	public String getCp_name() {
		return cp_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public String getPk_inv() {
		return pk_inv;
	}

	public void setPk_inv(String pk_inv) {
		this.pk_inv = pk_inv;
	}

	public String getInv_code() {
		return inv_code;
	}

	public void setInv_code(String inv_code) {
		this.inv_code = inv_code;
	}

	public String getInv_name() {
		return inv_name;
	}

	public void setInv_name(String inv_name) {
		this.inv_name = inv_name;
	}

	public String getInv_unit() {
		return inv_unit;
	}

	public void setInv_unit(String inv_unit) {
		this.inv_unit = inv_unit;
	}

	public UFDouble getBase_usage() {
		return base_usage;
	}

	public void setBase_usage(UFDouble base_usage) {
		this.base_usage = base_usage;
	}

	public UFDouble getUse_times() {
		return use_times;
	}

	public void setUse_times(UFDouble use_times) {
		this.use_times = use_times;
	}

	public UFDouble getTime_usage() {
		return time_usage;
	}

	public void setTime_usage(UFDouble time_usage) {
		this.time_usage = time_usage;
	}

	public UFDouble getOut_price() {
		return out_price;
	}

	public void setOut_price(UFDouble out_price) {
		this.out_price = out_price;
	}

	public UFDouble getBase_cost() {
		return base_cost;
	}

	public void setBase_cost(UFDouble base_cost) {
		this.base_cost = base_cost;
	}

	public UFDouble getSale_quantity() {
		return sale_quantity;
	}

	public void setSale_quantity(UFDouble sale_quantity) {
		this.sale_quantity = sale_quantity;
	}

	public UFDouble getSale_cost() {
		return sale_cost;
	}

	public void setSale_cost(UFDouble sale_cost) {
		this.sale_cost = sale_cost;
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

	public UFDouble getVdef11() {
		return vdef11;
	}

	public void setVdef11(UFDouble vdef11) {
		this.vdef11 = vdef11;
	}

	public UFDouble getVdef12() {
		return vdef12;
	}

	public void setVdef12(UFDouble vdef12) {
		this.vdef12 = vdef12;
	}

	public UFDouble getVdef13() {
		return vdef13;
	}

	public void setVdef13(UFDouble vdef13) {
		this.vdef13 = vdef13;
	}

	public UFDouble getVdef14() {
		return vdef14;
	}

	public void setVdef14(UFDouble vdef14) {
		this.vdef14 = vdef14;
	}

	public UFDouble getVdef15() {
		return vdef15;
	}

	public void setVdef15(UFDouble vdef15) {
		this.vdef15 = vdef15;
	}

	public UFDouble getVdef16() {
		return vdef16;
	}

	public void setVdef16(UFDouble vdef16) {
		this.vdef16 = vdef16;
	}

	public UFDouble getVdef17() {
		return vdef17;
	}

	public void setVdef17(UFDouble vdef17) {
		this.vdef17 = vdef17;
	}

	public UFDouble getVdef18() {
		return vdef18;
	}

	public void setVdef18(UFDouble vdef18) {
		this.vdef18 = vdef18;
	}

	public UFDouble getVdef19() {
		return vdef19;
	}

	public void setVdef19(UFDouble vdef19) {
		this.vdef19 = vdef19;
	}

	public UFDouble getVdef20() {
		return vdef20;
	}

	public void setVdef20(UFDouble vdef20) {
		this.vdef20 = vdef20;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}
	
}
