package nc.api_oa.hkjt.vo._264X;

import java.io.Serializable;

/**
 * 报销BillVO
 * 日常费用审批单
 */
public class BxVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3354893476018303383L;

	private BxHeadVO head;
	private BxItemVO[] items;
	
	public BxHeadVO getHead() {
		return head;
	}
	public void setHead(BxHeadVO head) {
		this.head = head;
	}
	public BxItemVO[] getItems() {
		return items;
	}
	public void setItems(BxItemVO[] items) {
		this.items = items;
	}
	
}
