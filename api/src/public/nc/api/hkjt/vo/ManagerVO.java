package nc.api.hkjt.vo;

import java.io.Serializable;

public class ManagerVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5720448235340672390L;

	private String orgCode;
	private String psnId;
	private String psnCode;
	private String psnName;
	private String sex;
	private String birthdate;
	private String idNumber;
	private String mobile;
	private String email;
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getPsnId() {
		return psnId;
	}
	public void setPsnId(String psnId) {
		this.psnId = psnId;
	}
	public String getPsnCode() {
		return psnCode;
	}
	public void setPsnCode(String psnCode) {
		this.psnCode = psnCode;
	}
	public String getPsnName() {
		return psnName;
	}
	public void setPsnName(String psnName) {
		this.psnName = psnName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
