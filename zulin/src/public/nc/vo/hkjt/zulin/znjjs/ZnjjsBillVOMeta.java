package nc.vo.hkjt.zulin.znjjs;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class ZnjjsBillVOMeta extends AbstractBillMeta {
  public ZnjjsBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.zulin.znjjs.ZnjjsHVO.class);
    this.addChildren(nc.vo.hkjt.zulin.znjjs.ZnjjsBVO.class);
  }
}