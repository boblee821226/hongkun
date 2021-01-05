package nc.vo.hkjt.arap.operate;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class OperateBillVOMeta extends AbstractBillMeta {
  public OperateBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.arap.operate.OperateHeadVO.class);
    this.addChildren(nc.vo.hkjt.arap.operate.OperateHVO.class);
    this.addChildren(nc.vo.hkjt.arap.operate.OperateCVO.class);
    this.addChildren(nc.vo.hkjt.arap.operate.OperateIVO.class);
    this.addChildren(nc.vo.hkjt.arap.operate.OperateGVO.class);
    this.addChildren(nc.vo.hkjt.arap.operate.OperateFVO.class);
    this.addChildren(nc.vo.hkjt.arap.operate.OperateDVO.class);
    this.addChildren(nc.vo.hkjt.arap.operate.OperateEVO.class);
    this.addChildren(nc.vo.hkjt.arap.operate.OperateBVO.class);
  }
}