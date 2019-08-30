package nc.vo.hkjt.srgk.jiudian.ruzhangmingxi;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class RzmxHVO extends SuperVO {
/**
*审核人
*/
public static final String APPROVER="approver";
/**
*制单时间
*/
public static final String CREATIONTIME="creationtime";
/**
*制单人
*/
public static final String CREATOR="creator";
/**
*单据日期
*/
public static final String DBILLDATE="dbilldate";
/**
*翻房率
*/
public static final String FFL="ffl";
/**
*单据状态
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*结账金额
*/
public static final String JZJE="jzje";
/**
*可出租房间数
*/
public static final String KCZFS="kczfs";
/**
*客房收入
*/
public static final String KFSR="kfsr";
/**
*修改时间
*/
public static final String MODIFIEDTIME="modifiedtime";
/**
*修改人
*/
public static final String MODIFIER="modifier";
/**
*平均房价
*/
public static final String PJFJ="pjfj";
/**
*业务类型
*/
public static final String PK_BUSITYPE="pk_busitype";
/**
*集团
*/
public static final String PK_GROUP="pk_group";
/**
*入账明细主表pk
*/
public static final String PK_HK_SRGK_JD_RZMX="pk_hk_srgk_jd_rzmx";
/**
*组织
*/
public static final String PK_ORG="pk_org";
/**
*组织v
*/
public static final String PK_ORG_V="pk_org_v";
/**
*REVPAR
*/
public static final String REVPAR="revpar";
/**
*审核时间
*/
public static final String TAUDITTIME="taudittime";
/**
*时间戳
*/
public static final String TS="ts";
/**
*审批批语
*/
public static final String VAPPROVENOTE="vapprovenote";
/**
*单据编号
*/
public static final String VBILLCODE="vbillcode";
/**
*单据类型
*/
public static final String VBILLTYPECODE="vbilltypecode";
/**
*自定义项01
*/
public static final String VDEF01="vdef01";
/**
*自定义项02
*/
public static final String VDEF02="vdef02";
/**
*自定义项03
*/
public static final String VDEF03="vdef03";
/**
*自定义项04
*/
public static final String VDEF04="vdef04";
/**
*自定义项05
*/
public static final String VDEF05="vdef05";
/**
*自定义项06
*/
public static final String VDEF06="vdef06";
/**
*自定义项07
*/
public static final String VDEF07="vdef07";
/**
*自定义项08
*/
public static final String VDEF08="vdef08";
/**
*自定义项09
*/
public static final String VDEF09="vdef09";
/**
*自定义项10
*/
public static final String VDEF10="vdef10";
/**
*备注
*/
public static final String VMEMO="vmemo";
/**
*交易类型
*/
public static final String VTRANTYPECODE="vtrantypecode";
/**
*消费金额
*/
public static final String XFJE="xfje";
/** 
* 获取审核人
*
* @return 审核人
*/
public String getApprover () {
return (String) this.getAttributeValue( RzmxHVO.APPROVER);
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.setAttributeValue( RzmxHVO.APPROVER,approver);
 } 

/** 
* 获取制单时间
*
* @return 制单时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( RzmxHVO.CREATIONTIME);
 } 

/** 
* 设置制单时间
*
* @param creationtime 制单时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( RzmxHVO.CREATIONTIME,creationtime);
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getCreator () {
return (String) this.getAttributeValue( RzmxHVO.CREATOR);
 } 

/** 
* 设置制单人
*
* @param creator 制单人
*/
public void setCreator ( String creator) {
this.setAttributeValue( RzmxHVO.CREATOR,creator);
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( RzmxHVO.DBILLDATE);
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( RzmxHVO.DBILLDATE,dbilldate);
 } 

/** 
* 获取翻房率
*
* @return 翻房率
*/
public UFDouble getFfl () {
return (UFDouble) this.getAttributeValue( RzmxHVO.FFL);
 } 

/** 
* 设置翻房率
*
* @param ffl 翻房率
*/
public void setFfl ( UFDouble ffl) {
this.setAttributeValue( RzmxHVO.FFL,ffl);
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( RzmxHVO.IBILLSTATUS);
 } 

/** 
* 设置单据状态
*
* @param ibillstatus 单据状态
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( RzmxHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* 获取结账金额
*
* @return 结账金额
*/
public UFDouble getJzje () {
return (UFDouble) this.getAttributeValue( RzmxHVO.JZJE);
 } 

/** 
* 设置结账金额
*
* @param jzje 结账金额
*/
public void setJzje ( UFDouble jzje) {
this.setAttributeValue( RzmxHVO.JZJE,jzje);
 } 

/** 
* 获取可出租房间数
*
* @return 可出租房间数
*/
public UFDouble getKczfs () {
return (UFDouble) this.getAttributeValue( RzmxHVO.KCZFS);
 } 

/** 
* 设置可出租房间数
*
* @param kczfs 可出租房间数
*/
public void setKczfs ( UFDouble kczfs) {
this.setAttributeValue( RzmxHVO.KCZFS,kczfs);
 } 

/** 
* 获取客房收入
*
* @return 客房收入
*/
public UFDouble getKfsr () {
return (UFDouble) this.getAttributeValue( RzmxHVO.KFSR);
 } 

/** 
* 设置客房收入
*
* @param kfsr 客房收入
*/
public void setKfsr ( UFDouble kfsr) {
this.setAttributeValue( RzmxHVO.KFSR,kfsr);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( RzmxHVO.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( RzmxHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( RzmxHVO.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( RzmxHVO.MODIFIER,modifier);
 } 

/** 
* 获取平均房价
*
* @return 平均房价
*/
public UFDouble getPjfj () {
return (UFDouble) this.getAttributeValue( RzmxHVO.PJFJ);
 } 

/** 
* 设置平均房价
*
* @param pjfj 平均房价
*/
public void setPjfj ( UFDouble pjfj) {
this.setAttributeValue( RzmxHVO.PJFJ,pjfj);
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( RzmxHVO.PK_BUSITYPE);
 } 

/** 
* 设置业务类型
*
* @param pk_busitype 业务类型
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( RzmxHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( RzmxHVO.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( RzmxHVO.PK_GROUP,pk_group);
 } 

/** 
* 获取入账明细主表pk
*
* @return 入账明细主表pk
*/
public String getPk_hk_srgk_jd_rzmx () {
return (String) this.getAttributeValue( RzmxHVO.PK_HK_SRGK_JD_RZMX);
 } 

/** 
* 设置入账明细主表pk
*
* @param pk_hk_srgk_jd_rzmx 入账明细主表pk
*/
public void setPk_hk_srgk_jd_rzmx ( String pk_hk_srgk_jd_rzmx) {
this.setAttributeValue( RzmxHVO.PK_HK_SRGK_JD_RZMX,pk_hk_srgk_jd_rzmx);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( RzmxHVO.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( RzmxHVO.PK_ORG,pk_org);
 } 

/** 
* 获取组织v
*
* @return 组织v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( RzmxHVO.PK_ORG_V);
 } 

/** 
* 设置组织v
*
* @param pk_org_v 组织v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( RzmxHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取REVPAR
*
* @return REVPAR
*/
public UFDouble getRevpar () {
return (UFDouble) this.getAttributeValue( RzmxHVO.REVPAR);
 } 

/** 
* 设置REVPAR
*
* @param revpar REVPAR
*/
public void setRevpar ( UFDouble revpar) {
this.setAttributeValue( RzmxHVO.REVPAR,revpar);
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( RzmxHVO.TAUDITTIME);
 } 

/** 
* 设置审核时间
*
* @param taudittime 审核时间
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( RzmxHVO.TAUDITTIME,taudittime);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( RzmxHVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( RzmxHVO.TS,ts);
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( RzmxHVO.VAPPROVENOTE);
 } 

/** 
* 设置审批批语
*
* @param vapprovenote 审批批语
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( RzmxHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return (String) this.getAttributeValue( RzmxHVO.VBILLCODE);
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( RzmxHVO.VBILLCODE,vbillcode);
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( RzmxHVO.VBILLTYPECODE);
 } 

/** 
* 设置单据类型
*
* @param vbilltypecode 单据类型
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( RzmxHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( RzmxHVO.VDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vdef01 自定义项01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( RzmxHVO.VDEF01,vdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( RzmxHVO.VDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vdef02 自定义项02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( RzmxHVO.VDEF02,vdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( RzmxHVO.VDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vdef03 自定义项03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( RzmxHVO.VDEF03,vdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( RzmxHVO.VDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vdef04 自定义项04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( RzmxHVO.VDEF04,vdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( RzmxHVO.VDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vdef05 自定义项05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( RzmxHVO.VDEF05,vdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( RzmxHVO.VDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vdef06 自定义项06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( RzmxHVO.VDEF06,vdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( RzmxHVO.VDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vdef07 自定义项07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( RzmxHVO.VDEF07,vdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( RzmxHVO.VDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vdef08 自定义项08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( RzmxHVO.VDEF08,vdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( RzmxHVO.VDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vdef09 自定义项09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( RzmxHVO.VDEF09,vdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( RzmxHVO.VDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( RzmxHVO.VDEF10,vdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return (String) this.getAttributeValue( RzmxHVO.VMEMO);
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( RzmxHVO.VMEMO,vmemo);
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( RzmxHVO.VTRANTYPECODE);
 } 

/** 
* 设置交易类型
*
* @param vtrantypecode 交易类型
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( RzmxHVO.VTRANTYPECODE,vtrantypecode);
 } 

/** 
* 获取消费金额
*
* @return 消费金额
*/
public UFDouble getXfje () {
return (UFDouble) this.getAttributeValue( RzmxHVO.XFJE);
 } 

/** 
* 设置消费金额
*
* @param xfje 消费金额
*/
public void setXfje ( UFDouble xfje) {
this.setAttributeValue( RzmxHVO.XFJE,xfje);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.RzmxHVO");
  }
}