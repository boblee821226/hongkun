package nc.vo.hkjt.srgk.huiguan.cctz;

import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CctzBVO extends SuperVO {
/**
*部门
*/
public static final String BM_PK="bm_pk";
/**
*差错日期
*/
public static final String CC_DATE="cc_date";
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
*差错类型
*/
public static final String LEIXING="leixing";
/**
*上层单据主键
*/
public static final String PK_HK_SRGK_HG_CCTZ="pk_hk_srgk_hg_cctz";
/**
*子表主键
*/
public static final String PK_HK_SRGK_HG_CCTZ_B="pk_hk_srgk_hg_cctz_b";
/**
*事项
*/
public static final String SHIXIANG="shixiang";
/**
*时间戳
*/
public static final String TS="ts";
/**
*调整日期
*/
public static final String TZ_DATE="tz_date";
/**
*调整科目数据1
*/
public static final String TZ_KM_DATA_1="tz_km_data_1";
/**
*调整科目数据2
*/
public static final String TZ_KM_DATA_2="tz_km_data_2";
/**
*调整科目明细1
*/
public static final String TZ_KM_INFO_1="tz_km_info_1";
/**
*调整科目明细2
*/
public static final String TZ_KM_INFO_2="tz_km_info_2";
/**
*调整科目结账方式1
*/
public static final String TZ_KM_JZFS_1="tz_km_jzfs_1";
/**
*调整科目结账方式2
*/
public static final String TZ_KM_JZFS_2="tz_km_jzfs_2";
/**
*调整科目收入项目1
*/
public static final String TZ_KM_SRXM_1="tz_km_srxm_1";
/**
*调整科目收入项目2
*/
public static final String TZ_KM_SRXM_2="tz_km_srxm_2";
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
*原因
*/
public static final String YUANYIN="yuanyin";
public static final String TZ_KM_SRXM_TYPE1 = "tz_km_srxm_type1";
public static final String TZ_KM_SRXM_TYPE2 = "tz_km_srxm_type2";

public String getTz_km_srxm_type1() {
	return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE1);
}

public void setTz_km_srxm_type1(String tz_km_srxm_type1) {
	this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE1, tz_km_srxm_type1);
}

public String getTz_km_srxm_type2() {
	return (String) this.getAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE2);
}

public void setTz_km_srxm_type2(String tz_km_srxm_type2) {
	this.setAttributeValue(SgshujuBVO.TZ_KM_SRXM_TYPE2, tz_km_srxm_type2);
}
/** 
* 获取部门
*
* @return 部门
*/
public String getBm_pk () {
return (String) this.getAttributeValue( CctzBVO.BM_PK);
 } 

/** 
* 设置部门
*
* @param bm_pk 部门
*/
public void setBm_pk ( String bm_pk) {
this.setAttributeValue( CctzBVO.BM_PK,bm_pk);
 } 

/** 
* 获取差错日期
*
* @return 差错日期
*/
public UFDate getCc_date () {
return (UFDate) this.getAttributeValue( CctzBVO.CC_DATE);
 } 

/** 
* 设置差错日期
*
* @param cc_date 差错日期
*/
public void setCc_date ( UFDate cc_date) {
this.setAttributeValue( CctzBVO.CC_DATE,cc_date);
 } 

/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( CctzBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( CctzBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( CctzBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( CctzBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( CctzBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( CctzBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( CctzBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( CctzBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( CctzBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( CctzBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( CctzBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( CctzBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取差错类型
*
* @return 差错类型
* @see String
*/
public String getLeixing () {
return (String) this.getAttributeValue( CctzBVO.LEIXING);
 } 

/** 
* 设置差错类型
*
* @param leixing 差错类型
* @see String
*/
public void setLeixing ( String leixing) {
this.setAttributeValue( CctzBVO.LEIXING,leixing);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_srgk_hg_cctz () {
return (String) this.getAttributeValue( CctzBVO.PK_HK_SRGK_HG_CCTZ);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_srgk_hg_cctz 上层单据主键
*/
public void setPk_hk_srgk_hg_cctz ( String pk_hk_srgk_hg_cctz) {
this.setAttributeValue( CctzBVO.PK_HK_SRGK_HG_CCTZ,pk_hk_srgk_hg_cctz);
 } 

/** 
* 获取子表主键
*
* @return 子表主键
*/
public String getPk_hk_srgk_hg_cctz_b () {
return (String) this.getAttributeValue( CctzBVO.PK_HK_SRGK_HG_CCTZ_B);
 } 

/** 
* 设置子表主键
*
* @param pk_hk_srgk_hg_cctz_b 子表主键
*/
public void setPk_hk_srgk_hg_cctz_b ( String pk_hk_srgk_hg_cctz_b) {
this.setAttributeValue( CctzBVO.PK_HK_SRGK_HG_CCTZ_B,pk_hk_srgk_hg_cctz_b);
 } 

/** 
* 获取事项
*
* @return 事项
*/
public String getShixiang () {
return (String) this.getAttributeValue( CctzBVO.SHIXIANG);
 } 

/** 
* 设置事项
*
* @param shixiang 事项
*/
public void setShixiang ( String shixiang) {
this.setAttributeValue( CctzBVO.SHIXIANG,shixiang);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( CctzBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( CctzBVO.TS,ts);
 } 

/** 
* 获取调整日期
*
* @return 调整日期
*/
public UFDate getTz_date () {
return (UFDate) this.getAttributeValue( CctzBVO.TZ_DATE);
 } 

/** 
* 设置调整日期
*
* @param tz_date 调整日期
*/
public void setTz_date ( UFDate tz_date) {
this.setAttributeValue( CctzBVO.TZ_DATE,tz_date);
 } 

/** 
* 获取调整科目数据1
*
* @return 调整科目数据1
*/
public UFDouble getTz_km_data_1 () {
return (UFDouble) this.getAttributeValue( CctzBVO.TZ_KM_DATA_1);
 } 

/** 
* 设置调整科目数据1
*
* @param tz_km_data_1 调整科目数据1
*/
public void setTz_km_data_1 ( UFDouble tz_km_data_1) {
this.setAttributeValue( CctzBVO.TZ_KM_DATA_1,tz_km_data_1);
 } 

/** 
* 获取调整科目数据2
*
* @return 调整科目数据2
*/
public UFDouble getTz_km_data_2 () {
return (UFDouble) this.getAttributeValue( CctzBVO.TZ_KM_DATA_2);
 } 

/** 
* 设置调整科目数据2
*
* @param tz_km_data_2 调整科目数据2
*/
public void setTz_km_data_2 ( UFDouble tz_km_data_2) {
this.setAttributeValue( CctzBVO.TZ_KM_DATA_2,tz_km_data_2);
 } 

/** 
* 获取调整科目明细1
*
* @return 调整科目明细1
*/
public String getTz_km_info_1 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_INFO_1);
 } 

/** 
* 设置调整科目明细1
*
* @param tz_km_info_1 调整科目明细1
*/
public void setTz_km_info_1 ( String tz_km_info_1) {
this.setAttributeValue( CctzBVO.TZ_KM_INFO_1,tz_km_info_1);
 } 

/** 
* 获取调整科目明细2
*
* @return 调整科目明细2
*/
public String getTz_km_info_2 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_INFO_2);
 } 

/** 
* 设置调整科目明细2
*
* @param tz_km_info_2 调整科目明细2
*/
public void setTz_km_info_2 ( String tz_km_info_2) {
this.setAttributeValue( CctzBVO.TZ_KM_INFO_2,tz_km_info_2);
 } 

/** 
* 获取调整科目结账方式1
*
* @return 调整科目结账方式1
*/
public String getTz_km_jzfs_1 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_JZFS_1);
 } 

/** 
* 设置调整科目结账方式1
*
* @param tz_km_jzfs_1 调整科目结账方式1
*/
public void setTz_km_jzfs_1 ( String tz_km_jzfs_1) {
this.setAttributeValue( CctzBVO.TZ_KM_JZFS_1,tz_km_jzfs_1);
 } 

/** 
* 获取调整科目结账方式2
*
* @return 调整科目结账方式2
*/
public String getTz_km_jzfs_2 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_JZFS_2);
 } 

/** 
* 设置调整科目结账方式2
*
* @param tz_km_jzfs_2 调整科目结账方式2
*/
public void setTz_km_jzfs_2 ( String tz_km_jzfs_2) {
this.setAttributeValue( CctzBVO.TZ_KM_JZFS_2,tz_km_jzfs_2);
 } 

/** 
* 获取调整科目收入项目1
*
* @return 调整科目收入项目1
*/
public String getTz_km_srxm_1 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_SRXM_1);
 } 

/** 
* 设置调整科目收入项目1
*
* @param tz_km_srxm_1 调整科目收入项目1
*/
public void setTz_km_srxm_1 ( String tz_km_srxm_1) {
this.setAttributeValue( CctzBVO.TZ_KM_SRXM_1,tz_km_srxm_1);
 } 

/** 
* 获取调整科目收入项目2
*
* @return 调整科目收入项目2
*/
public String getTz_km_srxm_2 () {
return (String) this.getAttributeValue( CctzBVO.TZ_KM_SRXM_2);
 } 

/** 
* 设置调整科目收入项目2
*
* @param tz_km_srxm_2 调整科目收入项目2
*/
public void setTz_km_srxm_2 ( String tz_km_srxm_2) {
this.setAttributeValue( CctzBVO.TZ_KM_SRXM_2,tz_km_srxm_2);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( CctzBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( CctzBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( CctzBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( CctzBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( CctzBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( CctzBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( CctzBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( CctzBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( CctzBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( CctzBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( CctzBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( CctzBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( CctzBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( CctzBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( CctzBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( CctzBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( CctzBVO.VROWNO,vrowno);
 } 

/** 
* 获取上次单据号
*
* @return 上次单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( CctzBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上次单据号
*
* @param vsourcebillcode 上次单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( CctzBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取原因
*
* @return 原因
*/
public String getYuanyin () {
return (String) this.getAttributeValue( CctzBVO.YUANYIN);
 } 

/** 
* 设置原因
*
* @param yuanyin 原因
*/
public void setYuanyin ( String yuanyin) {
this.setAttributeValue( CctzBVO.YUANYIN,yuanyin);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_CctzBVO");
  }
}