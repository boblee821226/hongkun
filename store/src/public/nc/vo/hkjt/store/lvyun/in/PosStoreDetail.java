package nc.vo.hkjt.store.lvyun.in;

import java.io.Serializable;

public class PosStoreDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1582800491543000555L;

	private String articleCode;	// ��Ʒ����
	private String number;		// �������
	
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
	
}
