package nc.vo.hkjt.zulin.sjdy;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.zulin.sjdy.SjdyHVO")
public class SjdyBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(SjdyBillVOMeta.class);
    return billMeta;
  }

  @Override
  public SjdyHVO getParentVO() {
    return (SjdyHVO) this.getParent();
  }
}