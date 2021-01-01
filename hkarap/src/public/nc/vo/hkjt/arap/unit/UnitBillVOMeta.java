package nc.vo.hkjt.arap.unit;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class UnitBillVOMeta extends AbstractBillMeta {
  public UnitBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.arap.unit.UnitHVO.class);
    this.addChildren(nc.vo.hkjt.arap.unit.UnitBVO.class);
  }
}