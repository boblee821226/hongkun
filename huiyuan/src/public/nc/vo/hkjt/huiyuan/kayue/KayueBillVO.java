package nc.vo.hkjt.huiyuan.kayue;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.kayue.KayueHVO")
public class KayueBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(KayueBillVOMeta.class);
    return billMeta;
  }

  @Override
  public KayueHVO getParentVO() {
    return (KayueHVO) this.getParent();
  }
}