package nc.vo.hkjt.srgk.huiguan.zhangdan;

import hd.vo.pub.tools.PuPubVO;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ZhangdanBVO extends SuperVO implements Comparable<ZhangdanBVO> {
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
*次卡
*/
public static final String CIKA="cika";
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
*代金券
*/
public static final String DAIJINQUAN="daijinquan";
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
*会员卡
*/
public static final String HUIYUANKA="huiyuanka";
/**
*手牌号
*/
public static final String KEYID="keyid";
/**
*mebercardid
*/
public static final String MEBERCARDID="mebercardid";
/**
*免单
*/
public static final String MIANDAN="miandan";
/**
*numberxount
*/
public static final String NUMBERXOUNT="numberxount";
/**
*上层单据主键
*/
public static final String PK_HK_DZPT_HG_ZHANGDAN="pk_hk_dzpt_hg_zhangdan";
/**
*子表主键
*/
public static final String PK_HK_DZPT_HG_ZHANGDAN_B="pk_hk_dzpt_hg_zhangdan_b";
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
*商品名称
*/
public static final String SQ_NAME="sq_name";
/**
*商品分类id
*/
public static final String SQFL_ID="sqfl_id";
/**
*商品分类
*/
public static final String SQFL_NAME="sqfl_name";
/**
*收入项目id
*/
public static final String SRXM_ID="srxm_id";
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
*往来
*/
public static final String WANGLAI="wanglai";
/**
*往来_信息
*/
public static final String WANGLAI_INFO="wanglai_info";
/**
*业务系统流水号
*/
public static final String WATERNUM="waternum";
/**
*现金
*/
public static final String XIANJIN="xianjin";
/**
*应收
*/
public static final String YINGSHOU="yingshou";
/**
*卡比例优惠
*/
public static final String YOUHUI_KABILI="youhui_kabili";
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
*手工优惠
*/
public static final String YOUHUI_SHOUGONG="youhui_shougong";
/**
*自动优惠
*/
public static final String YOUHUI_ZIDONG="youhui_zidong";
/**
*支票
*/
public static final String ZHIPIAO="zhipiao";
/** 
* 获取部门父ID
*
* @return 部门父ID
*/
public String getBm_fid () {
return (String) this.getAttributeValue( ZhangdanBVO.BM_FID);
 } 

/** 
* 设置部门父ID
*
* @param bm_fid 部门父ID
*/
public void setBm_fid ( String bm_fid) {
this.setAttributeValue( ZhangdanBVO.BM_FID,bm_fid);
 } 

/** 
* 获取部门id
*
* @return 部门id
*/
public String getBm_id () {
return (String) this.getAttributeValue( ZhangdanBVO.BM_ID);
 } 

/** 
* 设置部门id
*
* @param bm_id 部门id
*/
public void setBm_id ( String bm_id) {
this.setAttributeValue( ZhangdanBVO.BM_ID,bm_id);
 } 

/** 
* 获取部门名称
*
* @return 部门名称
*/
public String getBm_name () {
return (String) this.getAttributeValue( ZhangdanBVO.BM_NAME);
 } 

/** 
* 设置部门名称
*
* @param bm_name 部门名称
*/
public void setBm_name ( String bm_name) {
this.setAttributeValue( ZhangdanBVO.BM_NAME,bm_name);
 } 

/** 
* 获取源头单据表体id
*
* @return 源头单据表体id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( ZhangdanBVO.CFIRSTBILLBID);
 } 

/** 
* 设置源头单据表体id
*
* @param cfirstbillbid 源头单据表体id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( ZhangdanBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* 获取源头单据id
*
* @return 源头单据id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( ZhangdanBVO.CFIRSTBILLID);
 } 

/** 
* 设置源头单据id
*
* @param cfirstbillid 源头单据id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( ZhangdanBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* 获取源头单据类型
*
* @return 源头单据类型
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( ZhangdanBVO.CFIRSTTYPECODE);
 } 

/** 
* 设置源头单据类型
*
* @param cfirsttypecode 源头单据类型
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( ZhangdanBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* 获取充卡收入（次卡）
*
* @return 充卡收入（次卡）
*/
public UFDouble getChongka_ck_sr () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.CHONGKA_CK_SR);
 } 

/** 
* 设置充卡收入（次卡）
*
* @param chongka_ck_sr 充卡收入（次卡）
*/
public void setChongka_ck_sr ( UFDouble chongka_ck_sr) {
this.setAttributeValue( ZhangdanBVO.CHONGKA_CK_SR,chongka_ck_sr);
 } 

/** 
* 获取充卡应收（次卡）
*
* @return 充卡应收（次卡）
*/
public UFDouble getChongka_ck_ys () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.CHONGKA_CK_YS);
 } 

/** 
* 设置充卡应收（次卡）
*
* @param chongka_ck_ys 充卡应收（次卡）
*/
public void setChongka_ck_ys ( UFDouble chongka_ck_ys) {
this.setAttributeValue( ZhangdanBVO.CHONGKA_CK_YS,chongka_ck_ys);
 } 

/** 
* 获取充卡收入（会员卡）
*
* @return 充卡收入（会员卡）
*/
public UFDouble getChongka_hyk_sr () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.CHONGKA_HYK_SR);
 } 

/** 
* 设置充卡收入（会员卡）
*
* @param chongka_hyk_sr 充卡收入（会员卡）
*/
public void setChongka_hyk_sr ( UFDouble chongka_hyk_sr) {
this.setAttributeValue( ZhangdanBVO.CHONGKA_HYK_SR,chongka_hyk_sr);
 } 

/** 
* 获取充卡应收（会员卡）
*
* @return 充卡应收（会员卡）
*/
public UFDouble getChongka_hyk_ys () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.CHONGKA_HYK_YS);
 } 

/** 
* 设置充卡应收（会员卡）
*
* @param chongka_hyk_ys 充卡应收（会员卡）
*/
public void setChongka_hyk_ys ( UFDouble chongka_hyk_ys) {
this.setAttributeValue( ZhangdanBVO.CHONGKA_HYK_YS,chongka_hyk_ys);
 } 

/** 
* 获取次卡
*
* @return 次卡
*/
public UFDouble getCika () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.CIKA);
 } 

/** 
* 设置次卡
*
* @param cika 次卡
*/
public void setCika ( UFDouble cika) {
this.setAttributeValue( ZhangdanBVO.CIKA,cika);
 } 

/** 
* 获取上层单据表体id
*
* @return 上层单据表体id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( ZhangdanBVO.CSOURCEBILLBID);
 } 

/** 
* 设置上层单据表体id
*
* @param csourcebillbid 上层单据表体id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( ZhangdanBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* 获取上层单据id
*
* @return 上层单据id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( ZhangdanBVO.CSOURCEBILLID);
 } 

/** 
* 设置上层单据id
*
* @param csourcebillid 上层单据id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( ZhangdanBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* 获取上层单据类型
*
* @return 上层单据类型
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( ZhangdanBVO.CSOURCETYPECODE);
 } 

/** 
* 设置上层单据类型
*
* @param csourcetypecode 上层单据类型
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( ZhangdanBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* 获取代金券
*
* @return 代金券
*/
public UFDouble getDaijinquan () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.DAIJINQUAN);
 } 

/** 
* 设置代金券
*
* @param daijinquan 代金券
*/
public void setDaijinquan ( UFDouble daijinquan) {
this.setAttributeValue( ZhangdanBVO.DAIJINQUAN,daijinquan);
 } 

/** 
* 获取分区金额
*
* @return 分区金额
*/
public UFDouble getFenqu () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.FENQU);
 } 

/** 
* 设置分区金额
*
* @param fenqu 分区金额
*/
public void setFenqu ( UFDouble fenqu) {
this.setAttributeValue( ZhangdanBVO.FENQU,fenqu);
 } 

/** 
* 获取挂账
*
* @return 挂账
*/
public UFDouble getGuazhang () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.GUAZHANG);
 } 

/** 
* 设置挂账
*
* @param guazhang 挂账
*/
public void setGuazhang ( UFDouble guazhang) {
this.setAttributeValue( ZhangdanBVO.GUAZHANG,guazhang);
 } 

/** 
* 获取挂账_信息
*
* @return 挂账_信息
*/
public String getGuazhang_info () {
return (String) this.getAttributeValue( ZhangdanBVO.GUAZHANG_INFO);
 } 

/** 
* 设置挂账_信息
*
* @param guazhang_info 挂账_信息
*/
public void setGuazhang_info ( String guazhang_info) {
this.setAttributeValue( ZhangdanBVO.GUAZHANG_INFO,guazhang_info);
 } 

/** 
* 获取会员卡
*
* @return 会员卡
*/
public UFDouble getHuiyuanka () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.HUIYUANKA);
 } 

/** 
* 设置会员卡
*
* @param huiyuanka 会员卡
*/
public void setHuiyuanka ( UFDouble huiyuanka) {
this.setAttributeValue( ZhangdanBVO.HUIYUANKA,huiyuanka);
 } 

/** 
* 获取手牌号
*
* @return 手牌号
*/
public String getKeyid () {
return (String) this.getAttributeValue( ZhangdanBVO.KEYID);
 } 

/** 
* 设置手牌号
*
* @param keyid 手牌号
*/
public void setKeyid ( String keyid) {
this.setAttributeValue( ZhangdanBVO.KEYID,keyid);
 } 

/** 
* 获取mebercardid
*
* @return mebercardid
*/
public String getMebercardid () {
return (String) this.getAttributeValue( ZhangdanBVO.MEBERCARDID);
 } 

/** 
* 设置mebercardid
*
* @param mebercardid mebercardid
*/
public void setMebercardid ( String mebercardid) {
this.setAttributeValue( ZhangdanBVO.MEBERCARDID,mebercardid);
 } 

/** 
* 获取免单
*
* @return 免单
*/
public UFDouble getMiandan () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.MIANDAN);
 } 

/** 
* 设置免单
*
* @param miandan 免单
*/
public void setMiandan ( UFDouble miandan) {
this.setAttributeValue( ZhangdanBVO.MIANDAN,miandan);
 } 

/** 
* 获取numberxount
*
* @return numberxount
*/
public UFDouble getNumberxount () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.NUMBERXOUNT);
 } 

/** 
* 设置numberxount
*
* @param numberxount numberxount
*/
public void setNumberxount ( UFDouble numberxount) {
this.setAttributeValue( ZhangdanBVO.NUMBERXOUNT,numberxount);
 } 

/** 
* 获取上层单据主键
*
* @return 上层单据主键
*/
public String getPk_hk_dzpt_hg_zhangdan () {
return (String) this.getAttributeValue( ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN);
 } 

/** 
* 设置上层单据主键
*
* @param pk_hk_dzpt_hg_zhangdan 上层单据主键
*/
public void setPk_hk_dzpt_hg_zhangdan ( String pk_hk_dzpt_hg_zhangdan) {
this.setAttributeValue( ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN,pk_hk_dzpt_hg_zhangdan);
 } 

/** 
* 获取子表主键
*
* @return 子表主键
*/
public String getPk_hk_dzpt_hg_zhangdan_b () {
return (String) this.getAttributeValue( ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN_B);
 } 

/** 
* 设置子表主键
*
* @param pk_hk_dzpt_hg_zhangdan_b 子表主键
*/
public void setPk_hk_dzpt_hg_zhangdan_b ( String pk_hk_dzpt_hg_zhangdan_b) {
this.setAttributeValue( ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN_B,pk_hk_dzpt_hg_zhangdan_b);
 } 

/** 
* 获取POS
*
* @return POS
*/
public UFDouble getPos () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.POS);
 } 

/** 
* 设置POS
*
* @param pos POS
*/
public void setPos ( UFDouble pos) {
this.setAttributeValue( ZhangdanBVO.POS,pos);
 } 

/** 
* 获取实收
*
* @return 实收
*/
public UFDouble getShishou () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.SHISHOU);
 } 

/** 
* 设置实收
*
* @param shishou 实收
*/
public void setShishou ( UFDouble shishou) {
this.setAttributeValue( ZhangdanBVO.SHISHOU,shishou);
 } 

/** 
* 获取收入
*
* @return 收入
*/
public UFDouble getShouru () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.SHOURU);
 } 

/** 
* 设置收入
*
* @param shouru 收入
*/
public void setShouru ( UFDouble shouru) {
this.setAttributeValue( ZhangdanBVO.SHOURU,shouru);
 } 

/** 
* 获取商品名称
*
* @return 商品名称
*/
public String getSq_name () {
return (String) this.getAttributeValue( ZhangdanBVO.SQ_NAME);
 } 

/** 
* 设置商品名称
*
* @param sq_name 商品名称
*/
public void setSq_name ( String sq_name) {
this.setAttributeValue( ZhangdanBVO.SQ_NAME,sq_name);
 } 

/** 
* 获取商品分类id
*
* @return 商品分类id
*/
public String getSqfl_id () {
return (String) this.getAttributeValue( ZhangdanBVO.SQFL_ID);
 } 

/** 
* 设置商品分类id
*
* @param sqfl_id 商品分类id
*/
public void setSqfl_id ( String sqfl_id) {
this.setAttributeValue( ZhangdanBVO.SQFL_ID,sqfl_id);
 } 

/** 
* 获取商品分类
*
* @return 商品分类
*/
public String getSqfl_name () {
return (String) this.getAttributeValue( ZhangdanBVO.SQFL_NAME);
 } 

/** 
* 设置商品分类
*
* @param sqfl_name 商品分类
*/
public void setSqfl_name ( String sqfl_name) {
this.setAttributeValue( ZhangdanBVO.SQFL_NAME,sqfl_name);
 } 

/** 
* 获取收入项目id
*
* @return 收入项目id
*/
public String getSrxm_id () {
return (String) this.getAttributeValue( ZhangdanBVO.SRXM_ID);
 } 

/** 
* 设置收入项目id
*
* @param srxm_id 收入项目id
*/
public void setSrxm_id ( String srxm_id) {
this.setAttributeValue( ZhangdanBVO.SRXM_ID,srxm_id);
 } 

/** 
* 获取时间戳
*
* @return 时间戳
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( ZhangdanBVO.TS);
 } 

/** 
* 设置时间戳
*
* @param ts 时间戳
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( ZhangdanBVO.TS,ts);
 } 

/** 
* 获取自定义项01
*
* @return 自定义项01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF01);
 } 

/** 
* 设置自定义项01
*
* @param vbdef01 自定义项01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( ZhangdanBVO.VBDEF01,vbdef01);
 } 

/** 
* 获取自定义项02
*
* @return 自定义项02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF02);
 } 

/** 
* 设置自定义项02
*
* @param vbdef02 自定义项02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( ZhangdanBVO.VBDEF02,vbdef02);
 } 

/** 
* 获取自定义项03
*
* @return 自定义项03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF03);
 } 

/** 
* 设置自定义项03
*
* @param vbdef03 自定义项03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( ZhangdanBVO.VBDEF03,vbdef03);
 } 

/** 
* 获取自定义项04
*
* @return 自定义项04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF04);
 } 

/** 
* 设置自定义项04
*
* @param vbdef04 自定义项04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( ZhangdanBVO.VBDEF04,vbdef04);
 } 

/** 
* 获取自定义项05
*
* @return 自定义项05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF05);
 } 

/** 
* 设置自定义项05
*
* @param vbdef05 自定义项05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( ZhangdanBVO.VBDEF05,vbdef05);
 } 

/** 
* 获取自定义项06
*
* @return 自定义项06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF06);
 } 

/** 
* 设置自定义项06
*
* @param vbdef06 自定义项06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( ZhangdanBVO.VBDEF06,vbdef06);
 } 

/** 
* 获取自定义项07
*
* @return 自定义项07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF07);
 } 

/** 
* 设置自定义项07
*
* @param vbdef07 自定义项07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( ZhangdanBVO.VBDEF07,vbdef07);
 } 

/** 
* 获取自定义项08
*
* @return 自定义项08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF08);
 } 

/** 
* 设置自定义项08
*
* @param vbdef08 自定义项08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( ZhangdanBVO.VBDEF08,vbdef08);
 } 

/** 
* 获取自定义项09
*
* @return 自定义项09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF09);
 } 

/** 
* 设置自定义项09
*
* @param vbdef09 自定义项09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( ZhangdanBVO.VBDEF09,vbdef09);
 } 

/** 
* 获取自定义项10
*
* @return 自定义项10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF10);
 } 

/** 
* 设置自定义项10
*
* @param vbdef10 自定义项10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( ZhangdanBVO.VBDEF10,vbdef10);
 } 

/** 
* 获取备注
*
* @return 备注
*/
public String getVbmemo () {
return (String) this.getAttributeValue( ZhangdanBVO.VBMEMO);
 } 

/** 
* 设置备注
*
* @param vbmemo 备注
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( ZhangdanBVO.VBMEMO,vbmemo);
 } 

/** 
* 获取源头单据号
*
* @return 源头单据号
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( ZhangdanBVO.VFIRSTBILLCODE);
 } 

/** 
* 设置源头单据号
*
* @param vfirstbillcode 源头单据号
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( ZhangdanBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* 获取行号
*
* @return 行号
*/
public String getVrowno () {
return (String) this.getAttributeValue( ZhangdanBVO.VROWNO);
 } 

/** 
* 设置行号
*
* @param vrowno 行号
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( ZhangdanBVO.VROWNO,vrowno);
 } 

/** 
* 获取上次单据号
*
* @return 上次单据号
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( ZhangdanBVO.VSOURCEBILLCODE);
 } 

/** 
* 设置上次单据号
*
* @param vsourcebillcode 上次单据号
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( ZhangdanBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* 获取往来
*
* @return 往来
*/
public UFDouble getWanglai () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.WANGLAI);
 } 

/** 
* 设置往来
*
* @param wanglai 往来
*/
public void setWanglai ( UFDouble wanglai) {
this.setAttributeValue( ZhangdanBVO.WANGLAI,wanglai);
 } 

/** 
* 获取往来_信息
*
* @return 往来_信息
*/
public String getWanglai_info () {
return (String) this.getAttributeValue( ZhangdanBVO.WANGLAI_INFO);
 } 

/** 
* 设置往来_信息
*
* @param wanglai_info 往来_信息
*/
public void setWanglai_info ( String wanglai_info) {
this.setAttributeValue( ZhangdanBVO.WANGLAI_INFO,wanglai_info);
 } 

/** 
* 获取业务系统流水号
*
* @return 业务系统流水号
*/
public String getWaternum () {
return (String) this.getAttributeValue( ZhangdanBVO.WATERNUM);
 } 

/** 
* 设置业务系统流水号
*
* @param waternum 业务系统流水号
*/
public void setWaternum ( String waternum) {
this.setAttributeValue( ZhangdanBVO.WATERNUM,waternum);
 } 

/** 
* 获取现金
*
* @return 现金
*/
public UFDouble getXianjin () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.XIANJIN);
 } 

/** 
* 设置现金
*
* @param xianjin 现金
*/
public void setXianjin ( UFDouble xianjin) {
this.setAttributeValue( ZhangdanBVO.XIANJIN,xianjin);
 } 

/** 
* 获取应收
*
* @return 应收
*/
public UFDouble getYingshou () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YINGSHOU);
 } 

/** 
* 设置应收
*
* @param yingshou 应收
*/
public void setYingshou ( UFDouble yingshou) {
this.setAttributeValue( ZhangdanBVO.YINGSHOU,yingshou);
 } 

/** 
* 获取卡比例优惠
*
* @return 卡比例优惠
*/
public UFDouble getYouhui_kabili () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_KABILI);
 } 

/** 
* 设置卡比例优惠
*
* @param youhui_kabili 卡比例优惠
*/
public void setYouhui_kabili ( UFDouble youhui_kabili) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_KABILI,youhui_kabili);
 } 

/** 
* 获取优惠_其它
*
* @return 优惠_其它
*/
public UFDouble getYouhui_qt () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_QT);
 } 

/** 
* 设置优惠_其它
*
* @param youhui_qt 优惠_其它
*/
public void setYouhui_qt ( UFDouble youhui_qt) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_QT,youhui_qt);
 } 

/** 
* 获取手工优惠01
*
* @return 手工优惠01
*/
public UFDouble getYouhui_sg_01 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_01);
 } 

/** 
* 设置手工优惠01
*
* @param youhui_sg_01 手工优惠01
*/
public void setYouhui_sg_01 ( UFDouble youhui_sg_01) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_01,youhui_sg_01);
 } 

/** 
* 获取手工优惠02
*
* @return 手工优惠02
*/
public UFDouble getYouhui_sg_02 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_02);
 } 

/** 
* 设置手工优惠02
*
* @param youhui_sg_02 手工优惠02
*/
public void setYouhui_sg_02 ( UFDouble youhui_sg_02) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_02,youhui_sg_02);
 } 

/** 
* 获取手工优惠03
*
* @return 手工优惠03
*/
public UFDouble getYouhui_sg_03 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_03);
 } 

/** 
* 设置手工优惠03
*
* @param youhui_sg_03 手工优惠03
*/
public void setYouhui_sg_03 ( UFDouble youhui_sg_03) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_03,youhui_sg_03);
 } 

/** 
* 获取手工优惠04
*
* @return 手工优惠04
*/
public UFDouble getYouhui_sg_04 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_04);
 } 

/** 
* 设置手工优惠04
*
* @param youhui_sg_04 手工优惠04
*/
public void setYouhui_sg_04 ( UFDouble youhui_sg_04) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_04,youhui_sg_04);
 } 

/** 
* 获取手工优惠05
*
* @return 手工优惠05
*/
public UFDouble getYouhui_sg_05 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_05);
 } 

/** 
* 设置手工优惠05
*
* @param youhui_sg_05 手工优惠05
*/
public void setYouhui_sg_05 ( UFDouble youhui_sg_05) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_05,youhui_sg_05);
 } 

/** 
* 获取手工优惠06
*
* @return 手工优惠06
*/
public UFDouble getYouhui_sg_06 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_06);
 } 

/** 
* 设置手工优惠06
*
* @param youhui_sg_06 手工优惠06
*/
public void setYouhui_sg_06 ( UFDouble youhui_sg_06) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_06,youhui_sg_06);
 } 

/** 
* 获取手工优惠07
*
* @return 手工优惠07
*/
public UFDouble getYouhui_sg_07 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_07);
 } 

/** 
* 设置手工优惠07
*
* @param youhui_sg_07 手工优惠07
*/
public void setYouhui_sg_07 ( UFDouble youhui_sg_07) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_07,youhui_sg_07);
 } 

/** 
* 获取手工优惠08
*
* @return 手工优惠08
*/
public UFDouble getYouhui_sg_08 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_08);
 } 

/** 
* 设置手工优惠08
*
* @param youhui_sg_08 手工优惠08
*/
public void setYouhui_sg_08 ( UFDouble youhui_sg_08) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_08,youhui_sg_08);
 } 

/** 
* 获取手工优惠09
*
* @return 手工优惠09
*/
public UFDouble getYouhui_sg_09 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_09);
 } 

/** 
* 设置手工优惠09
*
* @param youhui_sg_09 手工优惠09
*/
public void setYouhui_sg_09 ( UFDouble youhui_sg_09) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_09,youhui_sg_09);
 } 

/** 
* 获取手工优惠10
*
* @return 手工优惠10
*/
public UFDouble getYouhui_sg_10 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_10);
 } 

/** 
* 设置手工优惠10
*
* @param youhui_sg_10 手工优惠10
*/
public void setYouhui_sg_10 ( UFDouble youhui_sg_10) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_10,youhui_sg_10);
 } 

/** 
* 获取手工优惠
*
* @return 手工优惠
*/
public UFDouble getYouhui_shougong () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SHOUGONG);
 } 

/** 
* 设置手工优惠
*
* @param youhui_shougong 手工优惠
*/
public void setYouhui_shougong ( UFDouble youhui_shougong) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SHOUGONG,youhui_shougong);
 } 

/** 
* 获取自动优惠
*
* @return 自动优惠
*/
public UFDouble getYouhui_zidong () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_ZIDONG);
 } 

/** 
* 设置自动优惠
*
* @param youhui_zidong 自动优惠
*/
public void setYouhui_zidong ( UFDouble youhui_zidong) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_ZIDONG,youhui_zidong);
 } 

/** 
* 获取支票
*
* @return 支票
*/
public UFDouble getZhipiao () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.ZHIPIAO);
 } 

/** 
* 设置支票
*
* @param zhipiao 支票
*/
public void setZhipiao ( UFDouble zhipiao) {
this.setAttributeValue( ZhangdanBVO.ZHIPIAO,zhipiao);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_ZhangdanBVO");
  }

@Override
public int compareTo (ZhangdanBVO other) {
	
	Map<String, Integer> map = new HashMap<>();
	map.put("儿童门票", 1);
	map.put("超时儿童门票", 2);
	map.put("儿童浴资119元", 3);
	map.put("儿童浴资169元", 4);
	
	String thisSqName = this.getSq_name();
	String otherSqName = other.getSq_name();
	
	Integer thisSqIndex = PuPubVO.getInteger_NullAs(map.get(thisSqName), 0);
	Integer otherSqIndex = PuPubVO.getInteger_NullAs(map.get(otherSqName), 0);
	
	if (thisSqIndex > otherSqIndex) return 1;
	else if (thisSqIndex < otherSqIndex) return -1;
	
	return 0;
}
}