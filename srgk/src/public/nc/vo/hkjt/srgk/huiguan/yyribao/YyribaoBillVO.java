package nc.vo.hkjt.srgk.huiguan.yyribao;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.srgk.huiguan.yyribao.YyribaoHVO")
public class YyribaoBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(YyribaoBillVOMeta.class);
    return billMeta;
  }

  @Override
  public YyribaoHVO getParentVO() {
    return (YyribaoHVO) this.getParent();
  }
}