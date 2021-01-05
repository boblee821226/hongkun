package nc.vo.hkjt.arap.operate;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.arap.operate.OperateHeadVO")
public class OperateBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(OperateBillVOMeta.class);
    return billMeta;
  }

  @Override
  public OperateHeadVO getParentVO() {
    return (OperateHeadVO) this.getParent();
  }
}