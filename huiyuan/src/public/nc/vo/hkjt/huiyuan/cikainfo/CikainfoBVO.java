package nc.vo.hkjt.huiyuan.cikainfo;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CikainfoBVO extends SuperVO {

	private static final long serialVersionUID = 2503513783647316032L;
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
*消费水号
*/
public static final String CONSUMEWATERNUM="consumewaternum";
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
*充值类型
*/
public static final String CZLX="czlx";
/**
*单价
*/
public static final String DANJIA="danjia";
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
*金额
*/
public static final String JINE="jine";
/**
*卡code
*/
public static final String KA_CODE="ka_code";
/**
*卡name
*/
public static final String KA_NAME="ka_name";
/**
*卡pk
*/
public static final String KA_PK="ka_pk";
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
public static final String PK_HK_HUIYUAN_CIKAINFO="pk_hk_huiyuan_cikainfo";
/**
*次卡信息子表pk
*/
public static final String PK_HK_HUIYUAN_CIKAINFO_B="pk_hk_huiyuan_cikainfo_b";
/**
*数量
*/
public static final String SHULIANG="shuliang";
/**
*次卡开始日期
*/
public static final String STARTDATA="startdata";
/**
*次卡水号
*/
public static final String TIMESCARDWATERNUM="timescardwaternum";
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
*项目类型
*/
public static final String XMLX="xmlx";
/**
*源卡code
*/
public static final String Y_KA_CODE="y_ka_code";
/**
*源卡name
*/
public static final String Y_KA_NAME="y_ka_name";
/**
*源卡pk
*/
public static final String Y_KA_PK="y_ka_pk";
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
return (String) this.getAttributeValue( CikainfoBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( CikainfoBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( CikainfoBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( CikainfoBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( CikainfoBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( CikainfoBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取消费水号
*
* @return 消费水号
*/
public String getConsumewaternum () {
return (String) this.getAttributeValue( CikainfoBVO.CONSUMEWATERNUM);
 } 

/** 
* 设置消费水号
*
* @param consumewaternum 消费水号
*/
public void setConsumewaternum ( String consumewaternum) {
this.setAttributeValue( CikainfoBVO.CONSUMEWATERNUM,consumewaternum);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( CikainfoBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( CikainfoBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( CikainfoBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( CikainfoBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( CikainfoBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( CikainfoBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取充值类型
*
* @return 充值类型
*/
public String getCzlx () {
return (String) this.getAttributeValue( CikainfoBVO.CZLX);
 } 

/** 
* 设置充值类型
*
* @param czlx 充值类型
*/
public void setCzlx ( String czlx) {
this.setAttributeValue( CikainfoBVO.CZLX,czlx);
 } 

/** 
* 获取单价
*
* @return 单价
*/
public UFDouble getDanjia () {
return (UFDouble) this.getAttributeValue( CikainfoBVO.DANJIA);
 } 

/** 
* 设置单价
*
* @param danjia 单价
*/
public void setDanjia ( UFDouble danjia) {
this.setAttributeValue( CikainfoBVO.DANJIA,danjia);
 } 

/** 
* 获取次卡结束日期
*
* @return 次卡结束日期
*/
public String getExpdata () {
return (String) this.getAttributeValue( CikainfoBVO.EXPDATA);
 } 

/** 
* 设置次卡结束日期
*
* @param expdata 次卡结束日期
*/
public void setExpdata ( String expdata) {
this.setAttributeValue( CikainfoBVO.EXPDATA,expdata);
 } 

/** 
* 获取项目编码
*
* @return 项目编码
*/
public String getItemid () {
return (String) this.getAttributeValue( CikainfoBVO.ITEMID);
 } 

/** 
* 设置项目编码
*
* @param itemid 项目编码
*/
public void setItemid ( String itemid) {
this.setAttributeValue( CikainfoBVO.ITEMID,itemid);
 } 

/** 
* 获取项目名称
*
* @return 项目名称
*/
public String getItemname () {
return (String) this.getAttributeValue( CikainfoBVO.ITEMNAME);
 } 

/** 
* 设置项目名称
*
* @param itemname 项目名称
*/
public void setItemname ( String itemname) {
this.setAttributeValue( CikainfoBVO.ITEMNAME,itemname);
 } 

/** 
* 获取金额
*
* @return 金额
*/
public UFDouble getJine () {
return (UFDouble) this.getAttributeValue( CikainfoBVO.JINE);
 } 

/** 
* 设置金额
*
* @param jine 金额
*/
public void setJine ( UFDouble jine) {
this.setAttributeValue( CikainfoBVO.JINE,jine);
 } 

/** 
* 获取卡code
*
* @return 卡code
*/
public String getKa_code () {
return (String) this.getAttributeValue( CikainfoBVO.KA_CODE);
 } 

/** 
* 设置卡code
*
* @param ka_code 卡code
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( CikainfoBVO.KA_CODE,ka_code);
 } 

/** 
* 获取卡name
*
* @return 卡name
*/
public String getKa_name () {
return (String) this.getAttributeValue( CikainfoBVO.KA_NAME);
 } 

/** 
* 设置卡name
*
* @param ka_name 卡name
*/
public void setKa_name ( String ka_name) {
this.setAttributeValue( CikainfoBVO.KA_NAME,ka_name);
 } 

/** 
* 获取卡pk
*
* @return 卡pk
*/
public String getKa_pk () {
return (String) this.getAttributeValue( CikainfoBVO.KA_PK);
 } 

/** 
* 设置卡pk
*
* @param ka_pk 卡pk
*/
public void setKa_pk ( String ka_pk) {
this.setAttributeValue( CikainfoBVO.KA_PK,ka_pk);
 } 

/** 
* 获取卡比例
*
* @return 卡比例
*/
public UFDouble getKabili () {
return (UFDouble) this.getAttributeValue( CikainfoBVO.KABILI);
 } 

/** 
* 设置卡比例
*
* @param kabili 卡比例
*/
public void setKabili ( UFDouble kabili) {
this.setAttributeValue( CikainfoBVO.KABILI,kabili);
 } 

/** 
* 获取卡型code
*
* @return 卡型code
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( CikainfoBVO.KAXING_CODE);
 } 

/** 
* 设置卡型code
*
* @param kaxing_code 卡型code
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( CikainfoBVO.KAXING_CODE,kaxing_code);
 } 

/** 
* 获取卡型name
*
* @return 卡型name
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( CikainfoBVO.KAXING_NAME);
 } 

/** 
* 设置卡型name
*
* @param kaxing_name 卡型name
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( CikainfoBVO.KAXING_NAME,kaxing_name);
 } 

/** 
* 获取卡型pk
*
* @return 卡型pk
*/
public String getKaxing_pk () {
return (String) this.getAttributeValue( CikainfoBVO.KAXING_PK);
 } 

/** 
* 设置卡型pk
*
* @param kaxing_pk 卡型pk
*/
public void setKaxing_pk ( String kaxing_pk) {
this.setAttributeValue( CikainfoBVO.KAXING_PK,kaxing_pk);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_cikainfo () {
return (String) this.getAttributeValue( CikainfoBVO.PK_HK_HUIYUAN_CIKAINFO);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_cikainfo 上层单据主键
*/
public void setPk_hk_huiyuan_cikainfo ( String pk_hk_huiyuan_cikainfo) {
this.setAttributeValue( CikainfoBVO.PK_HK_HUIYUAN_CIKAINFO,pk_hk_huiyuan_cikainfo);
 } 

/** 
* 获取次卡信息子表pk
*
* @return 次卡信息子表pk
*/
public String getPk_hk_huiyuan_cikainfo_b () {
return (String) this.getAttributeValue( CikainfoBVO.PK_HK_HUIYUAN_CIKAINFO_B);
 } 

/** 
* 设置次卡信息子表pk
*
* @param pk_hk_huiyuan_cikainfo_b 次卡信息子表pk
*/
public void setPk_hk_huiyuan_cikainfo_b ( String pk_hk_huiyuan_cikainfo_b) {
this.setAttributeValue( CikainfoBVO.PK_HK_HUIYUAN_CIKAINFO_B,pk_hk_huiyuan_cikainfo_b);
 } 

/** 
* 获取数量
*
* @return 数量
*/
public UFDouble getShuliang () {
return (UFDouble) this.getAttributeValue( CikainfoBVO.SHULIANG);
 } 

/** 
* 设置数量
*
* @param shuliang 数量
*/
public void setShuliang ( UFDouble shuliang) {
this.setAttributeValue( CikainfoBVO.SHULIANG,shuliang);
 } 

/** 
* 获取次卡开始日期
*
* @return 次卡开始日期
*/
public String getStartdata () {
return (String) this.getAttributeValue( CikainfoBVO.STARTDATA);
 } 

/** 
* 设置次卡开始日期
*
* @param startdata 次卡开始日期
*/
public void setStartdata ( String startdata) {
this.setAttributeValue( CikainfoBVO.STARTDATA,startdata);
 } 

/** 
* 获取次卡水号
*
* @return 次卡水号
*/
public String getTimescardwaternum () {
return (String) this.getAttributeValue( CikainfoBVO.TIMESCARDWATERNUM);
 } 

/** 
* 设置次卡水号
*
* @param timescardwaternum 次卡水号
*/
public void setTimescardwaternum ( String timescardwaternum) {
this.setAttributeValue( CikainfoBVO.TIMESCARDWATERNUM,timescardwaternum);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( CikainfoBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( CikainfoBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( CikainfoBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( CikainfoBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( CikainfoBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( CikainfoBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( CikainfoBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( CikainfoBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( CikainfoBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( CikainfoBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( CikainfoBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( CikainfoBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( CikainfoBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( CikainfoBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( CikainfoBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( CikainfoBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( CikainfoBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( CikainfoBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( CikainfoBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( CikainfoBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( CikainfoBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取项目大类
*
* @return 项目大类
*/
public String getXmdl () {
return (String) this.getAttributeValue( CikainfoBVO.XMDL);
 } 

/** 
* 设置项目大类
*
* @param xmdl 项目大类
*/
public void setXmdl ( String xmdl) {
this.setAttributeValue( CikainfoBVO.XMDL,xmdl);
 } 

/** 
* 获取项目类型
*
* @return 项目类型
* @see String
*/
public String getXmlx () {
return (String) this.getAttributeValue( CikainfoBVO.XMLX);
 } 

/** 
* 设置项目类型
*
* @param xmlx 项目类型
* @see String
*/
public void setXmlx ( String xmlx) {
this.setAttributeValue( CikainfoBVO.XMLX,xmlx);
 } 

/** 
* 获取源卡code
*
* @return 源卡code
*/
public String getY_ka_code () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KA_CODE);
 } 

/** 
* 设置源卡code
*
* @param y_ka_code 源卡code
*/
public void setY_ka_code ( String y_ka_code) {
this.setAttributeValue( CikainfoBVO.Y_KA_CODE,y_ka_code);
 } 

/** 
* 获取源卡name
*
* @return 源卡name
*/
public String getY_ka_name () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KA_NAME);
 } 

/** 
* 设置源卡name
*
* @param y_ka_name 源卡name
*/
public void setY_ka_name ( String y_ka_name) {
this.setAttributeValue( CikainfoBVO.Y_KA_NAME,y_ka_name);
 } 

/** 
* 获取源卡pk
*
* @return 源卡pk
*/
public String getY_ka_pk () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KA_PK);
 } 

/** 
* 设置源卡pk
*
* @param y_ka_pk 源卡pk
*/
public void setY_ka_pk ( String y_ka_pk) {
this.setAttributeValue( CikainfoBVO.Y_KA_PK,y_ka_pk);
 } 

/** 
* 获取源卡比例
*
* @return 源卡比例
*/
public UFDouble getY_kabili () {
return (UFDouble) this.getAttributeValue( CikainfoBVO.Y_KABILI);
 } 

/** 
* 设置源卡比例
*
* @param y_kabili 源卡比例
*/
public void setY_kabili ( UFDouble y_kabili) {
this.setAttributeValue( CikainfoBVO.Y_KABILI,y_kabili);
 } 

/** 
* 获取源卡型code
*
* @return 源卡型code
*/
public String getY_kaxing_code () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KAXING_CODE);
 } 

/** 
* 设置源卡型code
*
* @param y_kaxing_code 源卡型code
*/
public void setY_kaxing_code ( String y_kaxing_code) {
this.setAttributeValue( CikainfoBVO.Y_KAXING_CODE,y_kaxing_code);
 } 

/** 
* 获取源卡型name
*
* @return 源卡型name
*/
public String getY_kaxing_name () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KAXING_NAME);
 } 

/** 
* 设置源卡型name
*
* @param y_kaxing_name 源卡型name
*/
public void setY_kaxing_name ( String y_kaxing_name) {
this.setAttributeValue( CikainfoBVO.Y_KAXING_NAME,y_kaxing_name);
 } 

/** 
* 获取源卡型pk
*
* @return 源卡型pk
*/
public String getY_kaxing_pk () {
return (String) this.getAttributeValue( CikainfoBVO.Y_KAXING_PK);
 } 

/** 
* 设置源卡型pk
*
* @param y_kaxing_pk 源卡型pk
*/
public void setY_kaxing_pk ( String y_kaxing_pk) {
this.setAttributeValue( CikainfoBVO.Y_KAXING_PK,y_kaxing_pk);
 } 

/** 
* 获取业务时间
*
* @return 业务时间
*/
public UFDateTime getYwsj () {
return (UFDateTime) this.getAttributeValue( CikainfoBVO.YWSJ);
 } 

/** 
* 设置业务时间
*
* @param ywsj 业务时间
*/
public void setYwsj ( UFDateTime ywsj) {
this.setAttributeValue( CikainfoBVO.YWSJ,ywsj);
 } 

/** 
* 获取账单号
*
* @return 账单号
*/
public String getZdh () {
return (String) this.getAttributeValue( CikainfoBVO.ZDH);
 } 

/** 
* 设置账单号
*
* @param zdh 账单号
*/
public void setZdh ( String zdh) {
this.setAttributeValue( CikainfoBVO.ZDH,zdh);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_CikainfoBVO");
  }
}