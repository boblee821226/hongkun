package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.kadangan.KadanganHVO")
public class KadanganBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(KadanganBillVOMeta.class);
    return billMeta;
  }

  @Override
  public KadanganHVO getParentVO() {
    return (KadanganHVO) this.getParent();
  }
}