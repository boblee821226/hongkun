package nc.vo.hkjt.srgk.huiguan.othersystem;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * @author zhangjc
 *外系统班次VO
 */
public class BanCi_TempVO extends SuperVO {
	private static final long serialVersionUID = -8049269797273671962L;

	private String changeclassid;
	private String employname;
	private String changetime;
	private Integer comeinperson;
	private Integer checkoutperson;
	private UFDouble yingshou;
	private UFDouble shishou;
	private String pk_org;
	private UFDateTime dbilldate;
	
	public String getChangeclassid() {
		return changeclassid;
	}
	public void setChangeclassid(String changeclassid) {
		this.changeclassid = changeclassid;
	}
	public String getEmployname() {
		return employname;
	}
	public void setEmployname(String employname) {
		this.employname = employname;
	}
	public String getChangetime() {
		return changetime;
	}
	public void setChangetime(String changetime) {
		this.changetime = changetime;
	}
	public Integer getComeinperson() {
		return comeinperson;
	}
	public void setComeinperson(Integer comeinperson) {
		this.comeinperson = comeinperson;
	}
	public Integer getCheckoutperson() {
		return checkoutperson;
	}
	public void setCheckoutperson(Integer checkputperson) {
		this.checkoutperson = checkputperson;
	}
	public UFDouble getYingshou() {
		return yingshou;
	}
	public void setYingshou(UFDouble yingshou) {
		this.yingshou = yingshou;
	}
	public UFDouble getShishou() {
		return shishou;
	}
	public void setShishou(UFDouble shishou) {
		this.shishou = shishou;
	}
	public String getPk_org() {
		return pk_org;
	}
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}
	public UFDateTime getDbilldate() {
		return dbilldate;
	}
	public void setDbilldate(UFDateTime dbilldate) {
		this.dbilldate = dbilldate;
	}
	@Override
	public String getTableName() {
		// TODO 自动生成的方法存根
		return "hk_srgk_hg_banci_temp";
	}
	@Override
	public String getPrimaryKey() {
		// TODO 自动生成的方法存根
		return changeclassid;
	}
	
	
}
