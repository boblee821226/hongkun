package nc.vo.hkjt.zulin.znjjs;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.zulin.znjjs.ZnjjsHVO")
public class ZnjjsBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(ZnjjsBillVOMeta.class);
    return billMeta;
  }

  @Override
  public ZnjjsHVO getParentVO() {
    return (ZnjjsHVO) this.getParent();
  }
}