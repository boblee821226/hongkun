package nc.vo.hkjt.huiyuan.kaxing;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class KaxingBillVOMeta extends AbstractBillMeta {
  public KaxingBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.kaxing.KaxingHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganHVO2.class);
  }
}