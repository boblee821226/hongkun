package nc.vo.hkjt.fapiao.bill;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class BillFpBillVOMeta extends AbstractBillMeta {
  public BillFpBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.fapiao.bill.BillFpHVO.class);
    this.addChildren(nc.vo.hkjt.fapiao.bill.BillFpBVO.class);
  }
}