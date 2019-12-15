package nc.vo.hkjt.huiyuan.cikazong;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CikazongBVO extends SuperVO {
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
*差量
*/
public static final String CHALIANG="chaliang";
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
*充值金额
*/
public static final String CZ_MONEY="cz_money";
/**
*充值数量
*/
public static final String CZ_NUM="cz_num";
/**
*上层单据主键
*/
public static final String PK_HK_HUIYUAN_CIKAZONG="pk_hk_huiyuan_cikazong";
/**
*次卡总表子表pk
*/
public static final String PK_HK_HUIYUAN_CIKAZONG_B="pk_hk_huiyuan_cikazong_b";
/**
*日期
*/
public static final String RQ="rq";
/**
*调减金额
*/
public static final String TJ_MONEY="tj_money";
/**
*调减数量
*/
public static final String TJ_NUM="tj_num";
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
*消费金额
*/
public static final String XF_MONEY="xf_money";
/**
*消费数量
*/
public static final String XF_NUM="xf_num";
/**
*余额
*/
public static final String YUE="yue";
/**
*余量
*/
public static final String YULIANG="yuliang";
/**
*金贵余量
*/
public static final String YULIANG_JG="yuliang_jg";
/**
*转出金额
*/
public static final String ZC_MONEY="zc_money";
/**
*转出数量
*/
public static final String ZC_NUM="zc_num";
/**
*转入金额
*/
public static final String ZR_MONEY="zr_money";
/**
*转入数量
*/
public static final String ZR_NUM="zr_num";
/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( CikazongBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( CikazongBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( CikazongBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( CikazongBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( CikazongBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( CikazongBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取差量
*
* @return 差量
*/
public UFDouble getChaliang () {
return (UFDouble) this.getAttributeValue( CikazongBVO.CHALIANG);
 } 

/** 
* 设置差量
*
* @param chaliang 差量
*/
public void setChaliang ( UFDouble chaliang) {
this.setAttributeValue( CikazongBVO.CHALIANG,chaliang);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( CikazongBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( CikazongBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( CikazongBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( CikazongBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( CikazongBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( CikazongBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取充值金额
*
* @return 充值金额
*/
public UFDouble getCz_money () {
return (UFDouble) this.getAttributeValue( CikazongBVO.CZ_MONEY);
 } 

/** 
* 设置充值金额
*
* @param cz_money 充值金额
*/
public void setCz_money ( UFDouble cz_money) {
this.setAttributeValue( CikazongBVO.CZ_MONEY,cz_money);
 } 

/** 
* 获取充值数量
*
* @return 充值数量
*/
public UFDouble getCz_num () {
return (UFDouble) this.getAttributeValue( CikazongBVO.CZ_NUM);
 } 

/** 
* 设置充值数量
*
* @param cz_num 充值数量
*/
public void setCz_num ( UFDouble cz_num) {
this.setAttributeValue( CikazongBVO.CZ_NUM,cz_num);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_huiyuan_cikazong () {
return (String) this.getAttributeValue( CikazongBVO.PK_HK_HUIYUAN_CIKAZONG);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_huiyuan_cikazong 上层单据主键
*/
public void setPk_hk_huiyuan_cikazong ( String pk_hk_huiyuan_cikazong) {
this.setAttributeValue( CikazongBVO.PK_HK_HUIYUAN_CIKAZONG,pk_hk_huiyuan_cikazong);
 } 

/** 
* 获取次卡总表子表pk
*
* @return 次卡总表子表pk
*/
public String getPk_hk_huiyuan_cikazong_b () {
return (String) this.getAttributeValue( CikazongBVO.PK_HK_HUIYUAN_CIKAZONG_B);
 } 

/** 
* 设置次卡总表子表pk
*
* @param pk_hk_huiyuan_cikazong_b 次卡总表子表pk
*/
public void setPk_hk_huiyuan_cikazong_b ( String pk_hk_huiyuan_cikazong_b) {
this.setAttributeValue( CikazongBVO.PK_HK_HUIYUAN_CIKAZONG_B,pk_hk_huiyuan_cikazong_b);
 } 

/** 
* 获取日期
*
* @return 日期
*/
public UFDate getRq () {
return (UFDate) this.getAttributeValue( CikazongBVO.RQ);
 } 

/** 
* 设置日期
*
* @param rq 日期
*/
public void setRq ( UFDate rq) {
this.setAttributeValue( CikazongBVO.RQ,rq);
 } 

/** 
* 获取调减金额
*
* @return 调减金额
*/
public UFDouble getTj_money () {
return (UFDouble) this.getAttributeValue( CikazongBVO.TJ_MONEY);
 } 

/** 
* 设置调减金额
*
* @param tj_money 调减金额
*/
public void setTj_money ( UFDouble tj_money) {
this.setAttributeValue( CikazongBVO.TJ_MONEY,tj_money);
 } 

/** 
* 获取调减数量
*
* @return 调减数量
*/
public UFDouble getTj_num () {
return (UFDouble) this.getAttributeValue( CikazongBVO.TJ_NUM);
 } 

/** 
* 设置调减数量
*
* @param tj_num 调减数量
*/
public void setTj_num ( UFDouble tj_num) {
this.setAttributeValue( CikazongBVO.TJ_NUM,tj_num);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( CikazongBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( CikazongBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( CikazongBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( CikazongBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( CikazongBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( CikazongBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( CikazongBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( CikazongBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( CikazongBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( CikazongBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( CikazongBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( CikazongBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( CikazongBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( CikazongBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( CikazongBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( CikazongBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( CikazongBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public Integer getVrowno () {
return (Integer) this.getAttributeValue( CikazongBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( Integer vrowno) {
this.setAttributeValue( CikazongBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( CikazongBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( CikazongBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取消费金额
*
* @return 消费金额
*/
public UFDouble getXf_money () {
return (UFDouble) this.getAttributeValue( CikazongBVO.XF_MONEY);
 } 

/** 
* 设置消费金额
*
* @param xf_money 消费金额
*/
public void setXf_money ( UFDouble xf_money) {
this.setAttributeValue( CikazongBVO.XF_MONEY,xf_money);
 } 

/** 
* 获取消费数量
*
* @return 消费数量
*/
public UFDouble getXf_num () {
return (UFDouble) this.getAttributeValue( CikazongBVO.XF_NUM);
 } 

/** 
* 设置消费数量
*
* @param xf_num 消费数量
*/
public void setXf_num ( UFDouble xf_num) {
this.setAttributeValue( CikazongBVO.XF_NUM,xf_num);
 } 

/** 
* 获取余额
*
* @return 余额
*/
public UFDouble getYue () {
return (UFDouble) this.getAttributeValue( CikazongBVO.YUE);
 } 

/** 
* 设置余额
*
* @param yue 余额
*/
public void setYue ( UFDouble yue) {
this.setAttributeValue( CikazongBVO.YUE,yue);
 } 

/** 
* 获取余量
*
* @return 余量
*/
public UFDouble getYuliang () {
return (UFDouble) this.getAttributeValue( CikazongBVO.YULIANG);
 } 

/** 
* 设置余量
*
* @param yuliang 余量
*/
public void setYuliang ( UFDouble yuliang) {
this.setAttributeValue( CikazongBVO.YULIANG,yuliang);
 } 

/** 
* 获取金贵余量
*
* @return 金贵余量
*/
public UFDouble getYuliang_jg () {
return (UFDouble) this.getAttributeValue( CikazongBVO.YULIANG_JG);
 } 

/** 
* 设置金贵余量
*
* @param yuliang_jg 金贵余量
*/
public void setYuliang_jg ( UFDouble yuliang_jg) {
this.setAttributeValue( CikazongBVO.YULIANG_JG,yuliang_jg);
 } 

/** 
* 获取转出金额
*
* @return 转出金额
*/
public UFDouble getZc_money () {
return (UFDouble) this.getAttributeValue( CikazongBVO.ZC_MONEY);
 } 

/** 
* 设置转出金额
*
* @param zc_money 转出金额
*/
public void setZc_money ( UFDouble zc_money) {
this.setAttributeValue( CikazongBVO.ZC_MONEY,zc_money);
 } 

/** 
* 获取转出数量
*
* @return 转出数量
*/
public UFDouble getZc_num () {
return (UFDouble) this.getAttributeValue( CikazongBVO.ZC_NUM);
 } 

/** 
* 设置转出数量
*
* @param zc_num 转出数量
*/
public void setZc_num ( UFDouble zc_num) {
this.setAttributeValue( CikazongBVO.ZC_NUM,zc_num);
 } 

/** 
* 获取转入金额
*
* @return 转入金额
*/
public UFDouble getZr_money () {
return (UFDouble) this.getAttributeValue( CikazongBVO.ZR_MONEY);
 } 

/** 
* 设置转入金额
*
* @param zr_money 转入金额
*/
public void setZr_money ( UFDouble zr_money) {
this.setAttributeValue( CikazongBVO.ZR_MONEY,zr_money);
 } 

/** 
* 获取转入数量
*
* @return 转入数量
*/
public UFDouble getZr_num () {
return (UFDouble) this.getAttributeValue( CikazongBVO.ZR_NUM);
 } 

/** 
* 设置转入数量
*
* @param zr_num 转入数量
*/
public void setZr_num ( UFDouble zr_num) {
this.setAttributeValue( CikazongBVO.ZR_NUM,zr_num);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_CikazongBVO");
  }
}