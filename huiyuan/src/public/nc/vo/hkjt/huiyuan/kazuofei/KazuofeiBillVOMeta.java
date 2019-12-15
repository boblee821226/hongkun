package nc.vo.hkjt.huiyuan.kazuofei;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class KazuofeiBillVOMeta extends AbstractBillMeta {
  public KazuofeiBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.kazuofei.KazuofeiHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kazuofei.KazuofeiBVO.class);
  }
}