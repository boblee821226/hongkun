package nc.vo.hkjt.huiyuan.kazhangwuzong;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.kazhangwuzong.KazhangwuzongHVO")
public class KazhangwuzongBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(KazhangwuzongBillVOMeta.class);
    return billMeta;
  }

  @Override
  public KazhangwuzongHVO getParentVO() {
    return (KazhangwuzongHVO) this.getParent();
  }
}