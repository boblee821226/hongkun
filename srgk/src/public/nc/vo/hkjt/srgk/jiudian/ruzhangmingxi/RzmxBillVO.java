package nc.vo.hkjt.srgk.jiudian.ruzhangmingxi;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.srgk.jiudian.ruzhangmingxi.RzmxHVO")
public class RzmxBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(RzmxBillVOMeta.class);
    return billMeta;
  }

  @Override
  public RzmxHVO getParentVO() {
    return (RzmxHVO) this.getParent();
  }
}