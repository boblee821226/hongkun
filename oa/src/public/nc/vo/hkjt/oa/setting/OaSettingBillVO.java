package nc.vo.hkjt.oa.setting;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.oa.setting.OaSettingVO")
public class OaSettingBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(OaSettingBillVOMeta.class);
    return billMeta;
  }

  @Override
  public OaSettingVO getParentVO() {
    return (OaSettingVO) this.getParent();
  }
}