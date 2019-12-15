package nc.vo.hkjt.huiyuan.kaxing;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KaxingHVO extends SuperVO {
/**
*审核人
*/
public static final String APPROVER="approver";
/**
*cardalias
*/
public static final String CARDALIAS="cardalias";
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
*groupname
*/
public static final String GROUPNAME="groupname";
/**
*单据状态
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*卡型金额
*/
public static final String KA_JE="ka_je";
/**
*实收金额
*/
public static final String KA_SS="ka_ss";
/**
*赠送金额
*/
public static final String KA_ZS="ka_zs";
/**
*卡比例
*/
public static final String KABILI="kabili";
/**
*卡型编码
*/
public static final String KAXING_CODE="kaxing_code";
/**
*卡型名称
*/
public static final String KAXING_NAME="kaxing_name";
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
*卡型主键
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
*remark
*/
public static final String REMARK="remark";
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
*vpnuse
*/
public static final String VPNUSE="vpnuse";
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
return (String) this.getAttributeValue( KaxingHVO.APPROVER);
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.setAttributeValue( KaxingHVO.APPROVER,approver);
 } 

/** 
* 获取cardalias
*
* @return cardalias
*/
public String getCardalias () {
return (String) this.getAttributeValue( KaxingHVO.CARDALIAS);
 } 

/** 
* 设置cardalias
*
* @param cardalias cardalias
*/
public void setCardalias ( String cardalias) {
this.setAttributeValue( KaxingHVO.CARDALIAS,cardalias);
 } 

/** 
* 获取制单时间
*
* @return 制单时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( KaxingHVO.CREATIONTIME);
 } 

/** 
* 设置制单时间
*
* @param creationtime 制单时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( KaxingHVO.CREATIONTIME,creationtime);
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getCreator () {
return (String) this.getAttributeValue( KaxingHVO.CREATOR);
 } 

/** 
* 设置制单人
*
* @param creator 制单人
*/
public void setCreator ( String creator) {
this.setAttributeValue( KaxingHVO.CREATOR,creator);
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( KaxingHVO.DBILLDATE);
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( KaxingHVO.DBILLDATE,dbilldate);
 } 

/** 
* 获取groupname
*
* @return groupname
*/
public String getGroupname () {
return (String) this.getAttributeValue( KaxingHVO.GROUPNAME);
 } 

/** 
* 设置groupname
*
* @param groupname groupname
*/
public void setGroupname ( String groupname) {
this.setAttributeValue( KaxingHVO.GROUPNAME,groupname);
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( KaxingHVO.IBILLSTATUS);
 } 

/** 
* 设置单据状态
*
* @param ibillstatus 单据状态
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( KaxingHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* 获取卡型金额
*
* @return 卡型金额
*/
public UFDouble getKa_je () {
return (UFDouble) this.getAttributeValue( KaxingHVO.KA_JE);
 } 

/** 
* 设置卡型金额
*
* @param ka_je 卡型金额
*/
public void setKa_je ( UFDouble ka_je) {
this.setAttributeValue( KaxingHVO.KA_JE,ka_je);
 } 

/** 
* 获取实收金额
*
* @return 实收金额
*/
public UFDouble getKa_ss () {
return (UFDouble) this.getAttributeValue( KaxingHVO.KA_SS);
 } 

/** 
* 设置实收金额
*
* @param ka_ss 实收金额
*/
public void setKa_ss ( UFDouble ka_ss) {
this.setAttributeValue( KaxingHVO.KA_SS,ka_ss);
 } 

/** 
* 获取赠送金额
*
* @return 赠送金额
*/
public UFDouble getKa_zs () {
return (UFDouble) this.getAttributeValue( KaxingHVO.KA_ZS);
 } 

/** 
* 设置赠送金额
*
* @param ka_zs 赠送金额
*/
public void setKa_zs ( UFDouble ka_zs) {
this.setAttributeValue( KaxingHVO.KA_ZS,ka_zs);
 } 

/** 
* 获取卡比例
*
* @return 卡比例
*/
public UFDouble getKabili () {
return (UFDouble) this.getAttributeValue( KaxingHVO.KABILI);
 } 

/** 
* 设置卡比例
*
* @param kabili 卡比例
*/
public void setKabili ( UFDouble kabili) {
this.setAttributeValue( KaxingHVO.KABILI,kabili);
 } 

/** 
* 获取卡型编码
*
* @return 卡型编码
*/
public String getKaxing_code () {
return (String) this.getAttributeValue( KaxingHVO.KAXING_CODE);
 } 

/** 
* 设置卡型编码
*
* @param kaxing_code 卡型编码
*/
public void setKaxing_code ( String kaxing_code) {
this.setAttributeValue( KaxingHVO.KAXING_CODE,kaxing_code);
 } 

/** 
* 获取卡型名称
*
* @return 卡型名称
*/
public String getKaxing_name () {
return (String) this.getAttributeValue( KaxingHVO.KAXING_NAME);
 } 

/** 
* 设置卡型名称
*
* @param kaxing_name 卡型名称
*/
public void setKaxing_name ( String kaxing_name) {
this.setAttributeValue( KaxingHVO.KAXING_NAME,kaxing_name);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( KaxingHVO.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( KaxingHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( KaxingHVO.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( KaxingHVO.MODIFIER,modifier);
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( KaxingHVO.PK_BUSITYPE);
 } 

/** 
* 设置业务类型
*
* @param pk_busitype 业务类型
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( KaxingHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( KaxingHVO.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( KaxingHVO.PK_GROUP,pk_group);
 } 

/** 
* 获取卡型主键
*
* @return 卡型主键
*/
public String getPk_hk_huiyuan_kaxing () {
return (String) this.getAttributeValue( KaxingHVO.PK_HK_HUIYUAN_KAXING);
 } 

/** 
* 设置卡型主键
*
* @param pk_hk_huiyuan_kaxing 卡型主键
*/
public void setPk_hk_huiyuan_kaxing ( String pk_hk_huiyuan_kaxing) {
this.setAttributeValue( KaxingHVO.PK_HK_HUIYUAN_KAXING,pk_hk_huiyuan_kaxing);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( KaxingHVO.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( KaxingHVO.PK_ORG,pk_org);
 } 

/** 
* 获取组织v
*
* @return 组织v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( KaxingHVO.PK_ORG_V);
 } 

/** 
* 设置组织v
*
* @param pk_org_v 组织v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( KaxingHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取remark
*
* @return remark
*/
public String getRemark () {
return (String) this.getAttributeValue( KaxingHVO.REMARK);
 } 

/** 
* 设置remark
*
* @param remark remark
*/
public void setRemark ( String remark) {
this.setAttributeValue( KaxingHVO.REMARK,remark);
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( KaxingHVO.TAUDITTIME);
 } 

/** 
* 设置审核时间
*
* @param taudittime 审核时间
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( KaxingHVO.TAUDITTIME,taudittime);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KaxingHVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KaxingHVO.TS,ts);
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( KaxingHVO.VAPPROVENOTE);
 } 

/** 
* 设置审批批语
*
* @param vapprovenote 审批批语
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( KaxingHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return (String) this.getAttributeValue( KaxingHVO.VBILLCODE);
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( KaxingHVO.VBILLCODE,vbillcode);
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( KaxingHVO.VBILLTYPECODE);
 } 

/** 
* 设置单据类型
*
* @param vbilltypecode 单据类型
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( KaxingHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vdef01 自定义项01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( KaxingHVO.VDEF01,vdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vdef02 自定义项02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( KaxingHVO.VDEF02,vdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vdef03 自定义项03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( KaxingHVO.VDEF03,vdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vdef04 自定义项04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( KaxingHVO.VDEF04,vdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vdef05 自定义项05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( KaxingHVO.VDEF05,vdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vdef06 自定义项06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( KaxingHVO.VDEF06,vdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vdef07 自定义项07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( KaxingHVO.VDEF07,vdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vdef08 自定义项08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( KaxingHVO.VDEF08,vdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vdef09 自定义项09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( KaxingHVO.VDEF09,vdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( KaxingHVO.VDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( KaxingHVO.VDEF10,vdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return (String) this.getAttributeValue( KaxingHVO.VMEMO);
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( KaxingHVO.VMEMO,vmemo);
 } 

/** 
* 获取vpnuse
*
* @return vpnuse
*/
public String getVpnuse () {
return (String) this.getAttributeValue( KaxingHVO.VPNUSE);
 } 

/** 
* 设置vpnuse
*
* @param vpnuse vpnuse
*/
public void setVpnuse ( String vpnuse) {
this.setAttributeValue( KaxingHVO.VPNUSE,vpnuse);
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( KaxingHVO.VTRANTYPECODE);
 } 

/** 
* 设置交易类型
*
* @param vtrantypecode 交易类型
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( KaxingHVO.VTRANTYPECODE,vtrantypecode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_KaxingHVO");
  }
  
  public static final String DR="dr";
  public void setDr ( Integer dr) {
	  this.setAttributeValue( KaxingHVO.DR,dr);
  }
  public Integer getDr () {
	  return (Integer) this.getAttributeValue( KaxingHVO.DR);
	   } 
  
}