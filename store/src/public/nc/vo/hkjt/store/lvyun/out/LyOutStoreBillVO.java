package nc.vo.hkjt.store.lvyun.out;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.store.lvyun.out.LyOutStoreHVO")
public class LyOutStoreBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(LyOutStoreBillVOMeta.class);
    return billMeta;
  }

  @Override
  public LyOutStoreHVO getParentVO() {
    return (LyOutStoreHVO) this.getParent();
  }
}