package nc.vo.hkjt.srgk.huiguan.hzshuju;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class HykACkInfoVO extends SuperVO{
	private String pk_org;
	private String sqfl_name;
	private String dbilldate;
	private UFDouble xianjin;
	private UFDouble zhipiao;
	private UFDouble pos;
	private UFDouble hyaskje;
	private UFDouble hyksr;
	private UFDouble cksr;
	
	public UFDouble getZhipiao() {
		return zhipiao;
	}
	public void setZhipiao(UFDouble zhipiao) {
		this.zhipiao = zhipiao;
	}
	public String getPk_org() {
		return pk_org;
	}
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}
	public String getSqfl_name() {
		return sqfl_name;
	}
	public void setSqfl_name(String sqfl_name) {
		this.sqfl_name = sqfl_name;
	}
	public String getDbilldate() {
		return dbilldate;
	}
	public void setDbilldate(String dbilldate) {
		this.dbilldate = dbilldate;
	}
	public UFDouble getXianjin() {
		return xianjin;
	}
	public void setXianjin(UFDouble xianjin) {
		this.xianjin = xianjin;
	}
	public UFDouble getPos() {
		return pos;
	}
	public void setPos(UFDouble pos) {
		this.pos = pos;
	}
	public UFDouble getHyaskje() {
		return hyaskje;
	}
	public void setHyaskje(UFDouble hyaskje) {
		this.hyaskje = hyaskje;
	}
	public UFDouble getHyksr() {
		return hyksr;
	}
	public void setHyksr(UFDouble hyksr) {
		this.hyksr = hyksr;
	}
	public UFDouble getCksr() {
		return cksr;
	}
	public void setCksr(UFDouble cksr) {
		this.cksr = cksr;
	}
	
	
}
