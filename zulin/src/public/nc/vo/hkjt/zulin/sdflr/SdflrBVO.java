package nc.vo.hkjt.zulin.sdflr;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SdflrBVO extends SuperVO {
/**
*本次抄表数
*/
public static final String BCCB_NUM="bccb_num";
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
*区域
*/
public static final String PK_AREA="pk_area";
/**
*客户
*/
public static final String PK_CUST="pk_cust";
/**
*上层单据主键
*/
public static final String PK_HK_ZULIN_SDFLR="pk_hk_zulin_sdflr";
/**
*水电费录入子pk
*/
public static final String PK_HK_ZULIN_SDFLR_B="pk_hk_zulin_sdflr_b";
/**
*位置
*/
public static final String PK_PLACE="pk_place";
/**
*房号
*/
public static final String PK_ROOM="pk_room";
/**
*收费项目
*/
public static final String PK_SFXM="pk_sfxm";
/**
*单价
*/
public static final String PRICE="price";
/**
*上次抄表日期
*/
public static final String SCCB_DATE="sccb_date";
/**
*上次抄表数
*/
public static final String SCCB_NUM="sccb_num";
/**
*倍数
*/
public static final String TIMES="times";
/**
*时间戳
*/
public static final String TS="ts";
/**
*应缴金额
*/
public static final String USE_MNY="use_mny";
/**
*用量
*/
public static final String USE_NUM="use_num";
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
*自定义项11
*/
public static final String VBDEF11="vbdef11";
/**
*自定义项12
*/
public static final String VBDEF12="vbdef12";
/**
*自定义项13
*/
public static final String VBDEF13="vbdef13";
/**
*自定义项14
*/
public static final String VBDEF14="vbdef14";
/**
*自定义项15
*/
public static final String VBDEF15="vbdef15";
/**
*自定义项16
*/
public static final String VBDEF16="vbdef16";
/**
*自定义项17
*/
public static final String VBDEF17="vbdef17";
/**
*自定义项18
*/
public static final String VBDEF18="vbdef18";
/**
*自定义项19
*/
public static final String VBDEF19="vbdef19";
/**
*自定义项20
*/
public static final String VBDEF20="vbdef20";
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
* 获取本次抄表数
*
* @return 本次抄表数
*/
public UFDouble getBccb_num () {
return (UFDouble) this.getAttributeValue( SdflrBVO.BCCB_NUM);
 } 

/** 
* 设置本次抄表数
*
* @param bccb_num 本次抄表数
*/
public void setBccb_num ( UFDouble bccb_num) {
this.setAttributeValue( SdflrBVO.BCCB_NUM,bccb_num);
 } 

/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( SdflrBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( SdflrBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( SdflrBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( SdflrBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( SdflrBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( SdflrBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( SdflrBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( SdflrBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( SdflrBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( SdflrBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( SdflrBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( SdflrBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取区域
*
* @return 区域
*/
public String getPk_area () {
return (String) this.getAttributeValue( SdflrBVO.PK_AREA);
 } 

/** 
* 设置区域
*
* @param pk_area 区域
*/
public void setPk_area ( String pk_area) {
this.setAttributeValue( SdflrBVO.PK_AREA,pk_area);
 } 

/** 
* 获取客户
*
* @return 客户
*/
public String getPk_cust () {
return (String) this.getAttributeValue( SdflrBVO.PK_CUST);
 } 

/** 
* 设置客户
*
* @param pk_cust 客户
*/
public void setPk_cust ( String pk_cust) {
this.setAttributeValue( SdflrBVO.PK_CUST,pk_cust);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_zulin_sdflr () {
return (String) this.getAttributeValue( SdflrBVO.PK_HK_ZULIN_SDFLR);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_zulin_sdflr 上层单据主键
*/
public void setPk_hk_zulin_sdflr ( String pk_hk_zulin_sdflr) {
this.setAttributeValue( SdflrBVO.PK_HK_ZULIN_SDFLR,pk_hk_zulin_sdflr);
 } 

/** 
* 获取水电费录入子pk
*
* @return 水电费录入子pk
*/
public String getPk_hk_zulin_sdflr_b () {
return (String) this.getAttributeValue( SdflrBVO.PK_HK_ZULIN_SDFLR_B);
 } 

/** 
* 设置水电费录入子pk
*
* @param pk_hk_zulin_sdflr_b 水电费录入子pk
*/
public void setPk_hk_zulin_sdflr_b ( String pk_hk_zulin_sdflr_b) {
this.setAttributeValue( SdflrBVO.PK_HK_ZULIN_SDFLR_B,pk_hk_zulin_sdflr_b);
 } 

/** 
* 获取位置
*
* @return 位置
*/
public String getPk_place () {
return (String) this.getAttributeValue( SdflrBVO.PK_PLACE);
 } 

/** 
* 设置位置
*
* @param pk_place 位置
*/
public void setPk_place ( String pk_place) {
this.setAttributeValue( SdflrBVO.PK_PLACE,pk_place);
 } 

/** 
* 获取房号
*
* @return 房号
*/
public String getPk_room () {
return (String) this.getAttributeValue( SdflrBVO.PK_ROOM);
 } 

/** 
* 设置房号
*
* @param pk_room 房号
*/
public void setPk_room ( String pk_room) {
this.setAttributeValue( SdflrBVO.PK_ROOM,pk_room);
 } 

/** 
* 获取收费项目
*
* @return 收费项目
*/
public String getPk_sfxm () {
return (String) this.getAttributeValue( SdflrBVO.PK_SFXM);
 } 

/** 
* 设置收费项目
*
* @param pk_sfxm 收费项目
*/
public void setPk_sfxm ( String pk_sfxm) {
this.setAttributeValue( SdflrBVO.PK_SFXM,pk_sfxm);
 } 

/** 
* 获取单价
*
* @return 单价
*/
public UFDouble getPrice () {
return (UFDouble) this.getAttributeValue( SdflrBVO.PRICE);
 } 

/** 
* 设置单价
*
* @param price 单价
*/
public void setPrice ( UFDouble price) {
this.setAttributeValue( SdflrBVO.PRICE,price);
 } 

/** 
* 获取上次抄表日期
*
* @return 上次抄表日期
*/
public UFDate getSccb_date () {
return (UFDate) this.getAttributeValue( SdflrBVO.SCCB_DATE);
 } 

/** 
* 设置上次抄表日期
*
* @param sccb_date 上次抄表日期
*/
public void setSccb_date ( UFDate sccb_date) {
this.setAttributeValue( SdflrBVO.SCCB_DATE,sccb_date);
 } 

/** 
* 获取上次抄表数
*
* @return 上次抄表数
*/
public UFDouble getSccb_num () {
return (UFDouble) this.getAttributeValue( SdflrBVO.SCCB_NUM);
 } 

/** 
* 设置上次抄表数
*
* @param sccb_num 上次抄表数
*/
public void setSccb_num ( UFDouble sccb_num) {
this.setAttributeValue( SdflrBVO.SCCB_NUM,sccb_num);
 } 

/** 
* 获取倍数
*
* @return 倍数
*/
public UFDouble getTimes () {
return (UFDouble) this.getAttributeValue( SdflrBVO.TIMES);
 } 

/** 
* 设置倍数
*
* @param times 倍数
*/
public void setTimes ( UFDouble times) {
this.setAttributeValue( SdflrBVO.TIMES,times);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( SdflrBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( SdflrBVO.TS,ts);
 } 

/** 
* 获取应缴金额
*
* @return 应缴金额
*/
public UFDouble getUse_mny () {
return (UFDouble) this.getAttributeValue( SdflrBVO.USE_MNY);
 } 

/** 
* 设置应缴金额
*
* @param use_mny 应缴金额
*/
public void setUse_mny ( UFDouble use_mny) {
this.setAttributeValue( SdflrBVO.USE_MNY,use_mny);
 } 

/** 
* 获取用量
*
* @return 用量
*/
public UFDouble getUse_num () {
return (UFDouble) this.getAttributeValue( SdflrBVO.USE_NUM);
 } 

/** 
* 设置用量
*
* @param use_num 用量
*/
public void setUse_num ( UFDouble use_num) {
this.setAttributeValue( SdflrBVO.USE_NUM,use_num);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( SdflrBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( SdflrBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( SdflrBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( SdflrBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( SdflrBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( SdflrBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( SdflrBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( SdflrBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( SdflrBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( SdflrBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vbdef11 自定义项11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( SdflrBVO.VBDEF11,vbdef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vbdef12 自定义项12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( SdflrBVO.VBDEF12,vbdef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vbdef13 自定义项13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( SdflrBVO.VBDEF13,vbdef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vbdef14 自定义项14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( SdflrBVO.VBDEF14,vbdef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vbdef15 自定义项15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( SdflrBVO.VBDEF15,vbdef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vbdef16 自定义项16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( SdflrBVO.VBDEF16,vbdef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vbdef17 自定义项17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( SdflrBVO.VBDEF17,vbdef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vbdef18 自定义项18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( SdflrBVO.VBDEF18,vbdef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vbdef19 自定义项19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( SdflrBVO.VBDEF19,vbdef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( SdflrBVO.VBDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vbdef20 自定义项20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( SdflrBVO.VBDEF20,vbdef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( SdflrBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( SdflrBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( SdflrBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( SdflrBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( SdflrBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( SdflrBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( SdflrBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( SdflrBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_zulin_sdflrBVO");
  }
}