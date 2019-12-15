package nc.vo.hkjt.huiyuan.kaxing;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class KaxingTempVO extends SuperVO {
	
	private static final long serialVersionUID = -8385162097466482181L;
	
	private String cardtypeid;	//¿¨ÐÍ±àºÅ
	private String cardtypename;// ¿¨ÐÍÃû³Æ
	private UFDouble defaultmoney;
	private UFDouble griftmoney;
	private UFDouble shishou;
	private String cardalias;
	private String groupname;
	private String vpnuse;
	private String remark;
	
	public String getCardtypeid() {
		return cardtypeid;
	}
	public void setCardtypeid(String cardtypeid) {
		this.cardtypeid = cardtypeid;
	}
	public String getCardtypename() {
		return cardtypename;
	}
	public void setCardtypename(String cardtypename) {
		this.cardtypename = cardtypename;
	}
	public UFDouble getDefaultmoney() {
		return defaultmoney;
	}
	public void setDefaultmoney(UFDouble defaultmoney) {
		this.defaultmoney = defaultmoney;
	}
	public UFDouble getGriftmoney() {
		return griftmoney;
	}
	public void setGriftmoney(UFDouble griftmoney) {
		this.griftmoney = griftmoney;
	}
	public UFDouble getShishou() {
		return shishou;
	}
	public void setShishou(UFDouble shishou) {
		this.shishou = shishou;
	}
	public String getCardalias() {
		return cardalias;
	}
	public void setCardalias(String cardalias) {
		this.cardalias = cardalias;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getVpnuse() {
		return vpnuse;
	}
	public void setVpnuse(String vpnuse) {
		this.vpnuse = vpnuse;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String getTableName() {
		return "hk_huiyuan_kaxing_temp";
	}
	@Override
	public String getPrimaryKey() {
		return cardtypeid;
	}

}
