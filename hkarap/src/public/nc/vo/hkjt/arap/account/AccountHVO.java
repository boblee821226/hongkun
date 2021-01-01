package nc.vo.hkjt.arap.account;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class AccountHVO extends SuperVO {
/**
*账户编码
*/
public static final String ACC_CODE="acc_code";
/**
*账户名称
*/
public static final String ACC_NAME="acc_name";
/**
*账户状态
*/
public static final String ACC_STATUS="acc_status";
/**
*账户类别
*/
public static final String ACC_TYPE="acc_type";
/**
*公司地址
*/
public static final String ADDRESS="address";
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
*Email
*/
public static final String EMAIL="email";
/**
*合同联络人
*/
public static final String HTLLR="htllr";
/**
*合同联系方式
*/
public static final String HTLLR_PHONE="htllr_phone";
/**
*单据状态
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*结算方式
*/
public static final String JSFS="jsfs";
/**
*结算联络人
*/
public static final String JSLLR="jsllr";
/**
*结算联系方式
*/
public static final String JSLLR_PHONE="jsllr_phone";
/**
*修改时间
*/
public static final String MODIFIEDTIME="modifiedtime";
/**
*修改人
*/
public static final String MODIFIER="modifier";
/**
*OTA酒店ID
*/
public static final String OTA_ID="ota_id";
/**
*业务类型
*/
public static final String PK_BUSITYPE="pk_busitype";
/**
*客户pk
*/
public static final String PK_CUSTOMER="pk_customer";
/**
*集团
*/
public static final String PK_GROUP="pk_group";
/**
*应收账户pk
*/
public static final String PK_HK_ARAP_ACCOUNT="pk_hk_arap_account";
/**
*组织
*/
public static final String PK_ORG="pk_org";
/**
*组织v
*/
public static final String PK_ORG_V="pk_org_v";
/**
*公司签单人A
*/
public static final String QDR01="qdr01";
/**
*公司签单人B
*/
public static final String QDR02="qdr02";
/**
*公司签单人C
*/
public static final String QDR03="qdr03";
/**
*公司签单人D
*/
public static final String QDR04="qdr04";
/**
*公司签单人E
*/
public static final String QDR05="qdr05";
/**
*快递地址
*/
public static final String SEND="send";
/**
*签字样本A
*/
public static final String SIGN01="sign01";
/**
*签字样本B
*/
public static final String SIGN02="sign02";
/**
*签字样本C
*/
public static final String SIGN03="sign03";
/**
*签字样本D
*/
public static final String SIGN04="sign04";
/**
*签字样本E
*/
public static final String SIGN05="sign05";
/**
*审核时间
*/
public static final String TAUDITTIME="taudittime";
/**
*时间戳
*/
public static final String TS="ts";
/**
*付款主体
*/
public static final String UNIT_FK="unit_fk";
/**
*签章主体
*/
public static final String UNIT_QZ="unit_qz";
/**
*协议主体
*/
public static final String UNIT_XY="unit_xy";
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
*销售员
*/
public static final String XSY="xsy";
/**
*销售联系方式
*/
public static final String XSY_PHONE="xsy_phone";
/**
*协议有效期
*/
public static final String YXQ="yxq";
/** 
* 获取账户编码
*
* @return 账户编码
*/
public String getAcc_code () {
return (String) this.getAttributeValue( AccountHVO.ACC_CODE);
 } 

/** 
* 设置账户编码
*
* @param acc_code 账户编码
*/
public void setAcc_code ( String acc_code) {
this.setAttributeValue( AccountHVO.ACC_CODE,acc_code);
 } 

/** 
* 获取账户名称
*
* @return 账户名称
*/
public String getAcc_name () {
return (String) this.getAttributeValue( AccountHVO.ACC_NAME);
 } 

/** 
* 设置账户名称
*
* @param acc_name 账户名称
*/
public void setAcc_name ( String acc_name) {
this.setAttributeValue( AccountHVO.ACC_NAME,acc_name);
 } 

/** 
* 获取账户状态
*
* @return 账户状态
*/
public String getAcc_status () {
return (String) this.getAttributeValue( AccountHVO.ACC_STATUS);
 } 

/** 
* 设置账户状态
*
* @param acc_status 账户状态
*/
public void setAcc_status ( String acc_status) {
this.setAttributeValue( AccountHVO.ACC_STATUS,acc_status);
 } 

/** 
* 获取账户类别
*
* @return 账户类别
*/
public String getAcc_type () {
return (String) this.getAttributeValue( AccountHVO.ACC_TYPE);
 } 

/** 
* 设置账户类别
*
* @param acc_type 账户类别
*/
public void setAcc_type ( String acc_type) {
this.setAttributeValue( AccountHVO.ACC_TYPE,acc_type);
 } 

/** 
* 获取公司地址
*
* @return 公司地址
*/
public String getAddress () {
return (String) this.getAttributeValue( AccountHVO.ADDRESS);
 } 

/** 
* 设置公司地址
*
* @param address 公司地址
*/
public void setAddress ( String address) {
this.setAttributeValue( AccountHVO.ADDRESS,address);
 } 

/** 
* 获取审核人
*
* @return 审核人
*/
public String getApprover () {
return (String) this.getAttributeValue( AccountHVO.APPROVER);
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.setAttributeValue( AccountHVO.APPROVER,approver);
 } 

/** 
* 获取制单时间
*
* @return 制单时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( AccountHVO.CREATIONTIME);
 } 

/** 
* 设置制单时间
*
* @param creationtime 制单时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( AccountHVO.CREATIONTIME,creationtime);
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getCreator () {
return (String) this.getAttributeValue( AccountHVO.CREATOR);
 } 

/** 
* 设置制单人
*
* @param creator 制单人
*/
public void setCreator ( String creator) {
this.setAttributeValue( AccountHVO.CREATOR,creator);
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( AccountHVO.DBILLDATE);
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( AccountHVO.DBILLDATE,dbilldate);
 } 

/** 
* 获取Email
*
* @return Email
*/
public String getEmail () {
return (String) this.getAttributeValue( AccountHVO.EMAIL);
 } 

/** 
* 设置Email
*
* @param email Email
*/
public void setEmail ( String email) {
this.setAttributeValue( AccountHVO.EMAIL,email);
 } 

/** 
* 获取合同联络人
*
* @return 合同联络人
*/
public String getHtllr () {
return (String) this.getAttributeValue( AccountHVO.HTLLR);
 } 

/** 
* 设置合同联络人
*
* @param htllr 合同联络人
*/
public void setHtllr ( String htllr) {
this.setAttributeValue( AccountHVO.HTLLR,htllr);
 } 

/** 
* 获取合同联系方式
*
* @return 合同联系方式
*/
public String getHtllr_phone () {
return (String) this.getAttributeValue( AccountHVO.HTLLR_PHONE);
 } 

/** 
* 设置合同联系方式
*
* @param htllr_phone 合同联系方式
*/
public void setHtllr_phone ( String htllr_phone) {
this.setAttributeValue( AccountHVO.HTLLR_PHONE,htllr_phone);
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( AccountHVO.IBILLSTATUS);
 } 

/** 
* 设置单据状态
*
* @param ibillstatus 单据状态
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( AccountHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* 获取结算方式
*
* @return 结算方式
*/
public String getJsfs () {
return (String) this.getAttributeValue( AccountHVO.JSFS);
 } 

/** 
* 设置结算方式
*
* @param jsfs 结算方式
*/
public void setJsfs ( String jsfs) {
this.setAttributeValue( AccountHVO.JSFS,jsfs);
 } 

/** 
* 获取结算联络人
*
* @return 结算联络人
*/
public String getJsllr () {
return (String) this.getAttributeValue( AccountHVO.JSLLR);
 } 

/** 
* 设置结算联络人
*
* @param jsllr 结算联络人
*/
public void setJsllr ( String jsllr) {
this.setAttributeValue( AccountHVO.JSLLR,jsllr);
 } 

/** 
* 获取结算联系方式
*
* @return 结算联系方式
*/
public String getJsllr_phone () {
return (String) this.getAttributeValue( AccountHVO.JSLLR_PHONE);
 } 

/** 
* 设置结算联系方式
*
* @param jsllr_phone 结算联系方式
*/
public void setJsllr_phone ( String jsllr_phone) {
this.setAttributeValue( AccountHVO.JSLLR_PHONE,jsllr_phone);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( AccountHVO.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( AccountHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( AccountHVO.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( AccountHVO.MODIFIER,modifier);
 } 

/** 
* 获取OTA酒店ID
*
* @return OTA酒店ID
*/
public String getOta_id () {
return (String) this.getAttributeValue( AccountHVO.OTA_ID);
 } 

/** 
* 设置OTA酒店ID
*
* @param ota_id OTA酒店ID
*/
public void setOta_id ( String ota_id) {
this.setAttributeValue( AccountHVO.OTA_ID,ota_id);
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( AccountHVO.PK_BUSITYPE);
 } 

/** 
* 设置业务类型
*
* @param pk_busitype 业务类型
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( AccountHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* 获取客户pk
*
* @return 客户pk
*/
public String getPk_customer () {
return (String) this.getAttributeValue( AccountHVO.PK_CUSTOMER);
 } 

/** 
* 设置客户pk
*
* @param pk_customer 客户pk
*/
public void setPk_customer ( String pk_customer) {
this.setAttributeValue( AccountHVO.PK_CUSTOMER,pk_customer);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( AccountHVO.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( AccountHVO.PK_GROUP,pk_group);
 } 

/** 
* 获取应收账户pk
*
* @return 应收账户pk
*/
public String getPk_hk_arap_account () {
return (String) this.getAttributeValue( AccountHVO.PK_HK_ARAP_ACCOUNT);
 } 

/** 
* 设置应收账户pk
*
* @param pk_hk_arap_account 应收账户pk
*/
public void setPk_hk_arap_account ( String pk_hk_arap_account) {
this.setAttributeValue( AccountHVO.PK_HK_ARAP_ACCOUNT,pk_hk_arap_account);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( AccountHVO.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( AccountHVO.PK_ORG,pk_org);
 } 

/** 
* 获取组织v
*
* @return 组织v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( AccountHVO.PK_ORG_V);
 } 

/** 
* 设置组织v
*
* @param pk_org_v 组织v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( AccountHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取公司签单人A
*
* @return 公司签单人A
*/
public String getQdr01 () {
return (String) this.getAttributeValue( AccountHVO.QDR01);
 } 

/** 
* 设置公司签单人A
*
* @param qdr01 公司签单人A
*/
public void setQdr01 ( String qdr01) {
this.setAttributeValue( AccountHVO.QDR01,qdr01);
 } 

/** 
* 获取公司签单人B
*
* @return 公司签单人B
*/
public String getQdr02 () {
return (String) this.getAttributeValue( AccountHVO.QDR02);
 } 

/** 
* 设置公司签单人B
*
* @param qdr02 公司签单人B
*/
public void setQdr02 ( String qdr02) {
this.setAttributeValue( AccountHVO.QDR02,qdr02);
 } 

/** 
* 获取公司签单人C
*
* @return 公司签单人C
*/
public String getQdr03 () {
return (String) this.getAttributeValue( AccountHVO.QDR03);
 } 

/** 
* 设置公司签单人C
*
* @param qdr03 公司签单人C
*/
public void setQdr03 ( String qdr03) {
this.setAttributeValue( AccountHVO.QDR03,qdr03);
 } 

/** 
* 获取公司签单人D
*
* @return 公司签单人D
*/
public String getQdr04 () {
return (String) this.getAttributeValue( AccountHVO.QDR04);
 } 

/** 
* 设置公司签单人D
*
* @param qdr04 公司签单人D
*/
public void setQdr04 ( String qdr04) {
this.setAttributeValue( AccountHVO.QDR04,qdr04);
 } 

/** 
* 获取公司签单人E
*
* @return 公司签单人E
*/
public String getQdr05 () {
return (String) this.getAttributeValue( AccountHVO.QDR05);
 } 

/** 
* 设置公司签单人E
*
* @param qdr05 公司签单人E
*/
public void setQdr05 ( String qdr05) {
this.setAttributeValue( AccountHVO.QDR05,qdr05);
 } 

/** 
* 获取快递地址
*
* @return 快递地址
*/
public String getSend () {
return (String) this.getAttributeValue( AccountHVO.SEND);
 } 

/** 
* 设置快递地址
*
* @param send 快递地址
*/
public void setSend ( String send) {
this.setAttributeValue( AccountHVO.SEND,send);
 } 

/** 
* 获取签字样本A
*
* @return 签字样本A
*/
public String getSign01 () {
return (String) this.getAttributeValue( AccountHVO.SIGN01);
 } 

/** 
* 设置签字样本A
*
* @param sign01 签字样本A
*/
public void setSign01 ( String sign01) {
this.setAttributeValue( AccountHVO.SIGN01,sign01);
 } 

/** 
* 获取签字样本B
*
* @return 签字样本B
*/
public String getSign02 () {
return (String) this.getAttributeValue( AccountHVO.SIGN02);
 } 

/** 
* 设置签字样本B
*
* @param sign02 签字样本B
*/
public void setSign02 ( String sign02) {
this.setAttributeValue( AccountHVO.SIGN02,sign02);
 } 

/** 
* 获取签字样本C
*
* @return 签字样本C
*/
public String getSign03 () {
return (String) this.getAttributeValue( AccountHVO.SIGN03);
 } 

/** 
* 设置签字样本C
*
* @param sign03 签字样本C
*/
public void setSign03 ( String sign03) {
this.setAttributeValue( AccountHVO.SIGN03,sign03);
 } 

/** 
* 获取签字样本D
*
* @return 签字样本D
*/
public String getSign04 () {
return (String) this.getAttributeValue( AccountHVO.SIGN04);
 } 

/** 
* 设置签字样本D
*
* @param sign04 签字样本D
*/
public void setSign04 ( String sign04) {
this.setAttributeValue( AccountHVO.SIGN04,sign04);
 } 

/** 
* 获取签字样本E
*
* @return 签字样本E
*/
public String getSign05 () {
return (String) this.getAttributeValue( AccountHVO.SIGN05);
 } 

/** 
* 设置签字样本E
*
* @param sign05 签字样本E
*/
public void setSign05 ( String sign05) {
this.setAttributeValue( AccountHVO.SIGN05,sign05);
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( AccountHVO.TAUDITTIME);
 } 

/** 
* 设置审核时间
*
* @param taudittime 审核时间
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( AccountHVO.TAUDITTIME,taudittime);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( AccountHVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( AccountHVO.TS,ts);
 } 

/** 
* 获取付款主体
*
* @return 付款主体
*/
public String getUnit_fk () {
return (String) this.getAttributeValue( AccountHVO.UNIT_FK);
 } 

/** 
* 设置付款主体
*
* @param unit_fk 付款主体
*/
public void setUnit_fk ( String unit_fk) {
this.setAttributeValue( AccountHVO.UNIT_FK,unit_fk);
 } 

/** 
* 获取签章主体
*
* @return 签章主体
*/
public String getUnit_qz () {
return (String) this.getAttributeValue( AccountHVO.UNIT_QZ);
 } 

/** 
* 设置签章主体
*
* @param unit_qz 签章主体
*/
public void setUnit_qz ( String unit_qz) {
this.setAttributeValue( AccountHVO.UNIT_QZ,unit_qz);
 } 

/** 
* 获取协议主体
*
* @return 协议主体
*/
public String getUnit_xy () {
return (String) this.getAttributeValue( AccountHVO.UNIT_XY);
 } 

/** 
* 设置协议主体
*
* @param unit_xy 协议主体
*/
public void setUnit_xy ( String unit_xy) {
this.setAttributeValue( AccountHVO.UNIT_XY,unit_xy);
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( AccountHVO.VAPPROVENOTE);
 } 

/** 
* 设置审批批语
*
* @param vapprovenote 审批批语
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( AccountHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return (String) this.getAttributeValue( AccountHVO.VBILLCODE);
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( AccountHVO.VBILLCODE,vbillcode);
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( AccountHVO.VBILLTYPECODE);
 } 

/** 
* 设置单据类型
*
* @param vbilltypecode 单据类型
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( AccountHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( AccountHVO.VDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vdef01 自定义项01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( AccountHVO.VDEF01,vdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( AccountHVO.VDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vdef02 自定义项02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( AccountHVO.VDEF02,vdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( AccountHVO.VDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vdef03 自定义项03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( AccountHVO.VDEF03,vdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( AccountHVO.VDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vdef04 自定义项04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( AccountHVO.VDEF04,vdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( AccountHVO.VDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vdef05 自定义项05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( AccountHVO.VDEF05,vdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( AccountHVO.VDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vdef06 自定义项06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( AccountHVO.VDEF06,vdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( AccountHVO.VDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vdef07 自定义项07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( AccountHVO.VDEF07,vdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( AccountHVO.VDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vdef08 自定义项08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( AccountHVO.VDEF08,vdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( AccountHVO.VDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vdef09 自定义项09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( AccountHVO.VDEF09,vdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( AccountHVO.VDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( AccountHVO.VDEF10,vdef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVdef11 () {
return (String) this.getAttributeValue( AccountHVO.VDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vdef11 自定义项11
*/
public void setVdef11 ( String vdef11) {
this.setAttributeValue( AccountHVO.VDEF11,vdef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVdef12 () {
return (String) this.getAttributeValue( AccountHVO.VDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vdef12 自定义项12
*/
public void setVdef12 ( String vdef12) {
this.setAttributeValue( AccountHVO.VDEF12,vdef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVdef13 () {
return (String) this.getAttributeValue( AccountHVO.VDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vdef13 自定义项13
*/
public void setVdef13 ( String vdef13) {
this.setAttributeValue( AccountHVO.VDEF13,vdef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVdef14 () {
return (String) this.getAttributeValue( AccountHVO.VDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vdef14 自定义项14
*/
public void setVdef14 ( String vdef14) {
this.setAttributeValue( AccountHVO.VDEF14,vdef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVdef15 () {
return (String) this.getAttributeValue( AccountHVO.VDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vdef15 自定义项15
*/
public void setVdef15 ( String vdef15) {
this.setAttributeValue( AccountHVO.VDEF15,vdef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVdef16 () {
return (String) this.getAttributeValue( AccountHVO.VDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vdef16 自定义项16
*/
public void setVdef16 ( String vdef16) {
this.setAttributeValue( AccountHVO.VDEF16,vdef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVdef17 () {
return (String) this.getAttributeValue( AccountHVO.VDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vdef17 自定义项17
*/
public void setVdef17 ( String vdef17) {
this.setAttributeValue( AccountHVO.VDEF17,vdef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVdef18 () {
return (String) this.getAttributeValue( AccountHVO.VDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vdef18 自定义项18
*/
public void setVdef18 ( String vdef18) {
this.setAttributeValue( AccountHVO.VDEF18,vdef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVdef19 () {
return (String) this.getAttributeValue( AccountHVO.VDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vdef19 自定义项19
*/
public void setVdef19 ( String vdef19) {
this.setAttributeValue( AccountHVO.VDEF19,vdef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVdef20 () {
return (String) this.getAttributeValue( AccountHVO.VDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vdef20 自定义项20
*/
public void setVdef20 ( String vdef20) {
this.setAttributeValue( AccountHVO.VDEF20,vdef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return (String) this.getAttributeValue( AccountHVO.VMEMO);
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( AccountHVO.VMEMO,vmemo);
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( AccountHVO.VTRANTYPECODE);
 } 

/** 
* 设置交易类型
*
* @param vtrantypecode 交易类型
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( AccountHVO.VTRANTYPECODE,vtrantypecode);
 } 

/** 
* 获取销售员
*
* @return 销售员
*/
public String getXsy () {
return (String) this.getAttributeValue( AccountHVO.XSY);
 } 

/** 
* 设置销售员
*
* @param xsy 销售员
*/
public void setXsy ( String xsy) {
this.setAttributeValue( AccountHVO.XSY,xsy);
 } 

/** 
* 获取销售联系方式
*
* @return 销售联系方式
*/
public String getXsy_phone () {
return (String) this.getAttributeValue( AccountHVO.XSY_PHONE);
 } 

/** 
* 设置销售联系方式
*
* @param xsy_phone 销售联系方式
*/
public void setXsy_phone ( String xsy_phone) {
this.setAttributeValue( AccountHVO.XSY_PHONE,xsy_phone);
 } 

/** 
* 获取协议有效期
*
* @return 协议有效期
*/
public String getYxq () {
return (String) this.getAttributeValue( AccountHVO.YXQ);
 } 

/** 
* 设置协议有效期
*
* @param yxq 协议有效期
*/
public void setYxq ( String yxq) {
this.setAttributeValue( AccountHVO.YXQ,yxq);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_accoutHVO");
  }
}