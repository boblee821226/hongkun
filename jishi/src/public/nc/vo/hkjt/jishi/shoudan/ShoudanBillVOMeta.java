package nc.vo.hkjt.jishi.shoudan;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class ShoudanBillVOMeta extends AbstractBillMeta{
	
	public ShoudanBillVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.hkjt.jishi.shoudan.ShoudanHVO.class);
		this.addChildren(nc.vo.hkjt.jishi.shoudan.ShoudanBVO.class);
	}
}