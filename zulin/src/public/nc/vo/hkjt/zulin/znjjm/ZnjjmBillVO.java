package nc.vo.hkjt.zulin.znjjm;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.zulin.znjjm.ZnjjmHVO")
public class ZnjjmBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(ZnjjmBillVOMeta.class);
    return billMeta;
  }

  @Override
  public ZnjjmHVO getParentVO() {
    return (ZnjjmHVO) this.getParent();
  }
}