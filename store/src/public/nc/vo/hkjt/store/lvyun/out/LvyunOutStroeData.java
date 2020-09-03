package nc.vo.hkjt.store.lvyun.out;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class LvyunOutStroeData extends SuperVO {

	/**
	 *  pccode,pccode_des,store_code,store_name
		,plu_code,plu_name,number
		,art_code,art_name,art_number
	 */
	private static final long serialVersionUID = -7827797289060474726L;
	
	private String pccode;
	private String pccode_des;
	private String store_code;
	private String store_name;
	private String plu_code;
	private String plu_name;
	private UFDouble number;
	private String art_code;
	private String art_name;
	private UFDouble art_number;
	
	public String getPccode() {
		return pccode;
	}
	public void setPccode(String pccode) {
		this.pccode = pccode;
	}
	public String getPccode_des() {
		return pccode_des;
	}
	public void setPccode_des(String pccode_des) {
		this.pccode_des = pccode_des;
	}
	public String getStore_code() {
		return store_code;
	}
	public void setStore_code(String store_code) {
		this.store_code = store_code;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getPlu_code() {
		return plu_code;
	}
	public void setPlu_code(String plu_code) {
		this.plu_code = plu_code;
	}
	public String getPlu_name() {
		return plu_name;
	}
	public void setPlu_name(String plu_name) {
		this.plu_name = plu_name;
	}
	public UFDouble getNumber() {
		return number;
	}
	public void setNumber(UFDouble number) {
		this.number = number;
	}
	public String getArt_code() {
		return art_code;
	}
	public void setArt_code(String art_code) {
		this.art_code = art_code;
	}
	public String getArt_name() {
		return art_name;
	}
	public void setArt_name(String art_name) {
		this.art_name = art_name;
	}
	public UFDouble getArt_number() {
		return art_number;
	}
	public void setArt_number(UFDouble art_number) {
		this.art_number = art_number;
	}
	
}
