package nc.vo.hkjt.arap.bill;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class BillBillVOMeta extends AbstractBillMeta {
  public BillBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.arap.bill.BillHVO.class);
    this.addChildren(nc.vo.hkjt.arap.bill.BillBVO.class);
    this.addChildren(nc.vo.hkjt.arap.bill.BillEVO.class);
    this.addChildren(nc.vo.hkjt.arap.bill.BillCVO.class);
    this.addChildren(nc.vo.hkjt.arap.bill.BillDVO.class);
  }
}