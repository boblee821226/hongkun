package nc.vo.hkjt.store.lvyun.out;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class LyOutStoreBillVOMeta extends AbstractBillMeta {
  public LyOutStoreBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.store.lvyun.out.LyOutStoreHVO.class);
    this.addChildren(nc.vo.hkjt.store.lvyun.out.LyOutStoreBVO.class);
    this.addChildren(nc.vo.hkjt.store.lvyun.out.LyOutStoreDVO.class);
    this.addChildren(nc.vo.hkjt.store.lvyun.out.LyOutStoreCVO.class);
  }
}