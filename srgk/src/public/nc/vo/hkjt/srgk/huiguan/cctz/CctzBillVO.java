package nc.vo.hkjt.srgk.huiguan.cctz;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.srgk.huiguan.cctz.CctzHVO")
public class CctzBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(CctzBillVOMeta.class);
    return billMeta;
  }

  @Override
  public CctzHVO getParentVO() {
    return (CctzHVO) this.getParent();
  }
}