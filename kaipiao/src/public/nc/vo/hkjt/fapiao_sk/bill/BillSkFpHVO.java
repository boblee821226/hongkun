package nc.vo.hkjt.fapiao_sk.bill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillSkFpHVO extends SuperVO {
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
*发票代码
*/
public static final String FPDM="fpdm";
/**
*发票号码
*/
public static final String FPHM="fphm";
/**
*发票金额
*/
public static final String FPJE="fpje";
/**
*发票类型
*/
public static final String FPLX="fplx";
/**
*发票状态
*/
public static final String FPZT="fpzt";
/**
*合计金额
*/
public static final String I_JE="i_je";
/**
*价税合计
*/
public static final String I_JSHJ="i_jshj";
/**
*客户识别号
*/
public static final String I_KHSBH="i_khsbh";
/**
*开票客户2
*/
public static final String I_KPKH="i_kpkh";
/**
*开票日期
*/
public static final String I_KPRQ="i_kprq";
/**
*税额
*/
public static final String I_SE="i_se";
/**
*商品名称
*/
public static final String I_SPMC="i_spmc";
/**
*原发票代码
*/
public static final String I_YFPDM="i_yfpdm";
/**
*原发票号码
*/
public static final String I_YFPHM="i_yfphm";
/**
*作废日期
*/
public static final String I_ZFRQ="i_zfrq";
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
*业务类型
*/
public static final String PK_BUSITYPE="pk_busitype";
/**
*开票客户
*/
public static final String PK_CUSTOMER="pk_customer";
/**
*集团
*/
public static final String PK_GROUP="pk_group";
/**
*收款开票主表pk
*/
public static final String PK_HK_FAPIAO_SK_BILL="pk_hk_fapiao_sk_bill";
/**
*组织
*/
public static final String PK_ORG="pk_org";
/**
*组织v
*/
public static final String PK_ORG_V="pk_org_v";
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
*交易类型
*/
public static final String VTRANTYPECODE="vtrantypecode";
/** 
* 获取审核人
*
* @return 审核人
*/
public String getApprover () {
return (String) this.getAttributeValue( BillSkFpHVO.APPROVER);
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.setAttributeValue( BillSkFpHVO.APPROVER,approver);
 } 

/** 
* 获取制单时间
*
* @return 制单时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( BillSkFpHVO.CREATIONTIME);
 } 

/** 
* 设置制单时间
*
* @param creationtime 制单时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( BillSkFpHVO.CREATIONTIME,creationtime);
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getCreator () {
return (String) this.getAttributeValue( BillSkFpHVO.CREATOR);
 } 

/** 
* 设置制单人
*
* @param creator 制单人
*/
public void setCreator ( String creator) {
this.setAttributeValue( BillSkFpHVO.CREATOR,creator);
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( BillSkFpHVO.DBILLDATE);
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( BillSkFpHVO.DBILLDATE,dbilldate);
 } 

/** 
* 获取发票代码
*
* @return 发票代码
*/
public String getFpdm () {
return (String) this.getAttributeValue( BillSkFpHVO.FPDM);
 } 

/** 
* 设置发票代码
*
* @param fpdm 发票代码
*/
public void setFpdm ( String fpdm) {
this.setAttributeValue( BillSkFpHVO.FPDM,fpdm);
 } 

/** 
* 获取发票号码
*
* @return 发票号码
*/
public String getFphm () {
return (String) this.getAttributeValue( BillSkFpHVO.FPHM);
 } 

/** 
* 设置发票号码
*
* @param fphm 发票号码
*/
public void setFphm ( String fphm) {
this.setAttributeValue( BillSkFpHVO.FPHM,fphm);
 } 

/** 
* 获取发票金额
*
* @return 发票金额
*/
public UFDouble getFpje () {
return (UFDouble) this.getAttributeValue( BillSkFpHVO.FPJE);
 } 

/** 
* 设置发票金额
*
* @param fpje 发票金额
*/
public void setFpje ( UFDouble fpje) {
this.setAttributeValue( BillSkFpHVO.FPJE,fpje);
 } 

/** 
* 获取发票类型
*
* @return 发票类型
*/
public String getFplx () {
return (String) this.getAttributeValue( BillSkFpHVO.FPLX);
 } 

/** 
* 设置发票类型
*
* @param fplx 发票类型
*/
public void setFplx ( String fplx) {
this.setAttributeValue( BillSkFpHVO.FPLX,fplx);
 } 

/** 
* 获取发票状态
*
* @return 发票状态
* @see String
*/
public String getFpzt () {
return (String) this.getAttributeValue( BillSkFpHVO.FPZT);
 } 

/** 
* 设置发票状态
*
* @param fpzt 发票状态
* @see String
*/
public void setFpzt ( String fpzt) {
this.setAttributeValue( BillSkFpHVO.FPZT,fpzt);
 } 

/** 
* 获取合计金额
*
* @return 合计金额
*/
public UFDouble getI_je () {
return (UFDouble) this.getAttributeValue( BillSkFpHVO.I_JE);
 } 

/** 
* 设置合计金额
*
* @param i_je 合计金额
*/
public void setI_je ( UFDouble i_je) {
this.setAttributeValue( BillSkFpHVO.I_JE,i_je);
 } 

/** 
* 获取价税合计
*
* @return 价税合计
*/
public UFDouble getI_jshj () {
return (UFDouble) this.getAttributeValue( BillSkFpHVO.I_JSHJ);
 } 

/** 
* 设置价税合计
*
* @param i_jshj 价税合计
*/
public void setI_jshj ( UFDouble i_jshj) {
this.setAttributeValue( BillSkFpHVO.I_JSHJ,i_jshj);
 } 

/** 
* 获取客户识别号
*
* @return 客户识别号
*/
public String getI_khsbh () {
return (String) this.getAttributeValue( BillSkFpHVO.I_KHSBH);
 } 

/** 
* 设置客户识别号
*
* @param i_khsbh 客户识别号
*/
public void setI_khsbh ( String i_khsbh) {
this.setAttributeValue( BillSkFpHVO.I_KHSBH,i_khsbh);
 } 

/** 
* 获取开票客户2
*
* @return 开票客户2
*/
public String getI_kpkh () {
return (String) this.getAttributeValue( BillSkFpHVO.I_KPKH);
 } 

/** 
* 设置开票客户2
*
* @param i_kpkh 开票客户2
*/
public void setI_kpkh ( String i_kpkh) {
this.setAttributeValue( BillSkFpHVO.I_KPKH,i_kpkh);
 } 

/** 
* 获取开票日期
*
* @return 开票日期
*/
public UFDate getI_kprq () {
return (UFDate) this.getAttributeValue( BillSkFpHVO.I_KPRQ);
 } 

/** 
* 设置开票日期
*
* @param i_kprq 开票日期
*/
public void setI_kprq ( UFDate i_kprq) {
this.setAttributeValue( BillSkFpHVO.I_KPRQ,i_kprq);
 } 

/** 
* 获取税额
*
* @return 税额
*/
public UFDouble getI_se () {
return (UFDouble) this.getAttributeValue( BillSkFpHVO.I_SE);
 } 

/** 
* 设置税额
*
* @param i_se 税额
*/
public void setI_se ( UFDouble i_se) {
this.setAttributeValue( BillSkFpHVO.I_SE,i_se);
 } 

/** 
* 获取商品名称
*
* @return 商品名称
*/
public String getI_spmc () {
return (String) this.getAttributeValue( BillSkFpHVO.I_SPMC);
 } 

/** 
* 设置商品名称
*
* @param i_spmc 商品名称
*/
public void setI_spmc ( String i_spmc) {
this.setAttributeValue( BillSkFpHVO.I_SPMC,i_spmc);
 } 

/** 
* 获取原发票代码
*
* @return 原发票代码
*/
public String getI_yfpdm () {
return (String) this.getAttributeValue( BillSkFpHVO.I_YFPDM);
 } 

/** 
* 设置原发票代码
*
* @param i_yfpdm 原发票代码
*/
public void setI_yfpdm ( String i_yfpdm) {
this.setAttributeValue( BillSkFpHVO.I_YFPDM,i_yfpdm);
 } 

/** 
* 获取原发票号码
*
* @return 原发票号码
*/
public String getI_yfphm () {
return (String) this.getAttributeValue( BillSkFpHVO.I_YFPHM);
 } 

/** 
* 设置原发票号码
*
* @param i_yfphm 原发票号码
*/
public void setI_yfphm ( String i_yfphm) {
this.setAttributeValue( BillSkFpHVO.I_YFPHM,i_yfphm);
 } 

/** 
* 获取作废日期
*
* @return 作废日期
*/
public UFDate getI_zfrq () {
return (UFDate) this.getAttributeValue( BillSkFpHVO.I_ZFRQ);
 } 

/** 
* 设置作废日期
*
* @param i_zfrq 作废日期
*/
public void setI_zfrq ( UFDate i_zfrq) {
this.setAttributeValue( BillSkFpHVO.I_ZFRQ,i_zfrq);
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( BillSkFpHVO.IBILLSTATUS);
 } 

/** 
* 设置单据状态
*
* @param ibillstatus 单据状态
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( BillSkFpHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( BillSkFpHVO.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( BillSkFpHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( BillSkFpHVO.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( BillSkFpHVO.MODIFIER,modifier);
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( BillSkFpHVO.PK_BUSITYPE);
 } 

/** 
* 设置业务类型
*
* @param pk_busitype 业务类型
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( BillSkFpHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* 获取开票客户
*
* @return 开票客户
*/
public String getPk_customer () {
return (String) this.getAttributeValue( BillSkFpHVO.PK_CUSTOMER);
 } 

/** 
* 设置开票客户
*
* @param pk_customer 开票客户
*/
public void setPk_customer ( String pk_customer) {
this.setAttributeValue( BillSkFpHVO.PK_CUSTOMER,pk_customer);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( BillSkFpHVO.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( BillSkFpHVO.PK_GROUP,pk_group);
 } 

/** 
* 获取收款开票主表pk
*
* @return 收款开票主表pk
*/
public String getPk_hk_fapiao_sk_bill () {
return (String) this.getAttributeValue( BillSkFpHVO.PK_HK_FAPIAO_SK_BILL);
 } 

/** 
* 设置收款开票主表pk
*
* @param pk_hk_fapiao_sk_bill 收款开票主表pk
*/
public void setPk_hk_fapiao_sk_bill ( String pk_hk_fapiao_sk_bill) {
this.setAttributeValue( BillSkFpHVO.PK_HK_FAPIAO_SK_BILL,pk_hk_fapiao_sk_bill);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( BillSkFpHVO.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( BillSkFpHVO.PK_ORG,pk_org);
 } 

/** 
* 获取组织v
*
* @return 组织v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( BillSkFpHVO.PK_ORG_V);
 } 

/** 
* 设置组织v
*
* @param pk_org_v 组织v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( BillSkFpHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( BillSkFpHVO.TAUDITTIME);
 } 

/** 
* 设置审核时间
*
* @param taudittime 审核时间
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( BillSkFpHVO.TAUDITTIME,taudittime);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( BillSkFpHVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( BillSkFpHVO.TS,ts);
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( BillSkFpHVO.VAPPROVENOTE);
 } 

/** 
* 设置审批批语
*
* @param vapprovenote 审批批语
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( BillSkFpHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return (String) this.getAttributeValue( BillSkFpHVO.VBILLCODE);
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( BillSkFpHVO.VBILLCODE,vbillcode);
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( BillSkFpHVO.VBILLTYPECODE);
 } 

/** 
* 设置单据类型
*
* @param vbilltypecode 单据类型
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( BillSkFpHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( BillSkFpHVO.VDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vdef01 自定义项01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( BillSkFpHVO.VDEF01,vdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( BillSkFpHVO.VDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vdef02 自定义项02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( BillSkFpHVO.VDEF02,vdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( BillSkFpHVO.VDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vdef03 自定义项03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( BillSkFpHVO.VDEF03,vdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( BillSkFpHVO.VDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vdef04 自定义项04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( BillSkFpHVO.VDEF04,vdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( BillSkFpHVO.VDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vdef05 自定义项05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( BillSkFpHVO.VDEF05,vdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( BillSkFpHVO.VDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vdef06 自定义项06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( BillSkFpHVO.VDEF06,vdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( BillSkFpHVO.VDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vdef07 自定义项07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( BillSkFpHVO.VDEF07,vdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( BillSkFpHVO.VDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vdef08 自定义项08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( BillSkFpHVO.VDEF08,vdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( BillSkFpHVO.VDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vdef09 自定义项09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( BillSkFpHVO.VDEF09,vdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( BillSkFpHVO.VDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( BillSkFpHVO.VDEF10,vdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return (String) this.getAttributeValue( BillSkFpHVO.VMEMO);
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( BillSkFpHVO.VMEMO,vmemo);
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( BillSkFpHVO.VTRANTYPECODE);
 } 

/** 
* 设置交易类型
*
* @param vtrantypecode 交易类型
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( BillSkFpHVO.VTRANTYPECODE,vtrantypecode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_fp_sk_BillSkFpHVO");
  }
}