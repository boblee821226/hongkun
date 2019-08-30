package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class KadanganBillVOMeta extends AbstractBillMeta {
  public KadanganBillVOMeta() {
    this.init();
  }
  private void init() {
    this.setParent(nc.vo.hkjt.huiyuan.kadangan.KadanganHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganJHVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganXFVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganZFVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganCZVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganYZVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganHKVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganKPVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganJGVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganCKCZVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganCKXFVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganCKJGVO.class);
    this.addChildren(nc.vo.hkjt.huiyuan.kadangan.KadanganKKPVO.class);		// 可开票vo  2016年9月11日12:56:29
  }
}