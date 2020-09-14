package nc.vo.hkjt.store.lvyun.in;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
/**
 * 商品新增
 */
public class SavePosStoreArticle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6597208182681833391L;

	private String hotelGroupCode;
	private String hotelCode;
	private String method;
	private String v;
	private String format;
	private String appKey;
	private PosStoreArticle[] articles;
	
	private String sign;	// 签名
	
	public SavePosStoreArticle(
			  String hotelCode				// 酒店code
			, PosStoreArticle[] articles	// 物料
			) throws JsonGenerationException, JsonMappingException, IOException {
		super();
		this.method = "savePosStoreArticle";	// 函数名
		this.hotelCode = hotelCode;	// 酒店编码：GCBZ
		this.articles = articles;
		this.hotelGroupCode = GcSignUtils.hotelGroupCode;
		this.v = GcSignUtils.V;
		this.format = GcSignUtils.FORMAT;
		this.appKey = GcSignUtils.appKey;
		// 签名
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("hotelCode", this.getHotelCode());
		paramMap.put("hotelGroupCode", this.getHotelGroupCode());
		paramMap.put("method", this.getMethod());
		paramMap.put("v", this.getV());
		paramMap.put("format", this.getFormat());
		paramMap.put("appKey", this.getAppKey());
		ObjectMapper JSON = new ObjectMapper();
		String articlesStr = JSON.writeValueAsString(articles);
		paramMap.put("articles", articlesStr);
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

	public PosStoreArticle[] getArticles() {
		return articles;
	}

	public void setArticles(PosStoreArticle[] articles) {
		this.articles = articles;
	}
	
}
