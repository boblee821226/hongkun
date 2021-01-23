package nc.vo.hkjt.oa.setting;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class OaSettingHVO extends SuperVO {
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
*field_nc
*/
public static final String FIELD_NC="field_nc";
/**
*field_oa
*/
public static final String FIELD_OA="field_oa";
/**
*formula
*/
public static final String FORMULA="formula";
/**
*is_creator
*/
public static final String IS_CREATOR="is_creator";
/**
*is_id
*/
public static final String IS_ID="is_id";
/**
*is_tail
*/
public static final String IS_TAIL="is_tail";
/**
*is_title
*/
public static final String IS_TITLE="is_title";
/**
*上层单据主键
*/
public static final String PK_HK_OA_SETTING="pk_hk_oa_setting";
/**
*oa设置表头pk
*/
public static final String PK_HK_OA_SETTING_H="pk_hk_oa_setting_h";
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
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( OaSettingHVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( OaSettingHVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( OaSettingHVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( OaSettingHVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( OaSettingHVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( OaSettingHVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( OaSettingHVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( OaSettingHVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( OaSettingHVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( OaSettingHVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( OaSettingHVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( OaSettingHVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取field_nc
*
* @return field_nc
*/
public String getField_nc () {
return (String) this.getAttributeValue( OaSettingHVO.FIELD_NC);
 } 

/** 
* 设置field_nc
*
* @param field_nc field_nc
*/
public void setField_nc ( String field_nc) {
this.setAttributeValue( OaSettingHVO.FIELD_NC,field_nc);
 } 

/** 
* 获取field_oa
*
* @return field_oa
*/
public String getField_oa () {
return (String) this.getAttributeValue( OaSettingHVO.FIELD_OA);
 } 

/** 
* 设置field_oa
*
* @param field_oa field_oa
*/
public void setField_oa ( String field_oa) {
this.setAttributeValue( OaSettingHVO.FIELD_OA,field_oa);
 } 

/** 
* 获取formula
*
* @return formula
*/
public String getFormula () {
return (String) this.getAttributeValue( OaSettingHVO.FORMULA);
 } 

/** 
* 设置formula
*
* @param formula formula
*/
public void setFormula ( String formula) {
this.setAttributeValue( OaSettingHVO.FORMULA,formula);
 } 

/** 
* 获取is_creator
*
* @return is_creator
*/
public UFBoolean getIs_creator () {
return (UFBoolean) this.getAttributeValue( OaSettingHVO.IS_CREATOR);
 } 

/** 
* 设置is_creator
*
* @param is_creator is_creator
*/
public void setIs_creator ( UFBoolean is_creator) {
this.setAttributeValue( OaSettingHVO.IS_CREATOR,is_creator);
 } 

/** 
* 获取is_id
*
* @return is_id
*/
public UFBoolean getIs_id () {
return (UFBoolean) this.getAttributeValue( OaSettingHVO.IS_ID);
 } 

/** 
* 设置is_id
*
* @param is_id is_id
*/
public void setIs_id ( UFBoolean is_id) {
this.setAttributeValue( OaSettingHVO.IS_ID,is_id);
 } 

/** 
* 获取is_tail
*
* @return is_tail
*/
public UFBoolean getIs_tail () {
return (UFBoolean) this.getAttributeValue( OaSettingHVO.IS_TAIL);
 } 

/** 
* 设置is_tail
*
* @param is_tail is_tail
*/
public void setIs_tail ( UFBoolean is_tail) {
this.setAttributeValue( OaSettingHVO.IS_TAIL,is_tail);
 } 

/** 
* 获取is_title
*
* @return is_title
*/
public UFBoolean getIs_title () {
return (UFBoolean) this.getAttributeValue( OaSettingHVO.IS_TITLE);
 } 

/** 
* 设置is_title
*
* @param is_title is_title
*/
public void setIs_title ( UFBoolean is_title) {
this.setAttributeValue( OaSettingHVO.IS_TITLE,is_title);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_oa_setting () {
return (String) this.getAttributeValue( OaSettingHVO.PK_HK_OA_SETTING);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_oa_setting 上层单据主键
*/
public void setPk_hk_oa_setting ( String pk_hk_oa_setting) {
this.setAttributeValue( OaSettingHVO.PK_HK_OA_SETTING,pk_hk_oa_setting);
 } 

/** 
* 获取oa设置表头pk
*
* @return oa设置表头pk
*/
public String getPk_hk_oa_setting_h () {
return (String) this.getAttributeValue( OaSettingHVO.PK_HK_OA_SETTING_H);
 } 

/** 
* 设置oa设置表头pk
*
* @param pk_hk_oa_setting_h oa设置表头pk
*/
public void setPk_hk_oa_setting_h ( String pk_hk_oa_setting_h) {
this.setAttributeValue( OaSettingHVO.PK_HK_OA_SETTING_H,pk_hk_oa_setting_h);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( OaSettingHVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( OaSettingHVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( OaSettingHVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( OaSettingHVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( OaSettingHVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( OaSettingHVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( OaSettingHVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( OaSettingHVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( OaSettingHVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( OaSettingHVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( OaSettingHVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( OaSettingHVO.VBDEF10,vbdef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vbdef11 自定义项11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( OaSettingHVO.VBDEF11,vbdef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vbdef12 自定义项12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( OaSettingHVO.VBDEF12,vbdef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vbdef13 自定义项13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( OaSettingHVO.VBDEF13,vbdef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vbdef14 自定义项14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( OaSettingHVO.VBDEF14,vbdef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vbdef15 自定义项15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( OaSettingHVO.VBDEF15,vbdef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vbdef16 自定义项16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( OaSettingHVO.VBDEF16,vbdef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vbdef17 自定义项17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( OaSettingHVO.VBDEF17,vbdef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vbdef18 自定义项18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( OaSettingHVO.VBDEF18,vbdef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vbdef19 自定义项19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( OaSettingHVO.VBDEF19,vbdef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( OaSettingHVO.VBDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vbdef20 自定义项20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( OaSettingHVO.VBDEF20,vbdef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( OaSettingHVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( OaSettingHVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( OaSettingHVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( OaSettingHVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( OaSettingHVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( OaSettingHVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( OaSettingHVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( OaSettingHVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_oa_settingHVO");
  }
}