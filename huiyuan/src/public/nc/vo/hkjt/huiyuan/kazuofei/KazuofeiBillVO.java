package nc.vo.hkjt.huiyuan.kazuofei;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.kazuofei.KazuofeiHVO")
public class KazuofeiBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(KazuofeiBillVOMeta.class);
    return billMeta;
  }

  @Override
  public KazuofeiHVO getParentVO() {
    return (KazuofeiHVO) this.getParent();
  }
}