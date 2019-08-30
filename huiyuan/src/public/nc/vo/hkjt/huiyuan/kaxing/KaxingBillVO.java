package nc.vo.hkjt.huiyuan.kaxing;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.kaxing.KaxingHVO")
public class KaxingBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(KaxingBillVOMeta.class);
    return billMeta;
  }

  @Override
  public KaxingHVO getParentVO() {
    return (KaxingHVO) this.getParent();
  }
}