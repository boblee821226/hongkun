package nc.api_oa.hkjt.vo._263X;

import java.io.Serializable;

/**
 * ½è¿îBillVO
 */
public class JKVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3354893476018303383L;

	private JKHeaderVO head;
	private JKBusItemVO[] items;
	
	public JKHeaderVO getHead() {
		return head;
	}
	public void setHead(JKHeaderVO head) {
		this.head = head;
	}
	public JKBusItemVO[] getItems() {
		return items;
	}
	public void setItems(JKBusItemVO[] items) {
		this.items = items;
	}
	
}
