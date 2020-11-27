package nc.vo.tb.zior.pluginaction.fetchvalue;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class ZjsrdbVO extends SuperVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7566874700694747853L;

	private String pk_sfxm;		// 收费项目pk
	private String room_name;	// 房间号
	private String pk_room;		// 房间号PK（物料）
	private String pk_cust;		// 客户pk
	private String price;		// 平均单价
	private UFDouble mianji;	// 面积
	private String begin_date;	// 合同开始日期yyyy-mm-dd
	private String end_date;	// 合同截至日期yyyy-mm-dd（合同终止日期 和 租金确认截至日期）
	private String sfxm_name;	// 收费项目name
	private String cust_name;	// 客户name
	private String ksrq;		// 表体开始日期
	private String jzrq;		// 表体截至日期
	private Integer m_01;
	private Integer m_02;
	private Integer m_03;
	private Integer m_04;
	private Integer m_05;
	private Integer m_06;
	private Integer m_07;
	private Integer m_08;
	private Integer m_09;
	private Integer m_10;
	private Integer m_11;
	private Integer m_12;
	private String pk_szxm;		// 收支项目pk（预算）
	private String szxm_name;	// 收支项目name（预算）
	private String money;		// 金额
	private String ksrq_calc;	// 计算开始日期
	private String jzrq_calc;	// 计算截至日期
	
	public String getPk_sfxm() {
		return pk_sfxm;
	}
	public void setPk_sfxm(String pk_sfxm) {
		this.pk_sfxm = pk_sfxm;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getPk_room() {
		return pk_room;
	}
	public void setPk_room(String pk_room) {
		this.pk_room = pk_room;
	}
	public String getPk_cust() {
		return pk_cust;
	}
	public void setPk_cust(String pk_cust) {
		this.pk_cust = pk_cust;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public UFDouble getMianji() {
		return mianji;
	}
	public void setMianji(UFDouble mianji) {
		this.mianji = mianji;
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
	public String getSfxm_name() {
		return sfxm_name;
	}
	public void setSfxm_name(String sfxm_name) {
		this.sfxm_name = sfxm_name;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getKsrq() {
		return ksrq;
	}
	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}
	public String getJzrq() {
		return jzrq;
	}
	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}
	public Integer getM_01() {
		return m_01;
	}
	public void setM_01(Integer m_01) {
		this.m_01 = m_01;
	}
	public Integer getM_02() {
		return m_02;
	}
	public void setM_02(Integer m_02) {
		this.m_02 = m_02;
	}
	public Integer getM_03() {
		return m_03;
	}
	public void setM_03(Integer m_03) {
		this.m_03 = m_03;
	}
	public Integer getM_04() {
		return m_04;
	}
	public void setM_04(Integer m_04) {
		this.m_04 = m_04;
	}
	public Integer getM_05() {
		return m_05;
	}
	public void setM_05(Integer m_05) {
		this.m_05 = m_05;
	}
	public Integer getM_06() {
		return m_06;
	}
	public void setM_06(Integer m_06) {
		this.m_06 = m_06;
	}
	public Integer getM_07() {
		return m_07;
	}
	public void setM_07(Integer m_07) {
		this.m_07 = m_07;
	}
	public Integer getM_08() {
		return m_08;
	}
	public void setM_08(Integer m_08) {
		this.m_08 = m_08;
	}
	public Integer getM_09() {
		return m_09;
	}
	public void setM_09(Integer m_09) {
		this.m_09 = m_09;
	}
	public Integer getM_10() {
		return m_10;
	}
	public void setM_10(Integer m_10) {
		this.m_10 = m_10;
	}
	public Integer getM_11() {
		return m_11;
	}
	public void setM_11(Integer m_11) {
		this.m_11 = m_11;
	}
	public Integer getM_12() {
		return m_12;
	}
	public void setM_12(Integer m_12) {
		this.m_12 = m_12;
	}
	public String getPk_szxm() {
		return pk_szxm;
	}
	public void setPk_szxm(String pk_szxm) {
		this.pk_szxm = pk_szxm;
	}
	public String getSzxm_name() {
		return szxm_name;
	}
	public void setSzxm_name(String szxm_name) {
		this.szxm_name = szxm_name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getKsrq_calc() {
		return ksrq_calc;
	}
	public void setKsrq_calc(String ksrq_calc) {
		this.ksrq_calc = ksrq_calc;
	}
	public String getJzrq_calc() {
		return jzrq_calc;
	}
	public void setJzrq_calc(String jzrq_calc) {
		this.jzrq_calc = jzrq_calc;
	}
	
}
