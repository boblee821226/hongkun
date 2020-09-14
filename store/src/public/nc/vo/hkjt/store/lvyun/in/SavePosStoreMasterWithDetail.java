package nc.vo.hkjt.store.lvyun.in;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
/**
 * 新增入库
 */
public class SavePosStoreMasterWithDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 525064302882568565L;

	private String hotelGroupCode;
	private String hotelCode;
	private String method;
	private String v;
	private String format;
	private String appKey;
	private PosStoreDetail[] details;
	private String accnt;	// 订单号【Y】
	private String date;	// 日期yyyy-MM-dd hh:mm:ss【Y】
	private String invoice;	// 发票号
	private String remark;	// 备注
	private String targetCode;	// 吧台号【Y】
	private String userCode;	// 操作人代码
	
	private String sign;	// 签名
	
	public SavePosStoreMasterWithDetail(
			  String hotelCode
			, String accnt
			, String date
			, String targetCode
			, String userCode
			, String invoice
			, String remark
			, PosStoreDetail[] details
			) throws JsonGenerationException, JsonMappingException, IOException {
		super();
		this.method = "savePosStoreMasterWithDetail";	// 函数名
		this.hotelCode = hotelCode;	// 酒店编码：GCBZ
		this.hotelGroupCode = GcSignUtils.hotelGroupCode;
		this.v = GcSignUtils.V;
		this.format = GcSignUtils.FORMAT;
		this.appKey = GcSignUtils.appKey;
		this.accnt = accnt;
		this.date = date;
		this.details = details;
		this.invoice = invoice;
		this.remark = remark;
		this.targetCode = targetCode;
		this.userCode = userCode;
		// 签名
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("hotelCode", this.getHotelCode());
		paramMap.put("hotelGroupCode", this.getHotelGroupCode());
		paramMap.put("method", this.getMethod());
		paramMap.put("v", this.getV());
		paramMap.put("format", this.getFormat());
		paramMap.put("appKey", this.getAppKey());
		ObjectMapper JSON = new ObjectMapper();
		String detailsStr = JSON.writeValueAsString(details);
		paramMap.put("details", detailsStr);
		paramMap.put("accnt", this.getAccnt());
		paramMap.put("date", this.getDate());
		paramMap.put("invoice", this.getInvoice());
		paramMap.put("remark", this.getRemark());
		paramMap.put("targetCode", this.getTargetCode());
		paramMap.put("userCode", this.getUserCode());
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
	public PosStoreDetail[] getDetails() {
		return details;
	}
	public void setDetails(PosStoreDetail[] details) {
		this.details = details;
	}
	public String getAccnt() {
		return accnt;
	}
	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTargetCode() {
		return targetCode;
	}
	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
