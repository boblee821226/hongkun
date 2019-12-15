package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class KadanganJGCKtempVO extends SuperVO {

	private static final long serialVersionUID = -722544863263558988L;
	
	private String memberid;
	private String lastcounttime;
	private String itemid;
	private String itemname;
	private String startdata;
	private String expdata;
	private String waternum;
	private UFDouble times;
	
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	
	public String getLastcounttime() {
		return lastcounttime;
	}
	public void setLastcounttime(String lastcounttime) {
		this.lastcounttime = lastcounttime;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getStartdata() {
		return startdata;
	}
	public void setStartdata(String startdata) {
		this.startdata = startdata;
	}
	public String getExpdata() {
		return expdata;
	}
	public void setExpdata(String expdata) {
		this.expdata = expdata;
	}
	public String getWaternum() {
		return waternum;
	}
	public void setWaternum(String waternum) {
		this.waternum = waternum;
	}
	public UFDouble getTimes() {
		return times;
	}
	public void setTimes(UFDouble times) {
		this.times = times;
	}
	@Override
	public String getTableName() {
		return "hk_huiyuan_kadangan_jgck_temp";
	}
	@Override
	public String getPrimaryKey() {
		return waternum;
	}

}
