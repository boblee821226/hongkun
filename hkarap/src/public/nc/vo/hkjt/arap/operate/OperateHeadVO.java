package nc.vo.hkjt.arap.operate;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class OperateHeadVO extends SuperVO {
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
*单据状态
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*修改时间
*/
public static final String MODIFIEDTIME="modifiedtime";
/**
*修改人
*/
public static final String MODIFIER="modifier";
/**
*业务类型
*/
public static final String PK_BUSITYPE="pk_busitype";
/**
*集团
*/
public static final String PK_GROUP="pk_group";
/**
*账务操作pk
*/
public static final String PK_HK_ARAP_OPERATE="pk_hk_arap_operate";
/**
*组织
*/
public static final String PK_ORG="pk_org";
/**
*组织v
*/
public static final String PK_ORG_V="pk_org_v";
/**
*交易类型pk
*/
public static final String PK_TRANTYPE="pk_trantype";
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
*自定义项11
*/
public static final String VDEF11="vdef11";
/**
*自定义项12
*/
public static final String VDEF12="vdef12";
/**
*自定义项13
*/
public static final String VDEF13="vdef13";
/**
*自定义项14
*/
public static final String VDEF14="vdef14";
/**
*自定义项15
*/
public static final String VDEF15="vdef15";
/**
*自定义项16
*/
public static final String VDEF16="vdef16";
/**
*自定义项17
*/
public static final String VDEF17="vdef17";
/**
*自定义项18
*/
public static final String VDEF18="vdef18";
/**
*自定义项19
*/
public static final String VDEF19="vdef19";
/**
*自定义项20
*/
public static final String VDEF20="vdef20";
/**
*备注
*/
public static final String VMEMO="vmemo";
/**
*交易类型
*/
public static final String VTRANTYPECODE="vtrantypecode";
/** 
* 获取审核人
*
* @return 审核人
*/
public String getApprover () {
return (String) this.getAttributeValue( OperateHeadVO.APPROVER);
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.setAttributeValue( OperateHeadVO.APPROVER,approver);
 } 

/** 
* 获取制单时间
*
* @return 制单时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( OperateHeadVO.CREATIONTIME);
 } 

/** 
* 设置制单时间
*
* @param creationtime 制单时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( OperateHeadVO.CREATIONTIME,creationtime);
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getCreator () {
return (String) this.getAttributeValue( OperateHeadVO.CREATOR);
 } 

/** 
* 设置制单人
*
* @param creator 制单人
*/
public void setCreator ( String creator) {
this.setAttributeValue( OperateHeadVO.CREATOR,creator);
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( OperateHeadVO.DBILLDATE);
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( OperateHeadVO.DBILLDATE,dbilldate);
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( OperateHeadVO.IBILLSTATUS);
 } 

/** 
* 设置单据状态
*
* @param ibillstatus 单据状态
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( OperateHeadVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( OperateHeadVO.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( OperateHeadVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( OperateHeadVO.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( OperateHeadVO.MODIFIER,modifier);
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( OperateHeadVO.PK_BUSITYPE);
 } 

/** 
* 设置业务类型
*
* @param pk_busitype 业务类型
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( OperateHeadVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( OperateHeadVO.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( OperateHeadVO.PK_GROUP,pk_group);
 } 

/** 
* 获取账务操作pk
*
* @return 账务操作pk
*/
public String getPk_hk_arap_operate () {
return (String) this.getAttributeValue( OperateHeadVO.PK_HK_ARAP_OPERATE);
 } 

/** 
* 设置账务操作pk
*
* @param pk_hk_arap_operate 账务操作pk
*/
public void setPk_hk_arap_operate ( String pk_hk_arap_operate) {
this.setAttributeValue( OperateHeadVO.PK_HK_ARAP_OPERATE,pk_hk_arap_operate);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( OperateHeadVO.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( OperateHeadVO.PK_ORG,pk_org);
 } 

/** 
* 获取组织v
*
* @return 组织v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( OperateHeadVO.PK_ORG_V);
 } 

/** 
* 设置组织v
*
* @param pk_org_v 组织v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( OperateHeadVO.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取交易类型pk
*
* @return 交易类型pk
*/
public String getPk_trantype () {
return (String) this.getAttributeValue( OperateHeadVO.PK_TRANTYPE);
 } 

/** 
* 设置交易类型pk
*
* @param pk_trantype 交易类型pk
*/
public void setPk_trantype ( String pk_trantype) {
this.setAttributeValue( OperateHeadVO.PK_TRANTYPE,pk_trantype);
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( OperateHeadVO.TAUDITTIME);
 } 

/** 
* 设置审核时间
*
* @param taudittime 审核时间
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( OperateHeadVO.TAUDITTIME,taudittime);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( OperateHeadVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( OperateHeadVO.TS,ts);
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( OperateHeadVO.VAPPROVENOTE);
 } 

/** 
* 设置审批批语
*
* @param vapprovenote 审批批语
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( OperateHeadVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return (String) this.getAttributeValue( OperateHeadVO.VBILLCODE);
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( OperateHeadVO.VBILLCODE,vbillcode);
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( OperateHeadVO.VBILLTYPECODE);
 } 

/** 
* 设置单据类型
*
* @param vbilltypecode 单据类型
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( OperateHeadVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vdef01 自定义项01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( OperateHeadVO.VDEF01,vdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vdef02 自定义项02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( OperateHeadVO.VDEF02,vdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vdef03 自定义项03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( OperateHeadVO.VDEF03,vdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vdef04 自定义项04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( OperateHeadVO.VDEF04,vdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vdef05 自定义项05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( OperateHeadVO.VDEF05,vdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vdef06 自定义项06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( OperateHeadVO.VDEF06,vdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vdef07 自定义项07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( OperateHeadVO.VDEF07,vdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vdef08 自定义项08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( OperateHeadVO.VDEF08,vdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vdef09 自定义项09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( OperateHeadVO.VDEF09,vdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( OperateHeadVO.VDEF10,vdef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVdef11 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vdef11 自定义项11
*/
public void setVdef11 ( String vdef11) {
this.setAttributeValue( OperateHeadVO.VDEF11,vdef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVdef12 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vdef12 自定义项12
*/
public void setVdef12 ( String vdef12) {
this.setAttributeValue( OperateHeadVO.VDEF12,vdef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVdef13 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vdef13 自定义项13
*/
public void setVdef13 ( String vdef13) {
this.setAttributeValue( OperateHeadVO.VDEF13,vdef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVdef14 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vdef14 自定义项14
*/
public void setVdef14 ( String vdef14) {
this.setAttributeValue( OperateHeadVO.VDEF14,vdef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVdef15 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vdef15 自定义项15
*/
public void setVdef15 ( String vdef15) {
this.setAttributeValue( OperateHeadVO.VDEF15,vdef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVdef16 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vdef16 自定义项16
*/
public void setVdef16 ( String vdef16) {
this.setAttributeValue( OperateHeadVO.VDEF16,vdef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVdef17 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vdef17 自定义项17
*/
public void setVdef17 ( String vdef17) {
this.setAttributeValue( OperateHeadVO.VDEF17,vdef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVdef18 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vdef18 自定义项18
*/
public void setVdef18 ( String vdef18) {
this.setAttributeValue( OperateHeadVO.VDEF18,vdef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVdef19 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vdef19 自定义项19
*/
public void setVdef19 ( String vdef19) {
this.setAttributeValue( OperateHeadVO.VDEF19,vdef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVdef20 () {
return (String) this.getAttributeValue( OperateHeadVO.VDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vdef20 自定义项20
*/
public void setVdef20 ( String vdef20) {
this.setAttributeValue( OperateHeadVO.VDEF20,vdef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return (String) this.getAttributeValue( OperateHeadVO.VMEMO);
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( OperateHeadVO.VMEMO,vmemo);
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( OperateHeadVO.VTRANTYPECODE);
 } 

/** 
* 设置交易类型
*
* @param vtrantypecode 交易类型
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( OperateHeadVO.VTRANTYPECODE,vtrantypecode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_operateHeadVO");
  }
}