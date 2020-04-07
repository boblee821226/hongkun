package nc.vo.ct.purdaily.entity;

import nc.vo.ct.entity.CtAbstractVO;
import nc.vo.ct.enumeration.CtEntity;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.pubapp.res.NCModule;

/**
 * <b> 在此处简要描述此类的功能 </b>
 * <p>
 * 采购合同主表VO
 * </p>
 * 创建日期:2010-03-18 19:14:49
 * 
 * @author
 * @version lizhengb
 */

public class CtPuVO extends CtAbstractVO {
  /** 是否总括订单 */
  public static final String BBRACKETORDER = "bbracketorder";

  /** 协议供货 */
  public static final String BPROTSUPPLY = "bprotsupply";

  /** 发布 */
  public static final String BPUBLISH = "bpublish";

  /** 是否显示最新版 */
  public static final String BSHOWLATEST = "bshowlatest";

  /** 来源协同合同 */
  public static final String BSRCECMCT = "bsrcecmct";

  // 供应商
  public static final String CVENDORID = "cvendorid";

  /** 价格类型 */
  public static final String IPRICETYPE = "ipricetype";

  /** 响应状态 */
  public static final String IRESPSTATUS = "irespstatus";

  /** 变更状态 */
  public static final String MODIFYSTATUS = "modifystatus";

  // 采购合同id
  public static final String PK_CT_PU = "pk_ct_pu";

  /** 发布人 */
  public static final String PK_PUBPSN = "pk_pubpsn";

  /** 采购公司 */
  public static final String PK_PURCORP = "pk_purcorp";

  /** 响应人 */
  public static final String PK_RESPPSN = "pk_resppsn";

  /** 发布时间 */
  public static final String TPUBTIME = "tpubtime";

  /** 响应时间 */
  public static final String TRESPTIME = "tresptime";

  /** 拒绝原因 */
  public static final String VREASON = "vreason";

  // modify by liangchen1 港华合同变更生效以及重走审批流需求

  private static final long serialVersionUID = -6057905148775268085L;

  /**
   * 按照默认方式创建构造子. 创建日期:2010-03-18 19:14:49
   */
  public CtPuVO() {
    super();
  }

  /** 总括订单 getter 方法 */
  public UFBoolean getBbracketOrder() {
    return (UFBoolean) this.getAttributeValue(CtPuVO.BBRACKETORDER);
  }

  /** 协议供货 getter 方法 */
  public UFBoolean getBprotsupply() {
    return (UFBoolean) this.getAttributeValue(CtPuVO.BPROTSUPPLY);
  }

  /** 发布 getter 方法 */
  public UFBoolean getBpublish() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BPUBLISH);
  }

  @Override
  public UFBoolean getBsc() {
    return (UFBoolean) this.getAttributeValue(CtAbstractVO.BSC);
  }

  /** 是否显示最新版 getter 方法 */
  public UFBoolean getBshowLatest() {
    return (UFBoolean) this.getAttributeValue(CtPuVO.BSHOWLATEST);
  }

  /** 来源协同合同 getter 方法 */
  public UFBoolean getBsrcecmct() {
    return (UFBoolean) this.getAttributeValue(CtPuVO.BSRCECMCT);
  }

  public String getCvendorid() {
    return (String) this.getAttributeValue(CtPuVO.CVENDORID);
  }

  /** 价格类型 getter 方法 */
  public Integer getIpricetype() {
    return (Integer) this.getAttributeValue(CtPuVO.IPRICETYPE);
  }

  /** 响应状态 getter 方法 */
  public Integer getIrespstatus() {
    return (Integer) this.getAttributeValue(CtPuVO.IRESPSTATUS);
  }

  /**
   * 得到元数据的实体属性，其中”ct.ct_pu”, ct表示的组件命名空间，对于期初差异来说， 其组件命名空间就是合同管理ct，ct_pu
   * 表示该实体的数据库表名。
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(
            NCModule.CT.getName().toLowerCase() + "." + CtEntity.ct_pu.name());
    return meta;
  }

  // modify by liangchen1 港华合同变更生效以及重走审批流需求
  /** 变更状态 getter 方法 */
  public Integer getModifyStatus() {
    return (Integer) this.getAttributeValue(CtPuVO.MODIFYSTATUS);
  }

  public String getPk_ct_pu() {
    return (String) this.getAttributeValue(CtPuVO.PK_CT_PU);
  }

  /** 发布人 getter 方法 */
  public String getPk_pubpsn() {
    return (String) this.getAttributeValue(CtPuVO.PK_PUBPSN);
  }

  /** 采购公司 getter 方法 */
  public String getPk_purcorp() {
    return (String) this.getAttributeValue(CtPuVO.PK_PURCORP);
  }

  /** 响应人 getter 方法 */
  public String getPk_resppsn() {
    return (String) this.getAttributeValue(CtPuVO.PK_RESPPSN);
  }

  /** 发布日期 getter 方法 */
  public UFDateTime getTpubtime() {
    return (UFDateTime) this.getAttributeValue(CtPuVO.TPUBTIME);
  }

  /** 响应日期 getter 方法 */
  public UFDateTime getTresptime() {
    return (UFDateTime) this.getAttributeValue(CtPuVO.TRESPTIME);
  }

  /** 拒绝/变更理由 getter 方法 */
  public String getVreason() {
    return (String) this.getAttributeValue(CtPuVO.VREASON);
  }

  /** 总括订单 Setter 方法 */
  public void setBbracketOrder(UFBoolean bbracketorder) {
    this.setAttributeValue(CtPuVO.BBRACKETORDER, bbracketorder);
  }

  /** 协议供货 setter 方法 */
  public void setBprotsupply(UFBoolean bprotsupply) {
    this.setAttributeValue(CtPuVO.BPROTSUPPLY, bprotsupply);
  }

  /** 发布 setter 方法 */
  public void setBpublish(UFBoolean bpublish) {
    this.setAttributeValue(OrderHeaderVO.BPUBLISH, bpublish);
  }

  @Override
  public void setBsc(UFBoolean bsc) {
    this.setAttributeValue(CtAbstractVO.BSC, bsc);
  }

  /** 是否显示最新版 setter 方法 */
  public void setBshowLatest(UFBoolean bshowlatest) {
    this.setAttributeValue(CtPuVO.BSHOWLATEST, bshowlatest);
  }

  /** 来源协同合同 setter 方法 */
  public void setBsrcecmct(UFBoolean bsrcecmct) {
    this.setAttributeValue(CtPuVO.BSRCECMCT, bsrcecmct);
  }

  public void setCvendorid(String cvendorid) {
    this.setAttributeValue(CtPuVO.CVENDORID, cvendorid);
  }

  /** 价格类型 setter 方法 */
  public void setIpricetype(Integer ipricetype) {
    this.setAttributeValue(CtPuVO.IPRICETYPE, ipricetype);
  }

  /** 响应状态 setter 方法 */
  public void setIrespstatus(Integer irespstatus) {
    this.setAttributeValue(CtPuVO.IRESPSTATUS, irespstatus);
  }

  /** 变更状态 setter 方法 */
  public void setModifyStatus(Integer modifystatus) {
    this.setAttributeValue(CtPuVO.MODIFYSTATUS, modifystatus);
  }

  public void setPk_ct_pu(String pk_ct_pu) {
    this.setAttributeValue(CtPuVO.PK_CT_PU, pk_ct_pu);
  }

  /** 发布人 setter 方法 */
  public void setPk_pubpsn(String pk_pubpsn) {
    this.setAttributeValue(CtPuVO.PK_PUBPSN, pk_pubpsn);
  }

  /** 采购公司 setter 方法 */
  public void setPk_purcorp(String pk_purcorp) {
    this.setAttributeValue(CtPuVO.PK_PURCORP, pk_purcorp);
  }

  /** 响应人 setter 方法 */
  public void setPk_resppsn(String pk_resppsn) {
    this.setAttributeValue(CtPuVO.PK_RESPPSN, pk_resppsn);
  }

  /** 发布日期 setter 方法 */
  public void setTpubtime(UFDateTime tpubtime) {
    this.setAttributeValue(CtPuVO.TPUBTIME, tpubtime);
  }

  /** 响应日期 setter 方法 */
  public void setTresptime(UFDateTime tresptime) {
    this.setAttributeValue(CtPuVO.TRESPTIME, tresptime);
  }

  /** 拒绝/变更理由 setter 方法 */
  public void setVreason(String vreason) {
    this.setAttributeValue(CtPuVO.VREASON, vreason);
  }
  
  /**
   * HK
   * 增加 10个字段
   * vhkfield01 到 vhkfield10
   */
  public static final String VHKFIELD01 = "vhkfield01";
  public String getVhkfield01() {
	  return (String) this.getAttributeValue(CtPuVO.VHKFIELD01);
  }
  public void setVhkfield01(String vhkfield01) {
	  this.setAttributeValue(CtPuVO.VHKFIELD01, vhkfield01);
  }
  
  public static final String VHKFIELD02 = "vhkfield02";
  public String getVhkfield02() {
	  return (String) this.getAttributeValue(CtPuVO.VHKFIELD02);
  }
  public void setVhkfield02(String vhkfield02) {
	  this.setAttributeValue(CtPuVO.VHKFIELD02, vhkfield02);
  }
  
  public static final String VHKFIELD03 = "vhkfield03";
  public String getVhkfield03() {
	  return (String) this.getAttributeValue(CtPuVO.VHKFIELD03);
  }
  public void setVhkfield03(String vhkfield03) {
	  this.setAttributeValue(CtPuVO.VHKFIELD03, vhkfield03);
  }
  
  public static final String VHKFIELD04 = "vhkfield04";
  public String getVhkfield04() {
	  return (String) this.getAttributeValue(CtPuVO.VHKFIELD04);
  }
  public void setVhkfield04(String vhkfield04) {
	  this.setAttributeValue(CtPuVO.VHKFIELD04, vhkfield04);
  }
  
  public static final String VHKFIELD05 = "vhkfield05";
  public String getVhkfield05() {
	  return (String) this.getAttributeValue(CtPuVO.VHKFIELD05);
  }
  public void setVhkfield05(String vhkfield05) {
	  this.setAttributeValue(CtPuVO.VHKFIELD05, vhkfield05);
  }
  
  public static final String VHKFIELD06 = "vhkfield06";
  public String getVhkfield06() {
	  return (String) this.getAttributeValue(CtPuVO.VHKFIELD06);
  }
  public void setVhkfield06(String vhkfield06) {
	  this.setAttributeValue(CtPuVO.VHKFIELD06, vhkfield06);
  }
  
  public static final String VHKFIELD07 = "vhkfield07";
  public String getVhkfield07() {
	  return (String) this.getAttributeValue(CtPuVO.VHKFIELD07);
  }
  public void setVhkfield07(String vhkfield07) {
	  this.setAttributeValue(CtPuVO.VHKFIELD07, vhkfield07);
  }
  
  public static final String VHKFIELD08 = "vhkfield08";
  public String getVhkfield08() {
	  return (String) this.getAttributeValue(CtPuVO.VHKFIELD08);
  }
  public void setVhkfield08(String vhkfield08) {
	  this.setAttributeValue(CtPuVO.VHKFIELD08, vhkfield08);
  }
  
  public static final String VHKFIELD09 = "vhkfield09";
  public String getVhkfield09() {
	  return (String) this.getAttributeValue(CtPuVO.VHKFIELD09);
  }
  public void setVhkfield09(String vhkfield09) {
	  this.setAttributeValue(CtPuVO.VHKFIELD09, vhkfield09);
  }
  
  public static final String VHKFIELD10 = "vhkfield10";
  public String getVhkfield10() {
	  return (String) this.getAttributeValue(CtPuVO.VHKFIELD10);
  }
  public void setVhkfield10(String vhkfield10) {
	  this.setAttributeValue(CtPuVO.VHKFIELD10, vhkfield10);
  }
  /***END***/

}
