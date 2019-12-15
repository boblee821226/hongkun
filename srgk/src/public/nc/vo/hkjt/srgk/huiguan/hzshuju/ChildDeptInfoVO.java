package nc.vo.hkjt.srgk.huiguan.hzshuju;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class ChildDeptInfoVO extends SuperVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5183341106063119123L;
	private String pk_dept;
	private String hzdate;
	private String pk_srxm;
	private UFDouble qrsr;
	public String getPk_dept() {
		return pk_dept;
	}
	public void setPk_dept(String pk_dept) {
		this.pk_dept = pk_dept;
	}
	public String getHzdate() {
		return hzdate;
	}
	public void setHzdate(String hzdate) {
		this.hzdate = hzdate;
	}
	public String getPk_srxm() {
		return pk_srxm;
	}
	public void setPk_srxm(String pk_srxm) {
		this.pk_srxm = pk_srxm;
	}
	public UFDouble getQrsr() {
		return qrsr;
	}
	public void setQrsr(UFDouble qrsr) {
		this.qrsr = qrsr;
	}
	
}
