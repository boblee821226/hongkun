package nc.vo.hkjt.huiyuan.kadangan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KadanganHVO2 extends SuperVO {
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
*当前余额
*/
public static final String DQ_YE="dq_ye";
/**
*单据状态
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*会员卡号
*/
public static final String KA_CODE="ka_code";
/**
*会员姓名
*/
public static final String KA_NAME="ka_name";
/**
*卡比例
*/
public static final String KABILI="kabili";
/**
*卡状态
*/
public static final String KASTATUS="kastatus";
/**
*可开票总额
*/
public static final String KKPZE="kkpze";
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
*会员卡主键
*/
public static final String PK_HK_HUIYUAN_KADANGAN="pk_hk_huiyuan_kadangan";
/**
*上层单据主键
*/
public static final String PK_HK_HUIYUAN_KAXING="pk_hk_huiyuan_kaxing";
/**
*组织
*/
public static final String PK_ORG="pk_org";
/**
*组织v
*/
public static final String PK_ORG_V="pk_org_v";
/**
*期初余额
*/
public static final String QC_YE="qc_ye";
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
*已开票金额
*/
public static final String YKPJE="ykpje";
/** 
* 获取审核人
*
* @return 审核人
*/
public String getApprover () {
return (String) this.getAttributeValue( KadanganHVO2.APPROVER);
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.setAttributeValue( KadanganHVO2.APPROVER,approver);
 } 

/** 
* 获取制单时间
*
* @return 制单时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( KadanganHVO2.CREATIONTIME);
 } 

/** 
* 设置制单时间
*
* @param creationtime 制单时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( KadanganHVO2.CREATIONTIME,creationtime);
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getCreator () {
return (String) this.getAttributeValue( KadanganHVO2.CREATOR);
 } 

/** 
* 设置制单人
*
* @param creator 制单人
*/
public void setCreator ( String creator) {
this.setAttributeValue( KadanganHVO2.CREATOR,creator);
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( KadanganHVO2.DBILLDATE);
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( KadanganHVO2.DBILLDATE,dbilldate);
 } 

/** 
* 获取当前余额
*
* @return 当前余额
*/
public UFDouble getDq_ye () {
return (UFDouble) this.getAttributeValue( KadanganHVO2.DQ_YE);
 } 

/** 
* 设置当前余额
*
* @param dq_ye 当前余额
*/
public void setDq_ye ( UFDouble dq_ye) {
this.setAttributeValue( KadanganHVO2.DQ_YE,dq_ye);
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( KadanganHVO2.IBILLSTATUS);
 } 

/** 
* 设置单据状态
*
* @param ibillstatus 单据状态
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( KadanganHVO2.IBILLSTATUS,ibillstatus);
 } 

/** 
* 获取会员卡号
*
* @return 会员卡号
*/
public String getKa_code () {
return (String) this.getAttributeValue( KadanganHVO2.KA_CODE);
 } 

/** 
* 设置会员卡号
*
* @param ka_code 会员卡号
*/
public void setKa_code ( String ka_code) {
this.setAttributeValue( KadanganHVO2.KA_CODE,ka_code);
 } 

/** 
* 获取会员姓名
*
* @return 会员姓名
*/
public String getKa_name () {
return (String) this.getAttributeValue( KadanganHVO2.KA_NAME);
 } 

/** 
* 设置会员姓名
*
* @param ka_name 会员姓名
*/
public void setKa_name ( String ka_name) {
this.setAttributeValue( KadanganHVO2.KA_NAME,ka_name);
 } 

/** 
* 获取卡比例
*
* @return 卡比例
*/
public UFDouble getKabili () {
return (UFDouble) this.getAttributeValue( KadanganHVO2.KABILI);
 } 

/** 
* 设置卡比例
*
* @param kabili 卡比例
*/
public void setKabili ( UFDouble kabili) {
this.setAttributeValue( KadanganHVO2.KABILI,kabili);
 } 

/** 
* 获取卡状态
*
* @return 卡状态
* @see String
*/
public String getKastatus () {
return (String) this.getAttributeValue( KadanganHVO2.KASTATUS);
 } 

/** 
* 设置卡状态
*
* @param kastatus 卡状态
* @see String
*/
public void setKastatus ( String kastatus) {
this.setAttributeValue( KadanganHVO2.KASTATUS,kastatus);
 } 

/** 
* 获取可开票总额
*
* @return 可开票总额
*/
public UFDouble getKkpze () {
return (UFDouble) this.getAttributeValue( KadanganHVO2.KKPZE);
 } 

/** 
* 设置可开票总额
*
* @param kkpze 可开票总额
*/
public void setKkpze ( UFDouble kkpze) {
this.setAttributeValue( KadanganHVO2.KKPZE,kkpze);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( KadanganHVO2.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( KadanganHVO2.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( KadanganHVO2.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( KadanganHVO2.MODIFIER,modifier);
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( KadanganHVO2.PK_BUSITYPE);
 } 

/** 
* 设置业务类型
*
* @param pk_busitype 业务类型
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( KadanganHVO2.PK_BUSITYPE,pk_busitype);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( KadanganHVO2.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( KadanganHVO2.PK_GROUP,pk_group);
 } 

/** 
* 获取会员卡主键
*
* @return 会员卡主键
*/
public String getPk_hk_huiyuan_kadangan () {
return (String) this.getAttributeValue( KadanganHVO2.PK_HK_HUIYUAN_KADANGAN);
 } 

/** 
* 设置会员卡主键
*
* @param pk_hk_huiyuan_kadangan 会员卡主键
*/
public void setPk_hk_huiyuan_kadangan ( String pk_hk_huiyuan_kadangan) {
this.setAttributeValue( KadanganHVO2.PK_HK_HUIYUAN_KADANGAN,pk_hk_huiyuan_kadangan);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_kaxing () {
return (String) this.getAttributeValue( KadanganHVO2.PK_HK_HUIYUAN_KAXING);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_kaxing 上层单据主键
*/
public void setPk_hk_huiyuan_kaxing ( String pk_hk_huiyuan_kaxing) {
this.setAttributeValue( KadanganHVO2.PK_HK_HUIYUAN_KAXING,pk_hk_huiyuan_kaxing);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( KadanganHVO2.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( KadanganHVO2.PK_ORG,pk_org);
 } 

/** 
* 获取组织v
*
* @return 组织v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( KadanganHVO2.PK_ORG_V);
 } 

/** 
* 设置组织v
*
* @param pk_org_v 组织v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( KadanganHVO2.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取期初余额
*
* @return 期初余额
*/
public UFDouble getQc_ye () {
return (UFDouble) this.getAttributeValue( KadanganHVO2.QC_YE);
 } 

/** 
* 设置期初余额
*
* @param qc_ye 期初余额
*/
public void setQc_ye ( UFDouble qc_ye) {
this.setAttributeValue( KadanganHVO2.QC_YE,qc_ye);
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( KadanganHVO2.TAUDITTIME);
 } 

/** 
* 设置审核时间
*
* @param taudittime 审核时间
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( KadanganHVO2.TAUDITTIME,taudittime);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KadanganHVO2.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KadanganHVO2.TS,ts);
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( KadanganHVO2.VAPPROVENOTE);
 } 

/** 
* 设置审批批语
*
* @param vapprovenote 审批批语
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( KadanganHVO2.VAPPROVENOTE,vapprovenote);
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return (String) this.getAttributeValue( KadanganHVO2.VBILLCODE);
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( KadanganHVO2.VBILLCODE,vbillcode);
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( KadanganHVO2.VBILLTYPECODE);
 } 

/** 
* 设置单据类型
*
* @param vbilltypecode 单据类型
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( KadanganHVO2.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vdef01 自定义项01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( KadanganHVO2.VDEF01,vdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vdef02 自定义项02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( KadanganHVO2.VDEF02,vdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vdef03 自定义项03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( KadanganHVO2.VDEF03,vdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vdef04 自定义项04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( KadanganHVO2.VDEF04,vdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vdef05 自定义项05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( KadanganHVO2.VDEF05,vdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vdef06 自定义项06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( KadanganHVO2.VDEF06,vdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vdef07 自定义项07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( KadanganHVO2.VDEF07,vdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vdef08 自定义项08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( KadanganHVO2.VDEF08,vdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vdef09 自定义项09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( KadanganHVO2.VDEF09,vdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( KadanganHVO2.VDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( KadanganHVO2.VDEF10,vdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return (String) this.getAttributeValue( KadanganHVO2.VMEMO);
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( KadanganHVO2.VMEMO,vmemo);
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( KadanganHVO2.VTRANTYPECODE);
 } 

/** 
* 设置交易类型
*
* @param vtrantypecode 交易类型
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( KadanganHVO2.VTRANTYPECODE,vtrantypecode);
 } 

/** 
* 获取已开票金额
*
* @return 已开票金额
*/
public UFDouble getYkpje () {
return (UFDouble) this.getAttributeValue( KadanganHVO2.YKPJE);
 } 

/** 
* 设置已开票金额
*
* @param ykpje 已开票金额
*/
public void setYkpje ( UFDouble ykpje) {
this.setAttributeValue( KadanganHVO2.YKPJE,ykpje);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_KadanganHVO2");
  }
}