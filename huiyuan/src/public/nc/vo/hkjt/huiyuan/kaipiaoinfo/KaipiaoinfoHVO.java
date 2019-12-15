package nc.vo.hkjt.huiyuan.kaipiaoinfo;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class KaipiaoinfoHVO extends SuperVO {
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
*发票号
*/
public static final String FPH="fph";
/**
*发票代码
*/
public static final String FPDM="fpdm";
/**
*发票金额
*/
public static final String FPJE="fpje";
/**
*开票时间
*/
public static final String FPSJ="fpsj";
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
*会员开票主pk
*/
public static final String PK_HK_HUIYUAN_KAIPIAOINFO="pk_hk_huiyuan_kaipiaoinfo";
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
* 获取审核人
*
* @return 审核人
*/
public String getApprover () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.APPROVER);
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.setAttributeValue( KaipiaoinfoHVO.APPROVER,approver);
 } 

/** 
* 获取制单时间
*
* @return 制单时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( KaipiaoinfoHVO.CREATIONTIME);
 } 

/** 
* 设置制单时间
*
* @param creationtime 制单时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( KaipiaoinfoHVO.CREATIONTIME,creationtime);
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getCreator () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.CREATOR);
 } 

/** 
* 设置制单人
*
* @param creator 制单人
*/
public void setCreator ( String creator) {
this.setAttributeValue( KaipiaoinfoHVO.CREATOR,creator);
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( KaipiaoinfoHVO.DBILLDATE);
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( KaipiaoinfoHVO.DBILLDATE,dbilldate);
 } 

/** 
* 获取发票号
*
* @return 发票号
*/
public String getFph () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.FPH);
 } 

/** 
* 设置发票号
*
* @param fph 发票号
*/
public void setFph ( String fph) {
this.setAttributeValue( KaipiaoinfoHVO.FPH,fph);
 } 

/** 
* 获取发票代码
*
* @return 发票代码
*/
public String getFpdm () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.FPDM);
 } 

/** 
* 设置发票代码
*
* @param fpdm 发票代码
*/
public void setFpdm ( String fpdm) {
this.setAttributeValue( KaipiaoinfoHVO.FPDM,fpdm);
 } 

/** 
* 获取发票金额
*
* @return 发票金额
*/
public UFDouble getFpje () {
return (UFDouble) this.getAttributeValue( KaipiaoinfoHVO.FPJE);
 } 

/** 
* 设置发票金额
*
* @param fpje 发票金额
*/
public void setFpje ( UFDouble fpje) {
this.setAttributeValue( KaipiaoinfoHVO.FPJE,fpje);
 } 

/** 
* 获取开票时间
*
* @return 开票时间
*/
public UFDateTime getFpsj () {
return (UFDateTime) this.getAttributeValue( KaipiaoinfoHVO.FPSJ);
 } 

/** 
* 设置开票时间
*
* @param fpsj 开票时间
*/
public void setFpsj ( UFDateTime fpsj) {
this.setAttributeValue( KaipiaoinfoHVO.FPSJ,fpsj);
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( KaipiaoinfoHVO.IBILLSTATUS);
 } 

/** 
* 设置单据状态
*
* @param ibillstatus 单据状态
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( KaipiaoinfoHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( KaipiaoinfoHVO.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( KaipiaoinfoHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( KaipiaoinfoHVO.MODIFIER,modifier);
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.PK_BUSITYPE);
 } 

/** 
* 设置业务类型
*
* @param pk_busitype 业务类型
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( KaipiaoinfoHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( KaipiaoinfoHVO.PK_GROUP,pk_group);
 } 

/** 
* 获取会员开票主pk
*
* @return 会员开票主pk
*/
public String getPk_hk_huiyuan_kaipiaoinfo () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.PK_HK_HUIYUAN_KAIPIAOINFO);
 } 

/** 
* 设置会员开票主pk
*
* @param pk_hk_huiyuan_kaipiaoinfo 会员开票主pk
*/
public void setPk_hk_huiyuan_kaipiaoinfo ( String pk_hk_huiyuan_kaipiaoinfo) {
this.setAttributeValue( KaipiaoinfoHVO.PK_HK_HUIYUAN_KAIPIAOINFO,pk_hk_huiyuan_kaipiaoinfo);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( KaipiaoinfoHVO.PK_ORG,pk_org);
 } 

/** 
* 获取组织v
*
* @return 组织v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.PK_ORG_V);
 } 

/** 
* 设置组织v
*
* @param pk_org_v 组织v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( KaipiaoinfoHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( KaipiaoinfoHVO.TAUDITTIME);
 } 

/** 
* 设置审核时间
*
* @param taudittime 审核时间
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( KaipiaoinfoHVO.TAUDITTIME,taudittime);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( KaipiaoinfoHVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( KaipiaoinfoHVO.TS,ts);
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VAPPROVENOTE);
 } 

/** 
* 设置审批批语
*
* @param vapprovenote 审批批语
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( KaipiaoinfoHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VBILLCODE);
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( KaipiaoinfoHVO.VBILLCODE,vbillcode);
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VBILLTYPECODE);
 } 

/** 
* 设置单据类型
*
* @param vbilltypecode 单据类型
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( KaipiaoinfoHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vdef01 自定义项01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( KaipiaoinfoHVO.VDEF01,vdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vdef02 自定义项02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( KaipiaoinfoHVO.VDEF02,vdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vdef03 自定义项03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( KaipiaoinfoHVO.VDEF03,vdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vdef04 自定义项04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( KaipiaoinfoHVO.VDEF04,vdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vdef05 自定义项05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( KaipiaoinfoHVO.VDEF05,vdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vdef06 自定义项06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( KaipiaoinfoHVO.VDEF06,vdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vdef07 自定义项07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( KaipiaoinfoHVO.VDEF07,vdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vdef08 自定义项08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( KaipiaoinfoHVO.VDEF08,vdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vdef09 自定义项09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( KaipiaoinfoHVO.VDEF09,vdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( KaipiaoinfoHVO.VDEF10,vdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VMEMO);
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( KaipiaoinfoHVO.VMEMO,vmemo);
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.VTRANTYPECODE);
 } 

/** 
* 设置交易类型
*
* @param vtrantypecode 交易类型
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( KaipiaoinfoHVO.VTRANTYPECODE,vtrantypecode);
 } 

/** 
* 获取合计金额
*
* @return 合计金额
*/
public UFDouble getI_je () {
return (UFDouble) this.getAttributeValue( KaipiaoinfoHVO.I_JE);
 } 

/** 
* 设置合计金额
*
* @param i_je 合计金额
*/
public void setI_je ( UFDouble i_je) {
this.setAttributeValue( KaipiaoinfoHVO.I_JE,i_je);
 } 

/** 
* 获取价税合计
*
* @return 价税合计
*/
public UFDouble getI_jshj () {
return (UFDouble) this.getAttributeValue( KaipiaoinfoHVO.I_JSHJ);
 } 

/** 
* 设置价税合计
*
* @param i_jshj 价税合计
*/
public void setI_jshj ( UFDouble i_jshj) {
this.setAttributeValue( KaipiaoinfoHVO.I_JSHJ,i_jshj);
 } 

/** 
* 获取客户识别号
*
* @return 客户识别号
*/
public String getI_khsbh () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.I_KHSBH);
 } 

/** 
* 设置客户识别号
*
* @param i_khsbh 客户识别号
*/
public void setI_khsbh ( String i_khsbh) {
this.setAttributeValue( KaipiaoinfoHVO.I_KHSBH,i_khsbh);
 } 

/** 
* 获取开票客户
*
* @return 开票客户
*/
public String getI_kpkh () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.I_KPKH);
 } 

/** 
* 设置开票客户
*
* @param i_kpkh 开票客户
*/
public void setI_kpkh ( String i_kpkh) {
this.setAttributeValue( KaipiaoinfoHVO.I_KPKH,i_kpkh);
 } 

/** 
* 获取开票日期
*
* @return 开票日期
*/
public UFDate getI_kprq () {
return (UFDate) this.getAttributeValue( KaipiaoinfoHVO.I_KPRQ);
 } 

/** 
* 设置开票日期
*
* @param i_kprq 开票日期
*/
public void setI_kprq ( UFDate i_kprq) {
this.setAttributeValue( KaipiaoinfoHVO.I_KPRQ,i_kprq);
 } 

/** 
* 获取税额
*
* @return 税额
*/
public UFDouble getI_se () {
return (UFDouble) this.getAttributeValue( KaipiaoinfoHVO.I_SE);
 } 

/** 
* 设置税额
*
* @param i_se 税额
*/
public void setI_se ( UFDouble i_se) {
this.setAttributeValue( KaipiaoinfoHVO.I_SE,i_se);
 } 

/** 
* 获取商品名称
*
* @return 商品名称
*/
public String getI_spmc () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.I_SPMC);
 } 

/** 
* 设置商品名称
*
* @param i_spmc 商品名称
*/
public void setI_spmc ( String i_spmc) {
this.setAttributeValue( KaipiaoinfoHVO.I_SPMC,i_spmc);
 } 

/** 
* 获取原发票代码
*
* @return 原发票代码
*/
public String getI_yfpdm () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.I_YFPDM);
 } 

/** 
* 设置原发票代码
*
* @param i_yfpdm 原发票代码
*/
public void setI_yfpdm ( String i_yfpdm) {
this.setAttributeValue( KaipiaoinfoHVO.I_YFPDM,i_yfpdm);
 } 

/** 
* 获取原发票号码
*
* @return 原发票号码
*/
public String getI_yfphm () {
return (String) this.getAttributeValue( KaipiaoinfoHVO.I_YFPHM);
 } 

/** 
* 设置原发票号码
*
* @param i_yfphm 原发票号码
*/
public void setI_yfphm ( String i_yfphm) {
this.setAttributeValue( KaipiaoinfoHVO.I_YFPHM,i_yfphm);
 } 

/** 
* 获取作废日期
*
* @return 作废日期
*/
public UFDate getI_zfrq () {
return (UFDate) this.getAttributeValue( KaipiaoinfoHVO.I_ZFRQ);
 } 

/** 
* 设置作废日期
*
* @param i_zfrq 作废日期
*/
public void setI_zfrq ( UFDate i_zfrq) {
this.setAttributeValue( KaipiaoinfoHVO.I_ZFRQ,i_zfrq);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hy_KaipiaoinfoHVO");
  }
}