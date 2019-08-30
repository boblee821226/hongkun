package nc.vo.hkjt.srgk.huiguan.rsbaogao;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class RsbaogaoBillVOMeta extends AbstractBillMeta {
  public RsbaogaoBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoHVO.class);
    this.addChildren(nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoBVO.class);
  }
}