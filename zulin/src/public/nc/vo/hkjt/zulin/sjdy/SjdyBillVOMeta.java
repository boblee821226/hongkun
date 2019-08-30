package nc.vo.hkjt.zulin.sjdy;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class SjdyBillVOMeta extends AbstractBillMeta {
  public SjdyBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.zulin.sjdy.SjdyHVO.class);
    this.addChildren(nc.vo.hkjt.zulin.sjdy.SjdyBVO.class);
  }
}