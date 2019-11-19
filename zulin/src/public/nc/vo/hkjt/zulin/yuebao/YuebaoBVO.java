package nc.vo.hkjt.zulin.yuebao;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class YuebaoBVO extends SuperVO {
/**
*本期收款金额
*/
public UFDouble bqskje;
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
*单价
*/
public UFDouble danjia;
/**
*当月收入确认金额
*/
public UFDouble dysrqrje;
/**
*当月收入确认天数
*/
public UFDouble dysrqrts;
/**
*类型
*/
public String itype;
/**
*计算结束日期
*/
public UFDate jsjsrq;
/**
*计算开始日期
*/
public UFDate jsksrq;
/**
*出租面积
*/
public UFDouble mianji;
/**
*客户
*/
public String pk_cutomer;
/**
*上层单据主键
*/
public static final String PK_HK_ZULIN_YUEBAO="pk_hk_zulin_yuebao";
/**
*租赁月报子表pk
*/
public static final String PK_HK_ZULIN_YUEBAO_B="pk_hk_zulin_yuebao_b";
/**
*期初预收款余额
*/
public UFDouble qcyskye;
/**
*期末预收款余额
*/
public UFDouble qmyskye;
/**
*区域
*/
public String quyu;
/**
*房间号
*/
public String roomno;
/**
*收入项目
*/
public String srxm;
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
* 获取本期收款金额
*
* @return 本期收款金额
*/
public UFDouble getBqskje () {
return this.bqskje;
 } 

/** 
* 设置本期收款金额
*
* @param bqskje 本期收款金额
*/
public void setBqskje ( UFDouble bqskje) {
this.bqskje=bqskje;
 } 

/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( YuebaoBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( YuebaoBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( YuebaoBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( YuebaoBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( YuebaoBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( YuebaoBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( YuebaoBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( YuebaoBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( YuebaoBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( YuebaoBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( YuebaoBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( YuebaoBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取单价
*
* @return 单价
*/
public UFDouble getDanjia () {
return this.danjia;
 } 

/** 
* 设置单价
*
* @param danjia 单价
*/
public void setDanjia ( UFDouble danjia) {
this.danjia=danjia;
 } 

/** 
* 获取当月收入确认金额
*
* @return 当月收入确认金额
*/
public UFDouble getDysrqrje () {
return this.dysrqrje;
 } 

/** 
* 设置当月收入确认金额
*
* @param dysrqrje 当月收入确认金额
*/
public void setDysrqrje ( UFDouble dysrqrje) {
this.dysrqrje=dysrqrje;
 } 

/** 
* 获取当月收入确认天数
*
* @return 当月收入确认天数
*/
public UFDouble getDysrqrts () {
return this.dysrqrts;
 } 

/** 
* 设置当月收入确认天数
*
* @param dysrqrts 当月收入确认天数
*/
public void setDysrqrts ( UFDouble dysrqrts) {
this.dysrqrts=dysrqrts;
 } 

/** 
* 获取类型
*
* @return 类型
*/
public String getItype () {
return this.itype;
 } 

/** 
* 设置类型
*
* @param itype 类型
*/
public void setItype ( String itype) {
this.itype=itype;
 } 

/** 
* 获取计算结束日期
*
* @return 计算结束日期
*/
public UFDate getJsjsrq () {
return this.jsjsrq;
 } 

/** 
* 设置计算结束日期
*
* @param jsjsrq 计算结束日期
*/
public void setJsjsrq ( UFDate jsjsrq) {
this.jsjsrq=jsjsrq;
 } 

/** 
* 获取计算开始日期
*
* @return 计算开始日期
*/
public UFDate getJsksrq () {
return this.jsksrq;
 } 

/** 
* 设置计算开始日期
*
* @param jsksrq 计算开始日期
*/
public void setJsksrq ( UFDate jsksrq) {
this.jsksrq=jsksrq;
 } 

/** 
* 获取出租面积
*
* @return 出租面积
*/
public UFDouble getMianji () {
return this.mianji;
 } 

/** 
* 设置出租面积
*
* @param mianji 出租面积
*/
public void setMianji ( UFDouble mianji) {
this.mianji=mianji;
 } 

/** 
* 获取客户
*
* @return 客户
*/
public String getPk_cutomer () {
return this.pk_cutomer;
 } 

/** 
* 设置客户
*
* @param pk_cutomer 客户
*/
public void setPk_cutomer ( String pk_cutomer) {
this.pk_cutomer=pk_cutomer;
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_zulin_yuebao () {
return (String) this.getAttributeValue( YuebaoBVO.PK_HK_ZULIN_YUEBAO);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_zulin_yuebao 上层单据主键
*/
public void setPk_hk_zulin_yuebao ( String pk_hk_zulin_yuebao) {
this.setAttributeValue( YuebaoBVO.PK_HK_ZULIN_YUEBAO,pk_hk_zulin_yuebao);
 } 

/** 
* 获取租赁月报子表pk
*
* @return 租赁月报子表pk
*/
public String getPk_hk_zulin_yuebao_b () {
return (String) this.getAttributeValue( YuebaoBVO.PK_HK_ZULIN_YUEBAO_B);
 } 

/** 
* 设置租赁月报子表pk
*
* @param pk_hk_zulin_yuebao_b 租赁月报子表pk
*/
public void setPk_hk_zulin_yuebao_b ( String pk_hk_zulin_yuebao_b) {
this.setAttributeValue( YuebaoBVO.PK_HK_ZULIN_YUEBAO_B,pk_hk_zulin_yuebao_b);
 } 

/** 
* 获取期初预收款余额
*
* @return 期初预收款余额
*/
public UFDouble getQcyskye () {
return this.qcyskye;
 } 

/** 
* 设置期初预收款余额
*
* @param qcyskye 期初预收款余额
*/
public void setQcyskye ( UFDouble qcyskye) {
this.qcyskye=qcyskye;
 } 

/** 
* 获取期末预收款余额
*
* @return 期末预收款余额
*/
public UFDouble getQmyskye () {
return this.qmyskye;
 } 

/** 
* 设置期末预收款余额
*
* @param qmyskye 期末预收款余额
*/
public void setQmyskye ( UFDouble qmyskye) {
this.qmyskye=qmyskye;
 } 

/** 
* 获取区域
*
* @return 区域
*/
public String getQuyu () {
return this.quyu;
 } 

/** 
* 设置区域
*
* @param quyu 区域
*/
public void setQuyu ( String quyu) {
this.quyu=quyu;
 } 

/** 
* 获取房间号
*
* @return 房间号
*/
public String getRoomno () {
return this.roomno;
 } 

/** 
* 设置房间号
*
* @param roomno 房间号
*/
public void setRoomno ( String roomno) {
this.roomno=roomno;
 } 

/** 
* 获取收入项目
*
* @return 收入项目
*/
public String getSrxm () {
return this.srxm;
 } 

/** 
* 设置收入项目
*
* @param srxm 收入项目
*/
public void setSrxm ( String srxm) {
this.srxm=srxm;
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( YuebaoBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( YuebaoBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( YuebaoBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( YuebaoBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( YuebaoBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( YuebaoBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( YuebaoBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( YuebaoBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( YuebaoBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( YuebaoBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( YuebaoBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( YuebaoBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( YuebaoBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( YuebaoBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( YuebaoBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( YuebaoBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( YuebaoBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( YuebaoBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( YuebaoBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( YuebaoBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( YuebaoBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_zulin_yuebaoBVO");
  }
}