package nc.vo.hkjt.huiyuan.kayue;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KayueBVO extends SuperVO {
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
*差额
*/
public static final String CHAE="chae";
/**
*充值
*/
public static final String CHONGZHI="chongzhi";
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
*开卡店
*/
public static final String KA_DIAN="ka_dian";
/**
*卡张数
*/
public static final String KA_NUM="ka_num";
/**
*卡pk
*/
public static final String KA_PK="ka_pk";
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
public static final String PK_HK_HUIYUAN_KAYUE="pk_hk_huiyuan_kayue";
/**
*会员余额子表pk
*/
public static final String PK_HK_HUIYUAN_KAYUE_B="pk_hk_huiyuan_kayue_b";
/**
*期初
*/
public static final String QICHU="qichu";
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
*消费
*/
public static final String XIAOFEI="xiaofei";
/**
*余额
*/
public static final String YUE="yue";
/**
*金贵余额
*/
public static final String YUE_JG="yue_jg";
/**
*转出
*/
public static final String ZHUANCHU="zhuanchu";
/**
*转入
*/
public static final String ZHUANRU="zhuanru";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KayueBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KayueBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KayueBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KayueBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KayueBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KayueBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取差额
*
* @return 差额
*/
public UFDouble getChae () {
return (UFDouble) this.getAttributeValue( KayueBVO.CHAE);
 } 

/** 
* 设置差额
*
* @param chae 差额
*/
public void setChae ( UFDouble chae) {
this.setAttributeValue( KayueBVO.CHAE,chae);
 } 

/** 
* 获取充值
*
* @return 充值
*/
public UFDouble getChongzhi () {
return (UFDouble) this.getAttributeValue( KayueBVO.CHONGZHI);
 } 

/** 
* 设置充值
*
* @param chongzhi 充值
*/
public void setChongzhi ( UFDouble chongzhi) {
this.setAttributeValue( KayueBVO.CHONGZHI,chongzhi);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KayueBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KayueBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KayueBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KayueBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KayueBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KayueBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取卡code
*
* @return 卡code
*/
public String getKa_code () {
return (String) this.getAttributeValue( KayueBVO.KA_CODE);
 } 

/** 
* 设置卡code
*
* @param ka_code 卡code
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( KayueBVO.KA_CODE,ka_code);
 } 

/** 
* 获取开卡店
*
* @return 开卡店
*/
public String getKa_dian () {
return (String) this.getAttributeValue( KayueBVO.KA_DIAN);
 } 

/** 
* 设置开卡店
*
* @param ka_dian 开卡店
*/
public void setKa_dian ( String ka_dian) {
this.setAttributeValue( KayueBVO.KA_DIAN,ka_dian);
 } 

/** 
* 获取卡张数
*
* @return 卡张数
*/
public Integer getKa_num () {
return (Integer) this.getAttributeValue( KayueBVO.KA_NUM);
 } 

/** 
* 设置卡张数
*
* @param ka_num 卡张数
*/
public void setKa_num ( Integer ka_num) {
this.setAttributeValue( KayueBVO.KA_NUM,ka_num);
 } 

/** 
* 获取卡pk
*
* @return 卡pk
*/
public String getKa_pk () {
return (String) this.getAttributeValue( KayueBVO.KA_PK);
 } 

/** 
* 设置卡pk
*
* @param ka_pk 卡pk
*/
public void setKa_pk ( String ka_pk) {
this.setAttributeValue( KayueBVO.KA_PK,ka_pk);
 } 

/** 
* 获取卡型code
*
* @return 卡型code
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( KayueBVO.KAXING_CODE);
 } 

/** 
* 设置卡型code
*
* @param kaxing_code 卡型code
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( KayueBVO.KAXING_CODE,kaxing_code);
 } 

/** 
* 获取卡型name
*
* @return 卡型name
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( KayueBVO.KAXING_NAME);
 } 

/** 
* 设置卡型name
*
* @param kaxing_name 卡型name
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( KayueBVO.KAXING_NAME,kaxing_name);
 } 

/** 
* 获取卡型pk
*
* @return 卡型pk
*/
public String getKaxing_pk () {
return (String) this.getAttributeValue( KayueBVO.KAXING_PK);
 } 

/** 
* 设置卡型pk
*
* @param kaxing_pk 卡型pk
*/
public void setKaxing_pk ( String kaxing_pk) {
this.setAttributeValue( KayueBVO.KAXING_PK,kaxing_pk);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_kayue () {
return (String) this.getAttributeValue( KayueBVO.PK_HK_HUIYUAN_KAYUE);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_kayue 上层单据主键
*/
public void setPk_hk_huiyuan_kayue ( String pk_hk_huiyuan_kayue) {
this.setAttributeValue( KayueBVO.PK_HK_HUIYUAN_KAYUE,pk_hk_huiyuan_kayue);
 } 

/** 
* 获取会员余额子表pk
*
* @return 会员余额子表pk
*/
public String getPk_hk_huiyuan_kayue_b () {
return (String) this.getAttributeValue( KayueBVO.PK_HK_HUIYUAN_KAYUE_B);
 } 

/** 
* 设置会员余额子表pk
*
* @param pk_hk_huiyuan_kayue_b 会员余额子表pk
*/
public void setPk_hk_huiyuan_kayue_b ( String pk_hk_huiyuan_kayue_b) {
this.setAttributeValue( KayueBVO.PK_HK_HUIYUAN_KAYUE_B,pk_hk_huiyuan_kayue_b);
 } 

/** 
* 获取期初
*
* @return 期初
*/
public UFDouble getQichu () {
return (UFDouble) this.getAttributeValue( KayueBVO.QICHU);
 } 

/** 
* 设置期初
*
* @param qichu 期初
*/
public void setQichu ( UFDouble qichu) {
this.setAttributeValue( KayueBVO.QICHU,qichu);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KayueBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KayueBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KayueBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KayueBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KayueBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KayueBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KayueBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KayueBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KayueBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KayueBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KayueBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KayueBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KayueBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KayueBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KayueBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KayueBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KayueBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KayueBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KayueBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KayueBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KayueBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取消费
*
* @return 消费
*/
public UFDouble getXiaofei () {
return (UFDouble) this.getAttributeValue( KayueBVO.XIAOFEI);
 } 

/** 
* 设置消费
*
* @param xiaofei 消费
*/
public void setXiaofei ( UFDouble xiaofei) {
this.setAttributeValue( KayueBVO.XIAOFEI,xiaofei);
 } 

/** 
* 获取余额
*
* @return 余额
*/
public UFDouble getYue () {
return (UFDouble) this.getAttributeValue( KayueBVO.YUE);
 } 

/** 
* 设置余额
*
* @param yue 余额
*/
public void setYue ( UFDouble yue) {
this.setAttributeValue( KayueBVO.YUE,yue);
 } 

/** 
* 获取金贵余额
*
* @return 金贵余额
*/
public UFDouble getYue_jg () {
return (UFDouble) this.getAttributeValue( KayueBVO.YUE_JG);
 } 

/** 
* 设置金贵余额
*
* @param yue_jg 金贵余额
*/
public void setYue_jg ( UFDouble yue_jg) {
this.setAttributeValue( KayueBVO.YUE_JG,yue_jg);
 } 

/** 
* 获取转出
*
* @return 转出
*/
public UFDouble getZhuanchu () {
return (UFDouble) this.getAttributeValue( KayueBVO.ZHUANCHU);
 } 

/** 
* 设置转出
*
* @param zhuanchu 转出
*/
public void setZhuanchu ( UFDouble zhuanchu) {
this.setAttributeValue( KayueBVO.ZHUANCHU,zhuanchu);
 } 

/** 
* 获取转入
*
* @return 转入
*/
public UFDouble getZhuanru () {
return (UFDouble) this.getAttributeValue( KayueBVO.ZHUANRU);
 } 

/** 
* 设置转入
*
* @param zhuanru 转入
*/
public void setZhuanru ( UFDouble zhuanru) {
this.setAttributeValue( KayueBVO.ZHUANRU,zhuanru);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KayueBVO");
  }
}