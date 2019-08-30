package nc.vo.hkjt.zulin.yuebao;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.zulin.yuebao.YuebaoHVO")
public class YuebaoBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(YuebaoBillVOMeta.class);
    return billMeta;
  }

  @Override
  public YuebaoHVO getParentVO() {
    return (YuebaoHVO) this.getParent();
  }
}