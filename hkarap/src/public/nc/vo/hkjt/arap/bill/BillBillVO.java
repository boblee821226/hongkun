package nc.vo.hkjt.arap.bill;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.arap.bill.BillHVO")
public class BillBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(BillBillVOMeta.class);
    return billMeta;
  }

  @Override
  public BillHVO getParentVO() {
    return (BillHVO) this.getParent();
  }
}