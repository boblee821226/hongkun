package nc.vo.hkjt.srgk.huiguan.sgshuju;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class SgshujuBillVOMeta extends AbstractBillMeta {
  public SgshujuBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuHVO.class);
    this.addChildren(nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO.class);
  }
}