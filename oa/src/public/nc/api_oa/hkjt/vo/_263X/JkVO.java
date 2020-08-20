package nc.api_oa.hkjt.vo._263X;

import java.io.Serializable;

/**
 * ½è¿îBillVO
 */
public class JkVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3354893476018303383L;

	private JkHeadVO head;
	private JkItemVO[] items;
	
	public JkHeadVO getHead() {
		return head;
	}
	public void setHead(JkHeadVO head) {
		this.head = head;
	}
	public JkItemVO[] getItems() {
		return items;
	}
	public void setItems(JkItemVO[] items) {
		this.items = items;
	}
	
}
