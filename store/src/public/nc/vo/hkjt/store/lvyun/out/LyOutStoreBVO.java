package nc.vo.hkjt.store.lvyun.out;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class LyOutStoreBVO extends SuperVO {
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
*绿云售品code
*/
public static final String LY_SP_CODE="ly_sp_code";
/**
*绿云售品name
*/
public static final String LY_SP_NAME="ly_sp_name";
/**
*NC部门
*/
public static final String PK_DEPT="pk_dept";
/**
*上层单据主键
*/
public static final String PK_HK_STORE_LVYUN_OUT="pk_hk_store_lvyun_out";
/**
*零售品出库pk
*/
public static final String PK_HK_STORE_LVYUN_OUT_B="pk_hk_store_lvyun_out_b";
/**
*NC物料
*/
public static final String PK_INV="pk_inv";
/**
*NC仓库
*/
public static final String PK_STORE="pk_store";
/**
*出库金额
*/
public static final String SP_OUT_AMOUNT="sp_out_amount";
/**
*出库数量
*/
public static final String SP_OUT_QUANTITY="sp_out_quantity";
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
*营业点code
*/
public static final String YYD_CODE="yyd_code";
/**
*营业点name
*/
public static final String YYD_NAME="yyd_name";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( LyOutStoreBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( LyOutStoreBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( LyOutStoreBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( LyOutStoreBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( LyOutStoreBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( LyOutStoreBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( LyOutStoreBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( LyOutStoreBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( LyOutStoreBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( LyOutStoreBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( LyOutStoreBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( LyOutStoreBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取绿云售品code
*
* @return 绿云售品code
*/
public String getLy_sp_code () {
return (String) this.getAttributeValue( LyOutStoreBVO.LY_SP_CODE);
 } 

/** 
* 设置绿云售品code
*
* @param ly_sp_code 绿云售品code
*/
public void setLy_sp_code ( String ly_sp_code) {
this.setAttributeValue( LyOutStoreBVO.LY_SP_CODE,ly_sp_code);
 } 

/** 
* 获取绿云售品name
*
* @return 绿云售品name
*/
public String getLy_sp_name () {
return (String) this.getAttributeValue( LyOutStoreBVO.LY_SP_NAME);
 } 

/** 
* 设置绿云售品name
*
* @param ly_sp_name 绿云售品name
*/
public void setLy_sp_name ( String ly_sp_name) {
this.setAttributeValue( LyOutStoreBVO.LY_SP_NAME,ly_sp_name);
 } 

/** 
* 获取NC部门
*
* @return NC部门
*/
public String getPk_dept () {
return (String) this.getAttributeValue( LyOutStoreBVO.PK_DEPT);
 } 

/** 
* 设置NC部门
*
* @param pk_dept NC部门
*/
public void setPk_dept ( String pk_dept) {
this.setAttributeValue( LyOutStoreBVO.PK_DEPT,pk_dept);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_store_lvyun_out () {
return (String) this.getAttributeValue( LyOutStoreBVO.PK_HK_STORE_LVYUN_OUT);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_store_lvyun_out 上层单据主键
*/
public void setPk_hk_store_lvyun_out ( String pk_hk_store_lvyun_out) {
this.setAttributeValue( LyOutStoreBVO.PK_HK_STORE_LVYUN_OUT,pk_hk_store_lvyun_out);
 } 

/** 
* 获取零售品出库pk
*
* @return 零售品出库pk
*/
public String getPk_hk_store_lvyun_out_b () {
return (String) this.getAttributeValue( LyOutStoreBVO.PK_HK_STORE_LVYUN_OUT_B);
 } 

/** 
* 设置零售品出库pk
*
* @param pk_hk_store_lvyun_out_b 零售品出库pk
*/
public void setPk_hk_store_lvyun_out_b ( String pk_hk_store_lvyun_out_b) {
this.setAttributeValue( LyOutStoreBVO.PK_HK_STORE_LVYUN_OUT_B,pk_hk_store_lvyun_out_b);
 } 

/** 
* 获取NC物料
*
* @return NC物料
*/
public String getPk_inv () {
return (String) this.getAttributeValue( LyOutStoreBVO.PK_INV);
 } 

/** 
* 设置NC物料
*
* @param pk_inv NC物料
*/
public void setPk_inv ( String pk_inv) {
this.setAttributeValue( LyOutStoreBVO.PK_INV,pk_inv);
 } 

/** 
* 获取NC仓库
*
* @return NC仓库
*/
public String getPk_store () {
return (String) this.getAttributeValue( LyOutStoreBVO.PK_STORE);
 } 

/** 
* 设置NC仓库
*
* @param pk_store NC仓库
*/
public void setPk_store ( String pk_store) {
this.setAttributeValue( LyOutStoreBVO.PK_STORE,pk_store);
 } 

/** 
* 获取出库金额
*
* @return 出库金额
*/
public UFDouble getSp_out_amount () {
return (UFDouble) this.getAttributeValue( LyOutStoreBVO.SP_OUT_AMOUNT);
 } 

/** 
* 设置出库金额
*
* @param sp_out_amount 出库金额
*/
public void setSp_out_amount ( UFDouble sp_out_amount) {
this.setAttributeValue( LyOutStoreBVO.SP_OUT_AMOUNT,sp_out_amount);
 } 

/** 
* 获取出库数量
*
* @return 出库数量
*/
public UFDouble getSp_out_quantity () {
return (UFDouble) this.getAttributeValue( LyOutStoreBVO.SP_OUT_QUANTITY);
 } 

/** 
* 设置出库数量
*
* @param sp_out_quantity 出库数量
*/
public void setSp_out_quantity ( UFDouble sp_out_quantity) {
this.setAttributeValue( LyOutStoreBVO.SP_OUT_QUANTITY,sp_out_quantity);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( LyOutStoreBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( LyOutStoreBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( LyOutStoreBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( LyOutStoreBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( LyOutStoreBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( LyOutStoreBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( LyOutStoreBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( LyOutStoreBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( LyOutStoreBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( LyOutStoreBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( LyOutStoreBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( LyOutStoreBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vbdef11 自定义项11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( LyOutStoreBVO.VBDEF11,vbdef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vbdef12 自定义项12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( LyOutStoreBVO.VBDEF12,vbdef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vbdef13 自定义项13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( LyOutStoreBVO.VBDEF13,vbdef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vbdef14 自定义项14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( LyOutStoreBVO.VBDEF14,vbdef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vbdef15 自定义项15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( LyOutStoreBVO.VBDEF15,vbdef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vbdef16 自定义项16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( LyOutStoreBVO.VBDEF16,vbdef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vbdef17 自定义项17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( LyOutStoreBVO.VBDEF17,vbdef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vbdef18 自定义项18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( LyOutStoreBVO.VBDEF18,vbdef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vbdef19 自定义项19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( LyOutStoreBVO.VBDEF19,vbdef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vbdef20 自定义项20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( LyOutStoreBVO.VBDEF20,vbdef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( LyOutStoreBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( LyOutStoreBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( LyOutStoreBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( LyOutStoreBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( LyOutStoreBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( LyOutStoreBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( LyOutStoreBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( LyOutStoreBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取营业点code
*
* @return 营业点code
*/
public String getYyd_code () {
return (String) this.getAttributeValue( LyOutStoreBVO.YYD_CODE);
 } 

/** 
* 设置营业点code
*
* @param yyd_code 营业点code
*/
public void setYyd_code ( String yyd_code) {
this.setAttributeValue( LyOutStoreBVO.YYD_CODE,yyd_code);
 } 

/** 
* 获取营业点name
*
* @return 营业点name
*/
public String getYyd_name () {
return (String) this.getAttributeValue( LyOutStoreBVO.YYD_NAME);
 } 

/** 
* 设置营业点name
*
* @param yyd_name 营业点name
*/
public void setYyd_name ( String yyd_name) {
this.setAttributeValue( LyOutStoreBVO.YYD_NAME,yyd_name);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_store_lvyun_outBVO");
  }
}