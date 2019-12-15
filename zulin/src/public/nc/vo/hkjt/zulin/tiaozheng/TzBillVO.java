package nc.vo.hkjt.zulin.tiaozheng;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.zulin.tiaozheng.TzHVO")
public class TzBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(TzBillVOMeta.class);
    return billMeta;
  }

  @Override
  public TzHVO getParentVO() {
    return (TzHVO) this.getParent();
  }
}