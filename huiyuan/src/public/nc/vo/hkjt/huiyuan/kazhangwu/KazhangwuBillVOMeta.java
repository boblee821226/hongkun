package nc.vo.hkjt.huiyuan.kazhangwu;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class KazhangwuBillVOMeta extends AbstractBillMeta {
  public KazhangwuBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuBVO.class);
  }
}