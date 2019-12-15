package nc.vo.hkjt.huiyuan.kaipiaoinfo;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class KaipiaoinfoBillVOMeta extends AbstractBillMeta {
  public KaipiaoinfoBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kaipiaoinfo.KaipiaoinfoBVO.class);
  }
}