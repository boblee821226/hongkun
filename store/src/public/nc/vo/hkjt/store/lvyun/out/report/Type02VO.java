package nc.vo.hkjt.store.lvyun.out.report;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;
/**
 * 实际出库成本
 */
public class Type02VO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 975723139242846998L;
	
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
	// 材料
	private String pk_inv;
	private String inv_code;
	private String inv_name;
	private String inv_unit;
	// 出库单价
	private UFDouble out_price;
	// 出库数量
	private UFDouble out_quantity;
	// 出库金额
	private UFDouble out_amount;
	// 标准用量（均次用量*销售数量）
	private UFDouble base_quantity;
	// 销售成本（出库单价*标准用量）
	private UFDouble sale_cost;
	// 数量差异（出库数量-标准用量）
	private UFDouble differ_quantity;
	// 成本差异（出库金额-销售成本）
	private UFDouble differ_cost;
	// 差异原因（备注）
	private String vnote;
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
		return "hk_report_type02";
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

	public UFDouble getOut_price() {
		return out_price;
	}

	public void setOut_price(UFDouble out_price) {
		this.out_price = out_price;
	}

	public UFDouble getOut_quantity() {
		return out_quantity;
	}

	public void setOut_quantity(UFDouble out_quantity) {
		this.out_quantity = out_quantity;
	}

	public UFDouble getOut_amount() {
		return out_amount;
	}

	public void setOut_amount(UFDouble out_amount) {
		this.out_amount = out_amount;
	}

	public UFDouble getBase_quantity() {
		return base_quantity;
	}

	public void setBase_quantity(UFDouble base_quantity) {
		this.base_quantity = base_quantity;
	}

	public UFDouble getSale_cost() {
		return sale_cost;
	}

	public void setSale_cost(UFDouble sale_cost) {
		this.sale_cost = sale_cost;
	}

	public UFDouble getDiffer_quantity() {
		return differ_quantity;
	}

	public void setDiffer_quantity(UFDouble differ_quantity) {
		this.differ_quantity = differ_quantity;
	}

	public UFDouble getDiffer_cost() {
		return differ_cost;
	}

	public void setDiffer_cost(UFDouble differ_cost) {
		this.differ_cost = differ_cost;
	}

	public String getVnote() {
		return vnote;
	}

	public void setVnote(String vnote) {
		this.vnote = vnote;
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
