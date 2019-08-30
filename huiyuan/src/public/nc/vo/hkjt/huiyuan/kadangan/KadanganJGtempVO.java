package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class KadanganJGtempVO extends SuperVO {

	private static final long serialVersionUID = -722544863263558988L;
	
	private String memberid;
	private UFDouble leavemoney;
	private String lastenterdate;
	
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public UFDouble getLeavemoney() {
		return leavemoney;
	}
	public void setLeavemoney(UFDouble leavemoney) {
		this.leavemoney = leavemoney;
	}
	public String getLastenterdate() {
		return lastenterdate;
	}
	public void setLastenterdate(String lastenterdate) {
		this.lastenterdate = lastenterdate;
	}
	@Override
	public String getTableName() {
		return "hk_huiyuan_kadangan_jg_temp";
	}
	@Override
	public String getPrimaryKey() {
		return memberid;
	}

}
