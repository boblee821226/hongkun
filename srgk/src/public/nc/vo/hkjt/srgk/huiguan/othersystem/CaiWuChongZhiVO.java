package nc.vo.hkjt.srgk.huiguan.othersystem;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class CaiWuChongZhiVO extends SuperVO {
	private static final long serialVersionUID = 8407478365609879706L;
	private String turnid;
	private String billid;
	private String operatedate;
	private UFDate dbilldate;
	private String memberid;
	private String waternum;
	private String memberid_b;
	private Integer numbercount;
	private UFDouble yingshou;
	private UFDouble shishou;
	private String goodscatalogid;
	private String goodscatalogname;
	private String goodsid;
	private String goodsname;
	private String paymethod;
	private String context;
	private UFDouble pos;
	private UFDouble xianjin;
	private UFDouble wanglai;
	private String pk_org;
	private String mebercardid;
	private UFDouble numberxount;
	
	
	
	public static final String POS="pos";
	public static final String XIANJIN="xianjin";
	public static final String WANGLAI="wanglai";
	@Override
	public String getTableName() {
		// TODO 自动生成的方法存根
		return "hk_srgk_hg_caiwu";
	}

	@Override
	public String getPrimaryKey() {
		// TODO 自动生成的方法存根
		return billid;
	}

	public String getTurnid() {
		return turnid;
	}

	public void setTurnid(String turnid) {
		this.turnid = turnid;
	}

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public String getOperatedate() {
		return operatedate;
	}

	public void setOperatedate(String operatedate) {
		this.operatedate = operatedate;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}



	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public UFDouble getShishou() {
		return shishou;
	}

	public void setShishou(UFDouble shishou) {
		this.shishou = shishou;
	}

	public UFDate getDbilldate() {
		return dbilldate;
	}

	public void setDbilldate(UFDate dbilldate) {
		this.dbilldate = dbilldate;
	}

	public String getWaternum() {
		return waternum;
	}

	public void setWaternum(String waternum) {
		this.waternum = waternum;
	}


	public Integer getNumbercount() {
		return numbercount;
	}

	public void setNumbercount(Integer numbercount) {
		this.numbercount = numbercount;
	}

	public UFDouble getYingshou() {
		return yingshou;
	}

	public void setYingshou(UFDouble yingshou) {
		this.yingshou = yingshou;
	}

	public String getGoodscatalogid() {
		return goodscatalogid;
	}

	public void setGoodscatalogid(String goodscatalogid) {
		this.goodscatalogid = goodscatalogid;
	}

	public String getGoodscatalogname() {
		return goodscatalogname;
	}

	public void setGoodscatalogname(String goodscatalogname) {
		this.goodscatalogname = goodscatalogname;
	}


	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public UFDouble getPos() {
		return pos;
	}

	public void setPos(UFDouble pos) {
		this.pos = pos;
	}

	public UFDouble getXianjin() {
		return xianjin;
	}

	public void setXianjin(UFDouble xianjin) {
		this.xianjin = xianjin;
	}


	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getMemberid_b() {
		return memberid_b;
	}

	public void setMemberid_b(String memberid_b) {
		this.memberid_b = memberid_b;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public UFDouble getWanglai() {
		return wanglai;
	}

	public void setWanglai(UFDouble wanglai) {
		this.wanglai = wanglai;
	}

	public String getMebercardid() {
		return mebercardid;
	}

	public void setMebercardid(String mebercardid) {
		this.mebercardid = mebercardid;
	}

	public UFDouble getNumberxount() {
		return numberxount;
	}

	public void setNumberxount(UFDouble numberxount) {
		this.numberxount = numberxount;
	}
}
