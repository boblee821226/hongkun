package nc.vo.hkjt.arap.bill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillBVO extends SuperVO {
/**
*应收账户
*/
public static final String ACCNT="accnt";
/**
*账务标识
*/
public static final String ACT_TAG="act_tag";
/**
*审核标识
*/
public static final String AUDIT_TAG="audit_tag";
/**
*余额
*/
public static final String BALANCE9="balance9";
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
*消费核销
*/
public static final String CHARGE9="charge9";
/**
*结账单号
*/
public static final String CLOSE_ID="close_id";
/**
*入账时间
*/
public static final String CREATE_DATETIME="create_datetime";
/**
*操作用户名
*/
public static final String CREATE_USER="create_user";
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
*业务日期
*/
public static final String GEN_DATE="gen_date";
/**
*客人姓名
*/
public static final String GUEST_NAME="guest_name";
/**
*绿云id
*/
public static final String ID="id";
/**
*模块代码
*/
public static final String MODU_CODE="modu_code";
/**
*nc对账
*/
public static final String NC_DZ_01="nc_dz_01";
/**
*nc挂账审计
*/
public static final String NC_GZSJ_01="nc_gzsj_01";
/**
*nc核销
*/
public static final String NC_HX_01="nc_hx_01";
/**
*nc结算审计
*/
public static final String NC_JSSJ_01="nc_jssj_01";
/**
*nc内部往来
*/
public static final String NC_NBWL_01="nc_nbwl_01";
/**
*nc退款
*/
public static final String NC_TK_01="nc_tk_01";
/**
*nc佣金发票
*/
public static final String NC_YJFP_01="nc_yjfp_01";
/**
*账次
*/
public static final String NUMBER_LY="number_ly";
/**
*付款
*/
public static final String PAY="pay";
/**
*付款核销
*/
public static final String PAY9="pay9";
/**
*上层单据主键
*/
public static final String PK_HK_ARAP_BILL="pk_hk_arap_bill";
/**
*账务数据pk
*/
public static final String PK_HK_ARAP_BILL_B="pk_hk_arap_bill_b";
/**
*房间号
*/
public static final String RMNO="rmno";
/**
*分账号
*/
public static final String SUBACCNT="subaccnt";
/**
*账项代码
*/
public static final String TA_CODE="ta_code";
/**
*项目名称
*/
public static final String TA_DESCRIPT="ta_descript";
/**
*单号
*/
public static final String TA_NO="ta_no";
/**
*摘要
*/
public static final String TA_REMARK="ta_remark";
/**
*转账账号
*/
public static final String TRANS_ACCNT="trans_accnt";
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
* 获取应收账户
*
* @return 应收账户
*/
public String getAccnt () {
return (String) this.getAttributeValue( BillBVO.ACCNT);
 } 

/** 
* 设置应收账户
*
* @param accnt 应收账户
*/
public void setAccnt ( String accnt) {
this.setAttributeValue( BillBVO.ACCNT,accnt);
 } 

/** 
* 获取账务标识
*
* @return 账务标识
*/
public String getAct_tag () {
return (String) this.getAttributeValue( BillBVO.ACT_TAG);
 } 

/** 
* 设置账务标识
*
* @param act_tag 账务标识
*/
public void setAct_tag ( String act_tag) {
this.setAttributeValue( BillBVO.ACT_TAG,act_tag);
 } 

/** 
* 获取审核标识
*
* @return 审核标识
*/
public String getAudit_tag () {
return (String) this.getAttributeValue( BillBVO.AUDIT_TAG);
 } 

/** 
* 设置审核标识
*
* @param audit_tag 审核标识
*/
public void setAudit_tag ( String audit_tag) {
this.setAttributeValue( BillBVO.AUDIT_TAG,audit_tag);
 } 

/** 
* 获取余额
*
* @return 余额
*/
public UFDouble getBalance9 () {
return (UFDouble) this.getAttributeValue( BillBVO.BALANCE9);
 } 

/** 
* 设置余额
*
* @param balance9 余额
*/
public void setBalance9 ( UFDouble balance9) {
this.setAttributeValue( BillBVO.BALANCE9,balance9);
 } 

/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( BillBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( BillBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( BillBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( BillBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( BillBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( BillBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取消费金额
*
* @return 消费金额
*/
public UFDouble getCharge () {
return (UFDouble) this.getAttributeValue( BillBVO.CHARGE);
 } 

/** 
* 设置消费金额
*
* @param charge 消费金额
*/
public void setCharge ( UFDouble charge) {
this.setAttributeValue( BillBVO.CHARGE,charge);
 } 

/** 
* 获取消费核销
*
* @return 消费核销
*/
public UFDouble getCharge9 () {
return (UFDouble) this.getAttributeValue( BillBVO.CHARGE9);
 } 

/** 
* 设置消费核销
*
* @param charge9 消费核销
*/
public void setCharge9 ( UFDouble charge9) {
this.setAttributeValue( BillBVO.CHARGE9,charge9);
 } 

/** 
* 获取结账单号
*
* @return 结账单号
*/
public Integer getClose_id () {
return (Integer) this.getAttributeValue( BillBVO.CLOSE_ID);
 } 

/** 
* 设置结账单号
*
* @param close_id 结账单号
*/
public void setClose_id ( Integer close_id) {
this.setAttributeValue( BillBVO.CLOSE_ID,close_id);
 } 

/** 
* 获取入账时间
*
* @return 入账时间
*/
public String getCreate_datetime () {
return (String) this.getAttributeValue( BillBVO.CREATE_DATETIME);
 } 

/** 
* 设置入账时间
*
* @param create_datetime 入账时间
*/
public void setCreate_datetime ( String create_datetime) {
this.setAttributeValue( BillBVO.CREATE_DATETIME,create_datetime);
 } 

/** 
* 获取操作用户名
*
* @return 操作用户名
*/
public String getCreate_user () {
return (String) this.getAttributeValue( BillBVO.CREATE_USER);
 } 

/** 
* 设置操作用户名
*
* @param create_user 操作用户名
*/
public void setCreate_user ( String create_user) {
this.setAttributeValue( BillBVO.CREATE_USER,create_user);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( BillBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( BillBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( BillBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( BillBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( BillBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( BillBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取业务日期
*
* @return 业务日期
*/
public String getGen_date () {
return (String) this.getAttributeValue( BillBVO.GEN_DATE);
 } 

/** 
* 设置业务日期
*
* @param gen_date 业务日期
*/
public void setGen_date ( String gen_date) {
this.setAttributeValue( BillBVO.GEN_DATE,gen_date);
 } 

/** 
* 获取客人姓名
*
* @return 客人姓名
*/
public String getGuest_name () {
return (String) this.getAttributeValue( BillBVO.GUEST_NAME);
 } 

/** 
* 设置客人姓名
*
* @param guest_name 客人姓名
*/
public void setGuest_name ( String guest_name) {
this.setAttributeValue( BillBVO.GUEST_NAME,guest_name);
 } 

/** 
* 获取绿云id
*
* @return 绿云id
*/
public Integer getId () {
return (Integer) this.getAttributeValue( BillBVO.ID);
 } 

/** 
* 设置绿云id
*
* @param id 绿云id
*/
public void setId ( Integer id) {
this.setAttributeValue( BillBVO.ID,id);
 } 

/** 
* 获取模块代码
*
* @return 模块代码
*/
public String getModu_code () {
return (String) this.getAttributeValue( BillBVO.MODU_CODE);
 } 

/** 
* 设置模块代码
*
* @param modu_code 模块代码
*/
public void setModu_code ( String modu_code) {
this.setAttributeValue( BillBVO.MODU_CODE,modu_code);
 } 

/** 
* 获取nc对账
*
* @return nc对账
*/
public String getNc_dz_01 () {
return (String) this.getAttributeValue( BillBVO.NC_DZ_01);
 } 

/** 
* 设置nc对账
*
* @param nc_dz_01 nc对账
*/
public void setNc_dz_01 ( String nc_dz_01) {
this.setAttributeValue( BillBVO.NC_DZ_01,nc_dz_01);
 } 

/** 
* 获取nc挂账审计
*
* @return nc挂账审计
*/
public String getNc_gzsj_01 () {
return (String) this.getAttributeValue( BillBVO.NC_GZSJ_01);
 } 

/** 
* 设置nc挂账审计
*
* @param nc_gzsj_01 nc挂账审计
*/
public void setNc_gzsj_01 ( String nc_gzsj_01) {
this.setAttributeValue( BillBVO.NC_GZSJ_01,nc_gzsj_01);
 } 

/** 
* 获取nc核销
*
* @return nc核销
*/
public String getNc_hx_01 () {
return (String) this.getAttributeValue( BillBVO.NC_HX_01);
 } 

/** 
* 设置nc核销
*
* @param nc_hx_01 nc核销
*/
public void setNc_hx_01 ( String nc_hx_01) {
this.setAttributeValue( BillBVO.NC_HX_01,nc_hx_01);
 } 

/** 
* 获取nc结算审计
*
* @return nc结算审计
*/
public String getNc_jssj_01 () {
return (String) this.getAttributeValue( BillBVO.NC_JSSJ_01);
 } 

/** 
* 设置nc结算审计
*
* @param nc_jssj_01 nc结算审计
*/
public void setNc_jssj_01 ( String nc_jssj_01) {
this.setAttributeValue( BillBVO.NC_JSSJ_01,nc_jssj_01);
 } 

/** 
* 获取nc内部往来
*
* @return nc内部往来
*/
public String getNc_nbwl_01 () {
return (String) this.getAttributeValue( BillBVO.NC_NBWL_01);
 } 

/** 
* 设置nc内部往来
*
* @param nc_nbwl_01 nc内部往来
*/
public void setNc_nbwl_01 ( String nc_nbwl_01) {
this.setAttributeValue( BillBVO.NC_NBWL_01,nc_nbwl_01);
 } 

/** 
* 获取nc退款
*
* @return nc退款
*/
public String getNc_tk_01 () {
return (String) this.getAttributeValue( BillBVO.NC_TK_01);
 } 

/** 
* 设置nc退款
*
* @param nc_tk_01 nc退款
*/
public void setNc_tk_01 ( String nc_tk_01) {
this.setAttributeValue( BillBVO.NC_TK_01,nc_tk_01);
 } 

/** 
* 获取nc佣金发票
*
* @return nc佣金发票
*/
public String getNc_yjfp_01 () {
return (String) this.getAttributeValue( BillBVO.NC_YJFP_01);
 } 

/** 
* 设置nc佣金发票
*
* @param nc_yjfp_01 nc佣金发票
*/
public void setNc_yjfp_01 ( String nc_yjfp_01) {
this.setAttributeValue( BillBVO.NC_YJFP_01,nc_yjfp_01);
 } 

/** 
* 获取账次
*
* @return 账次
*/
public Integer getNumber_ly () {
return (Integer) this.getAttributeValue( BillBVO.NUMBER_LY);
 } 

/** 
* 设置账次
*
* @param number_ly 账次
*/
public void setNumber_ly ( Integer number_ly) {
this.setAttributeValue( BillBVO.NUMBER_LY,number_ly);
 } 

/** 
* 获取付款
*
* @return 付款
*/
public UFDouble getPay () {
return (UFDouble) this.getAttributeValue( BillBVO.PAY);
 } 

/** 
* 设置付款
*
* @param pay 付款
*/
public void setPay ( UFDouble pay) {
this.setAttributeValue( BillBVO.PAY,pay);
 } 

/** 
* 获取付款核销
*
* @return 付款核销
*/
public UFDouble getPay9 () {
return (UFDouble) this.getAttributeValue( BillBVO.PAY9);
 } 

/** 
* 设置付款核销
*
* @param pay9 付款核销
*/
public void setPay9 ( UFDouble pay9) {
this.setAttributeValue( BillBVO.PAY9,pay9);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_arap_bill () {
return (String) this.getAttributeValue( BillBVO.PK_HK_ARAP_BILL);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_arap_bill 上层单据主键
*/
public void setPk_hk_arap_bill ( String pk_hk_arap_bill) {
this.setAttributeValue( BillBVO.PK_HK_ARAP_BILL,pk_hk_arap_bill);
 } 

/** 
* 获取账务数据pk
*
* @return 账务数据pk
*/
public String getPk_hk_arap_bill_b () {
return (String) this.getAttributeValue( BillBVO.PK_HK_ARAP_BILL_B);
 } 

/** 
* 设置账务数据pk
*
* @param pk_hk_arap_bill_b 账务数据pk
*/
public void setPk_hk_arap_bill_b ( String pk_hk_arap_bill_b) {
this.setAttributeValue( BillBVO.PK_HK_ARAP_BILL_B,pk_hk_arap_bill_b);
 } 

/** 
* 获取房间号
*
* @return 房间号
*/
public String getRmno () {
return (String) this.getAttributeValue( BillBVO.RMNO);
 } 

/** 
* 设置房间号
*
* @param rmno 房间号
*/
public void setRmno ( String rmno) {
this.setAttributeValue( BillBVO.RMNO,rmno);
 } 

/** 
* 获取分账号
*
* @return 分账号
*/
public Integer getSubaccnt () {
return (Integer) this.getAttributeValue( BillBVO.SUBACCNT);
 } 

/** 
* 设置分账号
*
* @param subaccnt 分账号
*/
public void setSubaccnt ( Integer subaccnt) {
this.setAttributeValue( BillBVO.SUBACCNT,subaccnt);
 } 

/** 
* 获取账项代码
*
* @return 账项代码
*/
public String getTa_code () {
return (String) this.getAttributeValue( BillBVO.TA_CODE);
 } 

/** 
* 设置账项代码
*
* @param ta_code 账项代码
*/
public void setTa_code ( String ta_code) {
this.setAttributeValue( BillBVO.TA_CODE,ta_code);
 } 

/** 
* 获取项目名称
*
* @return 项目名称
*/
public String getTa_descript () {
return (String) this.getAttributeValue( BillBVO.TA_DESCRIPT);
 } 

/** 
* 设置项目名称
*
* @param ta_descript 项目名称
*/
public void setTa_descript ( String ta_descript) {
this.setAttributeValue( BillBVO.TA_DESCRIPT,ta_descript);
 } 

/** 
* 获取单号
*
* @return 单号
*/
public String getTa_no () {
return (String) this.getAttributeValue( BillBVO.TA_NO);
 } 

/** 
* 设置单号
*
* @param ta_no 单号
*/
public void setTa_no ( String ta_no) {
this.setAttributeValue( BillBVO.TA_NO,ta_no);
 } 

/** 
* 获取摘要
*
* @return 摘要
*/
public String getTa_remark () {
return (String) this.getAttributeValue( BillBVO.TA_REMARK);
 } 

/** 
* 设置摘要
*
* @param ta_remark 摘要
*/
public void setTa_remark ( String ta_remark) {
this.setAttributeValue( BillBVO.TA_REMARK,ta_remark);
 } 

/** 
* 获取转账账号
*
* @return 转账账号
*/
public Integer getTrans_accnt () {
return (Integer) this.getAttributeValue( BillBVO.TRANS_ACCNT);
 } 

/** 
* 设置转账账号
*
* @param trans_accnt 转账账号
*/
public void setTrans_accnt ( Integer trans_accnt) {
this.setAttributeValue( BillBVO.TRANS_ACCNT,trans_accnt);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( BillBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( BillBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( BillBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( BillBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( BillBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( BillBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( BillBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( BillBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( BillBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( BillBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( BillBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( BillBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( BillBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( BillBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( BillBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( BillBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( BillBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( BillBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( BillBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( BillBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( BillBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( BillBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取自定义项11
*
* @return 自定义项11
*/
public String getVbdef11 () {
return (String) this.getAttributeValue( BillBVO.VBDEF11);
 } 

/** 
* 设置自定义项11
*
* @param vbdef11 自定义项11
*/
public void setVbdef11 ( String vbdef11) {
this.setAttributeValue( BillBVO.VBDEF11,vbdef11);
 } 

/** 
* 获取自定义项12
*
* @return 自定义项12
*/
public String getVbdef12 () {
return (String) this.getAttributeValue( BillBVO.VBDEF12);
 } 

/** 
* 设置自定义项12
*
* @param vbdef12 自定义项12
*/
public void setVbdef12 ( String vbdef12) {
this.setAttributeValue( BillBVO.VBDEF12,vbdef12);
 } 

/** 
* 获取自定义项13
*
* @return 自定义项13
*/
public String getVbdef13 () {
return (String) this.getAttributeValue( BillBVO.VBDEF13);
 } 

/** 
* 设置自定义项13
*
* @param vbdef13 自定义项13
*/
public void setVbdef13 ( String vbdef13) {
this.setAttributeValue( BillBVO.VBDEF13,vbdef13);
 } 

/** 
* 获取自定义项14
*
* @return 自定义项14
*/
public String getVbdef14 () {
return (String) this.getAttributeValue( BillBVO.VBDEF14);
 } 

/** 
* 设置自定义项14
*
* @param vbdef14 自定义项14
*/
public void setVbdef14 ( String vbdef14) {
this.setAttributeValue( BillBVO.VBDEF14,vbdef14);
 } 

/** 
* 获取自定义项15
*
* @return 自定义项15
*/
public String getVbdef15 () {
return (String) this.getAttributeValue( BillBVO.VBDEF15);
 } 

/** 
* 设置自定义项15
*
* @param vbdef15 自定义项15
*/
public void setVbdef15 ( String vbdef15) {
this.setAttributeValue( BillBVO.VBDEF15,vbdef15);
 } 

/** 
* 获取自定义项16
*
* @return 自定义项16
*/
public String getVbdef16 () {
return (String) this.getAttributeValue( BillBVO.VBDEF16);
 } 

/** 
* 设置自定义项16
*
* @param vbdef16 自定义项16
*/
public void setVbdef16 ( String vbdef16) {
this.setAttributeValue( BillBVO.VBDEF16,vbdef16);
 } 

/** 
* 获取自定义项17
*
* @return 自定义项17
*/
public String getVbdef17 () {
return (String) this.getAttributeValue( BillBVO.VBDEF17);
 } 

/** 
* 设置自定义项17
*
* @param vbdef17 自定义项17
*/
public void setVbdef17 ( String vbdef17) {
this.setAttributeValue( BillBVO.VBDEF17,vbdef17);
 } 

/** 
* 获取自定义项18
*
* @return 自定义项18
*/
public String getVbdef18 () {
return (String) this.getAttributeValue( BillBVO.VBDEF18);
 } 

/** 
* 设置自定义项18
*
* @param vbdef18 自定义项18
*/
public void setVbdef18 ( String vbdef18) {
this.setAttributeValue( BillBVO.VBDEF18,vbdef18);
 } 

/** 
* 获取自定义项19
*
* @return 自定义项19
*/
public String getVbdef19 () {
return (String) this.getAttributeValue( BillBVO.VBDEF19);
 } 

/** 
* 设置自定义项19
*
* @param vbdef19 自定义项19
*/
public void setVbdef19 ( String vbdef19) {
this.setAttributeValue( BillBVO.VBDEF19,vbdef19);
 } 

/** 
* 获取自定义项20
*
* @return 自定义项20
*/
public String getVbdef20 () {
return (String) this.getAttributeValue( BillBVO.VBDEF20);
 } 

/** 
* 设置自定义项20
*
* @param vbdef20 自定义项20
*/
public void setVbdef20 ( String vbdef20) {
this.setAttributeValue( BillBVO.VBDEF20,vbdef20);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( BillBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( BillBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( BillBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( BillBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( BillBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( BillBVO.VROWNO,vrowno);
 } 

/** 
* 获取上层单据号
*
* @return 上层单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( BillBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上层单据号
*
* @param vsourcebillcode 上层单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( BillBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_arap_billBVO");
  }
}