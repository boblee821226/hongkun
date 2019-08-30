package nc.vo.hkjt.srgk.huiguan.hzshuju;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class WangLaiInfoVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7127811805070446358L;
	private String pk_group;
	private String pk_org;
	private String hzdate;
	private String wanglai_name;
	private UFDouble wanglai_jine;
	
	public String getWanglai_name() {
		return wanglai_name;
	}
	public void setWanglai_name(String wanglai_name) {
		this.wanglai_name = wanglai_name;
	}
	public String getPk_group() {
		return pk_group;
	}
	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}
	public String getPk_org() {
		return pk_org;
	}
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}
	public String getHzdate() {
		return hzdate;
	}
	public void setHzdate(String hzdate) {
		this.hzdate = hzdate;
	}
	public UFDouble getWanglai_jine() {
		return wanglai_jine;
	}
	public void setWanglai_jine(UFDouble wanglai_jine) {
		this.wanglai_jine = wanglai_jine;
	}
	
}
