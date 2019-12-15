package nc.vo.hkjt.fapiao_sk.bill;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class BillSkFpBillVOMeta extends AbstractBillMeta {
  public BillSkFpBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.fapiao_sk.bill.BillSkFpHVO.class);
    this.addChildren(nc.vo.hkjt.fapiao_sk.bill.BillSkFpBVO.class);
  }
}