package nc.vo.hkjt.zulin.yuebao;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 查询合同VO，用于月报
 *
 */
public class QueryHtVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8402875174487316565L;

	private UFDate ksrq;			// 合同明细-开始日期		开始日期
	private UFDate jsrq;			// 合同明细-结束日期		结束日期
	private UFDouble danjia;		// 合同明细-单价		单价
	private UFDouble mianji;		// 合同明细-面积		面积
	private String pk_customer;	// 合同表头-客户		对方pk
	private String pk_room;		// 合同表头-房间号
	private String pk_srxm;		// 合同明细-收入项目		支出项目
	private String pk_quyu;		// 合同表头-区域
	
	private UFDate zzrq;			// 合同表头-终止日期（退租日期）
	
	private UFDate yb_ksrq;		// 月报-开始日期		开始日期
	private UFDate yb_jsrq;		// 月报-结束日期		结束日期
	private Integer yb_days;		// 月报-计费天数		费用天数
	private UFDouble yb_mny;		// 月报-收入金额		费用
	
	private String vdef01;	// 客户-名称			对方名称
	private String vdef02;	// 房间号-名称			发票类型-名称
	private String vdef03;	// 收入项目-名称		支出项目-名称
	private String vdef04;	// 区域-名称			部门名称
	private String vdef05;	// 部门pk				部门pk
	private String vdef06;
	private String vdef07;	// 实际合同金额			合同总额
	private String vdef08;	//					整体合同开始日期
	private String vdef09;	//					整体合同结束日期
	private String vdef10;	// 合同号				合同号
	private String vdef21;	// 					生成责任凭证
	private String vdef22;
	private String vdef23;
	private String vdef24;
	private String vdef25;
	
	private UFDouble vdef11;	//					无税金额
	private UFDouble vdef12;	//					含税金额
	private UFDouble vdef13;	//					计算单价
	private UFDouble vdef14;	//					税率（印花税率）
	private UFDouble vdef15;
	
	public String getPk_customer() {
		return pk_customer;
	}
	public void setPk_customer(String pk_customer) {
		this.pk_customer = pk_customer;
	}
	public UFDate getKsrq() {
		return ksrq;
	}
	public void setKsrq(UFDate ksrq) {
		this.ksrq = ksrq;
	}
	public UFDate getJsrq() {
		return jsrq;
	}
	public void setJsrq(UFDate jsrq) {
		this.jsrq = jsrq;
	}
	public UFDouble getDanjia() {
		return danjia;
	}
	public void setDanjia(UFDouble danjia) {
		this.danjia = danjia;
	}
	public UFDouble getMianji() {
		return mianji;
	}
	public void setMianji(UFDouble mianji) {
		this.mianji = mianji;
	}
	public UFDate getYb_ksrq() {
		return yb_ksrq;
	}
	public void setYb_ksrq(UFDate yb_ksrq) {
		this.yb_ksrq = yb_ksrq;
	}
	public UFDate getYb_jsrq() {
		return yb_jsrq;
	}
	public void setYb_jsrq(UFDate yb_jsrq) {
		this.yb_jsrq = yb_jsrq;
	}
	public Integer getYb_days() {
		return yb_days;
	}
	public void setYb_days(Integer yb_days) {
		this.yb_days = yb_days;
	}
	public UFDouble getYb_mny() {
		return yb_mny;
	}
	public void setYb_mny(UFDouble yb_mny) {
		this.yb_mny = yb_mny;
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
	public String getPk_room() {
		return pk_room;
	}
	public void setPk_room(String pk_room) {
		this.pk_room = pk_room;
	}
	public String getPk_srxm() {
		return pk_srxm;
	}
	public void setPk_srxm(String pk_srxm) {
		this.pk_srxm = pk_srxm;
	}
	public String getPk_quyu() {
		return pk_quyu;
	}
	public void setPk_quyu(String pk_quyu) {
		this.pk_quyu = pk_quyu;
	}
	public UFDate getZzrq() {
		return zzrq;
	}
	public void setZzrq(UFDate zzrq) {
		this.zzrq = zzrq;
	}
	public String getVdef21() {
		return vdef21;
	}
	public void setVdef21(String vdef21) {
		this.vdef21 = vdef21;
	}
	public String getVdef22() {
		return vdef22;
	}
	public void setVdef22(String vdef22) {
		this.vdef22 = vdef22;
	}
	public String getVdef23() {
		return vdef23;
	}
	public void setVdef23(String vdef23) {
		this.vdef23 = vdef23;
	}
	public String getVdef24() {
		return vdef24;
	}
	public void setVdef24(String vdef24) {
		this.vdef24 = vdef24;
	}
	public String getVdef25() {
		return vdef25;
	}
	public void setVdef25(String vdef25) {
		this.vdef25 = vdef25;
	}
	
}
