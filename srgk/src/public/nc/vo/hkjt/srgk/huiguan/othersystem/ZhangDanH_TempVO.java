package nc.vo.hkjt.srgk.huiguan.othersystem;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class ZhangDanH_TempVO extends SuperVO {
	private static final long serialVersionUID = 8407478365609879706L;
	private String turnid;
	private String billid;
	private String operatedate;
	private String context;
	private UFDouble oldmoney;
	private UFDouble favourmoney;
	private UFDouble shishou;
	private String memberid;
	@Override
	public String getTableName() {
		// TODO 自动生成的方法存根
		return "hk_srgk_hg_zhangdan_temp";
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

	public UFDouble getOldmoney() {
		return oldmoney;
	}

	public void setOldmoney(UFDouble oldmoney) {
		this.oldmoney = oldmoney;
	}

	public UFDouble getFavourmoney() {
		return favourmoney;
	}

	public void setFavourmoney(UFDouble favourmoney) {
		this.favourmoney = favourmoney;
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
	
	/**
	 * 李彬  2016年8月26日19:21:30
	 */
	private String changetime;	// 交班时间
	public String getChangetime() {
		return changetime;
	}
	public void setChangetime(String changetime) {
		this.changetime = changetime;
	}
	/**END*/
}
