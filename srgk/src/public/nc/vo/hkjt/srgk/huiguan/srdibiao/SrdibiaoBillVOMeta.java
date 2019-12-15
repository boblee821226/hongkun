package nc.vo.hkjt.srgk.huiguan.srdibiao;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class SrdibiaoBillVOMeta extends AbstractBillMeta {
  public SrdibiaoBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoHVO.class);
    this.addChildren(nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoBVO.class);
  }
}