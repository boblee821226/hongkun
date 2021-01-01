package nc.vo.hkjt.arap.account;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.arap.account.AccountHVO")
public class AccountBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AccountBillVOMeta.class);
    return billMeta;
  }

  @Override
  public AccountHVO getParentVO() {
    return (AccountHVO) this.getParent();
  }
}