package nc.vo.hkjt.fapiao_sk.bill;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.fapiao_sk.bill.BillSkFpHVO")
public class BillSkFpBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(BillSkFpBillVOMeta.class);
    return billMeta;
  }

  @Override
  public BillSkFpHVO getParentVO() {
    return (BillSkFpHVO) this.getParent();
  }
}