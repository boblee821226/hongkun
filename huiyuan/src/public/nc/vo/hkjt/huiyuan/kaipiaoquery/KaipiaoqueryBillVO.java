package nc.vo.hkjt.huiyuan.kaipiaoquery;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.huiyuan.kaipiaoquery.KaipiaoqueryHVO")
public class KaipiaoqueryBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(KaipiaoqueryBillVOMeta.class);
    return billMeta;
  }

  @Override
  public KaipiaoqueryHVO getParentVO() {
    return (KaipiaoqueryHVO) this.getParent();
  }
}