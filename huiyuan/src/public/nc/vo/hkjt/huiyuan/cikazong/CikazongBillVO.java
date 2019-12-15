package nc.vo.hkjt.huiyuan.cikazong;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.cikazong.CikazongHVO")
public class CikazongBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(CikazongBillVOMeta.class);
    return billMeta;
  }

  @Override
  public CikazongHVO getParentVO() {
    return (CikazongHVO) this.getParent();
  }
}