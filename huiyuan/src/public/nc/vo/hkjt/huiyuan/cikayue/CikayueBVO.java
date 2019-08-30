package nc.vo.hkjt.huiyuan.cikayue;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CikayueBVO extends SuperVO {

private static final long serialVersionUID = 234364226984185059L;

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
*差次
*/
public static final String CHA_SL="cha_sl";
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
*充值数量
*/
public static final String CZ_SL="cz_sl";
/**
*项目编码
*/
public static final String ITEMID="itemid";
/**
*项目名称
*/
public static final String ITEMNAME="itemname";
/**
*金贵余量
*/
public static final String JG_SL="jg_sl";
/**
*上层单据主键
*/
public static final String PK_HK_HUIYUAN_CIKAYUE="pk_hk_huiyuan_cikayue";
/**
*次卡余额子pk
*/
public static final String PK_HK_HUIYUAN_CIKAYUE_B="pk_hk_huiyuan_cikayue_b";
/**
*期初金额
*/
public static final String QC_JE="qc_je";
/**
*期初数量
*/
public static final String QC_SL="qc_sl";
/**
*调减金额
*/
public static final String TJ_JE="tj_je";
/**
*调减数量
*/
public static final String TJ_SL="tj_sl";
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
*消费金额
*/
public static final String XF_JE="xf_je";
/**
*消费数量
*/
public static final String XF_SL="xf_sl";
/**
*余额
*/
public static final String YUE_JE="yue_je";
/**
*余量
*/
public static final String YUE_SL="yue_sl";
/**
*转出金额
*/
public static final String ZC_JE="zc_je";
/**
*转出数量
*/
public static final String ZC_SL="zc_sl";
/**
*转入金额
*/
public static final String ZR_JE="zr_je";
/**
*转入数量
*/
public static final String ZR_SL="zr_sl";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( CikayueBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( CikayueBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( CikayueBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( CikayueBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( CikayueBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( CikayueBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取差次
*
* @return 差次
*/
public UFDouble getCha_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.CHA_SL);
 } 

/** 
* 设置差次
*
* @param cha_sl 差次
*/
public void setCha_sl ( UFDouble cha_sl) {
this.setAttributeValue( CikayueBVO.CHA_SL,cha_sl);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( CikayueBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( CikayueBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( CikayueBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( CikayueBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( CikayueBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( CikayueBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取充值金额
*
* @return 充值金额
*/
public UFDouble getCz_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.CZ_JE);
 } 

/** 
* 设置充值金额
*
* @param cz_je 充值金额
*/
public void setCz_je ( UFDouble cz_je) {
this.setAttributeValue( CikayueBVO.CZ_JE,cz_je);
 } 

/** 
* 获取充值数量
*
* @return 充值数量
*/
public UFDouble getCz_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.CZ_SL);
 } 

/** 
* 设置充值数量
*
* @param cz_sl 充值数量
*/
public void setCz_sl ( UFDouble cz_sl) {
this.setAttributeValue( CikayueBVO.CZ_SL,cz_sl);
 } 

/** 
* 获取项目编码
*
* @return 项目编码
*/
public String getItemid () {
return (String) this.getAttributeValue( CikayueBVO.ITEMID);
 } 

/** 
* 设置项目编码
*
* @param itemid 项目编码
*/
public void setItemid ( String itemid) {
this.setAttributeValue( CikayueBVO.ITEMID,itemid);
 } 

/** 
* 获取项目名称
*
* @return 项目名称
*/
public String getItemname () {
return (String) this.getAttributeValue( CikayueBVO.ITEMNAME);
 } 

/** 
* 设置项目名称
*
* @param itemname 项目名称
*/
public void setItemname ( String itemname) {
this.setAttributeValue( CikayueBVO.ITEMNAME,itemname);
 } 

/** 
* 获取金贵余量
*
* @return 金贵余量
*/
public UFDouble getJg_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.JG_SL);
 } 

/** 
* 设置金贵余量
*
* @param jg_sl 金贵余量
*/
public void setJg_sl ( UFDouble jg_sl) {
this.setAttributeValue( CikayueBVO.JG_SL,jg_sl);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_cikayue () {
return (String) this.getAttributeValue( CikayueBVO.PK_HK_HUIYUAN_CIKAYUE);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_cikayue 上层单据主键
*/
public void setPk_hk_huiyuan_cikayue ( String pk_hk_huiyuan_cikayue) {
this.setAttributeValue( CikayueBVO.PK_HK_HUIYUAN_CIKAYUE,pk_hk_huiyuan_cikayue);
 } 

/** 
* 获取次卡余额子pk
*
* @return 次卡余额子pk
*/
public String getPk_hk_huiyuan_cikayue_b () {
return (String) this.getAttributeValue( CikayueBVO.PK_HK_HUIYUAN_CIKAYUE_B);
 } 

/** 
* 设置次卡余额子pk
*
* @param pk_hk_huiyuan_cikayue_b 次卡余额子pk
*/
public void setPk_hk_huiyuan_cikayue_b ( String pk_hk_huiyuan_cikayue_b) {
this.setAttributeValue( CikayueBVO.PK_HK_HUIYUAN_CIKAYUE_B,pk_hk_huiyuan_cikayue_b);
 } 

/** 
* 获取期初金额
*
* @return 期初金额
*/
public UFDouble getQc_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.QC_JE);
 } 

/** 
* 设置期初金额
*
* @param qc_je 期初金额
*/
public void setQc_je ( UFDouble qc_je) {
this.setAttributeValue( CikayueBVO.QC_JE,qc_je);
 } 

/** 
* 获取期初数量
*
* @return 期初数量
*/
public UFDouble getQc_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.QC_SL);
 } 

/** 
* 设置期初数量
*
* @param qc_sl 期初数量
*/
public void setQc_sl ( UFDouble qc_sl) {
this.setAttributeValue( CikayueBVO.QC_SL,qc_sl);
 } 

/** 
* 获取调减金额
*
* @return 调减金额
*/
public UFDouble getTj_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.TJ_JE);
 } 

/** 
* 设置调减金额
*
* @param tj_je 调减金额
*/
public void setTj_je ( UFDouble tj_je) {
this.setAttributeValue( CikayueBVO.TJ_JE,tj_je);
 } 

/** 
* 获取调减数量
*
* @return 调减数量
*/
public UFDouble getTj_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.TJ_SL);
 } 

/** 
* 设置调减数量
*
* @param tj_sl 调减数量
*/
public void setTj_sl ( UFDouble tj_sl) {
this.setAttributeValue( CikayueBVO.TJ_SL,tj_sl);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( CikayueBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( CikayueBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( CikayueBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( CikayueBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( CikayueBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( CikayueBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( CikayueBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( CikayueBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( CikayueBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( CikayueBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( CikayueBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( CikayueBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( CikayueBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( CikayueBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( CikayueBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( CikayueBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( CikayueBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( CikayueBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( CikayueBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( CikayueBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( CikayueBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取消费金额
*
* @return 消费金额
*/
public UFDouble getXf_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.XF_JE);
 } 

/** 
* 设置消费金额
*
* @param xf_je 消费金额
*/
public void setXf_je ( UFDouble xf_je) {
this.setAttributeValue( CikayueBVO.XF_JE,xf_je);
 } 

/** 
* 获取消费数量
*
* @return 消费数量
*/
public UFDouble getXf_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.XF_SL);
 } 

/** 
* 设置消费数量
*
* @param xf_sl 消费数量
*/
public void setXf_sl ( UFDouble xf_sl) {
this.setAttributeValue( CikayueBVO.XF_SL,xf_sl);
 } 

/** 
* 获取余额
*
* @return 余额
*/
public UFDouble getYue_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.YUE_JE);
 } 

/** 
* 设置余额
*
* @param yue_je 余额
*/
public void setYue_je ( UFDouble yue_je) {
this.setAttributeValue( CikayueBVO.YUE_JE,yue_je);
 } 

/** 
* 获取余量
*
* @return 余量
*/
public UFDouble getYue_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.YUE_SL);
 } 

/** 
* 设置余量
*
* @param yue_sl 余量
*/
public void setYue_sl ( UFDouble yue_sl) {
this.setAttributeValue( CikayueBVO.YUE_SL,yue_sl);
 } 

/** 
* 获取转出金额
*
* @return 转出金额
*/
public UFDouble getZc_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.ZC_JE);
 } 

/** 
* 设置转出金额
*
* @param zc_je 转出金额
*/
public void setZc_je ( UFDouble zc_je) {
this.setAttributeValue( CikayueBVO.ZC_JE,zc_je);
 } 

/** 
* 获取转出数量
*
* @return 转出数量
*/
public UFDouble getZc_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.ZC_SL);
 } 

/** 
* 设置转出数量
*
* @param zc_sl 转出数量
*/
public void setZc_sl ( UFDouble zc_sl) {
this.setAttributeValue( CikayueBVO.ZC_SL,zc_sl);
 } 

/** 
* 获取转入金额
*
* @return 转入金额
*/
public UFDouble getZr_je () {
return (UFDouble) this.getAttributeValue( CikayueBVO.ZR_JE);
 } 

/** 
* 设置转入金额
*
* @param zr_je 转入金额
*/
public void setZr_je ( UFDouble zr_je) {
this.setAttributeValue( CikayueBVO.ZR_JE,zr_je);
 } 

/** 
* 获取转入数量
*
* @return 转入数量
*/
public UFDouble getZr_sl () {
return (UFDouble) this.getAttributeValue( CikayueBVO.ZR_SL);
 } 

/** 
* 设置转入数量
*
* @param zr_sl 转入数量
*/
public void setZr_sl ( UFDouble zr_sl) {
this.setAttributeValue( CikayueBVO.ZR_SL,zr_sl);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_CikayueBVO");
  }
  
  public static final String KA_CODE="ka_code";
  public String getKa_code() {
  return (String) this.getAttributeValue( CikayueBVO.KA_CODE);
   } 
  public void setKa_code( String ka_code) {
  this.setAttributeValue( CikayueBVO.KA_CODE,ka_code);
   } 
  
  public static final String STARTDATA="startdata";
  public String getStartdata() {
  return (String) this.getAttributeValue( CikayueBVO.STARTDATA);
   } 
  public void setStartdata( String startdata) {
  this.setAttributeValue( CikayueBVO.STARTDATA,startdata);
   } 
  
}