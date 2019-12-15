package nc.vo.hkjt.srgk.huiguan.zhangdan;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ZhangdanBVO extends SuperVO {
/**
*���Ÿ�ID
*/
public static final String BM_FID="bm_fid";
/**
*����id
*/
public static final String BM_ID="bm_id";
/**
*��������
*/
public static final String BM_NAME="bm_name";
/**
*Դͷ���ݱ���id
*/
public static final String CFIRSTBILLBID="cfirstbillbid";
/**
*Դͷ����id
*/
public static final String CFIRSTBILLID="cfirstbillid";
/**
*Դͷ��������
*/
public static final String CFIRSTTYPECODE="cfirsttypecode";
/**
*�俨���루�ο���
*/
public static final String CHONGKA_CK_SR="chongka_ck_sr";
/**
*�俨Ӧ�գ��ο���
*/
public static final String CHONGKA_CK_YS="chongka_ck_ys";
/**
*�俨���루��Ա����
*/
public static final String CHONGKA_HYK_SR="chongka_hyk_sr";
/**
*�俨Ӧ�գ���Ա����
*/
public static final String CHONGKA_HYK_YS="chongka_hyk_ys";
/**
*�ο�
*/
public static final String CIKA="cika";
/**
*�ϲ㵥�ݱ���id
*/
public static final String CSOURCEBILLBID="csourcebillbid";
/**
*�ϲ㵥��id
*/
public static final String CSOURCEBILLID="csourcebillid";
/**
*�ϲ㵥������
*/
public static final String CSOURCETYPECODE="csourcetypecode";
/**
*����ȯ
*/
public static final String DAIJINQUAN="daijinquan";
/**
*�������
*/
public static final String FENQU="fenqu";
/**
*����
*/
public static final String GUAZHANG="guazhang";
/**
*����_��Ϣ
*/
public static final String GUAZHANG_INFO="guazhang_info";
/**
*��Ա��
*/
public static final String HUIYUANKA="huiyuanka";
/**
*���ƺ�
*/
public static final String KEYID="keyid";
/**
*mebercardid
*/
public static final String MEBERCARDID="mebercardid";
/**
*�ⵥ
*/
public static final String MIANDAN="miandan";
/**
*numberxount
*/
public static final String NUMBERXOUNT="numberxount";
/**
*�ϲ㵥������
*/
public static final String PK_HK_DZPT_HG_ZHANGDAN="pk_hk_dzpt_hg_zhangdan";
/**
*�ӱ�����
*/
public static final String PK_HK_DZPT_HG_ZHANGDAN_B="pk_hk_dzpt_hg_zhangdan_b";
/**
*POS
*/
public static final String POS="pos";
/**
*ʵ��
*/
public static final String SHISHOU="shishou";
/**
*����
*/
public static final String SHOURU="shouru";
/**
*��Ʒ����
*/
public static final String SQ_NAME="sq_name";
/**
*��Ʒ����id
*/
public static final String SQFL_ID="sqfl_id";
/**
*��Ʒ����
*/
public static final String SQFL_NAME="sqfl_name";
/**
*������Ŀid
*/
public static final String SRXM_ID="srxm_id";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*�Զ�����01
*/
public static final String VBDEF01="vbdef01";
/**
*�Զ�����02
*/
public static final String VBDEF02="vbdef02";
/**
*�Զ�����03
*/
public static final String VBDEF03="vbdef03";
/**
*�Զ�����04
*/
public static final String VBDEF04="vbdef04";
/**
*�Զ�����05
*/
public static final String VBDEF05="vbdef05";
/**
*�Զ�����06
*/
public static final String VBDEF06="vbdef06";
/**
*�Զ�����07
*/
public static final String VBDEF07="vbdef07";
/**
*�Զ�����08
*/
public static final String VBDEF08="vbdef08";
/**
*�Զ�����09
*/
public static final String VBDEF09="vbdef09";
/**
*�Զ�����10
*/
public static final String VBDEF10="vbdef10";
/**
*��ע
*/
public static final String VBMEMO="vbmemo";
/**
*Դͷ���ݺ�
*/
public static final String VFIRSTBILLCODE="vfirstbillcode";
/**
*�к�
*/
public static final String VROWNO="vrowno";
/**
*�ϴε��ݺ�
*/
public static final String VSOURCEBILLCODE="vsourcebillcode";
/**
*����
*/
public static final String WANGLAI="wanglai";
/**
*����_��Ϣ
*/
public static final String WANGLAI_INFO="wanglai_info";
/**
*ҵ��ϵͳ��ˮ��
*/
public static final String WATERNUM="waternum";
/**
*�ֽ�
*/
public static final String XIANJIN="xianjin";
/**
*Ӧ��
*/
public static final String YINGSHOU="yingshou";
/**
*�������Ż�
*/
public static final String YOUHUI_KABILI="youhui_kabili";
/**
*�Ż�_����
*/
public static final String YOUHUI_QT="youhui_qt";
/**
*�ֹ��Ż�01
*/
public static final String YOUHUI_SG_01="youhui_sg_01";
/**
*�ֹ��Ż�02
*/
public static final String YOUHUI_SG_02="youhui_sg_02";
/**
*�ֹ��Ż�03
*/
public static final String YOUHUI_SG_03="youhui_sg_03";
/**
*�ֹ��Ż�04
*/
public static final String YOUHUI_SG_04="youhui_sg_04";
/**
*�ֹ��Ż�05
*/
public static final String YOUHUI_SG_05="youhui_sg_05";
/**
*�ֹ��Ż�06
*/
public static final String YOUHUI_SG_06="youhui_sg_06";
/**
*�ֹ��Ż�07
*/
public static final String YOUHUI_SG_07="youhui_sg_07";
/**
*�ֹ��Ż�08
*/
public static final String YOUHUI_SG_08="youhui_sg_08";
/**
*�ֹ��Ż�09
*/
public static final String YOUHUI_SG_09="youhui_sg_09";
/**
*�ֹ��Ż�10
*/
public static final String YOUHUI_SG_10="youhui_sg_10";
/**
*�ֹ��Ż�
*/
public static final String YOUHUI_SHOUGONG="youhui_shougong";
/**
*�Զ��Ż�
*/
public static final String YOUHUI_ZIDONG="youhui_zidong";
/**
*֧Ʊ
*/
public static final String ZHIPIAO="zhipiao";
/** 
* ��ȡ���Ÿ�ID
*
* @return ���Ÿ�ID
*/
public String getBm_fid () {
return (String) this.getAttributeValue( ZhangdanBVO.BM_FID);
 } 

/** 
* ���ò��Ÿ�ID
*
* @param bm_fid ���Ÿ�ID
*/
public void setBm_fid ( String bm_fid) {
this.setAttributeValue( ZhangdanBVO.BM_FID,bm_fid);
 } 

/** 
* ��ȡ����id
*
* @return ����id
*/
public String getBm_id () {
return (String) this.getAttributeValue( ZhangdanBVO.BM_ID);
 } 

/** 
* ���ò���id
*
* @param bm_id ����id
*/
public void setBm_id ( String bm_id) {
this.setAttributeValue( ZhangdanBVO.BM_ID,bm_id);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getBm_name () {
return (String) this.getAttributeValue( ZhangdanBVO.BM_NAME);
 } 

/** 
* ���ò�������
*
* @param bm_name ��������
*/
public void setBm_name ( String bm_name) {
this.setAttributeValue( ZhangdanBVO.BM_NAME,bm_name);
 } 

/** 
* ��ȡԴͷ���ݱ���id
*
* @return Դͷ���ݱ���id
*/
public String getCfirstbillbid () {
return (String) this.getAttributeValue( ZhangdanBVO.CFIRSTBILLBID);
 } 

/** 
* ����Դͷ���ݱ���id
*
* @param cfirstbillbid Դͷ���ݱ���id
*/
public void setCfirstbillbid ( String cfirstbillbid) {
this.setAttributeValue( ZhangdanBVO.CFIRSTBILLBID,cfirstbillbid);
 } 

/** 
* ��ȡԴͷ����id
*
* @return Դͷ����id
*/
public String getCfirstbillid () {
return (String) this.getAttributeValue( ZhangdanBVO.CFIRSTBILLID);
 } 

/** 
* ����Դͷ����id
*
* @param cfirstbillid Դͷ����id
*/
public void setCfirstbillid ( String cfirstbillid) {
this.setAttributeValue( ZhangdanBVO.CFIRSTBILLID,cfirstbillid);
 } 

/** 
* ��ȡԴͷ��������
*
* @return Դͷ��������
*/
public String getCfirsttypecode () {
return (String) this.getAttributeValue( ZhangdanBVO.CFIRSTTYPECODE);
 } 

/** 
* ����Դͷ��������
*
* @param cfirsttypecode Դͷ��������
*/
public void setCfirsttypecode ( String cfirsttypecode) {
this.setAttributeValue( ZhangdanBVO.CFIRSTTYPECODE,cfirsttypecode);
 } 

/** 
* ��ȡ�俨���루�ο���
*
* @return �俨���루�ο���
*/
public UFDouble getChongka_ck_sr () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.CHONGKA_CK_SR);
 } 

/** 
* ���ó俨���루�ο���
*
* @param chongka_ck_sr �俨���루�ο���
*/
public void setChongka_ck_sr ( UFDouble chongka_ck_sr) {
this.setAttributeValue( ZhangdanBVO.CHONGKA_CK_SR,chongka_ck_sr);
 } 

/** 
* ��ȡ�俨Ӧ�գ��ο���
*
* @return �俨Ӧ�գ��ο���
*/
public UFDouble getChongka_ck_ys () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.CHONGKA_CK_YS);
 } 

/** 
* ���ó俨Ӧ�գ��ο���
*
* @param chongka_ck_ys �俨Ӧ�գ��ο���
*/
public void setChongka_ck_ys ( UFDouble chongka_ck_ys) {
this.setAttributeValue( ZhangdanBVO.CHONGKA_CK_YS,chongka_ck_ys);
 } 

/** 
* ��ȡ�俨���루��Ա����
*
* @return �俨���루��Ա����
*/
public UFDouble getChongka_hyk_sr () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.CHONGKA_HYK_SR);
 } 

/** 
* ���ó俨���루��Ա����
*
* @param chongka_hyk_sr �俨���루��Ա����
*/
public void setChongka_hyk_sr ( UFDouble chongka_hyk_sr) {
this.setAttributeValue( ZhangdanBVO.CHONGKA_HYK_SR,chongka_hyk_sr);
 } 

/** 
* ��ȡ�俨Ӧ�գ���Ա����
*
* @return �俨Ӧ�գ���Ա����
*/
public UFDouble getChongka_hyk_ys () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.CHONGKA_HYK_YS);
 } 

/** 
* ���ó俨Ӧ�գ���Ա����
*
* @param chongka_hyk_ys �俨Ӧ�գ���Ա����
*/
public void setChongka_hyk_ys ( UFDouble chongka_hyk_ys) {
this.setAttributeValue( ZhangdanBVO.CHONGKA_HYK_YS,chongka_hyk_ys);
 } 

/** 
* ��ȡ�ο�
*
* @return �ο�
*/
public UFDouble getCika () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.CIKA);
 } 

/** 
* ���ôο�
*
* @param cika �ο�
*/
public void setCika ( UFDouble cika) {
this.setAttributeValue( ZhangdanBVO.CIKA,cika);
 } 

/** 
* ��ȡ�ϲ㵥�ݱ���id
*
* @return �ϲ㵥�ݱ���id
*/
public String getCsourcebillbid () {
return (String) this.getAttributeValue( ZhangdanBVO.CSOURCEBILLBID);
 } 

/** 
* �����ϲ㵥�ݱ���id
*
* @param csourcebillbid �ϲ㵥�ݱ���id
*/
public void setCsourcebillbid ( String csourcebillbid) {
this.setAttributeValue( ZhangdanBVO.CSOURCEBILLBID,csourcebillbid);
 } 

/** 
* ��ȡ�ϲ㵥��id
*
* @return �ϲ㵥��id
*/
public String getCsourcebillid () {
return (String) this.getAttributeValue( ZhangdanBVO.CSOURCEBILLID);
 } 

/** 
* �����ϲ㵥��id
*
* @param csourcebillid �ϲ㵥��id
*/
public void setCsourcebillid ( String csourcebillid) {
this.setAttributeValue( ZhangdanBVO.CSOURCEBILLID,csourcebillid);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getCsourcetypecode () {
return (String) this.getAttributeValue( ZhangdanBVO.CSOURCETYPECODE);
 } 

/** 
* �����ϲ㵥������
*
* @param csourcetypecode �ϲ㵥������
*/
public void setCsourcetypecode ( String csourcetypecode) {
this.setAttributeValue( ZhangdanBVO.CSOURCETYPECODE,csourcetypecode);
 } 

/** 
* ��ȡ����ȯ
*
* @return ����ȯ
*/
public UFDouble getDaijinquan () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.DAIJINQUAN);
 } 

/** 
* ���ô���ȯ
*
* @param daijinquan ����ȯ
*/
public void setDaijinquan ( UFDouble daijinquan) {
this.setAttributeValue( ZhangdanBVO.DAIJINQUAN,daijinquan);
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getFenqu () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.FENQU);
 } 

/** 
* ���÷������
*
* @param fenqu �������
*/
public void setFenqu ( UFDouble fenqu) {
this.setAttributeValue( ZhangdanBVO.FENQU,fenqu);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getGuazhang () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.GUAZHANG);
 } 

/** 
* ���ù���
*
* @param guazhang ����
*/
public void setGuazhang ( UFDouble guazhang) {
this.setAttributeValue( ZhangdanBVO.GUAZHANG,guazhang);
 } 

/** 
* ��ȡ����_��Ϣ
*
* @return ����_��Ϣ
*/
public String getGuazhang_info () {
return (String) this.getAttributeValue( ZhangdanBVO.GUAZHANG_INFO);
 } 

/** 
* ���ù���_��Ϣ
*
* @param guazhang_info ����_��Ϣ
*/
public void setGuazhang_info ( String guazhang_info) {
this.setAttributeValue( ZhangdanBVO.GUAZHANG_INFO,guazhang_info);
 } 

/** 
* ��ȡ��Ա��
*
* @return ��Ա��
*/
public UFDouble getHuiyuanka () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.HUIYUANKA);
 } 

/** 
* ���û�Ա��
*
* @param huiyuanka ��Ա��
*/
public void setHuiyuanka ( UFDouble huiyuanka) {
this.setAttributeValue( ZhangdanBVO.HUIYUANKA,huiyuanka);
 } 

/** 
* ��ȡ���ƺ�
*
* @return ���ƺ�
*/
public String getKeyid () {
return (String) this.getAttributeValue( ZhangdanBVO.KEYID);
 } 

/** 
* �������ƺ�
*
* @param keyid ���ƺ�
*/
public void setKeyid ( String keyid) {
this.setAttributeValue( ZhangdanBVO.KEYID,keyid);
 } 

/** 
* ��ȡmebercardid
*
* @return mebercardid
*/
public String getMebercardid () {
return (String) this.getAttributeValue( ZhangdanBVO.MEBERCARDID);
 } 

/** 
* ����mebercardid
*
* @param mebercardid mebercardid
*/
public void setMebercardid ( String mebercardid) {
this.setAttributeValue( ZhangdanBVO.MEBERCARDID,mebercardid);
 } 

/** 
* ��ȡ�ⵥ
*
* @return �ⵥ
*/
public UFDouble getMiandan () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.MIANDAN);
 } 

/** 
* �����ⵥ
*
* @param miandan �ⵥ
*/
public void setMiandan ( UFDouble miandan) {
this.setAttributeValue( ZhangdanBVO.MIANDAN,miandan);
 } 

/** 
* ��ȡnumberxount
*
* @return numberxount
*/
public UFDouble getNumberxount () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.NUMBERXOUNT);
 } 

/** 
* ����numberxount
*
* @param numberxount numberxount
*/
public void setNumberxount ( UFDouble numberxount) {
this.setAttributeValue( ZhangdanBVO.NUMBERXOUNT,numberxount);
 } 

/** 
* ��ȡ�ϲ㵥������
*
* @return �ϲ㵥������
*/
public String getPk_hk_dzpt_hg_zhangdan () {
return (String) this.getAttributeValue( ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN);
 } 

/** 
* �����ϲ㵥������
*
* @param pk_hk_dzpt_hg_zhangdan �ϲ㵥������
*/
public void setPk_hk_dzpt_hg_zhangdan ( String pk_hk_dzpt_hg_zhangdan) {
this.setAttributeValue( ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN,pk_hk_dzpt_hg_zhangdan);
 } 

/** 
* ��ȡ�ӱ�����
*
* @return �ӱ�����
*/
public String getPk_hk_dzpt_hg_zhangdan_b () {
return (String) this.getAttributeValue( ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN_B);
 } 

/** 
* �����ӱ�����
*
* @param pk_hk_dzpt_hg_zhangdan_b �ӱ�����
*/
public void setPk_hk_dzpt_hg_zhangdan_b ( String pk_hk_dzpt_hg_zhangdan_b) {
this.setAttributeValue( ZhangdanBVO.PK_HK_DZPT_HG_ZHANGDAN_B,pk_hk_dzpt_hg_zhangdan_b);
 } 

/** 
* ��ȡPOS
*
* @return POS
*/
public UFDouble getPos () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.POS);
 } 

/** 
* ����POS
*
* @param pos POS
*/
public void setPos ( UFDouble pos) {
this.setAttributeValue( ZhangdanBVO.POS,pos);
 } 

/** 
* ��ȡʵ��
*
* @return ʵ��
*/
public UFDouble getShishou () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.SHISHOU);
 } 

/** 
* ����ʵ��
*
* @param shishou ʵ��
*/
public void setShishou ( UFDouble shishou) {
this.setAttributeValue( ZhangdanBVO.SHISHOU,shishou);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getShouru () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.SHOURU);
 } 

/** 
* ��������
*
* @param shouru ����
*/
public void setShouru ( UFDouble shouru) {
this.setAttributeValue( ZhangdanBVO.SHOURU,shouru);
 } 

/** 
* ��ȡ��Ʒ����
*
* @return ��Ʒ����
*/
public String getSq_name () {
return (String) this.getAttributeValue( ZhangdanBVO.SQ_NAME);
 } 

/** 
* ������Ʒ����
*
* @param sq_name ��Ʒ����
*/
public void setSq_name ( String sq_name) {
this.setAttributeValue( ZhangdanBVO.SQ_NAME,sq_name);
 } 

/** 
* ��ȡ��Ʒ����id
*
* @return ��Ʒ����id
*/
public String getSqfl_id () {
return (String) this.getAttributeValue( ZhangdanBVO.SQFL_ID);
 } 

/** 
* ������Ʒ����id
*
* @param sqfl_id ��Ʒ����id
*/
public void setSqfl_id ( String sqfl_id) {
this.setAttributeValue( ZhangdanBVO.SQFL_ID,sqfl_id);
 } 

/** 
* ��ȡ��Ʒ����
*
* @return ��Ʒ����
*/
public String getSqfl_name () {
return (String) this.getAttributeValue( ZhangdanBVO.SQFL_NAME);
 } 

/** 
* ������Ʒ����
*
* @param sqfl_name ��Ʒ����
*/
public void setSqfl_name ( String sqfl_name) {
this.setAttributeValue( ZhangdanBVO.SQFL_NAME,sqfl_name);
 } 

/** 
* ��ȡ������Ŀid
*
* @return ������Ŀid
*/
public String getSrxm_id () {
return (String) this.getAttributeValue( ZhangdanBVO.SRXM_ID);
 } 

/** 
* ����������Ŀid
*
* @param srxm_id ������Ŀid
*/
public void setSrxm_id ( String srxm_id) {
this.setAttributeValue( ZhangdanBVO.SRXM_ID,srxm_id);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( ZhangdanBVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( ZhangdanBVO.TS,ts);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVbdef01 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vbdef01 �Զ�����01
*/
public void setVbdef01 ( String vbdef01) {
this.setAttributeValue( ZhangdanBVO.VBDEF01,vbdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVbdef02 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vbdef02 �Զ�����02
*/
public void setVbdef02 ( String vbdef02) {
this.setAttributeValue( ZhangdanBVO.VBDEF02,vbdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVbdef03 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vbdef03 �Զ�����03
*/
public void setVbdef03 ( String vbdef03) {
this.setAttributeValue( ZhangdanBVO.VBDEF03,vbdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVbdef04 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vbdef04 �Զ�����04
*/
public void setVbdef04 ( String vbdef04) {
this.setAttributeValue( ZhangdanBVO.VBDEF04,vbdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVbdef05 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vbdef05 �Զ�����05
*/
public void setVbdef05 ( String vbdef05) {
this.setAttributeValue( ZhangdanBVO.VBDEF05,vbdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVbdef06 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vbdef06 �Զ�����06
*/
public void setVbdef06 ( String vbdef06) {
this.setAttributeValue( ZhangdanBVO.VBDEF06,vbdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVbdef07 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vbdef07 �Զ�����07
*/
public void setVbdef07 ( String vbdef07) {
this.setAttributeValue( ZhangdanBVO.VBDEF07,vbdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVbdef08 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vbdef08 �Զ�����08
*/
public void setVbdef08 ( String vbdef08) {
this.setAttributeValue( ZhangdanBVO.VBDEF08,vbdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVbdef09 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vbdef09 �Զ�����09
*/
public void setVbdef09 ( String vbdef09) {
this.setAttributeValue( ZhangdanBVO.VBDEF09,vbdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVbdef10 () {
return (String) this.getAttributeValue( ZhangdanBVO.VBDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vbdef10 �Զ�����10
*/
public void setVbdef10 ( String vbdef10) {
this.setAttributeValue( ZhangdanBVO.VBDEF10,vbdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVbmemo () {
return (String) this.getAttributeValue( ZhangdanBVO.VBMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vbmemo ��ע
*/
public void setVbmemo ( String vbmemo) {
this.setAttributeValue( ZhangdanBVO.VBMEMO,vbmemo);
 } 

/** 
* ��ȡԴͷ���ݺ�
*
* @return Դͷ���ݺ�
*/
public String getVfirstbillcode () {
return (String) this.getAttributeValue( ZhangdanBVO.VFIRSTBILLCODE);
 } 

/** 
* ����Դͷ���ݺ�
*
* @param vfirstbillcode Դͷ���ݺ�
*/
public void setVfirstbillcode ( String vfirstbillcode) {
this.setAttributeValue( ZhangdanBVO.VFIRSTBILLCODE,vfirstbillcode);
 } 

/** 
* ��ȡ�к�
*
* @return �к�
*/
public String getVrowno () {
return (String) this.getAttributeValue( ZhangdanBVO.VROWNO);
 } 

/** 
* �����к�
*
* @param vrowno �к�
*/
public void setVrowno ( String vrowno) {
this.setAttributeValue( ZhangdanBVO.VROWNO,vrowno);
 } 

/** 
* ��ȡ�ϴε��ݺ�
*
* @return �ϴε��ݺ�
*/
public String getVsourcebillcode () {
return (String) this.getAttributeValue( ZhangdanBVO.VSOURCEBILLCODE);
 } 

/** 
* �����ϴε��ݺ�
*
* @param vsourcebillcode �ϴε��ݺ�
*/
public void setVsourcebillcode ( String vsourcebillcode) {
this.setAttributeValue( ZhangdanBVO.VSOURCEBILLCODE,vsourcebillcode);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getWanglai () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.WANGLAI);
 } 

/** 
* ��������
*
* @param wanglai ����
*/
public void setWanglai ( UFDouble wanglai) {
this.setAttributeValue( ZhangdanBVO.WANGLAI,wanglai);
 } 

/** 
* ��ȡ����_��Ϣ
*
* @return ����_��Ϣ
*/
public String getWanglai_info () {
return (String) this.getAttributeValue( ZhangdanBVO.WANGLAI_INFO);
 } 

/** 
* ��������_��Ϣ
*
* @param wanglai_info ����_��Ϣ
*/
public void setWanglai_info ( String wanglai_info) {
this.setAttributeValue( ZhangdanBVO.WANGLAI_INFO,wanglai_info);
 } 

/** 
* ��ȡҵ��ϵͳ��ˮ��
*
* @return ҵ��ϵͳ��ˮ��
*/
public String getWaternum () {
return (String) this.getAttributeValue( ZhangdanBVO.WATERNUM);
 } 

/** 
* ����ҵ��ϵͳ��ˮ��
*
* @param waternum ҵ��ϵͳ��ˮ��
*/
public void setWaternum ( String waternum) {
this.setAttributeValue( ZhangdanBVO.WATERNUM,waternum);
 } 

/** 
* ��ȡ�ֽ�
*
* @return �ֽ�
*/
public UFDouble getXianjin () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.XIANJIN);
 } 

/** 
* �����ֽ�
*
* @param xianjin �ֽ�
*/
public void setXianjin ( UFDouble xianjin) {
this.setAttributeValue( ZhangdanBVO.XIANJIN,xianjin);
 } 

/** 
* ��ȡӦ��
*
* @return Ӧ��
*/
public UFDouble getYingshou () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YINGSHOU);
 } 

/** 
* ����Ӧ��
*
* @param yingshou Ӧ��
*/
public void setYingshou ( UFDouble yingshou) {
this.setAttributeValue( ZhangdanBVO.YINGSHOU,yingshou);
 } 

/** 
* ��ȡ�������Ż�
*
* @return �������Ż�
*/
public UFDouble getYouhui_kabili () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_KABILI);
 } 

/** 
* ���ÿ������Ż�
*
* @param youhui_kabili �������Ż�
*/
public void setYouhui_kabili ( UFDouble youhui_kabili) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_KABILI,youhui_kabili);
 } 

/** 
* ��ȡ�Ż�_����
*
* @return �Ż�_����
*/
public UFDouble getYouhui_qt () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_QT);
 } 

/** 
* �����Ż�_����
*
* @param youhui_qt �Ż�_����
*/
public void setYouhui_qt ( UFDouble youhui_qt) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_QT,youhui_qt);
 } 

/** 
* ��ȡ�ֹ��Ż�01
*
* @return �ֹ��Ż�01
*/
public UFDouble getYouhui_sg_01 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_01);
 } 

/** 
* �����ֹ��Ż�01
*
* @param youhui_sg_01 �ֹ��Ż�01
*/
public void setYouhui_sg_01 ( UFDouble youhui_sg_01) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_01,youhui_sg_01);
 } 

/** 
* ��ȡ�ֹ��Ż�02
*
* @return �ֹ��Ż�02
*/
public UFDouble getYouhui_sg_02 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_02);
 } 

/** 
* �����ֹ��Ż�02
*
* @param youhui_sg_02 �ֹ��Ż�02
*/
public void setYouhui_sg_02 ( UFDouble youhui_sg_02) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_02,youhui_sg_02);
 } 

/** 
* ��ȡ�ֹ��Ż�03
*
* @return �ֹ��Ż�03
*/
public UFDouble getYouhui_sg_03 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_03);
 } 

/** 
* �����ֹ��Ż�03
*
* @param youhui_sg_03 �ֹ��Ż�03
*/
public void setYouhui_sg_03 ( UFDouble youhui_sg_03) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_03,youhui_sg_03);
 } 

/** 
* ��ȡ�ֹ��Ż�04
*
* @return �ֹ��Ż�04
*/
public UFDouble getYouhui_sg_04 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_04);
 } 

/** 
* �����ֹ��Ż�04
*
* @param youhui_sg_04 �ֹ��Ż�04
*/
public void setYouhui_sg_04 ( UFDouble youhui_sg_04) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_04,youhui_sg_04);
 } 

/** 
* ��ȡ�ֹ��Ż�05
*
* @return �ֹ��Ż�05
*/
public UFDouble getYouhui_sg_05 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_05);
 } 

/** 
* �����ֹ��Ż�05
*
* @param youhui_sg_05 �ֹ��Ż�05
*/
public void setYouhui_sg_05 ( UFDouble youhui_sg_05) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_05,youhui_sg_05);
 } 

/** 
* ��ȡ�ֹ��Ż�06
*
* @return �ֹ��Ż�06
*/
public UFDouble getYouhui_sg_06 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_06);
 } 

/** 
* �����ֹ��Ż�06
*
* @param youhui_sg_06 �ֹ��Ż�06
*/
public void setYouhui_sg_06 ( UFDouble youhui_sg_06) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_06,youhui_sg_06);
 } 

/** 
* ��ȡ�ֹ��Ż�07
*
* @return �ֹ��Ż�07
*/
public UFDouble getYouhui_sg_07 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_07);
 } 

/** 
* �����ֹ��Ż�07
*
* @param youhui_sg_07 �ֹ��Ż�07
*/
public void setYouhui_sg_07 ( UFDouble youhui_sg_07) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_07,youhui_sg_07);
 } 

/** 
* ��ȡ�ֹ��Ż�08
*
* @return �ֹ��Ż�08
*/
public UFDouble getYouhui_sg_08 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_08);
 } 

/** 
* �����ֹ��Ż�08
*
* @param youhui_sg_08 �ֹ��Ż�08
*/
public void setYouhui_sg_08 ( UFDouble youhui_sg_08) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_08,youhui_sg_08);
 } 

/** 
* ��ȡ�ֹ��Ż�09
*
* @return �ֹ��Ż�09
*/
public UFDouble getYouhui_sg_09 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_09);
 } 

/** 
* �����ֹ��Ż�09
*
* @param youhui_sg_09 �ֹ��Ż�09
*/
public void setYouhui_sg_09 ( UFDouble youhui_sg_09) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_09,youhui_sg_09);
 } 

/** 
* ��ȡ�ֹ��Ż�10
*
* @return �ֹ��Ż�10
*/
public UFDouble getYouhui_sg_10 () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SG_10);
 } 

/** 
* �����ֹ��Ż�10
*
* @param youhui_sg_10 �ֹ��Ż�10
*/
public void setYouhui_sg_10 ( UFDouble youhui_sg_10) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SG_10,youhui_sg_10);
 } 

/** 
* ��ȡ�ֹ��Ż�
*
* @return �ֹ��Ż�
*/
public UFDouble getYouhui_shougong () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_SHOUGONG);
 } 

/** 
* �����ֹ��Ż�
*
* @param youhui_shougong �ֹ��Ż�
*/
public void setYouhui_shougong ( UFDouble youhui_shougong) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_SHOUGONG,youhui_shougong);
 } 

/** 
* ��ȡ�Զ��Ż�
*
* @return �Զ��Ż�
*/
public UFDouble getYouhui_zidong () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.YOUHUI_ZIDONG);
 } 

/** 
* �����Զ��Ż�
*
* @param youhui_zidong �Զ��Ż�
*/
public void setYouhui_zidong ( UFDouble youhui_zidong) {
this.setAttributeValue( ZhangdanBVO.YOUHUI_ZIDONG,youhui_zidong);
 } 

/** 
* ��ȡ֧Ʊ
*
* @return ֧Ʊ
*/
public UFDouble getZhipiao () {
return (UFDouble) this.getAttributeValue( ZhangdanBVO.ZHIPIAO);
 } 

/** 
* ����֧Ʊ
*
* @param zhipiao ֧Ʊ
*/
public void setZhipiao ( UFDouble zhipiao) {
this.setAttributeValue( ZhangdanBVO.ZHIPIAO,zhipiao);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_ZhangdanBVO");
  }
}