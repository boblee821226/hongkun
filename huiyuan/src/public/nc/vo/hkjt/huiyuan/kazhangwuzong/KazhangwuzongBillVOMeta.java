package nc.vo.hkjt.huiyuan.kazhangwuzong;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class KazhangwuzongBillVOMeta extends AbstractBillMeta {
  public KazhangwuzongBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongBVO.class);
  }
}