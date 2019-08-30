package nc.vo.hkjt.zulin.sdflr;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class SdflrBillVOMeta extends AbstractBillMeta {
  public SdflrBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.zulin.sdflr.SdflrHVO.class);
    this.addChildren(nc.vo.hkjt.zulin.sdflr.SdflrBVO.class);
  }
}