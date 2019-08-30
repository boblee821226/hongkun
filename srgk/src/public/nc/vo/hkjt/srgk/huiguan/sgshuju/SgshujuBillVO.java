package nc.vo.hkjt.srgk.huiguan.sgshuju;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuHVO")
public class SgshujuBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(SgshujuBillVOMeta.class);
    return billMeta;
  }

  @Override
  public SgshujuHVO getParentVO() {
    return (SgshujuHVO) this.getParent();
  }
}