package nc.vo.hkjt.huiyuan.cikainfo;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.cikainfo.CikainfoHVO")
public class CikainfoBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(CikainfoBillVOMeta.class);
    return billMeta;
  }

  @Override
  public CikainfoHVO getParentVO() {
    return (CikainfoHVO) this.getParent();
  }
}