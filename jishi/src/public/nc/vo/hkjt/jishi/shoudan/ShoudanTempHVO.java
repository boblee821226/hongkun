package nc.vo.hkjt.jishi.shoudan;

import nc.vo.pub.SuperVO;

/**
 * 收单 主表 临时表
 */
 
public class ShoudanTempHVO extends SuperVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2084425877231101085L;
	
	public String jishicode;
	public String jishiname;
	public String dbilldate;
	public String pk_org;
	public String getJishicode() {
		return jishicode;
	}
	public void setJishicode(String jishicode) {
		this.jishicode = jishicode;
	}
	public String getJishiname() {
		return jishiname;
	}
	public void setJishiname(String jishiname) {
		this.jishiname = jishiname;
	}
	public String getDbilldate() {
		return dbilldate;
	}
	public void setDbilldate(String dbilldate) {
		this.dbilldate = dbilldate;
	}
	public String getPk_org() {
		return pk_org;
	}
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}
	
	@Override
	public String getTableName() {
		return "HK_JISHI_SHOUDAN_TEMP";
	}
	@Override
	public String getPrimaryKey() {
		return jishicode;
	}
	
}
    