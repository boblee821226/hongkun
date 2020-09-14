package nc.vo.hkjt.store.lvyun.in;

import java.io.Serializable;
import java.util.HashMap;
/**
 * É¾³ýÈë¿â
 */
public class DeletePosStoreMasterWithDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1943210543291437042L;

	private String hotelGroupCode;
	private String hotelCode;
	private String method;
	private String v;
	private String format;
	private String appKey;
	private String accnt;

	private String sign;
	
	public DeletePosStoreMasterWithDetail(
			  String hotelCode	// ¾Æµêcode
			, String accnt		// ¶©µ¥ºÅ
			) {
		this.method = "deletePosStoreMasterWithDetail";
		this.hotelCode = hotelCode;	// ¾Æµê±àÂë£ºGCBZ
		this.hotelGroupCode = GcSignUtils.hotelGroupCode;
		this.v = GcSignUtils.V;
		this.format = GcSignUtils.FORMAT;
		this.appKey = GcSignUtils.appKey;
		this.accnt = accnt;
		// Éú³ÉÇ©Ãû
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("hotelCode", this.getHotelCode());
		paramMap.put("hotelGroupCode", this.getHotelGroupCode());
		paramMap.put("method", this.getMethod());
		paramMap.put("v", this.getV());
		paramMap.put("format", this.getFormat());
		paramMap.put("appKey", this.getAppKey());
		paramMap.put("accnt", this.getAccnt());
		this.sign = GcSignUtils.sign(paramMap);
	}
	
	public String getHotelGroupCode() {
		return hotelGroupCode;
	}
	public void setHotelGroupCode(String hotelGroupCode) {
		this.hotelGroupCode = hotelGroupCode;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getAccnt() {
		return accnt;
	}
	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
