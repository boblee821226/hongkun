package nc.vo.hkjt.arap.bill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillEVO extends SuperVO {
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
public static final String PK_HK_ARAP_BILL="pk_hk_arap_bill";
/**
*备用pk
*/
public static final String PK_HK_ARAP_BILL_E="pk_hk_arap_bill_e";
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
return (String) this.getAttributeValue( BillEVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( BillEVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( BillEVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( BillEVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( BillEVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( BillEVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( BillEVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( BillEVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( BillEVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( BillEVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( BillEVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( BillEVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_arap_bill () {
return (String) this.getAttributeValue( BillEVO.PK_HK_ARAP_BILL);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_arap_bill 上层单据主键
*/
public void setPk_hk_arap_bill ( String pk_hk_arap_bill) {
this.setAttributeValue( BillEVO.PK_HK_ARAP_BILL,pk_hk_arap_bill);
 } 

/** 
* 获取备用pk
*
* @return 备用pk
*/
public String getPk_hk_arap_bill_e () {
return (String) this.getAttributeValue( BillEVO.PK_HK_ARAP_BILL_E);
 } 

/** 
* 设置备用pk
*
* @param pk_hk_arap_bill_e 备用pk
*/
public void setPk_hk_arap_bill_e ( String pk_hk_arap_bill_e) {
this.setAttributeValue( BillEVO.PK_HK_ARAP_BILL_E,pk_hk_arap_bill_e);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( BillEVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( BillEVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVedef01 () {
return (String) this.getAttributeValue( BillEVO.VEDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vedef01 自定义项01
*/
public void setVedef01 ( String vedef01) {
this.setAttributeValue( BillEVO.VEDEF01,vedef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVedef02 () {
return (String) this.getAttributeValue( BillEVO.VEDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vedef02 自定义项02
*/
public void setVedef02 ( String vedef02) {
this.setAttributeValue( BillEVO.VEDEF02,vedef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVedef03 () {
return (String) this.getAttributeValue( BillEVO.VEDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vedef03 自定义项03
*/
public void setVedef03 ( String vedef03) {
this.setAttributeValue( BillEVO.VEDEF03,vedef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVedef04 () {
return (String) this.getAttributeValue( BillEVO.VEDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vedef04 自定义项04
*/
public void setVedef04 ( String vedef04) {
this.setAttributeValue( BillEVO.VEDEF04,vedef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVedef05 () {
return (String) this.getAttributeValue( BillEVO.VEDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vedef05 自定义项05
*/
public void setVedef05 ( String vedef05) {
this.setAttributeValue( BillEVO.VEDEF05,vedef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVedef06 () {
return (String) this.getAttributeValue( BillEVO.VEDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vedef06 自定义项06
*/
public void setVedef06 ( String vedef06) {
this.setAttributeValue( BillEVO.VEDEF06,vedef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVedef07 () {
return (String) this.getAttributeValue( BillEVO.VEDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vedef07 自定义项07
*/
public void setVedef07 ( String vedef07) {
this.setAttributeValue( BillEVO.VEDEF07,vedef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVedef08 () {
return (String) this.getAttributeValue( BillEVO.VEDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vedef08 自定义项08
*/
public void setVedef08 ( String vedef08) {
this.setAttributeValue( BillEVO.VEDEF08,vedef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVedef09 () {
return (String) this.getAttributeValue( BillEVO.VEDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vedef09 自定义项09
*/
public void setVedef09 ( String vedef09) {
this.setAttributeValue( BillEVO.VEDEF09,vedef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVedef10 () {
return (String) this.getAttributeValue( BillEVO.VEDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vedef10 自定义项10
*/
public void setVedef10 ( String vedef10) {
this.setAttributeValue( BillEVO.VEDEF10,vedef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVedef11 () {
return (String) this.getAttributeValue( BillEVO.VEDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vedef11 自定义项11
*/
public void setVedef11 ( String vedef11) {
this.setAttributeValue( BillEVO.VEDEF11,vedef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVedef12 () {
return (String) this.getAttributeValue( BillEVO.VEDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vedef12 自定义项12
*/
public void setVedef12 ( String vedef12) {
this.setAttributeValue( BillEVO.VEDEF12,vedef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVedef13 () {
return (String) this.getAttributeValue( BillEVO.VEDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vedef13 自定义项13
*/
public void setVedef13 ( String vedef13) {
this.setAttributeValue( BillEVO.VEDEF13,vedef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVedef14 () {
return (String) this.getAttributeValue( BillEVO.VEDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vedef14 自定义项14
*/
public void setVedef14 ( String vedef14) {
this.setAttributeValue( BillEVO.VEDEF14,vedef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVedef15 () {
return (String) this.getAttributeValue( BillEVO.VEDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vedef15 自定义项15
*/
public void setVedef15 ( String vedef15) {
this.setAttributeValue( BillEVO.VEDEF15,vedef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVedef16 () {
return (String) this.getAttributeValue( BillEVO.VEDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vedef16 自定义项16
*/
public void setVedef16 ( String vedef16) {
this.setAttributeValue( BillEVO.VEDEF16,vedef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVedef17 () {
return (String) this.getAttributeValue( BillEVO.VEDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vedef17 自定义项17
*/
public void setVedef17 ( String vedef17) {
this.setAttributeValue( BillEVO.VEDEF17,vedef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVedef18 () {
return (String) this.getAttributeValue( BillEVO.VEDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vedef18 自定义项18
*/
public void setVedef18 ( String vedef18) {
this.setAttributeValue( BillEVO.VEDEF18,vedef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVedef19 () {
return (String) this.getAttributeValue( BillEVO.VEDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vedef19 自定义项19
*/
public void setVedef19 ( String vedef19) {
this.setAttributeValue( BillEVO.VEDEF19,vedef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVedef20 () {
return (String) this.getAttributeValue( BillEVO.VEDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vedef20 自定义项20
*/
public void setVedef20 ( String vedef20) {
this.setAttributeValue( BillEVO.VEDEF20,vedef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVememo () {
return (String) this.getAttributeValue( BillEVO.VEMEMO);
 } 

/** 
* 设置备注
*
* @param vememo 备注
*/
public void setVememo ( String vememo) {
this.setAttributeValue( BillEVO.VEMEMO,vememo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( BillEVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( BillEVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( BillEVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( BillEVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( BillEVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( BillEVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_billEVO");
  }
}