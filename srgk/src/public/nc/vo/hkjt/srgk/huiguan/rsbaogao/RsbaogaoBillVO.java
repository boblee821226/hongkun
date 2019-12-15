package nc.vo.hkjt.srgk.huiguan.rsbaogao;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.hkjt.srgk.huiguan.rsbaogao.RsbaogaoHVO")
public class RsbaogaoBillVO extends AbstractBill {

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(RsbaogaoBillVOMeta.class);
    return billMeta;
  }

  @Override
  public RsbaogaoHVO getParentVO() {
    return (RsbaogaoHVO) this.getParent();
  }
}