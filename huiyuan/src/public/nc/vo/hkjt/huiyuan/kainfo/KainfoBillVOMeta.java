package nc.vo.hkjt.huiyuan.kainfo;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class KainfoBillVOMeta extends AbstractBillMeta {
  public KainfoBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.kainfo.KainfoHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kainfo.KainfoBVO.class);
  }
}