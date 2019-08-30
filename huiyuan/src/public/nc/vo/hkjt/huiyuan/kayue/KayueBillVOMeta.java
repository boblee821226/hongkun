package nc.vo.hkjt.huiyuan.kayue;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class KayueBillVOMeta extends AbstractBillMeta {
  public KayueBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.kayue.KayueHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kayue.KayueBVO.class);
  }
}