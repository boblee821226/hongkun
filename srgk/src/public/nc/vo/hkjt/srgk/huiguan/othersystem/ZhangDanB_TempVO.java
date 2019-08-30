package nc.vo.hkjt.srgk.huiguan.othersystem;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class ZhangDanB_TempVO extends SuperVO {
	private static final long serialVersionUID = 3445020128960733186L;
	private String waternum;
	private String billid;
	private String goodsname;
	private String goodscatalogname;
	private String storename;
	private String starttime;
	private String keyid;
	private String money;
	private String realmoney;
	private String status_type;
	private String mebercardid;
	private String numberxount;
	
	private UFDouble fenqu;	//分区金额
	
	
	@Override
	public String getTableName() {
		// TODO 自动生成的方法存根
		return "hk_srgk_hg_zhangdan_b_temp";
	}

	@Override
	public String getPrimaryKey() {
		// TODO 自动生成的方法存根
		return billid;
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

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getGoodscatalogname() {
		return goodscatalogname;
	}

	public void setGoodscatalogname(String goodscatalogname) {
		this.goodscatalogname = goodscatalogname;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}


	public String getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getRealmoney() {
		return realmoney;
	}

	public void setRealmoney(String realmoney) {
		this.realmoney = realmoney;
	}

	public String getStatus_type() {
		return status_type;
	}

	public void setStatus_type(String status_type) {
		this.status_type = status_type;
	}

	public String getMebercardid() {
		return mebercardid;
	}

	public void setMebercardid(String mebercardid) {
		this.mebercardid = mebercardid;
	}

	public String getNumberxount() {
		return numberxount;
	}

	public void setNumberxount(String numberxount) {
		this.numberxount = numberxount;
	}
}
