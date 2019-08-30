package nc.vo.hkjt.huiyuan.kazhangwu;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.kazhangwu.KazhangwuHVO")
public class KazhangwuBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(KazhangwuBillVOMeta.class);
    return billMeta;
  }

  @Override
  public KazhangwuHVO getParentVO() {
    return (KazhangwuHVO) this.getParent();
  }
}