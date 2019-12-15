package nc.vo.hkjt.srgk.huiguan.hzshuju;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class YyrbDeptInfoVO extends SuperVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8073596656786456977L;
	private String pk_group;
	private String pk_org;
	private String pk_dept;
	private String pk_srxm;
	private String hzdate;
	private String pk_fsrxm;
	private String name;
	private String code;
	private UFDouble yinghsou;
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
	public String getPk_dept() {
		return pk_dept;
	}
	public void setPk_dept(String pk_dept) {
		this.pk_dept = pk_dept;
	}
	public String getPk_srxm() {
		return pk_srxm;
	}
	public void setPk_srxm(String pk_srxm) {
		this.pk_srxm = pk_srxm;
	}
	public String getHzdate() {
		return hzdate;
	}
	public void setHzdate(String hzdate) {
		this.hzdate = hzdate;
	}
	public String getPk_fsrxm() {
		return pk_fsrxm;
	}
	public void setPk_fsrxm(String pk_fsrxm) {
		this.pk_fsrxm = pk_fsrxm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public UFDouble getYinghsou() {
		return yinghsou;
	}
	public void setYinghsou(UFDouble yinghsou) {
		this.yinghsou = yinghsou;
	}
	
}
