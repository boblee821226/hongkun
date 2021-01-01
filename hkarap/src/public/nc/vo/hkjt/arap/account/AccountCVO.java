package nc.vo.hkjt.arap.account;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class AccountCVO extends SuperVO {
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
*上层单据主键
*/
public static final String PK_HK_ARAP_ACCOUNT="pk_hk_arap_account";
/**
*佣金信息pk
*/
public static final String PK_HK_ARAP_ACCOUT_C="pk_hk_arap_accout_c";
/**
*时间戳
*/
public static final String TS="ts";
/**
*自定义项01
*/
public static final String VCDEF01="vcdef01";
/**
*自定义项02
*/
public static final String VCDEF02="vcdef02";
/**
*自定义项03
*/
public static final String VCDEF03="vcdef03";
/**
*自定义项04
*/
public static final String VCDEF04="vcdef04";
/**
*自定义项05
*/
public static final String VCDEF05="vcdef05";
/**
*自定义项06
*/
public static final String VCDEF06="vcdef06";
/**
*自定义项07
*/
public static final String VCDEF07="vcdef07";
/**
*自定义项08
*/
public static final String VCDEF08="vcdef08";
/**
*自定义项09
*/
public static final String VCDEF09="vcdef09";
/**
*自定义项10
*/
public static final String VCDEF10="vcdef10";
/**
*自定义项11
*/
public static final String VCDEF11="vcdef11";
/**
*自定义项12
*/
public static final String VCDEF12="vcdef12";
/**
*自定义项13
*/
public static final String VCDEF13="vcdef13";
/**
*自定义项14
*/
public static final String VCDEF14="vcdef14";
/**
*自定义项15
*/
public static final String VCDEF15="vcdef15";
/**
*自定义项16
*/
public static final String VCDEF16="vcdef16";
/**
*自定义项17
*/
public static final String VCDEF17="vcdef17";
/**
*自定义项18
*/
public static final String VCDEF18="vcdef18";
/**
*自定义项19
*/
public static final String VCDEF19="vcdef19";
/**
*自定义项20
*/
public static final String VCDEF20="vcdef20";
/**
*备注
*/
public static final String VCMEMO="vcmemo";
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
return (String) this.getAttributeValue( AccountCVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( AccountCVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( AccountCVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( AccountCVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( AccountCVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( AccountCVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( AccountCVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( AccountCVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( AccountCVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( AccountCVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( AccountCVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( AccountCVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_arap_account () {
return (String) this.getAttributeValue( AccountCVO.PK_HK_ARAP_ACCOUNT);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_arap_account 上层单据主键
*/
public void setPk_hk_arap_account ( String pk_hk_arap_account) {
this.setAttributeValue( AccountCVO.PK_HK_ARAP_ACCOUNT,pk_hk_arap_account);
 } 

/** 
* 获取佣金信息pk
*
* @return 佣金信息pk
*/
public String getPk_hk_arap_accout_c () {
return (String) this.getAttributeValue( AccountCVO.PK_HK_ARAP_ACCOUT_C);
 } 

/** 
* 设置佣金信息pk
*
* @param pk_hk_arap_accout_c 佣金信息pk
*/
public void setPk_hk_arap_accout_c ( String pk_hk_arap_accout_c) {
this.setAttributeValue( AccountCVO.PK_HK_ARAP_ACCOUT_C,pk_hk_arap_accout_c);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( AccountCVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( AccountCVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVcdef01 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vcdef01 自定义项01
*/
public void setVcdef01 ( String vcdef01) {
this.setAttributeValue( AccountCVO.VCDEF01,vcdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVcdef02 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vcdef02 自定义项02
*/
public void setVcdef02 ( String vcdef02) {
this.setAttributeValue( AccountCVO.VCDEF02,vcdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVcdef03 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vcdef03 自定义项03
*/
public void setVcdef03 ( String vcdef03) {
this.setAttributeValue( AccountCVO.VCDEF03,vcdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVcdef04 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vcdef04 自定义项04
*/
public void setVcdef04 ( String vcdef04) {
this.setAttributeValue( AccountCVO.VCDEF04,vcdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVcdef05 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vcdef05 自定义项05
*/
public void setVcdef05 ( String vcdef05) {
this.setAttributeValue( AccountCVO.VCDEF05,vcdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVcdef06 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vcdef06 自定义项06
*/
public void setVcdef06 ( String vcdef06) {
this.setAttributeValue( AccountCVO.VCDEF06,vcdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVcdef07 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vcdef07 自定义项07
*/
public void setVcdef07 ( String vcdef07) {
this.setAttributeValue( AccountCVO.VCDEF07,vcdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVcdef08 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vcdef08 自定义项08
*/
public void setVcdef08 ( String vcdef08) {
this.setAttributeValue( AccountCVO.VCDEF08,vcdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVcdef09 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vcdef09 自定义项09
*/
public void setVcdef09 ( String vcdef09) {
this.setAttributeValue( AccountCVO.VCDEF09,vcdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVcdef10 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vcdef10 自定义项10
*/
public void setVcdef10 ( String vcdef10) {
this.setAttributeValue( AccountCVO.VCDEF10,vcdef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVcdef11 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vcdef11 自定义项11
*/
public void setVcdef11 ( String vcdef11) {
this.setAttributeValue( AccountCVO.VCDEF11,vcdef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVcdef12 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vcdef12 自定义项12
*/
public void setVcdef12 ( String vcdef12) {
this.setAttributeValue( AccountCVO.VCDEF12,vcdef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVcdef13 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vcdef13 自定义项13
*/
public void setVcdef13 ( String vcdef13) {
this.setAttributeValue( AccountCVO.VCDEF13,vcdef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVcdef14 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vcdef14 自定义项14
*/
public void setVcdef14 ( String vcdef14) {
this.setAttributeValue( AccountCVO.VCDEF14,vcdef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVcdef15 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vcdef15 自定义项15
*/
public void setVcdef15 ( String vcdef15) {
this.setAttributeValue( AccountCVO.VCDEF15,vcdef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVcdef16 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vcdef16 自定义项16
*/
public void setVcdef16 ( String vcdef16) {
this.setAttributeValue( AccountCVO.VCDEF16,vcdef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVcdef17 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vcdef17 自定义项17
*/
public void setVcdef17 ( String vcdef17) {
this.setAttributeValue( AccountCVO.VCDEF17,vcdef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVcdef18 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vcdef18 自定义项18
*/
public void setVcdef18 ( String vcdef18) {
this.setAttributeValue( AccountCVO.VCDEF18,vcdef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVcdef19 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vcdef19 自定义项19
*/
public void setVcdef19 ( String vcdef19) {
this.setAttributeValue( AccountCVO.VCDEF19,vcdef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVcdef20 () {
return (String) this.getAttributeValue( AccountCVO.VCDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vcdef20 自定义项20
*/
public void setVcdef20 ( String vcdef20) {
this.setAttributeValue( AccountCVO.VCDEF20,vcdef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVcmemo () {
return (String) this.getAttributeValue( AccountCVO.VCMEMO);
 } 

/** 
* 设置备注
*
* @param vcmemo 备注
*/
public void setVcmemo ( String vcmemo) {
this.setAttributeValue( AccountCVO.VCMEMO,vcmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( AccountCVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( AccountCVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( AccountCVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( AccountCVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( AccountCVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( AccountCVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_accoutCVO");
  }
}