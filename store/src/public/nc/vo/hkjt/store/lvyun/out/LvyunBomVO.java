package nc.vo.hkjt.store.lvyun.out;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class LvyunBomVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2439161093284489831L;

	private String pk_inv;
	private UFDouble num;
	private UFDouble price;
	
	public LvyunBomVO(String pk_inv,UFDouble num,UFDouble price) {
		this.pk_inv = pk_inv;
		this.num = num;
		this.price = price;
	}
	
	public String getPk_inv() {
		return pk_inv;
	}
	public void setPk_inv(String pk_inv) {
		this.pk_inv = pk_inv;
	}
	public UFDouble getNum() {
		return num;
	}
	public void setNum(UFDouble num) {
		this.num = num;
	}
	public UFDouble getPrice() {
		return price;
	}
	public void setPrice(UFDouble price) {
		this.price = price;
	}
	
}
