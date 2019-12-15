package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KadanganYZVO extends SuperVO {
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
*卡余额
*/
public static final String KAYUE="kayue";
/**
*上层单据主键
*/
public static final String PK_HK_HUIYUAN_KADANGAN="pk_hk_huiyuan_kadangan";
/**
*会员余转主键
*/
public static final String PK_HK_HUIYUAN_KADANGAN_YZ="pk_hk_huiyuan_kadangan_yz";
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
*消费时间
*/
public static final String YZ_TIME="yz_time";
/**
*转出金额
*/
public static final String ZC_JE="zc_je";
/**
*转出实收
*/
public static final String ZC_SS="zc_ss";
/**
*账单号
*/
public static final String ZDH="zdh";
/**
*转入金额
*/
public static final String ZR_JE="zr_je";
/**
*转入实收
*/
public static final String ZR_SS="zr_ss";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KadanganYZVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KadanganYZVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KadanganYZVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KadanganYZVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KadanganYZVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KadanganYZVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KadanganYZVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KadanganYZVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KadanganYZVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KadanganYZVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KadanganYZVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KadanganYZVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取卡余额
*
* @return 卡余额
*/
public UFDouble getKayue () {
return (UFDouble) this.getAttributeValue( KadanganYZVO.KAYUE);
 } 

/** 
* 设置卡余额
*
* @param kayue 卡余额
*/
public void setKayue ( UFDouble kayue) {
this.setAttributeValue( KadanganYZVO.KAYUE,kayue);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_kadangan () {
return (String) this.getAttributeValue( KadanganYZVO.PK_HK_HUIYUAN_KADANGAN);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_kadangan 上层单据主键
*/
public void setPk_hk_huiyuan_kadangan ( String pk_hk_huiyuan_kadangan) {
this.setAttributeValue( KadanganYZVO.PK_HK_HUIYUAN_KADANGAN,pk_hk_huiyuan_kadangan);
 } 

/** 
* 获取会员余转主键
*
* @return 会员余转主键
*/
public String getPk_hk_huiyuan_kadangan_yz () {
return (String) this.getAttributeValue( KadanganYZVO.PK_HK_HUIYUAN_KADANGAN_YZ);
 } 

/** 
* 设置会员余转主键
*
* @param pk_hk_huiyuan_kadangan_yz 会员余转主键
*/
public void setPk_hk_huiyuan_kadangan_yz ( String pk_hk_huiyuan_kadangan_yz) {
this.setAttributeValue( KadanganYZVO.PK_HK_HUIYUAN_KADANGAN_YZ,pk_hk_huiyuan_kadangan_yz);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KadanganYZVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KadanganYZVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KadanganYZVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KadanganYZVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KadanganYZVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KadanganYZVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KadanganYZVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KadanganYZVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KadanganYZVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KadanganYZVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KadanganYZVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KadanganYZVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KadanganYZVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KadanganYZVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KadanganYZVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KadanganYZVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KadanganYZVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( KadanganYZVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( KadanganYZVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KadanganYZVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KadanganYZVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取消费时间
*
* @return 消费时间
*/
public UFDateTime getYz_time () {
return (UFDateTime) this.getAttributeValue( KadanganYZVO.YZ_TIME);
 } 

/** 
* 设置消费时间
*
* @param yz_time 消费时间
*/
public void setYz_time ( UFDateTime yz_time) {
this.setAttributeValue( KadanganYZVO.YZ_TIME,yz_time);
 } 

/** 
* 获取转出金额
*
* @return 转出金额
*/
public UFDouble getZc_je () {
return (UFDouble) this.getAttributeValue( KadanganYZVO.ZC_JE);
 } 

/** 
* 设置转出金额
*
* @param zc_je 转出金额
*/
public void setZc_je ( UFDouble zc_je) {
this.setAttributeValue( KadanganYZVO.ZC_JE,zc_je);
 } 

/** 
* 获取转出实收
*
* @return 转出实收
*/
public UFDouble getZc_ss () {
return (UFDouble) this.getAttributeValue( KadanganYZVO.ZC_SS);
 } 

/** 
* 设置转出实收
*
* @param zc_ss 转出实收
*/
public void setZc_ss ( UFDouble zc_ss) {
this.setAttributeValue( KadanganYZVO.ZC_SS,zc_ss);
 } 

/** 
* 获取账单号
*
* @return 账单号
*/
public String getZdh () {
return (String) this.getAttributeValue( KadanganYZVO.ZDH);
 } 

/** 
* 设置账单号
*
* @param zdh 账单号
*/
public void setZdh ( String zdh) {
this.setAttributeValue( KadanganYZVO.ZDH,zdh);
 } 

/** 
* 获取转入金额
*
* @return 转入金额
*/
public UFDouble getZr_je () {
return (UFDouble) this.getAttributeValue( KadanganYZVO.ZR_JE);
 } 

/** 
* 设置转入金额
*
* @param zr_je 转入金额
*/
public void setZr_je ( UFDouble zr_je) {
this.setAttributeValue( KadanganYZVO.ZR_JE,zr_je);
 } 

/** 
* 获取转入实收
*
* @return 转入实收
*/
public UFDouble getZr_ss () {
return (UFDouble) this.getAttributeValue( KadanganYZVO.ZR_SS);
 } 

/** 
* 设置转入实收
*
* @param zr_ss 转入实收
*/
public void setZr_ss ( UFDouble zr_ss) {
this.setAttributeValue( KadanganYZVO.ZR_SS,zr_ss);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KadanganYZVO");
  }
  
  public static final String DR="dr";
  public void setDr ( Integer dr) {
	  this.setAttributeValue( KadanganYZVO.DR,dr);
  }
  public Integer getDr () {
	  return (Integer) this.getAttributeValue( KadanganYZVO.DR);
	   } 
  
  public static final String DF_KA_CODE="df_ka_code";
  public void setDf_ka_code ( String df_ka_code) {
	  this.setAttributeValue( KadanganYZVO.DF_KA_CODE,df_ka_code);
  }
  public String getDf_ka_code () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KA_CODE);
	   } 
  
  public static final String DF_KA_NAME="df_ka_name";
  public void setDf_ka_name ( String df_ka_name) {
	  this.setAttributeValue( KadanganYZVO.DF_KA_NAME,df_ka_name);
  }
  public String getDf_ka_name () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KA_NAME);
	   } 
  
  public static final String DF_KA_PK="df_ka_pk";
  public void setDf_ka_pk ( String df_ka_pk) {
	  this.setAttributeValue( KadanganYZVO.DF_KA_PK,df_ka_pk);
  }
  public String getDf_ka_pk () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KA_PK);
	   } 
  
  public static final String DF_KAXING_PK="df_kaxing_pk";
  public void setDf_kaxing_pk ( String df_kaxing_pk) {
	  this.setAttributeValue( KadanganYZVO.DF_KAXING_PK,df_kaxing_pk);
  }
  public String getDf_kaxing_pk () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KAXING_PK);
  } 
  
  public static final String DF_KAXING_CODE="df_kaxing_code";
  public void setDf_kaxing_code ( String df_kaxing_code) {
	  this.setAttributeValue( KadanganYZVO.DF_KAXING_CODE,df_kaxing_code);
  }
  public String getDf_kaxing_code () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KAXING_CODE);
	   } 
  
  public static final String DF_KAXING_NAME="df_kaxing_name";
  public void setDf_kaxing_name ( String df_kaxing_name) {
	  this.setAttributeValue( KadanganYZVO.DF_KAXING_NAME,df_kaxing_name);
  }
  public String getDf_kaxing_name () {
	  return (String) this.getAttributeValue( KadanganYZVO.DF_KAXING_NAME);
	   } 
  
}