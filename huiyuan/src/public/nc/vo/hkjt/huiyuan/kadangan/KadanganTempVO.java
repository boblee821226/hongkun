package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class KadanganTempVO extends SuperVO {
	
	private static final long serialVersionUID = -1025713421641108144L;
	
	private String memberid;
	private String membername;
	private String cardtypeid;
	private UFDouble leavemoney;
	private String trueenterdate;
	private String lastenterdate;
	private String coach;
	private String kastatus;
	private UFDouble kabili;

	public UFDouble getKabili() {
		return kabili;
	}
	public void setKabili(UFDouble kabili) {
		this.kabili = kabili;
	}
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public String getCardtypeid() {
		return cardtypeid;
	}
	public void setCardtypeid(String cardtypeid) {
		this.cardtypeid = cardtypeid;
	}
	public UFDouble getLeavemoney() {
		return leavemoney;
	}
	public void setLeavemoney(UFDouble leavemoney) {
		this.leavemoney = leavemoney;
	}
	public String getTrueenterdate() {
		return trueenterdate;
	}
	public void setTrueenterdate(String trueenterdate) {
		this.trueenterdate = trueenterdate;
	}
	public String getLastenterdate() {
		return lastenterdate;
	}
	public void setLastenterdate(String lastenterdate) {
		this.lastenterdate = lastenterdate;
	}
	public String getKastatus() {
		return kastatus;
	}
	public void setKastatus(String kastatus) {
		this.kastatus = kastatus;
	}
	
	
	@Override
	public String getTableName() {
		return "hk_huiyuan_kadangan_temp";
	}
	@Override
	public String getPrimaryKey() {
		return memberid;
	}

}
