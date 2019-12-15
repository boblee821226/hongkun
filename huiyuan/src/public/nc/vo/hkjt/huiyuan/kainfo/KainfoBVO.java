package nc.vo.hkjt.huiyuan.kainfo;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KainfoBVO extends SuperVO {
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
*卡code
*/
public static final String KA_CODE="ka_code";
/**
*卡金额
*/
public static final String KA_JE="ka_je";
/**
*卡name
*/
public static final String KA_NAME="ka_name";
/**
*卡pk
*/
public static final String KA_PK="ka_pk";
/**
*实收金额
*/
public static final String KA_SS="ka_ss";
/**
*卡余额
*/
public static final String KA_YUE="ka_yue";
/**
*赠送金额
*/
public static final String KA_ZS="ka_zs";
/**
*卡比例
*/
public static final String KABILI="kabili";
/**
*卡型code
*/
public static final String KAXING_CODE="kaxing_code";
/**
*卡型name
*/
public static final String KAXING_NAME="kaxing_name";
/**
*卡型pk
*/
public static final String KAXING_PK="kaxing_pk";
/**
*上层单据主键
*/
public static final String PK_HK_HUIYUAN_KAINFO="pk_hk_huiyuan_kainfo";
/**
*会员信息子表主键
*/
public static final String PK_HK_HUIYUAN_KAINFO_B="pk_hk_huiyuan_kainfo_b";
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
*项目大类
*/
public static final String XMDL="xmdl";
/**
*休眠卡code
*/
public static final String XMKA_CODE="xmka_code";
/**
*休眠卡pk
*/
public static final String XMKA_PK="xmka_pk";
/**
*项目类型
*/
public static final String XMLX="xmlx";
/**
*源卡code
*/
public static final String Y_KA_CODE="y_ka_code";
/**
*源卡金额
*/
public static final String Y_KA_JE="y_ka_je";
/**
*源卡name
*/
public static final String Y_KA_NAME="y_ka_name";
/**
*源卡pk
*/
public static final String Y_KA_PK="y_ka_pk";
/**
*源实收金额
*/
public static final String Y_KA_SS="y_ka_ss";
/**
*源卡余额
*/
public static final String Y_KA_YUE="y_ka_yue";
/**
*源赠送金额
*/
public static final String Y_KA_ZS="y_ka_zs";
/**
*源卡比例
*/
public static final String Y_KABILI="y_kabili";
/**
*源卡型code
*/
public static final String Y_KAXING_CODE="y_kaxing_code";
/**
*源卡型name
*/
public static final String Y_KAXING_NAME="y_kaxing_name";
/**
*源卡型pk
*/
public static final String Y_KAXING_PK="y_kaxing_pk";
/**
*业务时间
*/
public static final String YWSJ="ywsj";
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
return (String) this.getAttributeValue( KainfoBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KainfoBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KainfoBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KainfoBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KainfoBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KainfoBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KainfoBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KainfoBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KainfoBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KainfoBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KainfoBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KainfoBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取卡code
*
* @return 卡code
*/
public String getKa_code () {
return (String) this.getAttributeValue( KainfoBVO.KA_CODE);
 } 

/** 
* 设置卡code
*
* @param ka_code 卡code
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( KainfoBVO.KA_CODE,ka_code);
 } 

/** 
* 获取卡金额
*
* @return 卡金额
*/
public UFDouble getKa_je () {
return (UFDouble) this.getAttributeValue( KainfoBVO.KA_JE);
 } 

/** 
* 设置卡金额
*
* @param ka_je 卡金额
*/
public void setKa_je ( UFDouble ka_je) {
this.setAttributeValue( KainfoBVO.KA_JE,ka_je);
 } 

/** 
* 获取卡name
*
* @return 卡name
*/
public String getKa_name () {
return (String) this.getAttributeValue( KainfoBVO.KA_NAME);
 } 

/** 
* 设置卡name
*
* @param ka_name 卡name
*/
public void setKa_name ( String ka_name) {
this.setAttributeValue( KainfoBVO.KA_NAME,ka_name);
 } 

/** 
* 获取卡pk
*
* @return 卡pk
*/
public String getKa_pk () {
return (String) this.getAttributeValue( KainfoBVO.KA_PK);
 } 

/** 
* 设置卡pk
*
* @param ka_pk 卡pk
*/
public void setKa_pk ( String ka_pk) {
this.setAttributeValue( KainfoBVO.KA_PK,ka_pk);
 } 

/** 
* 获取实收金额
*
* @return 实收金额
*/
public UFDouble getKa_ss () {
return (UFDouble) this.getAttributeValue( KainfoBVO.KA_SS);
 } 

/** 
* 设置实收金额
*
* @param ka_ss 实收金额
*/
public void setKa_ss ( UFDouble ka_ss) {
this.setAttributeValue( KainfoBVO.KA_SS,ka_ss);
 } 

/** 
* 获取卡余额
*
* @return 卡余额
*/
public UFDouble getKa_yue () {
return (UFDouble) this.getAttributeValue( KainfoBVO.KA_YUE);
 } 

/** 
* 设置卡余额
*
* @param ka_yue 卡余额
*/
public void setKa_yue ( UFDouble ka_yue) {
this.setAttributeValue( KainfoBVO.KA_YUE,ka_yue);
 } 

/** 
* 获取赠送金额
*
* @return 赠送金额
*/
public UFDouble getKa_zs () {
return (UFDouble) this.getAttributeValue( KainfoBVO.KA_ZS);
 } 

/** 
* 设置赠送金额
*
* @param ka_zs 赠送金额
*/
public void setKa_zs ( UFDouble ka_zs) {
this.setAttributeValue( KainfoBVO.KA_ZS,ka_zs);
 } 

/** 
* 获取卡比例
*
* @return 卡比例
*/
public UFDouble getKabili () {
return (UFDouble) this.getAttributeValue( KainfoBVO.KABILI);
 } 

/** 
* 设置卡比例
*
* @param kabili 卡比例
*/
public void setKabili ( UFDouble kabili) {
this.setAttributeValue( KainfoBVO.KABILI,kabili);
 } 

/** 
* 获取卡型code
*
* @return 卡型code
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( KainfoBVO.KAXING_CODE);
 } 

/** 
* 设置卡型code
*
* @param kaxing_code 卡型code
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( KainfoBVO.KAXING_CODE,kaxing_code);
 } 

/** 
* 获取卡型name
*
* @return 卡型name
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( KainfoBVO.KAXING_NAME);
 } 

/** 
* 设置卡型name
*
* @param kaxing_name 卡型name
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( KainfoBVO.KAXING_NAME,kaxing_name);
 } 

/** 
* 获取卡型pk
*
* @return 卡型pk
*/
public String getKaxing_pk () {
return (String) this.getAttributeValue( KainfoBVO.KAXING_PK);
 } 

/** 
* 设置卡型pk
*
* @param kaxing_pk 卡型pk
*/
public void setKaxing_pk ( String kaxing_pk) {
this.setAttributeValue( KainfoBVO.KAXING_PK,kaxing_pk);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_kainfo () {
return (String) this.getAttributeValue( KainfoBVO.PK_HK_HUIYUAN_KAINFO);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_kainfo 上层单据主键
*/
public void setPk_hk_huiyuan_kainfo ( String pk_hk_huiyuan_kainfo) {
this.setAttributeValue( KainfoBVO.PK_HK_HUIYUAN_KAINFO,pk_hk_huiyuan_kainfo);
 } 

/** 
* 获取会员信息子表主键
*
* @return 会员信息子表主键
*/
public String getPk_hk_huiyuan_kainfo_b () {
return (String) this.getAttributeValue( KainfoBVO.PK_HK_HUIYUAN_KAINFO_B);
 } 

/** 
* 设置会员信息子表主键
*
* @param pk_hk_huiyuan_kainfo_b 会员信息子表主键
*/
public void setPk_hk_huiyuan_kainfo_b ( String pk_hk_huiyuan_kainfo_b) {
this.setAttributeValue( KainfoBVO.PK_HK_HUIYUAN_KAINFO_B,pk_hk_huiyuan_kainfo_b);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KainfoBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KainfoBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KainfoBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KainfoBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KainfoBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KainfoBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KainfoBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KainfoBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KainfoBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KainfoBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KainfoBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KainfoBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KainfoBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KainfoBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KainfoBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KainfoBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KainfoBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KainfoBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KainfoBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KainfoBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KainfoBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取项目大类
*
* @return 项目大类
*/
public String getXmdl () {
return (String) this.getAttributeValue( KainfoBVO.XMDL);
 } 

/** 
* 设置项目大类
*
* @param xmdl 项目大类
*/
public void setXmdl ( String xmdl) {
this.setAttributeValue( KainfoBVO.XMDL,xmdl);
 } 

/** 
* 获取休眠卡code
*
* @return 休眠卡code
*/
public String getXmka_code () {
return (String) this.getAttributeValue( KainfoBVO.XMKA_CODE);
 } 

/** 
* 设置休眠卡code
*
* @param xmka_code 休眠卡code
*/
public void setXmka_code ( String xmka_code) {
this.setAttributeValue( KainfoBVO.XMKA_CODE,xmka_code);
 } 

/** 
* 获取休眠卡pk
*
* @return 休眠卡pk
*/
public String getXmka_pk () {
return (String) this.getAttributeValue( KainfoBVO.XMKA_PK);
 } 

/** 
* 设置休眠卡pk
*
* @param xmka_pk 休眠卡pk
*/
public void setXmka_pk ( String xmka_pk) {
this.setAttributeValue( KainfoBVO.XMKA_PK,xmka_pk);
 } 

/** 
* 获取项目类型
*
* @return 项目类型
* @see String
*/
public String getXmlx () {
return (String) this.getAttributeValue( KainfoBVO.XMLX);
 } 

/** 
* 设置项目类型
*
* @param xmlx 项目类型
* @see String
*/
public void setXmlx ( String xmlx) {
this.setAttributeValue( KainfoBVO.XMLX,xmlx);
 } 

/** 
* 获取源卡code
*
* @return 源卡code
*/
public String getY_ka_code () {
return (String) this.getAttributeValue( KainfoBVO.Y_KA_CODE);
 } 

/** 
* 设置源卡code
*
* @param y_ka_code 源卡code
*/
public void setY_ka_code ( String y_ka_code) {
this.setAttributeValue( KainfoBVO.Y_KA_CODE,y_ka_code);
 } 

/** 
* 获取源卡金额
*
* @return 源卡金额
*/
public UFDouble getY_ka_je () {
return (UFDouble) this.getAttributeValue( KainfoBVO.Y_KA_JE);
 } 

/** 
* 设置源卡金额
*
* @param y_ka_je 源卡金额
*/
public void setY_ka_je ( UFDouble y_ka_je) {
this.setAttributeValue( KainfoBVO.Y_KA_JE,y_ka_je);
 } 

/** 
* 获取源卡name
*
* @return 源卡name
*/
public String getY_ka_name () {
return (String) this.getAttributeValue( KainfoBVO.Y_KA_NAME);
 } 

/** 
* 设置源卡name
*
* @param y_ka_name 源卡name
*/
public void setY_ka_name ( String y_ka_name) {
this.setAttributeValue( KainfoBVO.Y_KA_NAME,y_ka_name);
 } 

/** 
* 获取源卡pk
*
* @return 源卡pk
*/
public String getY_ka_pk () {
return (String) this.getAttributeValue( KainfoBVO.Y_KA_PK);
 } 

/** 
* 设置源卡pk
*
* @param y_ka_pk 源卡pk
*/
public void setY_ka_pk ( String y_ka_pk) {
this.setAttributeValue( KainfoBVO.Y_KA_PK,y_ka_pk);
 } 

/** 
* 获取源实收金额
*
* @return 源实收金额
*/
public UFDouble getY_ka_ss () {
return (UFDouble) this.getAttributeValue( KainfoBVO.Y_KA_SS);
 } 

/** 
* 设置源实收金额
*
* @param y_ka_ss 源实收金额
*/
public void setY_ka_ss ( UFDouble y_ka_ss) {
this.setAttributeValue( KainfoBVO.Y_KA_SS,y_ka_ss);
 } 

/** 
* 获取源卡余额
*
* @return 源卡余额
*/
public UFDouble getY_ka_yue () {
return (UFDouble) this.getAttributeValue( KainfoBVO.Y_KA_YUE);
 } 

/** 
* 设置源卡余额
*
* @param y_ka_yue 源卡余额
*/
public void setY_ka_yue ( UFDouble y_ka_yue) {
this.setAttributeValue( KainfoBVO.Y_KA_YUE,y_ka_yue);
 } 

/** 
* 获取源赠送金额
*
* @return 源赠送金额
*/
public UFDouble getY_ka_zs () {
return (UFDouble) this.getAttributeValue( KainfoBVO.Y_KA_ZS);
 } 

/** 
* 设置源赠送金额
*
* @param y_ka_zs 源赠送金额
*/
public void setY_ka_zs ( UFDouble y_ka_zs) {
this.setAttributeValue( KainfoBVO.Y_KA_ZS,y_ka_zs);
 } 

/** 
* 获取源卡比例
*
* @return 源卡比例
*/
public UFDouble getY_kabili () {
return (UFDouble) this.getAttributeValue( KainfoBVO.Y_KABILI);
 } 

/** 
* 设置源卡比例
*
* @param y_kabili 源卡比例
*/
public void setY_kabili ( UFDouble y_kabili) {
this.setAttributeValue( KainfoBVO.Y_KABILI,y_kabili);
 } 

/** 
* 获取源卡型code
*
* @return 源卡型code
*/
public String getY_kaxing_code () {
return (String) this.getAttributeValue( KainfoBVO.Y_KAXING_CODE);
 } 

/** 
* 设置源卡型code
*
* @param y_kaxing_code 源卡型code
*/
public void setY_kaxing_code ( String y_kaxing_code) {
this.setAttributeValue( KainfoBVO.Y_KAXING_CODE,y_kaxing_code);
 } 

/** 
* 获取源卡型name
*
* @return 源卡型name
*/
public String getY_kaxing_name () {
return (String) this.getAttributeValue( KainfoBVO.Y_KAXING_NAME);
 } 

/** 
* 设置源卡型name
*
* @param y_kaxing_name 源卡型name
*/
public void setY_kaxing_name ( String y_kaxing_name) {
this.setAttributeValue( KainfoBVO.Y_KAXING_NAME,y_kaxing_name);
 } 

/** 
* 获取源卡型pk
*
* @return 源卡型pk
*/
public String getY_kaxing_pk () {
return (String) this.getAttributeValue( KainfoBVO.Y_KAXING_PK);
 } 

/** 
* 设置源卡型pk
*
* @param y_kaxing_pk 源卡型pk
*/
public void setY_kaxing_pk ( String y_kaxing_pk) {
this.setAttributeValue( KainfoBVO.Y_KAXING_PK,y_kaxing_pk);
 } 

/** 
* 获取业务时间
*
* @return 业务时间
*/
public UFDateTime getYwsj () {
return (UFDateTime) this.getAttributeValue( KainfoBVO.YWSJ);
 } 

/** 
* 设置业务时间
*
* @param ywsj 业务时间
*/
public void setYwsj ( UFDateTime ywsj) {
this.setAttributeValue( KainfoBVO.YWSJ,ywsj);
 } 

/** 
* 获取账单号
*
* @return 账单号
*/
public String getZdh () {
return (String) this.getAttributeValue( KainfoBVO.ZDH);
 } 

/** 
* 设置账单号
*
* @param zdh 账单号
*/
public void setZdh ( String zdh) {
this.setAttributeValue( KainfoBVO.ZDH,zdh);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KainfoBVO");
  }
}