package nc.vo.hkjt.huiyuan.kainfo;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class KainfoTempVO extends SuperVO {
	
	private static final long serialVersionUID = -2770356095341752491L;
	
	private String waternum;
	private String billid;
	private String feeindate;
	private String cardtypeId;
	private String memberid;
	private UFDouble money;
	private UFDouble leavecardmoney;
	private UFDouble truemoney;
	private String vpnname;
	private String feetype;
	private String memberguid;
	private String operatorname;
	private String sourcememberid;
	private String sourceleavemoney;
	private String sourcememberguid;
	
	
	public String getSourcememberid() {
		return sourcememberid;
	}
	public void setSourcememberid(String sourcememberid) {
		this.sourcememberid = sourcememberid;
	}
	public String getSourceleavemoney() {
		return sourceleavemoney;
	}
	public void setSourceleavemoney(String sourceleavemoney) {
		this.sourceleavemoney = sourceleavemoney;
	}
	public String getSourcememberguid() {
		return sourcememberguid;
	}
	public void setSourcememberguid(String sourcememberguid) {
		this.sourcememberguid = sourcememberguid;
	}
	public String getWaternum() {
		return waternum;
	}
	public void setWaternum(String waternum) {
		this.waternum = waternum;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public String getFeeindate() {
		return feeindate;
	}
	public void setFeeindate(String feeindate) {
		this.feeindate = feeindate;
	}
	public String getCardtypeId() {
		return cardtypeId;
	}
	public void setCardtypeId(String cardtypeId) {
		this.cardtypeId = cardtypeId;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public UFDouble getMoney() {
		return money;
	}
	public void setMoney(UFDouble money) {
		this.money = money;
	}
	public UFDouble getLeavecardmoney() {
		return leavecardmoney;
	}
	public void setLeavecardmoney(UFDouble leavecardmoney) {
		this.leavecardmoney = leavecardmoney;
	}
	public UFDouble getTruemoney() {
		return truemoney;
	}
	public void setTruemoney(UFDouble truemoney) {
		this.truemoney = truemoney;
	}
	public String getVpnname() {
		return vpnname;
	}
	public void setVpnname(String vpnname) {
		this.vpnname = vpnname;
	}
	public String getFeetype() {
		return feetype;
	}
	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}
	public String getMemberguid() {
		return memberguid;
	}
	public void setMemberguid(String memberguid) {
		this.memberguid = memberguid;
	}
	public String getOperatorname() {
		return operatorname;
	}
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}
	
	
	@Override
	public String getTableName() {
		return "hk_huiyuan_kainfo_temp";
	}
	@Override
	public String getPrimaryKey() {
		return waternum;
	}

}
