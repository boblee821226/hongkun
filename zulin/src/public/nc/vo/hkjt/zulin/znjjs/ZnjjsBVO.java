package nc.vo.hkjt.zulin.znjjs;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ZnjjsBVO extends SuperVO {
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
*合同行pk
*/
public static final String HT_BID="ht_bid";
/**
*合同号
*/
public static final String HT_CODE="ht_code";
/**
*合同pk
*/
public static final String HT_ID="ht_id";
/**
*合同行号
*/
public static final String HT_ROWNO="ht_rowno";
/**
*应缴费日期
*/
public static final String JF_DATE="jf_date";
/**
*应缴费金额
*/
public static final String JF_MNY="jf_mny";
/**
*缴费通知单行pk
*/
public static final String JFTZD_BID="jftzd_bid";
/**
*缴费通知单pk
*/
public static final String JFTZD_ID="jftzd_id";
/**
*区域
*/
public static final String PK_AREA="pk_area";
/**
*客户
*/
public static final String PK_CUST="pk_cust";
/**
*上层单据主键
*/
public static final String PK_HK_ZULIN_ZNJJS="pk_hk_zulin_znjjs";
/**
*滞纳金计算单子pk
*/
public static final String PK_HK_ZULIN_ZNJJS_B="pk_hk_zulin_znjjs_b";
/**
*房号
*/
public static final String PK_ROOM="pk_room";
/**
*收费项目
*/
public static final String PK_SFXM="pk_sfxm";
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
*自定义项11
*/
public static final String VBDEF11="vbdef11";
/**
*自定义项12
*/
public static final String VBDEF12="vbdef12";
/**
*自定义项13
*/
public static final String VBDEF13="vbdef13";
/**
*自定义项14
*/
public static final String VBDEF14="vbdef14";
/**
*自定义项15
*/
public static final String VBDEF15="vbdef15";
/**
*自定义项16
*/
public static final String VBDEF16="vbdef16";
/**
*自定义项17
*/
public static final String VBDEF17="vbdef17";
/**
*自定义项18
*/
public static final String VBDEF18="vbdef18";
/**
*自定义项19
*/
public static final String VBDEF19="vbdef19";
/**
*自定义项20
*/
public static final String VBDEF20="vbdef20";
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
*比例（‰）
*/
public static final String YQ_BL="yq_bl";
/**
*滞纳金
*/
public static final String YQ_MNY="yq_mny";
/**
*逾期天数
*/
public static final String YQ_NUM="yq_num";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( ZnjjsBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( ZnjjsBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( ZnjjsBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( ZnjjsBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( ZnjjsBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( ZnjjsBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( ZnjjsBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( ZnjjsBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( ZnjjsBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( ZnjjsBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( ZnjjsBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( ZnjjsBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取合同行pk
*
* @return 合同行pk
*/
public String getHt_bid () {
return (String) this.getAttributeValue( ZnjjsBVO.HT_BID);
 } 

/** 
* 设置合同行pk
*
* @param ht_bid 合同行pk
*/
public void setHt_bid ( String ht_bid) {
this.setAttributeValue( ZnjjsBVO.HT_BID,ht_bid);
 } 

/** 
* 获取合同号
*
* @return 合同号
*/
public String getHt_code () {
return (String) this.getAttributeValue( ZnjjsBVO.HT_CODE);
 } 

/** 
* 设置合同号
*
* @param ht_code 合同号
*/
public void setHt_code ( String ht_code) {
this.setAttributeValue( ZnjjsBVO.HT_CODE,ht_code);
 } 

/** 
* 获取合同pk
*
* @return 合同pk
*/
public String getHt_id () {
return (String) this.getAttributeValue( ZnjjsBVO.HT_ID);
 } 

/** 
* 设置合同pk
*
* @param ht_id 合同pk
*/
public void setHt_id ( String ht_id) {
this.setAttributeValue( ZnjjsBVO.HT_ID,ht_id);
 } 

/** 
* 获取合同行号
*
* @return 合同行号
*/
public String getHt_rowno () {
return (String) this.getAttributeValue( ZnjjsBVO.HT_ROWNO);
 } 

/** 
* 设置合同行号
*
* @param ht_rowno 合同行号
*/
public void setHt_rowno ( String ht_rowno) {
this.setAttributeValue( ZnjjsBVO.HT_ROWNO,ht_rowno);
 } 

/** 
* 获取应缴费日期
*
* @return 应缴费日期
*/
public UFDate getJf_date () {
return (UFDate) this.getAttributeValue( ZnjjsBVO.JF_DATE);
 } 

/** 
* 设置应缴费日期
*
* @param jf_date 应缴费日期
*/
public void setJf_date ( UFDate jf_date) {
this.setAttributeValue( ZnjjsBVO.JF_DATE,jf_date);
 } 

/** 
* 获取应缴费金额
*
* @return 应缴费金额
*/
public UFDouble getJf_mny () {
return (UFDouble) this.getAttributeValue( ZnjjsBVO.JF_MNY);
 } 

/** 
* 设置应缴费金额
*
* @param jf_mny 应缴费金额
*/
public void setJf_mny ( UFDouble jf_mny) {
this.setAttributeValue( ZnjjsBVO.JF_MNY,jf_mny);
 } 

/** 
* 获取缴费通知单行pk
*
* @return 缴费通知单行pk
*/
public String getJftzd_bid () {
return (String) this.getAttributeValue( ZnjjsBVO.JFTZD_BID);
 } 

/** 
* 设置缴费通知单行pk
*
* @param jftzd_bid 缴费通知单行pk
*/
public void setJftzd_bid ( String jftzd_bid) {
this.setAttributeValue( ZnjjsBVO.JFTZD_BID,jftzd_bid);
 } 

/** 
* 获取缴费通知单pk
*
* @return 缴费通知单pk
*/
public String getJftzd_id () {
return (String) this.getAttributeValue( ZnjjsBVO.JFTZD_ID);
 } 

/** 
* 设置缴费通知单pk
*
* @param jftzd_id 缴费通知单pk
*/
public void setJftzd_id ( String jftzd_id) {
this.setAttributeValue( ZnjjsBVO.JFTZD_ID,jftzd_id);
 } 

/** 
* 获取区域
*
* @return 区域
*/
public String getPk_area () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_AREA);
 } 

/** 
* 设置区域
*
* @param pk_area 区域
*/
public void setPk_area ( String pk_area) {
this.setAttributeValue( ZnjjsBVO.PK_AREA,pk_area);
 } 

/** 
* 获取客户
*
* @return 客户
*/
public String getPk_cust () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_CUST);
 } 

/** 
* 设置客户
*
* @param pk_cust 客户
*/
public void setPk_cust ( String pk_cust) {
this.setAttributeValue( ZnjjsBVO.PK_CUST,pk_cust);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_zulin_znjjs () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_HK_ZULIN_ZNJJS);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_zulin_znjjs 上层单据主键
*/
public void setPk_hk_zulin_znjjs ( String pk_hk_zulin_znjjs) {
this.setAttributeValue( ZnjjsBVO.PK_HK_ZULIN_ZNJJS,pk_hk_zulin_znjjs);
 } 

/** 
* 获取滞纳金计算单子pk
*
* @return 滞纳金计算单子pk
*/
public String getPk_hk_zulin_znjjs_b () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_HK_ZULIN_ZNJJS_B);
 } 

/** 
* 设置滞纳金计算单子pk
*
* @param pk_hk_zulin_znjjs_b 滞纳金计算单子pk
*/
public void setPk_hk_zulin_znjjs_b ( String pk_hk_zulin_znjjs_b) {
this.setAttributeValue( ZnjjsBVO.PK_HK_ZULIN_ZNJJS_B,pk_hk_zulin_znjjs_b);
 } 

/** 
* 获取房号
*
* @return 房号
*/
public String getPk_room () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_ROOM);
 } 

/** 
* 设置房号
*
* @param pk_room 房号
*/
public void setPk_room ( String pk_room) {
this.setAttributeValue( ZnjjsBVO.PK_ROOM,pk_room);
 } 

/** 
* 获取收费项目
*
* @return 收费项目
*/
public String getPk_sfxm () {
return (String) this.getAttributeValue( ZnjjsBVO.PK_SFXM);
 } 

/** 
* 设置收费项目
*
* @param pk_sfxm 收费项目
*/
public void setPk_sfxm ( String pk_sfxm) {
this.setAttributeValue( ZnjjsBVO.PK_SFXM,pk_sfxm);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( ZnjjsBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( ZnjjsBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( ZnjjsBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( ZnjjsBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( ZnjjsBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( ZnjjsBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( ZnjjsBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( ZnjjsBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( ZnjjsBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( ZnjjsBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( ZnjjsBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( ZnjjsBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vbdef11 自定义项11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( ZnjjsBVO.VBDEF11,vbdef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vbdef12 自定义项12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( ZnjjsBVO.VBDEF12,vbdef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vbdef13 自定义项13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( ZnjjsBVO.VBDEF13,vbdef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vbdef14 自定义项14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( ZnjjsBVO.VBDEF14,vbdef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vbdef15 自定义项15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( ZnjjsBVO.VBDEF15,vbdef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vbdef16 自定义项16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( ZnjjsBVO.VBDEF16,vbdef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vbdef17 自定义项17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( ZnjjsBVO.VBDEF17,vbdef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vbdef18 自定义项18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( ZnjjsBVO.VBDEF18,vbdef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vbdef19 自定义项19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( ZnjjsBVO.VBDEF19,vbdef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( ZnjjsBVO.VBDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vbdef20 自定义项20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( ZnjjsBVO.VBDEF20,vbdef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( ZnjjsBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( ZnjjsBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( ZnjjsBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( ZnjjsBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( ZnjjsBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( ZnjjsBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( ZnjjsBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( ZnjjsBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取比例（‰）
*
* @return 比例（‰）
*/
public UFDouble getYq_bl () {
return (UFDouble) this.getAttributeValue( ZnjjsBVO.YQ_BL);
 } 

/** 
* 设置比例（‰）
*
* @param yq_bl 比例（‰）
*/
public void setYq_bl ( UFDouble yq_bl) {
this.setAttributeValue( ZnjjsBVO.YQ_BL,yq_bl);
 } 

/** 
* 获取滞纳金
*
* @return 滞纳金
*/
public UFDouble getYq_mny () {
return (UFDouble) this.getAttributeValue( ZnjjsBVO.YQ_MNY);
 } 

/** 
* 设置滞纳金
*
* @param yq_mny 滞纳金
*/
public void setYq_mny ( UFDouble yq_mny) {
this.setAttributeValue( ZnjjsBVO.YQ_MNY,yq_mny);
 } 

/** 
* 获取逾期天数
*
* @return 逾期天数
*/
public UFDouble getYq_num () {
return (UFDouble) this.getAttributeValue( ZnjjsBVO.YQ_NUM);
 } 

/** 
* 设置逾期天数
*
* @param yq_num 逾期天数
*/
public void setYq_num ( UFDouble yq_num) {
this.setAttributeValue( ZnjjsBVO.YQ_NUM,yq_num);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_zulin_znjjsBVO");
  }
}