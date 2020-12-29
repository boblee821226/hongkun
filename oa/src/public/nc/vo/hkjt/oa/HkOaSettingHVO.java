package nc.vo.hkjt.oa;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;

public class HkOaSettingHVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 258950750581981372L;

	private String pk_hk_oa_setting;
	private String pk_hk_oa_setting_h;
	private String field_nc;
	private String field_oa;
	private String formula;
	private UFBoolean is_title;
	private UFBoolean is_creator;
	private UFBoolean is_id;
	private UFBoolean is_tail;
	private UFDateTime ts;
	private Integer dr;
	
	@Override
	public String getPrimaryKey() {
		return "pk_hk_oa_setting_h";
	}
	@Override
	public String getTableName() {
		return "hk_oa_setting_h";
	}
	@Override
	public String getParentPKFieldName() {
		return "pk_hk_oa_setting_h";
	}
	
	public String getPk_hk_oa_setting() {
		return pk_hk_oa_setting;
	}
	public void setPk_hk_oa_setting(String pk_hk_oa_setting) {
		this.pk_hk_oa_setting = pk_hk_oa_setting;
	}
	public String getPk_hk_oa_setting_h() {
		return pk_hk_oa_setting_h;
	}
	public void setPk_hk_oa_setting_h(String pk_hk_oa_setting_h) {
		this.pk_hk_oa_setting_h = pk_hk_oa_setting_h;
	}
	public String getField_nc() {
		return field_nc;
	}
	public void setField_nc(String field_nc) {
		this.field_nc = field_nc;
	}
	public String getField_oa() {
		return field_oa;
	}
	public void setField_oa(String field_oa) {
		this.field_oa = field_oa;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public UFBoolean getIs_title() {
		return is_title;
	}
	public void setIs_title(UFBoolean is_title) {
		this.is_title = is_title;
	}
	public UFBoolean getIs_creator() {
		return is_creator;
	}
	public void setIs_creator(UFBoolean is_creator) {
		this.is_creator = is_creator;
	}
	public UFBoolean getIs_id() {
		return is_id;
	}
	public void setIs_id(UFBoolean is_id) {
		this.is_id = is_id;
	}
	public UFBoolean getIs_tail() {
		return is_tail;
	}
	public void setIs_tail(UFBoolean is_tail) {
		this.is_tail = is_tail;
	}
	public UFDateTime getTs() {
		return ts;
	}
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}
	public Integer getDr() {
		return dr;
	}
	public void setDr(Integer dr) {
		this.dr = dr;
	}
	
}
