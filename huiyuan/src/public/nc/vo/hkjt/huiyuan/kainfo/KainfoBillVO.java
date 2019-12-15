package nc.vo.hkjt.huiyuan.kainfo;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.kainfo.KainfoHVO")
public class KainfoBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(KainfoBillVOMeta.class);
    return billMeta;
  }

  @Override
  public KainfoHVO getParentVO() {
    return (KainfoHVO) this.getParent();
  }
}