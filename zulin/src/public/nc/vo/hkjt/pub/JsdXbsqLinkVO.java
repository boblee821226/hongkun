package nc.vo.hkjt.pub;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;

public class JsdXbsqLinkVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2557641582562314756L;

	private String link_key;
	private String pk_jsd;
	private String pk_jsd_b;
	private Integer dr;
	private UFDateTime ts;
	
	public JsdXbsqLinkVO() {
		super();
		this.dr = 0;
		this.ts = new UFDateTime();
	}
	
	public JsdXbsqLinkVO(String link_key, String pk_jsd, String pk_jsd_b) {
		super();
		this.dr = 0;
		this.ts = new UFDateTime();
		this.link_key = link_key;
		this.pk_jsd = pk_jsd;
		this.pk_jsd_b = pk_jsd_b;
	}
	
	public String getLink_key() {
		return link_key;
	}
	public void setLink_key(String link_key) {
		this.link_key = link_key;
	}
	public String getPk_jsd() {
		return pk_jsd;
	}
	public void setPk_jsd(String pk_jsd) {
		this.pk_jsd = pk_jsd;
	}
	public String getPk_jsd_b() {
		return pk_jsd_b;
	}
	public void setPk_jsd_b(String pk_jsd_b) {
		this.pk_jsd_b = pk_jsd_b;
	}
	public Integer getDr() {
		return dr;
	}
	public void setDr(Integer dr) {
		this.dr = dr;
	}
	public UFDateTime getTs() {
		return ts;
	}
	public void setTs(UFDateTime ts) {
		this.ts = ts;
	}
	@Override
	public String getTableName() {
		return "hk_jsd_xbsq_link";
	}
	
}
