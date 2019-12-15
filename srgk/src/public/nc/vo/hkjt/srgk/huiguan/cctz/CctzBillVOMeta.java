package nc.vo.hkjt.srgk.huiguan.cctz;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class CctzBillVOMeta extends AbstractBillMeta {
  public CctzBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.srgk.huiguan.cctz.CctzHVO.class);
    this.addChildren(nc.vo.hkjt.srgk.huiguan.cctz.CctzBVO.class);
  }
}