package nc.vo.hkjt.arap.bill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillCVO extends SuperVO {
/**
*应收账号
*/
public static final String ACCNT="accnt";
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
*核销费用
*/
public static final String CHARGE="charge";
/**
*操作时间
*/
public static final String CREATE_DATETIME="create_datetime";
/**
*操作人
*/
public static final String CREATE_USER="create_user";
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
*绿云id
*/
public static final String ID="id";
/**
*核销结算
*/
public static final String PAY="pay";
/**
*上层单据主键
*/
public static final String PK_HK_ARAP_BILL="pk_hk_arap_bill";
/**
*核销主表pk
*/
public static final String PK_HK_ARAP_BILL_C="pk_hk_arap_bill_c";
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
* 获取应收账号
*
* @return 应收账号
*/
public Integer getAccnt () {
return (Integer) this.getAttributeValue( BillCVO.ACCNT);
 } 

/** 
* 设置应收账号
*
* @param accnt 应收账号
*/
public void setAccnt ( Integer accnt) {
this.setAttributeValue( BillCVO.ACCNT,accnt);
 } 

/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( BillCVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( BillCVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( BillCVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( BillCVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( BillCVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( BillCVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取核销费用
*
* @return 核销费用
*/
public UFDouble getCharge () {
return (UFDouble) this.getAttributeValue( BillCVO.CHARGE);
 } 

/** 
* 设置核销费用
*
* @param charge 核销费用
*/
public void setCharge ( UFDouble charge) {
this.setAttributeValue( BillCVO.CHARGE,charge);
 } 

/** 
* 获取操作时间
*
* @return 操作时间
*/
public String getCreate_datetime () {
return (String) this.getAttributeValue( BillCVO.CREATE_DATETIME);
 } 

/** 
* 设置操作时间
*
* @param create_datetime 操作时间
*/
public void setCreate_datetime ( String create_datetime) {
this.setAttributeValue( BillCVO.CREATE_DATETIME,create_datetime);
 } 

/** 
* 获取操作人
*
* @return 操作人
*/
public String getCreate_user () {
return (String) this.getAttributeValue( BillCVO.CREATE_USER);
 } 

/** 
* 设置操作人
*
* @param create_user 操作人
*/
public void setCreate_user ( String create_user) {
this.setAttributeValue( BillCVO.CREATE_USER,create_user);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( BillCVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( BillCVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( BillCVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( BillCVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( BillCVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( BillCVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取绿云id
*
* @return 绿云id
*/
public Integer getId () {
return (Integer) this.getAttributeValue( BillCVO.ID);
 } 

/** 
* 设置绿云id
*
* @param id 绿云id
*/
public void setId ( Integer id) {
this.setAttributeValue( BillCVO.ID,id);
 } 

/** 
* 获取核销结算
*
* @return 核销结算
*/
public UFDouble getPay () {
return (UFDouble) this.getAttributeValue( BillCVO.PAY);
 } 

/** 
* 设置核销结算
*
* @param pay 核销结算
*/
public void setPay ( UFDouble pay) {
this.setAttributeValue( BillCVO.PAY,pay);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_arap_bill () {
return (String) this.getAttributeValue( BillCVO.PK_HK_ARAP_BILL);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_arap_bill 上层单据主键
*/
public void setPk_hk_arap_bill ( String pk_hk_arap_bill) {
this.setAttributeValue( BillCVO.PK_HK_ARAP_BILL,pk_hk_arap_bill);
 } 

/** 
* 获取核销主表pk
*
* @return 核销主表pk
*/
public String getPk_hk_arap_bill_c () {
return (String) this.getAttributeValue( BillCVO.PK_HK_ARAP_BILL_C);
 } 

/** 
* 设置核销主表pk
*
* @param pk_hk_arap_bill_c 核销主表pk
*/
public void setPk_hk_arap_bill_c ( String pk_hk_arap_bill_c) {
this.setAttributeValue( BillCVO.PK_HK_ARAP_BILL_C,pk_hk_arap_bill_c);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( BillCVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( BillCVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVcdef01 () {
return (String) this.getAttributeValue( BillCVO.VCDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vcdef01 自定义项01
*/
public void setVcdef01 ( String vcdef01) {
this.setAttributeValue( BillCVO.VCDEF01,vcdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVcdef02 () {
return (String) this.getAttributeValue( BillCVO.VCDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vcdef02 自定义项02
*/
public void setVcdef02 ( String vcdef02) {
this.setAttributeValue( BillCVO.VCDEF02,vcdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVcdef03 () {
return (String) this.getAttributeValue( BillCVO.VCDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vcdef03 自定义项03
*/
public void setVcdef03 ( String vcdef03) {
this.setAttributeValue( BillCVO.VCDEF03,vcdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVcdef04 () {
return (String) this.getAttributeValue( BillCVO.VCDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vcdef04 自定义项04
*/
public void setVcdef04 ( String vcdef04) {
this.setAttributeValue( BillCVO.VCDEF04,vcdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVcdef05 () {
return (String) this.getAttributeValue( BillCVO.VCDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vcdef05 自定义项05
*/
public void setVcdef05 ( String vcdef05) {
this.setAttributeValue( BillCVO.VCDEF05,vcdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVcdef06 () {
return (String) this.getAttributeValue( BillCVO.VCDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vcdef06 自定义项06
*/
public void setVcdef06 ( String vcdef06) {
this.setAttributeValue( BillCVO.VCDEF06,vcdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVcdef07 () {
return (String) this.getAttributeValue( BillCVO.VCDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vcdef07 自定义项07
*/
public void setVcdef07 ( String vcdef07) {
this.setAttributeValue( BillCVO.VCDEF07,vcdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVcdef08 () {
return (String) this.getAttributeValue( BillCVO.VCDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vcdef08 自定义项08
*/
public void setVcdef08 ( String vcdef08) {
this.setAttributeValue( BillCVO.VCDEF08,vcdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVcdef09 () {
return (String) this.getAttributeValue( BillCVO.VCDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vcdef09 自定义项09
*/
public void setVcdef09 ( String vcdef09) {
this.setAttributeValue( BillCVO.VCDEF09,vcdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVcdef10 () {
return (String) this.getAttributeValue( BillCVO.VCDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vcdef10 自定义项10
*/
public void setVcdef10 ( String vcdef10) {
this.setAttributeValue( BillCVO.VCDEF10,vcdef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVcdef11 () {
return (String) this.getAttributeValue( BillCVO.VCDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vcdef11 自定义项11
*/
public void setVcdef11 ( String vcdef11) {
this.setAttributeValue( BillCVO.VCDEF11,vcdef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVcdef12 () {
return (String) this.getAttributeValue( BillCVO.VCDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vcdef12 自定义项12
*/
public void setVcdef12 ( String vcdef12) {
this.setAttributeValue( BillCVO.VCDEF12,vcdef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVcdef13 () {
return (String) this.getAttributeValue( BillCVO.VCDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vcdef13 自定义项13
*/
public void setVcdef13 ( String vcdef13) {
this.setAttributeValue( BillCVO.VCDEF13,vcdef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVcdef14 () {
return (String) this.getAttributeValue( BillCVO.VCDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vcdef14 自定义项14
*/
public void setVcdef14 ( String vcdef14) {
this.setAttributeValue( BillCVO.VCDEF14,vcdef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVcdef15 () {
return (String) this.getAttributeValue( BillCVO.VCDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vcdef15 自定义项15
*/
public void setVcdef15 ( String vcdef15) {
this.setAttributeValue( BillCVO.VCDEF15,vcdef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVcdef16 () {
return (String) this.getAttributeValue( BillCVO.VCDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vcdef16 自定义项16
*/
public void setVcdef16 ( String vcdef16) {
this.setAttributeValue( BillCVO.VCDEF16,vcdef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVcdef17 () {
return (String) this.getAttributeValue( BillCVO.VCDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vcdef17 自定义项17
*/
public void setVcdef17 ( String vcdef17) {
this.setAttributeValue( BillCVO.VCDEF17,vcdef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVcdef18 () {
return (String) this.getAttributeValue( BillCVO.VCDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vcdef18 自定义项18
*/
public void setVcdef18 ( String vcdef18) {
this.setAttributeValue( BillCVO.VCDEF18,vcdef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVcdef19 () {
return (String) this.getAttributeValue( BillCVO.VCDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vcdef19 自定义项19
*/
public void setVcdef19 ( String vcdef19) {
this.setAttributeValue( BillCVO.VCDEF19,vcdef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVcdef20 () {
return (String) this.getAttributeValue( BillCVO.VCDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vcdef20 自定义项20
*/
public void setVcdef20 ( String vcdef20) {
this.setAttributeValue( BillCVO.VCDEF20,vcdef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVcmemo () {
return (String) this.getAttributeValue( BillCVO.VCMEMO);
 } 

/** 
* 设置备注
*
* @param vcmemo 备注
*/
public void setVcmemo ( String vcmemo) {
this.setAttributeValue( BillCVO.VCMEMO,vcmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( BillCVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( BillCVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( BillCVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( BillCVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( BillCVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( BillCVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_billCVO");
  }
}