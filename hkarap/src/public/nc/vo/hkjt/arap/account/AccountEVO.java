package nc.vo.hkjt.arap.account;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class AccountEVO extends SuperVO {
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
*核销信息pk
*/
public static final String PK_HK_ARAP_ACCOUT_E="pk_hk_arap_accout_e";
/**
*时间戳
*/
public static final String TS="ts";
/**
*自定义项01
*/
public static final String VEDEF01="vedef01";
/**
*自定义项02
*/
public static final String VEDEF02="vedef02";
/**
*自定义项03
*/
public static final String VEDEF03="vedef03";
/**
*自定义项04
*/
public static final String VEDEF04="vedef04";
/**
*自定义项05
*/
public static final String VEDEF05="vedef05";
/**
*自定义项06
*/
public static final String VEDEF06="vedef06";
/**
*自定义项07
*/
public static final String VEDEF07="vedef07";
/**
*自定义项08
*/
public static final String VEDEF08="vedef08";
/**
*自定义项09
*/
public static final String VEDEF09="vedef09";
/**
*自定义项10
*/
public static final String VEDEF10="vedef10";
/**
*自定义项11
*/
public static final String VEDEF11="vedef11";
/**
*自定义项12
*/
public static final String VEDEF12="vedef12";
/**
*自定义项13
*/
public static final String VEDEF13="vedef13";
/**
*自定义项14
*/
public static final String VEDEF14="vedef14";
/**
*自定义项15
*/
public static final String VEDEF15="vedef15";
/**
*自定义项16
*/
public static final String VEDEF16="vedef16";
/**
*自定义项17
*/
public static final String VEDEF17="vedef17";
/**
*自定义项18
*/
public static final String VEDEF18="vedef18";
/**
*自定义项19
*/
public static final String VEDEF19="vedef19";
/**
*自定义项20
*/
public static final String VEDEF20="vedef20";
/**
*备注
*/
public static final String VEMEMO="vememo";
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
return (String) this.getAttributeValue( AccountEVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( AccountEVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( AccountEVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( AccountEVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( AccountEVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( AccountEVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( AccountEVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( AccountEVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( AccountEVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( AccountEVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( AccountEVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( AccountEVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_arap_account () {
return (String) this.getAttributeValue( AccountEVO.PK_HK_ARAP_ACCOUNT);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_arap_account 上层单据主键
*/
public void setPk_hk_arap_account ( String pk_hk_arap_account) {
this.setAttributeValue( AccountEVO.PK_HK_ARAP_ACCOUNT,pk_hk_arap_account);
 } 

/** 
* 获取核销信息pk
*
* @return 核销信息pk
*/
public String getPk_hk_arap_accout_e () {
return (String) this.getAttributeValue( AccountEVO.PK_HK_ARAP_ACCOUT_E);
 } 

/** 
* 设置核销信息pk
*
* @param pk_hk_arap_accout_e 核销信息pk
*/
public void setPk_hk_arap_accout_e ( String pk_hk_arap_accout_e) {
this.setAttributeValue( AccountEVO.PK_HK_ARAP_ACCOUT_E,pk_hk_arap_accout_e);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( AccountEVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( AccountEVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVedef01 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vedef01 自定义项01
*/
public void setVedef01 ( String vedef01) {
this.setAttributeValue( AccountEVO.VEDEF01,vedef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVedef02 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vedef02 自定义项02
*/
public void setVedef02 ( String vedef02) {
this.setAttributeValue( AccountEVO.VEDEF02,vedef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVedef03 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vedef03 自定义项03
*/
public void setVedef03 ( String vedef03) {
this.setAttributeValue( AccountEVO.VEDEF03,vedef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVedef04 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vedef04 自定义项04
*/
public void setVedef04 ( String vedef04) {
this.setAttributeValue( AccountEVO.VEDEF04,vedef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVedef05 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vedef05 自定义项05
*/
public void setVedef05 ( String vedef05) {
this.setAttributeValue( AccountEVO.VEDEF05,vedef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVedef06 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vedef06 自定义项06
*/
public void setVedef06 ( String vedef06) {
this.setAttributeValue( AccountEVO.VEDEF06,vedef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVedef07 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vedef07 自定义项07
*/
public void setVedef07 ( String vedef07) {
this.setAttributeValue( AccountEVO.VEDEF07,vedef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVedef08 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vedef08 自定义项08
*/
public void setVedef08 ( String vedef08) {
this.setAttributeValue( AccountEVO.VEDEF08,vedef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVedef09 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vedef09 自定义项09
*/
public void setVedef09 ( String vedef09) {
this.setAttributeValue( AccountEVO.VEDEF09,vedef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVedef10 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vedef10 自定义项10
*/
public void setVedef10 ( String vedef10) {
this.setAttributeValue( AccountEVO.VEDEF10,vedef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVedef11 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vedef11 自定义项11
*/
public void setVedef11 ( String vedef11) {
this.setAttributeValue( AccountEVO.VEDEF11,vedef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVedef12 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vedef12 自定义项12
*/
public void setVedef12 ( String vedef12) {
this.setAttributeValue( AccountEVO.VEDEF12,vedef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVedef13 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vedef13 自定义项13
*/
public void setVedef13 ( String vedef13) {
this.setAttributeValue( AccountEVO.VEDEF13,vedef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVedef14 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vedef14 自定义项14
*/
public void setVedef14 ( String vedef14) {
this.setAttributeValue( AccountEVO.VEDEF14,vedef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVedef15 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vedef15 自定义项15
*/
public void setVedef15 ( String vedef15) {
this.setAttributeValue( AccountEVO.VEDEF15,vedef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVedef16 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vedef16 自定义项16
*/
public void setVedef16 ( String vedef16) {
this.setAttributeValue( AccountEVO.VEDEF16,vedef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVedef17 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vedef17 自定义项17
*/
public void setVedef17 ( String vedef17) {
this.setAttributeValue( AccountEVO.VEDEF17,vedef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVedef18 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vedef18 自定义项18
*/
public void setVedef18 ( String vedef18) {
this.setAttributeValue( AccountEVO.VEDEF18,vedef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVedef19 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vedef19 自定义项19
*/
public void setVedef19 ( String vedef19) {
this.setAttributeValue( AccountEVO.VEDEF19,vedef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVedef20 () {
return (String) this.getAttributeValue( AccountEVO.VEDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vedef20 自定义项20
*/
public void setVedef20 ( String vedef20) {
this.setAttributeValue( AccountEVO.VEDEF20,vedef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVememo () {
return (String) this.getAttributeValue( AccountEVO.VEMEMO);
 } 

/** 
* 设置备注
*
* @param vememo 备注
*/
public void setVememo ( String vememo) {
this.setAttributeValue( AccountEVO.VEMEMO,vememo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( AccountEVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( AccountEVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( AccountEVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( AccountEVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( AccountEVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( AccountEVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_accoutEVO");
  }
}