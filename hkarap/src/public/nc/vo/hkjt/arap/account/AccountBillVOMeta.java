package nc.vo.hkjt.arap.account;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AccountBillVOMeta extends AbstractBillMeta {
  public AccountBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.arap.account.AccountHVO.class);
    this.addChildren(nc.vo.hkjt.arap.account.AccountBVO.class);
    this.addChildren(nc.vo.hkjt.arap.account.AccountCVO.class);
    this.addChildren(nc.vo.hkjt.arap.account.AccountEVO.class);
    this.addChildren(nc.vo.hkjt.arap.account.AccountDVO.class);
  }
}