package nc.vo.hkjt.huiyuan.cikatongji;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class CikatongjiBillVOMeta extends AbstractBillMeta {
  public CikatongjiBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.cikatongji.CikatongjiHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.cikatongji.CikatongjiBVO.class);
  }
}