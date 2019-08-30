package nc.vo.hkjt.srgk.jiudian.ruzhangmingxi;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class RzmxBillVOMeta extends AbstractBillMeta {
  public RzmxBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxHVO.class);
    this.addChildren(nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxBVO.class);
  }
}