package nc.vo.hkjt.fapiao.bill;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BillFpHVO extends SuperVO {
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
*开票客户
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
*集团
*/
public static final String PK_GROUP="pk_group";
/**
*账单开票主pk
*/
public static final String PK_HK_FAPIAO_BILL="pk_hk_fapiao_bill";
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
return (String) this.getAttributeValue( BillFpHVO.APPROVER);
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.setAttributeValue( BillFpHVO.APPROVER,approver);
 } 

/** 
* 获取制单时间
*
* @return 制单时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( BillFpHVO.CREATIONTIME);
 } 

/** 
* 设置制单时间
*
* @param creationtime 制单时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( BillFpHVO.CREATIONTIME,creationtime);
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getCreator () {
return (String) this.getAttributeValue( BillFpHVO.CREATOR);
 } 

/** 
* 设置制单人
*
* @param creator 制单人
*/
public void setCreator ( String creator) {
this.setAttributeValue( BillFpHVO.CREATOR,creator);
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( BillFpHVO.DBILLDATE);
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( BillFpHVO.DBILLDATE,dbilldate);
 } 

/** 
* 获取发票代码
*
* @return 发票代码
*/
public String getFpdm () {
return (String) this.getAttributeValue( BillFpHVO.FPDM);
 } 

/** 
* 设置发票代码
*
* @param fpdm 发票代码
*/
public void setFpdm ( String fpdm) {
this.setAttributeValue( BillFpHVO.FPDM,fpdm);
 } 

/** 
* 获取发票号码
*
* @return 发票号码
*/
public String getFphm () {
return (String) this.getAttributeValue( BillFpHVO.FPHM);
 } 

/** 
* 设置发票号码
*
* @param fphm 发票号码
*/
public void setFphm ( String fphm) {
this.setAttributeValue( BillFpHVO.FPHM,fphm);
 } 

/** 
* 获取发票金额
*
* @return 发票金额
*/
public UFDouble getFpje () {
return (UFDouble) this.getAttributeValue( BillFpHVO.FPJE);
 } 

/** 
* 设置发票金额
*
* @param fpje 发票金额
*/
public void setFpje ( UFDouble fpje) {
this.setAttributeValue( BillFpHVO.FPJE,fpje);
 } 

/** 
* 获取发票类型
*
* @return 发票类型
*/
public String getFplx () {
return (String) this.getAttributeValue( BillFpHVO.FPLX);
 } 

/** 
* 设置发票类型
*
* @param fplx 发票类型
*/
public void setFplx ( String fplx) {
this.setAttributeValue( BillFpHVO.FPLX,fplx);
 } 

/** 
* 获取发票状态
*
* @return 发票状态
* @see String
*/
public String getFpzt () {
return (String) this.getAttributeValue( BillFpHVO.FPZT);
 } 

/** 
* 设置发票状态
*
* @param fpzt 发票状态
* @see String
*/
public void setFpzt ( String fpzt) {
this.setAttributeValue( BillFpHVO.FPZT,fpzt);
 } 

/** 
* 获取合计金额
*
* @return 合计金额
*/
public UFDouble getI_je () {
return (UFDouble) this.getAttributeValue( BillFpHVO.I_JE);
 } 

/** 
* 设置合计金额
*
* @param i_je 合计金额
*/
public void setI_je ( UFDouble i_je) {
this.setAttributeValue( BillFpHVO.I_JE,i_je);
 } 

/** 
* 获取价税合计
*
* @return 价税合计
*/
public UFDouble getI_jshj () {
return (UFDouble) this.getAttributeValue( BillFpHVO.I_JSHJ);
 } 

/** 
* 设置价税合计
*
* @param i_jshj 价税合计
*/
public void setI_jshj ( UFDouble i_jshj) {
this.setAttributeValue( BillFpHVO.I_JSHJ,i_jshj);
 } 

/** 
* 获取客户识别号
*
* @return 客户识别号
*/
public String getI_khsbh () {
return (String) this.getAttributeValue( BillFpHVO.I_KHSBH);
 } 

/** 
* 设置客户识别号
*
* @param i_khsbh 客户识别号
*/
public void setI_khsbh ( String i_khsbh) {
this.setAttributeValue( BillFpHVO.I_KHSBH,i_khsbh);
 } 

/** 
* 获取开票客户
*
* @return 开票客户
*/
public String getI_kpkh () {
return (String) this.getAttributeValue( BillFpHVO.I_KPKH);
 } 

/** 
* 设置开票客户
*
* @param i_kpkh 开票客户
*/
public void setI_kpkh ( String i_kpkh) {
this.setAttributeValue( BillFpHVO.I_KPKH,i_kpkh);
 } 

/** 
* 获取开票日期
*
* @return 开票日期
*/
public UFDate getI_kprq () {
return (UFDate) this.getAttributeValue( BillFpHVO.I_KPRQ);
 } 

/** 
* 设置开票日期
*
* @param i_kprq 开票日期
*/
public void setI_kprq ( UFDate i_kprq) {
this.setAttributeValue( BillFpHVO.I_KPRQ,i_kprq);
 } 

/** 
* 获取税额
*
* @return 税额
*/
public UFDouble getI_se () {
return (UFDouble) this.getAttributeValue( BillFpHVO.I_SE);
 } 

/** 
* 设置税额
*
* @param i_se 税额
*/
public void setI_se ( UFDouble i_se) {
this.setAttributeValue( BillFpHVO.I_SE,i_se);
 } 

/** 
* 获取商品名称
*
* @return 商品名称
*/
public String getI_spmc () {
return (String) this.getAttributeValue( BillFpHVO.I_SPMC);
 } 

/** 
* 设置商品名称
*
* @param i_spmc 商品名称
*/
public void setI_spmc ( String i_spmc) {
this.setAttributeValue( BillFpHVO.I_SPMC,i_spmc);
 } 

/** 
* 获取原发票代码
*
* @return 原发票代码
*/
public String getI_yfpdm () {
return (String) this.getAttributeValue( BillFpHVO.I_YFPDM);
 } 

/** 
* 设置原发票代码
*
* @param i_yfpdm 原发票代码
*/
public void setI_yfpdm ( String i_yfpdm) {
this.setAttributeValue( BillFpHVO.I_YFPDM,i_yfpdm);
 } 

/** 
* 获取原发票号码
*
* @return 原发票号码
*/
public String getI_yfphm () {
return (String) this.getAttributeValue( BillFpHVO.I_YFPHM);
 } 

/** 
* 设置原发票号码
*
* @param i_yfphm 原发票号码
*/
public void setI_yfphm ( String i_yfphm) {
this.setAttributeValue( BillFpHVO.I_YFPHM,i_yfphm);
 } 

/** 
* 获取作废日期
*
* @return 作废日期
*/
public UFDate getI_zfrq () {
return (UFDate) this.getAttributeValue( BillFpHVO.I_ZFRQ);
 } 

/** 
* 设置作废日期
*
* @param i_zfrq 作废日期
*/
public void setI_zfrq ( UFDate i_zfrq) {
this.setAttributeValue( BillFpHVO.I_ZFRQ,i_zfrq);
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( BillFpHVO.IBILLSTATUS);
 } 

/** 
* 设置单据状态
*
* @param ibillstatus 单据状态
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( BillFpHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( BillFpHVO.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( BillFpHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( BillFpHVO.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( BillFpHVO.MODIFIER,modifier);
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( BillFpHVO.PK_BUSITYPE);
 } 

/** 
* 设置业务类型
*
* @param pk_busitype 业务类型
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( BillFpHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( BillFpHVO.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( BillFpHVO.PK_GROUP,pk_group);
 } 

/** 
* 获取账单开票主pk
*
* @return 账单开票主pk
*/
public String getPk_hk_fapiao_bill () {
return (String) this.getAttributeValue( BillFpHVO.PK_HK_FAPIAO_BILL);
 } 

/** 
* 设置账单开票主pk
*
* @param pk_hk_fapiao_bill 账单开票主pk
*/
public void setPk_hk_fapiao_bill ( String pk_hk_fapiao_bill) {
this.setAttributeValue( BillFpHVO.PK_HK_FAPIAO_BILL,pk_hk_fapiao_bill);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( BillFpHVO.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( BillFpHVO.PK_ORG,pk_org);
 } 

/** 
* 获取组织v
*
* @return 组织v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( BillFpHVO.PK_ORG_V);
 } 

/** 
* 设置组织v
*
* @param pk_org_v 组织v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( BillFpHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( BillFpHVO.TAUDITTIME);
 } 

/** 
* 设置审核时间
*
* @param taudittime 审核时间
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( BillFpHVO.TAUDITTIME,taudittime);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( BillFpHVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( BillFpHVO.TS,ts);
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( BillFpHVO.VAPPROVENOTE);
 } 

/** 
* 设置审批批语
*
* @param vapprovenote 审批批语
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( BillFpHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return (String) this.getAttributeValue( BillFpHVO.VBILLCODE);
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( BillFpHVO.VBILLCODE,vbillcode);
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( BillFpHVO.VBILLTYPECODE);
 } 

/** 
* 设置单据类型
*
* @param vbilltypecode 单据类型
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( BillFpHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vdef01 自定义项01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( BillFpHVO.VDEF01,vdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vdef02 自定义项02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( BillFpHVO.VDEF02,vdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vdef03 自定义项03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( BillFpHVO.VDEF03,vdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vdef04 自定义项04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( BillFpHVO.VDEF04,vdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vdef05 自定义项05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( BillFpHVO.VDEF05,vdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vdef06 自定义项06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( BillFpHVO.VDEF06,vdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vdef07 自定义项07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( BillFpHVO.VDEF07,vdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vdef08 自定义项08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( BillFpHVO.VDEF08,vdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vdef09 自定义项09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( BillFpHVO.VDEF09,vdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( BillFpHVO.VDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( BillFpHVO.VDEF10,vdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return (String) this.getAttributeValue( BillFpHVO.VMEMO);
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( BillFpHVO.VMEMO,vmemo);
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( BillFpHVO.VTRANTYPECODE);
 } 

/** 
* 设置交易类型
*
* @param vtrantypecode 交易类型
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( BillFpHVO.VTRANTYPECODE,vtrantypecode);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hk_fp_BillFpHVO");
  }
}