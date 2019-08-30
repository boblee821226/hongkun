package nc.vo.hkjt.zulin.sdflr;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.zulin.sdflr.SdflrHVO")
public class SdflrBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(SdflrBillVOMeta.class);
    return billMeta;
  }

  @Override
  public SdflrHVO getParentVO() {
    return (SdflrHVO) this.getParent();
  }
}