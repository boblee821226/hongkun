package nc.vo.hkjt.zulin.znjjm;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class ZnjjmBillVOMeta extends AbstractBillMeta {
  public ZnjjmBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.zulin.znjjm.ZnjjmHVO.class);
    this.addChildren(nc.vo.hkjt.zulin.znjjm.ZnjjmBVO.class);
  }
}