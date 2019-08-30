package nc.vo.hkjt.srgk.huiguan.rsbaogao;

import nc.vo.hkjt.srgk.huiguan.sgshuju.SgshujuBVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class RsbaogaoCVO extends SuperVO {
/**
*部门
*/
public static final String BM_PK="bm_pk";
/**
*编码
*/
public static final String CODE="code";
/**
*创建时间
*/
public static final String CREATIONTIME="creationtime";
/**
*创建人
*/
public static final String CREATOR="creator";
/**
*修改时间
*/
public static final String MODIFIEDTIME="modifiedtime";
/**
*修改人
*/
public static final String MODIFIER="modifier";
/**
*名称
*/
public static final String NAME="name";
/**
*集团
*/
public static final String PK_GROUP="pk_group";
/**
*日审报告主表pk
*/
public static final String PK_HK_SRGK_HG_RSBAOGAO="pk_hk_srgk_hg_rsbaogao";
/**
*日审报告子表pk
*/
public static final String PK_HK_SRGK_HG_RSBAOGAO_B="pk_hk_srgk_hg_rsbaogao_b";
/**
*调整表pk
*/
public static final String PK_HK_SRGK_HG_RSBAOGAO_C="pk_hk_srgk_hg_rsbaogao_c";
/**
*组织
*/
public static final String PK_ORG="pk_org";
/**
*组织版本
*/
public static final String PK_ORG_V="pk_org_v";
/**
*时间戳
*/
public static final String TS="ts";
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
*自定义项1
*/
public static final String VDEF1="vdef1";
/**
*自定义项2
*/
public static final String VDEF2="vdef2";
/**
*自定义项3
*/
public static final String VDEF3="vdef3";
/**
*自定义项4
*/
public static final String VDEF4="vdef4";
/**
*自定义项5
*/
public static final String VDEF5="vdef5";
/**
*行号
*/
public static final String VROWNO="vrowno";
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
return (String) this.getAttributeValue( RsbaogaoCVO.BM_PK);
 } 

/** 
* 设置部门
*
* @param bm_pk 部门
*/
public void setBm_pk ( String bm_pk) {
this.setAttributeValue( RsbaogaoCVO.BM_PK,bm_pk);
 } 

/** 
* 获取编码
*
* @return 编码
*/
public String getCode () {
return (String) this.getAttributeValue( RsbaogaoCVO.CODE);
 } 

/** 
* 设置编码
*
* @param code 编码
*/
public void setCode ( String code) {
this.setAttributeValue( RsbaogaoCVO.CODE,code);
 } 

/** 
* 获取创建时间
*
* @return 创建时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( RsbaogaoCVO.CREATIONTIME);
 } 

/** 
* 设置创建时间
*
* @param creationtime 创建时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( RsbaogaoCVO.CREATIONTIME,creationtime);
 } 

/** 
* 获取创建人
*
* @return 创建人
*/
public String getCreator () {
return (String) this.getAttributeValue( RsbaogaoCVO.CREATOR);
 } 

/** 
* 设置创建人
*
* @param creator 创建人
*/
public void setCreator ( String creator) {
this.setAttributeValue( RsbaogaoCVO.CREATOR,creator);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( RsbaogaoCVO.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( RsbaogaoCVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( RsbaogaoCVO.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( RsbaogaoCVO.MODIFIER,modifier);
 } 

/** 
* 获取名称
*
* @return 名称
*/
public String getName () {
return (String) this.getAttributeValue( RsbaogaoCVO.NAME);
 } 

/** 
* 设置名称
*
* @param name 名称
*/
public void setName ( String name) {
this.setAttributeValue( RsbaogaoCVO.NAME,name);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( RsbaogaoCVO.PK_GROUP,pk_group);
 } 

/** 
* 获取日审报告主表pk
*
* @return 日审报告主表pk
*/
public String getPk_hk_srgk_hg_rsbaogao () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO);
 } 

/** 
* 设置日审报告主表pk
*
* @param pk_hk_srgk_hg_rsbaogao 日审报告主表pk
*/
public void setPk_hk_srgk_hg_rsbaogao ( String pk_hk_srgk_hg_rsbaogao) {
this.setAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO,pk_hk_srgk_hg_rsbaogao);
 } 

/** 
* 获取日审报告子表pk
*
* @return 日审报告子表pk
*/
public String getPk_hk_srgk_hg_rsbaogao_b () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO_B);
 } 

/** 
* 设置日审报告子表pk
*
* @param pk_hk_srgk_hg_rsbaogao_b 日审报告子表pk
*/
public void setPk_hk_srgk_hg_rsbaogao_b ( String pk_hk_srgk_hg_rsbaogao_b) {
this.setAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO_B,pk_hk_srgk_hg_rsbaogao_b);
 } 

/** 
* 获取调整表pk
*
* @return 调整表pk
*/
public String getPk_hk_srgk_hg_rsbaogao_c () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO_C);
 } 

/** 
* 设置调整表pk
*
* @param pk_hk_srgk_hg_rsbaogao_c 调整表pk
*/
public void setPk_hk_srgk_hg_rsbaogao_c ( String pk_hk_srgk_hg_rsbaogao_c) {
this.setAttributeValue( RsbaogaoCVO.PK_HK_SRGK_HG_RSBAOGAO_C,pk_hk_srgk_hg_rsbaogao_c);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( RsbaogaoCVO.PK_ORG,pk_org);
 } 

/** 
* 获取组织版本
*
* @return 组织版本
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( RsbaogaoCVO.PK_ORG_V);
 } 

/** 
* 设置组织版本
*
* @param pk_org_v 组织版本
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( RsbaogaoCVO.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( RsbaogaoCVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( RsbaogaoCVO.TS,ts);
 } 

/** 
* 获取调整科目数据1
*
* @return 调整科目数据1
*/
public UFDouble getTz_km_data_1 () {
return (UFDouble) this.getAttributeValue( RsbaogaoCVO.TZ_KM_DATA_1);
 } 

/** 
* 设置调整科目数据1
*
* @param tz_km_data_1 调整科目数据1
*/
public void setTz_km_data_1 ( UFDouble tz_km_data_1) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_DATA_1,tz_km_data_1);
 } 

/** 
* 获取调整科目数据2
*
* @return 调整科目数据2
*/
public UFDouble getTz_km_data_2 () {
return (UFDouble) this.getAttributeValue( RsbaogaoCVO.TZ_KM_DATA_2);
 } 

/** 
* 设置调整科目数据2
*
* @param tz_km_data_2 调整科目数据2
*/
public void setTz_km_data_2 ( UFDouble tz_km_data_2) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_DATA_2,tz_km_data_2);
 } 

/** 
* 获取调整科目明细1
*
* @return 调整科目明细1
*/
public String getTz_km_info_1 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_INFO_1);
 } 

/** 
* 设置调整科目明细1
*
* @param tz_km_info_1 调整科目明细1
*/
public void setTz_km_info_1 ( String tz_km_info_1) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_INFO_1,tz_km_info_1);
 } 

/** 
* 获取调整科目明细2
*
* @return 调整科目明细2
*/
public String getTz_km_info_2 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_INFO_2);
 } 

/** 
* 设置调整科目明细2
*
* @param tz_km_info_2 调整科目明细2
*/
public void setTz_km_info_2 ( String tz_km_info_2) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_INFO_2,tz_km_info_2);
 } 

/** 
* 获取调整科目结账方式1
*
* @return 调整科目结账方式1
*/
public String getTz_km_jzfs_1 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_JZFS_1);
 } 

/** 
* 设置调整科目结账方式1
*
* @param tz_km_jzfs_1 调整科目结账方式1
*/
public void setTz_km_jzfs_1 ( String tz_km_jzfs_1) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_JZFS_1,tz_km_jzfs_1);
 } 

/** 
* 获取调整科目结账方式2
*
* @return 调整科目结账方式2
*/
public String getTz_km_jzfs_2 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_JZFS_2);
 } 

/** 
* 设置调整科目结账方式2
*
* @param tz_km_jzfs_2 调整科目结账方式2
*/
public void setTz_km_jzfs_2 ( String tz_km_jzfs_2) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_JZFS_2,tz_km_jzfs_2);
 } 

/** 
* 获取调整科目收入项目1
*
* @return 调整科目收入项目1
*/
public String getTz_km_srxm_1 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_SRXM_1);
 } 

/** 
* 设置调整科目收入项目1
*
* @param tz_km_srxm_1 调整科目收入项目1
*/
public void setTz_km_srxm_1 ( String tz_km_srxm_1) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_SRXM_1,tz_km_srxm_1);
 } 

/** 
* 获取调整科目收入项目2
*
* @return 调整科目收入项目2
*/
public String getTz_km_srxm_2 () {
return (String) this.getAttributeValue( RsbaogaoCVO.TZ_KM_SRXM_2);
 } 

/** 
* 设置调整科目收入项目2
*
* @param tz_km_srxm_2 调整科目收入项目2
*/
public void setTz_km_srxm_2 ( String tz_km_srxm_2) {
this.setAttributeValue( RsbaogaoCVO.TZ_KM_SRXM_2,tz_km_srxm_2);
 } 

/** 
* 获取自定义项1
*
* @return 自定义项1
*/
public String getVdef1 () {
return (String) this.getAttributeValue( RsbaogaoCVO.VDEF1);
 } 

/** 
* 设置自定义项1
*
* @param vdef1 自定义项1
*/
public void setVdef1 ( String vdef1) {
this.setAttributeValue( RsbaogaoCVO.VDEF1,vdef1);
 } 

/** 
* 获取自定义项2
*
* @return 自定义项2
*/
public String getVdef2 () {
return (String) this.getAttributeValue( RsbaogaoCVO.VDEF2);
 } 

/** 
* 设置自定义项2
*
* @param vdef2 自定义项2
*/
public void setVdef2 ( String vdef2) {
this.setAttributeValue( RsbaogaoCVO.VDEF2,vdef2);
 } 

/** 
* 获取自定义项3
*
* @return 自定义项3
*/
public String getVdef3 () {
return (String) this.getAttributeValue( RsbaogaoCVO.VDEF3);
 } 

/** 
* 设置自定义项3
*
* @param vdef3 自定义项3
*/
public void setVdef3 ( String vdef3) {
this.setAttributeValue( RsbaogaoCVO.VDEF3,vdef3);
 } 

/** 
* 获取自定义项4
*
* @return 自定义项4
*/
public String getVdef4 () {
return (String) this.getAttributeValue( RsbaogaoCVO.VDEF4);
 } 

/** 
* 设置自定义项4
*
* @param vdef4 自定义项4
*/
public void setVdef4 ( String vdef4) {
this.setAttributeValue( RsbaogaoCVO.VDEF4,vdef4);
 } 

/** 
* 获取自定义项5
*
* @return 自定义项5
*/
public String getVdef5 () {
return (String) this.getAttributeValue( RsbaogaoCVO.VDEF5);
 } 

/** 
* 设置自定义项5
*
* @param vdef5 自定义项5
*/
public void setVdef5 ( String vdef5) {
this.setAttributeValue( RsbaogaoCVO.VDEF5,vdef5);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( RsbaogaoCVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( RsbaogaoCVO.VROWNO,vrowno);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_RsbaogaoCVO");
  }
}