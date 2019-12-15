package nc.vo.hkjt.huiyuan.huanka;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class HuankaTempVO extends SuperVO {
	
	private static final long serialVersionUID = 7046883297938241896L;
	
	private String cardid;
	private String memberid;
	private String sourcememberid;
	private String cardtype;
	private UFDouble leavemoney;
	private String outdate;
	private String outperson;
	private String outvpn;
	
	
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getSourcememberid() {
		return sourcememberid;
	}
	public void setSourcememberid(String sourcememberid) {
		this.sourcememberid = sourcememberid;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public UFDouble getLeavemoney() {
		return leavemoney;
	}
	public void setLeavemoney(UFDouble leavemoney) {
		this.leavemoney = leavemoney;
	}
	public String getOutdate() {
		return outdate;
	}
	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}
	public String getOutperson() {
		return outperson;
	}
	public void setOutperson(String outperson) {
		this.outperson = outperson;
	}
	public String getOutvpn() {
		return outvpn;
	}
	public void setOutvpn(String outvpn) {
		this.outvpn = outvpn;
	}
	
	@Override
	public String getTableName() {
		return "hk_huiyuan_huanka_temp";
	}
	@Override
	public String getPrimaryKey() {
		return cardid;
	}
	
}
