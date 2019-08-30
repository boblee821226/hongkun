package nc.vo.hkjt.huiyuan.cikatongji;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.cikatongji.CikatongjiHVO")
public class CikatongjiBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(CikatongjiBillVOMeta.class);
    return billMeta;
  }

  @Override
  public CikatongjiHVO getParentVO() {
    return (CikatongjiHVO) this.getParent();
  }
}