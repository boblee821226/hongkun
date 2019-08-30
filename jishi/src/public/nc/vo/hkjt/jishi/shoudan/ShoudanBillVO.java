package nc.vo.hkjt.jishi.shoudan;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.jishi.shoudan.ShoudanHVO")

public class ShoudanBillVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(ShoudanBillVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public ShoudanHVO getParentVO(){
	  	return (ShoudanHVO)this.getParent();
	  }
	  
}