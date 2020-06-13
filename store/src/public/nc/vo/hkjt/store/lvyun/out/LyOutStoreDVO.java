package nc.vo.hkjt.store.lvyun.out;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class LyOutStoreDVO extends SuperVO {
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
*菜品出库金额
*/
public static final String CP_OUT_AMOUNT="cp_out_amount";
/**
*菜品出库数量
*/
public static final String CP_OUT_QUANTITY="cp_out_quantity";
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
*材料出库金额
*/
public static final String INV_OUT_AMOUNT="inv_out_amount";
/**
*材料出库数量
*/
public static final String INV_OUT_QUANTITY="inv_out_quantity";
/**
*绿云菜品code
*/
public static final String LY_CP_CODE="ly_cp_code";
/**
*绿云菜品name
*/
public static final String LY_CP_NAME="ly_cp_name";
/**
*NC成本卡
*/
public static final String PK_BOM="pk_bom";
/**
*NC部门
*/
public static final String PK_DEPT="pk_dept";
/**
*上层单据主键
*/
public static final String PK_HK_STORE_LVYUN_OUT="pk_hk_store_lvyun_out";
/**
*加工品成本pk
*/
public static final String PK_HK_STORE_LVYUN_OUT_D="pk_hk_store_lvyun_out_d";
/**
*NC物料
*/
public static final String PK_INV="pk_inv";
/**
*NC仓库
*/
public static final String PK_STORE="pk_store";
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
return (String) this.getAttributeValue( LyOutStoreDVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( LyOutStoreDVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( LyOutStoreDVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( LyOutStoreDVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( LyOutStoreDVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( LyOutStoreDVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取菜品出库金额
*
* @return 菜品出库金额
*/
public UFDouble getCp_out_amount () {
return (UFDouble) this.getAttributeValue( LyOutStoreDVO.CP_OUT_AMOUNT);
 } 

/** 
* 设置菜品出库金额
*
* @param cp_out_amount 菜品出库金额
*/
public void setCp_out_amount ( UFDouble cp_out_amount) {
this.setAttributeValue( LyOutStoreDVO.CP_OUT_AMOUNT,cp_out_amount);
 } 

/** 
* 获取菜品出库数量
*
* @return 菜品出库数量
*/
public UFDouble getCp_out_quantity () {
return (UFDouble) this.getAttributeValue( LyOutStoreDVO.CP_OUT_QUANTITY);
 } 

/** 
* 设置菜品出库数量
*
* @param cp_out_quantity 菜品出库数量
*/
public void setCp_out_quantity ( UFDouble cp_out_quantity) {
this.setAttributeValue( LyOutStoreDVO.CP_OUT_QUANTITY,cp_out_quantity);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( LyOutStoreDVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( LyOutStoreDVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( LyOutStoreDVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( LyOutStoreDVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( LyOutStoreDVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( LyOutStoreDVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取材料出库金额
*
* @return 材料出库金额
*/
public UFDouble getInv_out_amount () {
return (UFDouble) this.getAttributeValue( LyOutStoreDVO.INV_OUT_AMOUNT);
 } 

/** 
* 设置材料出库金额
*
* @param inv_out_amount 材料出库金额
*/
public void setInv_out_amount ( UFDouble inv_out_amount) {
this.setAttributeValue( LyOutStoreDVO.INV_OUT_AMOUNT,inv_out_amount);
 } 

/** 
* 获取材料出库数量
*
* @return 材料出库数量
*/
public UFDouble getInv_out_quantity () {
return (UFDouble) this.getAttributeValue( LyOutStoreDVO.INV_OUT_QUANTITY);
 } 

/** 
* 设置材料出库数量
*
* @param inv_out_quantity 材料出库数量
*/
public void setInv_out_quantity ( UFDouble inv_out_quantity) {
this.setAttributeValue( LyOutStoreDVO.INV_OUT_QUANTITY,inv_out_quantity);
 } 

/** 
* 获取绿云菜品code
*
* @return 绿云菜品code
*/
public String getLy_cp_code () {
return (String) this.getAttributeValue( LyOutStoreDVO.LY_CP_CODE);
 } 

/** 
* 设置绿云菜品code
*
* @param ly_cp_code 绿云菜品code
*/
public void setLy_cp_code ( String ly_cp_code) {
this.setAttributeValue( LyOutStoreDVO.LY_CP_CODE,ly_cp_code);
 } 

/** 
* 获取绿云菜品name
*
* @return 绿云菜品name
*/
public String getLy_cp_name () {
return (String) this.getAttributeValue( LyOutStoreDVO.LY_CP_NAME);
 } 

/** 
* 设置绿云菜品name
*
* @param ly_cp_name 绿云菜品name
*/
public void setLy_cp_name ( String ly_cp_name) {
this.setAttributeValue( LyOutStoreDVO.LY_CP_NAME,ly_cp_name);
 } 

/** 
* 获取NC成本卡
*
* @return NC成本卡
*/
public String getPk_bom () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_BOM);
 } 

/** 
* 设置NC成本卡
*
* @param pk_bom NC成本卡
*/
public void setPk_bom ( String pk_bom) {
this.setAttributeValue( LyOutStoreDVO.PK_BOM,pk_bom);
 } 

/** 
* 获取NC部门
*
* @return NC部门
*/
public String getPk_dept () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_DEPT);
 } 

/** 
* 设置NC部门
*
* @param pk_dept NC部门
*/
public void setPk_dept ( String pk_dept) {
this.setAttributeValue( LyOutStoreDVO.PK_DEPT,pk_dept);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_store_lvyun_out () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_HK_STORE_LVYUN_OUT);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_store_lvyun_out 上层单据主键
*/
public void setPk_hk_store_lvyun_out ( String pk_hk_store_lvyun_out) {
this.setAttributeValue( LyOutStoreDVO.PK_HK_STORE_LVYUN_OUT,pk_hk_store_lvyun_out);
 } 

/** 
* 获取加工品成本pk
*
* @return 加工品成本pk
*/
public String getPk_hk_store_lvyun_out_d () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_HK_STORE_LVYUN_OUT_D);
 } 

/** 
* 设置加工品成本pk
*
* @param pk_hk_store_lvyun_out_d 加工品成本pk
*/
public void setPk_hk_store_lvyun_out_d ( String pk_hk_store_lvyun_out_d) {
this.setAttributeValue( LyOutStoreDVO.PK_HK_STORE_LVYUN_OUT_D,pk_hk_store_lvyun_out_d);
 } 

/** 
* 获取NC物料
*
* @return NC物料
*/
public String getPk_inv () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_INV);
 } 

/** 
* 设置NC物料
*
* @param pk_inv NC物料
*/
public void setPk_inv ( String pk_inv) {
this.setAttributeValue( LyOutStoreDVO.PK_INV,pk_inv);
 } 

/** 
* 获取NC仓库
*
* @return NC仓库
*/
public String getPk_store () {
return (String) this.getAttributeValue( LyOutStoreDVO.PK_STORE);
 } 

/** 
* 设置NC仓库
*
* @param pk_store NC仓库
*/
public void setPk_store ( String pk_store) {
this.setAttributeValue( LyOutStoreDVO.PK_STORE,pk_store);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( LyOutStoreDVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( LyOutStoreDVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( LyOutStoreDVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( LyOutStoreDVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( LyOutStoreDVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( LyOutStoreDVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( LyOutStoreDVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( LyOutStoreDVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( LyOutStoreDVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( LyOutStoreDVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( LyOutStoreDVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( LyOutStoreDVO.VBDEF10,vbdef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vbdef11 自定义项11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( LyOutStoreDVO.VBDEF11,vbdef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vbdef12 自定义项12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( LyOutStoreDVO.VBDEF12,vbdef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vbdef13 自定义项13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( LyOutStoreDVO.VBDEF13,vbdef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vbdef14 自定义项14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( LyOutStoreDVO.VBDEF14,vbdef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vbdef15 自定义项15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( LyOutStoreDVO.VBDEF15,vbdef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vbdef16 自定义项16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( LyOutStoreDVO.VBDEF16,vbdef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vbdef17 自定义项17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( LyOutStoreDVO.VBDEF17,vbdef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vbdef18 自定义项18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( LyOutStoreDVO.VBDEF18,vbdef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vbdef19 自定义项19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( LyOutStoreDVO.VBDEF19,vbdef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vbdef20 自定义项20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( LyOutStoreDVO.VBDEF20,vbdef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( LyOutStoreDVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( LyOutStoreDVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( LyOutStoreDVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( LyOutStoreDVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( LyOutStoreDVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( LyOutStoreDVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( LyOutStoreDVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( LyOutStoreDVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取营业点code
*
* @return 营业点code
*/
public String getYyd_code () {
return (String) this.getAttributeValue( LyOutStoreDVO.YYD_CODE);
 } 

/** 
* 设置营业点code
*
* @param yyd_code 营业点code
*/
public void setYyd_code ( String yyd_code) {
this.setAttributeValue( LyOutStoreDVO.YYD_CODE,yyd_code);
 } 

/** 
* 获取营业点name
*
* @return 营业点name
*/
public String getYyd_name () {
return (String) this.getAttributeValue( LyOutStoreDVO.YYD_NAME);
 } 

/** 
* 设置营业点name
*
* @param yyd_name 营业点name
*/
public void setYyd_name ( String yyd_name) {
this.setAttributeValue( LyOutStoreDVO.YYD_NAME,yyd_name);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_store_lvyun_outDVO");
  }
}