package nc.vo.hkjt.arap.account;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class AccountDVO extends SuperVO {
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
*销项发票信息pk
*/
public static final String PK_HK_ARAP_ACCOUT_D="pk_hk_arap_accout_d";
/**
*时间戳
*/
public static final String TS="ts";
/**
*自定义项01
*/
public static final String VDDEF01="vddef01";
/**
*自定义项02
*/
public static final String VDDEF02="vddef02";
/**
*自定义项03
*/
public static final String VDDEF03="vddef03";
/**
*自定义项04
*/
public static final String VDDEF04="vddef04";
/**
*自定义项05
*/
public static final String VDDEF05="vddef05";
/**
*自定义项06
*/
public static final String VDDEF06="vddef06";
/**
*自定义项07
*/
public static final String VDDEF07="vddef07";
/**
*自定义项08
*/
public static final String VDDEF08="vddef08";
/**
*自定义项09
*/
public static final String VDDEF09="vddef09";
/**
*自定义项10
*/
public static final String VDDEF10="vddef10";
/**
*自定义项11
*/
public static final String VDDEF11="vddef11";
/**
*自定义项12
*/
public static final String VDDEF12="vddef12";
/**
*自定义项13
*/
public static final String VDDEF13="vddef13";
/**
*自定义项14
*/
public static final String VDDEF14="vddef14";
/**
*自定义项15
*/
public static final String VDDEF15="vddef15";
/**
*自定义项16
*/
public static final String VDDEF16="vddef16";
/**
*自定义项17
*/
public static final String VDDEF17="vddef17";
/**
*自定义项18
*/
public static final String VDDEF18="vddef18";
/**
*自定义项19
*/
public static final String VDDEF19="vddef19";
/**
*自定义项20
*/
public static final String VDDEF20="vddef20";
/**
*备注
*/
public static final String VDMEMO="vdmemo";
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
return (String) this.getAttributeValue( AccountDVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( AccountDVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( AccountDVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( AccountDVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( AccountDVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( AccountDVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( AccountDVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( AccountDVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( AccountDVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( AccountDVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( AccountDVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( AccountDVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_arap_account () {
return (String) this.getAttributeValue( AccountDVO.PK_HK_ARAP_ACCOUNT);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_arap_account 上层单据主键
*/
public void setPk_hk_arap_account ( String pk_hk_arap_account) {
this.setAttributeValue( AccountDVO.PK_HK_ARAP_ACCOUNT,pk_hk_arap_account);
 } 

/** 
* 获取销项发票信息pk
*
* @return 销项发票信息pk
*/
public String getPk_hk_arap_accout_d () {
return (String) this.getAttributeValue( AccountDVO.PK_HK_ARAP_ACCOUT_D);
 } 

/** 
* 设置销项发票信息pk
*
* @param pk_hk_arap_accout_d 销项发票信息pk
*/
public void setPk_hk_arap_accout_d ( String pk_hk_arap_accout_d) {
this.setAttributeValue( AccountDVO.PK_HK_ARAP_ACCOUT_D,pk_hk_arap_accout_d);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( AccountDVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( AccountDVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVddef01 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vddef01 自定义项01
*/
public void setVddef01 ( String vddef01) {
this.setAttributeValue( AccountDVO.VDDEF01,vddef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVddef02 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vddef02 自定义项02
*/
public void setVddef02 ( String vddef02) {
this.setAttributeValue( AccountDVO.VDDEF02,vddef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVddef03 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vddef03 自定义项03
*/
public void setVddef03 ( String vddef03) {
this.setAttributeValue( AccountDVO.VDDEF03,vddef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVddef04 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vddef04 自定义项04
*/
public void setVddef04 ( String vddef04) {
this.setAttributeValue( AccountDVO.VDDEF04,vddef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVddef05 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vddef05 自定义项05
*/
public void setVddef05 ( String vddef05) {
this.setAttributeValue( AccountDVO.VDDEF05,vddef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVddef06 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vddef06 自定义项06
*/
public void setVddef06 ( String vddef06) {
this.setAttributeValue( AccountDVO.VDDEF06,vddef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVddef07 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vddef07 自定义项07
*/
public void setVddef07 ( String vddef07) {
this.setAttributeValue( AccountDVO.VDDEF07,vddef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVddef08 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vddef08 自定义项08
*/
public void setVddef08 ( String vddef08) {
this.setAttributeValue( AccountDVO.VDDEF08,vddef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVddef09 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vddef09 自定义项09
*/
public void setVddef09 ( String vddef09) {
this.setAttributeValue( AccountDVO.VDDEF09,vddef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVddef10 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vddef10 自定义项10
*/
public void setVddef10 ( String vddef10) {
this.setAttributeValue( AccountDVO.VDDEF10,vddef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVddef11 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vddef11 自定义项11
*/
public void setVddef11 ( String vddef11) {
this.setAttributeValue( AccountDVO.VDDEF11,vddef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVddef12 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vddef12 自定义项12
*/
public void setVddef12 ( String vddef12) {
this.setAttributeValue( AccountDVO.VDDEF12,vddef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVddef13 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vddef13 自定义项13
*/
public void setVddef13 ( String vddef13) {
this.setAttributeValue( AccountDVO.VDDEF13,vddef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVddef14 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vddef14 自定义项14
*/
public void setVddef14 ( String vddef14) {
this.setAttributeValue( AccountDVO.VDDEF14,vddef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVddef15 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vddef15 自定义项15
*/
public void setVddef15 ( String vddef15) {
this.setAttributeValue( AccountDVO.VDDEF15,vddef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVddef16 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vddef16 自定义项16
*/
public void setVddef16 ( String vddef16) {
this.setAttributeValue( AccountDVO.VDDEF16,vddef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVddef17 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vddef17 自定义项17
*/
public void setVddef17 ( String vddef17) {
this.setAttributeValue( AccountDVO.VDDEF17,vddef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVddef18 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vddef18 自定义项18
*/
public void setVddef18 ( String vddef18) {
this.setAttributeValue( AccountDVO.VDDEF18,vddef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVddef19 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vddef19 自定义项19
*/
public void setVddef19 ( String vddef19) {
this.setAttributeValue( AccountDVO.VDDEF19,vddef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVddef20 () {
return (String) this.getAttributeValue( AccountDVO.VDDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vddef20 自定义项20
*/
public void setVddef20 ( String vddef20) {
this.setAttributeValue( AccountDVO.VDDEF20,vddef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVdmemo () {
return (String) this.getAttributeValue( AccountDVO.VDMEMO);
 } 

/** 
* 设置备注
*
* @param vdmemo 备注
*/
public void setVdmemo ( String vdmemo) {
this.setAttributeValue( AccountDVO.VDMEMO,vdmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( AccountDVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( AccountDVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( AccountDVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( AccountDVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( AccountDVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( AccountDVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_accoutDVO");
  }
}