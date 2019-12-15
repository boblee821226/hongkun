package nc.vo.hkjt.huiyuan.huanka;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.huanka.HuankaHVO")
public class HuankaBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(HuankaBillVOMeta.class);
    return billMeta;
  }

  @Override
  public HuankaHVO getParentVO() {
    return (HuankaHVO) this.getParent();
  }
}