package nc.vo.hkjt.pub;

import nc.vo.pub.SuperVO;

public class SmPubFilesystem extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5834759811300689845L;
	
	private String creator;
	private Integer dr;
	private String filedesc;
	private String filelength;
	private String filepath;
	private String filetype;
	private Long hashidx;
	private String isdoc;
	private String isfolder;
	private String lasttime;
	private String modifier;
	private String modifytime;
	private String pk;
	private String pk_doc;
	private String rootpath;
	private Long scantimes;
	private String ts;
	
	@Override
	public String getPKFieldName() {
		return "pk";
	}
	@Override
	public String getPrimaryKey() {
		return this.getPk();
	}
	@Override
	public String getTableName() {
		return "sm_pub_filesystem";
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Integer getDr() {
		return dr;
	}
	public void setDr(Integer dr) {
		this.dr = dr;
	}
	public String getFiledesc() {
		return filedesc;
	}
	public void setFiledesc(String filedesc) {
		this.filedesc = filedesc;
	}
	public String getFilelength() {
		return filelength;
	}
	public void setFilelength(String filelength) {
		this.filelength = filelength;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public Long getHashidx() {
		return hashidx;
	}
	public void setHashidx(Long hashidx) {
		this.hashidx = hashidx;
	}
	public String getIsdoc() {
		return isdoc;
	}
	public void setIsdoc(String isdoc) {
		this.isdoc = isdoc;
	}
	public String getIsfolder() {
		return isfolder;
	}
	public void setIsfolder(String isfolder) {
		this.isfolder = isfolder;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifytime() {
		return modifytime;
	}
	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getPk_doc() {
		return pk_doc;
	}
	public void setPk_doc(String pk_doc) {
		this.pk_doc = pk_doc;
	}
	public String getRootpath() {
		return rootpath;
	}
	public void setRootpath(String rootpath) {
		this.rootpath = rootpath;
	}
	public Long getScantimes() {
		return scantimes;
	}
	public void setScantimes(Long scantimes) {
		this.scantimes = scantimes;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
}
