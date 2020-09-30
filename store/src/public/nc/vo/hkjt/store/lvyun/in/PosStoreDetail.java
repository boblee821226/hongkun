package nc.vo.hkjt.store.lvyun.in;

import java.io.Serializable;

public class PosStoreDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1582800491543000555L;

	private String articleCode;	// 商品编码
	private String number;		// 入库数量
	private String price;		// 单价
	
	public String getArticleCode() {
		return articleCode;
	}
	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
