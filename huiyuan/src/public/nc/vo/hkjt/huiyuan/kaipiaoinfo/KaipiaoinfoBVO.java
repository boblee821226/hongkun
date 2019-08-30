package nc.vo.hkjt.huiyuan.kaipiaoinfo;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KaipiaoinfoBVO extends SuperVO {
/**
*源头单据表体id
*/
public static final String CFIRSTBILLBID="cfirstbillbid";
/**
*源头单据id
*/
public static final String CFIRSTBILLID="cfirstbillid";
/**
*源头单据类型
*/
public static final String CFIRSTTYPECODE="cfirsttypecode";
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
*发票金额
*/
public static final String FPJE="fpje";
/**
*卡号
*/
public static final String KA_CODE="ka_code";
/**
*卡pk
*/
public static final String KA_PK="ka_pk";
/**
*卡型code
*/
public static final String KAXING_CODE="kaxing_code";
/**
*卡型name
*/
public static final String KAXING_NAME="kaxing_name";
/**
*卡型pk
*/
public static final String KAXING_PK="kaxing_pk";
/**
*可开票总额
*/
public static final String KKPZE="kkpze";
/**
*上层单据主键
*/
public static final String PK_HK_HUIYUAN_KAIPIAOINFO="pk_hk_huiyuan_kaipiaoinfo";
/**
*会员开票子pk
*/
public static final String PK_HK_HUIYUAN_KAIPIAOINFO_B="pk_hk_huiyuan_kaipiaoinfo_b";
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
*之前开票总额
*/
public static final String ZQKPJE="zqkpje";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KaipiaoinfoBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KaipiaoinfoBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KaipiaoinfoBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KaipiaoinfoBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KaipiaoinfoBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KaipiaoinfoBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取发票金额
*
* @return 发票金额
*/
public UFDouble getFpje () {
return (UFDouble) this.getAttributeValue( KaipiaoinfoBVO.FPJE);
 } 

/** 
* 设置发票金额
*
* @param fpje 发票金额
*/
public void setFpje ( UFDouble fpje) {
this.setAttributeValue( KaipiaoinfoBVO.FPJE,fpje);
 } 

/** 
* 获取卡号
*
* @return 卡号
*/
public String getKa_code () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.KA_CODE);
 } 

/** 
* 设置卡号
*
* @param ka_code 卡号
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( KaipiaoinfoBVO.KA_CODE,ka_code);
 } 

/** 
* 获取卡pk
*
* @return 卡pk
*/
public String getKa_pk () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.KA_PK);
 } 

/** 
* 设置卡pk
*
* @param ka_pk 卡pk
*/
public void setKa_pk ( String ka_pk) {
this.setAttributeValue( KaipiaoinfoBVO.KA_PK,ka_pk);
 } 

/** 
* 获取卡型code
*
* @return 卡型code
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.KAXING_CODE);
 } 

/** 
* 设置卡型code
*
* @param kaxing_code 卡型code
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( KaipiaoinfoBVO.KAXING_CODE,kaxing_code);
 } 

/** 
* 获取卡型name
*
* @return 卡型name
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.KAXING_NAME);
 } 

/** 
* 设置卡型name
*
* @param kaxing_name 卡型name
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( KaipiaoinfoBVO.KAXING_NAME,kaxing_name);
 } 

/** 
* 获取卡型pk
*
* @return 卡型pk
*/
public String getKaxing_pk () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.KAXING_PK);
 } 

/** 
* 设置卡型pk
*
* @param kaxing_pk 卡型pk
*/
public void setKaxing_pk ( String kaxing_pk) {
this.setAttributeValue( KaipiaoinfoBVO.KAXING_PK,kaxing_pk);
 } 

/** 
* 获取可开票总额
*
* @return 可开票总额
*/
public UFDouble getKkpze () {
return (UFDouble) this.getAttributeValue( KaipiaoinfoBVO.KKPZE);
 } 

/** 
* 设置可开票总额
*
* @param kkpze 可开票总额
*/
public void setKkpze ( UFDouble kkpze) {
this.setAttributeValue( KaipiaoinfoBVO.KKPZE,kkpze);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_kaipiaoinfo () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.PK_HK_HUIYUAN_KAIPIAOINFO);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_kaipiaoinfo 上层单据主键
*/
public void setPk_hk_huiyuan_kaipiaoinfo ( String pk_hk_huiyuan_kaipiaoinfo) {
this.setAttributeValue( KaipiaoinfoBVO.PK_HK_HUIYUAN_KAIPIAOINFO,pk_hk_huiyuan_kaipiaoinfo);
 } 

/** 
* 获取会员开票子pk
*
* @return 会员开票子pk
*/
public String getPk_hk_huiyuan_kaipiaoinfo_b () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.PK_HK_HUIYUAN_KAIPIAOINFO_B);
 } 

/** 
* 设置会员开票子pk
*
* @param pk_hk_huiyuan_kaipiaoinfo_b 会员开票子pk
*/
public void setPk_hk_huiyuan_kaipiaoinfo_b ( String pk_hk_huiyuan_kaipiaoinfo_b) {
this.setAttributeValue( KaipiaoinfoBVO.PK_HK_HUIYUAN_KAIPIAOINFO_B,pk_hk_huiyuan_kaipiaoinfo_b);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KaipiaoinfoBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KaipiaoinfoBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KaipiaoinfoBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KaipiaoinfoBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KaipiaoinfoBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KaipiaoinfoBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KaipiaoinfoBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KaipiaoinfoBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KaipiaoinfoBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KaipiaoinfoBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KaipiaoinfoBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KaipiaoinfoBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KaipiaoinfoBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KaipiaoinfoBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KaipiaoinfoBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KaipiaoinfoBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KaipiaoinfoBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KaipiaoinfoBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取之前开票总额
*
* @return 之前开票总额
*/
public UFDouble getZqkpje () {
return (UFDouble) this.getAttributeValue( KaipiaoinfoBVO.ZQKPJE);
 } 

/** 
* 设置之前开票总额
*
* @param zqkpje 之前开票总额
*/
public void setZqkpje ( UFDouble zqkpje) {
this.setAttributeValue( KaipiaoinfoBVO.ZQKPJE,zqkpje);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KaipiaoinfoBVO");
  }
}