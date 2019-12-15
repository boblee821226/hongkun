package nc.vo.hkjt.huiyuan.cikayue;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class CikayueBillVOMeta extends AbstractBillMeta {
  public CikayueBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.cikayue.CikayueHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.cikayue.CikayueBVO.class);
  }
}