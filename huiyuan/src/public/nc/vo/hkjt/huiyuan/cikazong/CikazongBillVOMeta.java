package nc.vo.hkjt.huiyuan.cikazong;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class CikazongBillVOMeta extends AbstractBillMeta {
  public CikazongBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.cikazong.CikazongHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.cikazong.CikazongBVO.class);
  }
}