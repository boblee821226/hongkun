package nc.vo.hkjt.arap.bill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillDVO extends SuperVO {
/**
*账务流水序号
*/
public static final String AR_ACCOUNT_ID="ar_account_id";
/**
*夜审日期
*/
public static final String BIZ_DATE="biz_date";
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
*费用
*/
public static final String CHARGE="charge";
/**
*核销id
*/
public static final String CLOSE_ID="close_id";
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
*客人姓名
*/
public static final String GUEST_NAME="guest_name";
/**
*结算
*/
public static final String PAY="pay";
/**
*上层单据主键
*/
public static final String PK_HK_ARAP_BILL="pk_hk_arap_bill";
/**
*核销子表pk
*/
public static final String PK_HK_ARAP_BILL_D="pk_hk_arap_bill_d";
/**
*房间号
*/
public static final String RMNO="rmno";
/**
*代码
*/
public static final String TA_CODE="ta_code";
/**
*项目描述
*/
public static final String TA_DESCRIPT="ta_descript";
/**
*摘要
*/
public static final String TA_REMARK="ta_remark";
/**
*时间戳
*/
public static final String TS="ts";
/**
*自定义项01
*/
public static final String VDDEF01="vddef01";
/**
*自定义项02
*/
public static final String VDDEF02="vddef02";
/**
*自定义项03
*/
public static final String VDDEF03="vddef03";
/**
*自定义项04
*/
public static final String VDDEF04="vddef04";
/**
*自定义项05
*/
public static final String VDDEF05="vddef05";
/**
*自定义项06
*/
public static final String VDDEF06="vddef06";
/**
*自定义项07
*/
public static final String VDDEF07="vddef07";
/**
*自定义项08
*/
public static final String VDDEF08="vddef08";
/**
*自定义项09
*/
public static final String VDDEF09="vddef09";
/**
*自定义项10
*/
public static final String VDDEF10="vddef10";
/**
*自定义项11
*/
public static final String VDDEF11="vddef11";
/**
*自定义项12
*/
public static final String VDDEF12="vddef12";
/**
*自定义项13
*/
public static final String VDDEF13="vddef13";
/**
*自定义项14
*/
public static final String VDDEF14="vddef14";
/**
*自定义项15
*/
public static final String VDDEF15="vddef15";
/**
*自定义项16
*/
public static final String VDDEF16="vddef16";
/**
*自定义项17
*/
public static final String VDDEF17="vddef17";
/**
*自定义项18
*/
public static final String VDDEF18="vddef18";
/**
*自定义项19
*/
public static final String VDDEF19="vddef19";
/**
*自定义项20
*/
public static final String VDDEF20="vddef20";
/**
*备注
*/
public static final String VDMEMO="vdmemo";
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
* 获取账务流水序号
*
* @return 账务流水序号
*/
public Integer getAr_account_id () {
return (Integer) this.getAttributeValue( BillDVO.AR_ACCOUNT_ID);
 } 

/** 
* 设置账务流水序号
*
* @param ar_account_id 账务流水序号
*/
public void setAr_account_id ( Integer ar_account_id) {
this.setAttributeValue( BillDVO.AR_ACCOUNT_ID,ar_account_id);
 } 

/** 
* 获取夜审日期
*
* @return 夜审日期
*/
public String getBiz_date () {
return (String) this.getAttributeValue( BillDVO.BIZ_DATE);
 } 

/** 
* 设置夜审日期
*
* @param biz_date 夜审日期
*/
public void setBiz_date ( String biz_date) {
this.setAttributeValue( BillDVO.BIZ_DATE,biz_date);
 } 

/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( BillDVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( BillDVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( BillDVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( BillDVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( BillDVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( BillDVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取费用
*
* @return 费用
*/
public UFDouble getCharge () {
return (UFDouble) this.getAttributeValue( BillDVO.CHARGE);
 } 

/** 
* 设置费用
*
* @param charge 费用
*/
public void setCharge ( UFDouble charge) {
this.setAttributeValue( BillDVO.CHARGE,charge);
 } 

/** 
* 获取核销id
*
* @return 核销id
*/
public Integer getClose_id () {
return (Integer) this.getAttributeValue( BillDVO.CLOSE_ID);
 } 

/** 
* 设置核销id
*
* @param close_id 核销id
*/
public void setClose_id ( Integer close_id) {
this.setAttributeValue( BillDVO.CLOSE_ID,close_id);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( BillDVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( BillDVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( BillDVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( BillDVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( BillDVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( BillDVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取客人姓名
*
* @return 客人姓名
*/
public String getGuest_name () {
return (String) this.getAttributeValue( BillDVO.GUEST_NAME);
 } 

/** 
* 设置客人姓名
*
* @param guest_name 客人姓名
*/
public void setGuest_name ( String guest_name) {
this.setAttributeValue( BillDVO.GUEST_NAME,guest_name);
 } 

/** 
* 获取结算
*
* @return 结算
*/
public UFDouble getPay () {
return (UFDouble) this.getAttributeValue( BillDVO.PAY);
 } 

/** 
* 设置结算
*
* @param pay 结算
*/
public void setPay ( UFDouble pay) {
this.setAttributeValue( BillDVO.PAY,pay);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_arap_bill () {
return (String) this.getAttributeValue( BillDVO.PK_HK_ARAP_BILL);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_arap_bill 上层单据主键
*/
public void setPk_hk_arap_bill ( String pk_hk_arap_bill) {
this.setAttributeValue( BillDVO.PK_HK_ARAP_BILL,pk_hk_arap_bill);
 } 

/** 
* 获取核销子表pk
*
* @return 核销子表pk
*/
public String getPk_hk_arap_bill_d () {
return (String) this.getAttributeValue( BillDVO.PK_HK_ARAP_BILL_D);
 } 

/** 
* 设置核销子表pk
*
* @param pk_hk_arap_bill_d 核销子表pk
*/
public void setPk_hk_arap_bill_d ( String pk_hk_arap_bill_d) {
this.setAttributeValue( BillDVO.PK_HK_ARAP_BILL_D,pk_hk_arap_bill_d);
 } 

/** 
* 获取房间号
*
* @return 房间号
*/
public String getRmno () {
return (String) this.getAttributeValue( BillDVO.RMNO);
 } 

/** 
* 设置房间号
*
* @param rmno 房间号
*/
public void setRmno ( String rmno) {
this.setAttributeValue( BillDVO.RMNO,rmno);
 } 

/** 
* 获取代码
*
* @return 代码
*/
public String getTa_code () {
return (String) this.getAttributeValue( BillDVO.TA_CODE);
 } 

/** 
* 设置代码
*
* @param ta_code 代码
*/
public void setTa_code ( String ta_code) {
this.setAttributeValue( BillDVO.TA_CODE,ta_code);
 } 

/** 
* 获取项目描述
*
* @return 项目描述
*/
public String getTa_descript () {
return (String) this.getAttributeValue( BillDVO.TA_DESCRIPT);
 } 

/** 
* 设置项目描述
*
* @param ta_descript 项目描述
*/
public void setTa_descript ( String ta_descript) {
this.setAttributeValue( BillDVO.TA_DESCRIPT,ta_descript);
 } 

/** 
* 获取摘要
*
* @return 摘要
*/
public String getTa_remark () {
return (String) this.getAttributeValue( BillDVO.TA_REMARK);
 } 

/** 
* 设置摘要
*
* @param ta_remark 摘要
*/
public void setTa_remark ( String ta_remark) {
this.setAttributeValue( BillDVO.TA_REMARK,ta_remark);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( BillDVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( BillDVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVddef01 () {
return (String) this.getAttributeValue( BillDVO.VDDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vddef01 自定义项01
*/
public void setVddef01 ( String vddef01) {
this.setAttributeValue( BillDVO.VDDEF01,vddef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVddef02 () {
return (String) this.getAttributeValue( BillDVO.VDDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vddef02 自定义项02
*/
public void setVddef02 ( String vddef02) {
this.setAttributeValue( BillDVO.VDDEF02,vddef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVddef03 () {
return (String) this.getAttributeValue( BillDVO.VDDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vddef03 自定义项03
*/
public void setVddef03 ( String vddef03) {
this.setAttributeValue( BillDVO.VDDEF03,vddef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVddef04 () {
return (String) this.getAttributeValue( BillDVO.VDDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vddef04 自定义项04
*/
public void setVddef04 ( String vddef04) {
this.setAttributeValue( BillDVO.VDDEF04,vddef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVddef05 () {
return (String) this.getAttributeValue( BillDVO.VDDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vddef05 自定义项05
*/
public void setVddef05 ( String vddef05) {
this.setAttributeValue( BillDVO.VDDEF05,vddef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVddef06 () {
return (String) this.getAttributeValue( BillDVO.VDDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vddef06 自定义项06
*/
public void setVddef06 ( String vddef06) {
this.setAttributeValue( BillDVO.VDDEF06,vddef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVddef07 () {
return (String) this.getAttributeValue( BillDVO.VDDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vddef07 自定义项07
*/
public void setVddef07 ( String vddef07) {
this.setAttributeValue( BillDVO.VDDEF07,vddef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVddef08 () {
return (String) this.getAttributeValue( BillDVO.VDDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vddef08 自定义项08
*/
public void setVddef08 ( String vddef08) {
this.setAttributeValue( BillDVO.VDDEF08,vddef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVddef09 () {
return (String) this.getAttributeValue( BillDVO.VDDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vddef09 自定义项09
*/
public void setVddef09 ( String vddef09) {
this.setAttributeValue( BillDVO.VDDEF09,vddef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVddef10 () {
return (String) this.getAttributeValue( BillDVO.VDDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vddef10 自定义项10
*/
public void setVddef10 ( String vddef10) {
this.setAttributeValue( BillDVO.VDDEF10,vddef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVddef11 () {
return (String) this.getAttributeValue( BillDVO.VDDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vddef11 自定义项11
*/
public void setVddef11 ( String vddef11) {
this.setAttributeValue( BillDVO.VDDEF11,vddef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVddef12 () {
return (String) this.getAttributeValue( BillDVO.VDDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vddef12 自定义项12
*/
public void setVddef12 ( String vddef12) {
this.setAttributeValue( BillDVO.VDDEF12,vddef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVddef13 () {
return (String) this.getAttributeValue( BillDVO.VDDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vddef13 自定义项13
*/
public void setVddef13 ( String vddef13) {
this.setAttributeValue( BillDVO.VDDEF13,vddef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVddef14 () {
return (String) this.getAttributeValue( BillDVO.VDDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vddef14 自定义项14
*/
public void setVddef14 ( String vddef14) {
this.setAttributeValue( BillDVO.VDDEF14,vddef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVddef15 () {
return (String) this.getAttributeValue( BillDVO.VDDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vddef15 自定义项15
*/
public void setVddef15 ( String vddef15) {
this.setAttributeValue( BillDVO.VDDEF15,vddef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVddef16 () {
return (String) this.getAttributeValue( BillDVO.VDDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vddef16 自定义项16
*/
public void setVddef16 ( String vddef16) {
this.setAttributeValue( BillDVO.VDDEF16,vddef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVddef17 () {
return (String) this.getAttributeValue( BillDVO.VDDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vddef17 自定义项17
*/
public void setVddef17 ( String vddef17) {
this.setAttributeValue( BillDVO.VDDEF17,vddef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVddef18 () {
return (String) this.getAttributeValue( BillDVO.VDDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vddef18 自定义项18
*/
public void setVddef18 ( String vddef18) {
this.setAttributeValue( BillDVO.VDDEF18,vddef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVddef19 () {
return (String) this.getAttributeValue( BillDVO.VDDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vddef19 自定义项19
*/
public void setVddef19 ( String vddef19) {
this.setAttributeValue( BillDVO.VDDEF19,vddef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVddef20 () {
return (String) this.getAttributeValue( BillDVO.VDDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vddef20 自定义项20
*/
public void setVddef20 ( String vddef20) {
this.setAttributeValue( BillDVO.VDDEF20,vddef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVdmemo () {
return (String) this.getAttributeValue( BillDVO.VDMEMO);
 } 

/** 
* 设置备注
*
* @param vdmemo 备注
*/
public void setVdmemo ( String vdmemo) {
this.setAttributeValue( BillDVO.VDMEMO,vdmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( BillDVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( BillDVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( BillDVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( BillDVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( BillDVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( BillDVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_billDVO");
  }
}