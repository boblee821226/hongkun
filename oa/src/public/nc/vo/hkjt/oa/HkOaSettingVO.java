package nc.vo.hkjt.oa;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

/**
 * OA…Ë÷√vo
 */
public class HkOaSettingVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 366417063239237576L;

	private String pk_hk_oa_setting;
	private String pk_billtypecode;
	private String pk_billtypeid;
	private String parentbilltype;
	private String workflowid;
	private String url_data;
	private String url_file;
	private UFDateTime ts;
	private Integer dr;
	
	@Override
	public String getPrimaryKey() {
		return "pk_hk_oa_setting";
	}
	@Override
	public String getTableName() {
		return "hk_oa_setting";
	}
	
	public String getParentbilltype() {
		return parentbilltype;
	}
	public void setParentbilltype(String parentbilltype) {
		this.parentbilltype = parentbilltype;
	}
	public String getPk_hk_oa_setting() {
		return pk_hk_oa_setting;
	}
	public void setPk_hk_oa_setting(String pk_hk_oa_setting) {
		this.pk_hk_oa_setting = pk_hk_oa_setting;
	}
	public String getPk_billtypecode() {
		return pk_billtypecode;
	}
	public void setPk_billtypecode(String pk_billtypecode) {
		this.pk_billtypecode = pk_billtypecode;
	}
	public String getPk_billtypeid() {
		return pk_billtypeid;
	}
	public void setPk_billtypeid(String pk_billtypeid) {
		this.pk_billtypeid = pk_billtypeid;
	}
	public String getWorkflowid() {
		return workflowid;
	}
	public void setWorkflowid(String workflowid) {
		this.workflowid = workflowid;
	}
	public String getUrl_data() {
		return url_data;
	}
	public void setUrl_data(String url_data) {
		this.url_data = url_data;
	}
	public String getUrl_file() {
		return url_file;
	}
	public void setUrl_file(String url_file) {
		this.url_file = url_file;
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
