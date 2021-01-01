package nc.vo.hkjt.arap.unit;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.arap.unit.UnitHVO")
public class UnitBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(UnitBillVOMeta.class);
    return billMeta;
  }

  @Override
  public UnitHVO getParentVO() {
    return (UnitHVO) this.getParent();
  }
}