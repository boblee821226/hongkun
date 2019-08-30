package nc.vo.hkjt.zulin.tiaozheng;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class TzBillVOMeta extends AbstractBillMeta {
  public TzBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.zulin.tiaozheng.TzHVO.class);
    this.addChildren(nc.vo.hkjt.zulin.tiaozheng.TzBVO.class);
  }
}