package nc.vo.hkjt.store.lvyun.in;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class PosStoreArticle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6180529879894080888L;

	private String code;	// 物料编码
	private String name;	// 物料名称
	private String helpCode;// 助记码
	private String standent;// 规格
	private String sseg;	// 小类code
	private String cseg;	// 大类code
	private String price;	// 价格
	private String unit;	// 单位
	private String ref;		// 备注
	private String storage;	// 科目代码
	@JsonIgnore
	private String pk;		// NC物料pk
	@JsonIgnore
	private String lyCode;	// 绿云code
	
	public PosStoreArticle() {
		super();
	}
	
	public PosStoreArticle (
			String code,	// 物料编码
			String name,	// 物料名称
			String helpCode,// 助记码
			String standent,// 规格
			String sseg,	// 小类code
			String cseg,	// 大类code
			String price,	// 价格
			String unit,	// 单位
			String ref,		// 备注
			String storage	// 科目代码
			) {
		super();
		this.code = code;
		this.name = name;
		this.helpCode = helpCode;
		this.standent = standent;
		this.sseg = sseg;
		this.cseg = cseg;
		this.price = price;
		this.unit = unit;
		this.ref = ref;
		this.storage = storage;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHelpCode() {
		return helpCode;
	}
	public void setHelpCode(String helpCode) {
		this.helpCode = helpCode;
	}
	public String getStandent() {
		return standent;
	}
	public void setStandent(String standent) {
		this.standent = standent;
	}
	public String getSseg() {
		return sseg;
	}
	public void setSseg(String sseg) {
		this.sseg = sseg;
	}
	public String getCseg() {
		return cseg;
	}
	public void setCseg(String cseg) {
		this.cseg = cseg;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getLyCode() {
		return lyCode;
	}
	public void setLyCode(String lyCode) {
		this.lyCode = lyCode;
	}
	
}
