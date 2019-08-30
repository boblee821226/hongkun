package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KadanganCKXFVO extends SuperVO {
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
*次卡结束日期
*/
public static final String EXPDATA="expdata";
/**
*项目编码
*/
public static final String ITEMID="itemid";
/**
*项目名称
*/
public static final String ITEMNAME="itemname";
/**
*次卡卡比例
*/
public static final String KABILI="kabili";
/**
*上层单据主键
*/
public static final String PK_HK_HUIYUAN_KADANGAN="pk_hk_huiyuan_kadangan";
/**
*次卡消费pk
*/
public static final String PK_HK_HUIYUAN_KADANGAN_CKXF="pk_hk_huiyuan_kadangan_ckxf";
/**
*次卡开始日期
*/
public static final String STARTDATA="startdata";
/**
*时间戳
*/
public static final String TS="ts";
/**
*使用次数
*/
public static final String USEDNUM="usednum";
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
*消费时间
*/
public static final String XF_TIME="xf_time";
/**
*源卡code
*/
public static final String Y_KA_CODE="y_ka_code";
/**
*源卡pk
*/
public static final String Y_KA_PK="y_ka_pk";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KadanganCKXFVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KadanganCKXFVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KadanganCKXFVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KadanganCKXFVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KadanganCKXFVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KadanganCKXFVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KadanganCKXFVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KadanganCKXFVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KadanganCKXFVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KadanganCKXFVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KadanganCKXFVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KadanganCKXFVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取次卡结束日期
*
* @return 次卡结束日期
*/
public String getExpdata () {
return (String) this.getAttributeValue( KadanganCKXFVO.EXPDATA);
 } 

/** 
* 设置次卡结束日期
*
* @param expdata 次卡结束日期
*/
public void setExpdata ( String expdata) {
this.setAttributeValue( KadanganCKXFVO.EXPDATA,expdata);
 } 

/** 
* 获取项目编码
*
* @return 项目编码
*/
public String getItemid () {
return (String) this.getAttributeValue( KadanganCKXFVO.ITEMID);
 } 

/** 
* 设置项目编码
*
* @param itemid 项目编码
*/
public void setItemid ( String itemid) {
this.setAttributeValue( KadanganCKXFVO.ITEMID,itemid);
 } 

/** 
* 获取项目名称
*
* @return 项目名称
*/
public String getItemname () {
return (String) this.getAttributeValue( KadanganCKXFVO.ITEMNAME);
 } 

/** 
* 设置项目名称
*
* @param itemname 项目名称
*/
public void setItemname ( String itemname) {
this.setAttributeValue( KadanganCKXFVO.ITEMNAME,itemname);
 } 

/** 
* 获取次卡卡比例
*
* @return 次卡卡比例
*/
public UFDouble getKabili () {
return (UFDouble) this.getAttributeValue( KadanganCKXFVO.KABILI);
 } 

/** 
* 设置次卡卡比例
*
* @param kabili 次卡卡比例
*/
public void setKabili ( UFDouble kabili) {
this.setAttributeValue( KadanganCKXFVO.KABILI,kabili);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_kadangan () {
return (String) this.getAttributeValue( KadanganCKXFVO.PK_HK_HUIYUAN_KADANGAN);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_kadangan 上层单据主键
*/
public void setPk_hk_huiyuan_kadangan ( String pk_hk_huiyuan_kadangan) {
this.setAttributeValue( KadanganCKXFVO.PK_HK_HUIYUAN_KADANGAN,pk_hk_huiyuan_kadangan);
 } 

/** 
* 获取次卡消费pk
*
* @return 次卡消费pk
*/
public String getPk_hk_huiyuan_kadangan_ckxf () {
return (String) this.getAttributeValue( KadanganCKXFVO.PK_HK_HUIYUAN_KADANGAN_CKXF);
 } 

/** 
* 设置次卡消费pk
*
* @param pk_hk_huiyuan_kadangan_ckxf 次卡消费pk
*/
public void setPk_hk_huiyuan_kadangan_ckxf ( String pk_hk_huiyuan_kadangan_ckxf) {
this.setAttributeValue( KadanganCKXFVO.PK_HK_HUIYUAN_KADANGAN_CKXF,pk_hk_huiyuan_kadangan_ckxf);
 } 

/** 
* 获取次卡开始日期
*
* @return 次卡开始日期
*/
public String getStartdata () {
return (String) this.getAttributeValue( KadanganCKXFVO.STARTDATA);
 } 

/** 
* 设置次卡开始日期
*
* @param startdata 次卡开始日期
*/
public void setStartdata ( String startdata) {
this.setAttributeValue( KadanganCKXFVO.STARTDATA,startdata);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KadanganCKXFVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KadanganCKXFVO.TS,ts);
 } 

/** 
* 获取使用次数
*
* @return 使用次数
*/
public UFDouble getUsednum () {
return (UFDouble) this.getAttributeValue( KadanganCKXFVO.USEDNUM);
 } 

/** 
* 设置使用次数
*
* @param usednum 使用次数
*/
public void setUsednum ( UFDouble usednum) {
this.setAttributeValue( KadanganCKXFVO.USEDNUM,usednum);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KadanganCKXFVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KadanganCKXFVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KadanganCKXFVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KadanganCKXFVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KadanganCKXFVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KadanganCKXFVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KadanganCKXFVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KadanganCKXFVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KadanganCKXFVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KadanganCKXFVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KadanganCKXFVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KadanganCKXFVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KadanganCKXFVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KadanganCKXFVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KadanganCKXFVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KadanganCKXFVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KadanganCKXFVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KadanganCKXFVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KadanganCKXFVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KadanganCKXFVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KadanganCKXFVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KadanganCKXFVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KadanganCKXFVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KadanganCKXFVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KadanganCKXFVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KadanganCKXFVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KadanganCKXFVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KadanganCKXFVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取消费时间
*
* @return 消费时间
*/
public UFDateTime getXf_time () {
return (UFDateTime) this.getAttributeValue( KadanganCKXFVO.XF_TIME);
 } 

/** 
* 设置消费时间
*
* @param xf_time 消费时间
*/
public void setXf_time ( UFDateTime xf_time) {
this.setAttributeValue( KadanganCKXFVO.XF_TIME,xf_time);
 } 

/** 
* 获取源卡code
*
* @return 源卡code
*/
public String getY_ka_code () {
return (String) this.getAttributeValue( KadanganCKXFVO.Y_KA_CODE);
 } 

/** 
* 设置源卡code
*
* @param y_ka_code 源卡code
*/
public void setY_ka_code ( String y_ka_code) {
this.setAttributeValue( KadanganCKXFVO.Y_KA_CODE,y_ka_code);
 } 

/** 
* 获取源卡pk
*
* @return 源卡pk
*/
public String getY_ka_pk () {
return (String) this.getAttributeValue( KadanganCKXFVO.Y_KA_PK);
 } 

/** 
* 设置源卡pk
*
* @param y_ka_pk 源卡pk
*/
public void setY_ka_pk ( String y_ka_pk) {
this.setAttributeValue( KadanganCKXFVO.Y_KA_PK,y_ka_pk);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KadanganCKXFVO");
  }
  
  public static final String DR="dr";
  public void setDr ( Integer dr) {
	  this.setAttributeValue( KadanganCZVO.DR,dr);
  }
  public Integer getDr () {
	  return (Integer) this.getAttributeValue( KadanganCKXFVO.DR);
  } 
  
  public static final String ZDH="zdh";	// 账单号
  public void setZdh ( String zdh) {
	  this.setAttributeValue( KadanganCKXFVO.ZDH,zdh);
  }
  public String getZdh () {
	  return (String) this.getAttributeValue( KadanganCKXFVO.ZDH );
  } 
  
  public static final String PRICE="price";
  public UFDouble getPrice () {
	  return (UFDouble) this.getAttributeValue( KadanganCKXFVO.PRICE);
	   } 
  public void setPrice ( UFDouble price) {
	  this.setAttributeValue( KadanganCKXFVO.PRICE,price);
   } 
  
  public static final String YWDATE="ywdate";
  public void setYwdate ( String ywdate) {
	  this.setAttributeValue( KadanganCKXFVO.YWDATE,ywdate);
  }
  public String getYwdate () {
	  return (String) this.getAttributeValue( KadanganCKXFVO.YWDATE );
  }
}

