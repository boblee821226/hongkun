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
*�����
*/
public static final String APPROVER="approver";
/**
*���
*/
public static final String BANCI="banci";
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
*�Ƶ�ʱ��
*/
public static final String CREATIONTIME="creationtime";
/**
*�Ƶ���
*/
public static final String CREATOR="creator";
/**
*����ȯ
*/
public static final String DAIJINQUAN="daijinquan";
/**
*��������
*/
public static final String DBILLDATE="dbilldate";
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
*��Ա�����ѽ��
*/
public static final String HUIYUANKA="huiyuanka";
/**
*��Ա���Żݽ��
*/
public static final String HUIYUANKA_BL="huiyuanka_bl";
/**
*��Ա����Ϣ
*/
public static final String HUIYUANKA_INFO="huiyuanka_info";
/**
*��Ա��ʵ�ʽ��
*/
public static final String HUIYUANKA_SJ="huiyuanka_sj";
/**
*����״̬
*/
public static final String IBILLSTATUS="ibillstatus";
/**
*�ⵥ
*/
public static final String MIANDAN="miandan";
/**
*�޸�ʱ��
*/
public static final String MODIFIEDTIME="modifiedtime";
/**
*�޸���
*/
public static final String MODIFIER="modifier";
/**
*ҵ������
*/
public static final String PK_BUSITYPE="pk_busitype";
/**
*����
*/
public static final String PK_GROUP="pk_group";
/**
*��������
*/
public static final String PK_HK_DZPT_HG_ZHANGDAN="pk_hk_dzpt_hg_zhangdan";
/**
*��֯
*/
public static final String PK_ORG="pk_org";
/**
*��֯v
*/
public static final String PK_ORG_V="pk_org_v";
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
*���ʱ��
*/
public static final String TAUDITTIME="taudittime";
/**
*ʱ���
*/
public static final String TS="ts";
/**
*��������
*/
public static final String VAPPROVENOTE="vapprovenote";
/**
*���ݱ��
*/
public static final String VBILLCODE="vbillcode";
/**
*��������
*/
public static final String VBILLTYPECODE="vbilltypecode";
/**
*�Զ�����01
*/
public static final String VDEF01="vdef01";
/**
*�Զ�����02
*/
public static final String VDEF02="vdef02";
/**
*�Զ�����03
*/
public static final String VDEF03="vdef03";
/**
*�Զ�����04
*/
public static final String VDEF04="vdef04";
/**
*�Զ�����05
*/
public static final String VDEF05="vdef05";
/**
*�Զ�����06
*/
public static final String VDEF06="vdef06";
/**
*�Զ�����07
*/
public static final String VDEF07="vdef07";
/**
*�Զ�����08
*/
public static final String VDEF08="vdef08";
/**
*�Զ�����09
*/
public static final String VDEF09="vdef09";
/**
*�Զ�����10
*/
public static final String VDEF10="vdef10";
/**
*��ע
*/
public static final String VMEMO="vmemo";
/**
*��������
*/
public static final String VTRANTYPECODE="vtrantypecode";
/**
*����
*/
public static final String WANGLAI="wanglai";
/**
*����_��Ϣ
*/
public static final String WANGLAI_INFO="wanglai_info";
/**
*�ֽ�
*/
public static final String XIANJIN="xianjin";
/**
*Ӧ��
*/
public static final String YINGSHOU="yingshou";
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
*֧Ʊ
*/
public static final String ZHIPIAO="zhipiao";
/** 
* ��ȡ�����
*
* @return �����
*/
public String getApprover () {
return (String) this.getAttributeValue( ZhangdanHVO.APPROVER);
 } 

/** 
* ���������
*
* @param approver �����
*/
public void setApprover ( String approver) {
this.setAttributeValue( ZhangdanHVO.APPROVER,approver);
 } 

/** 
* ��ȡ���
*
* @return ���
*/
public String getBanci () {
return (String) this.getAttributeValue( ZhangdanHVO.BANCI);
 } 

/** 
* ���ð��
*
* @param banci ���
*/
public void setBanci ( String banci) {
this.setAttributeValue( ZhangdanHVO.BANCI,banci);
 } 

/** 
* ��ȡ�俨���루�ο���
*
* @return �俨���루�ο���
*/
public UFDouble getChongka_ck_sr () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.CHONGKA_CK_SR);
 } 

/** 
* ���ó俨���루�ο���
*
* @param chongka_ck_sr �俨���루�ο���
*/
public void setChongka_ck_sr ( UFDouble chongka_ck_sr) {
this.setAttributeValue( ZhangdanHVO.CHONGKA_CK_SR,chongka_ck_sr);
 } 

/** 
* ��ȡ�俨Ӧ�գ��ο���
*
* @return �俨Ӧ�գ��ο���
*/
public UFDouble getChongka_ck_ys () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.CHONGKA_CK_YS);
 } 

/** 
* ���ó俨Ӧ�գ��ο���
*
* @param chongka_ck_ys �俨Ӧ�գ��ο���
*/
public void setChongka_ck_ys ( UFDouble chongka_ck_ys) {
this.setAttributeValue( ZhangdanHVO.CHONGKA_CK_YS,chongka_ck_ys);
 } 

/** 
* ��ȡ�俨���루��Ա����
*
* @return �俨���루��Ա����
*/
public UFDouble getChongka_hyk_sr () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.CHONGKA_HYK_SR);
 } 

/** 
* ���ó俨���루��Ա����
*
* @param chongka_hyk_sr �俨���루��Ա����
*/
public void setChongka_hyk_sr ( UFDouble chongka_hyk_sr) {
this.setAttributeValue( ZhangdanHVO.CHONGKA_HYK_SR,chongka_hyk_sr);
 } 

/** 
* ��ȡ�俨Ӧ�գ���Ա����
*
* @return �俨Ӧ�գ���Ա����
*/
public UFDouble getChongka_hyk_ys () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.CHONGKA_HYK_YS);
 } 

/** 
* ���ó俨Ӧ�գ���Ա����
*
* @param chongka_hyk_ys �俨Ӧ�գ���Ա����
*/
public void setChongka_hyk_ys ( UFDouble chongka_hyk_ys) {
this.setAttributeValue( ZhangdanHVO.CHONGKA_HYK_YS,chongka_hyk_ys);
 } 

/** 
* ��ȡ�Ƶ�ʱ��
*
* @return �Ƶ�ʱ��
*/
public UFDateTime getCreationtime () {
return (UFDateTime) this.getAttributeValue( ZhangdanHVO.CREATIONTIME);
 } 

/** 
* �����Ƶ�ʱ��
*
* @param creationtime �Ƶ�ʱ��
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( ZhangdanHVO.CREATIONTIME,creationtime);
 } 

/** 
* ��ȡ�Ƶ���
*
* @return �Ƶ���
*/
public String getCreator () {
return (String) this.getAttributeValue( ZhangdanHVO.CREATOR);
 } 

/** 
* �����Ƶ���
*
* @param creator �Ƶ���
*/
public void setCreator ( String creator) {
this.setAttributeValue( ZhangdanHVO.CREATOR,creator);
 } 

/** 
* ��ȡ����ȯ
*
* @return ����ȯ
*/
public UFDouble getDaijinquan () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.DAIJINQUAN);
 } 

/** 
* ���ô���ȯ
*
* @param daijinquan ����ȯ
*/
public void setDaijinquan ( UFDouble daijinquan) {
this.setAttributeValue( ZhangdanHVO.DAIJINQUAN,daijinquan);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public UFDate getDbilldate () {
return (UFDate) this.getAttributeValue( ZhangdanHVO.DBILLDATE);
 } 

/** 
* ���õ�������
*
* @param dbilldate ��������
*/
public void setDbilldate ( UFDate dbilldate) {
this.setAttributeValue( ZhangdanHVO.DBILLDATE,dbilldate);
 } 

/** 
* ��ȡ�������
*
* @return �������
*/
public UFDouble getFenqu () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.FENQU);
 } 

/** 
* ���÷������
*
* @param fenqu �������
*/
public void setFenqu ( UFDouble fenqu) {
this.setAttributeValue( ZhangdanHVO.FENQU,fenqu);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getGuazhang () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.GUAZHANG);
 } 

/** 
* ���ù���
*
* @param guazhang ����
*/
public void setGuazhang ( UFDouble guazhang) {
this.setAttributeValue( ZhangdanHVO.GUAZHANG,guazhang);
 } 

/** 
* ��ȡ����_��Ϣ
*
* @return ����_��Ϣ
*/
public String getGuazhang_info () {
return (String) this.getAttributeValue( ZhangdanHVO.GUAZHANG_INFO);
 } 

/** 
* ���ù���_��Ϣ
*
* @param guazhang_info ����_��Ϣ
*/
public void setGuazhang_info ( String guazhang_info) {
this.setAttributeValue( ZhangdanHVO.GUAZHANG_INFO,guazhang_info);
 } 

/** 
* ��ȡ��Ա�����ѽ��
*
* @return ��Ա�����ѽ��
*/
public UFDouble getHuiyuanka () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.HUIYUANKA);
 } 

/** 
* ���û�Ա�����ѽ��
*
* @param huiyuanka ��Ա�����ѽ��
*/
public void setHuiyuanka ( UFDouble huiyuanka) {
this.setAttributeValue( ZhangdanHVO.HUIYUANKA,huiyuanka);
 } 

/** 
* ��ȡ��Ա���Żݽ��
*
* @return ��Ա���Żݽ��
*/
public UFDouble getHuiyuanka_bl () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.HUIYUANKA_BL);
 } 

/** 
* ���û�Ա���Żݽ��
*
* @param huiyuanka_bl ��Ա���Żݽ��
*/
public void setHuiyuanka_bl ( UFDouble huiyuanka_bl) {
this.setAttributeValue( ZhangdanHVO.HUIYUANKA_BL,huiyuanka_bl);
 } 

/** 
* ��ȡ��Ա����Ϣ
*
* @return ��Ա����Ϣ
*/
public String getHuiyuanka_info () {
return (String) this.getAttributeValue( ZhangdanHVO.HUIYUANKA_INFO);
 } 

/** 
* ���û�Ա����Ϣ
*
* @param huiyuanka_info ��Ա����Ϣ
*/
public void setHuiyuanka_info ( String huiyuanka_info) {
this.setAttributeValue( ZhangdanHVO.HUIYUANKA_INFO,huiyuanka_info);
 } 

/** 
* ��ȡ��Ա��ʵ�ʽ��
*
* @return ��Ա��ʵ�ʽ��
*/
public UFDouble getHuiyuanka_sj () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.HUIYUANKA_SJ);
 } 

/** 
* ���û�Ա��ʵ�ʽ��
*
* @param huiyuanka_sj ��Ա��ʵ�ʽ��
*/
public void setHuiyuanka_sj ( UFDouble huiyuanka_sj) {
this.setAttributeValue( ZhangdanHVO.HUIYUANKA_SJ,huiyuanka_sj);
 } 

/** 
* ��ȡ����״̬
*
* @return ����״̬
* @see String
*/
public Integer getIbillstatus () {
return (Integer) this.getAttributeValue( ZhangdanHVO.IBILLSTATUS);
 } 

/** 
* ���õ���״̬
*
* @param ibillstatus ����״̬
* @see String
*/
public void setIbillstatus ( Integer ibillstatus) {
this.setAttributeValue( ZhangdanHVO.IBILLSTATUS,ibillstatus);
 } 

/** 
* ��ȡ�ⵥ
*
* @return �ⵥ
*/
public UFDouble getMiandan () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.MIANDAN);
 } 

/** 
* �����ⵥ
*
* @param miandan �ⵥ
*/
public void setMiandan ( UFDouble miandan) {
this.setAttributeValue( ZhangdanHVO.MIANDAN,miandan);
 } 

/** 
* ��ȡ�޸�ʱ��
*
* @return �޸�ʱ��
*/
public UFDateTime getModifiedtime () {
return (UFDateTime) this.getAttributeValue( ZhangdanHVO.MODIFIEDTIME);
 } 

/** 
* �����޸�ʱ��
*
* @param modifiedtime �޸�ʱ��
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( ZhangdanHVO.MODIFIEDTIME,modifiedtime);
 } 

/** 
* ��ȡ�޸���
*
* @return �޸���
*/
public String getModifier () {
return (String) this.getAttributeValue( ZhangdanHVO.MODIFIER);
 } 

/** 
* �����޸���
*
* @param modifier �޸���
*/
public void setModifier ( String modifier) {
this.setAttributeValue( ZhangdanHVO.MODIFIER,modifier);
 } 

/** 
* ��ȡҵ������
*
* @return ҵ������
*/
public String getPk_busitype () {
return (String) this.getAttributeValue( ZhangdanHVO.PK_BUSITYPE);
 } 

/** 
* ����ҵ������
*
* @param pk_busitype ҵ������
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( ZhangdanHVO.PK_BUSITYPE,pk_busitype);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public String getPk_group () {
return (String) this.getAttributeValue( ZhangdanHVO.PK_GROUP);
 } 

/** 
* ���ü���
*
* @param pk_group ����
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( ZhangdanHVO.PK_GROUP,pk_group);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getPk_hk_dzpt_hg_zhangdan () {
return (String) this.getAttributeValue( ZhangdanHVO.PK_HK_DZPT_HG_ZHANGDAN);
 } 

/** 
* ������������
*
* @param pk_hk_dzpt_hg_zhangdan ��������
*/
public void setPk_hk_dzpt_hg_zhangdan ( String pk_hk_dzpt_hg_zhangdan) {
this.setAttributeValue( ZhangdanHVO.PK_HK_DZPT_HG_ZHANGDAN,pk_hk_dzpt_hg_zhangdan);
 } 

/** 
* ��ȡ��֯
*
* @return ��֯
*/
public String getPk_org () {
return (String) this.getAttributeValue( ZhangdanHVO.PK_ORG);
 } 

/** 
* ������֯
*
* @param pk_org ��֯
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( ZhangdanHVO.PK_ORG,pk_org);
 } 

/** 
* ��ȡ��֯v
*
* @return ��֯v
*/
public String getPk_org_v () {
return (String) this.getAttributeValue( ZhangdanHVO.PK_ORG_V);
 } 

/** 
* ������֯v
*
* @param pk_org_v ��֯v
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( ZhangdanHVO.PK_ORG_V,pk_org_v);
 } 

/** 
* ��ȡPOS
*
* @return POS
*/
public UFDouble getPos () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.POS);
 } 

/** 
* ����POS
*
* @param pos POS
*/
public void setPos ( UFDouble pos) {
this.setAttributeValue( ZhangdanHVO.POS,pos);
 } 

/** 
* ��ȡʵ��
*
* @return ʵ��
*/
public UFDouble getShishou () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.SHISHOU);
 } 

/** 
* ����ʵ��
*
* @param shishou ʵ��
*/
public void setShishou ( UFDouble shishou) {
this.setAttributeValue( ZhangdanHVO.SHISHOU,shishou);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getShouru () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.SHOURU);
 } 

/** 
* ��������
*
* @param shouru ����
*/
public void setShouru ( UFDouble shouru) {
this.setAttributeValue( ZhangdanHVO.SHOURU,shouru);
 } 

/** 
* ��ȡ���ʱ��
*
* @return ���ʱ��
*/
public UFDateTime getTaudittime () {
return (UFDateTime) this.getAttributeValue( ZhangdanHVO.TAUDITTIME);
 } 

/** 
* �������ʱ��
*
* @param taudittime ���ʱ��
*/
public void setTaudittime ( UFDateTime taudittime) {
this.setAttributeValue( ZhangdanHVO.TAUDITTIME,taudittime);
 } 

/** 
* ��ȡʱ���
*
* @return ʱ���
*/
public UFDateTime getTs () {
return (UFDateTime) this.getAttributeValue( ZhangdanHVO.TS);
 } 

/** 
* ����ʱ���
*
* @param ts ʱ���
*/
public void setTs ( UFDateTime ts) {
this.setAttributeValue( ZhangdanHVO.TS,ts);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVapprovenote () {
return (String) this.getAttributeValue( ZhangdanHVO.VAPPROVENOTE);
 } 

/** 
* ������������
*
* @param vapprovenote ��������
*/
public void setVapprovenote ( String vapprovenote) {
this.setAttributeValue( ZhangdanHVO.VAPPROVENOTE,vapprovenote);
 } 

/** 
* ��ȡ���ݱ��
*
* @return ���ݱ��
*/
public String getVbillcode () {
return (String) this.getAttributeValue( ZhangdanHVO.VBILLCODE);
 } 

/** 
* ���õ��ݱ��
*
* @param vbillcode ���ݱ��
*/
public void setVbillcode ( String vbillcode) {
this.setAttributeValue( ZhangdanHVO.VBILLCODE,vbillcode);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVbilltypecode () {
return (String) this.getAttributeValue( ZhangdanHVO.VBILLTYPECODE);
 } 

/** 
* ���õ�������
*
* @param vbilltypecode ��������
*/
public void setVbilltypecode ( String vbilltypecode) {
this.setAttributeValue( ZhangdanHVO.VBILLTYPECODE,vbilltypecode);
 } 

/** 
* ��ȡ�Զ�����01
*
* @return �Զ�����01
*/
public String getVdef01 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF01);
 } 

/** 
* �����Զ�����01
*
* @param vdef01 �Զ�����01
*/
public void setVdef01 ( String vdef01) {
this.setAttributeValue( ZhangdanHVO.VDEF01,vdef01);
 } 

/** 
* ��ȡ�Զ�����02
*
* @return �Զ�����02
*/
public String getVdef02 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF02);
 } 

/** 
* �����Զ�����02
*
* @param vdef02 �Զ�����02
*/
public void setVdef02 ( String vdef02) {
this.setAttributeValue( ZhangdanHVO.VDEF02,vdef02);
 } 

/** 
* ��ȡ�Զ�����03
*
* @return �Զ�����03
*/
public String getVdef03 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF03);
 } 

/** 
* �����Զ�����03
*
* @param vdef03 �Զ�����03
*/
public void setVdef03 ( String vdef03) {
this.setAttributeValue( ZhangdanHVO.VDEF03,vdef03);
 } 

/** 
* ��ȡ�Զ�����04
*
* @return �Զ�����04
*/
public String getVdef04 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF04);
 } 

/** 
* �����Զ�����04
*
* @param vdef04 �Զ�����04
*/
public void setVdef04 ( String vdef04) {
this.setAttributeValue( ZhangdanHVO.VDEF04,vdef04);
 } 

/** 
* ��ȡ�Զ�����05
*
* @return �Զ�����05
*/
public String getVdef05 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF05);
 } 

/** 
* �����Զ�����05
*
* @param vdef05 �Զ�����05
*/
public void setVdef05 ( String vdef05) {
this.setAttributeValue( ZhangdanHVO.VDEF05,vdef05);
 } 

/** 
* ��ȡ�Զ�����06
*
* @return �Զ�����06
*/
public String getVdef06 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF06);
 } 

/** 
* �����Զ�����06
*
* @param vdef06 �Զ�����06
*/
public void setVdef06 ( String vdef06) {
this.setAttributeValue( ZhangdanHVO.VDEF06,vdef06);
 } 

/** 
* ��ȡ�Զ�����07
*
* @return �Զ�����07
*/
public String getVdef07 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF07);
 } 

/** 
* �����Զ�����07
*
* @param vdef07 �Զ�����07
*/
public void setVdef07 ( String vdef07) {
this.setAttributeValue( ZhangdanHVO.VDEF07,vdef07);
 } 

/** 
* ��ȡ�Զ�����08
*
* @return �Զ�����08
*/
public String getVdef08 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF08);
 } 

/** 
* �����Զ�����08
*
* @param vdef08 �Զ�����08
*/
public void setVdef08 ( String vdef08) {
this.setAttributeValue( ZhangdanHVO.VDEF08,vdef08);
 } 

/** 
* ��ȡ�Զ�����09
*
* @return �Զ�����09
*/
public String getVdef09 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF09);
 } 

/** 
* �����Զ�����09
*
* @param vdef09 �Զ�����09
*/
public void setVdef09 ( String vdef09) {
this.setAttributeValue( ZhangdanHVO.VDEF09,vdef09);
 } 

/** 
* ��ȡ�Զ�����10
*
* @return �Զ�����10
*/
public String getVdef10 () {
return (String) this.getAttributeValue( ZhangdanHVO.VDEF10);
 } 

/** 
* �����Զ�����10
*
* @param vdef10 �Զ�����10
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( ZhangdanHVO.VDEF10,vdef10);
 } 

/** 
* ��ȡ��ע
*
* @return ��ע
*/
public String getVmemo () {
return (String) this.getAttributeValue( ZhangdanHVO.VMEMO);
 } 

/** 
* ���ñ�ע
*
* @param vmemo ��ע
*/
public void setVmemo ( String vmemo) {
this.setAttributeValue( ZhangdanHVO.VMEMO,vmemo);
 } 

/** 
* ��ȡ��������
*
* @return ��������
*/
public String getVtrantypecode () {
return (String) this.getAttributeValue( ZhangdanHVO.VTRANTYPECODE);
 } 

/** 
* ���ý�������
*
* @param vtrantypecode ��������
*/
public void setVtrantypecode ( String vtrantypecode) {
this.setAttributeValue( ZhangdanHVO.VTRANTYPECODE,vtrantypecode);
 } 

/** 
* ��ȡ����
*
* @return ����
*/
public UFDouble getWanglai () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.WANGLAI);
 } 

/** 
* ��������
*
* @param wanglai ����
*/
public void setWanglai ( UFDouble wanglai) {
this.setAttributeValue( ZhangdanHVO.WANGLAI,wanglai);
 } 

/** 
* ��ȡ����_��Ϣ
*
* @return ����_��Ϣ
*/
public String getWanglai_info () {
return (String) this.getAttributeValue( ZhangdanHVO.WANGLAI_INFO);
 } 

/** 
* ��������_��Ϣ
*
* @param wanglai_info ����_��Ϣ
*/
public void setWanglai_info ( String wanglai_info) {
this.setAttributeValue( ZhangdanHVO.WANGLAI_INFO,wanglai_info);
 } 

/** 
* ��ȡ�ֽ�
*
* @return �ֽ�
*/
public UFDouble getXianjin () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.XIANJIN);
 } 

/** 
* �����ֽ�
*
* @param xianjin �ֽ�
*/
public void setXianjin ( UFDouble xianjin) {
this.setAttributeValue( ZhangdanHVO.XIANJIN,xianjin);
 } 

/** 
* ��ȡӦ��
*
* @return Ӧ��
*/
public UFDouble getYingshou () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YINGSHOU);
 } 

/** 
* ����Ӧ��
*
* @param yingshou Ӧ��
*/
public void setYingshou ( UFDouble yingshou) {
this.setAttributeValue( ZhangdanHVO.YINGSHOU,yingshou);
 } 

/** 
* ��ȡ�Ż�_����
*
* @return �Ż�_����
*/
public UFDouble getYouhui_qt () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_QT);
 } 

/** 
* �����Ż�_����
*
* @param youhui_qt �Ż�_����
*/
public void setYouhui_qt ( UFDouble youhui_qt) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_QT,youhui_qt);
 } 

/** 
* ��ȡ�ֹ��Ż�01
*
* @return �ֹ��Ż�01
*/
public UFDouble getYouhui_sg_01 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_01);
 } 

/** 
* �����ֹ��Ż�01
*
* @param youhui_sg_01 �ֹ��Ż�01
*/
public void setYouhui_sg_01 ( UFDouble youhui_sg_01) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_01,youhui_sg_01);
 } 

/** 
* ��ȡ�ֹ��Ż�02
*
* @return �ֹ��Ż�02
*/
public UFDouble getYouhui_sg_02 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_02);
 } 

/** 
* �����ֹ��Ż�02
*
* @param youhui_sg_02 �ֹ��Ż�02
*/
public void setYouhui_sg_02 ( UFDouble youhui_sg_02) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_02,youhui_sg_02);
 } 

/** 
* ��ȡ�ֹ��Ż�03
*
* @return �ֹ��Ż�03
*/
public UFDouble getYouhui_sg_03 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_03);
 } 

/** 
* �����ֹ��Ż�03
*
* @param youhui_sg_03 �ֹ��Ż�03
*/
public void setYouhui_sg_03 ( UFDouble youhui_sg_03) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_03,youhui_sg_03);
 } 

/** 
* ��ȡ�ֹ��Ż�04
*
* @return �ֹ��Ż�04
*/
public UFDouble getYouhui_sg_04 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_04);
 } 

/** 
* �����ֹ��Ż�04
*
* @param youhui_sg_04 �ֹ��Ż�04
*/
public void setYouhui_sg_04 ( UFDouble youhui_sg_04) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_04,youhui_sg_04);
 } 

/** 
* ��ȡ�ֹ��Ż�05
*
* @return �ֹ��Ż�05
*/
public UFDouble getYouhui_sg_05 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_05);
 } 

/** 
* �����ֹ��Ż�05
*
* @param youhui_sg_05 �ֹ��Ż�05
*/
public void setYouhui_sg_05 ( UFDouble youhui_sg_05) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_05,youhui_sg_05);
 } 

/** 
* ��ȡ�ֹ��Ż�06
*
* @return �ֹ��Ż�06
*/
public UFDouble getYouhui_sg_06 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_06);
 } 

/** 
* �����ֹ��Ż�06
*
* @param youhui_sg_06 �ֹ��Ż�06
*/
public void setYouhui_sg_06 ( UFDouble youhui_sg_06) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_06,youhui_sg_06);
 } 

/** 
* ��ȡ�ֹ��Ż�07
*
* @return �ֹ��Ż�07
*/
public UFDouble getYouhui_sg_07 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_07);
 } 

/** 
* �����ֹ��Ż�07
*
* @param youhui_sg_07 �ֹ��Ż�07
*/
public void setYouhui_sg_07 ( UFDouble youhui_sg_07) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_07,youhui_sg_07);
 } 

/** 
* ��ȡ�ֹ��Ż�08
*
* @return �ֹ��Ż�08
*/
public UFDouble getYouhui_sg_08 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_08);
 } 

/** 
* �����ֹ��Ż�08
*
* @param youhui_sg_08 �ֹ��Ż�08
*/
public void setYouhui_sg_08 ( UFDouble youhui_sg_08) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_08,youhui_sg_08);
 } 

/** 
* ��ȡ�ֹ��Ż�09
*
* @return �ֹ��Ż�09
*/
public UFDouble getYouhui_sg_09 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_09);
 } 

/** 
* �����ֹ��Ż�09
*
* @param youhui_sg_09 �ֹ��Ż�09
*/
public void setYouhui_sg_09 ( UFDouble youhui_sg_09) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_09,youhui_sg_09);
 } 

/** 
* ��ȡ�ֹ��Ż�10
*
* @return �ֹ��Ż�10
*/
public UFDouble getYouhui_sg_10 () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.YOUHUI_SG_10);
 } 

/** 
* �����ֹ��Ż�10
*
* @param youhui_sg_10 �ֹ��Ż�10
*/
public void setYouhui_sg_10 ( UFDouble youhui_sg_10) {
this.setAttributeValue( ZhangdanHVO.YOUHUI_SG_10,youhui_sg_10);
 } 

/** 
* ��ȡ֧Ʊ
*
* @return ֧Ʊ
*/
public UFDouble getZhipiao () {
return (UFDouble) this.getAttributeValue( ZhangdanHVO.ZHIPIAO);
 } 

/** 
* ����֧Ʊ
*
* @param zhipiao ֧Ʊ
*/
public void setZhipiao ( UFDouble zhipiao) {
this.setAttributeValue( ZhangdanHVO.ZHIPIAO,zhipiao);
 } 


  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("hkjt.hg_ZhangdanHVO");
  }
  
  
  /**
   * ���� �Զ����� 11-40
   * HK-����2
   * 2019��1��9��14:42:39
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