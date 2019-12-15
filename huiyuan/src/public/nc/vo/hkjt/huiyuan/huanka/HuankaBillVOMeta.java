package nc.vo.hkjt.huiyuan.huanka;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class HuankaBillVOMeta extends AbstractBillMeta {
  public HuankaBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.huanka.HuankaHVO.class);
  }
}