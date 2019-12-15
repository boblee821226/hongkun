package nc.vo.hkjt.srgk.huiguan.zhangdan;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.srgk.huiguan.zhangdan.ZhangdanHVO")
public class ZhangdanBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(ZhangdanBillVOMeta.class);
    return billMeta;
  }

  @Override
  public ZhangdanHVO getParentVO() {
    return (ZhangdanHVO) this.getParent();
  }
}