package nc.vo.hkjt.srgk.huiguan.rsbaogao;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class RsbaogaoBVO extends SuperVO {
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
*大类
*/
public static final String DALEI="dalei";
/**
*附件说明
*/
public static final String FJSM="fjsm";
/**
*稽核范围
*/
public static final String JHFW="jhfw";
/**
*稽核要求
*/
public static final String JHYQ="jhyq";
/**
*稽核点
*/
public static final String JIHEDIAN="jihedian";
/**
*上层单据主键
*/
public static final String PK_HK_SRGK_HG_RSBAOGAO="pk_hk_srgk_hg_rsbaogao";
/**
*子表主键
*/
public static final String PK_HK_SRGK_HG_RSBAOGAO_B="pk_hk_srgk_hg_rsbaogao_b";
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
*上次单据号
*/
public static final String VSOURCEBILLCODE="vsourcebillcode";
/**
*异常原因
*/
public static final String YCYY="ycyy";
/**
*状态
*/
public static final String ZHANGTAI="zhangtai";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( RsbaogaoBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( RsbaogaoBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( RsbaogaoBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( RsbaogaoBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( RsbaogaoBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( RsbaogaoBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( RsbaogaoBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( RsbaogaoBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( RsbaogaoBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( RsbaogaoBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( RsbaogaoBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( RsbaogaoBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取大类
*
* @return 大类
* @see String
*/
public String getDalei () {
return (String) this.getAttributeValue( RsbaogaoBVO.DALEI);
 } 

/** 
* 设置大类
*
* @param dalei 大类
* @see String
*/
public void setDalei ( String dalei) {
this.setAttributeValue( RsbaogaoBVO.DALEI,dalei);
 } 

/** 
* 获取附件说明
*
* @return 附件说明
*/
public String getFjsm () {
return (String) this.getAttributeValue( RsbaogaoBVO.FJSM);
 } 

/** 
* 设置附件说明
*
* @param fjsm 附件说明
*/
public void setFjsm ( String fjsm) {
this.setAttributeValue( RsbaogaoBVO.FJSM,fjsm);
 } 

/** 
* 获取稽核范围
*
* @return 稽核范围
*/
public String getJhfw () {
return (String) this.getAttributeValue( RsbaogaoBVO.JHFW);
 } 

/** 
* 设置稽核范围
*
* @param jhfw 稽核范围
*/
public void setJhfw ( String jhfw) {
this.setAttributeValue( RsbaogaoBVO.JHFW,jhfw);
 } 

/** 
* 获取稽核要求
*
* @return 稽核要求
* @see String
*/
public String getJhyq () {
return (String) this.getAttributeValue( RsbaogaoBVO.JHYQ);
 } 

/** 
* 设置稽核要求
*
* @param jhyq 稽核要求
* @see String
*/
public void setJhyq ( String jhyq) {
this.setAttributeValue( RsbaogaoBVO.JHYQ,jhyq);
 } 

/** 
* 获取稽核点
*
* @return 稽核点
*/
public String getJihedian () {
return (String) this.getAttributeValue( RsbaogaoBVO.JIHEDIAN);
 } 

/** 
* 设置稽核点
*
* @param jihedian 稽核点
*/
public void setJihedian ( String jihedian) {
this.setAttributeValue( RsbaogaoBVO.JIHEDIAN,jihedian);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_srgk_hg_rsbaogao () {
return (String) this.getAttributeValue( RsbaogaoBVO.PK_HK_SRGK_HG_RSBAOGAO);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_srgk_hg_rsbaogao 上层单据主键
*/
public void setPk_hk_srgk_hg_rsbaogao ( String pk_hk_srgk_hg_rsbaogao) {
this.setAttributeValue( RsbaogaoBVO.PK_HK_SRGK_HG_RSBAOGAO,pk_hk_srgk_hg_rsbaogao);
 } 

/** 
* 获取子表主键
*
* @return 子表主键
*/
public String getPk_hk_srgk_hg_rsbaogao_b () {
return (String) this.getAttributeValue( RsbaogaoBVO.PK_HK_SRGK_HG_RSBAOGAO_B);
 } 

/** 
* 设置子表主键
*
* @param pk_hk_srgk_hg_rsbaogao_b 子表主键
*/
public void setPk_hk_srgk_hg_rsbaogao_b ( String pk_hk_srgk_hg_rsbaogao_b) {
this.setAttributeValue( RsbaogaoBVO.PK_HK_SRGK_HG_RSBAOGAO_B,pk_hk_srgk_hg_rsbaogao_b);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( RsbaogaoBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( RsbaogaoBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( RsbaogaoBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( RsbaogaoBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( RsbaogaoBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( RsbaogaoBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( RsbaogaoBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( RsbaogaoBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( RsbaogaoBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( RsbaogaoBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( RsbaogaoBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( RsbaogaoBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( RsbaogaoBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( RsbaogaoBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( RsbaogaoBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( RsbaogaoBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( RsbaogaoBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( RsbaogaoBVO.VROWNO,vrowno);
 } 

/** 
* 获取上次单据号
*
* @return 上次单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( RsbaogaoBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上次单据号
*
* @param vsourcebillcode 上次单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( RsbaogaoBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取异常原因
*
* @return 异常原因
*/
public String getYcyy () {
return (String) this.getAttributeValue( RsbaogaoBVO.YCYY);
 } 

/** 
* 设置异常原因
*
* @param ycyy 异常原因
*/
public void setYcyy ( String ycyy) {
this.setAttributeValue( RsbaogaoBVO.YCYY,ycyy);
 } 

/** 
* 获取状态
*
* @return 状态
* @see String
*/
public String getZhangtai () {
return (String) this.getAttributeValue( RsbaogaoBVO.ZHANGTAI);
 } 

/** 
* 设置状态
*
* @param zhangtai 状态
* @see String
*/
public void setZhangtai ( String zhangtai) {
this.setAttributeValue( RsbaogaoBVO.ZHANGTAI,zhangtai);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_RsbaogaoBVO");
  }
}