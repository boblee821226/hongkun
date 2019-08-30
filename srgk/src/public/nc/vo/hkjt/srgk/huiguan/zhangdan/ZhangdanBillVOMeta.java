package nc.vo.hkjt.srgk.huiguan.zhangdan;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class ZhangdanBillVOMeta extends AbstractBillMeta {
  public ZhangdanBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanHVO.class);
    this.addChildren(nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanBVO.class);
  }
}