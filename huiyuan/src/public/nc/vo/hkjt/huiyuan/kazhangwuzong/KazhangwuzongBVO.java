package nc.vo.hkjt.huiyuan.kazhangwuzong;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KazhangwuzongBVO extends SuperVO {
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
*一级大卡
*/
public static final String DAKA1="daka1";
/**
*三级大卡
*/
public static final String DAKA3="daka3";
/**
*会员卡可用余额
*/
public static final String KAYUE="kayue";
/**
*上层单据主键
*/
public static final String PK_HK_HUIYUAN_KAZHANGWUZONG="pk_hk_huiyuan_kazhangwuzong";
/**
*会员账务总表子pk
*/
public static final String PK_HK_HUIYUAN_KAZHANGWUZONG_B="pk_hk_huiyuan_kazhangwuzong_b";
/**
*日期
*/
public static final String RQ="rq";
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
*验证
*/
public static final String YANZHENG="yanzheng";
/**
*总余额
*/
public static final String YUE="yue";
/**
*金贵余额
*/
public static final String YUE_JG="yue_jg";
/**
*休眠卡余额
*/
public static final String YUE_XM="yue_xm";
/**
*应付总余额
*/
public static final String YUE_YF="yue_yf";
/**
*作废卡余额
*/
public static final String ZUOFEI="zuofei";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( KazhangwuzongBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( KazhangwuzongBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( KazhangwuzongBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( KazhangwuzongBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( KazhangwuzongBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( KazhangwuzongBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( KazhangwuzongBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取一级大卡
*
* @return 一级大卡
*/
public UFDouble getDaka1 () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.DAKA1);
 } 

/** 
* 设置一级大卡
*
* @param daka1 一级大卡
*/
public void setDaka1 ( UFDouble daka1) {
this.setAttributeValue( KazhangwuzongBVO.DAKA1,daka1);
 } 

/** 
* 获取三级大卡
*
* @return 三级大卡
*/
public UFDouble getDaka3 () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.DAKA3);
 } 

/** 
* 设置三级大卡
*
* @param daka3 三级大卡
*/
public void setDaka3 ( UFDouble daka3) {
this.setAttributeValue( KazhangwuzongBVO.DAKA3,daka3);
 } 

/** 
* 获取会员卡可用余额
*
* @return 会员卡可用余额
*/
public UFDouble getKayue () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.KAYUE);
 } 

/** 
* 设置会员卡可用余额
*
* @param kayue 会员卡可用余额
*/
public void setKayue ( UFDouble kayue) {
this.setAttributeValue( KazhangwuzongBVO.KAYUE,kayue);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_kazhangwuzong () {
return (String) this.getAttributeValue( KazhangwuzongBVO.PK_HK_HUIYUAN_KAZHANGWUZONG);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_kazhangwuzong 上层单据主键
*/
public void setPk_hk_huiyuan_kazhangwuzong ( String pk_hk_huiyuan_kazhangwuzong) {
this.setAttributeValue( KazhangwuzongBVO.PK_HK_HUIYUAN_KAZHANGWUZONG,pk_hk_huiyuan_kazhangwuzong);
 } 

/** 
* 获取会员账务总表子pk
*
* @return 会员账务总表子pk
*/
public String getPk_hk_huiyuan_kazhangwuzong_b () {
return (String) this.getAttributeValue( KazhangwuzongBVO.PK_HK_HUIYUAN_KAZHANGWUZONG_B);
 } 

/** 
* 设置会员账务总表子pk
*
* @param pk_hk_huiyuan_kazhangwuzong_b 会员账务总表子pk
*/
public void setPk_hk_huiyuan_kazhangwuzong_b ( String pk_hk_huiyuan_kazhangwuzong_b) {
this.setAttributeValue( KazhangwuzongBVO.PK_HK_HUIYUAN_KAZHANGWUZONG_B,pk_hk_huiyuan_kazhangwuzong_b);
 } 

/** 
* 获取日期
*
* @return 日期
*/
public String getRq () {
return (String) this.getAttributeValue( KazhangwuzongBVO.RQ);
 } 

/** 
* 设置日期
*
* @param rq 日期
*/
public void setRq ( String rq) {
this.setAttributeValue( KazhangwuzongBVO.RQ,rq);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KazhangwuzongBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KazhangwuzongBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( KazhangwuzongBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( KazhangwuzongBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( KazhangwuzongBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( KazhangwuzongBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( KazhangwuzongBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( KazhangwuzongBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( KazhangwuzongBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取验证
*
* @return 验证
*/
public UFDouble getYanzheng () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.YANZHENG);
 } 

/** 
* 设置验证
*
* @param yanzheng 验证
*/
public void setYanzheng ( UFDouble yanzheng) {
this.setAttributeValue( KazhangwuzongBVO.YANZHENG,yanzheng);
 } 

/** 
* 获取总余额
*
* @return 总余额
*/
public UFDouble getYue () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.YUE);
 } 

/** 
* 设置总余额
*
* @param yue 总余额
*/
public void setYue ( UFDouble yue) {
this.setAttributeValue( KazhangwuzongBVO.YUE,yue);
 } 

/** 
* 获取金贵余额
*
* @return 金贵余额
*/
public UFDouble getYue_jg () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.YUE_JG);
 } 

/** 
* 设置金贵余额
*
* @param yue_jg 金贵余额
*/
public void setYue_jg ( UFDouble yue_jg) {
this.setAttributeValue( KazhangwuzongBVO.YUE_JG,yue_jg);
 } 

/** 
* 获取休眠卡余额
*
* @return 休眠卡余额
*/
public UFDouble getYue_xm () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.YUE_XM);
 } 

/** 
* 设置休眠卡余额
*
* @param yue_xm 休眠卡余额
*/
public void setYue_xm ( UFDouble yue_xm) {
this.setAttributeValue( KazhangwuzongBVO.YUE_XM,yue_xm);
 } 

/** 
* 获取应付总余额
*
* @return 应付总余额
*/
public UFDouble getYue_yf () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.YUE_YF);
 } 

/** 
* 设置应付总余额
*
* @param yue_yf 应付总余额
*/
public void setYue_yf ( UFDouble yue_yf) {
this.setAttributeValue( KazhangwuzongBVO.YUE_YF,yue_yf);
 } 

/** 
* 获取作废卡余额
*
* @return 作废卡余额
*/
public UFDouble getZuofei () {
return (UFDouble) this.getAttributeValue( KazhangwuzongBVO.ZUOFEI);
 } 

/** 
* 设置作废卡余额
*
* @param zuofei 作废卡余额
*/
public void setZuofei ( UFDouble zuofei) {
this.setAttributeValue( KazhangwuzongBVO.ZUOFEI,zuofei);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KazhangwuzongBVO");
  }
}