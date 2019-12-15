package nc.vo.hkjt.srgk.huiguan.zhangdan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ZhangdanHVO extends SuperVO {
/**
*审核人
*/
public static final String APPROVER="approver";
/**
*班次
*/
public static final String BANCI="banci";
/**
*充卡收入（次卡）
*/
public static final String CHONGKA_CK_SR="chongka_ck_sr";
/**
*充卡应收（次卡）
*/
public static final String CHONGKA_CK_YS="chongka_ck_ys";
/**
*充卡收入（会员卡）
*/
public static final String CHONGKA_HYK_SR="chongka_hyk_sr";
/**
*充卡应收（会员卡）
*/
public static final String CHONGKA_HYK_YS="chongka_hyk_ys";
/**
*制单时间
*/
public static final String CREATIONTIME="creationtime";
/**
*制单人
*/
public static final String CREATOR="creator";
/**
*代金券
*/
public static final String DAIJINQUAN="daijinquan";
/**
*单据日期
*/
public static final String DBILLDATE="dbilldate";
/**
*分区金额
*/
public static final String FENQU="fenqu";
/**
*挂账
*/
public static final String GUAZHANG="guazhang";
/**
*挂账_信息
*/
public static final String GUAZHANG_INFO="guazhang_info";
/**
*会员卡消费金额
*/
public static final String HUIYUANKA="huiyuanka";
/**
*会员卡优惠金额
*/
public static final String HUIYUANKA_BL="huiyuanka_bl";
/**
*会员卡信息
*/
public static final String HUIYUANKA_INFO="huiyuanka_info";
/**
*会员卡实际金额
*/
public static final String HUIYUANKA_SJ="huiyuanka_sj";
/**
*单据状态
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*免单
*/
public static final String MIANDAN="miandan";
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
*主表主键
*/
public static final String PK_HK_DZPT_HG_ZHANGDAN="pk_hk_dzpt_hg_zhangdan";
/**
*组织
*/
public static final String PK_ORG="pk_org";
/**
*组织v
*/
public static final String PK_ORG_V="pk_org_v";
/**
*POS
*/
public static final String POS="pos";
/**
*实收
*/
public static final String SHISHOU="shishou";
/**
*收入
*/
public static final String SHOURU="shouru";
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
*往来
*/
public static final String WANGLAI="wanglai";
/**
*往来_信息
*/
public static final String WANGLAI_INFO="wanglai_info";
/**
*现金
*/
public static final String XIANJIN="xianjin";
/**
*应收
*/
public static final String YINGSHOU="yingshou";
/**
*优惠_其它
*/
public static final String YOUHUI_QT="youhui_qt";
/**
*手工优惠01
*/
public static final String YOUHUI_SG_01="youhui_sg_01";
/**
*手工优惠02
*/
public static final String YOUHUI_SG_02="youhui_sg_02";
/**
*手工优惠03
*/
public static final String YOUHUI_SG_03="youhui_sg_03";
/**
*手工优惠04
*/
public static final String YOUHUI_SG_04="youhui_sg_04";
/**
*手工优惠05
*/
public static final String YOUHUI_SG_05="youhui_sg_05";
/**
*手工优惠06
*/
public static final String YOUHUI_SG_06="youhui_sg_06";
/**
*手工优惠07
*/
public static final String YOUHUI_SG_07="youhui_sg_07";
/**
*手工优惠08
*/
public static final String YOUHUI_SG_08="youhui_sg_08";
/**
*手工优惠09
*/
public static final String YOUHUI_SG_09="youhui_sg_09";
/**
*手工优惠10
*/
public static final String YOUHUI_SG_10="youhui_sg_10";
/**
*支票
*/
public static final String ZHIPIAO="zhipiao";
/** 
* 获取审核人
*
* @return 审核人
*/
public String getApprover () {
return (String) this.getAttributeValue( ZhangdanHVO.APPROVER);
 } 

/** 
* 设置审核人
*
* @param approver 审核人
*/
public void setApprover ( String approver) {
this.setAttributeValue( ZhangdanHVO.APPROVER,approver);
 } 

/** 
* 获取班次
*
* @return 班次
*/
public String getBanci () {
return (String) this.getAttributeValue( ZhangdanHVO.BANCI);
 } 

/** 
* 设置班次
*
* @param banci 班次
*/
public void setBanci ( String banci) {
this.setAttributeValue( ZhangdanHVO.BANCI,banci);
 } 

/** 
* 获取充卡收入（次卡）
*
* @return 充卡收入（次卡）
*/
public UFDouble getChongka_ck_sr () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.CHONGKA_CK_SR);
 } 

/** 
* 设置充卡收入（次卡）
*
* @param chongka_ck_sr 充卡收入（次卡）
*/
public void setChongka_ck_sr ( UFDouble chongka_ck_sr) {
this.setAttributeValue( ZhangdanHVO.CHONGKA_CK_SR,chongka_ck_sr);
 } 

/** 
* 获取充卡应收（次卡）
*
* @return 充卡应收（次卡）
*/
public UFDouble getChongka_ck_ys () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.CHONGKA_CK_YS);
 } 

/** 
* 设置充卡应收（次卡）
*
* @param chongka_ck_ys 充卡应收（次卡）
*/
public void setChongka_ck_ys ( UFDouble chongka_ck_ys) {
this.setAttributeValue( ZhangdanHVO.CHONGKA_CK_YS,chongka_ck_ys);
 } 

/** 
* 获取充卡收入（会员卡）
*
* @return 充卡收入（会员卡）
*/
public UFDouble getChongka_hyk_sr () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.CHONGKA_HYK_SR);
 } 

/** 
* 设置充卡收入（会员卡）
*
* @param chongka_hyk_sr 充卡收入（会员卡）
*/
public void setChongka_hyk_sr ( UFDouble chongka_hyk_sr) {
this.setAttributeValue( ZhangdanHVO.CHONGKA_HYK_SR,chongka_hyk_sr);
 } 

/** 
* 获取充卡应收（会员卡）
*
* @return 充卡应收（会员卡）
*/
public UFDouble getChongka_hyk_ys () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.CHONGKA_HYK_YS);
 } 

/** 
* 设置充卡应收（会员卡）
*
* @param chongka_hyk_ys 充卡应收（会员卡）
*/
public void setChongka_hyk_ys ( UFDouble chongka_hyk_ys) {
this.setAttributeValue( ZhangdanHVO.CHONGKA_HYK_YS,chongka_hyk_ys);
 } 

/** 
* 获取制单时间
*
* @return 制单时间
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( ZhangdanHVO.CREATIONTIME);
 } 

/** 
* 设置制单时间
*
* @param creationtime 制单时间
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( ZhangdanHVO.CREATIONTIME,creationtime);
 } 

/** 
* 获取制单人
*
* @return 制单人
*/
public String getCreator () {
return (String) this.getAttributeValue( ZhangdanHVO.CREATOR);
 } 

/** 
* 设置制单人
*
* @param creator 制单人
*/
public void setCreator ( String creator) {
this.setAttributeValue( ZhangdanHVO.CREATOR,creator);
 } 

/** 
* 获取代金券
*
* @return 代金券
*/
public UFDouble getDaijinquan () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.DAIJINQUAN);
 } 

/** 
* 设置代金券
*
* @param daijinquan 代金券
*/
public void setDaijinquan ( UFDouble daijinquan) {
this.setAttributeValue( ZhangdanHVO.DAIJINQUAN,daijinquan);
 } 

/** 
* 获取单据日期
*
* @return 单据日期
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( ZhangdanHVO.DBILLDATE);
 } 

/** 
* 设置单据日期
*
* @param dbilldate 单据日期
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( ZhangdanHVO.DBILLDATE,dbilldate);
 } 

/** 
* 获取分区金额
*
* @return 分区金额
*/
public UFDouble getFenqu () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.FENQU);
 } 

/** 
* 设置分区金额
*
* @param fenqu 分区金额
*/
public void setFenqu ( UFDouble fenqu) {
this.setAttributeValue( ZhangdanHVO.FENQU,fenqu);
 } 

/** 
* 获取挂账
*
* @return 挂账
*/
public UFDouble getGuazhang () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.GUAZHANG);
 } 

/** 
* 设置挂账
*
* @param guazhang 挂账
*/
public void setGuazhang ( UFDouble guazhang) {
this.setAttributeValue( ZhangdanHVO.GUAZHANG,guazhang);
 } 

/** 
* 获取挂账_信息
*
* @return 挂账_信息
*/
public String getGuazhang_info () {
return (String) this.getAttributeValue( ZhangdanHVO.GUAZHANG_INFO);
 } 

/** 
* 设置挂账_信息
*
* @param guazhang_info 挂账_信息
*/
public void setGuazhang_info ( String guazhang_info) {
this.setAttributeValue( ZhangdanHVO.GUAZHANG_INFO,guazhang_info);
 } 

/** 
* 获取会员卡消费金额
*
* @return 会员卡消费金额
*/
public UFDouble getHuiyuanka () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.HUIYUANKA);
 } 

/** 
* 设置会员卡消费金额
*
* @param huiyuanka 会员卡消费金额
*/
public void setHuiyuanka ( UFDouble huiyuanka) {
this.setAttributeValue( ZhangdanHVO.HUIYUANKA,huiyuanka);
 } 

/** 
* 获取会员卡优惠金额
*
* @return 会员卡优惠金额
*/
public UFDouble getHuiyuanka_bl () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.HUIYUANKA_BL);
 } 

/** 
* 设置会员卡优惠金额
*
* @param huiyuanka_bl 会员卡优惠金额
*/
public void setHuiyuanka_bl ( UFDouble huiyuanka_bl) {
this.setAttributeValue( ZhangdanHVO.HUIYUANKA_BL,huiyuanka_bl);
 } 

/** 
* 获取会员卡信息
*
* @return 会员卡信息
*/
public String getHuiyuanka_info () {
return (String) this.getAttributeValue( ZhangdanHVO.HUIYUANKA_INFO);
 } 

/** 
* 设置会员卡信息
*
* @param huiyuanka_info 会员卡信息
*/
public void setHuiyuanka_info ( String huiyuanka_info) {
this.setAttributeValue( ZhangdanHVO.HUIYUANKA_INFO,huiyuanka_info);
 } 

/** 
* 获取会员卡实际金额
*
* @return 会员卡实际金额
*/
public UFDouble getHuiyuanka_sj () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.HUIYUANKA_SJ);
 } 

/** 
* 设置会员卡实际金额
*
* @param huiyuanka_sj 会员卡实际金额
*/
public void setHuiyuanka_sj ( UFDouble huiyuanka_sj) {
this.setAttributeValue( ZhangdanHVO.HUIYUANKA_SJ,huiyuanka_sj);
 } 

/** 
* 获取单据状态
*
* @return 单据状态
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( ZhangdanHVO.IBILLSTATUS);
 } 

/** 
* 设置单据状态
*
* @param ibillstatus 单据状态
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( ZhangdanHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* 获取免单
*
* @return 免单
*/
public UFDouble getMiandan () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.MIANDAN);
 } 

/** 
* 设置免单
*
* @param miandan 免单
*/
public void setMiandan ( UFDouble miandan) {
this.setAttributeValue( ZhangdanHVO.MIANDAN,miandan);
 } 

/** 
* 获取修改时间
*
* @return 修改时间
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( ZhangdanHVO.MODIFIEDTIME);
 } 

/** 
* 设置修改时间
*
* @param modifiedtime 修改时间
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( ZhangdanHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* 获取修改人
*
* @return 修改人
*/
public String getModifier () {
return (String) this.getAttributeValue( ZhangdanHVO.MODIFIER);
 } 

/** 
* 设置修改人
*
* @param modifier 修改人
*/
public void setModifier ( String modifier) {
this.setAttributeValue( ZhangdanHVO.MODIFIER,modifier);
 } 

/** 
* 获取业务类型
*
* @return 业务类型
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( ZhangdanHVO.PK_BUSITYPE);
 } 

/** 
* 设置业务类型
*
* @param pk_busitype 业务类型
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( ZhangdanHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* 获取集团
*
* @return 集团
*/
public String getPk_group () {
return (String) this.getAttributeValue( ZhangdanHVO.PK_GROUP);
 } 

/** 
* 设置集团
*
* @param pk_group 集团
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( ZhangdanHVO.PK_GROUP,pk_group);
 } 

/** 
* 获取主表主键
*
* @return 主表主键
*/
public String getPk_hk_dzpt_hg_zhangdan () {
return (String) this.getAttributeValue( ZhangdanHVO.PK_HK_DZPT_HG_ZHANGDAN);
 } 

/** 
* 设置主表主键
*
* @param pk_hk_dzpt_hg_zhangdan 主表主键
*/
public void setPk_hk_dzpt_hg_zhangdan ( String pk_hk_dzpt_hg_zhangdan) {
this.setAttributeValue( ZhangdanHVO.PK_HK_DZPT_HG_ZHANGDAN,pk_hk_dzpt_hg_zhangdan);
 } 

/** 
* 获取组织
*
* @return 组织
*/
public String getPk_org () {
return (String) this.getAttributeValue( ZhangdanHVO.PK_ORG);
 } 

/** 
* 设置组织
*
* @param pk_org 组织
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( ZhangdanHVO.PK_ORG,pk_org);
 } 

/** 
* 获取组织v
*
* @return 组织v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( ZhangdanHVO.PK_ORG_V);
 } 

/** 
* 设置组织v
*
* @param pk_org_v 组织v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( ZhangdanHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* 获取POS
*
* @return POS
*/
public UFDouble getPos () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.POS);
 } 

/** 
* 设置POS
*
* @param pos POS
*/
public void setPos ( UFDouble pos) {
this.setAttributeValue( ZhangdanHVO.POS,pos);
 } 

/** 
* 获取实收
*
* @return 实收
*/
public UFDouble getShishou () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.SHISHOU);
 } 

/** 
* 设置实收
*
* @param shishou 实收
*/
public void setShishou ( UFDouble shishou) {
this.setAttributeValue( ZhangdanHVO.SHISHOU,shishou);
 } 

/** 
* 获取收入
*
* @return 收入
*/
public UFDouble getShouru () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.SHOURU);
 } 

/** 
* 设置收入
*
* @param shouru 收入
*/
public void setShouru ( UFDouble shouru) {
this.setAttributeValue( ZhangdanHVO.SHOURU,shouru);
 } 

/** 
* 获取审核时间
*
* @return 审核时间
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( ZhangdanHVO.TAUDITTIME);
 } 

/** 
* 设置审核时间
*
* @param taudittime 审核时间
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( ZhangdanHVO.TAUDITTIME,taudittime);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( ZhangdanHVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( ZhangdanHVO.TS,ts);
 } 

/** 
* 获取审批批语
*
* @return 审批批语
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( ZhangdanHVO.VAPPROVENOTE);
 } 

/** 
* 设置审批批语
*
* @param vapprovenote 审批批语
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( ZhangdanHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* 获取单据编号
*
* @return 单据编号
*/
public String getVbillcode () {
return (String) this.getAttributeValue( ZhangdanHVO.VBILLCODE);
 } 

/** 
* 设置单据编号
*
* @param vbillcode 单据编号
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( ZhangdanHVO.VBILLCODE,vbillcode);
 } 

/** 
* 获取单据类型
*
* @return 单据类型
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( ZhangdanHVO.VBILLTYPECODE);
 } 

/** 
* 设置单据类型
*
* @param vbilltypecode 单据类型
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( ZhangdanHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vdef01 自定义项01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( ZhangdanHVO.VDEF01,vdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vdef02 自定义项02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( ZhangdanHVO.VDEF02,vdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vdef03 自定义项03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( ZhangdanHVO.VDEF03,vdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vdef04 自定义项04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( ZhangdanHVO.VDEF04,vdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vdef05 自定义项05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( ZhangdanHVO.VDEF05,vdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vdef06 自定义项06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( ZhangdanHVO.VDEF06,vdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vdef07 自定义项07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( ZhangdanHVO.VDEF07,vdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vdef08 自定义项08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( ZhangdanHVO.VDEF08,vdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vdef09 自定义项09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( ZhangdanHVO.VDEF09,vdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vdef10 自定义项10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( ZhangdanHVO.VDEF10,vdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVmemo () {
return (String) this.getAttributeValue( ZhangdanHVO.VMEMO);
 } 

/** 
* 设置备注
*
* @param vmemo 备注
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( ZhangdanHVO.VMEMO,vmemo);
 } 

/** 
* 获取交易类型
*
* @return 交易类型
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( ZhangdanHVO.VTRANTYPECODE);
 } 

/** 
* 设置交易类型
*
* @param vtrantypecode 交易类型
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( ZhangdanHVO.VTRANTYPECODE,vtrantypecode);
 } 

/** 
* 获取往来
*
* @return 往来
*/
public UFDouble getWanglai () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.WANGLAI);
 } 

/** 
* 设置往来
*
* @param wanglai 往来
*/
public void setWanglai ( UFDouble wanglai) {
this.setAttributeValue( ZhangdanHVO.WANGLAI,wanglai);
 } 

/** 
* 获取往来_信息
*
* @return 往来_信息
*/
public String getWanglai_info () {
return (String) this.getAttributeValue( ZhangdanHVO.WANGLAI_INFO);
 } 

/** 
* 设置往来_信息
*
* @param wanglai_info 往来_信息
*/
public void setWanglai_info ( String wanglai_info) {
this.setAttributeValue( ZhangdanHVO.WANGLAI_INFO,wanglai_info);
 } 

/** 
* 获取现金
*
* @return 现金
*/
public UFDouble getXianjin () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.XIANJIN);
 } 

/** 
* 设置现金
*
* @param xianjin 现金
*/
public void setXianjin ( UFDouble xianjin) {
this.setAttributeValue( ZhangdanHVO.XIANJIN,xianjin);
 } 

/** 
* 获取应收
*
* @return 应收
*/
public UFDouble getYingshou () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YINGSHOU);
 } 

/** 
* 设置应收
*
* @param yingshou 应收
*/
public void setYingshou ( UFDouble yingshou) {
this.setAttributeValue( ZhangdanHVO.YINGSHOU,yingshou);
 } 

/** 
* 获取优惠_其它
*
* @return 优惠_其它
*/
public UFDouble getYouhui_qt () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_QT);
 } 

/** 
* 设置优惠_其它
*
* @param youhui_qt 优惠_其它
*/
public void setYouhui_qt ( UFDouble youhui_qt) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_QT,youhui_qt);
 } 

/** 
* 获取手工优惠01
*
* @return 手工优惠01
*/
public UFDouble getYouhui_sg_01 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_01);
 } 

/** 
* 设置手工优惠01
*
* @param youhui_sg_01 手工优惠01
*/
public void setYouhui_sg_01 ( UFDouble youhui_sg_01) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_01,youhui_sg_01);
 } 

/** 
* 获取手工优惠02
*
* @return 手工优惠02
*/
public UFDouble getYouhui_sg_02 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_02);
 } 

/** 
* 设置手工优惠02
*
* @param youhui_sg_02 手工优惠02
*/
public void setYouhui_sg_02 ( UFDouble youhui_sg_02) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_02,youhui_sg_02);
 } 

/** 
* 获取手工优惠03
*
* @return 手工优惠03
*/
public UFDouble getYouhui_sg_03 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_03);
 } 

/** 
* 设置手工优惠03
*
* @param youhui_sg_03 手工优惠03
*/
public void setYouhui_sg_03 ( UFDouble youhui_sg_03) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_03,youhui_sg_03);
 } 

/** 
* 获取手工优惠04
*
* @return 手工优惠04
*/
public UFDouble getYouhui_sg_04 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_04);
 } 

/** 
* 设置手工优惠04
*
* @param youhui_sg_04 手工优惠04
*/
public void setYouhui_sg_04 ( UFDouble youhui_sg_04) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_04,youhui_sg_04);
 } 

/** 
* 获取手工优惠05
*
* @return 手工优惠05
*/
public UFDouble getYouhui_sg_05 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_05);
 } 

/** 
* 设置手工优惠05
*
* @param youhui_sg_05 手工优惠05
*/
public void setYouhui_sg_05 ( UFDouble youhui_sg_05) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_05,youhui_sg_05);
 } 

/** 
* 获取手工优惠06
*
* @return 手工优惠06
*/
public UFDouble getYouhui_sg_06 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_06);
 } 

/** 
* 设置手工优惠06
*
* @param youhui_sg_06 手工优惠06
*/
public void setYouhui_sg_06 ( UFDouble youhui_sg_06) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_06,youhui_sg_06);
 } 

/** 
* 获取手工优惠07
*
* @return 手工优惠07
*/
public UFDouble getYouhui_sg_07 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_07);
 } 

/** 
* 设置手工优惠07
*
* @param youhui_sg_07 手工优惠07
*/
public void setYouhui_sg_07 ( UFDouble youhui_sg_07) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_07,youhui_sg_07);
 } 

/** 
* 获取手工优惠08
*
* @return 手工优惠08
*/
public UFDouble getYouhui_sg_08 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_08);
 } 

/** 
* 设置手工优惠08
*
* @param youhui_sg_08 手工优惠08
*/
public void setYouhui_sg_08 ( UFDouble youhui_sg_08) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_08,youhui_sg_08);
 } 

/** 
* 获取手工优惠09
*
* @return 手工优惠09
*/
public UFDouble getYouhui_sg_09 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_09);
 } 

/** 
* 设置手工优惠09
*
* @param youhui_sg_09 手工优惠09
*/
public void setYouhui_sg_09 ( UFDouble youhui_sg_09) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_09,youhui_sg_09);
 } 

/** 
* 获取手工优惠10
*
* @return 手工优惠10
*/
public UFDouble getYouhui_sg_10 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_10);
 } 

/** 
* 设置手工优惠10
*
* @param youhui_sg_10 手工优惠10
*/
public void setYouhui_sg_10 ( UFDouble youhui_sg_10) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_10,youhui_sg_10);
 } 

/** 
* 获取支票
*
* @return 支票
*/
public UFDouble getZhipiao () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.ZHIPIAO);
 } 

/** 
* 设置支票
*
* @param zhipiao 支票
*/
public void setZhipiao ( UFDouble zhipiao) {
this.setAttributeValue( ZhangdanHVO.ZHIPIAO,zhipiao);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_ZhangdanHVO");
  }
  
  
  /**
   * 增加 自定义项 11-40
   * HK-问题2
   * 2019年1月9日14:42:39
   */
  public static final String VDEF11="vdef11";
  public String getVdef11 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF11 );
  } 
  public void setVdef11 ( String vdef11) {
	  this.setAttributeValue( ZhangdanHVO.VDEF11,vdef11 );
  }
  
  public static final String VDEF12="vdef12";
  public String getVdef12 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF12 );
  } 
  public void setVdef12 ( String vdef12) {
	  this.setAttributeValue( ZhangdanHVO.VDEF12,vdef12 );
  }
  
  public static final String VDEF13="vdef13";
  public String getVdef13 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF13 );
  } 
  public void setVdef13 ( String vdef13) {
	  this.setAttributeValue( ZhangdanHVO.VDEF13,vdef13 );
  }
  
  public static final String VDEF14="vdef14";
  public String getVdef14 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF14 );
  } 
  public void setVdef14 ( String vdef14) {
	  this.setAttributeValue( ZhangdanHVO.VDEF14,vdef14 );
  }
  
  public static final String VDEF15="vdef15";
  public String getVdef15 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF15 );
  } 
  public void setVdef15 ( String vdef15) {
	  this.setAttributeValue( ZhangdanHVO.VDEF15,vdef15 );
  }
  
  public static final String VDEF16="vdef16";
  public String getVdef16 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF16 );
  } 
  public void setVdef16 ( String vdef16) {
	  this.setAttributeValue( ZhangdanHVO.VDEF16,vdef16 );
  }
  
  public static final String VDEF17="vdef17";
  public String getVdef17 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF17 );
  } 
  public void setVdef17 ( String vdef17) {
	  this.setAttributeValue( ZhangdanHVO.VDEF17,vdef17 );
  }
  
  public static final String VDEF18="vdef18";
  public String getVdef18 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF18 );
  } 
  public void setVdef18 ( String vdef18) {
	  this.setAttributeValue( ZhangdanHVO.VDEF18,vdef18 );
  }
  
  public static final String VDEF19="vdef19";
  public String getVdef19 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF19 );
  } 
  public void setVdef19 ( String vdef19) {
	  this.setAttributeValue( ZhangdanHVO.VDEF19,vdef19 );
  }
  
  public static final String VDEF20="vdef20";
  public String getVdef20 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF20 );
  } 
  public void setVdef20 ( String vdef20) {
	  this.setAttributeValue( ZhangdanHVO.VDEF20,vdef20 );
  }
  
  public static final String VDEF21="vdef21";
  public String getVdef21 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF21 );
  } 
  public void setVdef21 ( String vdef21) {
	  this.setAttributeValue( ZhangdanHVO.VDEF21,vdef21 );
  }
  
  public static final String VDEF22="vdef22";
  public String getVdef22 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF22 );
  } 
  public void setVdef22 ( String vdef22) {
	  this.setAttributeValue( ZhangdanHVO.VDEF22,vdef22 );
  }
  
  public static final String VDEF23="vdef23";
  public String getVdef23 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF23 );
  } 
  public void setVdef23 ( String vdef23) {
	  this.setAttributeValue( ZhangdanHVO.VDEF23,vdef23 );
  }
  
  public static final String VDEF24="vdef24";
  public String getVdef24 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF24 );
  } 
  public void setVdef24 ( String vdef24) {
	  this.setAttributeValue( ZhangdanHVO.VDEF24,vdef24 );
  }
  
  public static final String VDEF25="vdef25";
  public String getVdef25 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF25 );
  } 
  public void setVdef25 ( String vdef25) {
	  this.setAttributeValue( ZhangdanHVO.VDEF25,vdef25 );
  }
  
  public static final String VDEF26="vdef26";
  public String getVdef26 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF26 );
  } 
  public void setVdef26 ( String vdef26) {
	  this.setAttributeValue( ZhangdanHVO.VDEF26,vdef26 );
  }
  
  public static final String VDEF27="vdef27";
  public String getVdef27 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF27 );
  } 
  public void setVdef27 ( String vdef27) {
	  this.setAttributeValue( ZhangdanHVO.VDEF27,vdef27 );
  }
  
  public static final String VDEF28="vdef28";
  public String getVdef28 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF28 );
  } 
  public void setVdef28 ( String vdef28) {
	  this.setAttributeValue( ZhangdanHVO.VDEF28,vdef28 );
  }
  
  public static final String VDEF29="vdef29";
  public String getVdef29 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF29 );
  } 
  public void setVdef29 ( String vdef29) {
	  this.setAttributeValue( ZhangdanHVO.VDEF29,vdef29 );
  }
  
  public static final String VDEF30="vdef30";
  public String getVdef30 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF30 );
  } 
  public void setVdef30 ( String vdef30) {
	  this.setAttributeValue( ZhangdanHVO.VDEF30,vdef30 );
  }
  
  public static final String VDEF31="vdef31";
  public String getVdef31 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF31 );
  } 
  public void setVdef31 ( String vdef31) {
	  this.setAttributeValue( ZhangdanHVO.VDEF31,vdef31 );
  }
  
  public static final String VDEF32="vdef32";
  public String getVdef32 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF32 );
  } 
  public void setVdef32 ( String vdef32) {
	  this.setAttributeValue( ZhangdanHVO.VDEF32,vdef32 );
  }
  
  public static final String VDEF33="vdef33";
  public String getVdef33 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF33 );
  } 
  public void setVdef33 ( String vdef33) {
	  this.setAttributeValue( ZhangdanHVO.VDEF33,vdef33 );
  }
  
  public static final String VDEF34="vdef34";
  public String getVdef34 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF34 );
  } 
  public void setVdef34 ( String vdef34) {
	  this.setAttributeValue( ZhangdanHVO.VDEF34,vdef34 );
  }
  
  public static final String VDEF35="vdef35";
  public String getVdef35 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF35 );
  } 
  public void setVdef35 ( String vdef35) {
	  this.setAttributeValue( ZhangdanHVO.VDEF35,vdef35 );
  }
  
  public static final String VDEF36="vdef36";
  public String getVdef36 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF36 );
  } 
  public void setVdef36 ( String vdef36) {
	  this.setAttributeValue( ZhangdanHVO.VDEF36,vdef36 );
  }
  
  public static final String VDEF37="vdef37";
  public String getVdef37 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF37 );
  } 
  public void setVdef37 ( String vdef37) {
	  this.setAttributeValue( ZhangdanHVO.VDEF37,vdef37 );
  }
  
  public static final String VDEF38="vdef38";
  public String getVdef38 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF38 );
  } 
  public void setVdef38 ( String vdef38) {
	  this.setAttributeValue( ZhangdanHVO.VDEF38,vdef38 );
  }
  
  public static final String VDEF39="vdef39";
  public String getVdef39 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF39 );
  } 
  public void setVdef39 ( String vdef39) {
	  this.setAttributeValue( ZhangdanHVO.VDEF39,vdef39 );
  }
  
  public static final String VDEF40="vdef40";
  public String getVdef40 () {
	  return (String) this.getAttributeValue( ZhangdanHVO.VDEF40 );
  } 
  public void setVdef40 ( String vdef40) {
	  this.setAttributeValue( ZhangdanHVO.VDEF40,vdef40 );
  }
  /***END***/
  
}