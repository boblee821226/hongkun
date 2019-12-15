package nc.vo.hkjt.huiyuan.kaipiaoquery;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class KaipiaoqueryBillVOMeta extends AbstractBillMeta {
  public KaipiaoqueryBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryBVO.class);
  }
}