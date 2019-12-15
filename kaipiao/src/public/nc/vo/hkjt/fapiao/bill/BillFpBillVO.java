package nc.vo.hkjt.fapiao.bill;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.fapiao.bill.BillFpHVO")
public class BillFpBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(BillFpBillVOMeta.class);
    return billMeta;
  }

  @Override
  public BillFpHVO getParentVO() {
    return (BillFpHVO) this.getParent();
  }
}