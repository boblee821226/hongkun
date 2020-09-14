package nc.vo.hkjt.store.lvyun.in;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class PosStoreArticle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6180529879894080888L;

	private String code;	// ���ϱ���
	private String name;	// ��������
	private String helpCode;// ������
	private String standent;// ���
	private String sseg;	// С��code
	private String cseg;	// ����code
	private String price;	// �۸�
	private String unit;	// ��λ
	private String ref;		// ��ע
	private String storage;	// ��Ŀ����
	@JsonIgnore
	private String pk;		// NC����pk
	@JsonIgnore
	private String lyCode;	// ����code
	
	public PosStoreArticle() {
		super();
	}
	
	public PosStoreArticle (
			String code,	// ���ϱ���
			String name,	// ��������
			String helpCode,// ������
			String standent,// ���
			String sseg,	// С��code
			String cseg,	// ����code
			String price,	// �۸�
			String unit,	// ��λ
			String ref,		// ��ע
			String storage	// ��Ŀ����
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
