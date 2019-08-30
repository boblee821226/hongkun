package nc.vo.hkjt.zulin.yuebao;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class YuebaoBillVOMeta extends AbstractBillMeta {
  public YuebaoBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.zulin.yuebao.YuebaoHVO.class);
    this.addChildren(nc.vo.hkjt.zulin.yuebao.YuebaoBVO.class);
  }
}