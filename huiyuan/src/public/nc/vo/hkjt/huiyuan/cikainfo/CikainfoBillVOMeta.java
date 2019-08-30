package nc.vo.hkjt.huiyuan.cikainfo;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class CikainfoBillVOMeta extends AbstractBillMeta {
  public CikainfoBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.cikainfo.CikainfoHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.cikainfo.CikainfoBVO.class);
  }
}