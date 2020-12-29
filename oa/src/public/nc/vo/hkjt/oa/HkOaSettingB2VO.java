package nc.vo.hkjt.oa;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

public class HkOaSettingB2VO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6707433277553392918L;

	private String pk_hk_oa_setting;
	private String pk_hk_oa_setting_b2;
	private String table_code;
	private String field_nc;
	private String field_oa;
	private String formula;
	private UFDateTime ts;
	private Integer dr;
	
	@Override
	public String getPrimaryKey() {
		return "pk_hk_oa_setting_b2";
	}
	@Override
	public String getTableName() {
		return "hk_oa_setting_b2";
	}
	@Override
	public String getParentPKFieldName() {
		return "pk_hk_oa_setting_b2";
	}
	
	public String getPk_hk_oa_setting() {
		return pk_hk_oa_setting;
	}
	public void setPk_hk_oa_setting(String pk_hk_oa_setting) {
		this.pk_hk_oa_setting = pk_hk_oa_setting;
	}
	public String getPk_hk_oa_setting_b2() {
		return pk_hk_oa_setting_b2;
	}
	public void setPk_hk_oa_setting_b2(String pk_hk_oa_setting_b2) {
		this.pk_hk_oa_setting_b2 = pk_hk_oa_setting_b2;
	}
	public String getTable_code() {
		return table_code;
	}
	public void setTable_code(String table_code) {
		this.table_code = table_code;
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
