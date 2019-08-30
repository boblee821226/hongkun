package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KadanganCZVO extends SuperVO {
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
*充值金额
*/
public static final String CZ_JE="cz_je";
/**
*实收金额
*/
public static final String CZ_SS="cz_ss";
/**
*充值时间
*/
public static final String CZ_TIME="cz_time";
/**
*赠送金额
*/
public static final String CZ_ZS="cz_zs";
/**
*卡余额
*/
public static final String KAYUE="kayue";
/**
*上层单据主键
*/
public static final String PK_HK_HUIYUAN_KADANGAN="pk_hk_huiyuan_kadangan";
/**
*会员充值主键
*/
public static final String PK_HK_HUIYUAN_KADANGAN_CZ="pk_hk_huiyuan_kadangan_cz";
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
*账单号
*/
public static final String ZDH="zdh";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KadanganCZVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KadanganCZVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KadanganCZVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KadanganCZVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KadanganCZVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KadanganCZVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KadanganCZVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KadanganCZVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KadanganCZVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KadanganCZVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KadanganCZVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KadanganCZVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取充值金额
*
* @return 充值金额
*/
public UFDouble getCz_je () {
return (UFDouble) this.getAttributeValue( KadanganCZVO.CZ_JE);
 } 

/** 
* 设置充值金额
*
* @param cz_je 充值金额
*/
public void setCz_je ( UFDouble cz_je) {
this.setAttributeValue( KadanganCZVO.CZ_JE,cz_je);
 } 

/** 
* 获取实收金额
*
* @return 实收金额
*/
public UFDouble getCz_ss () {
return (UFDouble) this.getAttributeValue( KadanganCZVO.CZ_SS);
 } 

/** 
* 设置实收金额
*
* @param cz_ss 实收金额
*/
public void setCz_ss ( UFDouble cz_ss) {
this.setAttributeValue( KadanganCZVO.CZ_SS,cz_ss);
 } 

/** 
* 获取充值时间
*
* @return 充值时间
*/
public UFDateTime getCz_time () {
return (UFDateTime) this.getAttributeValue( KadanganCZVO.CZ_TIME);
 } 

/** 
* 设置充值时间
*
* @param cz_time 充值时间
*/
public void setCz_time ( UFDateTime cz_time) {
this.setAttributeValue( KadanganCZVO.CZ_TIME,cz_time);
 } 

/** 
* 获取赠送金额
*
* @return 赠送金额
*/
public UFDouble getCz_zs () {
return (UFDouble) this.getAttributeValue( KadanganCZVO.CZ_ZS);
 } 

/** 
* 设置赠送金额
*
* @param cz_zs 赠送金额
*/
public void setCz_zs ( UFDouble cz_zs) {
this.setAttributeValue( KadanganCZVO.CZ_ZS,cz_zs);
 } 

/** 
* 获取卡余额
*
* @return 卡余额
*/
public UFDouble getKayue () {
return (UFDouble) this.getAttributeValue( KadanganCZVO.KAYUE);
 } 

/** 
* 设置卡余额
*
* @param kayue 卡余额
*/
public void setKayue ( UFDouble kayue) {
this.setAttributeValue( KadanganCZVO.KAYUE,kayue);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_kadangan () {
return (String) this.getAttributeValue( KadanganCZVO.PK_HK_HUIYUAN_KADANGAN);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_kadangan 上层单据主键
*/
public void setPk_hk_huiyuan_kadangan ( String pk_hk_huiyuan_kadangan) {
this.setAttributeValue( KadanganCZVO.PK_HK_HUIYUAN_KADANGAN,pk_hk_huiyuan_kadangan);
 } 

/** 
* 获取会员充值主键
*
* @return 会员充值主键
*/
public String getPk_hk_huiyuan_kadangan_cz () {
return (String) this.getAttributeValue( KadanganCZVO.PK_HK_HUIYUAN_KADANGAN_CZ);
 } 

/** 
* 设置会员充值主键
*
* @param pk_hk_huiyuan_kadangan_cz 会员充值主键
*/
public void setPk_hk_huiyuan_kadangan_cz ( String pk_hk_huiyuan_kadangan_cz) {
this.setAttributeValue( KadanganCZVO.PK_HK_HUIYUAN_KADANGAN_CZ,pk_hk_huiyuan_kadangan_cz);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KadanganCZVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KadanganCZVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KadanganCZVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KadanganCZVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KadanganCZVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KadanganCZVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KadanganCZVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KadanganCZVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KadanganCZVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KadanganCZVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KadanganCZVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KadanganCZVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KadanganCZVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KadanganCZVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KadanganCZVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KadanganCZVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KadanganCZVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KadanganCZVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KadanganCZVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KadanganCZVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KadanganCZVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KadanganCZVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KadanganCZVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KadanganCZVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KadanganCZVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KadanganCZVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( KadanganCZVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( KadanganCZVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KadanganCZVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KadanganCZVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取账单号
*
* @return 账单号
*/
public String getZdh () {
return (String) this.getAttributeValue( KadanganCZVO.ZDH);
 } 

/** 
* 设置账单号
*
* @param zdh 账单号
*/
public void setZdh ( String zdh) {
this.setAttributeValue( KadanganCZVO.ZDH,zdh);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KadanganCZVO");
  }
  
  public static final String DR="dr";
  public void setDr ( Integer dr) {
	  this.setAttributeValue( KadanganCZVO.DR,dr);
  }
  public Integer getDr () {
	  return (Integer) this.getAttributeValue( KadanganCZVO.DR);
	   } 
  
}