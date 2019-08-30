package nc.vo.hkjt.srgk.huiguan.yyribao;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class YyribaoBillVOMeta extends AbstractBillMeta {
  public YyribaoBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoHVO.class);
    this.addChildren(nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoBVO.class);
  }
}