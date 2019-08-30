package nc.vo.hkjt.huiyuan.huanka;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class HuankaHVO extends SuperVO {
/**
*审核人
*/
public static final String APPROVER="approver";
/**
*cardid
*/
public static final String CARDID="cardid";
/**
*制单时间
*/
public static final String CREATIONTIME="creationtime";
/**
*制单人
*/
public static final String CREATOR="creator";
/**
*操作员
*/
public static final String CZY="czy";
/**
*单据日期
*/
public static final String DBILLDATE="dbilldate";
/**
*换卡时间
*/
public static final String HK_TIME="hk_time";
/**
*单据状态
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*现卡号
*/
public static final String KA_CODE="ka_code";
/**
*卡金额
*/
public static final String KA_JE="ka_je";
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
*会员换卡pk
*/
public static final String PK_HK_HUIYUAN_HUANKA="pk_hk_huiyuan_huanka";
/**
*组织
*/
public static final String PK_ORG="pk_org";
/**
*组织v
*/
public static final String PK_ORG_V="pk_org_v";
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
*原卡号
*/
public static final String Y_KA_CODE="y_ka_code";
/** 
* 获取审核人
*
* @return 审核人
*/
public String getApprover () {
return (String) this.getAttributeValue( HuankaHVO.APPROVER);
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.setAttributeValue( HuankaHVO.APPROVER,approver);
 } 

/** 
* 获取cardid
*
* @return cardid
*/
public String getCardid () {
return (String) this.getAttributeValue( HuankaHVO.CARDID);
 } 

/** 
* 设置cardid
*
* @param cardid cardid
*/
public void setCardid ( String cardid) {
this.setAttributeValue( HuankaHVO.CARDID,cardid);
 } 

/** 
* 获取制单时间
*
* @return 制单时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( HuankaHVO.CREATIONTIME);
 } 

/** 
* 设置制单时间
*
* @param creationtime 制单时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( HuankaHVO.CREATIONTIME,creationtime);
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getCreator () {
return (String) this.getAttributeValue( HuankaHVO.CREATOR);
 } 

/** 
* 设置制单人
*
* @param creator 制单人
*/
public void setCreator ( String creator) {
this.setAttributeValue( HuankaHVO.CREATOR,creator);
 } 

/** 
* 获取操作员
*
* @return 操作员
*/
public String getCzy () {
return (String) this.getAttributeValue( HuankaHVO.CZY);
 } 

/** 
* 设置操作员
*
* @param czy 操作员
*/
public void setCzy ( String czy) {
this.setAttributeValue( HuankaHVO.CZY,czy);
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( HuankaHVO.DBILLDATE);
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( HuankaHVO.DBILLDATE,dbilldate);
 } 

/** 
* 获取换卡时间
*
* @return 换卡时间
*/
public UFDateTime getHk_time () {
return (UFDateTime) this.getAttributeValue( HuankaHVO.HK_TIME);
 } 

/** 
* 设置换卡时间
*
* @param hk_time 换卡时间
*/
public void setHk_time ( UFDateTime hk_time) {
this.setAttributeValue( HuankaHVO.HK_TIME,hk_time);
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( HuankaHVO.IBILLSTATUS);
 } 

/** 
* 设置单据状态
*
* @param ibillstatus 单据状态
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( HuankaHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* 获取现卡号
*
* @return 现卡号
*/
public String getKa_code () {
return (String) this.getAttributeValue( HuankaHVO.KA_CODE);
 } 

/** 
* 设置现卡号
*
* @param ka_code 现卡号
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( HuankaHVO.KA_CODE,ka_code);
 } 

/** 
* 获取卡金额
*
* @return 卡金额
*/
public UFDouble getKa_je () {
return (UFDouble) this.getAttributeValue( HuankaHVO.KA_JE);
 } 

/** 
* 设置卡金额
*
* @param ka_je 卡金额
*/
public void setKa_je ( UFDouble ka_je) {
this.setAttributeValue( HuankaHVO.KA_JE,ka_je);
 } 

/** 
* 获取卡pk
*
* @return 卡pk
*/
public String getKa_pk () {
return (String) this.getAttributeValue( HuankaHVO.KA_PK);
 } 

/** 
* 设置卡pk
*
* @param ka_pk 卡pk
*/
public void setKa_pk ( String ka_pk) {
this.setAttributeValue( HuankaHVO.KA_PK,ka_pk);
 } 

/** 
* 获取卡型code
*
* @return 卡型code
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( HuankaHVO.KAXING_CODE);
 } 

/** 
* 设置卡型code
*
* @param kaxing_code 卡型code
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( HuankaHVO.KAXING_CODE,kaxing_code);
 } 

/** 
* 获取卡型name
*
* @return 卡型name
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( HuankaHVO.KAXING_NAME);
 } 

/** 
* 设置卡型name
*
* @param kaxing_name 卡型name
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( HuankaHVO.KAXING_NAME,kaxing_name);
 } 

/** 
* 获取卡型pk
*
* @return 卡型pk
*/
public String getKaxing_pk () {
return (String) this.getAttributeValue( HuankaHVO.KAXING_PK);
 } 

/** 
* 设置卡型pk
*
* @param kaxing_pk 卡型pk
*/
public void setKaxing_pk ( String kaxing_pk) {
this.setAttributeValue( HuankaHVO.KAXING_PK,kaxing_pk);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( HuankaHVO.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( HuankaHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( HuankaHVO.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( HuankaHVO.MODIFIER,modifier);
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( HuankaHVO.PK_BUSITYPE);
 } 

/** 
* 设置业务类型
*
* @param pk_busitype 业务类型
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( HuankaHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( HuankaHVO.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( HuankaHVO.PK_GROUP,pk_group);
 } 

/** 
* 获取会员换卡pk
*
* @return 会员换卡pk
*/
public String getPk_hk_huiyuan_huanka () {
return (String) this.getAttributeValue( HuankaHVO.PK_HK_HUIYUAN_HUANKA);
 } 

/** 
* 设置会员换卡pk
*
* @param pk_hk_huiyuan_huanka 会员换卡pk
*/
public void setPk_hk_huiyuan_huanka ( String pk_hk_huiyuan_huanka) {
this.setAttributeValue( HuankaHVO.PK_HK_HUIYUAN_HUANKA,pk_hk_huiyuan_huanka);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( HuankaHVO.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( HuankaHVO.PK_ORG,pk_org);
 } 

/** 
* 获取组织v
*
* @return 组织v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( HuankaHVO.PK_ORG_V);
 } 

/** 
* 设置组织v
*
* @param pk_org_v 组织v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( HuankaHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( HuankaHVO.TAUDITTIME);
 } 

/** 
* 设置审核时间
*
* @param taudittime 审核时间
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( HuankaHVO.TAUDITTIME,taudittime);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( HuankaHVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( HuankaHVO.TS,ts);
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( HuankaHVO.VAPPROVENOTE);
 } 

/** 
* 设置审批批语
*
* @param vapprovenote 审批批语
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( HuankaHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return (String) this.getAttributeValue( HuankaHVO.VBILLCODE);
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( HuankaHVO.VBILLCODE,vbillcode);
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( HuankaHVO.VBILLTYPECODE);
 } 

/** 
* 设置单据类型
*
* @param vbilltypecode 单据类型
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( HuankaHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( HuankaHVO.VDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vdef01 自定义项01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( HuankaHVO.VDEF01,vdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( HuankaHVO.VDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vdef02 自定义项02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( HuankaHVO.VDEF02,vdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( HuankaHVO.VDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vdef03 自定义项03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( HuankaHVO.VDEF03,vdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( HuankaHVO.VDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vdef04 自定义项04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( HuankaHVO.VDEF04,vdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( HuankaHVO.VDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vdef05 自定义项05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( HuankaHVO.VDEF05,vdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( HuankaHVO.VDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vdef06 自定义项06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( HuankaHVO.VDEF06,vdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( HuankaHVO.VDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vdef07 自定义项07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( HuankaHVO.VDEF07,vdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( HuankaHVO.VDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vdef08 自定义项08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( HuankaHVO.VDEF08,vdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( HuankaHVO.VDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vdef09 自定义项09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( HuankaHVO.VDEF09,vdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( HuankaHVO.VDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( HuankaHVO.VDEF10,vdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return (String) this.getAttributeValue( HuankaHVO.VMEMO);
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( HuankaHVO.VMEMO,vmemo);
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( HuankaHVO.VTRANTYPECODE);
 } 

/** 
* 设置交易类型
*
* @param vtrantypecode 交易类型
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( HuankaHVO.VTRANTYPECODE,vtrantypecode);
 } 

/** 
* 获取原卡号
*
* @return 原卡号
*/
public String getY_ka_code () {
return (String) this.getAttributeValue( HuankaHVO.Y_KA_CODE);
 } 

/** 
* 设置原卡号
*
* @param y_ka_code 原卡号
*/
public void setY_ka_code ( String y_ka_code) {
this.setAttributeValue( HuankaHVO.Y_KA_CODE,y_ka_code);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_HuankaHVO");
  }
  
  public static final String DR="dr";
  public void setDr ( Integer dr) {
	  this.setAttributeValue( HuankaHVO.DR,dr);
  }
  public Integer getDr () {
	  return (Integer) this.getAttributeValue( HuankaHVO.DR);
	   } 
  
}