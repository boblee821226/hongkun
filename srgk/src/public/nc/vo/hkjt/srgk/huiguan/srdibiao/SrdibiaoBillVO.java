package nc.vo.hkjt.srgk.huiguan.srdibiao;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.srgk.huiguan.srdibiao.SrdibiaoHVO")
public class SrdibiaoBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(SrdibiaoBillVOMeta.class);
    return billMeta;
  }

  @Override
  public SrdibiaoHVO getParentVO() {
    return (SrdibiaoHVO) this.getParent();
  }
}