package nc.vo.hkjt.srgk.jiudian.ruzhangmingxi;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class RzmxBVO extends SuperVO {
/**
*accid
*/
public static final String ACCID="accid";
/**
*部门父ID
*/
public static final String BM_FID="bm_fid";
/**
*部门id
*/
public static final String BM_ID="bm_id";
/**
*部门名称
*/
public static final String BM_NAME="bm_name";
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
*消费金额
*/
public static final String CHARGE="charge";
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
*descript
*/
public static final String DESCRIPT="descript";
/**
*项目编码
*/
public static final String ITEM_CODE="item_code";
/**
*项目名称
*/
public static final String ITEM_NAME="item_name";
/**
*交易类别编码
*/
public String jylb_code;
/**
*交易类别
*/
public static final String JYLB_NAME="jylb_name";
/**
*结账方式id
*/
public static final String JZFS_ID="jzfs_id";
/**
*结账方式名称
*/
public String jzfs_name;
/**
*客户名字
*/
public static final String KHMZ="khmz";
/**
*结账金额
*/
public static final String PAYMENT="payment";
/**
*上层单据主键
*/
public static final String PK_HK_SRGK_JD_RZMX="pk_hk_srgk_jd_rzmx";
/**
*入账明细子表主键
*/
public static final String PK_HK_SRGK_JD_RZMX_B="pk_hk_srgk_jd_rzmx_b";
/**
*refno
*/
public static final String REFNO="refno";
/**
*房间号
*/
public static final String RMNO="rmno";
/**
*房间类型
*/
public static final String RMTYPE_NAME="rmtype_name";
/**
*商品分类id
*/
public static final String SPFL_ID="spfl_id";
/**
*商品分类名称
*/
public static final String SPFL_NAME="spfl_name";
/**
*收入项目id
*/
public static final String SRXM_ID="srxm_id";
/**
*收银员
*/
public static final String SYY_CODE="syy_code";
/**
*结账时间
*/
public static final String TRANSDATE="transdate";
/**
*transid
*/
public static final String TRANSID="transid";
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
*上次单据号
*/
public static final String VSOURCEBILLCODE="vsourcebillcode";
/** 
* 获取accid
*
* @return accid
*/
public String getAccid () {
return (String) this.getAttributeValue( RzmxBVO.ACCID);
 } 

/** 
* 设置accid
*
* @param accid accid
*/
public void setAccid ( String accid) {
this.setAttributeValue( RzmxBVO.ACCID,accid);
 } 

/** 
* 获取部门父ID
*
* @return 部门父ID
*/
public String getBm_fid () {
return (String) this.getAttributeValue( RzmxBVO.BM_FID);
 } 

/** 
* 设置部门父ID
*
* @param bm_fid 部门父ID
*/
public void setBm_fid ( String bm_fid) {
this.setAttributeValue( RzmxBVO.BM_FID,bm_fid);
 } 

/** 
* 获取部门id
*
* @return 部门id
*/
public String getBm_id () {
return (String) this.getAttributeValue( RzmxBVO.BM_ID);
 } 

/** 
* 设置部门id
*
* @param bm_id 部门id
*/
public void setBm_id ( String bm_id) {
this.setAttributeValue( RzmxBVO.BM_ID,bm_id);
 } 

/** 
* 获取部门名称
*
* @return 部门名称
*/
public String getBm_name () {
return (String) this.getAttributeValue( RzmxBVO.BM_NAME);
 } 

/** 
* 设置部门名称
*
* @param bm_name 部门名称
*/
public void setBm_name ( String bm_name) {
this.setAttributeValue( RzmxBVO.BM_NAME,bm_name);
 } 

/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( RzmxBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( RzmxBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( RzmxBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( RzmxBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( RzmxBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( RzmxBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取消费金额
*
* @return 消费金额
*/
public UFDouble getCharge () {
return (UFDouble) this.getAttributeValue( RzmxBVO.CHARGE);
 } 

/** 
* 设置消费金额
*
* @param charge 消费金额
*/
public void setCharge ( UFDouble charge) {
this.setAttributeValue( RzmxBVO.CHARGE,charge);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( RzmxBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( RzmxBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( RzmxBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( RzmxBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( RzmxBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( RzmxBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取descript
*
* @return descript
*/
public String getDescript () {
return (String) this.getAttributeValue( RzmxBVO.DESCRIPT);
 } 

/** 
* 设置descript
*
* @param descript descript
*/
public void setDescript ( String descript) {
this.setAttributeValue( RzmxBVO.DESCRIPT,descript);
 } 

/** 
* 获取项目编码
*
* @return 项目编码
*/
public String getItem_code () {
return (String) this.getAttributeValue( RzmxBVO.ITEM_CODE);
 } 

/** 
* 设置项目编码
*
* @param item_code 项目编码
*/
public void setItem_code ( String item_code) {
this.setAttributeValue( RzmxBVO.ITEM_CODE,item_code);
 } 

/** 
* 获取项目名称
*
* @return 项目名称
*/
public String getItem_name () {
return (String) this.getAttributeValue( RzmxBVO.ITEM_NAME);
 } 

/** 
* 设置项目名称
*
* @param item_name 项目名称
*/
public void setItem_name ( String item_name) {
this.setAttributeValue( RzmxBVO.ITEM_NAME,item_name);
 } 

/** 
* 获取交易类别编码
*
* @return 交易类别编码
*/
public String getJylb_code () {
return this.jylb_code;
 } 

/** 
* 设置交易类别编码
*
* @param jylb_code 交易类别编码
*/
public void setJylb_code ( String jylb_code) {
this.jylb_code=jylb_code;
 } 

/** 
* 获取交易类别
*
* @return 交易类别
*/
public String getJylb_name () {
return (String) this.getAttributeValue( RzmxBVO.JYLB_NAME);
 } 

/** 
* 设置交易类别
*
* @param jylb_name 交易类别
*/
public void setJylb_name ( String jylb_name) {
this.setAttributeValue( RzmxBVO.JYLB_NAME,jylb_name);
 } 

/** 
* 获取结账方式id
*
* @return 结账方式id
*/
public String getJzfs_id () {
return (String) this.getAttributeValue( RzmxBVO.JZFS_ID);
 } 

/** 
* 设置结账方式id
*
* @param jzfs_id 结账方式id
*/
public void setJzfs_id ( String jzfs_id) {
this.setAttributeValue( RzmxBVO.JZFS_ID,jzfs_id);
 } 

/** 
* 获取结账方式名称
*
* @return 结账方式名称
*/
public String getJzfs_name () {
return this.jzfs_name;
 } 

/** 
* 设置结账方式名称
*
* @param jzfs_name 结账方式名称
*/
public void setJzfs_name ( String jzfs_name) {
this.jzfs_name=jzfs_name;
 } 

/** 
* 获取客户名字
*
* @return 客户名字
*/
public String getKhmz () {
return (String) this.getAttributeValue( RzmxBVO.KHMZ);
 } 

/** 
* 设置客户名字
*
* @param khmz 客户名字
*/
public void setKhmz ( String khmz) {
this.setAttributeValue( RzmxBVO.KHMZ,khmz);
 } 

/** 
* 获取结账金额
*
* @return 结账金额
*/
public UFDouble getPayment () {
return (UFDouble) this.getAttributeValue( RzmxBVO.PAYMENT);
 } 

/** 
* 设置结账金额
*
* @param payment 结账金额
*/
public void setPayment ( UFDouble payment) {
this.setAttributeValue( RzmxBVO.PAYMENT,payment);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_srgk_jd_rzmx () {
return (String) this.getAttributeValue( RzmxBVO.PK_HK_SRGK_JD_RZMX);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_srgk_jd_rzmx 上层单据主键
*/
public void setPk_hk_srgk_jd_rzmx ( String pk_hk_srgk_jd_rzmx) {
this.setAttributeValue( RzmxBVO.PK_HK_SRGK_JD_RZMX,pk_hk_srgk_jd_rzmx);
 } 

/** 
* 获取入账明细子表主键
*
* @return 入账明细子表主键
*/
public String getPk_hk_srgk_jd_rzmx_b () {
return (String) this.getAttributeValue( RzmxBVO.PK_HK_SRGK_JD_RZMX_B);
 } 

/** 
* 设置入账明细子表主键
*
* @param pk_hk_srgk_jd_rzmx_b 入账明细子表主键
*/
public void setPk_hk_srgk_jd_rzmx_b ( String pk_hk_srgk_jd_rzmx_b) {
this.setAttributeValue( RzmxBVO.PK_HK_SRGK_JD_RZMX_B,pk_hk_srgk_jd_rzmx_b);
 } 

/** 
* 获取refno
*
* @return refno
*/
public String getRefno () {
return (String) this.getAttributeValue( RzmxBVO.REFNO);
 } 

/** 
* 设置refno
*
* @param refno refno
*/
public void setRefno ( String refno) {
this.setAttributeValue( RzmxBVO.REFNO,refno);
 } 

/** 
* 获取房间号
*
* @return 房间号
*/
public String getRmno () {
return (String) this.getAttributeValue( RzmxBVO.RMNO);
 } 

/** 
* 设置房间号
*
* @param rmno 房间号
*/
public void setRmno ( String rmno) {
this.setAttributeValue( RzmxBVO.RMNO,rmno);
 } 

/** 
* 获取房间类型
*
* @return 房间类型
*/
public String getRmtype_name () {
return (String) this.getAttributeValue( RzmxBVO.RMTYPE_NAME);
 } 

/** 
* 设置房间类型
*
* @param rmtype_name 房间类型
*/
public void setRmtype_name ( String rmtype_name) {
this.setAttributeValue( RzmxBVO.RMTYPE_NAME,rmtype_name);
 } 

/** 
* 获取商品分类id
*
* @return 商品分类id
*/
public String getSpfl_id () {
return (String) this.getAttributeValue( RzmxBVO.SPFL_ID);
 } 

/** 
* 设置商品分类id
*
* @param spfl_id 商品分类id
*/
public void setSpfl_id ( String spfl_id) {
this.setAttributeValue( RzmxBVO.SPFL_ID,spfl_id);
 } 

/** 
* 获取商品分类名称
*
* @return 商品分类名称
*/
public String getSpfl_name () {
return (String) this.getAttributeValue( RzmxBVO.SPFL_NAME);
 } 

/** 
* 设置商品分类名称
*
* @param spfl_name 商品分类名称
*/
public void setSpfl_name ( String spfl_name) {
this.setAttributeValue( RzmxBVO.SPFL_NAME,spfl_name);
 } 

/** 
* 获取收入项目id
*
* @return 收入项目id
*/
public String getSrxm_id () {
return (String) this.getAttributeValue( RzmxBVO.SRXM_ID);
 } 

/** 
* 设置收入项目id
*
* @param srxm_id 收入项目id
*/
public void setSrxm_id ( String srxm_id) {
this.setAttributeValue( RzmxBVO.SRXM_ID,srxm_id);
 } 

/** 
* 获取收银员
*
* @return 收银员
*/
public String getSyy_code () {
return (String) this.getAttributeValue( RzmxBVO.SYY_CODE);
 } 

/** 
* 设置收银员
*
* @param syy_code 收银员
*/
public void setSyy_code ( String syy_code) {
this.setAttributeValue( RzmxBVO.SYY_CODE,syy_code);
 } 

/** 
* 获取结账时间
*
* @return 结账时间
*/
public UFDateTime getTransdate () {
return (UFDateTime) this.getAttributeValue( RzmxBVO.TRANSDATE);
 } 

/** 
* 设置结账时间
*
* @param transdate 结账时间
*/
public void setTransdate ( UFDateTime transdate) {
this.setAttributeValue( RzmxBVO.TRANSDATE,transdate);
 } 

/** 
* 获取transid
*
* @return transid
*/
public String getTransid () {
return (String) this.getAttributeValue( RzmxBVO.TRANSID);
 } 

/** 
* 设置transid
*
* @param transid transid
*/
public void setTransid ( String transid) {
this.setAttributeValue( RzmxBVO.TRANSID,transid);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( RzmxBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( RzmxBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( RzmxBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( RzmxBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( RzmxBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( RzmxBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( RzmxBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( RzmxBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( RzmxBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( RzmxBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( RzmxBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( RzmxBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( RzmxBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( RzmxBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( RzmxBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( RzmxBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( RzmxBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( RzmxBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( RzmxBVO.VROWNO,vrowno);
 } 

/** 
* 获取上次单据号
*
* @return 上次单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( RzmxBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上次单据号
*
* @param vsourcebillcode 上次单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( RzmxBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.RzmxBVO");
  }
}