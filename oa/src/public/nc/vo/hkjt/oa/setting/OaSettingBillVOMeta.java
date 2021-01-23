package nc.vo.hkjt.oa.setting;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class OaSettingBillVOMeta extends AbstractBillMeta {
  public OaSettingBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.oa.setting.OaSettingVO.class);
    this.addChildren(nc.vo.hkjt.oa.setting.OaSettingB2VO.class);
    this.addChildren(nc.vo.hkjt.oa.setting.OaSettingB3VO.class);
    this.addChildren(nc.vo.hkjt.oa.setting.OaSettingHVO.class);
    this.addChildren(nc.vo.hkjt.oa.setting.OaSettingB4VO.class);
    this.addChildren(nc.vo.hkjt.oa.setting.OaSettingB5VO.class);
    this.addChildren(nc.vo.hkjt.oa.setting.OaSettingB1VO.class);
  }
}