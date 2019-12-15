package nc.vo.hkjt.huiyuan.cikayue;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.cikayue.CikayueHVO")
public class CikayueBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(CikayueBillVOMeta.class);
    return billMeta;
  }

  @Override
  public CikayueHVO getParentVO() {
    return (CikayueHVO) this.getParent();
  }
}