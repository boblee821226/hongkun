package nc.vo.hkjt.oa.setting;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class OaSettingVO extends SuperVO {
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
*parentbilltype
*/
public static final String PARENTBILLTYPE="parentbilltype";
/**
*pk_billtypecode
*/
public static final String PK_BILLTYPECODE="pk_billtypecode";
/**
*pk_billtypeid
*/
public static final String PK_BILLTYPEID="pk_billtypeid";
/**
*业务类型
*/
public static final String PK_BUSITYPE="pk_busitype";
/**
*集团
*/
public static final String PK_GROUP="pk_group";
/**
*oa设置pk
*/
public static final String PK_HK_OA_SETTING="pk_hk_oa_setting";
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
*table_code_1
*/
public static final String TABLE_CODE_1="table_code_1";
/**
*table_code_2
*/
public static final String TABLE_CODE_2="table_code_2";
/**
*table_code_3
*/
public static final String TABLE_CODE_3="table_code_3";
/**
*table_code_4
*/
public static final String TABLE_CODE_4="table_code_4";
/**
*table_code_5
*/
public static final String TABLE_CODE_5="table_code_5";
/**
*审核时间
*/
public static final String TAUDITTIME="taudittime";
/**
*时间戳
*/
public static final String TS="ts";
/**
*url_data
*/
public static final String URL_DATA="url_data";
/**
*url_file
*/
public static final String URL_FILE="url_file";
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
*workflowid
*/
public static final String WORKFLOWID="workflowid";
/** 
* 获取审核人
*
* @return 审核人
*/
public String getApprover () {
return (String) this.getAttributeValue( OaSettingVO.APPROVER);
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.setAttributeValue( OaSettingVO.APPROVER,approver);
 } 

/** 
* 获取制单时间
*
* @return 制单时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( OaSettingVO.CREATIONTIME);
 } 

/** 
* 设置制单时间
*
* @param creationtime 制单时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( OaSettingVO.CREATIONTIME,creationtime);
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getCreator () {
return (String) this.getAttributeValue( OaSettingVO.CREATOR);
 } 

/** 
* 设置制单人
*
* @param creator 制单人
*/
public void setCreator ( String creator) {
this.setAttributeValue( OaSettingVO.CREATOR,creator);
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( OaSettingVO.DBILLDATE);
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( OaSettingVO.DBILLDATE,dbilldate);
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( OaSettingVO.IBILLSTATUS);
 } 

/** 
* 设置单据状态
*
* @param ibillstatus 单据状态
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( OaSettingVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( OaSettingVO.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( OaSettingVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( OaSettingVO.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( OaSettingVO.MODIFIER,modifier);
 } 

/** 
* 获取parentbilltype
*
* @return parentbilltype
*/
public String getParentbilltype () {
return (String) this.getAttributeValue( OaSettingVO.PARENTBILLTYPE);
 } 

/** 
* 设置parentbilltype
*
* @param parentbilltype parentbilltype
*/
public void setParentbilltype ( String parentbilltype) {
this.setAttributeValue( OaSettingVO.PARENTBILLTYPE,parentbilltype);
 } 

/** 
* 获取pk_billtypecode
*
* @return pk_billtypecode
*/
public String getPk_billtypecode () {
return (String) this.getAttributeValue( OaSettingVO.PK_BILLTYPECODE);
 } 

/** 
* 设置pk_billtypecode
*
* @param pk_billtypecode pk_billtypecode
*/
public void setPk_billtypecode ( String pk_billtypecode) {
this.setAttributeValue( OaSettingVO.PK_BILLTYPECODE,pk_billtypecode);
 } 

/** 
* 获取pk_billtypeid
*
* @return pk_billtypeid
*/
public String getPk_billtypeid () {
return (String) this.getAttributeValue( OaSettingVO.PK_BILLTYPEID);
 } 

/** 
* 设置pk_billtypeid
*
* @param pk_billtypeid pk_billtypeid
*/
public void setPk_billtypeid ( String pk_billtypeid) {
this.setAttributeValue( OaSettingVO.PK_BILLTYPEID,pk_billtypeid);
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( OaSettingVO.PK_BUSITYPE);
 } 

/** 
* 设置业务类型
*
* @param pk_busitype 业务类型
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( OaSettingVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( OaSettingVO.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( OaSettingVO.PK_GROUP,pk_group);
 } 

/** 
* 获取oa设置pk
*
* @return oa设置pk
*/
public String getPk_hk_oa_setting () {
return (String) this.getAttributeValue( OaSettingVO.PK_HK_OA_SETTING);
 } 

/** 
* 设置oa设置pk
*
* @param pk_hk_oa_setting oa设置pk
*/
public void setPk_hk_oa_setting ( String pk_hk_oa_setting) {
this.setAttributeValue( OaSettingVO.PK_HK_OA_SETTING,pk_hk_oa_setting);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( OaSettingVO.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( OaSettingVO.PK_ORG,pk_org);
 } 

/** 
* 获取组织v
*
* @return 组织v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( OaSettingVO.PK_ORG_V);
 } 

/** 
* 设置组织v
*
* @param pk_org_v 组织v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( OaSettingVO.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取交易类型pk
*
* @return 交易类型pk
*/
public String getPk_trantype () {
return (String) this.getAttributeValue( OaSettingVO.PK_TRANTYPE);
 } 

/** 
* 设置交易类型pk
*
* @param pk_trantype 交易类型pk
*/
public void setPk_trantype ( String pk_trantype) {
this.setAttributeValue( OaSettingVO.PK_TRANTYPE,pk_trantype);
 } 

/** 
* 获取table_code_1
*
* @return table_code_1
*/
public String getTable_code_1 () {
return (String) this.getAttributeValue( OaSettingVO.TABLE_CODE_1);
 } 

/** 
* 设置table_code_1
*
* @param table_code_1 table_code_1
*/
public void setTable_code_1 ( String table_code_1) {
this.setAttributeValue( OaSettingVO.TABLE_CODE_1,table_code_1);
 } 

/** 
* 获取table_code_2
*
* @return table_code_2
*/
public String getTable_code_2 () {
return (String) this.getAttributeValue( OaSettingVO.TABLE_CODE_2);
 } 

/** 
* 设置table_code_2
*
* @param table_code_2 table_code_2
*/
public void setTable_code_2 ( String table_code_2) {
this.setAttributeValue( OaSettingVO.TABLE_CODE_2,table_code_2);
 } 

/** 
* 获取table_code_3
*
* @return table_code_3
*/
public String getTable_code_3 () {
return (String) this.getAttributeValue( OaSettingVO.TABLE_CODE_3);
 } 

/** 
* 设置table_code_3
*
* @param table_code_3 table_code_3
*/
public void setTable_code_3 ( String table_code_3) {
this.setAttributeValue( OaSettingVO.TABLE_CODE_3,table_code_3);
 } 

/** 
* 获取table_code_4
*
* @return table_code_4
*/
public String getTable_code_4 () {
return (String) this.getAttributeValue( OaSettingVO.TABLE_CODE_4);
 } 

/** 
* 设置table_code_4
*
* @param table_code_4 table_code_4
*/
public void setTable_code_4 ( String table_code_4) {
this.setAttributeValue( OaSettingVO.TABLE_CODE_4,table_code_4);
 } 

/** 
* 获取table_code_5
*
* @return table_code_5
*/
public String getTable_code_5 () {
return (String) this.getAttributeValue( OaSettingVO.TABLE_CODE_5);
 } 

/** 
* 设置table_code_5
*
* @param table_code_5 table_code_5
*/
public void setTable_code_5 ( String table_code_5) {
this.setAttributeValue( OaSettingVO.TABLE_CODE_5,table_code_5);
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( OaSettingVO.TAUDITTIME);
 } 

/** 
* 设置审核时间
*
* @param taudittime 审核时间
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( OaSettingVO.TAUDITTIME,taudittime);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( OaSettingVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( OaSettingVO.TS,ts);
 } 

/** 
* 获取url_data
*
* @return url_data
*/
public String getUrl_data () {
return (String) this.getAttributeValue( OaSettingVO.URL_DATA);
 } 

/** 
* 设置url_data
*
* @param url_data url_data
*/
public void setUrl_data ( String url_data) {
this.setAttributeValue( OaSettingVO.URL_DATA,url_data);
 } 

/** 
* 获取url_file
*
* @return url_file
*/
public String getUrl_file () {
return (String) this.getAttributeValue( OaSettingVO.URL_FILE);
 } 

/** 
* 设置url_file
*
* @param url_file url_file
*/
public void setUrl_file ( String url_file) {
this.setAttributeValue( OaSettingVO.URL_FILE,url_file);
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( OaSettingVO.VAPPROVENOTE);
 } 

/** 
* 设置审批批语
*
* @param vapprovenote 审批批语
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( OaSettingVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return (String) this.getAttributeValue( OaSettingVO.VBILLCODE);
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( OaSettingVO.VBILLCODE,vbillcode);
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( OaSettingVO.VBILLTYPECODE);
 } 

/** 
* 设置单据类型
*
* @param vbilltypecode 单据类型
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( OaSettingVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vdef01 自定义项01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( OaSettingVO.VDEF01,vdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vdef02 自定义项02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( OaSettingVO.VDEF02,vdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vdef03 自定义项03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( OaSettingVO.VDEF03,vdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vdef04 自定义项04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( OaSettingVO.VDEF04,vdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vdef05 自定义项05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( OaSettingVO.VDEF05,vdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vdef06 自定义项06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( OaSettingVO.VDEF06,vdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vdef07 自定义项07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( OaSettingVO.VDEF07,vdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vdef08 自定义项08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( OaSettingVO.VDEF08,vdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vdef09 自定义项09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( OaSettingVO.VDEF09,vdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( OaSettingVO.VDEF10,vdef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVdef11 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vdef11 自定义项11
*/
public void setVdef11 ( String vdef11) {
this.setAttributeValue( OaSettingVO.VDEF11,vdef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVdef12 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vdef12 自定义项12
*/
public void setVdef12 ( String vdef12) {
this.setAttributeValue( OaSettingVO.VDEF12,vdef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVdef13 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vdef13 自定义项13
*/
public void setVdef13 ( String vdef13) {
this.setAttributeValue( OaSettingVO.VDEF13,vdef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVdef14 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vdef14 自定义项14
*/
public void setVdef14 ( String vdef14) {
this.setAttributeValue( OaSettingVO.VDEF14,vdef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVdef15 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vdef15 自定义项15
*/
public void setVdef15 ( String vdef15) {
this.setAttributeValue( OaSettingVO.VDEF15,vdef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVdef16 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vdef16 自定义项16
*/
public void setVdef16 ( String vdef16) {
this.setAttributeValue( OaSettingVO.VDEF16,vdef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVdef17 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vdef17 自定义项17
*/
public void setVdef17 ( String vdef17) {
this.setAttributeValue( OaSettingVO.VDEF17,vdef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVdef18 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vdef18 自定义项18
*/
public void setVdef18 ( String vdef18) {
this.setAttributeValue( OaSettingVO.VDEF18,vdef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVdef19 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vdef19 自定义项19
*/
public void setVdef19 ( String vdef19) {
this.setAttributeValue( OaSettingVO.VDEF19,vdef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVdef20 () {
return (String) this.getAttributeValue( OaSettingVO.VDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vdef20 自定义项20
*/
public void setVdef20 ( String vdef20) {
this.setAttributeValue( OaSettingVO.VDEF20,vdef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return (String) this.getAttributeValue( OaSettingVO.VMEMO);
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( OaSettingVO.VMEMO,vmemo);
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( OaSettingVO.VTRANTYPECODE);
 } 

/** 
* 设置交易类型
*
* @param vtrantypecode 交易类型
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( OaSettingVO.VTRANTYPECODE,vtrantypecode);
 } 

/** 
* 获取workflowid
*
* @return workflowid
*/
public String getWorkflowid () {
return (String) this.getAttributeValue( OaSettingVO.WORKFLOWID);
 } 

/** 
* 设置workflowid
*
* @param workflowid workflowid
*/
public void setWorkflowid ( String workflowid) {
this.setAttributeValue( OaSettingVO.WORKFLOWID,workflowid);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_oa_settingVO");
  }
}