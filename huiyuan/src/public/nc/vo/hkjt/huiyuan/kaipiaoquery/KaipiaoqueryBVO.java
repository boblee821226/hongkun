package nc.vo.hkjt.huiyuan.kaipiaoquery;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KaipiaoqueryBVO extends SuperVO {
/**
*源头单据表体id
*/
public static final String CFIRSTBILLBID="cfirstbillbid";	// 用做 发卡店
/**
*源头单据id
*/
public static final String CFIRSTBILLID="cfirstbillid";		// 开票截至时间
/**
*源头单据类型
*/
public static final String CFIRSTTYPECODE="cfirsttypecode";	// 作废开票金额
/**
*上层单据表体id
*/
public static final String CSOURCEBILLBID="csourcebillbid";
/**
*上层单据id
*/
public static final String CSOURCEBILLID="csourcebillid";
/**
*上层单据类型
*/
public static final String CSOURCETYPECODE="csourcetypecode";
/**
*发票号
*/
public static final String FPH="fph";
/**
*发票金额
*/
public static final String FPJE="fpje";
/**
*开票时间
*/
public static final String FPSJ="fpsj";
/**
*开票人
*/
public static final String KPR="kpr";
/**
*上层单据主键
*/
public static final String PK_HK_HUIYUAN_KAIPIAOQUERY="pk_hk_huiyuan_kaipiaoquery";
/**
*会员开票查询子pk
*/
public static final String PK_HK_HUIYUAN_KAIPIAOQUERY_B="pk_hk_huiyuan_kaipiaoquery_b";
/**
*时间戳
*/
public static final String TS="ts";
/**
*自定义项01
*/
public static final String VBDEF01="vbdef01";
/**
*自定义项02
*/
public static final String VBDEF02="vbdef02";
/**
*自定义项03
*/
public static final String VBDEF03="vbdef03";
/**
*自定义项04
*/
public static final String VBDEF04="vbdef04";
/**
*自定义项05
*/
public static final String VBDEF05="vbdef05";
/**
*自定义项06
*/
public static final String VBDEF06="vbdef06";
/**
*自定义项07
*/
public static final String VBDEF07="vbdef07";
/**
*自定义项08
*/
public static final String VBDEF08="vbdef08";
/**
*自定义项09
*/
public static final String VBDEF09="vbdef09";
/**
*自定义项10
*/
public static final String VBDEF10="vbdef10";
/**
*备注
*/
public static final String VBMEMO="vbmemo";
/**
*源头单据号
*/
public static final String VFIRSTBILLCODE="vfirstbillcode";
/**
*行号
*/
public static final String VROWNO="vrowno";
/**
*上层单据号
*/
public static final String VSOURCEBILLCODE="vsourcebillcode";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KaipiaoqueryBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KaipiaoqueryBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KaipiaoqueryBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KaipiaoqueryBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KaipiaoqueryBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KaipiaoqueryBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取发票号
*
* @return 发票号
*/
public String getFph () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.FPH);
 } 

/** 
* 设置发票号
*
* @param fph 发票号
*/
public void setFph ( String fph) {
this.setAttributeValue( KaipiaoqueryBVO.FPH,fph);
 } 

/** 
* 获取发票金额
*
* @return 发票金额
*/
public UFDouble getFpje () {
return (UFDouble) this.getAttributeValue( KaipiaoqueryBVO.FPJE);
 } 

/** 
* 设置发票金额
*
* @param fpje 发票金额
*/
public void setFpje ( UFDouble fpje) {
this.setAttributeValue( KaipiaoqueryBVO.FPJE,fpje);
 } 

/** 
* 获取开票时间
*
* @return 开票时间
*/
public UFDateTime getFpsj () {
return (UFDateTime) this.getAttributeValue( KaipiaoqueryBVO.FPSJ);
 } 

/** 
* 设置开票时间
*
* @param fpsj 开票时间
*/
public void setFpsj ( UFDateTime fpsj) {
this.setAttributeValue( KaipiaoqueryBVO.FPSJ,fpsj);
 } 

/** 
* 获取开票人
*
* @return 开票人
*/
public String getKpr () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.KPR);
 } 

/** 
* 设置开票人
*
* @param kpr 开票人
*/
public void setKpr ( String kpr) {
this.setAttributeValue( KaipiaoqueryBVO.KPR,kpr);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_kaipiaoquery () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.PK_HK_HUIYUAN_KAIPIAOQUERY);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_kaipiaoquery 上层单据主键
*/
public void setPk_hk_huiyuan_kaipiaoquery ( String pk_hk_huiyuan_kaipiaoquery) {
this.setAttributeValue( KaipiaoqueryBVO.PK_HK_HUIYUAN_KAIPIAOQUERY,pk_hk_huiyuan_kaipiaoquery);
 } 

/** 
* 获取会员开票查询子pk
*
* @return 会员开票查询子pk
*/
public String getPk_hk_huiyuan_kaipiaoquery_b () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.PK_HK_HUIYUAN_KAIPIAOQUERY_B);
 } 

/** 
* 设置会员开票查询子pk
*
* @param pk_hk_huiyuan_kaipiaoquery_b 会员开票查询子pk
*/
public void setPk_hk_huiyuan_kaipiaoquery_b ( String pk_hk_huiyuan_kaipiaoquery_b) {
this.setAttributeValue( KaipiaoqueryBVO.PK_HK_HUIYUAN_KAIPIAOQUERY_B,pk_hk_huiyuan_kaipiaoquery_b);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KaipiaoqueryBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KaipiaoqueryBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KaipiaoqueryBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KaipiaoqueryBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KaipiaoqueryBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KaipiaoqueryBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KaipiaoqueryBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KaipiaoqueryBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KaipiaoqueryBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KaipiaoqueryBVO");
  }
}